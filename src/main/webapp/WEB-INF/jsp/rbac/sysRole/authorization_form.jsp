<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
	<table id="datagrid-sysPermission-list"></table>
	<div id='datagrid-sysPermission-toolbar' class="datagrid-toolbar"> 		
		<div class="searchForm">
			<div class="container_12 no-margin width-100">		
				<div class="grid_10 no-margin"><h4>当前授权角色：${sysRole.name}</h4></div>		
				<div class="grid_2 align-right height-30"><input class="align-middle" type="checkbox" id="checkedAll"> <label for="checkedAll">全选择</label></div>
				<div class="clear"></div>
			</div>
		</div> 		
	</div>
	<script>
		$(function() {
			$('#checkedAll').on('click', function() {
				var checked = this.checked;				
				$('#datagrid-sysPermission-list').treegrid('getPanel').find('input:checkbox').prop('checked', checked);
				
			});
			
			var dataGridOptions = {
				// 设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
				fitColumns : true,
				// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能
				autoRowHeight : false,
				// 设置为 true，则显示带有行号的列
				rownumbers : true,
				// 隐藏边框
				border : false,
				// 当设置为 true 时，尺寸就适应它的父容器
				fit : true,
				// 设置为 true，则只允许选中一行
				singleSelect : true,
				loadMsg : '',
				idField : 'id',
				treeField : 'sysMenu.name',
				pagination : false,
				url : contextPath + '/rbac/sysRole/getAuthorizationJson/${sysRole.id}',
				toolbar : '#datagrid-sysPermission-toolbar',
				columns : [ [ {
					field : 'sysMenu.name',
					title : '菜单',
					width : 25,
				}, {
					field : 'sysPermissions',
					title : '权限列表',
					width : 50,
					formatter : function(value, row, index) {
						var html = "";
						for (var i = 0; i < value.length; i++) {
							var checked = value[i].checked == true ? ' checked ' : '';
							html = html + '<input class="align-middle" type="checkbox" id="' + value[i].id + '" value="' + value[i].id + '"' + checked + '> <label class="align-middle" for="' + value[i].id + '">'
									+ value[i].name + '</label>&nbsp;&nbsp;';
						}
						return html;
					}
				}] ],
			};

			$('#datagrid-sysPermission-list').treegrid(dataGridOptions);
		});
	</script>
