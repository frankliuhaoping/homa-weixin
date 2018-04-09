$(function(){
	$(".sectionTwos div").click(function(){
        $(".sectionTwos div").css('backgroundColor','#ff7c44');
        $(".two_Tab").show();
        $(".sectionThrees div").css('backgroundColor','#9a9a9a');
        $(".two_Tab1").hide();
        
        loadOrderMsg("SignYear","SignMonth",-1);
    });

    $(".sectionThrees div").click(function(){
        $(".sectionThrees div").css('backgroundColor','#ff7c44');
        $(".two_Tab1").show();
        $(".sectionTwos div").css('backgroundColor','#9a9a9a');
        $(".two_Tab").hide();
        
        loadOrderMsg("OrderYear","OrderMonth",-1);
    });
    if($("#departMentFlag").length>0){
    loadDepartMsg("DepartYear","DepartMonth",-1);
    }else{
    	loadOrderMsg("OrderYear","OrderMonth",-1);
    	loadOrderMsg("SignYear","SignMonth",-1);
    }
});

/**
 * type = -1 查询 ,0 减 ,1 加
 * @param yearId
 * @param monthId
 * @param type
 */
function changeDate(yearId,monthId,type){
	var retDate = [];
	var year = $("#"+yearId+"").html();
	var month = $("#"+monthId+"").html();
	
	retDate[0] = year;
	retDate[1] = month;
	if(type==-1){
		return retDate;
	}
	if(type== 0){
		if(parseInt(month)-1 == 0){
			year = parseInt(year)-1;
			month = 12;
		}else{
			month = parseInt(month)-1;
		}
	}else{
		if(parseInt(month)+1 == 13){
			year = parseInt(year)+1;
			month = 1;
		}else{
			month = parseInt(month)+1;
		}
	}
	
	
	var date = new Date();
	console.log(date.getMonth())
	if(date.getFullYear()<year){
		return false;
	}else if(date.getFullYear()==year){
		if(date.getMonth()+1<month){
			return false;
		}
	}
	
	
	
	$("#"+monthId+"").html(month);
	$("#"+yearId+"").html(year);
	
	retDate[0] = year;
	retDate[1] = month;
	return retDate;
}

/**
 * 
 * 签到记录 和 订单统计
 * @param yearId
 * @param monthId
 * @param type
 */
function loadOrderMsg(yearId,monthId,type){
	var ret = changeDate(yearId,monthId,type);
	var flag = 0;
	if(ret == false){
		return ;
	}
	var employeeId = $("#employeeId_1").val();
	var node = "";
	var url =contextPath+"/weixin/workbench/queryCount/countOrderJson";
	if($(".two_Tab1").css("display")=="block"){
		node = $("#employeeMsgDiv");
	}else{
		url =contextPath+"/weixin/workbench/queryCount/countSignJson"
		node = $("#employeeSignDiv");
		flag = 1;
	}
	
	var submitData ={employeeId:employeeId,year:ret[0],month:ret[1]};
	
	$.showPreloader('数据加载中....');
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:url,
		success:function (data){
			$.hidePreloader();
		    var mdata = data.result;
		    
		    node.html("");
			for(var i =0;i<mdata.length;i++){
				console.log(mdata);
				var tt = "￥";
				if(flag == 1){
					tt="";
				}

				var html ='<article class="wrapss cc"><section class="sectionOne">'+
		           mdata[i].days+
		        '</section><section class="sectionTwo">'+
		           mdata[i].nums+
		        '</section><section class="sectionThree">'+tt+
		        	mdata[i].moneys+
		        '</section></article>';
				node.append(html);
				//var html = '<div class="pading_div cc" style="width:100%;" onclick="getEmployeeById('+"'"+mdata[i].id+"'" +')"><img src="'+mdata[i].head+'"/> <p class="p1">'+mdata[i].name+'</p></div>';
				//node.append(html);
			}
			
			if(flag != 1){
				if(mdata.length>0){
					orderCountPart(mdata[0].sumNums,mdata[0].sumMoneys,node);
				}else{
					orderCountPart(0,0,node);
				}
			}
		},
		error:function(){
			$.hidePreloader();
		}
	})
	
}

function orderCountPart(nums,moneys,node){
	var html ='<article class="wrapss cc"><section class="sectionOne">'+
    "合计"+
 '</section><section class="sectionTwo">'+
    nums+
 '</section><section class="sectionThree">'+"￥"+
 	moneys+
 '</section></article>';
	node.append(html);
}

function loadDepartMsg(yearId,monthId,type){
	var ret = changeDate(yearId,monthId,type);
	var flag = 0;
	if(ret == false){
		return ;
	}
	var node =$("#departmentDiv");
	var departmentId = $("#departmentId").val();
	var url = contextPath+"/weixin/workbench/queryCount/departListJson";
	var submitData ={eid:departmentId,year:ret[0],month:ret[1]};
	
	$.showPreloader('数据加载中....');
	$.ajax({
		data:submitData,
		type:"POST",
		dataType:"json",
		url:url,
		success:function (data){
			$.hidePreloader();
		    var mdata = data.result[0].listData;
		    console.log(data);
		    
		    node.html("");
			for(var i =0;i<mdata.length;i++){
				console.log(mdata);
				var tt = "￥";
				if(flag == 1){
					tt="";
				}

				var html ='<article class="wrapss cc"><section class="sectionOne">'+
		           mdata[i].DAY+
		        '</section><section class="sectionTwo">'+
		           mdata[i].NUMS+
		        '</section><section class="sectionThree">'+tt+
		        	mdata[i].SALESMONEY+
		        '</section></article>';
				node.append(html);
				//var html = '<div class="pading_div cc" style="width:100%;" onclick="getEmployeeById('+"'"+mdata[i].id+"'" +')"><img src="'+mdata[i].head+'"/> <p class="p1">'+mdata[i].name+'</p></div>';
				//node.append(html);
			}
			
			
			var html ='<article class="wrapss cc"><section class="sectionOne">'+
	           "合计"+
	        '</section><section class="sectionTwo">'+
	        	data.result[0].sumNums+
	        '</section><section class="sectionThree">'+"￥"+
	        	data.result[0].sumMoneys+
	        '</section></article>';
			node.append(html);
			
			
		},
		error:function(){
			$.hidePreloader();
		}
	})
}