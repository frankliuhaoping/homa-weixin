function Mark(){};
Mark.prototype = {
	init: function(){
		var that = this;
		
		$(function(){
			
			var pageId = "#mark-friend-list-page";
			if($("#mark-mine-list-page").length > 0){
				pageId = "#mark-mine-list-page";
			}
			
			var $page = $(pageId).find("input[name='page']");
			$page.val(1);
			
			if("#mark-friend-list-page" == pageId){
				that.loadMarkFriendList(pageId, 1);
				that.bindCommentEvent(pageId);
			}else if("#mark-mine-list-page" == pageId){
				that.loadMarkMineList(pageId, 1);
			}
			that.bindPhotoBowserEvent();
			
			$.initInfiniteScroll($("#mark-friend-list-page .infinite-scroll"));
			$("#mark-friend-list-page .infinite-scroll").on('infinite', function() {
				that.loadMarkFriendList("#mark-friend-list-page", 1);
			});
			
			$.initInfiniteScroll($("#mark-mine-list-page .infinite-scroll"));
			$("#mark-mine-list-page .infinite-scroll").on('infinite', function() {
				that.loadMarkMineList("#mark-mine-list-page", 1);
			});
			
		});
	},
	
	/**
	 * 
	 * @param pageId 
	 * @param type 1为append 2为prepend
	 */
	loadMarkFriendList: function(pageId, type){
		var that = this;
		if(that.loading){
			return ;
		}
		that.loading = true;
		
		if(that.isEnd){
	        return ;
		}
		$(pageId).find('.infinite-scroll-preloader').show();
		var params = $(pageId).find("#searchForm").serialize();
		$.ajax({
			data:params,
			type:"POST",
			dataType:"json",
			url: contextPath + "/weixin/workbench/marketing/experience/list",
			success:function (data){
				if(data.code == 200){
					if(data.result.length > 0){
						var html = "";
						for(var i in data.result){
							html += that.renderHtml(data.result[i]);
						}
						if(type == 1){
							$(pageId).find(".share_div").append(html);
							var $page = $(pageId).find("input[name='page']");
							$page.val(parseInt($page.val()) + 1);
						}else{
							if($(pageId).find(".share_div .more_content").length > 0){
								$(pageId).find(".share_div").prepend(html);
							}else{
								$(pageId).find(".share_div").html(html);
							}
							$(pageId).find("input[name='search.id_eq']").val("");
						}
					}else{
						if($(pageId).find(".more_content").length == 0){
							html = '<div class="no-data">暂无数据</div>';
							$(pageId).find(".share_div").html(html);
						}else{
							html = '<div class="no-data">没有更多了</div>';
							$(pageId).find(".share_div").append(html);
						}
						
						that.isEnd = true;
						$.detachInfiniteScroll($(pageId).find('.infinite-scroll'));
					}
					//容器发生改变,如果是js滚动，需要刷新滚动
			        $.refreshScroller();
				}else{
					$.toast(data.msg, 500);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				$.toast("网络异常", 500);
			},
			complete: function(XHR, TS){
				$(pageId).find('.infinite-scroll-preloader').hide();
				that.loading = false;
			}
		});
	},
	
	renderHtml: function(bean){
		var headImgUrl = bean.headImgUrl ? bean.headImgUrl : contextPath + "/static/images/head.jpg";
		var html = 	'<div class="more_content">';
		html +=		'	<div class="div_tabless cc">';
		html +=		'		<div class="avg fl">';
		html +=		'			<a href="' + contextPath + '/weixin/workbench/marketing/experience/mine?employeeId=' + bean.employeeId + '" external>';
		html +=		'			<img class="tx" src="' + headImgUrl + '" width="60" height="60">';
		html +=		'			</a>';
		html +=		'		</div>';
		html +=		'		<div class="avg_r fl">';
		html +=		'			<div class="f18 name">' + bean.nickName + '</div>';
		

		//html +=		'				<span>' + bean.categoryName + '</span>&nbsp;';
		
		
		//html +=		'		<div>';
		//html +=		'			<i class="iconfont_m">&#xe622</i> ' + bean.createdTime;
		//html +=		'		</div>';
		
		if(bean.content){
			html +=		'	<div class="p_text f16 y666">' + bean.content + '</div>';
		}
		
		if(bean.images.length > 0){
			html +=	'	<div class="p_img">';
			for(var i in bean.images){
			html +=	'	<img class="pb-standalone" src="' + contextPath + bean.images[i].imageUrl + '" width="30%">';
			}
			html +=	'	</div>';
		}
		
		
		html +=		'	<div class="f14 y999 w50b fl" style="line-height:30px;"><i class="iconfont_m">&#xe622</i> ' + bean.createdTime + '</div>';
		html +=		'	<div class="click_div cc f16 w50b fl">';
		html +=		'		<span><s>' + bean.comments.length + '</s>评论</span><span><i class="iconfont_m open-comment" data-mark-id="' + bean.id + '">&#xe64a</i></span>';
		html +=		'	</div>';
		html +=		'	</div>';
		html +=		'			</div>';
		
		
		if(bean.comments.length > 0){
			html +=	'	<div class="comment_div">';
			for(var j in bean.comments){
				html +=	'	<div>' + bean.comments[j].name + ' : ' + bean.comments[j].content + '</div>';
			}
			html +=	'	</div>';
		}
		html +=		'		</div>';
		html +=		'		</div>';
		html +=		'</div>';
		return html;
	},
	
	/**
	 * 
	 * @param pageId 
	 * @param type 1为append 2为prepend
	 */
	loadMarkMineList: function(pageId, type){
		var that = this;
		if(that.loading){
			return ;
		}
		that.loading = true;
		
		if(that.isEnd){
	        return ;
		}
		$(pageId).find('.infinite-scroll-preloader').show();
		var params = $(pageId).find("#searchForm").serialize();
		$.ajax({
			data:params,
			type:"POST",
			dataType:"json",
			url: contextPath + "/weixin/workbench/marketing/experience/list",
			success:function (data){
				if(data.code == 200){
					if(data.result.length > 0){
						var html = "";
						for(var i in data.result){
							html += that.renderHtml2(data.result[i]);
						}
						if(type == 1){
							$(pageId).find(".marketing_content").append(html);
							var $page = $(pageId).find("input[name='page']");
							$page.val(parseInt($page.val()) + 1);
						}else if(type == 2){
							if($(pageId).find(".marketing_content .table_div").length > 0){
								$(pageId).find(".marketing_content").prepend(html);
							}else{
								$(pageId).find(".marketing_content").html(html);
							}
							$(pageId).find("input[name='search.id_eq']").val("");
						}
					}else{
						if($(pageId).find(".table_div").length == 0){
							html = '<div class="no-data">暂无数据</div>';
							$(pageId).find(".marketing_content").html(html);
						}else{
							html = '<div class="no-data">没有更多了</div>';
							$(pageId).find(".marketing_content").append(html);
						}
						$.detachInfiniteScroll($(pageId).find('.infinite-scroll'));
						
						that.isEnd = true;
					}
					//容器发生改变,如果是js滚动，需要刷新滚动
			        $.refreshScroller();
				}else{
					$.toast(data.msg, 500);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				$.toast("网络异常", 500);
			},
			complete: function(XHR, TS){
				$(pageId).find('.infinite-scroll-preloader').hide();
				that.loading = false;
			}
		});
	},
	
	renderHtml2: function (bean){
		var html = 	'<div class="table_div cc">';
		html +=		'	<div style="margin: 8px 0 0 0;">';
		html +=		'		<span class="day">' + bean.createdTime.split("-")[0] + '</span>';
		html +=		'		<span class="year">' + bean.createdTime.split("-")[1] + '</span>';
		html +=		'	</div>';
		html +=		'	<div>';
		
		if(bean.content){
			html +=		'		<p class="p_text f16 y666">' + bean.content + '</p>';
		}
		
		if(bean.images.length > 0){
			for(var i in bean.images){
			html +=	'	<img class="pb-standalone" src="' + contextPath + bean.images[i].imageUrl + '" width="80" heiht="80">';
			}
		}
		html +=		'	</div>';
		html +=		'</div>';
		return html;
	},
	bindCommentEvent: function(pageId){
		$(pageId).off("click", ".open-comment");
		$(pageId).on("click", ".open-comment", function(event){
			var element = this;
			var id = $(element).data("mark-id");
			$("#mark-exce").val(id);
			$.popup('.popup-comment');
			$(".modal-overlay-visible").remove();
			
			$("#send-comment").click(function(event){
				mark.sendComment(pageId, element);
			});
			$("#popup-comment-close").click(function(event){
				$.closeModal(".popup-comment");
				$("#send-comment").off();
				$("#popup-comment-close").off();
			});
		});
	},
	openCommentPopup: function(id){
		$("#mark-exce").val(id);
		$.popup('.popup-comment');
		$(".modal-overlay-visible").remove();
	},
	sendComment: function(pageId, ele){
		var markExec = $("#mark-exce");
		var commentContent = $("#comment-content");
		if($.trim(markExec.val()) == "" || $.trim(commentContent.val()) == ""){
			$.alert("请输入内容");
			return ;
		}
		var params = {
			markId: markExec.val(),
			commentContent: commentContent.val()
		};
		$.ajax({
			data:params,
			type:"POST",
			dataType:"json",
			url: contextPath + "/weixin/workbench/marketing/experience/comment/add",
			success:function (data){
				if(data.code == 200){
					var $parentEle = $(ele).parents(".more_content");
					var c_div = $("<div>").text(data.result.commentBy + " : " + data.result.content);
					if($parentEle.find(".comment_div").length > 0){
						$parentEle.find(".comment_div").append(c_div);
					}else{
						$parentEle.append($('<div class="comment_div">').append(c_div));
					}
					markExec.val("");
					commentContent.val("");
					$.closeModal(".popup-comment");
					var $s = $(ele).parents(".click_div").find("s");
					$s.text(parseInt($s.text()) + 1);
				}else{
					$.toast(data.msg, 500);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				$.toast("网络异常", 500);
			},
			complete: function(XHR, TS){
				$("#send-comment").off();
			}
		});
	},
	bindPhotoBowserEvent:function(){
		$(document).off("click", ".pb-standalone");
		$(document).on("click", ".pb-standalone", function(event){
			var images = $(this).parent().find("img");
			var photos = new Array();
			images.each(function(index, ele){
				photos.push($(ele).attr("src"));
			});
			var myPhotoBrowserStandalone = $.photoBrowser({
				photos : photos
			});
			myPhotoBrowserStandalone.open();
		});
	}
	
};
var mark = new Mark();
mark.init();