$(function(){
	$('#uploadFileCard').UploadImg({
		width : '800',
		//height : '40',
		quality : '0.5', // 压缩率，默认值为0.8,如果quality是1 宽和高都未设定 则上传原图
		mixsize : '10000000',
		type : 'image/png,image/jpg,image/jpeg,image/pjpeg,image/gif,image/bmp,image/x-png',
		beforeInit : function(){
			$.showPreloader('图片加载中....');
		},
		before : function(blob) {
		},
		success : function(base64, type) {
				try{
				$('#cardUploadMsg').hide();
				//alert(this.result);
				$('#cardUploadMsg_after').attr("src", base64);
				$('#cardUploadMsg_after').css("display", "block");
				$("#uploadFileCard").css({
					"height" : 180,
					"width" : 240,
					"marginLeft" : -120
				});
				$.hidePreloader();
				}catch(e){
					alert(e.message)
				}		
		}
	});
	
	$.getJSON(contextPath + '/weixin/getJsapiTicket', {
		url : weiXinConfigUrl
	}, function(data) {
		wx.config({
			debug : weiXinConfigDebug,
			appId : data.appid,
			timestamp : data.timestamp,
			nonceStr : data.noncestr,
			signature : data.signature,
			jsApiList : [ 'scanQRCode' ]
		});
	});

	wx.ready(function() {
		document.querySelector('#scanQRCode').onclick = function() {
			wx.scanQRCode({
				needResult : 1,// 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				// scanType : [ "barCode" ], 可以指定扫二维码还是一维码，默认二者都有
				desc : 'scanQRCode desc',
				success : function(data) {
					var barCode = data.resultStr.split(',');
					if (barCode.length > 1) {
						$('#invokeNo').val(barCode[1]);
					} else {
						$('#invokeNo').val(data.resultStr);
					}
				}
			});
		};
	});
});

function uploadBtnClick(id) {
	var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
	if (ie) {
		document.getElementById("uploadFileCard").click();
	} else {
		alert("click")
		var mouseobj = document.createEvent("MouseEvents");
		mouseobj.initEvent("click", true, true);
		document.getElementById("uploadFileCard").dispatchEvent(mouseobj);
	}
}

function Cardfilechange(files) {
	if (files.length > 0) {
		var file = files[0];
		console.log(file);
		//alert(file.type);
		//alert("image/jpeg" == file.type);
		if((file.type!="image/jpeg" && file.type!="image/png")){
			$.toast("文件格式不正确",1000);
			return ;
		}
		console.log(file);
		var read = new FileReader();
		read.onloadstart = (function() {
			
			$.showPreloader('图片加载中....')
		});
		read.onload = (function() {
			try{
			$('#cardUploadMsg').hide();
			//alert(this.result);
			$('#cardUploadMsg_after').attr("src", this.result);
			$('#cardUploadMsg_after').css("display", "block");
			$("#uploadFileCard").css({
				"height" : 180,
				"width" : 240,
				"marginLeft" : -120
			});
			$.hidePreloader();
			}catch(e){
				alert(e.message)
			}
		});

		read.readAsDataURL(file);
	}
}

function uploadSalesOrderMsg() {
	// 图片
	var picture = $('#cardUploadMsg_after').attr("src");
	var temp = $('#cardUploadMsg').attr("src");
	if(picture=="none" && temp=="/homa-weixin/static/wxworkbench/img/camera.jpg"){
		$.toast("请拍照上传凭证",1000);
		return ;
	}
	// 凭证
	var invokeNo = $("#invokeNo").val();
	if(invokeNo==""){
		$.toast("请填写好凭证",1000);
		return;
	}
	// orderID
	var orderId = $('#cardUploadOrderId').val();
	//alert("订单id ： "+orderId);
	//alert("picture ： "+picture);
	var submitData = {
		orderId : orderId,
		picture : picture,
		invokeNo : invokeNo
	};
	
	
	
	
//提示是否重复
	$.ajax({
		data:{invoke:invokeNo},
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/salesOrder/beforeSave",
		success:function (data){
			if(data.code!=200){
				if(confirm("今天内该凭证已经提交过订单是否再次提交?")){
					//ajax 调用
					postInvoke(submitData);
				}else{
					return ;
				}
			}else{
				postInvoke(submitData);
			}
			
		},
		error:function(){
			$.toast("网络异常", 1000);
			$.hidePreloader();
		}
	})
	
	
	


}


function postInvoke(submitData){
	$.showPreloader('提交数据中....')
	$.ajax({
		data : submitData,
		type : "POST",
		dataType : "json",
		url : contextPath + "/weixin/workbench/salesOrder/saveInvoke",
		success : function(data) {
			$.hidePreloader();
			if (data.code == 200) {
				$.toast("保存成功", 1000);
				//deletePageNode();
				//location.href = contextPath + "/weixin/workbench/index";
				// $.router.loadPage("#salesOrder-page");
				//$.router.loadPage(contextPath+"/weixin/workbench/salesOrder/index");
				window.location.href = contextPath+"/weixin/workbench/salesOrder/index";
			} else {
				$.toast(data.msg, 1000);
			}

		},
		error : function() {
			$.toast("网络异常", 1000);
			$.hidePreloader();
		}
	})
}