<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.cnyirui.framework.utils.CurrentUserUtils"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="page infinite-scroll addProductScroll" id="addProduct-page">
	<div class="content infinite-scroll addProductScroll" data-distance="100">
		<div class="addp_s">
			<div class="div_input fl" style="margin:0">
		   		&nbsp;<i class="iconfont">&#xe719;</i><input class="tet" type="text" placeholder="输入关键字" id="productSearchKeyWord"/>
			</div>
		 	<button class="button_m orange fl" style="width:24%;    margin-left: 1%;height:42px;" onclick="seachProductsOnly(1)">搜索</button>
		</div>
		<ul class="Product_ul" id="productul1">
		<c:forEach items="${products}" var="o">
		    <li>
		        <div class="click_div" onclick="findProduct('${o.id}',this)" thisId="${o.id}">
		        <i class="iconfont" >&#xe98e;</i>
		        <span>${o.name}</span>
		        </div>
		        <div class="li_menu">
		        </div>
		    </li>
		    </c:forEach>
		</ul>
	
		<ul class="Product_ul" style="display:none" id="productul2">
			<li id="insertNodeProducts">
			
			</li>
		</ul>
	
		<div class="infinite-scroll-preloader addProductScroll" style="display:none">
	        <div class="preloader"></div>
	    </div>
	</div>
</div>

