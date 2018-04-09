var addProduct_keyWord="";
var addProduct_curretnPage=0;
var addProduct_flag = 0;
var addProduct_hasNext = 1;
$(function(){
	addProduct_keyWord="";
	addProduct_curretnPage=0;
	addProduct_flag = 0;
	addProduct_hasNext = 1;
	 
	$.initInfiniteScroll($(".infinite-scroll.addProductScroll"));
	$(".infinite-scroll.addProductScroll").on('infinite', function() {
		if($("#productul2").css("display")=="block"){
			seachProductsOnly();
		}
	});
});

function switchCustomerMsg(obj){
            var Left_div=$(obj).find(".in_radius1");

            if( Left_div.css("right")=='0px')
            {
            		
                $(Left_div).css("right","25px")/*({right:'25px'},300)*/;
                $(obj).css("background","#0894ec");
                $(".from_div").css("height","250px");//.animate({height:"250px"});
                //$(".from_div").animate({height:"250px"});
            }else
            {
            	$(Left_div).css("right","0px")//$(Left_div).animate({right:'0px'},300);
                $(obj).css("background","#0894ec");
                $(".from_div").css("height","0px");//$(".from_div").animate({height:"0px"});

            }
        }


        function switchRetMoney(obj){
            var Left_div=$(obj).find(".in_radius");

            if( Left_div.css("left")=='0px')
            {
                $(Left_div).css("left","25px")/*.animate({left:'25px'},300)*/;
                $(obj).css("background","#ccc");
                $(obj).parent().next().find("input").val(0);
                $(obj).parent().next().find("input").attr("disabled", true);
            }else
            {
            	$(Left_div).css("left","0px")/*Left_div.animate({left:'0px'},300);*/
                $(obj).css("background","#0894ec");
            	$(obj).parent().next().find("input").removeAttr("disabled");
            	//$("#retMoney").val("");
            }
        };
        
        
        
function checkSumProducts(){
	//alert($('input[name="productnumber"]').length);
	var sum = 0;
	var price = 0;
	$('input[name="productnumber"]').each(function(index,object){
		if(!isNaN($(object).val()) && $(object).val()!=""){
			var num = parseInt($(object).val())
			sum+=num;
			console.log($('input[name="productprice"]').get(index))
			var tempprice = $($('input[name="productprice"]').get(index)).val();
			if(!isNaN(tempprice) && tempprice!=""){
				price+=num*tempprice;
			}
		}
	});
	
	$("#sumofproduct").text(sum);
	price =  parseInt(price)<price.toFixed(2)?price.toFixed(2):parseInt(price);
	$("#sumofmoney").text(price);
}


function saveSalesOrder(){
	checkSumProducts();
	var productsId = [];
	var productsNum = [];
	var productsPrice = [];
	var retMoneys = [];
	var num = 0;
	var pnum = 0;
	//IDS
	console.log($('#orderProducts').children().length)
	$('#orderProducts').children().each(function(index,object){
		if($(object).attr("thisid")){
			productsId.push($(object).attr("thisid"));
			pnum ++ ; 
		}
	});
	if(pnum==0){
		$.toast("请选择产品",1000);
		return ;
	}
	console.log(productsId)
	num =0;
	//数量
	$('div input[name="productnumber"]').each(function(index,object){
		if(!isNaN($(object).val()) && $(object).val()!="" ){
			productsNum.push($(object).val());
			num++;
		}
	})
	if(pnum!=num){
		$.toast("请填写选购数量",1000);
		return ;
	}
	
	num=0;
	//单价
	$('div input[name="productprice"]').each(function(index,object){
		if(!isNaN($(object).val()) && $(object).val()!=""){
			productsPrice.push($(object).val());
			num++;
		}
	})
	if(pnum!=num){
		$.toast("请填写产品单价",1000);
		return ;
	}
	num=0;
	var flagr = 0;
	//返现金额
	$('div input[name="retMoney"]').each(function(index,object){
		var ret = 0;
		if($(object).val()==""){
			retMoneys.push(0);
			num++;
		}
		else if(!isNaN($(object).val())){
			retMoneys.push($(object).val());
			ret = $(object).val();
			num++;
		}
		
		
	})
	if(pnum!=num){
		$.toast("请填写产品返现金额",1000);
		return ;
	}
	
	//总价
	var productsMoney = $("#sumofmoney").text();
	//返现金额
	
	var retMoney = $("#retMoney").val();
	
	if(retMoney==""){
		retMoney = 0;
	}
	
	if(isNaN(retMoney)){
		$.toast("请填写正确的返现金额",1000);
		return ;
	} 
	for(var i =0;i<retMoneys.length;i++){
		if(retMoneys[i]>productsPrice[i]*productsNum[i]){
			$.toast("返现金额不能大于产品总价",1000);
			return ;
		}
	}
	var customerName = "";
	var customerAddr = "";
	var customerPhone = "";
	if($("#customerMsgBtn").css("right")=="25px"){
		customerName = $("#customerName").val();
		customerAddr = $("#customerAddr").val();
		customerPhone = $("#customerPhone").val();
		
		if(customerName=="" ){
			$.toast("请填写好客户姓名",1000);
			return ;
		}
		if(customerAddr=="" ){
			$.toast("请填写好客户地址",1000);
			return ;
		}
		
		var reg = /^0?1[3|4|5|8|7][0-9]\d{8}$/;
		var reg2 = /^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/;
		if (!reg.test(customerPhone) && !reg2.test(customerPhone)) {
			$.toast("请输入正确的顾客电话",1000);
			return ;
		}
		 
		if(customerPhone=="" ){
			$.toast("请填写好顾客电话",1000);
			return ;
		}
		
		
		
	}else{
	}
	var flag = 0;
	var salesOrderId= $("#salesOrderId").val();
	//用户不参与
	var url = contextPath+"/weixin/workbench/salesOrder/cardUpload?orderId=";
	//用户参与
	if($("#customerMsgBtn").css("right")=="0px"){
		url = contextPath+"/weixin/workbench/salesOrder/ewm?orderId=";
		flag+=1;
	}
	
	//修改订单
	if(salesOrderId!=""){
		flag +=1;
	}
	if(flag == 2){
		customerName = $("#customerName").val();
		customerAddr = $("#customerAddr").val();
		customerPhone = $("#customerPhone").val();
	}
	
	
	var submitData = {salesOrderId:salesOrderId,productsId:productsId.join(','),productsNum:productsNum.join(','),productsPrice:productsPrice.join(','),
			productsMoney:productsMoney,retMoney:retMoney,
			customerPhone:customerPhone,customerName:customerName,customerAddr:customerAddr,
			retMoneys:retMoneys.join(',')};
	console.log(submitData);
	
	
	//提示是否重复
	
	$.ajax({
		data:{name:customerName},
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/salesOrder/beforeSave",
		success:function (data){
			if(data.code!=200){
				if(confirm("今天内该用户已经提交过订单是否再次提交?")){
					//ajax 调用
					postOrder(submitData,flag,url);
				}else{
					return ;
				}
			}else{
				postOrder(submitData,flag,url);
			}
			
		},
		error:function(){
			$.toast("网络异常", 1000);
			$.hidePreloader();
		}
	})
	
	
	
	
	
}


function postOrder(submitData,flag,url){
	$.showPreloader('提交数据中....')
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/salesOrder/saveOrder",
		success:function (data){
			
			$("#customerName").val("");
			 $("#customerAddr").val("");
			$("#customerPhone").val("");
			$("#orderProducts").html("");
			$("#sumofmoney").text("0");
			$("#sumofproduct").text("0");
			
			
		    var mdata = data.result;
		    console.log(mdata);
		    $.hidePreloader();
		    if(flag == 2){
		    	$.toast("订单修改成功",1000);
		    	setTimeout(function(){
		    		window.location.href = contextPath+"/weixin/workbench/salesOrder/index";
		    	},700);
		    	return;
		    }
		    window.location.href = url+mdata;
		},
		error:function(){
			$.toast("网络异常", 1000);
			$.hidePreloader();
		}
	})
}

function showProducts(){
	$("#first_step").hide();
	$("#second_step").show();
	return false;
}

function findProduct(id,obj){
	console.log($(obj).next().css("display"))
	if($(obj).next().css("display")=="none"){
		$(obj).next().css("display","block");
		console.log($(obj).next().find('p').length == 0)
		if($(obj).next().find('p').length == 0){
			getProductJsonList(id,0,$(obj).next());
		}
		//$(obj).find("i").html("&#xe98c;");
	}else{
		$(obj).next().css("display","none");
		//$(obj).find("i").html("&#xe98e;");
	}
}


function getProductJsonList(parentId,len,appendNode){
	showMainProduct();
	var page = parseInt(len/10);
	//alert("currentPage :"+page)
	var submitData = {currentPage:page,parentId:parentId};
	
	
	$.showPreloader();
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/salesOrder/productListJson",
		success:function (data){
			$.hidePreloader();
		    var mdata = data.result;
		    if($(appendNode).find('p').length>0){
		    	$($(appendNode).find('p').get($(appendNode).find('p').length-1)).remove()
		    }
		    	//$(appendNode).find('p').get($(appendNode).find('p').length-1).remove();
			for(var i =0;i<mdata.length;i++){
				var html = '<p more="'+mdata[i].more+'" thisid="'+mdata[i].id+'" onclick="returnProduct(this)"><i class="iconfont_m">&#xe63f;</i> '+mdata[i].text+'</p>';
				$(appendNode).append(html);
			}
			if(mdata[i-1].more == 1){
				$(appendNode).append('<p onclick="getMoreProduct(this)" class="more"><i class="iconfont_m">&#xe646;</i> '+"点击更多"+'</p>');
			}
		}
	})
}


function getMoreProduct(obj){
	//alert($(obj).parent().prev().attr("thisid"));
	var parentid = $(obj).parent().prev().attr("thisid");
	var len = $(obj).parent().find('p').length -1;
	//alert("more len :"+len);
	var appendNode = $(obj).parent();
	getProductJsonList(parentid,len,appendNode);
	
}



function returnProduct(obj){
	var thisid = $(obj).attr("thisid");
	var text = $(obj).html();
	var flag = 0;
	$('#orderProducts').children('div').each(function(index,thisobj){
		if($(thisobj).attr("thisid") == thisid){
			$.toast("产品已被选过",1000);
			flag = 1;
		}
	});
	if(flag == 1)
		return ;

	var html = '';

	
	html = '		<div class="ul_one" thisid="'+thisid+'" style="position: relative;background: #fff; border: 1px solid #ccc; padding: 9px; border-radius: 5px;">'+
		'<span style="display: block;font-size:26px;line-height:20px;text-align: center;color:#ff0000;border-radius: 50%;position:absolute;right: -3px;top: -13px;" onclick="closeProductDiv(this)"><i class="icon_m iconfont_m">&#xe639;</i> </span>'+
       ' <div class="f18" >'+text+'</div>'+
         	
       ' <div class="proN_P">'+
      '      <div class="h_flexbox Num_Price"><label>数量:</label><div><input type="text" class="text1" value="1" name="productnumber" onchange="checkSumProducts()"/></div></div>'+
     '       <div class="h_flexbox Num_Price"><label>单价:</label><div><input type="text" class="text1" value="" name="productprice" onchange="checkSumProducts()"/></div></div>'+
            
    '    </div>'+
   '     <div class="Create_Order_number">'+
   '     <ul class="cc ul_two">'+
    '        <li>'+
     '           <div class="div_radius" style="background:#ccc; margin-top: 10px;" onclick="switchRetMoney(this)">'+
     '               <div class="in_radius" id="retMoneyBtn" style="left:25px"></div>'+
    '            </div>'+
    '            返现金额</li>'+
    '        <li  class="h_flexbox"><div><input type="text" placeholder="0" class="text1" id="retMoney" name="retMoney" disabled="disabled" value=""/></div></li>'+
   '     </ul>'+
  '  </div>'+
  '  <div style="clear: both;"></div>'+
  '  </div>';
	$('#orderProducts').prepend(html);
	
	//容器发生改变,如果是js滚动，需要刷新滚动
    $.refreshScroller();
    
	$("#first_step").show();
	$("#second_step").hide();
}
 

function seachProductsOnly(val){
	
	if(val==1){
		$("#insertNodeProducts").html("");
		addProduct_curretnPage=0;
		addProduct_flag = 0;
		addProduct_hasNext = 1;
	}
	
	var productSearchKeyWord = $("#productSearchKeyWord").val();
	var submitData = {keyWord:productSearchKeyWord,currentPage:addProduct_curretnPage};
	if(productSearchKeyWord==""){
		showMainProduct();
		return ;
	}else{
		showSubProduct();
	}
	
	if(addProduct_hasNext==0){
		return ;
	}
	if(addProduct_flag==0){
		addProduct_flag = 1;
	}else{
		return ;
	}
	
	//$.showPreloader();
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:contextPath+"/weixin/workbench/salesOrder/productListJsonOnly",
		success:function (data){
			//$.hidePreloader();
		    var mdata = data.result;
		    addProduct_flag = 0;
		    //$("#insertNodeProducts").html("");
		    addProduct_curretnPage++;
			for(var i =0;i<mdata.length;i++){
				
				//var html = '<p more="'+mdata[i].more+'" thisid="'+mdata[i].id+'" onclick="returnProduct(this)">'+mdata[i].text+'</p>';
				
				var html ='<div class="click_div" thisid="'+mdata[i].id+'" onclick="returnProduct(this)">'+mdata[i].text+'</div>';
				$("#insertNodeProducts").append(html);
				
				if(addProduct_hasNext=='0'){
			        $('.infinite-scroll-preloader.addProductScroll').remove();
			        return ;
				}
			}
			if(mdata.length==0){
				$('.infinite-scroll-preloader.addProductScroll').remove();
			}
		},error:function(){
			$.hidePreloader();
		}
	})
}

function showMainProduct(){
	$("#productul1").css("display","block");
	$("#productul2").css("display","none");
	$('.infinite-scroll-preloader.addProductScroll').css("display","none");
}

function showSubProduct(){
	$("#productul1").css("display","none");
	$("#productul2").css("display","block");
	$('.infinite-scroll-preloader.addProductScroll').css("display","block");
}

function closeProductDiv(obj){
	$(obj).parent().remove();
	//$(obj).parent().remove();
	checkSumProducts();
}
