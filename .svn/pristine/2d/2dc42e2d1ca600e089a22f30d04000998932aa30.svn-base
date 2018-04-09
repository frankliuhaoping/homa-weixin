<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>订单列表</title>
</head>
<body>
<div class="page" id="salesOrder-page">
	<div class="content infinite-scroll salesOrderScroll" data-distance="100" data-toggle="scroller">
		<div class="order_list" style="display: block;">
			<div class="order_list_tab">
				<div class="con_flexbox">
					<span class="tabspan con_flexbox-h order_list_active" title="2">一周</span>
					<span class="tabspan con_flexbox-h" title="1">一个月</span>
					<span class="tabspan con_flexbox-h" title="0">全部</span>
				</div>
			</div>
	        <div class="div_txt">
		        <div style="width:100%;padding:0 5px;" class="con_flexbox">
		            <input type="text" class="txt con_flexbox-h" placeholder="输入关键字搜索" id="salesOrder_keyWord"/>
		            <button onclick="searchKeyWord()">查询</button>
		        </div>    
	        </div>
			<div class="con_container">
		        <div class="div_but">
		        <a href="${contextPath }/weixin/workbench/salesOrder/createOrder" external>
		            <button class="button_m orange fw700"><i class="icon_m iconfont_m">&#xe609;</i>&nbsp;&nbsp;添&nbsp;加&nbsp;订&nbsp;单</button>
		           </a>
		        </div>
		
				<!-- <div class="div_table " >
					<div class="div_tr">
				
						<div class="div_td">	
							<div class="div_td1"><i class="icon_m iconfont_m">&#xe634;</i>&nbsp;订单-12161145461</div>	
							<div class="div_td2">12-16&nbsp;11:45</div>
						</div>	
							
						<div class="div_td con_flexbox con_ellipsis">		
							<p><i class="icon_m iconfont_m">&#xe624;</i><span>528522</span><br />
							<i class="icon_m iconfont_m">&#xe627;</i>张三 &nbsp;&nbsp;&nbsp;&nbsp;<i class="icon_m iconfont_m">&#xe604;</i>19522556666</p>
						</div>
					</div>
				</div> -->
		
		        <div class="div_table " id="salesOrderTable"></div>
	        </div>
 	  	 </div>
		 <div class="infinite-scroll-preloader salesOrderScroll">
	        <div class="preloader"></div>
	     </div>
		 <div class="order_list">dfghdh</div>
		 <div class="order_list">o135123</div>
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/salesOrder/salesOrder.js' charset='utf-8'></script>
</body>
</html>
