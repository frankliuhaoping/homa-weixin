<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} 首页" />
<title>${webAppTitle}-首页</title>
 <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/backend/contact_grab.css"/>
 <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/lightbox.css"/>
 <style type="text/css">

  .datagrid-cell-c1-code{
	height: 60px!important;

 }
 </style>
   	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
 
</head>
<body>


	<div id="txl" class="easyui-layout" data-options="fit:true" style="margin:5px; padding: 5px">   
	    <div data-options="region:'west',title:'会话列表',split:true" style="width:380px;">
	    	<table id="chatHistory" class="">  </table>  
	    </div>   
	    
	    <div data-options="region:'center',title:'聊天记录'" style="padding:5px;">
			 <table id="chatContent"></table>
			 
			 <div id='datagrid-toolbar' class="datagrid-toolbar">
			 
			 <!-- 搜索框 -->
			 <input id="searcher" style="width:300px;height:30px"></input> 
			 <div id="menu" style="width:120px"> 
			 <div data-options="name:'all',iconCls:'icon-ok'">全部</div> 
             <div data-options="name:'san'">最近三个月</div>
             <div data-options="name:'liu'">最近六个月</div> 
             <div data-options="name:'nian'">最近一年</div> 
             </div> 
             
             <!-- 刷新按钮 -->
			<a href="#" class="easyui-linkbutton" id="refreshBtn" onclick="$('#chatContent').datagrid('reload');return false;" data-options="iconCls:'icon-reload',plain:true">刷新</a>
	        <c:if test="${controllerConfig.hasAddPermission() || controllerConfig.hasDelPermission()}">
	        	<a class="datagrid-btn-separator no-float"></a>
	        </c:if>
	        	        <c:if test="${controllerConfig.hasExportPermission()}">	        
	        	<a href="" class="easyui-linkbutton" id="exportBtn" data-options="iconCls:'icon-export-to-excel',plain:true">导出</a>
	        </c:if>
			    </div> 
				<div id="datagrid-row-toolbar" class="hidden"> 
			    	<%@ include file="/WEB-INF/jsp/common/datagrid_row_toolbar_browse_buttons.jsp"%> 
				</div>  
	    </div> 
	  </div> 
	
<script src="${contextPath}/static/js/lightbox-2.6.min.js"></script>
<script src="${contextPath}/static/js/backend/chatHistoryList.js"></script>

   

</body>

 <script>
 
 
 	$(function(){
 			$(".List a").lightBox(); 
 			
 	});
 </script>


</html>