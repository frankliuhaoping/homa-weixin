<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>修改密码</title>
</head>
<body>

<div class="page">
<div class="accordion">
<div class="sub-nav active" style="display: block;">
					<div class="html about-me">
						<div class="photo">
							<div class="photo-overlay">
								<img src="${headimg}" />
							</div>
						</div>
				     <h4>${name}</h4>
						<p>${sysRoleType}</p>
					</div>
				</div>
				
				 <div class="changePwd p8">
                <input type="password" name="username" class="txt" placeholder="请输入旧密码" id="oldpassword" />
                </div>
                
                <div class="changePwd p8">
                <input type="password" name="username" class="txt" placeholder="请输入新密码" id="newpassword" />
                </div>
                <div class="changePwd p8">
                <input type="password" name="username" class="txt" placeholder="请再输入一次新密码" id="againpassword" />
                </div>
               
                <div class="p8">
					<button class="button_m orange" id="loginButton"><i class="icon_m iconfont_m">&#xe643;</i>&nbsp;提交修改</button>
				</div>
</div>
</div>

<script>

		$("#loginButton").on("click",function changePassword(){
			var oldword =  $("#oldpassword").val();
			var newword =  $("#newpassword").val();
			var againword =  $("#againpassword").val();
		
			if(oldword==""){
				$.toast("旧密码不能为空",1000);return;
			}
			
			if(newword == oldword){
				$.toast("新旧密码不能相同",1000);return;
			}
			
			if(newword==""){
				$.toast("新密码不能为空",1000);return;
			}
	
			if(againword==""){
				$.toast("再次确认密码不能为空",1000);return;
			}
			if(newword!=againword){
				$.toast("两次输入的密码不一致",1000);return;
			}
			var submitData = {"oldWord" : oldword,"newWord" : newword};
			$.ajax({
				data:submitData,
				type:"POST",
				dataType:"json",
				url:contextPath+"/weixin/workbench/changePassword",
				success:function (data){
					$.toast(data.msg, 1000);
				},
				error:function(){
					$.toast("网络异常", 1000);
					
				}
			})
			
		});


	
	
	
	</script>

</body>


	
	</html>
	