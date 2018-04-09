<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style>
        *{margin:0;padding:0;}

        /*清除浮动代码*/
        .cc { zoom: 1; }
        .cc:after { content: '\20'; display: block; height: 0; clear: both; visibility: hidden; }
        .c { clear: both; height: 0; font: 0/0 Arial; overflow: hidden; width: 0; }
        /*end*/
        .pinlin{width:100%;margin-top:15px;border-bottom:1px solid #7B7B7B;}
        .pinlin ul li{list-style-type:none;word-break: break-all;word-wrap: break-word; }
        .pinlin ul li.li1{float:left;width:50%;}
        .pinlin ul li.li2{float:right;width:30%;}
    </style>
<div class="space-10"></div>
<div class="container_16">
	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}">
		<input type="hidden" name="version" value="${model.version}">
		<input type="hidden" name="action" value="">
		
		<div style="text-align:center;" >
			<span style="font-size:20px;">${typicalCase.title}案例的评论</span>
		</div>
		<br/>
		<hr/>
		
		<c:forEach items="${typicalCase.commentList}" var="list" varStatus="status">
			
			<div class="pinlin">
				 <ul class="cc">
				     <li class="li1"><p>${list.content}</p><p style="line-height: 50px;"><fmt:formatDate value="${list.commentTime}" pattern="yyyy-MM-dd hh:mm:ss" /></p></li>
				     <li class="li2">
				     	<div>
							评论人：${list.commentBy.realName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="btn" href="#" class="easyui-linkbutton" title="删除评论" onclick="del(${list.id},this);return false;" data-options="iconCls:'icon-remove', plain:true">删除</a>  
						</div>
					 </li>
				 </ul>
			</div>
			
		</c:forEach>
	</form>
</div>
<script>
	function del(id,ele) {
		if(window.confirm('确定删除该条评论吗？？？')) {
			$.post("${contextPath}/backend/typicalCase/deleteComment/" + id,function(data){
				if(data.success == "yes") {
					$(ele).parents(".pinlin").remove();
				} else {
					alert("删除该条评论失败！");
				}
			},'json');
		}
	}
</script>
<div class="space-10"></div>
