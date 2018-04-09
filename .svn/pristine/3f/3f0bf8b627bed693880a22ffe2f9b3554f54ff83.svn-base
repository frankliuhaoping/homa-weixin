/**
 * 
 */

$(function() {
	    var Width=parseFloat($(".progress-bar").css("width"));
    var Width1=parseFloat($(".progress-bar .anim_div").css("width"));
    var chu=Width1/Width*100;
  $(".progress-bar .anim_div span").html(Math.round(chu)+"%");
  
	var date = new Date();
	var year = date.getFullYear()
	$(".date_year").text(year + "年");
	$("#ahref").attr("href",contextPath+"/weixin/workbench/saleTask/saleTaskaChart?year="+year);	

	
	
	$("#jia").on("click",function() {
	         	year++;
	        	$(".date_year").text(year + "年");
	        	$("#ahref").attr("href",contextPath+"/weixin/workbench/saleTask/saleTaskaChart?year="+year);	
	      	  $.post(contextPath + "/weixin/workbench/saleTask/jsonList?year="+year,
	   			   function(data){
	      		console.log(data); 
	   		       //alert(data.month1); // John
	      		 if(data.type!=null){
	   		     var html="";
	   		      for (var int = 1; int <= 12; int++) {
	   			     console.log(data); //  2pm
	   			   var ratio = (data["month"+int])*100;
				     var ratioStr = ratio.toString();
				     ratioStr = ratioStr.substring(0,4);
				     var unit="";
				     if(data.type==0){
				    	 unit="(元)";
				     }else if(data.type==1){
				    	 unit="(台)";
				     }
				     
	                  html+='<div class="accomplish_data cc">'
	               	   +' <span class="span1">'+int+'月'+'</span>'
	   			       + '<span class="span2">月度任务<s>'+data["totalmonth"+int]+unit+'</s></span></div>'
	   			       +'<div class="schedule">'
	   			       +'<div class="progress-bar orange_one shine">'
	   			       +'<div class="anim_div" style="width:'+ratio+'%;">'
	   			       +'<span>'+ratioStr+'%</span>'+data["taskmonth"+int]+'</div></div></div>';
	   		  }
	   		      $("#taskContent").html(html);
	      		 }else{
	      			$("#taskContent").html('<img src="'+contextPath+'/static/wxworkbench/img/zxyhhd-no.jpg" width="100%">');
		   		   }
	   		      }, "json");
				
	});
	
	$("#jian").on("click",function() {
     	year--;
    	$(".date_year").text(year  + "年");
    	$("#ahref").attr("href",contextPath+"/weixin/workbench/saleTask/saleTaskaChart?year="+year);	
    	  $.post(contextPath + "/weixin/workbench/saleTask/jsonList?year="+year,
	   			   function(data){
	   		       //alert(data.month1); // John
    		  console.log(data); 
	   		   if(data.type!=null){
	   		     var html="";
	   		      for (var int = 1; int <= 12; int++) {
	   			     console.log(data); //  2pm
				     var ratio = (data["month"+int])*100;
				     var ratioStr = ratio.toString();
				     ratioStr = ratioStr.substring(0,4);
				     var unit="";
				     if(data.type==0){
				    	 unit="(元)";
				     }else if(data.type==1){
				    	 unit="(台)";
				     }
	                  html+='<div class="accomplish_data cc">'
	               	   +' <span class="span1">'+int+'月'+'</span>'
	   			       + '<span class="span2">月度任务<s>'+data["totalmonth"+int]+unit+'</s></span></div>'
	   			       +'<div class="schedule">'
	   			       +'<div class="progress-bar orange_one shine">'
	   			       +'<div class="anim_div" style="width:'+ratio+'%;">'
	   			       +'<span>'+ratioStr+'%</span>'+data["taskmonth"+int]+'</div></div></div>';
	   		  }
	   		      $("#taskContent").html(html);
	   		      
	   		   }else{
	   			$("#taskContent").html('<img src="'+contextPath+'/static/wxworkbench/img/zxyhhd-no.jpg" width="100%">');
	   		   }
	   		      }, "json");
		
});
	
	  $.post(contextPath + "/weixin/workbench/saleTask/jsonList",
			   function(data){

		  if(data.type!=null){
		     var html="";
		      for (var int = 1; int <= 12; int++) {
			     console.log(data); //  2pm
			     var ratio = (data["month"+int])*100;
			     var ratioStr = ratio.toString();
			     ratioStr = ratioStr.substring(0,4);
			     var unit="";
			     if(data.type==0){
			    	 unit="(元)";
			     }else if(data.type==1){
			    	 unit="(台)";
			     }
                 html+='<div class="accomplish_data cc">'
            	   +' <span class="span1">'+int+'月'+'</span>'
			       + '<span class="span2">月度任务<s>'+data["totalmonth"+int]+unit+'</s></span></div>'
			       +'<div class="schedule">'
			       +'<div class="progress-bar orange_one shine">'
			       +'<span >'+ratioStr+'%</span>'
			       +'<div class="anim_div" style="width:'+ratio+'%;">'
			       +data["taskmonth"+int]+'</div></div></div>';
		  }
		      $("#taskContent").html(html);
		      //$(".anim_div").css("width",'50%');
		  }else{
	   			$("#taskContent").html('<img src="'+contextPath+'/static/wxworkbench/img/zxyhhd-no.jpg" width="100%">');
  		   }
		      }, "json");
})