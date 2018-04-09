var notice_flag = 0;
var notice_flag2 = 0;
var notice_flag3 = 0;
var notice_flag4 = 0;
var notice1_curretnPage=0;
var notice1_more=0;
var notice2_curretnPage=0;
var notice2_more=0;
var notice3_curretnPage=0;
var notice3_more=0;
var notice4_curretnPage=0;
var notice4_more=0;

$(function(){
	 notice_flag = 0;
	 notice_flag2 = 0;
	 notice_flag3 = 0;
	 notice_flag4 = 0;
	 notice1_curretnPage=0;
	 notice1_more=0;
	 notice2_curretnPage=0;
	 notice2_more=0;
	 notice3_curretnPage=0;
	 notice3_more=0;
	 notice4_curretnPage=0;
	 notice4_more=0;
	$(" .Tab_Controls div").click(function(){
		
       var _index1=$(this).index();
       $(this).addClass("active").siblings("div").removeClass("active");
       $("#notice .notice-page-notice").eq(_index1).show().siblings(".notice-page-notice").hide();
       var flag = ($(".active.noticScrollflag").attr("title"));
		var noticesflag = $(".noticesflag").css("display","none");
		console.log(flag)
		$("."+flag+"").css("display","block");
      // $(".enterprise_notice_con").eq(_index1).show().siblings(".enterprise_notice_con").hide();
   });

	noticetab1();
	noticetab2();
	noticetab3();
	noticetab4();
	
	$.initInfiniteScroll($(".infinite-scroll.noticScroll"));
	$(".infinite-scroll.noticScroll").on('infinite', function() {
		var flag = ($(".active.noticScrollflag").attr("title"));
		if(flag=="noticScroll1"){
			noticetab1();
		}else if(flag=="noticScroll2"){
			noticetab2();
		}else if(flag=="noticScroll3"){
			noticetab3();
		}else if(flag=="noticScroll4"){
			noticetab4();
		}
	});
});

function noticetab1(){
	if(notice_flag == 1 || notice1_more==1){
		return ;
	}
	notice_flag = 1;
	var page = notice1_curretnPage;
	$.ajax({
		data:page,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/notice/findAllNotice?page="+page+"&rows=9",
		success:function (data){
			notice_flag = 0;
			notice1_curretnPage++;
		    var notices = data.result;
		   // alert(notices.length);
		    var html = "";
			for(var i =0;i<notices.length;i++){
			    
			    
			    html = "<div class='enterprise_notice_con shadow' onclick='getNotiectTab1("+'"'+notices[i].id+'"'+")'>"+
				'<div class="div_dl cc p8">'+
			
			'<p class=" y333 c_orange1 f18" style="font-size: 18px;"><i class="iconfont_m">&#xe629;</i> '+notices[i].title+'</p>'+
			'<p class="f14 y999 ptb5"><i class="iconfont_m">&#xe622;</i> '+notices[i].createTime+'</p>'+
	
			//'<p class="f16 y666 ti2">'+notices[i].content+'</p>'+
		
			'</div></div>';
			    $("#notice-page-one").append(html);
			}
			
			
			if(data.result.length==0 ||!data.result ||notices[0].more == false || notices[0].more=="false" ||!data.result){
				notice1_more = 1;
				$(".infinite-scroll-preloader.noticesflag.noticScroll1").remove();
			}
			$(".enterprise_notice_con p img").remove();
			//alert(html);
		}
	});
}

function noticetab2(){
	
	if(notice_flag2 == 1 || notice2_more==1){
		return ;
	}
	notice_flag2 = 1;
	var page = notice2_curretnPage;
	
	$.ajax({
		data:page,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/news/findAllNews?page="+page+"&rows=9",
		success:function (data){
			console.log(data)
			notice_flag2 = 0;
			notice2_curretnPage++;
		    var news = data.result;
		    //alert(news.length);
		    var html = "";
			for(var i =0;i<news.length;i++){
			    html = "<div class='enterprise_notice_con shadow' onclick='getNotiectTab2("+'"'+news[i].id+'"'+")'>"+
					'<div class="div_dl cc p8">'+
				
				'<p class=" y333 c_orange1 f18" style="font-size: 18px;"><i class="iconfont_m">&#xe63d;</i> '+news[i].title+'</p>'+
				'<p class="f14 y999 ptb5"><i class="iconfont_m">&#xe622;</i> '+news[i].createTime+'</p>'+
		
				//'<p class="f16 y666 ti2">'+news[i].content+'</p>'+
			
				'</div></div>';
			    $("#notice-page-two").append(html);
			}
			
			
			if(data.result.length==0 ||!data.result ||news[0].more == false || news[0].more=="false" ||!data.result){
				notice2_more = 1;
				$(".infinite-scroll-preloader.noticesflag.noticScroll2").remove();
			}
			$(".enterprise_notice_con p img").remove();
			//alert(html);
		}
	});
}

function noticetab3(){
	
	if(notice_flag3 == 1 || notice3_more==1){
		return ;
	}
	notice_flag3 = 1;
	var page = notice3_curretnPage;
	$.ajax({
		data:page,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/typicalCase/findAllTypicalCases?page="+page+"&rows=9",
		success:function (data){
			//console.log(data)
			notice_flag3 = 0;
			notice3_curretnPage++;
		    var typicalCases = data.result;
		    //alert(typicalCases.length+"fff");
		    var html = "";
			for(var i =0;i<typicalCases.length;i++){
			    
			    
			    html = "<div class='enterprise_notice_con shadow' onclick='getNotiectTab3("+'"'+typicalCases[i].id+'"'+")'>"+
				'<div class="div_dl cc p8">'+
			
			'<p class=" y333 c_orange1 f18" style="font-size: 18px;"><i class="iconfont_m">&#xe63c;</i> '+typicalCases[i].title+'</p>'+
			'<p class="f14 y999 ptb5"><i class="iconfont_m">&#xe622;</i> '+typicalCases[i].createTime+'</p>'+
	
			//'<p class="f16 y666 ti2">'+typicalCases[i].content+'</p>'+
		
			'</div></div>';
			    $("#notice-page-three").append(html);
			}
			
			if(data.result.length==0 ||!data.result ||typicalCases[0].more == false || typicalCases[0].more=="false" ){
				notice3_more = 1;
				$(".infinite-scroll-preloader.noticesflag.noticScroll3").remove();
			}
			$(".enterprise_notice_con p img").remove();
			//alert(html);
		}
	});
}


function noticetab4(){
	
	if(notice_flag4 == 1 || notice4_more==1){
		return ;
	}
	notice_flag4 = 1;
	var page = notice4_curretnPage;
	
	$.ajax({
		data:page,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/otherInfo/findAllOtherInfo?page="+page+"&rows=9",
		success:function (data){
			console.log(data)
			notice_flag4 = 0;
			notice4_curretnPage++;
		    var otherInfo = data.result;
		    //alert(news.length);
		    var html = "";
			for(var i =0;i<otherInfo.length;i++){
			    html = "<div class='enterprise_notice_con shadow' onclick='getNotiectTab4("+'"'+otherInfo[i].id+'"'+")'>"+
					'<div class="div_dl cc p8">'+
				
				'<p class=" y333 c_orange1 f18" style="font-size: 18px;"><i class="iconfont_m">&#xe657;</i> '+otherInfo[i].title+'</p>'+
				'<p class="f14 y999 ptb5"><i class="iconfont_m">&#xe622;</i> '+otherInfo[i].createTime+'</p>'+
		
				//'<p class="f16 y666 ti2">'+news[i].content+'</p>'+
			
				'</div></div>';
			    $("#notice-page-four").append(html);
			}
			
			
			if(data.result.length==0 ||!data.result ||otherInfo[0].more == false || otherInfo[0].more=="false" ||!data.result){
				notice4_more = 1;
				$(".infinite-scroll-preloader.noticesflag.noticScroll4").remove();
			}
			$(".enterprise_notice_con p img").remove();
			//alert(html);
		}
	});
}

function getNotiectTab1(id){
	var url = contextPath+"/weixin/workbench/notice/noticeDetail?id="+id;
	window.location.href = url;
}

function getNotiectTab2(id){
	var url = contextPath+"/weixin/workbench/news/newDetail?id="+id;
	window.location.href = url;
}

function getNotiectTab3(id){
	var url = contextPath+"/weixin/workbench/typicalCase/typicalCasesDetail?id="+id;
	window.location.href = url;
}

function getNotiectTab4(id){
	var url = contextPath+"/weixin/workbench/otherInfo/otherInfoDetail?id="+id;
	window.location.href = url;
}