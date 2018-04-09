<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

				<label>年月 </label>
				<%
						Calendar calendar = Calendar.getInstance();
						int year = calendar.get(Calendar.YEAR);
						int month = calendar.get(Calendar.MONTH) + 1;
				%>
				<select class="easyui-combobox" name="search.wageYear_eq" data-options="width: 70, value: <%=year %>, editable: false">
				<%
						for(int i = year - 5; i < year + 5; i++){
							out.println("<option value=\"" + i + "\">"+ i +"</option>");
						}
				%>
				</select>
				<select class="easyui-combobox" name="search.wageMonth_eq" data-options="width: 50, value: <%=month %>, editable: false">
				<%
						for(int i = 1; i <= 12; i++){
							out.println("<option value=\"" + i + "\">"+ i +"</option>");
						}	
				 %> 
				</select>