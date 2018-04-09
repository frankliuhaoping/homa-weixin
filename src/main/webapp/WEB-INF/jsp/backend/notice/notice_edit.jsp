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
			<div class="grid_2 align-right height-30"><label><span class="red">*&nbsp;</span>标题</label></div>
			<div class="grid_14"><input class="easyui-textbox" name="title" value="${model.title}" data-options="width: '100%', required: true"></div>
			<div class="clear"></div>
		</div>
		
				<div class="grid_g margin-bottom-10">
			<label><span class="red">*&nbsp;是否发布 </label> 
							<select class="easyui-combobox" id="selects2" name="isRelease" style="width: 60px" data-options="editable:false">
							   <c:if test="${model.isRelease eq false}">
							    <option value="false">是</option>
							    <option value="true">否</option>
							   </c:if>
							      <c:if test="${model.isRelease eq true}">
							    <option value="true">否</option>
							    <option value="false">是</option>
							   </c:if>
							   
							</select>
							
									<div class="grid_1">&nbsp;</div>
			          <div class="clear"></div>
							
							</div>
		
		<div class="grid_g no-margin">
			<div class="grid_2 align-right height-30"><label>内容</label></div>		
			<div class="grid_14">
				<textarea name="content" id="myEditor">${model.content}</textarea>
			</div>
			<div class="clear"></div>
		</div>
	</form>
</div>
<div class="space-10"></div>