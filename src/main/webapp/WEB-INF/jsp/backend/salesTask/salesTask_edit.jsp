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
			<div class="grid_2 align-right height-30"><label>类型</label></div>
			<div class="grid_6">
				<select id="type" name="type" class="easyui-combobox grid_2" data-options="value:'${model.type}', width: '100%'">
  					<option value="1" selected = "selected">数量</option>
  					<option value="0">金额</option>
				</select>
			</div>
			
			<div class="grid_2 align-right height-30"><label>年份</label></div>
			<div class="grid_6">
				<select id="year" name="year" class="easyui-combobox" data-options="width: '100%'">
	  				<option value="${model.year-9}">${model.year-9}</option>
	  				<option value="${model.year-8}">${model.year-8}</option>
	  				<option value="${model.year-7}">${model.year-7}</option>
	  				<option value="${model.year-6}">${model.year-6}</option>
	  				<option value="${model.year-5}">${model.year-5}</option>
	  				<option value="${model.year-4}">${model.year-4}</option>
	  				<option value="${model.year-3}">${model.year-3}</option>
	  				<option value="${model.year-2}">${model.year-2}</option>
	  				<option value="${model.year-1}">${model.year-1}</option>
	  				<option value="${model.year}" selected = "selected" >${model.year}</option>
	  				<option value="${model.year+1}">${model.year+1}</option>
	  				<option value="${model.year+2}">${model.year+2}</option>
	  				<option value="${model.year+3}">${model.year+3}</option>
	  				<option value="${model.year+4}">${model.year+4}</option>
	  				<option value="${model.year+5}">${model.year+5}</option>
	  				<option value="${model.year+6}">${model.year+6}</option>
	  				<option value="${model.year+7}">${model.year+7}</option>
	  				<option value="${model.year+8}">${model.year+8}</option>
	  				<option value="${model.year+9}">${model.year+9}</option>
				</select>
			</div>			
			<div class="clear"></div>
		</div>		
					
		<div class="grid_g margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>组织架构</label></div>			
			<div class="grid_6">
			   <input class="easyui-combobox" id="org" name="organization.id"  
			    	data-options="
			    		width: '100%',
			    		required: true,
			    		valueField:'id',
			    		textField:'name',
			    		url:'${contextPath}/backend/organization/getSubOrganization',
			    		onSelect: organizationSelect" />	
			</div>
			
			<div class="grid_2 align-right height-30"><label>姓名</label></div>			
			<div class="grid_6">
			   <input class="easyui-combobox" name="employee.id" id="empl" value="${model.employee.id}"  data-options="width: '100%',valueField:'id',textField:'name',"/>			
			</div>			
			<div class="clear"></div>
		</div>
	
		<div class="grid_g grid_g_month margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>一月</label></div>
			<div class="grid_6"><input class="easyui-numberbox"  name="month1" value="${model.month1}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>
			
			<div class="grid_2 align-right height-30"><label>二月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month2" value="${model.month2}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>			
			<div class="clear"></div>
		</div>
			
		<div class="grid_g grid_g_month margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>三月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month3" value="${model.month3}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>
			
			<div class="grid_2 align-right height-30"><label>四月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month4" value="${model.month4}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>			
			<div class="clear"></div>
		</div>
		
		<div class="grid_g grid_g_month margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>五月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month5" value="${model.month5}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>
			
			<div class="grid_2 align-right height-30"><label>六月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month6" value="${model.month6}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>			
			<div class="clear"></div>
		</div>
				
		<div class="grid_g grid_g_month margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>七月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month7" value="${model.month7}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>

			<div class="grid_2 align-right height-30"><label>八月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month8" value="${model.month8}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>			
			<div class="clear"></div>
		</div>
		
		<div class="grid_g grid_g_month margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>九月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month9" value="${model.month9}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>

			<div class="grid_2 align-right height-30"><label>十月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month10" value="${model.month10}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g grid_g_month margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>十一月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month11" value="${model.month11}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>
			
			<div class="grid_2 align-right height-30"><label>十二月</label></div>
			<div class="grid_6"><input class="easyui-numberbox" name="month12" value="${model.month12}" data-options="width: '100%', required: true, missingMessage:'必须填写整数', onChange: calcTotal"></div>			
			<div class="clear"></div>
		</div>	
		
		<div class="grid_g grid_g_month margin-bottom-10">
			<div class="grid_2 align-right height-30"><label>总任务数</label></div>
			<div class="grid_14"><input class="easyui-numberbox" name="total" value="${model.month11}" data-options="width: '100%', required: true, disabled: true"></div>
			<div class="clear"></div>			
		</div>			
	</form>
</div>
<div class="space-10"></div>
<script>
	var organizationId = '${model.organization.id}';
	var employeeId = '${model.employee.id}';
</script>