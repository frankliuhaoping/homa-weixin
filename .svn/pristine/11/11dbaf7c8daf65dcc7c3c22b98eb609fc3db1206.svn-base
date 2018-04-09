function showPictrueDetial(obj){
	var pic = $(obj).attr("src");
	
	$.modal({
		afterText:  '<div class="swiper-container" onclick="closePictureDetail()">'+
	    '<div class="swiper-pagination"></div>'+
	    '<div class="swiper-wrapper">'+
	      '<div class="swiper-slide"><img src="'+pic+'"style="display:block;width:100%"></div>'+
	    '</div>'+
	  '</div>'
    });

	$(".modal.modal-no-buttons.modal-in").css("margin-top"," -150px");

	$(".modal-overlay").click(function(){
		closePictureDetail();
	});
}

function closePictureDetail(){
	$.closeModal();
}

function deleteOrder(id,object){
	
	var submitData = {orderId:id};
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/customer/salesOrder/deleteOrder",
		success:function (data){
			$.toast(data.msg,1000);
			$("a[title='"+id+"']").remove();
			setTimeout(function(){
				history.go(-1);
			},1000);
		},
		error:function(){
			$.toast("网络异常", 1000);
			/*$.hidePreloader();*/
		}
	})
	
}