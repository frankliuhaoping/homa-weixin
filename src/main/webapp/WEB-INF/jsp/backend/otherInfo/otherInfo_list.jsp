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
				<label>标题查找 </label><input class="easyui-textbox" name="search.title_like">
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
			</c:if>
		</div>
	</div>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
		$(function() {
			var dataGridOptions = {
				columns : [ [{
					field : 'title',
					title : '标题',
					width : 30,
					sortable : true,
					exportable : true,
				},{
					field : 'isRelease',
					title : '是否发布',
					width : 30,
					sortable : true,
					exportable : true,
				},{
					field : 'releaseTime',
					title : '发布时间',
					width : 30,
					sortable : true,
					exportable : true,
				}] ],
			};
			
			var options = {
					formDialog : {
						width : '60%',
						height : 600,
						dialogId : '#editdialog',
						formId : '#editForm',
						title : '${controllerConfig.pageTitle}',
						onOpen : function(dialog, action) {
							UE.delEditor('myEditor');
							var ue = UE.getEditor('myEditor',{toolbars: [
							          							                                   ['anchor', //锚点
							        							                                    'undo', //撤销
							        							                                    'redo', //重做
							        							                                    'bold', //加粗
							        							                                    'indent', //首行缩进
							        							                                    'snapscreen', //截图
							        							                                    'italic', //斜体
							        							                                    'underline', //下划线
							        							                                    'strikethrough', //删除线
							        							                                    'subscript', //下标
							        							                                    'fontborder', //字符边框
							        							                                    'superscript', //上标
							        							                                    'formatmatch', //格式刷
							        							                                    'source', //源代码
							        							                                    'blockquote', //引用
							        							                                    'pasteplain', //纯文本粘贴模式
							        							                                    'selectall', //全选
							        							                                    'print', //打印
							        							                                    'preview', //预览
							        							                                    'horizontal', //分隔线
							        							                                    'removeformat', //清除格式
							        							                                    'time', //时间
							        							                                    'date', //日期
							        							                                    'unlink', //取消链接
							        							                                    'insertrow', //前插入行
							        							                                    'insertcol', //前插入列
							        							                                    'mergeright', //右合并单元格
							        							                                    'mergedown', //下合并单元格
							        							                                    'deleterow', //删除行
							        							                                    'deletecol', //删除列
							        							                                    'splittorows', //拆分成行
							        							                                    'splittocols', //拆分成列
							        							                                    'splittocells', //完全拆分单元格
							        							                                    'deletecaption', //删除表格标题
							        							                                    'inserttitle', //插入标题
							        							                                    'mergecells', //合并多个单元格
							        							                                    'deletetable', //删除表格
							        							                                    'cleardoc', //清空文档
							        							                                    'insertparagraphbeforetable', //"表格前插入行"
							        							                                    'fontfamily', //字体
							        							                                    'fontsize', //字号
							        							                                    'paragraph', //段落格式
							        							                                    'simpleupload', //单图上传
							        							                                    'insertimage', //多图上传
							        							                                    'edittable', //表格属性
							        							                                    'edittd', //单元格属性
							        							                                    'link', //超链接
							        							                                    'emotion', //表情
							        							                                    'spechars', //特殊字符
							        							                                    'searchreplace', //查询替换
							        							                                    'insertvideo', //视频
							        							                                    'help', //帮助
							        							                                    'justifyleft', //居左对齐
							        							                                    'justifyright', //居右对齐
							        							                                    'justifycenter', //居中对齐
							        							                                    'justifyjustify', //两端对齐
							        							                                    'forecolor', //字体颜色
							        							                                    'backcolor', //背景色
							        							                                    'insertorderedlist', //有序列表
							        							                                    'insertunorderedlist', //无序列表
							        							                                    'directionalityltr', //从左向右输入
							        							                                    'directionalityrtl', //从右向左输入
							        							                                    'rowspacingtop', //段前距
							        							                                    'rowspacingbottom', //段后距
							        							                                    'pagebreak', //分页
							        							                                    'insertframe', //插入Iframe
							        							                                    'imagenone', //默认
							        							                                    'imageleft', //左浮动
							        							                                    'imageright', //右浮动
							        							                                    'attachment', //附件
							        							                                    'imagecenter', //居中
							        							                                    'wordimage', //图片转存
							        							                                    'lineheight', //行间距
							        							                                    'edittip ', //编辑提示
							        							                                    'customstyle', //自定义标题
							        							                                    'autotypeset', //自动排版
							        							                                    'webapp', //百度应用
							        							                                    'touppercase', //字母大写
							        							                                    'tolowercase', //字母小写
							        							                                    'template', //模板
							        							                                    'inserttable', //插入表格
							        							                                    'charts', // 图表
							        							                                             ] ]}); 
						},
					},					
					url : {
						urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
					}
				};
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
		});		
	</script>
	<!-- 
	<textarea name="content" id="myEditor"></textarea>
	<script type="text/javascript">
		//UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
		UE.getEditor('myEditor');
	</script> -->
</body>
</html>