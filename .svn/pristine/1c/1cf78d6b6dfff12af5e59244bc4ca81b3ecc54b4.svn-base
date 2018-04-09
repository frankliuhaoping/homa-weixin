var loading = false;
var lastTime = null;
var sessionId = null;
var receiverId = null;
var groupId = null;
var senderId = null;
var senderName = null;
var senderHeadImg = null;
var chatType = 1;
$(function(){
	/**
	*建立websocket连接
	*/
	connect();
	
	lastTime = null;
	sessionId = $("#sessionId").val();
	chatType = $("#chatType").val();
	receiverId = $.getQueryString("otherId");
	groupId = $.getQueryString("groupId");
	initChatKeyborad();
	initResizeIMG();
	getDetailData(true, true);
	
	if(sessionId != ""){
		refreshUnReadNum(sessionId);
	}
	
	$(".content-msg-detail").click(function(){
		$(".emoji_list_ul").hide();
	});
	
	/**
	 * 图片点击放大
	 */
	 $("#j-msg-detail-list").on("click",".msg_img",function(){
		 var length = $(".popup-img").length;
		 if(length > 1){
			 $(".popup-img")[0].remove();
		 }
	     var imgsrc = $(this).attr("src");
	     $("#big_img_p").attr("src", imgsrc);
	     
		 $.popup(".popup-img");
		 $(".modal-overlay-visible").remove();
	 });
	 
	 $("#chat_detail").on("click",".popup-img",function(){
		 $.closeModal(".popup-img");
	 });
	 
	 $.initPullToRefresh($("#msg_content_list"));
	 $("#msg_content_list").on('refresh',function(e) {
		 getDetailData(false, false);
	 });
})

function getDetailData(showIndicator, scrollToBottom){
	var url = contextPath + "/weixin/workbench/chatcontent/detail";
	if(showIndicator){
		$.showIndicator();
	}
	// 如果正在加载，则退出
    if (loading) return;
    // 设置flag
    loading = true;
	$.ajaxPost({
		url:url,
		data:{lastTime:lastTime,sessionId:sessionId,receiverId:receiverId},
		success:function(data, textStatus, jqXHR){
			// 重置加载flag
	        loading = false;
	        
			if(data.code == 200){
				senderId = data.result.senderId;
				senderName = data.result.senderName;
				senderHeadImg = data.result.senderHeadImg;
				
				var contentList = data.result.contentVoList;
				console.log(contentList)
				for(var i in contentList){
					var chatcontent = contentList[i];
					
					var uuid = $.uuid();
					chatcontent.uuid = uuid;
					
					var preSenderId = chatcontent.senderId;
					if(preSenderId == senderId){
						chatcontent.inOut = 1;
					}else{
						chatcontent.inOut = 2;
					}
					renderChatDetailHtml(chatcontent, false);
					
					if(i == contentList.length-1){
						lastTime = chatcontent.sendTime;
					}
				}
				
				if(lastTime == null){
					lastTime = data.result.currentTime;
				}
				if(scrollToBottom){
					//滚动到底部
			        var e = document.getElementById("msg_content_list");
			        e.scrollTop=e.scrollHeight;
				}else{
					// 加载完毕需要重置
			        $.pullToRefreshDone('#msg_content_list');
				}
				
			}
			if(showIndicator){
				$.hideIndicator();
			}
		}
	});
}

/**
 * 初始化聊天键盘
 */
var resetTextarea = null;
function initChatKeyborad(){
	//init emoji
    
    var k_i_height =  $(".toolbar")[0].offsetHeight;
    var i_b_height =  $("#message_box")[0].offsetHeight;

    resetTextarea = function(){
   	 	$(".toolbar,#message_box").css("height","");
   	 	var height = $("#message_box")[0].offsetHeight;
        var diff = height - $("#message_box")[0].clientHeight; 
        var scrollHeight = $("#message_box")[0].scrollHeight;
        if (scrollHeight + diff > height) {
            var newi_b_height = scrollHeight + diff;
            var newk_i_height = k_i_height + (newi_b_height - i_b_height);
            $("#message_box").height(newi_b_height);
            $(".toolbar").height(newk_i_height);
         if(scrollHeight > 112){
       	  $("#message_box").height(126)
       	  $(".toolbar").height(144)
            }
        }
    }
    
    var emoji_list = [
        "[微笑]","[撇嘴]","[色]","[发呆]","[得意]","[流泪]","[害羞]","[闭嘴]","[睡]","[大哭]","[尴尬]","[发怒]","[调皮]","[呲牙]","[惊讶]","[难过]","[酷]","[冷汗]","[抓狂]","[吐]","[偷笑]","[愉快]","[白眼]","[傲慢]","[饥饿]","[困]","[惊恐]","[流汗]","[憨笑]","[悠闲]","[奋斗]","[咒骂]","[疑问]","[嘘]","[晕]","[疯了]","[衰]","[骷髅]","[敲打]","[再见]","[擦汗]","[抠鼻]","[鼓掌]","[糗大了]","[坏笑]","[左哼哼]","[右哼哼]","[哈欠]","[鄙视]","[委屈]","[快哭了]","[阴险]","[亲亲]","[吓]","[可怜]","[菜刀]","[西瓜]","[啤酒]","[篮球]","[乒乓]"                                                                                  
    ];
    var emoji_list_li="";
    for(var i=1;i<61; i++){
        if(i%20 == 1){
             emoji_list_li += '<div class="swiper-slide"><ul class="emoji_list_ul">'
        }
        if(i%20 == 0){
             emoji_list_li += '<li class="emoji_list_li" data-emoji='+emoji_list[i-1]+'><img src="'+contextPath+'/static/wxworkbench/img/emojicons/ace_emoji_'+i+'.png" alt=""></li><li class="emoji_delete"><img src="'+contextPath+'/static/wxworkbench/img/emojicons/ace_emoji_delete.png" alt=""></li></ul></div> ';continue;  
        }
        emoji_list_li += '<li class="emoji_list_li"  data-emoji='+emoji_list[i-1]+'><img src="'+contextPath+'/static/wxworkbench/img/emojicons/ace_emoji_'+i+'.png" alt=""></li>';
    }
    $(".swiper-wrapper.emoji_list").html(emoji_list_li);
    $(".emoji_list_ul li").width($("body").width()/7);

    $(".keyborad").on("click","._emoji",function(){
        //$("#message_box").focus();
        $(".emoji_list_ul").toggle();
    })
   
    $(".swiper-container").swiper();
    
    var emoji_list_flag = true;
    $(".keyborad").on("click",".emoji_list_li",function(e){
        emoji_list_flag = false;
        e.preventDefault();
        var message_box = $("#message_box");
        var mes = message_box.val();
        var len = mes.length;
        var emoji_str = $(this).attr("data-emoji");
        var demoji_len = emoji_str.length;
        var curpo = message_box[0].selectionStart;
        var message = mes.substr(0,message_box[0].selectionStart) + emoji_str + mes.substr(message_box[0].selectionStart,len);
        message_box.val(message);
        message_box[0].selectionStart = message_box[0].selectionEnd = curpo + demoji_len;
        resetTextarea();
        message_box.blur();
        emoji_list_flag = true;
        
        $(".link.option").hide();
        $(".link.send-message").show();
    })
    
    
    $(".keyborad").on("click",".emoji_delete",function(){
        var message = $("#message_box").val();
        $("#message_box").val(message.substring(0,message.length-1));
        resetTextarea();
    })
    
    $(".keyborad").on("click",".more_option",function(){
        $(".emoji_list_ul").hide();
    })
    
    $("#message_box").focus(function(){
        if(emoji_list_flag){$(".emoji_list_ul").hide()}
        if($(this).val().length > 0){
        	$(".link.option").hide();
        	
        	//$(".link.send-message").show();
        	$(".link.send-message").css("display","-webkit-box");

        }
    })
    
    $("#message_box").on('input',function(){
   	 	resetTextarea();
   	 	var message = $("#message_box").val();
   	 	if(!message || message.length == 0){
   	        $(".link.option").css("display","-webkit-box");
   	        $(".link.send-message").hide();
   	        return;
   	 	}
        $(".link.option").hide();
        $(".link.send-message").css("display","-webkit-box");
    })
    
    var flags,touchflag = true;
    $(".keyborad i.icon.voice").on("click",function(){
        $(".key_voice,.key_inp").toggle();
        resetTextarea();
    })
    
}


function sendTextMsg(){
	var message = $("#message_box").val();
	message =message.replace(/[\n]/g,"<br>");
	sendMsg(message, 1);
	
	$(".link.option").css("display","-webkit-box");
    $(".link.send-message").hide();
    $(".emoji_list_ul").hide();
    $("#message_box").val("");
    resetTextarea();
}

function initResizeIMG(){
	$('#upload_image').localResizeIMG({
	    width: 360,
	    quality: 0.8,
	    success: function (result) {
	        var status = true;
	        if (result.height > 1600) {
	            status = false;
	            alert("照片最大高度不超过1600像素");
	        }
	        if (status) {
	        	sendMsg(result.base64, 2);
	        }
	    }
	});
}
/**
 *	发送文本数据
 *	inOut 1.我发的   2.其他人发的
 */
function sendMsg(content, contentType){
    var sendTime = new Date().getTime();
    var uuid = $.uuid();
    var chatMsg = {
    	"inOut": 1,
        "content" : content,
        "contentType" : contentType,
        "voiceTime" : 0,
        "sendTime" : sendTime,
        "uuid":uuid,
        "receiverId" : receiverId,
        "groupId" : groupId,
        "chatSession.id" : sessionId,
        "name":senderName,
        "headImgUrl":senderHeadImg,
        "loading":true
    }

    //页面增加一条聊天消息
    renderChatDetailHtml(chatMsg, true);
    //滚动到底部
    window.scrollTo(0, document.body.scrollHeight);
    
    if(contentType == 1){
    	/**
         * 文本
         */
    	ajaxPostMsg(chatMsg);
    }else if(contentType == 2){
    	/**
    	 * 图片
    	 */
    	ajaxPostMsg(chatMsg);
    }else if(contentType == 3){
    	/**
    	 * 语音
    	 */
    }
   
}


/**
 * ajax提交数据
 * @param content
 * @param contentType
 * @param uuid
 */
function ajaxPostMsg(chatMsg){
    if (chatMsg.content && chatMsg.content != "") {
        var param = chatMsg;
        var url = contextPath + "/weixin/workbench/chatcontent/chatadd";
        $.ajaxPost({
    		url:url,
    		data:param,
    		success:function(data, textStatus, jqXHR){
    			var cuuid = data.result.uuid;
    			if(data.result.sessionId != null 
    					&& data.result.sessionId != ""){
    				sessionId = data.result.sessionId;
    			}
    			$("#"+cuuid).find(".loading").remove();
    		}
    	});
    }
}

/**
 *页面增加一条聊天消息 
 * @param {Object} chatMsg
 * @param {Object} isBottom 是否加到底部
 */
function renderChatDetailHtml(chatMsg,isBottom){
	var inOut = chatMsg.inOut;
    var content = chatMsg.content;
    var contentType = chatMsg.contentType;
    var voiceTime = chatMsg.voiceTime;
    var sendTime = chatMsg.sendTime;
    var uuid = chatMsg.uuid;
    var name = chatMsg.name;
    var headImgUrl = chatMsg.headImgUrl;
    var loading = chatMsg.loading;
    
    var timeStr = $.chatTimeTranslate(sendTime);
    
    if(headImgUrl == null || headImgUrl == ''){
    	headImgUrl = contextPath+"/static/wxworkbench/img/head.jpg";
	}
    /**
     * send_type代表发送者
     * 1.导购
     * 2.顾客
     * 3.系统消息
     */
    var html = "";
    if(inOut == 1){
    	html += "<li class='time'>"+timeStr+"</li>";
    	html += "<li class='right' id='"+uuid+"'>";
    	if(loading){
    		html += "<div class='loading'></div>";
    	}
    	if(chatType == 2){
    		html += "<section style='display:inline-block;width: 70%;'>";
    		html += "<p style='font-size: 12px;margin-right: 10px;'>"+name+"</p>";
    	}
    	
    	html += "<section class='msg'>";
    	if(contentType == 1){
    		html += emojiParse(content);
    	}else if(contentType == 2){
    		html += "<img src='"+content+"' class='msg_img'>";
    	}
    	html += "</section>";
    	
    	if(chatType == 2){
    		html += "</section>";
    	}
    	
    	html += "<section class='photo'>";
    	html += "<img src='"+headImgUrl+"' alt=''>";
    	html += "</section>";
    	html += "</li>";
    	
    	if(chatType == 2){
    		html += "</section>";
    	}
    }else if(inOut == 2){
    	html += "<li class='time'>"+timeStr+"</li>";
    	html += "<li class='left' id='"+uuid+"'>";
    	
    	html += "<section class='photo'>";
    	html += "<img src='"+headImgUrl+"' alt=''>";
    	html += "</section>";
    	
    	if(chatType == 2){
    		html += "<section style='display:inline-block;width: 70%;'>";
    		html += "<p style='font-size: 12px;margin-left: 10px;'>"+name+"</p>";
    	}
    	
    	html += "<section class='msg'>";
    	if(contentType == 1){
    		html += emojiParse(content);
    	}else if(contentType == 2){
    		html += "<img src='"+content+"' class='msg_img'>";
    	}
    	html += "</section>";
    	
    	if(chatType == 2){
    		html += "</section>";
    	}
    	
    	html += "</li>";
    }
    
    if(isBottom){
    	$("#j-msg-detail-list").append(html);
        
    	 //滚动到底部
        var e = document.getElementById("msg_content_list");
        e.scrollTop=e.scrollHeight;
    }else{
    	$("#j-msg-detail-list").prepend(html);
    }
}

/**
 * 刷新未读条数
 */
function refreshUnReadNum(currentSessionId){
	var url = contextPath + "/weixin/workbench/chatcontent/updateUnReadNumZero";
	$.ajaxPost({
		url:url,
		data:{sessionId:currentSessionId},
		success:function(data, textStatus, jqXHR){
			
		}
	});
}

/**
 * 表情符替换
 * @param text
 * @returns
 */
function emojiParse(text){
	var emoji_list = [
      "[微笑]","[撇嘴]","[色]","[发呆]","[得意]","[流泪]","[害羞]","[闭嘴]","[睡]","[大哭]","[尴尬]","[发怒]","[调皮]","[呲牙]","[惊讶]","[难过]","[酷]","[冷汗]","[抓狂]","[吐]","[偷笑]","[愉快]","[白眼]","[傲慢]","[饥饿]","[困]","[惊恐]","[流汗]","[憨笑]","[悠闲]","[奋斗]","[咒骂]","[疑问]","[嘘]","[晕]","[疯了]","[衰]","[骷髅]","[敲打]","[再见]","[擦汗]","[抠鼻]","[鼓掌]","[糗大了]","[坏笑]","[左哼哼]","[右哼哼]","[哈欠]","[鄙视]","[委屈]","[快哭了]","[阴险]","[亲亲]","[吓]","[可怜]","[菜刀]","[西瓜]","[啤酒]","[篮球]","[乒乓]"                                                                                  
    ];
	              
	var regx = /(\[[\u4e00-\u9fa5]*\w*\]){1}/g; 
    var rs = text.match(regx);  
    if (rs) {  
        for (var i = 0; i < rs.length; i++) {
            var symbol = rs[i];  
            var imgpath = null;
            for(var j=0;j<emoji_list.length; j++){
            	if(symbol == emoji_list[j]){
            		imgpath = contextPath+"/static/wxworkbench/img/emojicons/ace_emoji_"+(j+1)+".png";
            		break;
            	}
            }
            if(imgpath){
                var t = "<img src='"+imgpath+"' style='width:30px;'/>";  
                text = text.replace(symbol, t);  
            }
        }  
    }
    return text;
}

/**
 * 返回到聊天首页
 */
function backToIndex(){
	var url = contextPath + "/weixin/workbench/chatcontact/index";
	if(groupId && groupId != ""){
		url +="?type=3"
	}else if(receiverId && receiverId != ""){
		url +="?type=2"
	}
	window.location.href = url;
}