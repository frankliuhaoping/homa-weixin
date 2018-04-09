/**
 * 
 */


	


$(function(){
	
	$.post(contextPath + "/weixin/workbench/saleTask/jsonList",
			   function(data){
			     //alert(data.month1); // John
			     console.log(data); //  2pm
			     
			     $("#year88").text(data.taskTotal);
			     $("#fini").text(data.finishTotal);
			     if(data.rate>0){
			    	 
			    	  draw(data.rate*100);
			     }else{
			    	 draw(0);
			     }
			     
			    //百分比
			     
			   }, "json");
	
	
	
	    var progress = document.getElementById('progress');
	    var cxt = progress.getContext('2d');

	    var progressDiv = document.getElementsByClassName('progress')[0];
	    progress.height = progressDiv.offsetHeight;
	    progress.width = progressDiv.offsetWidth;

	    var arcCenter_w = progressDiv.offsetWidth/2;
	    var arcCenter_h = progressDiv.offsetHeight/2;
	    var arc_R = arcCenter_h-25;
	    var arc_R1 = arcCenter_h-55;
	    var arc_R2 = arcCenter_h-60;

	    //没数组，用了浅拷贝
	    function extendCopy(p,c) {
	      var f = {};
	      for (var i in p) { f[i] = p[i];}
	      for (var i in c) { f[i] = c[i];}
	　　　return f
	　　};

	    var drawRing = function(opt){
	            var  options = opt || {};
	            /*X点，Y点，半径，起始点，结束点，是否逆时针,moveToX,moveToY,边框宽度，填充颜色，边框填充颜色 */
	            var optiondefault = {
	                x:arcCenter_w,
	                y:arcCenter_h,
	                r:arc_R,
	                startClient:0,
	                endClient:Math.PI*2,
	                ifCCw:false,
	                mX:"",
	                mY:"",
	                lineWidth:0,
	                fs:'red',
	                ss:'#fff'
	            }

	           var option = extendCopy(optiondefault,options);
	           this.drawArc = function(progress){
	                    cxt.beginPath();
	                    if(option.mX!="" && option.mY!=""){
	                        cxt.moveTo(option.mX, option.mY);
	                    }
	                    cxt.arc(option.x,option.y, option.r, option.startClient, option.endClient, option.ifCCw);
	                    cxt.lineWidth = option.lineWidth;
	                    cxt.closePath();
	                    cxt.fillStyle = option.fs;
	                    cxt.strokeStyle = option.ss;
	                    cxt.stroke();
	                    cxt.fill();}
	           this.drawfont = function(progress){
	                    cxt.font = "bold 1.5rem Arial";
	                    cxt.fillStyle = '#e74c3c';
	                    cxt.textAlign = 'center';
	                    cxt.textBaseline = 'middle';
	                    cxt.moveTo(arcCenter_w, arcCenter_h);
	                    cxt.fillText(progress+'%', arcCenter_w, arcCenter_h);
	        }
	            this.clearArc = function () {
	                        cxt.clearRect(0,0,progressDiv.offsetWidth,progressDiv.offsetHeight);
	            }
	    }



	    var grd=cxt.createLinearGradient(arcCenter_w,arcCenter_h-arc_R,arcCenter_w,arcCenter_h+arc_R);
	    grd.addColorStop(0,"#FF7300");
	    grd.addColorStop(0.5,"#FFFF01");
	    grd.addColorStop(1,"#FF7300");

	    var drawring1 = new drawRing({fs:"#ccc"})
	    var drawring3 = new drawRing({mX:arcCenter_w,mY:arcCenter_h,r:arc_R1,fs:"#fff"})
	    var drawring4 = new drawRing({mX:arcCenter_w,mY:arcCenter_h,r:arc_R2,fs:"#fff",ss:"#ccc",lineWidth:2})
	    var draw2 = function(i){
	        return new drawRing({mX:arcCenter_w,mY:arcCenter_h,startClient:-Math.PI/2,endClient:Math.PI*i/50-Math.PI/2,fs:grd});
	    }
	    var i = 0;
	    function draw(progress){
            drawring1.clearArc();
            drawring1.drawArc();
            draw2(i).drawArc(progress);
            drawring3.drawArc();
            drawring4.drawArc();
            drawring4.drawfont(i.toFixed(2));
            if(i >progress - 0.01){return;}
            	(Math.floor(progress) <= i)?i=parseFloat(i+0.01):i++;
            setTimeout(function(){draw(progress); },10);

    }
});
