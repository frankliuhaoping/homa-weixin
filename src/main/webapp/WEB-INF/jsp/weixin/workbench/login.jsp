<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.5.6/css/sm.css">
<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/aoma.css">
<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/aoma1.css">

<script>
	var contextPath = "${contextPath}";
</script>

<meta name="description" content="我的工作台登录" />
<title>我的工作台登录</title>
</head>
<body>

	<div class="page">
		<div class="content-padded">
			<div class="working_platform_top">
				<img src="${contextPath}/static/wxworkbench/img/aoma.png" width="70%" />
				<div class="working_platform">
					<div class="working_platform_con" style="border-bottom:none">
						<i class="icon_m iconfont_m">&#xe610;</i><input type="text" name="username" class="txt" placeholder="帐号/工号/手机号" id="username" />
					</div>
					<div class="working_platform_con" >
						<i class="icon_m iconfont_m">&#xe605;</i><input type="password" name="password" class="txt" placeholder="密码" id="password" />
					</div>
				</div>
				<div class="loginbtn">
					<button class="button_m orange" id="loginButton"><i class="icon_m iconfont_m">&#xe60e;</i>&nbsp;绑定微信账号</button>
				</div>		
			</div>
		</div>
	</div>
	<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.js' charset='utf-8'></script>
	<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.5.6/js/sm.js' charset='utf-8'></script>
	<script>
		function validateLogin() {
			if ($('#username').val() == '') {
				$('#username').focus();
				$.toast("请输入 帐号/工号/手机号码");
				return false;
			}

			if ($('#password').val() == '') {
				$('#password').focus();
				$.toast("请输入 登录密码");
				return false;
			}
			return true;
		}

		$(function() {
			$('#loginButton').on('click', function() {
				if (validateLogin()) {
					var username = $('#username').val();
					var password = $('#password').val();
					$.post(contextPath + '/weixin/workbench/login/tologin', {
						'username' : username,
						'password' : password
					}, function(response) {
						if (response.success == 'yes') {
							window.location.href = response.message;
						} else {
							$.toast(response.message);
						}
					}, 'json');
				}
			});
		});			
	</script>
</body>
</html>