<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
<title>${webAppTitle}-${controllerConfig.pageTitle}</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/lightbox.css"/>
</head>
<body>
	<!-- <table id="datagrid-list"></table>
	<div id='datagrid-toolbar' class="datagrid-toolbar">
		<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
		<div class="searchForm">
			<form id="searchForm" class="easyui-form">
				<label>顾客名称 </label><input class="easyui-textbox" name="search.customerName_like"> 
				<label>返现范围 </label><input class="easyui-textbox" style="width: 60px" name="search.cashBack_gte"> 到 <input
					class="easyui-textbox" style="width: 60px" name="search.cashBack_lte"> 
				<label>是否有发票号 </label>
				<select id="selects1" name="">
					<option value="all">全部</option>
					<option value="true">是</option>
					<option value="false">否</option>
				</select> <label>是否上报 </label>
				<select class="easyui-combobox" id="selects2" name="search.isUpload_eq" style="width: 60px">
					<option value="">全部</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select> 
				<label>销售时间 </label><input class="easyui-datebox" style="width: 100px" name="search.salesTime_gte"> 到  <input class="easyui-datebox" style="width: 100px" name="search.salestime_lte">

				<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a> 
				<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
			</form>
		</div>
	</div>-->

	<div id="txl" class="easyui-layout" data-options="fit:true" style="margin: 5px; padding: 5px">
		<div data-options="region:'west',title:'组织架构',split:true" style="width: 250px;">
			<ul id="organizationTree">
			</ul>
		</div>
		<div data-options="region:'center',title:'订单明细'">
			<table id="datagrid-list"></table>
			<div id='datagrid-toolbar' class="datagrid-toolbar">
				<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
				<div class="searchForm" style="padding-bottom: 5px">
					<form id="searchForm" class="easyui-form">
						<div class="form-group"> 
							<label>销售时间 </label><input class="easyui-datebox" style="width: 100px" name="search.salesTime_gte"> 到 <input class="easyui-datebox" style="width: 100px" name="search.salestime_lte">
						</div>					
						<div class="form-group">
							<label>导购员 </label><input class="easyui-textbox" name="search.e.name_like" style="width: 100px">
						</div>
						<div class="form-group">
							<label>返现范围 </label><input class="easyui-textbox" style="width: 60px" name="search.cashBack_gte"> 到 <input class="easyui-textbox" style="width: 60px" name="search.cashBack_lte">
						</div> 
						<div class="form-group">
							<label>是否有凭证号 </label> 
							<select id="selects1" name="">
								<option value="all">全部</option>
								<option value="true">有</option>
								<option value="false">没有</option>
							</select>
						</div>
						<div class="form-group"> 
							<label>是否上报 </label> 
							<select class="easyui-combobox" id="selects2" name="search.isUpload_eq" style="width: 60px" data-options="editable:false">
								<option value="">全部</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
						<div class="form-group">
							<input type="hidden" name="search.organization.id_eq" id="search_organization"> 
							<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>
							<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
						</div>
					</form>
				</div>
			</div>
			<div id="datagrid-row-toolbar" class="hidden">
				<div id="datagrid-row-toolbar-browse-row.index">
					<a href="row.imageUrl" target="_blank" class="easyui-linkbutton viewImage" title="查看发票图片" id="viewRowBtn_row.index" data-options="iconCls:'icon-view', plain:true, onClick: viewInvoiceImage"></a>
				</div>											
			</div>
		</div>
	</div>

	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
		var urlPrefix = "${controllerConfig.urlPrefix}"
	</script>
	<script src="${contextPath}/static/js/organizationTree.js"></script>
	<script src="${contextPath}/static/js/lightbox-2.6.min.js"></script>
	<script src="${contextPath}/static/js/backend/salesOrder.js"></script>
</body>
</html>