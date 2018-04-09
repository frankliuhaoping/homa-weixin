<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}"> 
		<input type="hidden" name="version" value="${model.version}"> 
		<input type="hidden" name="action" value="">

<%-- 		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label><span class="red">*&nbsp;</span>所属分类</label></div>
			<div class="grid_13"><input class="easyui-combobox" name="category.id" data-options="valueField:'id',textField:'name',url:'${contextPath}/backend/newsCategory/getCategory',value:'${model.category.id}', editable: false" /></div>
			<div class="clear"></div>
		</div> --%>

		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label><span class="red">*&nbsp;</span>标题</label></div>
			<div class="grid_13"><input class="easyui-textbox" name="title" value="${model.title}" data-options="width: '100%'"></div>
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
	
		<div class="grid_g margin-bottom-10" style="<c:if test="${model.coverPictureUrl == null }">display: none;</c:if>" id="div1">
			<div class="grid_2 align-right height-30">&nbsp;</div>			
			<div class="grid_13">			
				<img id="typical_imgId" alt="" src="<c:url value='${model.coverPictureUrl}' />" width="150px" height="150px">
				<input type="hidden" value="${model.coverPictureUrl }" id="coverPictureUrl" name="coverPictureUrl" />
			</div>
			<div class="clear"></div>
		</div>		

		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>封面图片</label></div>			
			<div class="grid_13 height-30">
				<a class="easyui-linkbutton" id="typical_btn">选择图片</a>
				<label>建议尺寸：200X200</label>
			</div>			
			<div class="clear"></div>
		</div>

		<div class="grid_g no-margin">
			<div class="grid_2 align-right height-30"><label>内容</label></div>		
			<div class="grid_13">
				<textarea name="content" id="myEditor">${model.content}</textarea>
			</div>
			<div class="clear"></div>
		</div>
	</form>
</div>
<div class="space-10"></div>
<script>
	base.plupload({
		browse_button : 'typical_btn',
		dir : 'typicalCase',
		imageId : 'typical_imgId',
		hiddenId : 'coverPictureUrl',
		success : function() {
			$("#div1").css('display', 'block');
		}
	})
</script>

