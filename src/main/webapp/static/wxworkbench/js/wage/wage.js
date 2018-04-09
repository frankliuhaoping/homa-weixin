function Wage(){}
Wage.prototype = {
	init: function(){
		var that = this;
		$(function(){
			var pageId = "wage-list-page";
			if($("#wage-detail-page").length > 0){
				pageId = "wage-detail-page";
			}
			
			that.loadEmployeeWageList("#" + pageId);
			
			$("#wage-list-page").off("click", ".iconfont_m");
			$("#wage-list-page").on("click", ".iconfont_m", function(event){
				var $currentYear = $("#wage-list-page input[name='current-year']");
				var $maxYear = $("#wage-list-page input[name='max-year']");
				if($(this).hasClass("left")){
					$currentYear.val(parseInt($currentYear.val()) - 1);
				}else{
					if((parseInt($currentYear.val()) + 1) > parseInt($maxYear.val())){
						return ;
					}
					$currentYear.val(parseInt($currentYear.val()) + 1);
				}
				that.loadEmployeeWageList("#wage-list-page");
			});
			
			if(pageId == "wage-detail-page"){
				var url = e.currentTarget.baseURI;
				var id = url.substring(url.indexOf("detail") + 7);
				if($("a[href*='"+id+"']").find(".con_right").text() == "未阅读"){
					$("a[href*='"+id+"']").find(".con_right").text("已阅").addClass("yi_yue1");
				}
			}
		})
	},
	
	loadEmployeeWageList: function(pageId){
		var that = this;
		var year = $(pageId).find("input[name='current-year']").val();
		$(pageId).find("#c-year").text(year + "年");
		$.showPreloader('请稍候....')
		$.ajax({
			data:{year: year},
			type:"POST",
			dataType:"json",
			url: contextPath + "/weixin/workbench/employee/wage/list",
			success:function (data){
				if(data.code == 200){
					var html = "";
					for(var i in data.result){
						html += that.renderEmployeeWageList(data.result[i]);
					}
					if("" == html){
						html = '<img src="' + contextPath + '/static/wxworkbench/img/zxyhhd-no.jpg" width="100%">';
					}
					$(pageId).find("#div_table").html(html);
				}else{
					$.toast(data.msg, 500);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				$.toast("网络异常", 500);
			},
			complete: function(XHR, TS){
				$.hidePreloader();
			}
		})
	},
	
	renderEmployeeWageList: function(bean){
		var status = "";
		if(bean.isAbnormal){
			status = '<div class="con_right yi_yue"><i class="iconfont_m">&#xe615;</i> 异议</div>';
		}else{
			if(bean.isRead){
				status = '<div class="con_right yi_yue1"><i class="iconfont_m">&#xe613;</i> 已阅</div>';
			}else{
				status = '<div class="con_right"><i class="iconfont_m">&#xe614;</i> 未阅读</div>';
			}
		}
		var html = '<a href="' + contextPath + '/weixin/workbench/employee/wage/detail/' + bean.id + '">';
		html +=	   '  <aside class="aoma_con cc">';
		html +=    '	<div class="con_left">';
		html +=    '		<p class="p1"><font class="fot">' + bean.wageMonth + '</font>月&nbsp;第' + bean.wageVersion + '次通知</p>';
		html +=    '		<p class="p2"><i class="iconfont_m">&#xe622;</i> ' + bean.createdTime + '</p>';
		html +=    '	</div>';
		html +=    		status;
		html +=    '  </aside>';
		html +=    '</a>';
	    return html;
	},
	
	abnormal: function(id){
		$.confirm('确认提出异议?', function () {
			$.post(contextPath + "/weixin/workbench/employee/wage/abnormal", {id: id}, function(data){
				if(data.code == 200){
					$("a[href*='"+id+"']").find(".con_right").text("异议").removeClass("yi_yue1").addClass("yi_yue");
				}
				$.alert(data.msg);
			}, 'json');
	    });
	}
};

var wage = new Wage();
wage.init();