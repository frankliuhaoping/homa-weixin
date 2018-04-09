<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询统计</title>
</head>
<body>
<div class="page" id="queryCountIndex-page">
	<div class="content infinite-scroll queryScroll" data-toggle="scroller">
	
	
		<div>

<input type="text" id="searchEmployee" style="
    padding: 3px 0;
    width: 68%;    margin: 3px;">
<button onclick="searchEmployeeBtn()" style="
    background: orange;
    padding: 3px 0;
    border-radius: 3px;
    border: none;
    color: #fff;
    float: right;
        margin: 3px;
    /* margin-right: 10px; */
    width: 28%;
">员工搜索</button>    

</div>

<!-- <div class="div_txt">
		        <div style="width:100%;padding:0 5px;" class="con_flexbox">
		            <input type="text" class="txt con_flexbox-h" placeholder="输入关键字搜索" id="salesOrder_keyWord">
		            <button onclick="searchKeyWord()">查询</button>
		        </div>    
	        </div>	 -->
	
		<div>

		<ul class="inquiry_statistics_ul" id="querycounttable" class="querycounttable">
<%-- 		 	<c:forEach items="${ organizationTreeTemps}" var ="o">
			 	<li class="cc" onclick="getEmployees('${o.organizationId}',this)">
			 		<i class="icon_m iconfont_m">&#xe647;</i> ${o.organizationName }
			 	</li>
			      <div class="li_con cc">
			          <!-- <div class="pading_div cc" style="width:100%;">
			              <img src="img/home1s.png"/> <p class="p1">黄婷</p> <p class="pp1">计中心经理</p>
			          </div> -->
			          
		     	 </div>
		
		 	</c:forEach> --%>
		</ul>
		
		
		<ul class="inquiry_statistics_ul" id="querycounttable1" class="querycounttable1" style='display:none'>

						<div class="li_con cc" style="display: block;">
							<div class="pading_div cc" style="width: 100%;"
								onclick="getEmployeeById('86043888-4f96-4757-b272-1a6a75d7bac3')">
								<img src="/homa-weixin/static/wxworkbench/img/head.jpg">
								<p class="p1">李成涛</p>
							</div>
						</div>

		
		 	
		</ul>
	  
	  	<div class="infinite-scroll-preloader queryScroll querycounttable">
	        <div class="preloader"></div>
	    </div>
	    
	    <div class="infinite-scroll-preloader queryScroll1 querycounttable1" style="display:none">
	        <div class="preloader"></div>
	    </div>
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/queryCount/queryCount.js' charset='utf-8'></script>
</body>
</html>
