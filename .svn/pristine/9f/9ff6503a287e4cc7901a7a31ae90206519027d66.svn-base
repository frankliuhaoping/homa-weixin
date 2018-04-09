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
	    	<ul id="organizationTree"></ul> 
	    </div>   
	    <div data-options="region:'center',title:'签到记录'">
			 <table id="datagrid-list"></table>
			    <div id='datagrid-toolbar' class="datagrid-toolbar">
					<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
			        <div class="searchForm">
						<form id="searchForm" class="easyui-form">
							<div class="form-group">					
								<label>签到时间</label><input class="easyui-datebox" name="search.signTime_gte" style="width:100px"> 至 <input class="easyui-datebox" name="search.signTime_lte" style="width:100px">
							</div>						
							<div class="form-group">
								<label>员工姓名 </label><input class="easyui-textbox" name="search.e.name_like" style="width:100px">
							</div>
							<div class="form-group">
								<label>角色</label>
								<select class="easyui-combobox" name="search.e.sysRoleType_eq" data-options="width: '80', editable: false">   
								    <option value="" selected>全部</option>
								    <c:forEach items="${sysRoleTypes}" var="sysRoleType">
								    	<option value="${sysRoleType.value}">${sysRoleType.text}</option>
								    </c:forEach>
								</select>
							</div>		
							<div class="form-group">			
								<label>签到类型 </label>
								<select class="easyui-combobox" name="search.signType_eq" data-options="width: 80, editable: false">   
								    <option value="">全部</option>   
								    <option value="0">上班</option>   
								    <option value="1">下班</option>   
								</select>
							</div>		
							<input type="hidden" name="search.organization.id_eq" id="search_organization">
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
	<script src="${contextPath}/static/js/backend/signRecord.js"></script>
</body>
</html>