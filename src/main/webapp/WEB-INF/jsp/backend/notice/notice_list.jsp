<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
<title>${webAppTitle}-${controllerConfig.pageTitle}</title>
<script type="text/javascript" src="${contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${contextPath}/static/ueditor/ueditor.all.js"></script>

</head>
<body>
    <table id="datagrid-list"></table>
    <div id='datagrid-toolbar' class="datagrid-toolbar">
		<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
        <div class="searchForm">
			<form id="searchForm" class="easyui-form">
				<label>标题查找 </label><input class="easyui-textbox" name="search.title_like" style="width:200px">
											<div class="form-group"> 
							<label>是否发布 </label> 
							<select class="easyui-combobox" id="selects2" name="search.isRelease_eq" style="width: 60px" data-options="editable:false">
								<option value="">全部</option>
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</div>
				<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>	            			
				<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
			</form>            
        </div>        
    </div>
	<div id="datagrid-row-toolbar" class="hidden">
    	<div id="datagrid-row-toolbar-browse-row.index">
			<c:if test="${controllerConfig.hasViewPermission()}">
				<a href="#" class="easyui-linkbutton" title="查看当前记录" id="viewRowBtn_row.index" onclick="crud.view(row.id, row.index);return false;" data-options="iconCls:'icon-view', plain:true"></a>
			</c:if>
			<c:if test="${controllerConfig.hasEditPermission()}">
				<a href="#" class="easyui-linkbutton" title="修改当前记录" id="editRowBtn_row.index" onclick="crud.edit(row.id, row.index);return false;" data-options="iconCls:'icon-edit', plain:true"></a>
			</c:if>
			<c:if test="${controllerConfig.hasDelPermission()}">
				<a href="#" class="easyui-linkbutton" title="删除当前记录" id="delRowBtn_row.index" onclick="crud.del(row.id, row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
				<a href="#" class="easyui-linkbutton" title="置顶" onclick="noticeStick(row.id);return false;" data-options="iconCls:'icon-arrow-up',plain:true" title="置顶">置顶</a>
			</c:if>
		</div>
	</div>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	
	<script>
		function noticeStick(id) {
			$.post("${contextPath}/backend/notice/stick/" + id , function(data){
				if(data.success == 'yes') {
					window.location.href = "${contextPath}/backend/notice/list?";
				} else {
					alert('置顶失败！请重试');
				}
			}, 'json');
		} 
	</script>
	
	<script>
		$(function() {
			var dataGridOptions = {
				columns : [ [{
					field : 'title',
					title : '标题',
					width : 50,
					sortable : true,
					exportable : true,
				},{
					field : 'isRelease',
					title : '是否发布',
					width : 50,
					sortable : true,
					exportable : true,
				},{
					field : 'releaseTime',
					title : '发布时间',
					width : 50,
					sortable : true,
					exportable : true,
				}] ],
			};
			
			var options = {
					formDialog : {
						title : '${controllerConfig.pageTitle}',
						width : '50%',
						height : 600,
						onOpen : function(dialog, action) {
							UE.delEditor('myEditor');
							ue = UE.getEditor('myEditor',{ initialFrameHeight: 400}); 					
						},
						onSubmit:function(){
							//html状态
							//if(ue.queryCommandState( 'source' ) == 1){
							//	ue.execCommand('source');
							//}
						}
					},					
					url : {
						urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
					},
					
				};
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);	
			

		});		
	</script>
</body>
</html>