<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}">
		<input type="hidden" name="version" value="${model.version}">
		<input type="hidden" name="action" value="">		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>登录名称</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="loginName" value="${model.loginName}" data-options="width: '100%', required: true, missingMessage : '请输入登录名称！',"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>登录密码</label></div>
			<div class="grid_11"><input class="easyui-textbox" type="password" name="password" value="${model.password}" data-options="width: '100%', required: true, missingMessage : '请输入登录密码！',"></div>
			<div class="clear"></div>
		</div>		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>真实姓名</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="realName" value="${model.realName}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>			
			
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30">可用角色</div>		
			<div class="grid_11">
			    <ul class="easyui-panel datalist" data-options="width:'100%', height:150">
			    	<c:forEach items="${sysRoleList}" var="sysRole" varStatus="status">
			    		<li value="${sysRole.id}">			    			
			    			<input type="checkbox" class="align-middle" name="sysRoles[${status.index}].id" id="sysRole_${sysRole.id}" value="${sysRole.id}"
			    			<c:forEach items="${model.sysRoles}" var="modelSysRole">
			    				<c:if test="${modelSysRole.id == sysRole.id}">
			    					checked="checked"
			    				</c:if>
			    			</c:forEach>>&nbsp;
			    			<label class="align-middle" for="sysRole_${sysRole.id}">${sysRole.name}</label>
			    		</li>	
			    	</c:forEach>
	    		</ul>	
			</div>
			<div class="clear"></div>
		</div>	
		
		<div class="grid_g">
			<div class="grid_4 align-right height-30"><label>是否管理员</label></div>		
			<div class="grid_4 height-30">				
				<input type="radio" class="align-middle" name="isAdmin" id="isAdminYes" value="true" <c:if test="${model.isAdmin}">checked="checked"</c:if>> <label for="isAdminYes">是</label>
				<input type="radio" class="align-middle" name="isAdmin" id="isAdminNo" value="false" <c:if test="${!model.isAdmin}">checked="checked"</c:if>> <label for="isAdminNo">否</label>
			</div>			
			<div class="grid_3 align-right height-30"><label>是否屏蔽</label></div>		
			<div class="grid_4 height-30">				
				<input type="radio" class="align-middle" name="isDisabled" id="isDisabledYes" value="true" <c:if test="${model.isDisabled}">checked="checked"</c:if>> <label for="isDisabledYes">是</label>
				<input type="radio" class="align-middle" name="isDisabled" id="isDisabledNo" value="false" <c:if test="${!model.isDisabled}">checked="checked"</c:if>> <label for="isDisabledNo">否</label>
			</div>			
			<div class="clear"></div>
		</div>
		
	</form>
</div>
<div class="space-10"></div>