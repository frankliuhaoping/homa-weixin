<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询统计</title>
</head>
<body>
<div class="page" id="MemployeeMsg-page">
	<div class="content">
		<input type="hidden" value="${eid}" id="departmentId"/>
		<div class="img_top">
		    <div class="sectionOnes"><img src="${contextPath }/static/wxworkbench/img/home9.png"/>${name}</div>
		</div>
		<div class="aoma_top">
		    <span style="float:left;margin-left:15px;" onclick="loadDepartMsg('DepartYear','DepartMonth',0)"><i class="iconfont_m">&#xe652;</i></span>
		    <span id="DepartYear">${year}</span>-<span id="DepartMonth">${month}</span>
		    <span style="float:right;margin-right:15px;" onclick="loadDepartMsg('DepartYear','DepartMonth',1)"><i class="iconfont_m">&#xe64f;</i></span>
		</div>

		<div class="two_Tab" style="display:block; ">
		    <div class="wrapss wrapss_topbottom cc">
		        <section class="sectionOne">日期</section>
		        <section class="sectionTwo">销售数量</section>
		        <section class="sectionThree">销售金额</section>
		    </div>
		
			<div id="departmentDiv">
			<c:forEach items="${list }" var="o">
				<article class="wrapss cc">
			        <section class="sectionOne">
			            ${o.day }
			        </section>
			        <section class="sectionTwo">
			           ${o.nums }
			        </section>
			        <section class="sectionThree">
			            <fmt:formatNumber value="${o.salesMoney }" pattern="0"/>
			        </section>
			    </article>
			</c:forEach>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="departMentFlag"/>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/queryCount/OrderMsg.js' charset='utf-8'></script>
</body>
</html>
