

var addProduct_keyWord="";
var addProduct_curretnPage=0;
var addProduct_flag = 0;
var addProduct_hasNext = 1;

$(document).on("pageInit", "#addProduct-page", function(e, pageId, $page) {
	document.title="选择产品";
	 addProduct_keyWord="";
	 addProduct_curretnPage=0;
	 addProduct_flag = 0;
	 addProduct_hasNext = 1;
});



function findProduct(id,obj){
	console.log($(obj).next().css("display"))
	if($(obj).next().css("display")=="none"){
		$(obj).next().css("display","block");
		console.log($(obj).next().find('p').length == 0)
		if($(obj).next().find('p').length == 0){
			getProductJsonList(id,0,$(obj).next());
		}
		//$(obj).find("i").html("&#xe63f;");
	}else{
		$(obj).next().css("display","none");
		//$(obj).find("i").html("&#xe63f;");
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
	$.router.back();
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
$(document).on('infinite', '.infinite-scroll.addProductScroll',function() {
	console.log($("#productul2").css("display")=="block");
	if($("#productul2").css("display")=="block"){
		seachProductsOnly();
	}
});