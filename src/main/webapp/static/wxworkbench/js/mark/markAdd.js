function MarkAdd(){}
MarkAdd.prototype = {
	init: function(){
		var that = this;
		
		$(function(){
			that.pageId = "#mark-add-page";
			that.flag = false;
		});
	},
	
	fileChange:function(files) {
		if (files.length > 0) {
			$.showPreloader('图片加载中....')
			for(var i = 0; i < files.length; i++){
				var file = files[i];
				$.compressImage({
					file: file,
					width : '600',
					quality : '0.8', // 压缩率，默认值为0.8,如果quality是1 宽和高都未设定 则上传原图
					success : function(base64, type){
						$(markAdd.pageId).find(".more_img").append($('<img width="45px" height="45px">').attr("src", base64).css("margin-right", "5px"));
					}
				});
			}
			$.hidePreloader();
		}
	},
	
	send: function(){
		var text = $(markAdd.pageId).find("#text").val();
		var images = $(markAdd.pageId).find(".more_img img");
		var imagesData = "";
		images.each(function(index, ele){
			imagesData += ele.src.split(",")[1] + ",";
		});
		imagesData.substring(0, imagesData.length-1);
		if($.trim(text) == "" && images.length == 0){
			$.alert('请说点什么或发点什么吧');
			return ;
		}
		markAdd.flag = true;
		markAdd.backFlag1 = true;
		markAdd.backFlag2 = true;
		$(markAdd.pageId).find("#fileForm").append($("<input>").attr("name", "imagesData").attr("value", imagesData).attr("type", "hidden"));
		$(markAdd.pageId).find("#fileForm").append($("<input>").attr("name", "content").attr("value", text).attr("type", "hidden"));
		
		$.showPreloader();
		$(markAdd.pageId).find("#fileForm").submit();
	},
	
	uploadSuccess: function(){
		if(markAdd.flag){
			var result = $(window.frames["fileFrame"].document).find("body").text();
			result = JSON.parse(result);
			if(result.code == 200){
				$("input[name='search.id_eq']").val(result.result);
				var url = contextPath + "/weixin/workbench/marketing/experience/mine?employeeId=" + $("#employeeId").val();
				
				window.location.href = url;
				
			}else{
				$.alert(result.msg);
			}
			markAdd.flag = false;
			$.hidePreloader();
		}
	},
	
	test: function(){
		
	}
}
var markAdd = new MarkAdd();
markAdd.init();