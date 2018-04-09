<%@page import="java.util.Calendar"%>
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
	    <div data-options="region:'center',title:'工资 '">
    <table id="datagrid-list"></table>
    <div id='datagrid-toolbar' class="datagrid-toolbar">
		<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
        <div class="searchForm" style="padding-bottom: 5px">
			<form id="searchForm" class="easyui-form">			
				<%@ include file="/WEB-INF/jsp/common/select_year_month.jsp"%>	
				<div class="form-group">										
					<label>姓名 </label><input class="easyui-textbox" name="search.employee.name_like" style="width: 100px">
				</div>
				<div class="form-group">
					<label>手机号码 </label><input class="easyui-textbox" name="search.employee.mobileNo_like" style="width: 100px">
				</div>
				<div class="form-group">
					<label>是否有申请异常 </label> 
					<select class="easyui-combobox" name="search.isAbnormal_eq" data-options="width: 80, editable: false">   
					    <option value="">全部</option>   
					    <option value="0">有异常</option>   
					    <option value="1">没有申请</option>   
					</select>
				</div>
				<div class="form-group">
					<label>第几次工资 </label> 
					<select class="easyui-combobox" name="search.wageVersion_eq" data-options="width: 80, editable: false">   
					    <option value="">全部</option>   
					    <option value="1">第一次工资</option>   
					    <option value="2">第二次工资</option>   
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
    </div>
    </div>
    
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script src="${contextPath}/static/js/organizationTree.js"></script>
	<script>
		$(function() {
			var dataGridOptions = {
				columns : [ [{
					field : 'employee.name',
					title : '姓名',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'employee.mobileNo',
					title : '手机号码',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'wageVersion',
					title : '第几次工资',
					width : 20,
					sortable : true,
					exportable : true,
				}, {
					field : 'actualWage',
					title : '实发工资',
					width : 20,
					sortable : true,
					exportable : true,
				}] ],
			};
			
			var options = {			
					addCkColumn : false,
					addIdColumn : true, // 添加ID列
					addVersionColumn : false, // 添加version列
					addRecordInfoColumns : false, // 记录信息列 createdBy.realName等											
					url : {
						urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
					}
				};
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
			
			var customTreeOptions = {
					onClick : function(node, nodeType) {
						$('#search_organization').val(node.id);
						crud.search();
					}
				}
		    organizationTree = ryUILib.OrganizationTree(customTreeOptions, {});
		});		
	</script>
</body>
</html>