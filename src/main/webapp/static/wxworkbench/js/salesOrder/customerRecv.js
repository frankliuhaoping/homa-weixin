function saveCustomerMsg(){
	
	var customerName = $("#customerName").val();
	var customerAddr = $("#customerAddr").val();
	var customerPhone = $("#customerPhone").val();
	var orderId = $("#orderId").val();
	if(customerName=="" || !customerName){
		$.toast("请输入收货人名字",1000);
		return ;
	}
	if(customerAddr=="" || !customerAddr){
		$.toast("请输入收货人地址",1000);
		return ;
	
	}
	
	var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	 if (!reg.test(customerPhone)) {
		 $.toast("请输入正确的收货人号码",1000);
		 return ;
	 }
	
	if(customerPhone=="" || !customerPhone){
		$.toast("请输入收货人号码",1000);
		return ;
	}
	
	
	var submitData = {customerName:customerName,customerAddr:customerAddr,
			customerPhone:customerPhone,orderId:orderId};
	$.showPreloader('提交数据中....')
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/customer/salesOrder/customerSave",
		success:function (data){
			$.hidePreloader();
			if(data.code==200){
				$.toast(data.msg,1000);
				setTimeout(function(){
					WeixinJSBridge.call('closeWindow');
				},2000);
			}else{
				$.toast(data.msg,1000);
			}
		},
		error:function(){
			$.toast("网络异常", 1000);
			$.hidePreloader();
		}
	})
	
}
