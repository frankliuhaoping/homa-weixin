<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
<title>${webAppTitle}-${controllerConfig.pageTitle}</title>

</head>
<body>
	<div id="txl" class="easyui-layout" data-options="fit:true" style="margin:5px; padding: 5px">   
	      
	    <div data-options="region:'center',title:'员工'">
			 <table id="datagrid-list"></table>
			    <div id='datagrid-toolbar' class="datagrid-toolbar">
					<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
			        <div class="searchForm">
						<form id="searchForm" class="easyui-form">
							<label>员工姓名 </label><input class="easyui-textbox" name="search.name_like">
							<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>	            			
							<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
						</form>            
			        </div>        
			    </div>
				    
	    </div>   
	  </div>  
   		<script>
		var urlPrefix = "${controllerConfig.urlPrefix}"
	 </script>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script src="${contextPath}/static/js/organizationTree.js"></script>
	<script src="${contextPath}/static/js/backend/employeeFind.js"></script>

</body>
</html>