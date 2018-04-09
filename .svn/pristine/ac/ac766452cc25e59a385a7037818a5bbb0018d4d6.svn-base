<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>发布营销经验</title>
	<style>
	.file_div{position:relative}
	#uploadFileCard{    
		opacity: 0;
	    position: absolute;
	    left: 0;
	    top:0;
	    height: 38px;
	}
	</style>
</head>
<body>
<script type='text/javascript' src='${contextPath}/static/imageUpload/getImageBase64_v3.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/imageUpload/getImageBase64_v2.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/imageUpload/mobileBUGFix.mini.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/imageUpload/megapix-image.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/mark/markAdd.js' charset='utf-8'></script>
<div class="page" id="mark-add-page">
	<form id="fileForm" action="<c:url value='/weixin/workbench/marketing/experience/doAdd'/>" method="post" enctype="multipart/form-data" target="fileFrame">
		
	</form>
	<div class="content" style="overflow:hidden;">
		<div class="Release p8">
			<textarea placeholder="请输入" id="text"></textarea>

			<div class="file_div">
				<span class="span1">
					<i class="iconfont_m">&#xe63a;</i> 
					<input type="file" id="uploadFileCard" onchange="markAdd.fileChange(this.files);" class="flies_but" capture="camera" accept="image/*"/>
					选择图片
				</span>
				<div class="more_img cc">
					
				</div>
			</div>
			<button class="button_m orange" style="width: 100%; margin: 35px auto 0;" onclick="markAdd.send();"><i class="iconfont_m">&#xe636;</i> 发布</button>
			<input type="hidden" value="${employeeId}" id="employeeId">
		</div>
	</div>
	<iframe id="fileFrame" name="fileFrame" style="display:none" onload="markAdd.uploadSuccess();"></iframe> 
</div>
</body>
</html>