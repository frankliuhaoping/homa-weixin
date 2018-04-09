<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<style type="text/css">
	.slideBox{ position:relative; width:320px;  height:152px; overflow:hidden; margin:10px auto; }
	.slideBox .hd{ position:absolute; width:100%;  height:27px; bottom:0; left:0; z-index:1; }
	.slideBox .hd img{ width:11px;  }
	.slideBox .prev,.slideBox .next{ position:absolute; left:0; top:0; display:block; width:23px; height:27px; line-height:27px; text-align:center;   }
	.slideBox .next{ left:auto; right:0;}
	.slideBox .bd{ position:relative; z-index:0; }
	.slideBox .bd li{ position:relative; }
	.slideBox .bd li img{ width:320px;  height:152px; display:block;   }
	.slideBox .bd li a{ -webkit-tap-highlight-color:rgba(0, 0, 0, 0); /* 取消链接高亮 */  }
	.slideBox .bd li .tit{ display:block; width:100%;  position:absolute; bottom:0; text-indent:10px; height:27px; line-height:27px;  text-align:center;  color:#fff; background-color:rgba(0,0,0,0.7); ; 
	}
</style>

<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/aoma1.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/lightbox.css"/>



<div class="container_16">

 

	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}"> <input
			type="hidden" name="version" value="${model.version}"> <input
			type="hidden" name="action" value="">


          <div class="more_content" style="border:none">
      <div class="div_tabless cc">
              <div><img src="${employee.headImgUrl}" width="60" height="60"/></div>
              <div>
                  <p>${employee.nickName}</p>
                  <!-- <div><span></span>&nbsp;${employee.address}</div> -->
              </div>
              <div>
                  <i class="iconfont_m">&#xe622;</i> <fmt:formatDate value="${market.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
              </div>
      </div>

      <p class="p_text">${market.content}</p>
      
      			<div id="slideBox" class="slideBox">

				<div class="bd">
					<ul>
							<c:if test="${fn:length(imageList) > 0}">
				<c:forEach items="${imageList}" var="i">
					            <li>
									<a class="pic" href="${contextPath}${i.imageUrl}" data-lightbox="example-set"><img src="${contextPath}${i.imageUrl}" /></a>
								</li>
				</c:forEach>
				</c:if>
					</ul>
				</div>

				<div class="hd">
					<span class="prev"><img src="${contextPath}/static/images/sohu-prev.png"/></span>
					<span class="next"><img src="${contextPath}/static/images/sohu-next.png"/></span>
				</div>

			</div>
	
       <div class="click_div cc"><span><s style="color: #FC0505;">${fn:length(comments)}</s>评论</span><span><i class="iconfont_m">&#xe658;</i></span></div>

      <div style="margin-top:15px;">
		<c:if test="${fn:length(comments) > 0}">
		
		<c:forEach items="${comments}" var="i" varStatus="status">
		${status.index + 1}楼：
		
		<div style="border-bottom:1px solid #ccc;">${i.content}</div>
		</c:forEach>
		
		</c:if>
      </div>
  </div>
</form>

</div>
	<script src="${contextPath}/static/js/TouchSlide.1.1.js"></script>
<script src="${contextPath}/static/js/lightbox-2.6.min.js"></script>		
<script>
    
      var listimg = "${fn:length(imageList)}";
      if (listimg>0) {
    	  TouchSlide({ slideCell:"#slideBox", mainCell:".bd ul", effect:"leftLoop" });
	}

       $(".bd a").on("click,",function(){
    	  this.lightBox(); 
         });
    
			</script>

