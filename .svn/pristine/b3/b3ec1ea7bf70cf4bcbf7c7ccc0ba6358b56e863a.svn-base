<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>案例详情</title>
	<style type="text/css">
	.inform_text p {
	    font-size: 18px;
	    text-indent: 0;
	    line-height: 28px;
	}
	</style>
</head>
<body>
<div class="page" id="noticetab3-page">
	<input type="hidden" id="comLoginName" value="${employeeName }"/>
	<div class="content infinite-scroll typicalDetail" data-toggle="scroller" data-distance="100" id="notice">
		<div class="p8"><img src="${contextPath}/static/wxworkbench/img/biglogo.png" width="30%" /></div>
		
		<div class="div_inform plr8 ">
		  	<input type="hidden"  value="${typicalCase.id}" id="typicalCaseDetailId"/> 
		  	<h3 class="f22 fw700" onclick="getComments()">${typicalCase.title }</h3>
		  	<p class="f14 y999 ptb8">
		  		<i class="iconfont_m">&#xe622;</i> 
		  		<fmt:formatDate value="${typicalCase.releaseTime}" pattern="yyyy-MM-dd"/>&nbsp;
		  		<fmt:formatDate value="${typicalCase.releaseTime}" pattern="HH:mm"/>
		  	</p>
		</div>
		    
		<div class="inform_text plr8">
		    <div style="text-align:center">
		    
		    <c:if test="${typicalCase.coverPictureUrl!=null && typicalCase.coverPictureUrl!=''}">
		    <img src="${contextPath }${typicalCase.coverPictureUrl}" width="100%"/>
		    </c:if>
		    
		    </div>
		    <p class="p2">${typicalCase.content }</p>
		    
		    <div class="pinlun_t">评论</div>
		    <div id="commentsDig" style="margin-bottom: 30px;">
		    	
	
	<%-- 	     <c:forEach items="${typicalCase.commentList }" var="o">
			   <section class="con_container_90" style="margin-top: 10px;">
			   		<div class="con_flexbox">
			   		<h3 class="con_flexbox-h con_ellipsis" style="color:#5E97D8; line-height: 1.5em;">${o.commentBy.employeeList[0].name}</h3>
			   		<div style="min-width:120px;"><fmt:formatDate value="${o.commentTime }" pattern="yyyy-MM-dd HH:mm"/></div>
			   		</div>
			    	<div class="con_af_com_bottomline" style="margin-top:5px;">
			    	${o.content}
			    	</div>
			    </section>
		    </c:forEach> --%>
		    
			</div>
		    
		</div>
			
		<div class="infinite-scroll-preloader typicalDetail"  style="">
	       	<div class="preloader"></div>
	    </div>
	</div>
</div>
<div class="append_con con_flexbox" style="height:35px;margin-top:15px;bottom: 0px; left: 0px; position: absolute; z-index: 2222222;background-color: #EFEFF4;">
		        <div class="con_flexbox-h" style="border: 1px solid #ccc; border-radius: 5px;    background: #fff;">
		        <div class="con_flexbox">
		        <i class="iconfont_m" style="line-height:35px;padding-left: 10px;">&#xe638</i>
		        <input type="text" placeholder="请输入" class="txts con_flexbox-h" id="commentText" style="width:100%;height:35px;border: none;outline: none;background-color: transparent;"/>
		        </div>
		        </div>
		        <div class="orange" style="width:3rem;height:100%;line-height: 35px;text-align: center;color:#fff;border-radius:5px;margin-left:5px;" onclick="toComment()"><i class="iconfont_m" style="line-height:35px;">&#xe638</i>发布</div>
		    </div> 
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/notice/typical_case.js' charset='utf-8'></script>
</body>
</html>
