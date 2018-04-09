var initSearch = function(){
		var $page=$(document),
			$searchMain=$("#j-searchMain"),
			$searchMask=$("#j-searchMask");//搜索内容遮罩
		//自动计算遮罩真实高度
		$searchMask.height($(window).height()-48);
		//显示搜索容器
		$page.find(".j-showSearch").click(function(){
			$("#seach-friend-result").empty();
			$searchMask.find(".j-noResult").show();
			$searchMain.find(".con-search").addClass("show");
			$searchMain.find(".j-conSearchIpt").focus();
			$searchMask.show();
			return false;
		});

		//隐藏搜索容器
		var hideSearch=function(){
			$searchMain.find(".con-search").removeClass("show");
			$searchMain.find(".j-conSearchIpt").val("");
			$searchMask.hide();
			return false;
		}
		$page.find(".j-hideSearch").click(hideSearch);
		$searchMask.click(hideSearch);

		//清除文字
		$page.find(".j-clearTxt").click(function(){
			$(this).hide().siblings(".j-conSearchIpt").val("").focus().change();
		});

		//搜索响应
		$page.find(".j-conSearchIpt").on("change keyup",function(){
			var $ipt=$(this),
				val=$ipt.val();

			if(val!=""){
				$ipt.siblings(".j-clearTxt").show();
				var url = contextPath + "/weixin/workbench/chatcontact/searchEmployee";
				$.ajaxPost({
					url:url,
					data:{keyword:val},
					success:function(data, textStatus, jqXHR){
						if(data.code == 200){
							$("#seach-friend-result").empty();
							var result = data.result;
							for(var i in result){
								var employee = result[i];
			    				var id = employee.id;
			    				var name = employee.name;
			    				var remark = employee.remark;
			    				var headImgUrl = employee.headImgUrl;
								
			    				if(!headImgUrl || headImgUrl == ""){
			    					headImgUrl = contextPath+"/static/wxworkbench/img/head.jpg";
								}
			    				
			    				var html = "<li>";
			    				html += "<a href='javascript:void(0);' class='bdbox clearfix' onclick='toChatDetail(\""+id+"\");' external>";
			    				html += "<div class='photo fl'>";
			    				html += "<img src='"+headImgUrl+"'>";
			    				html += "</div>";
			    				html += "<div class='excerpt bdbox'>";
			    				html += "<strong class='name'>"+name+"</strong>";
			    				html += "<p class='con' style='margin:0;'>"+remark+"</p>";
			    				html += "</div>";
			    				html += "</a>";
			    				html += "</li>";
			    				$("#seach-friend-result").append(html);
							}
							if(result.length == 0){
								$searchMask.find(".j-noResult").show();
							}else{
								$searchMask.find(".j-noResult").hide();
							}
						}
					}
				});
			}else{
				$ipt.siblings(".j-clearTxt").hide();
				$searchMask.find(".j-noResult").hide();
			}
		});

		//阻止搜索结果的冒泡
		$searchMask.find(".j-noResult").click(function(){
			return false;
		});
}


var groupInitSearch = function(){
	var $page=$(document),
		$searchMain=$("#j-searchMain"),
		$searchMask=$("#j-searchMask");//搜索内容遮罩
	//自动计算遮罩真实高度
	$searchMask.height($(window).height()-48);
	//显示搜索容器
	$page.find(".j-showSearch").click(function(){
		$("#seach-friend-result").empty();
		$searchMask.find(".j-noResult").show();
		$searchMain.find(".con-search").addClass("show");
		$searchMain.find(".j-conSearchIpt").focus();
		$searchMask.show();
		return false;
	});

	//隐藏搜索容器
	var hideSearch=function(){
		$searchMain.find(".con-search").removeClass("show");
		$searchMain.find(".j-conSearchIpt").val("");
		$searchMask.hide();
		return false;
	}
	$page.find(".j-hideSearch").click(hideSearch);
	$searchMask.click(hideSearch);

	//清除文字
	$page.find(".j-clearTxt").click(function(){
		$(this).hide().siblings(".j-conSearchIpt").val("").focus().change();
	});

	//搜索响应
	$page.find(".j-conSearchIpt").on("change keyup",function(){
		var $ipt=$(this),
			val=$ipt.val();

		if(val!=""){
			$ipt.siblings(".j-clearTxt").show();
			var url = contextPath + "/weixin/workbench/chatcontact/searchEmployee";
			$.ajaxPost({
				url:url,
				data:{keyword:val},
				success:function(data, textStatus, jqXHR){
					if(data.code == 200){
						$("#seach-friend-result").empty();
						var result = data.result;
						for(var i in result){
							var employee = result[i];
		    				var id = employee.id;
		    				var name = employee.name;
		    				var remark = employee.remark;
		    				var headImgUrl = employee.headImgUrl;
							
		    				if(!headImgUrl || headImgUrl == ""){
		    					headImgUrl = contextPath+"/static/wxworkbench/img/head.jpg";
							}
		    				
			    			var html = "<li>";
			    			html += "<a href='javascript:void(0);' class='bdbox clearfix' onclick='checkboxSel(\""+id+"\",\""+name+"\",\""+headImgUrl+"\");' external>";
			    			
			    			html += "<div class='photo fl'>";
			    			html += "<img src='"+headImgUrl+"'>";
			    			html += "</div>";
			    			html += "<div class='excerpt bdbox'>";
			    			html += "<strong class='name'>"+name+"</strong>";
			    			html += "<p class='con util_lineClamp util_clamp1' style='margin:0;'>";
			    			html += remark;
			    			html += "</p>";
			    			html += "</div>";
			    			
			    			html += "<div style='float:right;'>";
			    			
			    			if(currentCheckedEmployees.indexOf(id) > -1){
			    				html += "<div class='icon icon-check chatgroup_checkbox cgcb_active' id='m_checkbox_"+id+"'></div>";	
			    			}else{
			    				html += "<div class='icon chatgroup_checkbox' id='m_checkbox_"+id+"'></div>";	
			    			}
			    			
			    			html += "</div>";
			    			
			    			html += "</a>";
			    			html += "</li>";
			    			
		    				$("#seach-friend-result").append(html);
						}
						if(result.length == 0){
							$searchMask.find(".j-noResult").show();
						}else{
							$searchMask.find(".j-noResult").hide();
						}
					}
				}
			});
		}else{
			$ipt.siblings(".j-clearTxt").hide();
			$searchMask.find(".j-noResult").hide();
		}
	});

	//阻止搜索结果的冒泡
	$searchMask.find(".j-noResult").click(function(){
		return false;
	});
}