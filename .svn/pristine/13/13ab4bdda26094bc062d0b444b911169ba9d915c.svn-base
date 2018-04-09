/**
 * ajax请求
 */
$.ajaxPost = function(param){
		$.ajax({
            type: 'POST',
    		url: param.url,
    		dataType:'json',
    		data: param.data,
            timeout: 20000,
    		success: function(data, textStatus, jqXHR) {
                if(data.code==401){
                    //$.initAppApiSettings.on401NoAuth(data);
                }else{
                    param.success(data, textStatus, jqXHR);
                }
    		},
            error: function(XMLHttpRequest, textStatus, errorThrown){
            	$.toast("网络异常", 500);
            },
            complete: function(XHR, TS){
                if(param.complete){
                    param.complete(XHR, TS);
                }
            }
	  });
}

$.contactTimeTranslate = function(time){
    if(time == "" || time == 0){
        return "";
    }
    return this.timeTranslate(time, false, true);
}

$.chatTimeTranslate = function(time){
    return $.timeTranslate(time, true, true);
}

$.timeTranslate = function(time, all, today){
    var send_time_date = new Date();
    send_time_date.setTime(time);

    var now = new Date();
    
    var year = send_time_date.getFullYear();     
    var month = send_time_date.getMonth()+1;     
    var day = send_time_date.getDate();     
    var hour = send_time_date.getHours();     
    var minute = send_time_date.getMinutes();
    var second = send_time_date.getSeconds();
         
    if(month<10){
        month = "0" + month;
    }
    if(day<10){
        day = "0" + day;
    }
    if(hour <10){
        hour = "0" + hour;
    }
    if(minute <10){
        minute = "0" + minute;
    }
    if(second <10){
        second = "0" + second;
    }
    
    var time_str = year+"-"+month+"-"+day;
    if(all){
        time_str = year+"-"+month+"-"+day+" "+hour+":"+minute;  
    }
    if(today){
       if(year == now.getFullYear()
            && month == now.getMonth()+1
            && day == now.getDate()){
            time_str = hour+":"+minute;    
        } 
    }
    return time_str;
}

$.uuid = function(){
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "";
    
    var uuid = s.join("");
    return uuid;
}

$.getQueryString = function(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}