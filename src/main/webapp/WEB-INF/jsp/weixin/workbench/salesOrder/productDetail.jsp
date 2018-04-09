<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>订单详细</title>
</head>
<body>
<div class="page " id="productDetail-page">
	<div class="content">
<div class="particular_top">
    <div class="top_auto">
        <!-- <h4><i class="iconfont">&#xe99a;</i>订单状态：已完成</h4> -->
        <p><i class="icon_m iconfont_m">&#xe634;</i> 订单号：${salesOrder.orderNoN }</p>
        <p><i class="icon_m iconfont_m">&#xe622;</i> 下单时间：<fmt:formatDate value="${salesOrder.salesTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
    </div>
</div>

<div class="particular_top1">
    <div class="con_flexbox"><div class="w7em"><i class="icon_m iconfont_m">&#xe651;</i>客户姓名：</div><div class="con_flexbox-h">${salesOrder.customerName }</div></div>
	<div class="con_flexbox"><div class="w7em"><i class="icon_m iconfont_m">&#xe655;</i>客户电话：</div><div class="con_flexbox-h">${salesOrder.customerTel }</div></div>
    <div class="con_flexbox"><div class="w7em"><i class="iconfont_m" >&#xe654;</i>客户地址：</div><div class="con_flexbox-h">${salesOrder.customerAddress }</div></div>
</div>

<%--  <div class="order_list particular_con" style="display: block; background: #fff; ">
        <ul>
			<c:forEach items="${salesOrder.salesOrderDetailList}" var="o">
				<li class="li1">
             	   <span class="span_left">${o.product.name }
             	   <br/><em>分类：${o.product.category.name }</em></span>
         		 	  <span class="span_right">
         		 	  <p class="f16">￥<fmt:formatNumber value="${o.price }" pattern="0"/></p>
         		 	  <p class="f14">x${o.qty }</p>
         		   　</span>
          		</li>
			</c:forEach>
            <li class="li2" style="border:none;">
                <span class="span_left" style="text-indent:0;">共<font>${productsSum}</font>件商品</span>
                <span class="span_right">实付：<font>￥<fmt:formatNumber value="${salesOrder.salesMoney}" pattern="0.00"/></font></span>
            </li>
        </ul>
 </div> --%>
 
 
  <div class="order_list particular_con" style="display: block;">
        <ul style="background:#fff;">
			<c:forEach items="${salesOrder.salesOrderDetailList}" var="o">
			<li class="li1 con_af_center_bottomline con_flexbox">
				<div class="con_flexbox-h">
					<div class="con_ellipsis f18" ><i class="iconfont_m">&#xe63f;</i> ${o.product.name }</div>
					<em class="f14 y999">分类：${o.product.category.name }</em>
				</div>
				<div class="" style="min-width:30px; text-align: right;">
					<div class="con_ellipsis f16" style="line-height: 25px;">￥<fmt:formatNumber value="${o.price }" pattern="0.#"/></div>
					<div class="f14">x${o.qty}</span></div>
				</div>

         	</li>
		</c:forEach>	
			<li class="li2" style="border:none;">
                <span class="span_left" style="text-indent:0;">共<font>${productsSum}</font>件商品</span>
                <span class="span_right">实付：<font>￥<fmt:formatNumber value="${salesOrder.salesMoney}" pattern="0.#"/></font></span>
            </li>
			
			
			
        </ul>
 </div>


<div class="footer_con cc">
  <p><i class="iconfont_m" style="line-height:45px;">&#xe657;</i> 凭证信息</p>
    <div class="publish_img cc">
    	<c:if test="${salesOrder.invoiceImageUrl==null || salesOrder.invoiceImageUrl=='null' || salesOrder.invoiceImageUrl=='' }">
    	<div  style="margin:50px auto;text-align: center;">暂无凭证</div>
    	</c:if>
    	<c:if test="${salesOrder.invoiceImageUrl!=null}">
    		<img src="${contextPath }${salesOrder.invoiceImageUrl}" width="90%" style="height: 136px;" onclick="showPictrueDetial(this)"/>
    	</c:if>
        
    </div>
    <div class="d_barcode cc">
        <img src="${contextPath }/weixin/qrCode/so/${salesOrder.id}" width="90%" onclick="showPictrueDetial(this)"/>
    </div>
</div>

<footer class="salary_footer more_css_footer" style="padding-top: 19px;">


<c:if test="${salesOrder.isUpload == false }">
   
	    <div class="div_but" style="width:46%;">	    
	    <a href="${contextPath}/weixin/workbench/salesOrder/cardUpload?orderId=${salesOrder.id}" style="color: white;" external>
	        <button style="width:90%;margin: 0 0 0 7%;" class="button_m orange"><i class="icon_m iconfont_m" style="color: white;">&#xe63a;</i> 上传凭证<tton>	   
	   	</a>
	    </div>
    

<div class="row" style="margin-top:20px;margin-bottom:20px;">
      <div class="col-50">
			<a href="${contextPath}/weixin/workbench/salesOrder/createOrder?orderId=${salesOrder.id}" external>
  			  <div class="div_but">
    	  <button style="width:86%;margin:0 auto;" class="button_m green"><i class="icon_m iconfont_m">&#xe638;</i> 修改<tton>
        
    </div></a>
	  </div>
      <div class="col-50">
<button style="width:86%;margin:0 auto;" class="button_m blue" onclick="deleteOrder('${salesOrder.id}')"><i class="icon_m iconfont_m">&#xe639;</i> 删除</button>
     </div>
</div>
</c:if>
<c:if test="${salesOrder.isUpload == true }">
	<p style="text-align:center;padding-bottom:20px">订单已上报内销系统,不能进行修改</p>
</c:if>
</footer>
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/salesOrder/productDetail.js' charset='utf-8'></script>
</body>
</html>
