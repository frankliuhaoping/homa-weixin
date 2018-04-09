<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
<title>${webAppTitle}-${controllerConfig.pageTitle}</title>
</head>
<body>
	
	<div>
	  <p style="font-size:20px; text-align:center;">${employee.name}的工资明细</p>
		<table id="cpss" style="width:100%">   
				<c:forEach items="${employeeWage}" var="wage">
				<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px; ">
		   			<th style="font-size:20px;width:50%;text-align: left;">实发工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.actualWage }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">工龄工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.ageWage }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">奖励工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.awardWage }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">基本底薪</th><td style="font-size:20px;width:50%;text-align: center;">${wage.basicSalary }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">社保(公司)</th><td style="font-size:20px;width:50%;text-align: center;">${wage.companyInsurance }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">审核工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.examineWage }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">过节费</th><td style="font-size:20px;width:50%;text-align: center;">${wage.holidayFee }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">其他费用</th><td style="font-size:20px;width:50%;text-align: center;">${wage.otherFee }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">加班工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.overtimeWage }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">提成工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.royaltyWage }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">社保(个人)</th><td style="font-size:20px;width:50%;text-align: center;">${wage.socialInsurance }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">扣税</th><td style="font-size:20px;width:50%;text-align: center;">${wage.taxDeduction }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">应发工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.virtualWage }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">工资月份</th><td style="font-size:20px;width:50%;text-align: center;">${wage.wageMonth }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">第几次工资</th><td style="font-size:20px;width:50%;text-align: center;">${wage.wageVersion }</td>
		   		</tr>
		   		<tr style="width:100%;border-bottom:2px solid #ccc; line-height: 50px;">
		   			<th style="font-size:20px">工资年份</th><td style="font-size:20px;width:50%;text-align: center;">${wage.wageYear }</td>
		   		</tr>
	   			</c:forEach>
		</table> 
	</div>
   	<!-- 
   		
   	 -->
</body>
</html>