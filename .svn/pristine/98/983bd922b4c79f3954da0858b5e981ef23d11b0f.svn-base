<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.cnyirui.framework.utils.CurrentUserUtils"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
	<title>签到历史</title>
</head>
<body>

<div class="page " id="sales_sign-historyList">
	<div class="content">
	
	 <div class="aoma_top">
    <span style="float:left;margin-left:15px;" class="date_reduce"><i class="iconfont">&#xeb3e;</i></span>
    <span class="date_year">2015</span>-<span class="date_month">4</span>
    <span style="float:right;margin-right:15px;" class="date_plus"><i class="iconfont">&#xeb58;</i></span>
</div>


<div class="wrapss wrapss_topbottom cc f16">
    <section class="sectionOne">
        日期
    </section>
    <section class="sectionTwo">
        时间
    </section>
    <section class="sectionThree">
         签到类型
    </section>
</div>

<div id="contentData">

 <c:forEach items="${signList}" var="s">

<article class="wrapss cc">
    <section class="sectionOne">
<fmt:formatDate value="${s.signTime}" pattern="yyyy-MM-dd"/>
    </section>
    <section class="sectionTwo">
  <fmt:formatDate value="${s.signTime}" pattern="HH:mm:ss"/>
    </section>
    <section class="sectionThree">

        
        <c:if test="${s.signType eq 0}">
        
         上班
        
        </c:if>
        
         <c:if test="${s.signType eq 1}">
         下班
        </c:if>
        
    </section>
    
</article>

 </c:forEach>
 
 </div>
	
	</div>
	</div>
	
	 <script type='text/javascript' src='${contextPath}/static/wxworkbench/js/sign/salesHistoryList.js' charset='utf-8'></script>
	
	</body>
	</html>
	