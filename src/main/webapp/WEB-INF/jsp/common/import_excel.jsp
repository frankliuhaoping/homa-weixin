<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<div class="grid_g margin-bottom-10">
		<div class="grid_1">&nbsp;</div>		
		<div class="grid_14">1、<a class="easyui-linkbutton" id="__downloadTemplateFile">下载模板文件</a></div>
		<div class="grid_1">&nbsp;</div>
		<div class="clear"></div>
	</div>

	<div class="grid_g">
		<div class="grid_1">&nbsp;</div>	
		<div class="grid_15 height-30"><label>2、请选择要导入的文件</label></div>
		<div class="clear"></div>		
	</div>
	
	<div class="grid_g margin-bottom-10">
		<div class="grid_1">&nbsp;</div>	
		<div class="grid_14"><input class="easyui-textbox" name="file" id="__chooseFile" data-options="width: '100%', editable: false, buttonText: '选择文件'"></div>
		<div class="grid_1">&nbsp;</div>						
		<div class="clear"></div>
	</div>
</div>
<div class="space-10"></div>
