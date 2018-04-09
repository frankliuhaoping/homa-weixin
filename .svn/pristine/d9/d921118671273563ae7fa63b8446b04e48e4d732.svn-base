var groupOrgPage = 1;
var loading = false;
$(function(){
	
	groupOrgPage = 1;
	currentCheckedEmployees = [];
	var checkedEmployees = $("#checkedEmployees").val();
	var checkedArr = checkedEmployees.split(",");
	for(var i in checkedArr){
		var employeeId = checkedArr[i];
		if(employeeId && employeeId != ""){
			currentCheckedEmployees.push(employeeId);
		}
	}
	
	resetPeopleSelWidth();
	
	loading = false;
	groupInitSearch();
	/**
	 * 获取通讯录数据
	 */
	getGroupOrgData();
	
	/**
	 * 编辑群组
	 */
	$(".finish_b").click(function(){
		var chatGroupId = $("#chat_group_id").val();
		var headImg = $("#head_img_base64").val();
		var groupName = $("#groupName").val();
		var remark = $("#remark").val();
		if(groupName == ""){
			$.toast("请输入群组名称");
			return;
		}
		if(remark == ""){
			$.toast("请输入群组简介");
			return;
		}
		
		if(currentCheckedEmployees.length == 0){
			$.toast("请选择参与聊天的人员");
			return;
		}
		var groupMemberIds = currentCheckedEmployees.join();
		
		var url = contextPath + "/weixin/workbench/chatgroup/addOrUpdate";
        $.ajaxPost({
    		url:url,
    		data:{headImg:headImg,name:groupName,remark:remark,groupMemberIds:groupMemberIds,id:chatGroupId},
    		success:function(data, textStatus, jqXHR){
    			if(data.code == 200){
    				if(chatGroupId == ""){
    					$.toast("创建群组成功");
    				}else{
    					$.toast("修改群组成功");
    				}
    				
    				setTimeout(function(){
    					var url = contextPath + "/weixin/workbench/chatcontact/index?type=3";
    					window.location.href = url;
    				},2000);
    			}
    		}
    	});
	});
	
	initGroupResizeIMG();
	
	$.initInfiniteScroll($("#group_org_scroll"));
	$("#group_org_scroll").on('infinite', function() {
		getGroupOrgData();
	});
	
	/**
	 * 下一步按钮的点击事件
	 */
	$("#group_edit_btn").click(function(){
		var chatGroupId = $("#chat_group_id").val();
		var headImg = $("#head_img_base64").val();
		var groupName = $("#groupName").val();
		var remark = $("#remark").val();
		var checkedEmployeeIds = $("input[name=group_members]:checked");
		if(groupName == ""){
			$.toast("请输入群组名称");
			return;
		}
		if(remark == ""){
			$.toast("请输入群组简介");
			return;
		}
		$("#first_step").hide();
		$("#second_step").show();
	});
	/**
	 * 解散群组
	 */
	$("#group_del_btn").click(function(){
		$.confirm('确认要解散该群组吗？', '确认信息', function() {
			var chatGroupId = $("#chat_group_id").val();
			var url = contextPath + "/weixin/workbench/chatgroup/del";
			$.ajaxPost({
				url:url,
				data:{id:chatGroupId},
				success:function(data, textStatus, jqXHR){
					if(data.code == 200){
						$.toast("解散群组成功");
					}
					
					setTimeout(function(){
    					var url = contextPath + "/weixin/workbench/chatcontact/index?type=3";
    					window.location.href = url;
    				},2000);
				}
			});
		}, function() {
			
		});
	});
});


function initGroupResizeIMG(){
	$('#group_upload_image').localResizeIMG({
	    width: 360,
	    quality: 0.8,
	    success: function (result) {
	        var status = true;
	        if (result.height > 1600) {
	            status = false;
	            alert("照片最大高度不超过1600像素");
	        }
	        if (status) {
	        	$("#groud_head_img").attr("src",result.base64);
	        	$("#head_img_base64").val(result.clearBase64);
	        }
	    }
	});
}

function getGroupOrgData(){
	var url = contextPath + "/weixin/workbench/chatcontact/getChatOrg";
	// 如果正在加载，则退出
    if (loading) return;
    // 设置flag
    loading = true;
    
	$.ajaxPost({
		url:url,
		data:{page:groupOrgPage,rows:20},
		success:function(data, textStatus, jqXHR){
			// 重置加载flag
	        loading = false;
	        
			if(data.code == 200){
				var currentPage = data.result.page;
				var totalPages = data.result.totalPages;
				var chatOrgVoList = data.result.chatOrgVoList;
				
				groupOrgPage = currentPage + 1;
				for(var i in chatOrgVoList){
					var chatOrgVo = chatOrgVoList[i];
					var orgId = chatOrgVo.id;
					var orgName = chatOrgVo.name;
					var directorOrg = chatOrgVo.directorOrg;
					
					var html = "<dl>";
		    		html += "<dt class='clearfix' org_id='"+orgId+"' director_org='"+directorOrg+"' is_group='true' onclick='accondingOrg(this);'>";
		    		html += "<i class='icon-contactArrow'></i>";
		    		html += "<strong class='name bdbox'>"+orgName+"</strong>";
		    		html += "</dt>";
		    		html += "<dd>";
		    		html += "<ul class='comm-list comm-list-friendList'>";
								
		    		html += "</ul>";
		    		html += "</dd>";
		    		html += "</dl>";
		    		
					$("#group-contact-ul").append(html);
				}
				
				if(currentPage == totalPages){
					// 加载完毕，则注销无限加载事件，以防不必要的加载
		            $.detachInfiniteScroll($('#group_org_scroll'));
		            // 删除加载提示符
		            $('.infinite-scroll-preloader').remove();
				}
				
				//容器发生改变,如果是js滚动，需要刷新滚动
		        $.refreshScroller();
			}
		}
	});
}