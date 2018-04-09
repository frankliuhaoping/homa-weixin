<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>编辑群组</title>
</head>
<body>
<div class="page" id="chat_group_edit">
<div class="content infinite-scroll" id="group_org_scroll" data-toggle="scroller">
	<div class="gridContainer clearfix" id="first_step">
		<input type="hidden" id="head_img_base64">
		<input type="hidden" id="chat_group_id" value="${chatGroup.id }">
		<div>
			<div class="header_g">
		    	<div class="groud_icon fl" id="groud_headimg_d" style="position: relative;">
		    		<c:if test="${chatGroup.headImg == null || chatGroup.headImg == ''}">
		    		<img src="<c:url value="/static/wxworkbench/img/groud.png"/>" id="groud_head_img">
		    		</c:if>
		    		<c:if test="${chatGroup.headImg != null && chatGroup.headImg != ''}">
		    		<img src="${chatGroup.headImg }" id="groud_head_img">
		    		</c:if>
		    		<input type="file" id="group_upload_image" value="图片上传" accept="image/jpeg,image/gif,image/png" capture="camera">
		    	</div>
		        <div class="groud_txt list-block" style="margin:0px;">
		        	<div class="item-inner" style="width:auto;border-bottom: 1px solid #fff;">
		        		<div class="item-input">
		        		<input type="text" placeholder="群组名称"  id="groupName" value="${chatGroup.name }">
		        		</div>
		        	</div>
		        </div>
		    </div>
		    <div class="groud_intro shadow">
		    	<div class="g_i_l fl">简介：</div>
		        <div class="g_i_r list-block" style="margin:0px;">
		        	<textarea rows="3" cols="3" style="width:100%" id="remark">${chatGroup.remark }</textarea>
		        </div>
		        <div class="clear"></div>
		   </div>
	      <div class="clear"></div>
		  <div class="div_but g_btn">
              <button class="button_m orange fw700" id="group_edit_btn">&nbsp;&nbsp;下&nbsp;一&nbsp;步</button>
          </div>
          <c:if test="${chatGroup.id != null}">
          <div class="div_but g_btn">
              <button class="button_m blue fw700" id="group_del_btn">&nbsp;&nbsp;解&nbsp;散&nbsp;群&nbsp;组</button>
          </div>
          </c:if>
		</div>
	</div>
	<div id="second_step" style="display:none;">
		<input type="hidden" id="checkedEmployees" value="${checkedEmployees }"/>
		<div class="con-search">
			<a href="javascript:;" class="input imitateCon j-showSearch con_flexbox-h" style=""><i class="icon-msg-search"></i>搜索</a>
	    </div>
	
	    <section class="friendList j-friendList" id="group-contact-ul" style="padding-bottom: 62px;">
						
		</section>
		<!-- 加载提示符 -->
	    <div class="infinite-scroll-preloader">
	        <div class="preloader"></div>
	    </div>
	     
	    <div class="group_toolbar con_flexbox ">
			<div class="group_scroll con_flexbox-h">
				<ul id="peopleSelUl">
					<c:forEach items="${chatSessionMemberList }" var="chatSessionMember">
					<li id="people_${chatSessionMember.employee.id }" style="width:48px;">
						<c:if test="${chatSessionMember.employee.headImgUrl == null || chatSessionMember.employee.headImgUrl == ''}">
		    			<img src="${contextPath }/static/wxworkbench/img/head.jpg" class="group_img">
		    			</c:if>
		    			<c:if test="${chatSessionMember.employee.headImgUrl != null && chatSessionMember.employee.headImgUrl != ''}">
		    			<img src="${chatSessionMember.employee.headImgUrl }" class="group_img" >
		    			</c:if>
						<p class="util_ellipsis" style="text-align: center;">${chatSessionMember.employee.name }</p>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div class="m_button orange group_complete finish_b" <c:if test="${checkedEmployees == null }">style="display:none;"</c:if> >完成</div>
			<div class="g_btn group_complete2" <c:if test="${checkedEmployees != null }">style="display:none;"</c:if> style="padding: 0px 10px;">
				<input type="button" class="button_m orange finish_b" value="完成" style=" width:100%;">
			</div>
	    </div>
    </div>
</div>
<section id="j-searchMain">
	<div class="con-search" >
		<div class="realCon">
			<div class="inputPn">
				<input type="text" placeholder="搜索" class="input bdbox j-conSearchIpt" external>
				<i class="icon-msg-search"></i>
				<em class="clearTxt j-clearTxt">×</em>
			</div>
			<a href="javascript:;" class="cancel j-hideSearch">取消</a>
		</div>
    </div>
	<div class="commMask commMask-search result" id="j-searchMask">
		<p class="txtCenter noResult j-noResult">对不起，无搜索结果</p>
	
		<ul class="comm-list comm-list-friendList" id="seach-friend-result">
						
		</ul>
	</div>
</section>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/util/util.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/search/search.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/acconding_org.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/util/localResizeIMG2.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/util/mobileBUGFix.mini.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/group/group.js'></script>
<script type="text/javascript">
	var current_employee_id = "${employeeId}";
</script>
</body>
</html>
