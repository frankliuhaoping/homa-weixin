<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询统计</title>
</head>
<body>
<div class="page " id="MqueryCountIndex-page">
	<div class="content infinite-scroll queryScroll" data-toggle="scroller">
	
	
	<div>

<input type="text" id="searchDepart" style="
    padding: 3px 0;
    width: 68%;    margin: 3px;">
<button onclick="searchDepartBtn()" style="
    background: orange;
    padding: 3px 0;
    border-radius: 3px;
    border: none;
    color: #fff;
    float: right;    margin: 3px;
    /* margin-right: 10px; */
    width: 28%;
">部门搜索</button>    

</div>
<!-- 
<div class="div_txt">
		        <div style="width:100%;padding:0 5px;" class="con_flexbox">
		            <input type="text" class="txt con_flexbox-h" placeholder="输入关键字搜索" id="salesOrder_keyWord">
		            <button onclick="searchKeyWord()">查询</button>
		        </div>    
	        </div> -->
	
	
    
    
		<ul class="inquiry_statistics_ul" id="querycounttable">
		 	<c:forEach items="${ organizationTreeTemps}" var ="o">
			 	<li class="cc" onclick="getDepartMent('${o.organizationId}')"> ${o.organizationName }</li>
			      <div class="li_con cc">    
		     	 </div>
		
		 	</c:forEach>
		</ul>
	  	
	  	<div class="infinite-scroll-preloader queryScroll">
	        <div class="preloader"></div>
	    </div>
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/queryCount/queryCount.js' charset='utf-8'></script>
</body>
</html>
