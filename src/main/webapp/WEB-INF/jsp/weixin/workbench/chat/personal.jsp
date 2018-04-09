<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>个人详情</title>
</head>
<body>
<div class="page" id="personal_detail">
<div class="gridContainer clearfix">
	<div class="header_p con_flexbox">
    	<div class="personal_icon">
    		<c:if test="${employee.headImgUrl == null || employee.headImgUrl == '' }">
    		<img src="${contextPath }/static/wxworkbench/img/head.jpg"/>
    		</c:if>
    		<c:if test="${employee.headImgUrl != null && employee.headImgUrl != '' }">
    		<img src="${employee.headImgUrl}"/>
    		</c:if>
    	</div>
        <div class="personal_txt con_flexbox-h">
        	<span>${employee.name}</span>
            <div class="con_flexbox">
			<p><c:if test="${employee.remark != ''}">
            	${employee.remark }|
            	</c:if></p>	
            <p class="con_flexbox-h con_ellipsis">${employee.organization.name }</p> 		
            </div>
        </div>
        
    </div>
    <div class="personal_intro">
    	<ul>
    		<li>
        		<span>手机：</span>
           		<p>${employee.mobileNo }</p>
       		</li>
            <li>
        		<span>电话：</span>
           		<p>${employee.officeTel }</p>
       		</li>
            <li>
        		<span>E-mail：</span>
           		<p>${employee.eMail }</p>
       		</li>
		</ul>
    </div>
    <div class="clear"></div>
    <c:if test="${currentEmployeeId != employee.id }">
    <div class="g_btn">
		<input type="button" class="button_m orange" value="发送信息" id="chat_to_btn" style=" width:100%;">
	</div>
	</c:if>
</div>
</div>
<script type="text/javascript">
$(function(){
	$("#chat_to_btn").click(function(){
		var url = contextPath+"/weixin/workbench/chatcontent/to_detail?otherId=${employee.id }";
		window.location.href = url;
	});
})
</script>
</body>
</html>
