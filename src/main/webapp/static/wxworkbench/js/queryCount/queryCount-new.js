$(function() {
	getGroupOrgData("");
	
	groupInitSearch();
});

var  loading = false;
function getGroupOrgData(parentId){
	var url = contextPath + "/weixin/workbench/queryCount/organizationListNew";
	// 如果正在加载，则退出
    if (loading) return;
    // 设置flag
    loading = true;
    $.showIndicator();
    
	$.ajaxPost({
		url:url,
		data:{parentId:parentId},
		success:function(data, textStatus, jqXHR){
			// 重置加载flag
	        loading = false;
	        
			if(data.code == 200){
				var chatOrgVoList = data.result;
				
				for(var i in chatOrgVoList){
					var chatOrgVo = chatOrgVoList[i];
					var orgId = chatOrgVo.organizationId;
					var orgName = chatOrgVo.organizationName;
					
					var html = "<dl>";
		    		html += "<dt class='clearfix' org_id='"+orgId+"' onclick='accondingOrg(this);'>";
		    		html += "<i class='icon-contactArrow'></i>";
		    		html += "<strong class='name bdbox'>"+orgName+"</strong>";
		    		html += "<div style='font-size:20px;color: #ff7d44;width: 50px;text-align: center;position:absolute;right:0;' onclick='getDepartMent(event,\""+orgId+"\")'><i class='iconfont_m icon-chaxun'></i></div>"
		    		html += "</dt>";
		    		html += "<dd>";
		    		html += "<ul class='comm-list comm-list-friendList' style='padding-left: 20px;'>";
								
		    		html += "</ul>";
		    		html += "</dd>";
		    		html += "</dl>";
		    		
					$("#static-contact-ul").append(html);
				}
				
				//容器发生改变,如果是js滚动，需要刷新滚动
		        $.refreshScroller();
			}
			
			$.hideIndicator();
		}
	});
}

var peopleLoading = false;
function accondingOrg(t){
	if(peopleLoading)return;
	peopleLoading = true;
	
	var content = $(t);
	var $parent=content.parent("dl");
	$parent.siblings("dl").removeClass("open");
	
	var orgId = content.attr("org_id");
	var hasLoaded = content.attr("has_loaded");
	if(orgId && orgId != "" && !hasLoaded){
		var url = contextPath + "/weixin/workbench/queryCount/organizationListNew";
		$.showIndicator();
		
		$.ajaxPost({
			url:url,
			data:{parentId:orgId},
			success:function(data, textStatus, jqXHR){
				peopleLoading = false;
				if(data.code == 200){
					var chatOrgVoList = data.result;
					
					var html = "";
					for(var i in chatOrgVoList){
						var chatOrgVo = chatOrgVoList[i];
						var orgId = chatOrgVo.organizationId;
						var orgName = chatOrgVo.organizationName;
						
						html += "<dl>";
			    		html += "<dt class='clearfix' org_id='"+orgId+"' onclick='accondingOrg(this);'>";
			    		html += "<i class='icon-contactArrow'></i>";
			    		html += "<strong class='name bdbox'>"+orgName+"</strong>";
			    		html += "<div style='font-size:20px;color: #ff7d44;width: 50px;text-align: center;position:absolute;right:0;' onclick='getDepartMent(event,\""+orgId+"\")'><i class='iconfont_m icon-chaxun'></i></div>"
			    		html += "</dt>";
			    		html += "<dd>";
			    		html += "<ul class='comm-list comm-list-friendList' style='padding-left: 20px;'>";
									
			    		html += "</ul>";
			    		html += "</dd>";
			    		html += "</dl>";
			    		
					}
					$parent.find("ul").append(html);
					
					//容器发生改变,如果是js滚动，需要刷新滚动
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

function getDepartMent(e,oid) {
	var url = contextPath + "/weixin/workbench/queryCount/departmentMsg?eid=" + oid;
	window.location.href = url;
	e.stopPropagation();
}