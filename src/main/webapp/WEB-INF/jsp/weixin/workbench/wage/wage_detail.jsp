<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工资通知明细</title>
</head>
<body>
<div class="page " id="wage-detail-page">
	<div class="content">
		<article class="salary" style="min-height:100%;height:100%;position:relative;">
			<div style="padding-top: 20%;">
		    <p class="salary_p">${wageYear}年${wageMonth}月&nbsp;</p>
		    <p>第${wageVersion}次通知</p>
		    <p class="salary_p1"><i class="iconfont_m">&#xe633;</i> <font><fmt:formatNumber value="${virtualWage}" pattern="#"/></font><fmt:formatNumber value="${virtualWage}" pattern="#.00" maxIntegerDigits="0"/></p>
		    </div>
			<div class=""  style="position:absolute; bottom:20%;width:70%; margin: 0 15%;">
			<c:if test="${wageVersion == 1 && !isAbnormal}"></c:if>
				  <div class="div_but">
				      <button class="button_m white" style="text-shadow:none;" onclick="wage.abnormal('${id}');"><span class="iconfont_m">&#xe615;</span>我有异议</button>
				  </div>
			</div>
		</article>
		<!-- 
		<p class="salary_p2">工资明细</p>
		<ul class="salary_ul">
		    <li>
		        <div class="li_right">基本工资</div>
		        <div class="li_left">
		        	<font><fmt:formatNumber value="${basicSalary}" pattern="#"/></font><fmt:formatNumber value="${basicSalary}" pattern="#.00" maxIntegerDigits="0"/>
		        </div>
		    </li>
		    <li>
		        <div class="li_right">提成工资</div>
		        <div class="li_left">
		        	<font><fmt:formatNumber value="${royaltyWage}" pattern="#"/></font><fmt:formatNumber value="${royaltyWage}" pattern="#.00" maxIntegerDigits="0"/>
		        </div>
		    </li>
		    <li>
		        <div class="li_right">加班工资</div>
		        <div class="li_left">
					<font><fmt:formatNumber value="${overtimeWage}" pattern="#"/></font><fmt:formatNumber value="${overtimeWage}" pattern="#.00" maxIntegerDigits="0"/>
				</div>
			</li>
		    <li>
		        <div class="li_right">社保个人扣款</div>
		        <div class="li_left li_left_red">
					<font><fmt:formatNumber value="${socialInsurance}" pattern="#"/></font><fmt:formatNumber value="${socialInsurance}" pattern="#.00" maxIntegerDigits="0"/>
				</div>
			</li>
		    <li>
		        <div class="li_right">个人所得税</div>
		        <div class="li_left li_left_red">
					<font><fmt:formatNumber value="${taxDeduction}" pattern="#"/></font><fmt:formatNumber value="${taxDeduction}" pattern="#.00" maxIntegerDigits="0"/>
				</div>
			</li>
		</ul>
		 -->
<%-- 		<footer class="salary_footer">
		  <p class="p1">说明：</p>
		  <p class="p2">1.工资属于公司保密信息，勿将个人工资信息泄露。</p>
		  <!-- 
		  <p class="p2">2.红色数字为扣款明细。</p>
		   -->
			<c:if test="${wageVersion == 1 && !isAbnormal}">
		  <div class="div_but">
		      <button class="button_m orange" style="width:90%;margin:20px auto;" onclick="wage.abnormal('${id}');">我有异议</button>
		  </div>
		  </c:if>
		</footer> --%>
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/wage/wage.js' charset='utf-8'></script>
</body>
</html>