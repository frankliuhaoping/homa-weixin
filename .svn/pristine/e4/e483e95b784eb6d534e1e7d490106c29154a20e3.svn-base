var isRecord = false;
var startTouch = false;
var cycleTimes = 0;
var sendFlag = false;
var touchStartY = null;
var intt = null;
var endt = null;
var t = null;
$(".voice_btn").on("touchstart",function(e){
	isRecord = true;
	startTouch = true;
    cycleTimes = 0;
    e.preventDefault();
    sendFlag = false;
    touchStartY = e.touches[0].pageY;
    startRecord();
    $(this).text("松开 结束");
    intt = new Date;
    if (!t) {
        logTime()
    }
})

function logTime() {
    over.innerHTML = "10";
    t = setInterval(function() {
        var a = new Date;
        if (a > (intt.getTime() + 49000)) {
            record.style.display = "none";
            if (slideRecord.style.display != "block") {
                et.style.display = "block";
                et.style.visibility = "visible"
            }
            timeLimitFlag = true;
            if (over.innerHTML != "0") {
                over.innerHTML -= 1
            }
            if (over.innerHTML == 0) {
                over.innerHTML = "!";
                info.innerHTML = "说话时间超长";
                setTimeout(function() {
                    et.style.display = "none";
                    record.style.display = "none";
                    sendFlag = true;
                    clearInterval(t);
                    t = null;
                    stopRecord(0)
                }, 1000)
            }
        }
    }, 1000)
}


$(".voice_btn").on("touchend",function(e){
    
    
})

$(".voice_btn").on("touchmove",function(e){
    
})