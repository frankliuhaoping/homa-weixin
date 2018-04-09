<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>查询统计</title>
</head>
<body>
<div class="page " id="employeeMsg-page">
	<div class="content">
		<input type="hidden" value="${employee.id }" id="employeeId_1"/>
		<div class="img_top">
		    <div class="sectionOnes"><img src="${contextPath}${headurl}"/>${employee.name }</div>
		    <div class="sectionTwos " ><div><i class="iconfont_m f22">&#xe648;</i><br />签到记录</div></div>
		    <div class="sectionThrees"><div class="active"><i class="iconfont_m f22">&#xe634;</i><br />订单记录</div></div>
		</div>
		<div class="two_Tab" style="display:block; ">
			<div class="aoma_top">
			    <span style="float:left;margin-left:15px;" onclick="loadOrderMsg('SignYear','SignMonth',0)"><i class="iconfont_m">&#xe652;</i></span>
			    <span id="SignYear">${year}</span>-<span id="SignMonth">${month}</span>
			    <span style="float:right;margin-right:15px;" onclick="loadOrderMsg('SignYear','SignMonth',1)"><i class="iconfont_m">&#xe64f;</i></span>
			</div>

		    <div class="wrapss wrapss_topbottom cc">
		        <section class="sectionOne">日期</section>
		        <section class="sectionTwo">时间</section>
		        <section class="sectionThree">签到类型</section>
		    </div>
			<div id="employeeSignDiv">
			<c:forEach items="${signs }" var ="o">
				<article class="wrapss cc">
			        <section class="sectionOne">
			            <fmt:formatDate value="${o.signTime}" pattern="d"/>
			        </section>
			        <section class="sectionTwo">
			            <fmt:formatDate value="${o.signTime}" pattern="hh:mm:ss"/>
			        </section>
			        <section class="sectionThree">
			            <c:if test="${o.signType ==0}">
			            	上班
			            </c:if>
			            <c:if test="${o.signType ==1}">
			            	下班
			            </c:if>
			        </section>
			    </article>
			</c:forEach>
			</div>
		</div>

		<div class="two_Tab1">
			<div class="aoma_top">
			    <span style="float:left;margin-left:15px;" onclick="loadOrderMsg('OrderYear','OrderMonth',0)"><i class="iconfont_m">&#xe652;</i></span>
			    <span id="OrderYear">${year}</span>-<span id="OrderMonth">${month}</span>
			    <span style="float:right;margin-right:15px;" onclick="loadOrderMsg('OrderYear','OrderMonth',1)"><i class="iconfont_m">&#xe64f;</i></span>
			</div>
		    <div class="wrapss wrapss_topbottom cc">
		        <section class="sectionOne">
		           	 日期
		        </section>
		        <section class="sectionTwo">
		                              销售数量
		        </section>
		        <section class="sectionThree">
		                              销售金额
		        </section>
		    </div>

			<div id="employeeMsgDiv">
			<c:forEach items="${days}" var="o" varStatus="status">
			    <article class="wrapss cc">
			        <section class="sectionOne">
			           ${o}
			        </section>
			        <section class="sectionTwo">
			           ${nums[status.index]}
			        </section>
			        <section class="sectionThree">
			          	  ￥${moneys[status.index]}
			        </section>
			    </article>
			</c:forEach>
			</div>
		</div>
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/queryCount/OrderMsg.js' charset='utf-8'></script>
</body>
</html>
