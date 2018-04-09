<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.cnyirui.framework.utils.CurrentUserUtils"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title>填写收货信息</title>
	<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.5.6/css/sm.css">
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/aoma.css">	
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/aoma1.css">
	<script>
		var contextPath = "${contextPath}";
	</script>
</head>
<body>
<input type="hidden" value="${orderId}" id="orderId"/>
<div class="particular_top">
    <div class="con_flexbox">
   <!--  	<div class="con_left">
    		<i class="iconfont">&#xe99a;</i>
    	</div> -->
    	<div class="con_right con_flexbox-h">
    		<p><i class="iconfont_m">&#xe634;</i> <span>订单编号：</span>${salesOrder.orderNoN }</p>  
    		<p><i class="iconfont_m">&#xe622;</i> <span>下单时间：</span><fmt:formatDate value="${salesOrder.salesTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
    	</div>

    </div>
</div>


 <div class="order_list particular_con" style="display: block;">
        <ul style="background:#fff;">
			<c:forEach items="${salesOrder.salesOrderDetailList}" var="o">
			<li class="li1 con_af_center_bottomline con_flexbox">
				<div class="con_flexbox-h">
					<div class="con_ellipsis f16" >${o.product.name }</div>
					<em class="f14">分类：${o.product.category.name }</em>
				</div>
				<div class="" style="min-width:30px; text-align: right;">
					<div class="con_ellipsis f16" style="line-height: 25px;">￥<fmt:formatNumber value="${o.price }" pattern="0.#"/></div>
					<div class="f14">x${o.qty}</span></div>
				</div>
<%--                 <span class="span_left">${o.product.name }
                <br/><em>分类：${o.product.category.name }</em></span>
        	    <span class="span_right">￥<fmt:formatNumber value="${o.price }" pattern="0"/>
            <br/>　x${o.qty}</span> --%>
         	</li>
		</c:forEach>	
			<li class="li2" style="border:none;">
                <span class="span_left" style="text-indent:0;">共<font>${productsSum}</font>件商品</span>
                <span class="span_right">实付：<font>￥<fmt:formatNumber value="${salesOrder.salesMoney}" pattern="0.#"/></font></span>
            </li>
			
			
			
        </ul>
 </div>


<%-- <div class="Receiving_footer">
  <p class="con_container con_flexbox con_af_com_bline_10 f16">收货姓名：<input type="text" class="txt con_flexbox-h f16" id="customerName" value="${salesOrder.customerName}"/></p>
  <p class="con_container con_flexbox con_af_com_bline_10 f16">详细地址：<textarea class="txt con_flexbox-h f16" id="customerAddr">${salesOrder.customerAddress}</textarea></p>
  <p class="con_container con_flexbox f16">联系电话：<input type="text" class="txt con_flexbox-h f16" id="customerPhone" value="${salesOrder.customerTel}"/></p>
</div>
 --%>
 
 			<div class="from_div kh_Order" style="display: block; height: 250px">
				<div class="container">
					<p class="con_flexbox f16">
						<span>客户名字：</span><input type="text" class="con_flexbox-h f16"
							placeholder="你的名字" id="customerName"
							value="${salesOrder.customerName }" />
					</p>
					<p class="con_flexbox f16">
						<span>详细地址：</span>
						<textarea class="con_flexbox-h f16" style="height:90px;" id="customerAddr">${salesOrder.customerAddress}</textarea>
					</p>
					<p class="con_flexbox f16">
						<span>联系电话：</span><input type="text" class="con_flexbox-h f16"
							placeholder="您的联系方式" id="customerPhone"
							value="${salesOrder.customerTel }" />
					</p>
				</div>
			</div>

<!-- <div style="height:2em;width:60%;background:#4485C5;color:#fff;line-height:2em;font-size:1.2em;margin:10px auto;border-radius:5px;text-align:center;">提&nbsp;&nbsp;&nbsp;交</div> -->
<!-- <button class="" style="margin:10px auto;width:80%;" onclick="saveCustomerMsg()">提交</button> -->
<div class="p8"><div class="button_m orange" onclick="saveCustomerMsg()">提&nbsp;&nbsp;交</div></div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/salesOrder/customerRecv.js' charset='utf-8'></script>

<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.js' charset='utf-8'></script>
	<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.5.6/js/sm.js' charset='utf-8'></script>
</body>
</html>

