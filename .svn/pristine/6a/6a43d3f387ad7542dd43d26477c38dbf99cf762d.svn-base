var salesOrder_keyWord="";
var salesOrder_curretnPage=0;
var salesOrder_flag = 0;
var salesOrder_hasNext = 1;

$(function(){
	$.getCurrentPage();
	salesOrder_curretnPage=0;
	salesOrder_flag= 0;
	salesOrder_hasNext = 1;
	salesOrder_keyWord="";
	$('#salesOrderTable').html("");
	load_SalesOrder_Page();
	
	$(".order_list_tab span").on("click",function(){
		$(this).addClass("order_list_active").siblings().removeClass("order_list_active");
		searchTime();
	});
	
	$.initInfiniteScroll($(".infinite-scroll.salesOrderScroll"));
	$(".infinite-scroll.salesOrderScroll").on('infinite', function() {
		load_SalesOrder_Page();
	});
});

function load_SalesOrder_Page(){
	if(salesOrder_flag == 0){
		salesOrder_flag = 1;
	}else{
		return ;
	}
	if(salesOrder_hasNext=='0'){
        $('.infinite-scroll-preloader.salesOrderScroll').css("display","none");
        return ;
	}
	
	$('.infinite-scroll-preloader.salesOrderScroll').css("display","block");
/*	$.showPreloader();	$.hideIndicator();
	$.showPreloader('提交数据中....')*/
	var time = $(".order_list_active").attr("title");
	var submitData = {keyWord:salesOrder_keyWord,currentPage:salesOrder_curretnPage,time:time};
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/salesOrder/orderListJson",
		success:function (data){
			//$.hidePreloader();
			console.log(data.result)
			if(data.result.length==0){
				$('.infinite-scroll-preloader.salesOrderScroll').css("display","none");
/*				$('#salesOrderTable').append("亲你没有订单");
*/			}
			salesOrder_curretnPage++;
			salesOrder_flag = 0;
			appendSalesOrder(data.result);
			
			if(salesOrder_hasNext=='0'){
		        $('.infinite-scroll-preloader.salesOrderScroll').css("display","none");
		        return ;
			}
			/*$.refreshScroller();*/
		},
		error:function(){
			$.toast("网络异常", 1000);
			/*$.hidePreloader();*/
		}
	})
}
function appendSalesOrder(data){
	for(var i=0;i<data.length;i++){
	var html ="";
	var temp = '	<div class="div_td1"><i class="iconfont_m">&#xe634;</i>&nbsp;订单-'+data[i].invoiceNo+'</div>	';
	if(data[i].customerName==""){
		temp = '	<div class="div_td1"><i class="iconfont_m" style="color:#DEAB7D">&#xe615;</i>&nbsp;订单-'+data[i].invoiceNo+'</div>	';
	}
	if(data[i].flag=="false" ||data[i].flag==false){
		temp = '	<div class="div_td1"><i class="iconfont_m" style="color:red">&#xe615;</i>&nbsp;订单-'+data[i].invoiceNo+'</div>	';
		
	}

	
	
	
	html = '<a href="'+contextPath+"/weixin/workbench/salesOrder/productDetail?orderId="+data[i].id+'" title="'+data[i].id+'" external>'+
	'<div class="div_tr">'+
	'<div class="div_td">	'+
	temp+
	'	<div class="div_td2">'+data[i].salesTime+"&nbsp;"+data[i].time+'</div>'+
	'</div>	'+
		
	'<div class="div_td">		'+
	'	<div class="o_je"><i class="icon_m iconfont_m">&#xe624;</i><span>'+data[i].salesMoney+'</span></div>'+
	'	<div class="o_name"><p><i class="icon_m iconfont_m">&#xe627;</i>'+data[i].customerName+' &nbsp;&nbsp;&nbsp;&nbsp; </p><p><i class="icon_m iconfont_m">&#xe604;</i>'+data[i].phone+'</p></div>'+
	'</div>'+
'</div>';
	
	

'</a>';
	$('#salesOrderTable').append(html);
	salesOrder_hasNext = data[i].more;
	}
}

function searchKeyWord(){
	salesOrder_keyWord  = $('#salesOrder_keyWord').val();
	console.log(salesOrder_keyWord)
	$('#salesOrderTable').html("");
	salesOrder_curretnPage=0;
	salesOrder_flag= 0;
	salesOrder_hasNext = 1;
	load_SalesOrder_Page();
}

function searchTime(){
	$('#salesOrderTable').html("");
	salesOrder_curretnPage=0;
	salesOrder_flag= 0;
	salesOrder_hasNext = 1;
	load_SalesOrder_Page();
}



function salesOrderDetailMsg(id){
	
	
}