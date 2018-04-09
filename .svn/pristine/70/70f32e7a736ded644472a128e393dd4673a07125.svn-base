Array.prototype.indexOf = function(val) {              
    for (var i = 0; i < this.length; i++) {  
        if (this[i] == val) return i;  
    }  
    return -1;  
}; 

var peopleLoading = false;
var currentCheckedEmployees = [];
function accondingOrg(t){
	if(peopleLoading)return;
	peopleLoading = true;
	
	var content = $(t);
	var $parent=content.parent("dl");
	$parent.siblings("dl").removeClass("open");
	
	var orgId = content.attr("org_id");
	var directorOrg = content.attr("director_org");
	var hasLoaded = content.attr("has_loaded");
	var is_group = content.attr("is_group");
	if(orgId && orgId != "" && !hasLoaded){
		var url = contextPath + "/weixin/workbench/chatcontact/getChatOrgEmployee";
		$.showIndicator();
		
		$.ajaxPost({
			url:url,
			data: {orgId:orgId,directorOrg:directorOrg},
			success:function(data, textStatus, jqXHR){
				peopleLoading = false;
				if(data.code == 200){
					var result = data.result;
					var ul = $parent.find("ul");
	    			for(var i in result){
	    				var employee = result[i];
	    				var id = employee.id;
	    				var name = employee.name;
	    				var remark = employee.remark;
	    				var headImgUrl = employee.headImgUrl;
	    				
	    				if(!headImgUrl || headImgUrl == ""){
	    					headImgUrl = contextPath+"/static/wxworkbench/img/head.jpg";
						}
	    				
	    				if(is_group){
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
			    				html += "<div class='icon icon-check chatgroup_checkbox cgcb_active' id='checkbox_"+id+"'></div>";	
			    			}else{
			    				if(id == current_employee_id){
			    					html += "<div class='icon chatgroup_checkbox' id='checkbox_"+id+"' style='background: #ddd;'></div>";
			    				}else{
			    					html += "<div class='icon chatgroup_checkbox' id='checkbox_"+id+"'></div>";
			    				}
			    				
			    			}
			    			
			    			html += "</div>";
			    			
			    			html += "</a>";
			    			html += "</li>";
	    				}else{
	    					var html = "<li>";
			    			html += "<a href='javascript:void(0);' class='bdbox clearfix' onclick='toChatDetail(\""+id+"\");' external>";
			    			html += "<div class='photo fl'>";
			    			html += "<img src='"+headImgUrl+"'>";
			    			html += "</div>";
			    			html += "<div class='excerpt bdbox'>";
			    			html += "<strong class='name'>"+name+"</strong>";
			    			html += "<p class='con util_lineClamp util_clamp1' style='margin:0;'>";
			    			html += remark;
			    			html += "</p>";
			    			html += "</div>";
			    			html += "<div class='meta1 fr' onclick='toPersonalDetail(\""+id+"\");'>";
			    			html += "<span class='icon icon-me' style='font-size: 1.2rem;color:#808080;'></span>";
			    			html += "</div>";
			    			html += "</a>";
			    			html += "</li>";
	    				}
		    			ul.append(html);
	    			}
	    			content.attr("has_loaded",true);
				}
    			$.hideIndicator();
    			
    			//容器发生改变,如果是js滚动，需要刷新滚动
    	        $.refreshScroller();
    	        
    			$parent.toggleClass("open");
			}
		});
	}else{
		$parent.toggleClass("open");
		peopleLoading = false;
	}
}

function toChatDetail(id){
	if(id == current_employee_id){
		$.toast("不能与自己聊天");
		return;
	}
	var url = contextPath+"/weixin/workbench/chatcontent/to_detail?otherId="+id
	window.location.href = url;
	return false;
}
/**
 * 跳转到个人详情
 */
function toPersonalDetail(id){
	sessionId = null;
	receiverId = null;
	
	var url = contextPath+"/weixin/workbench/chatgroup/personal?otherId="+id
	window.location.href = url;
	
	event.stopPropagation();
	event.preventDefault();
}

function toGroupSetting(id){
	var url = contextPath + "/weixin/workbench/chatgroup/addOrUpdateInit?groupId="+id;
	window.location.href = url;
	
	event.stopPropagation();
	event.preventDefault();
}

function checkboxSel(id, name, headImgUrl){
	if(id == current_employee_id)return;
	
	var index = currentCheckedEmployees.indexOf(id);
	if(index > -1){
		$("#checkbox_"+id).removeClass("icon-check");
		$("#checkbox_"+id).removeClass("cgcb_active");
		
		$("#m_checkbox_"+id).removeClass("icon-check");
		$("#m_checkbox_"+id).removeClass("cgcb_active");
		removePeopleSel(id);
		currentCheckedEmployees.splice(index, 1);  
	}else{
		$("#checkbox_"+id).addClass("icon-check");
		$("#checkbox_"+id).addClass("cgcb_active");
		
		$("#m_checkbox_"+id).addClass("icon-check");
		$("#m_checkbox_"+id).addClass("cgcb_active");
		appendPeopleSel(id, name, headImgUrl);
		currentCheckedEmployees.push(id);
	}
	
	resetPeopleSelWidth();
	
	if(currentCheckedEmployees.length > 0){
		$(".group_complete").show();
		$(".group_complete2").hide();
	}else{
		$(".group_complete").hide();
		$(".group_complete2").show();
	}
	
	event.stopPropagation();
	event.preventDefault();
}

function resetPeopleSelWidth(){
	var length = $("#peopleSelUl").find("li").length;
	var width = length * 60 + 2;
	$("#peopleSelUl").css("width",width);
	
	$(".group_scroll").scrollLeft(width);
}

function appendPeopleSel(id, name, headImgUrl){
	var html = "<li id='people_"+id+"' style='width:48px;'>";
	html += "<img src='"+headImgUrl+"' class='group_img'>";
	html += "<p class='util_ellipsis' style='text-align: center;'>"+name+"</p>";
	html += "</li>";
	$("#peopleSelUl").append(html);
}

function removePeopleSel(id){
	$("#peopleSelUl").find("#people_"+id).remove();
}