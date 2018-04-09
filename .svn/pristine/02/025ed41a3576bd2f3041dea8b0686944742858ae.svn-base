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
	    <div data-options="region:'west',title:'组织架构',split:true" style="width:250px;">
	    	<ul id="organizationTree" >			</ul> 
	    </div>   
	    <div data-options="region:'center',title:'订单完成情况'" >
			 <table id="datagrid-list"></table>
			    <div id='datagrid-toolbar' class="datagrid-toolbar">
					<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
			        <div class="searchForm">
						  <form id="searchForm" class="easyui-form">
							<label>名称</label><input class="easyui-textbox" name="search.dgname_like">
							<label>年份</label><input class="easyui-textbox" name="search.y_like">
							<input type="hidden" name="search.organization.id_eq" id="search_organization">
							<input type="hidden" name="nodeType" id="search_organization_nodeType">
							<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>	            			
							<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
						</form>            
			        </div>        
			    </div>
				<!--  <div id="datagrid-row-toolbar" class="hidden">
			    	<%@ include file="/WEB-INF/jsp/common/datagrid_row_toolbar_browse_buttons.jsp"%>
				</div>	-->    
	    </div>
	    <div id="dialog"></div>   
	  </div>  
   
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script src="${contextPath}/static/js/backend/echarts-all.js"></script>
	<script src="${contextPath}/static/js/organizationTree.js"></script>
	<script src="${contextPath}/static/js/backend/salesOrderDb.js"></script>
	<script>
	var urlPrefix = "${controllerConfig.urlPrefix}"
	</script>
</body>
</html>