<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} 系统登录" />
<title>${webAppTitle} - 系统登录</title>
<script>
	$.fn.textbox.defaults.height = 40;
</script>
</head>
<body>
	<div class="space-32" style="margin-bottom: 100px">	
	</div>	
	<!-- 
	<div style="marign:0px auto;text-align:center;margin-bottom: 10px">
		<img src="${contextPath}/static/wxworkbench/img/aoma.png" style="width: 200px"/>
	</div>  -->	
	<div class="easyui-panel" title="${webAppTitle} - 系统登录" data-options="cls:'loginBox', bodyCls: 'loginBody'">
		<form id="loginForm" method="post">
			<div class="margin-bottom-10">
				<input class="easyui-textbox" name="username" id="username"
					data-options="prompt:'用户名', iconCls:'icon-man', iconWidth:38, width: '100%', required: 'true', missingMessage : '请输入用户名！'">
			</div>
			<div class="margin-bottom-10">
				<input class="easyui-textbox" type="password" name="password" id="password" 
					data-options="prompt:'密码', iconCls:'icon-lock', iconWidth:38, width: '100%', required: 'true', missingMessage : '请输入密码！'">
			</div>
			<c:if test="${captchaEnabled}">
				<div class="container_16 no-margin width-100 margin-bottom-10">
					<div class="grid_7 no-margin">
						<input class="easyui-textbox" name="captcha" id="captcha" value="" data-options="prompt:'验证码', width: '100%', required: 'true', missingMessage : '请输入验证码！'">				
					</div>
					<div class="grid_7 no-margin align-center"><img src="${contextPath}/static/captcha/30" id="captchaImg"></div>
					<div class="grid_2 no-margin width-16 align-right"><a href="#" class="easyui-linkbutton" data-options="plain: true" onClick="changeCaptcha();return false;">刷新</a></div>
					<div class="clear"></div>
				</div>
			</c:if>					
			<div class="margin-bottom-20">			
				<input type="checkbox" class="align-middle" name="rememberMe" id="rememberMe"> <label class="align-middle" for="rememberMe">下次自动登录</label>
			</div>	
			<div>
				<a href="#" class="easyui-linkbutton width-100 loginBtn" id="loginBtn" data-options="iconCls:'icon-ok', height: 45"> <span class="font-size-14">登录</span></a>
			</div>
		</form>
	</div>
	<script>
		$(function() {
			base.initLoginForm();
			$('#loginBtn').on('click', function() {
				$('#loginForm').submit();
				return false;
			});
			
			$('#username').textbox('textbox').focus();
		});
		/**
		 * 刷新验证码
		 */
		function changeCaptcha() {
			var src = $('#captchaImg').attr('src');
			var srcArray = src.split('/', 5);
			// 干拢线数
			var lineNum = parseInt(srcArray[4]);
			lineNum -= 5;
			if (lineNum < 10) {
				lineNum = 10;
			}
			srcArray[4] = String(lineNum);
			src = srcArray.join('/');
			var random = Math.random();
			src = src + ' ?' + random;
			$('#captchaImg').attr('src', src);
		}
		
		var captchaEnabled = '${captchaEnabled}';
		if (captchaEnabled=='true'){
			setTimeout(function() {
				$.ajax({
					url : contextPath + '/static/captcha/get',
					type : 'GET',
					global : false,
					dataType : 'JSON',
					success : function(data) {
						$('#captcha').textbox('setValue', data.message);
					}
				});
			}, 500);
		}
	</script>
</body>
</html>