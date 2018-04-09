var commentPage = 0;
var commentFlag = 0;
var commentMore = 0;
var pageinit = 0;

$(function(){
	commentPage = 0;
	 commentFlag = 0;
	 commentMore = 0;
	 getComments();
	 console.log("load page1");
	 pageinit = 1;
	 
	 $.initInfiniteScroll($(".infinite-scroll.typicalDetail"));
	 $(".infinite-scroll.typicalDetail").on('infinite', function() {
		 getComments();
	 });
});

function getComments(){
	if(commentFlag == 1 || commentMore==1){
		return ;
	}
	commentFlag = 1;
	
	var id =$("#typicalCaseDetailId").val();
	
	
	var len = $(".messageItem").length;
	if(!len){
		len = 0;
		
	}
	console.log(len)
	var page = (len/9) +1;
	var submitData={'search.typicalCase.id_eq':id,page:page,rows:9};
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/typicalCase/getMoreComment",
		success:function (data){
			console.log(data)
			commentFlag = 0;
			commentPage++;
		    var notices = data.result;
		    var html = "";
			for(var i =0;i<notices.length;i++){
				var html='<section class="con_container_90 messageItem" style="margin-top: 10px;">'+
		   		'<div class="con_flexbox">'+
		   		'<h3 class="con_flexbox-h con_ellipsis" style="color:#5E97D8; line-height: 1.5em;">'+notices[i].name+'</h3>'+
		   		'<div style="min-width:120px;">'+notices[i].time+'</div>'+
		   		'</div>'+
		    	'<div class="con_af_com_bottomline" style="margin-top:5px;">'+
		    	notices[i].content+
		    	'</div>'+
		    '</section>';
				$("#commentsDig").append(html);
			}
			
			if(data.result.length==0 ||notices[0].more == false || notices[0].more=="false" ||!data.result){
				commentMore = 1;
				$(".infinite-scroll-preloader.typicalDetail").remove();
			}
		},error:function(){
			$.toast("网络异常");commentFlag = 0;
		}
	});
}

function toComment(){
	var id =  $("#typicalCaseDetailId").val();
	var content = $("#commentText").val();
	if(content==""){
		$.toast("请输入评论",600);
		return ;
	}
	var submitData={id:id,content:content};
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/typicalCase/saveComment",
		success:function (data){
			if(data.code == 200){
				$.toast("发布成功",600);
				addToComments($("#commentText").val());
				$("#commentText").val("");
			}else{
				$.toast(data.msg,600);
			}
		},error:function(){
			$.toast("网络异常",600);
			return ;
			
		}
	});
	
}

function addToComments(contentx){
	var d = new Date();
	var date = d.getFullYear()+"-"+(parseInt(d.getMonth())+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+':'+d.getSeconds();
	var content = contentx;
	var name = $("#comLoginName").val();
	
	var html='<section class="con_container_90 messageItem" style="margin-top: 10px;">'+
		'<div class="con_flexbox">'+
   		'<h3 class="con_flexbox-h con_ellipsis" style="color:#5E97D8; line-height: 1.5em;">'+name+'</h3>'+
   		'<div style="min-width:120px;">'+date+'</div>'+
   		'</div>'+
    	'<div class="con_af_com_bottomline" style="margin-top:5px;">'+
    	content+
    	'</div>'+
    '</section>';
	$("#commentsDig").prepend(html);
}