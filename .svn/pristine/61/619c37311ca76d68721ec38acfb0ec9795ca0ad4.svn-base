<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}">
		<input type="hidden" name="action" value="">		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>所属菜单</label></div>			
			<div class="grid_11">
			    <select name="parent.id" class="easyui-combotree" 
			            data-options="url:'${contextPath}/rbac/sysMenu/treeJson', width: '100%', value: '${model.parent.id}'">
			    </select>			
			</div>
			<div class="clear"></div>
		</div>		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>菜单名称</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="name" value="${model.name}" data-options="width: '100%', required: true"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>菜单类型</label></div>
			<div class="grid_11">
				<select class="easyui-combobox" name="menuType" data-options="">
					<c:forEach items="${weiXinMenuTypes}" var="weiXinMenuType">
						<option value="${weiXinMenuType.value}">${weiXinMenuType.text}</option>
					</c:forEach>
				</select>
			</div>
			<div class="clear"></div>
		</div>			
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>URL</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="url" value="${model.url}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>		
					
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>图标 iconCls</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="icon" value="${model.icon}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>用途</label></div>
			<div class="grid_11">
				<select class="easyui-combobox" name="useOf" data-options="width: '100%', editable: false, value:'${model.useOf}'">   
				    <option value="0">PC端</option>   
				    <option value="1">微信端</option>				    
				</select>  			
			</div>
			<div class="clear"></div>
		</div>			
			
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>备注</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="remark" value="${model.remark}" data-options="width: '100%', height: 80, multiline: true"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10" id="permissionCodeDiv">
			<div class="grid_4 align-right height-30"><label>权限代码</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="permissionCode" value="${model.permissionCode}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g" id="defaultPermissionDiv">
			<div class="grid_4 align-right height-30"><label>默认权限</label></div>
			<div class="grid_12 height-30">
				<input type="checkbox" class="align-middle" name="viewPermission" id="viewPermission" value="true"> <label for="viewPermission">查看</label>&nbsp;
				<input type="checkbox" class="align-middle" name="addPermission" id="addPermission" value="true"> <label for="addPermission">添加</label>&nbsp;
				<input type="checkbox" class="align-middle" name="editPermission" id="editPermission" value="true"> <label for="editPermission">修改</label>&nbsp;
				<input type="checkbox" class="align-middle" name="delPermission" id="delPermission" value="true"> <label for="delPermission">删除</label>&nbsp;
			</div>
			<div class="grid_4 align-right height-30"></div>
			<div class="grid_12 height-30">
				<input type="checkbox" class="align-middle" name="importPermission" id="importPermission" value="true"> <label for="importPermission">导入</label>
				<input type="checkbox" class="align-middle" name="exportPermission" id="exportPermission" value="true"> <label for="exportPermission">导出</label>
			</div>
			<div class="clear"></div>			
		</div>			

		<div class="grid_g">
			<div class="grid_4 align-right height-30"><label>是否屏蔽</label></div>		
			<div class="grid_5 height-30">
				<input type="radio" class="align-middle" name="isDisabled" id="isDisabledYes" value="true" <c:if test="${model.isDisabled}">checked="checked"</c:if>> <label for="isDisabledYes">是</label>
				<input type="radio" class="align-middle" name="isDisabled" id="isDisabledNo" value="false" <c:if test="${!model.isDisabled}">checked="checked"</c:if>> <label for="isDisabledNo">否</label>
			</div>
			<div class="clear"></div>			
		</div>		
	</form>
</div>
<div class="space-10"></div>