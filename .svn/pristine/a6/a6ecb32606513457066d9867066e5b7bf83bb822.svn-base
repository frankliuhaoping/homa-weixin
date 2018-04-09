<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
<title>${webAppTitle}-${controllerConfig.pageTitle}</title>

</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true" style="margin:5px; padding: 5px">   
	    <div data-options="region:'west',title:'产品分类',split:true" style="width:250px;">
	    	<ul id="tt" class="easyui-tree" data-options="url:'${contextPath}/backend/productCategory/treeJson'"> 	
			</ul> 
	    </div>   
	    <div data-options="region:'center',title:'产品详情'" >
			 <table id="datagrid-list"></table>
			    <div id='datagrid-toolbar' class="datagrid-toolbar">
					<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
			        <div class="searchForm">
						<form id="searchForm" class="easyui-form">
							<label>产品名称 </label><input class="easyui-textbox" name="search.name_like">
							<input type="hidden"  name="search.deleted_eq" value="0">
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
   
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
		var urlPrefix = "${controllerConfig.urlPrefix}"
	</script>
	<script src="${contextPath}/static/js/backend/product.js"></script>
</body>
</html>