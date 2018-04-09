var contactpage = 1;
var loading = false;
var hasNext = true;

$(function(){
	/**
	*建立websocket连接
	*/
	connect();	
	
	initSearch();
	
	var type = $("#type").val();
	if(type == 1){
		/**
		 * 刷新消息列表
		 */
		getContactData();
		
		$("#tab2").removeClass("active");
		$("#tab3").removeClass("active");
		$("#tab1").addClass("active")
	}else if(type == 2){
		/**
		 * 获取通讯录数据
		 */
		getChatOrgData();
		
		$("#tab1").removeClass("active");
		$("#tab3").removeClass("active");
		$("#tab2").addClass("active");
	}else if(type == 3){
		/**
		 * 获取群组数据
		 */
		refreshGroupData(true);
		
		$("#tab1").removeClass("active");
		$("#tab2").removeClass("active");
		$("#tab3").addClass("active");
	}
	
	$("#atab1").click(function(){
		hasNext = true;
		loading = false;
		contactpage = 1;
		$("#comm-contact-list").empty();
		$('#comm-contact-loading').css("display","block");
		/**
		 * 刷新消息列表
		 */
		getContactData();
		
		$("#tab2").removeClass("active");
		$("#tab3").removeClass("active");
		$("#tab1").addClass("active");
	});
	
	$("#atab2").click(function(){
		hasNext = true;
		loading = false;
		contactpage = 1;
		$("#chat-contact-ul").empty();
		$('#chat-contact-loading').css("display","block");
		/**
		 * 获取通讯录数据
		 */
		getChatOrgData();
		
		$("#tab1").removeClass("active");
		$("#tab3").removeClass("active");
		$("#tab2").addClass("active");
	});
	
	$("#atab3").click(function(){
		/**
		 * 获取群组数据
		 */
		refreshGroupData(true);
		
		$("#tab1").removeClass("active");
		$("#tab2").removeClass("active");
		$("#tab3").addClass("active");
	});
	
	$.initInfiniteScroll($("#chat_org_scroll"));
	 
	$("#chat_org_scroll").on('infinite', function() {
		if($("#tab1").hasClass("active")){
			getContactData();
		}
		if($("#tab2").hasClass("active")){
			getChatOrgData();
		}
	});
	
});


/**
 * 刷新群组列表
 */
function refreshGroupData(showIndicator){
	var url = contextPath+"/weixin/workbench/chatcontact/groupList";
    if(showIndicator){
		$.showIndicator();
	}
	$.ajaxPost({
		url:url,
		success:function(data, textStatus, jqXHR){
			if(data.code == 200){
				$("#group_list_ul").empty();
				
				var groupList = data.result;
				for(var i in groupList){
					var group = groupList[i];
					var id = group.id;
					var headImg = group.headImg;
					var name = group.name;
					var remark = group.remark;
					var creater = group.creater;
					var csyser = group.csyser;
					
					if(!headImg || headImg == ""){
						headImg = contextPath+"/static/wxworkbench/img/groud.png";
					}
					var html = "<li>";
					html += "<a href='"+contextPath+"/weixin/workbench/chatcontent/to_detail?groupId="+id+"' class='bdbox clearfix' external>";
					html += "<div class='photo fl'>";
					html += "<img src='"+headImg+"'>";
					html += "</div>";
					html += "<div class='excerpt bdbox'>";
					html += "<strong class='name'>"+name+"</strong>";
					html += "<p class='con util_lineClamp util_clamp1' style='margin:0;'>"+remark+"</p>";
					html += "</div>";
					
					if(creater == csyser){
						html += "<div class='meta1 fr' onclick='toGroupSetting(\""+id+"\");'>";
		    			html += "<span class='icon icon-settings' style='font-size:1.2rem;color:#808080;'></span>";
		    			html += "</div>";
					}
	    			
					html += "</a>";
					html += "</li>";
					$("#group_list_ul").append(html);
				}
				
				if(groupList.length == 0){
					$("#group_list_ul").append("<li style='text-align: center;background: #efeff4;padding-top: 50px;'>暂无群组数据</li>");
				}
			}
			
			if(showIndicator){
				$.hideIndicator();
			}
			//容器发生改变,如果是js滚动，需要刷新滚动
	        $.refreshScroller();
		}
	});
}

/**
 * 获取最近联系人信息
 * @param showIndicator
 */
function getContactData(){
	// 如果正在加载，则退出
    if (loading) return;
    // 设置flag
    loading = true;
    
    if(!hasNext){
        return ;
	}
	var url = contextPath + "/weixin/workbench/chatcontact/recent";
	$.ajaxPost({
		url:url,
		data:{page:contactpage,rows:20},
		success:function(data, textStatus, jqXHR){
			// 重置加载flag
	        loading = false;
			if(contactpage == 1){
	        	$("#comm-contact-list").empty();
	        }
			
			if(data.code == 200){
				var currentPage = data.result.page;
				var totalPages = data.result.totalPages;
				contactpage = currentPage + 1;
				
				var recentContactVoList = data.result.recentContactVoList;
				
				for(var i in recentContactVoList){
					var chatSession = recentContactVoList[i];
					var sessionId = chatSession.id;
					var headImgUrl = chatSession.headImgUrl;
					var name = chatSession.name;
					var lastContent = chatSession.lastContent;
					var lastSendTime = chatSession.lastSendTime;
					var unReadNum = chatSession.unReadNum;
					var type = chatSession.type;
					
					if(!headImgUrl || headImgUrl == ""){
						if(type == 1){
							headImgUrl = contextPath+"/static/wxworkbench/img/head.jpg";
						}else{
							headImgUrl = contextPath+"/static/wxworkbench/img/groud.png";
						}
						
					}
					
					var timStr = $.contactTimeTranslate(lastSendTime);
					
					var html = "<li>";
					html += "<a href='"+contextPath+"/weixin/workbench/chatcontent/to_detail?sessionId="+sessionId+"' class='bdbox clearfix' external>";
					html += "<div class='photo fl'>";
					html += "<img src='"+headImgUrl+"'>";
					html += "</div>";
					html += "<div class='excerpt bdbox'>";
					html += "<strong class='name'>"+name+"</strong>";
					html += "<p class='con util_lineClamp util_clamp1' style='margin:0;'>"+lastContent+"</p>";
					html += "</div>";
					html += "<div class='meta fr'>";
					html += "<span class='time'>"+timStr+"</span>";
					if(unReadNum != 0){
						html += "<span class='num bg-red'>"+unReadNum+"</span>";
					}
					html += "</div>";
					html += "</a>";
					html += "</li>";
					$("#comm-contact-list").append(html);
				}
				
				if(recentContactVoList.length == 0 && currentPage == 1){
					$("#comm-contact-list").append("<li style='text-align: center;background: #efeff4;padding-top: 50px;'>暂无消息数据</li>");
				}
				
				if(currentPage == totalPages ||totalPages == 0){
					hasNext = false;
		            // 删除加载提示符
		            $('#comm-contact-loading').css("display","none");
				}
			}
			//容器发生改变,如果是js滚动，需要刷新滚动
	        $.refreshScroller();
		}
	});
}

function getChatOrgData(){
	var url = contextPath + "/weixin/workbench/chatcontact/getChatOrg";
	// 如果正在加载，则退出
    if (loading) return;
    // 设置flag
    loading = true;
    
    if(!hasNext){
        return ;
	}
	$.ajaxPost({
		url:url,
		data:{page:contactpage,rows:20},
		success:function(data, textStatus, jqXHR){
			// 重置加载flag
	        loading = false;
	        if(contactpage == 1){
	        	$("#chat-contact-ul").empty();
	        }
			if(data.code == 200){
				var currentPage = data.result.page;
				var totalPages = data.result.totalPages;
				var chatOrgVoList = data.result.chatOrgVoList;
				
				contactpage = currentPage + 1;
				for(var i in chatOrgVoList){
					var chatOrgVo = chatOrgVoList[i];
					var orgId = chatOrgVo.id;
					var orgName = chatOrgVo.name;
					var directorOrg = chatOrgVo.directorOrg;
					
					var html = "<dl>";
		    		html += "<dt class='clearfix' org_id='"+orgId+"' director_org='"+directorOrg+"' onclick='accondingOrg(this);'>";
		    		html += "<i class='icon-contactArrow'></i>";
		    		html += "<strong class='name bdbox'>"+orgName+"</strong>";
		    		html += "</dt>";
		    		html += "<dd>";
		    		html += "<ul class='comm-list comm-list-friendList'>";
								
		    		html += "</ul>";
		    		html += "</dd>";
		    		html += "</dl>";
				
					$("#chat-contact-ul").append(html);
				}
				
				if(currentPage == totalPages||totalPages == 0){
					hasNext = false;
		            // 删除加载提示符
					$('#chat-contact-loading').css("display","none");
				}
				
				//容器发生改变,如果是js滚动，需要刷新滚动
		        $.refreshScroller();
			}
		}
	});
}