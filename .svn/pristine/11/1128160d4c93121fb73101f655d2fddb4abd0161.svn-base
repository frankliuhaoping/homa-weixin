;(function($){
    //document.addEventListener("touchmove",function(e){e.preventDefault();})
    var win_w = $(window).width();
    var win_h = $(window).height();
    var win_w_half = win_w/2;
    
    var Btn_flag = true,X1=win_w,Y1="";
    
    var phoneBtn = {
        
        init:function(obj,option){
            var Btn_Max_Left = win_w - parseInt(option.Btn_width);
            var Btn_Max_Top = win_h - parseInt(option.Btn_height);
            var Btn_center = {x:win_w_half-parseInt(option.Btn_width)/2,y:win_w_half-parseInt(option.Btn_height)/2};
            var cBtnLen = $(option.cBtnName).length;
            var left_arr = [],top_arr = [];
            for(var i=0;i<cBtnLen;i++){
                top_arr.push(Btn_center.y+option._R*(Math.sin((Math.PI*2/cBtnLen)*i)));
                left_arr.push(Btn_center.x+option._R*(Math.cos((Math.PI*2/cBtnLen)*i)));
            }

            phoneBtn.EvenClick(obj,option,left_arr,top_arr,Btn_Max_Left,Btn_Max_Top,Btn_center);
            phoneBtn.EvenDragStart(obj,option,Btn_Max_Left,Btn_Max_Top);
            phoneBtn.EvenDragEnd(obj,option);
        },
        
        EvenDragStart:function(obj,option,Btn_Max_Left,Btn_Max_Top){
            obj.on("touchstart",function(){
            	$(document).on("touchmove",function(e){e.preventDefault();})
                phoneBtn.EvenDragMove(obj,option,Btn_Max_Left,Btn_Max_Top);
            })
        },
        
        EvenDragMove:function(obj,option,Btn_Max_Left,Btn_Max_Top){
            obj.on("touchmove",function(e){
                X1 = e.touches[0].clientX - obj.width()/2;
                Y1 = e.touches[0].clientY - obj.height()/2;
                X1<0?X1=0:X1;
                X1>Btn_Max_Left?X1 = Btn_Max_Left:X1;
                Y1<0?Y1=0:Y1;
                Y1>Btn_Max_Top?Y1=Btn_Max_Top:Y1;
                
                if(X1 > win_w_half){
                    obj[0].style.left = "";
                    obj[0].style.right = (win_w-X1-obj.width()) + 'px';
                }else{
                    obj[0].style.right = "";
                    obj[0].style.left =X1 + 'px';
                }
                obj[0].style.top = Y1 + 'px';
            })
        },
        
        EvenDragEnd:function(obj,option){
            obj.on("touchend",function(){
               obj.off("touchmove");
               $(document).off("touchmove")
               if(X1 > win_w_half){
                   $(this)[0].style.left = "";
                   $(this).animate({right:"0"},option.timer)
               }else{
                   $(this)[0].style.right = "";
                   $(this).animate({left:"0"},option.timer)
               }
                
                
            })
        },
        
        EvenClick:function(obj,option,left_arr,top_arr,Btn_Max_Left,Btn_Max_Top,Btn_center){
            
                obj.on("click",function(e){
                    e.preventDefault();
                    if(Btn_flag){
                        $(this).off("touchstart");
                        $(option.layer_Bg).show();
                        $(this).find(".fixBtn_one").hide();
                        $(this).animate({width:win_w,height:win_w,top:option.Btn_top},option.timer);
                        
                        setTimeout(function(){
                            $(option.cBtnName).css({"left":Btn_center.x,"top":Btn_center.y,"display":"block"});
                            setTimeout(function(){
                            $(option.cBtnName).each(function(i){
                                $(this).animate({left:left_arr[i],top:top_arr[i]},option.timer);
                            })
                            },20)
                        },option.timer)
                        Btn_flag = !Btn_flag;
                        return;
                    }
                    
                });
                $(option.layer_Bg).on("click",function(){

                        $(this).hide();
                        $(option.cBtnName).animate({left:Btn_center.x,top:Btn_center.y},option.timer);
                        setTimeout(function(){
                            $(option.cBtnName).hide();
                            obj.animate({width:option.Btn_width,height:option.Btn_height,top:"50%"},option.timer);
                            setTimeout(function(){obj.find(".fixBtn_one").show();},option.timer)
                                             },option.timer);
                        phoneBtn.EvenDragStart(obj,option,Btn_Max_Left,Btn_Max_Top);
                        Btn_flag = !Btn_flag;
                            
                });
                                    
        }
        
        
    } 
    
    
    $.fn.phoneMoveBtn = function(options){
        var defaults = {
            Btn_width:"48px",
            Btn_height:"48px",
            Btn_top:"80px",
            timer:"500",
            _R:"80", 
            cBtnName:".fixBtn_c",
            cBtn_width:"48px",
            cBtn_height:"48px",
            layer_Bg:"#layer_Bg"
        };
        var option = $.extend(defaults,options);
        
        phoneBtn.init(this,option);
    }
        
    
        
})(Zepto);
