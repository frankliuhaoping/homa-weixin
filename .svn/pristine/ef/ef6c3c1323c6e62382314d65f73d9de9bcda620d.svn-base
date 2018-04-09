$(function(){
	var stompClient = null;
	//连接服务器的函数
	function connectewm() {
		var socket = new SockJS(websocket_server_domain);
		stompClient = Stomp.over(socket);
		stompClient.connect('', '', function(frame) {		
			  //关闭二维码
	        var sub_chat_dgy = stompClient.subscribe("/message/receive_ewm_message/order/"+current_order_id, function(chat){
	        	if($("#ewm-page").hasClass("page-current")){
	        		closeEwm();
	        	}
	        });			
			}, function() {
				connectewm();
		});
	}	
	connectewm();
});

function closeEwm(){
	var url = contextPath+"/weixin/workbench/salesOrder/cardUpload?orderId="+$("#ewmOrderId").val();
	window.location.href = url;
}
    
function loadComp(){
	
}