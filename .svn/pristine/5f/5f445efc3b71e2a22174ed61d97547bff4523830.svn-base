<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>上传凭证</title>
</head>
<body>
<div class="page" id="cardUpload-page">
	<div class="content">
		<input type="hidden" value="${orderId }" id="cardUploadOrderId"/>
	
		<div class="Credentials_upload">
		   <div class="upload_img">
		   <c:if test="${salesOrder.invoiceImageUrl == null}"> 
		   		<input type="file" id="uploadFileCard"  class="flies_but" capture="camera"   accept="image/*"/>
		   </c:if>
		   <c:if test="${salesOrder.invoiceImageUrl != null}"> 
		   		<input type="file" id="uploadFileCard"  class="flies_but" capture="camera"   accept="image/*" style="border-radius: 0px;width: 240px;height: 180px;margin-left: -120px;"/>
		   </c:if>
		   
		   <img src="" id="cardUploadMsg_after"/>
		   <c:if test="${salesOrder.invoiceImageUrl == null}"> 
		     	<img src="${contextPath}/static/wxworkbench/img/camera.jpg"  id="cardUploadMsg"/>
		   </c:if>
		    
		   <c:if test="${salesOrder.invoiceImageUrl != null}"> 
		   		<img src="${contextPath}${salesOrder.invoiceImageUrl}"  id="cardUploadMsg" style="border-radius: 0;width:auto;height: 180px;"/>
		   </c:if>
		   <p> 凭证拍照</p>
		   </div>
		</div>
		<div class="div_texts con_flexbox">
			<i class="iconfont_m edit_font">&#xe642;</i>
		    <input type="text" placeholder="请输入凭证编码" class="texts con_flexbox-h f20" id="invokeNo" value="${salesOrder.invoiceNo}"/>
		    <span id="scanQRCode"><i class="iconfont_m" style="color:#fff;">&#xe628;</i></span>
		</div>

		<div style="text-align: left;margin-bottom: 12px; padding-left: 12%;    margin-top: -20px;" class="f16">
			客户姓名：${salesOrder.customerName }<br>
			客户电话：${salesOrder.customerTel }<br>
		</div>


		<%-- <a href="${contextPath }/weixin/workbench/salesOrder/save"> --%>
		<button class="button_m orange" style="width:80%;margin: 0px auto;" onclick="uploadSalesOrderMsg()">提交凭证</button>
		<!-- </a> -->
	</div>
	<div class="h30"></div>
</div>
<script type='text/javascript' src='${contextPath}/static/imageUpload/getImageBase64_v3.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/imageUpload/getImageBase64_v2.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/imageUpload/mobileBUGFix.mini.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/imageUpload/megapix-image.js' charset='utf-8'></script>

<script>
	var weiXinConfigUrl = location.href.split('#')[0];
	var weiXinConfigDebug = false;
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>	
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/salesOrder/cardUpload.js' charset='utf-8'></script>
</body>
</html>
