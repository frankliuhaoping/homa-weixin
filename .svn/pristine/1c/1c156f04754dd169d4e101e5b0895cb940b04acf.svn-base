<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div style="margin:20px auto; text-align:center">
	<label>请选择年月 </label>
	<%
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
	%>
	<select class="easyui-combobox" data-options="width: 70, value: <%=year %>, editable: false" id="cbSelectYear">
	<%
			for(int i = year - 5; i < year + 5; i++){
				out.println("<option value=\"" + i + "\">"+ i +"</option>");
			}
	%>
	</select>
	<select class="easyui-combobox" data-options="width: 50, value: <%=month %>, editable: false" id="cbSelectMonth">
	<%
			for(int i = 1; i <= 12; i++){
				out.println("<option value=\"" + i + "\">"+ i +"</option>");
			}	
	 %> 
	</select>
</div>