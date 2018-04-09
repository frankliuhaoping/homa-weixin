<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>新增订单</title>
</head>
<body>
<div class="page" id="createOrder-page">
	<div class="content infinite-scroll addProductScroll" data-toggle="scroller">

		<div id="first_step">
			<input type="hidden" id="salesOrderId" value="${salesOrder.id }" />
			<!-- <header class="Create_Order_top"> 产品列表 </header> -->
	
			<div class="p8">
				<a href="javascript:void(0);" onclick="showProducts();" external>
					<button class="button_m white addProduct">
						<i class="iconfont_m">&#xe619;</i> 添加产品
					</button>
				</a>
				<div style="clear: both;"></div>
			</div>
	
			<div class="Create_Order">
	
				<div class="container p8" id="orderProducts">
	
					<c:forEach items="${salesOrder.salesOrderDetailList}" var="o">
					
	
	
	
						<div class="ul_one" thisid="${o.product.id }"
							style="position: relative; background: #fff; border: 1px solid #ccc; padding: 9px; border-radius: 5px;">
							<span
								style="display: block; font-size: 26px; line-height: 20px; text-align: center; color: #ff0000; border-radius: 50%; position: absolute; right: -3px; top: -13px;"
								onclick="closeProductDiv(this)"><i
								class="icon_m iconfont_m"></i></span>
							<div class="f18">
								<i class="iconfont_m">&#xe63f;</i> ${o.product.name }
							</div>
							<div class="proN_P">
								<div class="h_flexbox Num_Price">
									<label>数量:</label>
									<div>
										<input type="text" class="text1" value="${o.qty }" name="productnumber"
											onchange="checkSumProducts()">
									</div>
								</div>
								<div class="h_flexbox Num_Price">
									<label>单价:</label>
									<div>
										<input type="text" class="text1" value='<fmt:formatNumber value="${o.price }" pattern="0.#"/>' name="productprice"
											onchange="checkSumProducts()">
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="Create_Order_number">
								<ul class="cc ul_two">
									<li>
									<c:if test="${o.cashBack == 0}">
										<div class="div_radius"
											style="background: #ccc; margin-top: 10px;"
											onclick="switchRetMoney(this)">
											<div class="in_radius" id="retMoneyBtn" style="left: 25px"></div>
										</div> 
										</c:if>
										<c:if test="${o.cashBack != 0}">
										<div class="div_radius" style="padding-top: 10px; background: rgb(8, 148, 236);" onclick="switchRetMoney(this)">
											<div class="in_radius" id="retMoneyBtn" style="left: 0px;"></div>
											</div>
										</c:if>
										
										返现金额
									</li>
									<li class="h_flexbox"><div>
											<input type="text" placeholder="0" class="text1" id="retMoney"
												name="retMoney" <c:if test="${o.cashBack == 0}">disabled="disabled"</c:if> value='<fmt:formatNumber value="${o.cashBack}" pattern="0.#"/>'>
										</div></li>
								</ul>
							</div>
							<div style="clear: both;"></div>
						</div>
	
					</c:forEach>
				</div>
	
				<div style="clear: both;"></div>
				<div class="div_sun cc">
					<div class="container">
						<span class="span1 f18" onclick="checkSumProducts()">共<s
							id="sumofproduct" class="f20"> <c:if
									test="${salesOrder.salesOrderDetailList.size() ==null}">0</c:if>
								${salesOrder.salesOrderDetailList.size() }
	
						</s>件商品
						</span> <span class="span2 f20">￥<s id="sumofmoney">
						
						<c:if test="${salesOrder.salesMoney==null}">0</c:if>
						
						<fmt:formatNumber
									value="${salesOrder.salesMoney}" pattern="0.#" /></s></span>
					</div>
				</div>
	
				<div class="show_hide">
					<div class="container">
						<div class="div_radius1" style="background: #0894ec;"
							onclick="switchCustomerMsg(this)">
							<div class="in_radius1" id="customerMsgBtn" style="right: 25px"></div>
						</div>
						填写客户信息
					</div>
				</div>
	
				<div class="from_div" style="display: block; height: 250px">
					<div class="container">
						<p class="con_flexbox f16">
							<span>客户名字：</span><input type="text" class="con_flexbox-h f16"
								placeholder="顾客的名字" id="customerName"
								value="${salesOrder.customerName }" />
						</p>
						<p class="con_flexbox f16">
							<span>详细地址：</span>
							<textarea class="con_flexbox-h f16" style="height:90px" id="customerAddr">${salesOrder.customerAddress}</textarea>
						</p>
						<p class="con_flexbox f16">
							<span>联系电话：</span><input type="text" class="con_flexbox-h f16"
								placeholder="顾客的联系方式" id="customerPhone"
								value="${salesOrder.customerTel }" />
						</p>
					</div>
				</div>
	
			</div>
			<footer>
				<%-- <a href="${contextPath }/weixin/workbench/salesOrder/cardUpload"> --%>
				<div class="div_btn p8">
					<button class="button_m orange" onclick="saveSalesOrder()">提交订单</button>
				</div>
				<!-- </a> -->
				<div class="h30"></div>
			</footer>
		</div>
		
		<div id="second_step" style="display:none;">
			<div class="addp_s">
				<div class="div_input fl" style="margin:0">
			   		&nbsp;<i class="iconfont_m">&#xe614;</i><input class="tet" type="text" placeholder="输入关键字" id="productSearchKeyWord"/>
				</div>
			 	<button class="button_m orange fl" style="width:24%;margin-left: 1%;height:42px;" onclick="seachProductsOnly(1)">搜索</button>
			</div>
			<ul class="Product_ul" id="productul1">
			<c:forEach items="${products}" var="o">
			    <li>
			        <div class="click_div" onclick="findProduct('${o.id}',this)" thisId="${o.id}">
			        <i class="iconfont_m" >&#xe63f;</i>
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
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/salesOrder/createOrder.js' charset='utf-8'></script>
</body>
</html>
