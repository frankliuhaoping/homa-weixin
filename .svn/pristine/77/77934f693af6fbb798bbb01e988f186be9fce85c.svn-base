//连接服务器的函数
function connect() {
    var socket = new SockJS(websocket_server_domain);
    var stompClient = Stomp.over(socket);
    stompClient.connect('', '', function(frame){
	    	//订阅聊天消息
	        var sub_chat_dgy = stompClient.subscribe("/message/receive_chat_message/employee/"+current_employee_id, function(chat){
	        	processChat(chat.body)
	        });
        }, function() {
            connect();
    });
}

/**
 *聊天消息处理 
 * @param {Object} data
 */
function processChat(chat){
    console.log(chat);
    chat = JSON.parse(chat);
    /**
     * 当前窗口是否聊天
     */
    var flag = $("#chat_detail").hasClass("page-current");
    if(flag){
    	var sessionId = $("#chat_detail").find("#sessionId").val();
    	if(sessionId == chat.chatSessionId){
    		var uuid = $.uuid();
    		chat.uuid = uuid;
    		chat.inOut = 2;
    		renderChatDetailHtml(chat, true);
    		
    		if(sessionId != ""){
    			refreshUnReadNum(sessionId);
    		}
    	}
    }
    
    var chatIndexLength = $("#chat_index").length;
    var mainPageLength = $("#main-page").length;
    if(chatIndexLength >= 1 || mainPageLength >= 1){
    	refreshSumUnReadNum();
    }
    
    /**
     * 当前窗口是最近联系人窗口
     */
    flag = $("#contact_recent").hasClass("page-current");
    if(chatIndexLength >= 1){
    	if($("#chat_index").find("#tab1").hasClass("active")){
    		getContactData(false);
    	}
	}
}


/**
 * 刷新总的未读条数
 */
function refreshSumUnReadNum(){
	var url = contextPath + "/weixin/workbench/chatcontact/sumUnReadNum";
	$.ajaxPost({
		url:url,
		success:function(data, textStatus, jqXHR){
			var code = data.code;
			if(code == 200){
				var unReadNum = data.result;
				if(unReadNum > 0){
					$("#chat_index").find("#recent_msg_icon").html("<span class='num'>"+unReadNum+"</span>");
					$("#main-page").find("#index_unread_num").html("<span class='num'>"+unReadNum+"</span>");
				}else{
					$("#chat_index").find("#recent_msg_icon").html("");
					$("#main-page").find("#index_unread_num").html("");
				}
				
			}
		}
	});
}