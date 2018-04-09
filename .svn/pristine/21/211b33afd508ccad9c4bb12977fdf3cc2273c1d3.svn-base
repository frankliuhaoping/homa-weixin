<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}">
		<input type="hidden" name="version" value="${model.version}">
		<input type="hidden" name="action" value="">				
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>工号</label></div>
			<div class="grid_5"><input class="easyui-textbox" name="code" value="${model.code}" data-options="width: '100%'"></div>
			
			<div class="grid_3 align-right height-30"><label><span class="red">*&nbsp;</span>姓名</label></div>
			<div class="grid_5"><input class="easyui-textbox" name="name" value="${model.name}" data-options="width: '100%', required: true"></div>			
			<div class="clear"></div>
		</div>		

		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label><span class="red">*&nbsp;</span>组织架构</label></div>			
			<div class="grid_5"><input class="easyui-selecttextbox" name="organization.id" id="employeeOrganization" data-options="value: '${model.organization.id}', text : '${model.organization.name}', width: '100%', required: true" ></div>
			
			<div class="grid_3 align-right height-30"><label><span class="red">*&nbsp;</span>角色</label></div>
			<div class="grid_5">
				<select class="easyui-combobox" name="sysRoleType" data-options="width: '100%', editable: false, value: '${model.sysRoleType }', required: true">   
				    <option value=""></option>
				    <c:forEach items="${sysRoleTypes}" var="sysRoleType">
				    	<option value="${sysRoleType.value}">${sysRoleType.text}</option>
				    </c:forEach>
				</select>
			</div>			
			<div class="clear"></div>
		</div>		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label><span class="red">*&nbsp;</span>性别</label></div>
			<div class="grid_5">
				<select class="easyui-combobox" name="sex" id="sex" data-options="value:'${model.sex}', width: '100%', editable : false">
					<option value='0'>男</option>
					<option value='1'>女</option>
					<option value='2'>保密</option>
				</select>
			</div>
			
			<div class="grid_3 align-right height-30"><label><span class="red">*&nbsp;</span>类型</label></div>
			<div class="grid_5">
				<select class="easyui-combobox" name="salerType" data-options="value:'${model.salerType}', width: '100%', editable : false, required: true">			
					<option value='0'>专职</option>
					<option value='1'>兼职</option>
				</select>			
			</div>			
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>手机</label></div>
			<div class="grid_5"><input class="easyui-textbox" name="mobileNo" value="${model.mobileNo}" data-options="width: '100%'"></div>
			
			<div class="grid_3 align-right height-30"><label>电话</label></div>
			<div class="grid_5"><input class="easyui-textbox" name="officeTel" value="${model.officeTel}" data-options="width: '100%'"></div>			
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30">出生日期</label></div>
			<div class="grid_5"><input class="easyui-datebox" name="birthdate" value="${model.birthdate}" data-options="width: '100%'"></div>

			<div class="grid_3 align-right height-30"><label>入职日期</label></div>
			<div class="grid_5"><input class="easyui-datebox" name="inDate" value="${model.inDate}" data-options="width: '100%'"></div>			
			<div class="clear"></div>
		</div>		

		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>QQ</label></div>
			<div class="grid_5"><input class="easyui-textbox" name="qq" value="${model.qq}" data-options="width: '100%'"></div>
			
			<div class="grid_3 align-right height-30"><label>微信号</label></div>
			<div class="grid_5"><input class="easyui-textbox" name="weixinNo" value="${model.weixinNo}" data-options="width: '100%'"></div>			
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>身份证号</label></div>
			<div class="grid_13"><input class="easyui-textbox" name="idNumber" value="${model.idNumber}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>			
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>E-Mail</label></div>
			<div class="grid_13"><input class="easyui-textbox" name="eMail" value="${model.eMail}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>		
			
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>地址</label></div>
			<div class="grid_13"><input class="easyui-textbox" name="address" value="${model.address}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>		
				
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>备注</label></div>
			<div class="grid_13"><input class="easyui-textbox" name="remark" value="${model.remark}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
	</form>
</div>
<div class="space-10"></div>
<script>
	setTimeout(function() {
		commonSelect.initSelectOrganization({selecttextboxId : '#employeeOrganization'});
	}, 300);
</script>