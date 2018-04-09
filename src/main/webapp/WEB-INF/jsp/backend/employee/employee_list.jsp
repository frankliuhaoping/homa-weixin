<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
<title>${webAppTitle}-${controllerConfig.pageTitle}</title>
<script src="${contextPath}/static/js/easyuiProduct.js"></script>
</head>
<body>
	<div id="txl" class="easyui-layout" data-options="fit:true" style="margin:5px; padding: 5px">   
	    <div data-options="region:'west',title:'组织架构',split:true" style="width:250px;">
	    	<ul id="organizationTree"></ul> 
	    </div>   
	    <div data-options="region:'center',title:'员工'">
			 <table id="datagrid-list"></table>
			    <div id='datagrid-toolbar' class="datagrid-toolbar">
					<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
			        <div class="searchForm">
						<form id="searchForm" class="easyui-form">
							<label>姓名 </label><input class="easyui-textbox" name="search.name_like" style="width: 100px">
							<label>工号 </label><input class="easyui-textbox" name="search.code_like" style="width: 100px">
							<label>手机号 </label><input class="easyui-textbox" name="search.mobileNo_like" style="width: 100px">
							<label>角色</label>
							<select class="easyui-combobox" name="search.sysRoleType_eq" data-options="width: '80', editable: false">   
							    <option value="" selected>全部</option>
							    <c:forEach items="${sysRoleTypes}" var="sysRoleType">
							    	<option value="${sysRoleType.value}">${sysRoleType.text}</option>
							    </c:forEach>
							</select>
							<input type="hidden" name="search.organization.id_eq" id="search_organization">
							<input type="hidden" name="search.deleted_eq" id="search.delete_eq" value="0">
							<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>	            			
							<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
						</form>            
			        </div>        
			    </div>
				<div id="datagrid-row-toolbar" class="hidden">
			    	<%@ include file="/WEB-INF/jsp/common/datagrid_row_toolbar_browse_buttons.jsp"%>
				</div>	    
	    </div>   
	  </div>  
   	<script>
		var urlPrefix = "${controllerConfig.urlPrefix}"
	</script>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script src="${contextPath}/static/js/organizationTree.js"></script>
	<script src="${contextPath}/static/js/backend/employee.js"></script>
	<script src="${contextPath}/static/js/backend/commonSelect.js"></script>	
</body>
</html>