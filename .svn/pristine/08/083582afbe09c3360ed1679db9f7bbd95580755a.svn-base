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
	    	<ul id="organizationTree" >			</ul> 
	    </div>   
	    <div data-options="region:'center',title:'订单统计(按产品)'">
			 <table id="datagrid-list"></table>
			    <div id='datagrid-toolbar' class="datagrid-toolbar">
					<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
			        <div class="searchForm">
						<form id="searchForm" class="easyui-form">
							<!--  <label>产品分类 </label><input class="easyui-textbox" name="search.pc.name_like">-->
							<label>产品分类 </label><input  class="easyui-combobox" name="search.s.productcategoryid_eq" data-options="valueField:'id',textField:'name',url:'${contextPath}/backend/product/findProductCategory',width:100,editable:false">
							<label>产品名称 </label><input class="easyui-textbox" name="search.s.productname_like">
							<label>销售时间 ：</label><input class="easyui-datebox" style="width:100px" name="search.s.salesTime_gte"> 到 <input class="easyui-datebox" style="width:100px" name="search.s.salestime_lte">
							<input type="hidden" name="search.organization.id_eq" id="search_organization">
							<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>	            			
							<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
						</form>            
			        </div>        
			    </div>
	    </div>   
	  </div>  
   
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script src="${contextPath}/static/js/organizationTree.js"></script>
	<script src="${contextPath}/static/js/backend/countSalesOrderByProduct.js"></script>
	<script>
	var urlPrefix = "${controllerConfig.urlPrefix}"
	</script>
</body>
</html>