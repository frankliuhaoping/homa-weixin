<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<body>
<div class="page" id="chat_group_detail">
<div class="content gridContainer clearfix">
	<input type="hidden" id="chat_group_id" value="${chatGroup.id }">
	<div>
		<div class="header_g">
	    	<div class="groud_icon fl" id="groud_headimg_d">
	    		<c:if test="${chatGroup.headImg == null || chatGroup.headImg == ''}">
	    		<img src="<c:url value="/static/wxworkbench/img/groud.png"/>" id="groud_head_img">
	    		</c:if>
	    		<c:if test="${chatGroup.headImg != null && chatGroup.headImg != ''}">
	    		<img src="${chatGroup.headImg }" id="groud_head_img">
	    		</c:if>
	    	</div>
	    	<div class="groud_txt">
	    		${chatGroup.name }
	    	</div>
	       
	    </div>
	    <div class="groud_intro shadow">
	    	<div class="g_i_l fl">简介：</div>
	        <div class="g_i_r list-block" style="margin:0px;">
	        	${chatGroup.remark }
	        </div>
	        <div class="clear"></div>
	    </div>
	   <div class="g_menbers">
		   <ul>
		   		<c:forEach items="${chatSessionMemberList }" var="chatSessionMember">
				<li>
			  		<label for="">
			  			<c:if test="${chatSessionMember.employee.headImgUrl == null || chatSessionMember.employee.headImgUrl == ''}">
	    				<img src="<c:url value="/static/wxworkbench/img/head.jpg"/>">
	    				</c:if>
	    				<c:if test="${chatSessionMember.employee.headImgUrl != null && chatSessionMember.employee.headImgUrl != ''}">
	    				<img src="${chatSessionMember.employee.headImgUrl }">
	    				</c:if>
			  			<p>${chatSessionMember.employee.name }</p>
			  		</label>
				</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
	  </div>
      <div class="clear"></div>
  </div>
</div>
</div>
</body>
</html>
