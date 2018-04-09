var sound = document.getElementById("sound");
var aftersound = document.getElementById("aftersound");
var holdSound = document.getElementById("holdSound");
var holdsay = document.getElementById("holdsay");
var record = document.getElementById("record");
var shortRecord = document.getElementById("shortRecord");
var slideRecord = document.getElementById("slideRecord");
var over = document.getElementById("over");
var info = document.getElementById("info");
var et = document.getElementById("endtime");
var mysay = document.getElementById("mysay");
var intt = null;
var endt = null;
var t = null;
var sendFlag = false;
var touchStartY = null;
var timeLimitFlag = false;
var startVoiceFlag = false;
var stopVoiceType = 0;
var recordTime = 0;
var startTouch = false;
var shortClickId = null;
var isRecord = false;
var cycleTimes = 0;
var jsApiList = ['checkJsApi', 'onMenuShareTimeline', 'translateVoice', 'startRecord', 'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice', 'uploadVoice', 'downloadVoice', 'showMenuItems', 'hideMenuItems', 'showOptionMenu', 'hideAllNonBaseMenuItem'];
wx.config({
    debug: false, 
    appId: "wx2f4607777e92ace2", 
    timestamp: '${timestamp}', 
    nonceStr: '${nonceStr}',
    signature: '${signature}',
    jsApiList: jsApiList // 功能列表，我们要使用JS-SDK的什么功能
});

wx.ready(function() {
    wx.hideAllNonBaseMenuItem({
        success: function() {}
    });
    wx.showMenuItems({
        menuList: ['menuItem:share:timeline']
    });
    wx.hideMenuItems({
        menuList: ['menuItem:exposeArticl', 'menuItem:setFont', 'menuItem:refresh', 'menuItem:share:appMessage', 'menuItem:share:qq', 'menuItem:share:weiboApp', 'menuItem:favorite', 'menuItem:share:facebook', 'menuItem:copyUrl', 'menuItem:readMode', 'menuItem:openWithQQBrowser', 'menuItem:openWithSafari', 'menuItem:share:email']
    });
    wx.showOptionMenu();
    startRecord();
    stopRecordCycle();
    mysay.addEventListener("touchstart", nowTime, false);
    mysay.addEventListener("touchend", endTime, false);
    mysay.addEventListener("touchmove", slide, false);
    sound.addEventListener("touchstart", startVoice, false);
    wx.onVoiceRecordEnd({
        complete: function(a) {
            localId = a.localId
        }
    });
    wx.onVoicePlayEnd({
        complete: function(a) {
            startVoiceFlag = false;
            stopSay()
        }
    })
});

function say() {
    var a = document.getElementById("speak");
    a.src = "/static/wx/images/speechg.gif"
}

function stopSay() {
    var a = document.getElementById("speak");
    a.src = "/static/wx/images/speechh.png"
}

function nowTime(e) {
    isRecord = true;
    startTouch = true;
    cycleTimes = 0;
    e.preventDefault();
    sendFlag = false;
    timeLimitFlag = false;
    stopVoiceType = 0;
    touchStartY = e.touches[0].pageY;
    startRecord();
    mysay.style.background = "#0bbc0a";
    mysay.style.color = "#fff";
    mysay.innerHTML = "松开 结束";
    holdSound.style.display = "block";
    record.style.display = "block";
    intt = new Date;
    if (!t) {
        logTime()
    }
}

function logTime() {
    over.innerHTML = "10";
    t = setInterval(function() {
        var a = new Date;
        holdsay.style.visibility = "visible";
        console.log(a.getTime());
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

function endTime() {
    startTouch = false;
    mysay.innerHTML = "按住 说话";
    if (!sendFlag) {
        mysay.style.display = "block";
        mysay.style.background = "#fff";
        mysay.style.color = "#0bbc0a"
    }
    et.style.visibility = "hidden";
    slideRecord.style.display = "none";
    if (sendFlag == true) {
        return
    }
    clearInterval(t);
    t = null;
    stopRecord(0)
}

function cancelSend() {
    record.style.display = "none";
    slideRecord.style.display = "block"
}

function sendNode() {
    slideRecord.style.display = "none"
}

function stopRecord(d) {
	alert("zz")
    record.style.display = "none";
    wx.stopRecord({
        success: function(b) {
            isRecord = false;
            endt = new Date;
            var a = endt.getTime() - intt.getTime();
            recordTime = Math.ceil(a / 1000);
            var w = 55;
            var p = (115 / 60);
            var e = parseInt(a / 1000);
            var c = w + e * p;
            if (a < 1000) {
                shortTimeFunc()
            } else if (stopVoiceType != 1 || sendFlag) {
                et.style.visibility = "hidden";
                mysay.style.display = "none";
                sound.style.display = "block";
                holdSound.style.display = "none";
                slideRecord.style.display = "none";
                $("#time").text(getDate("time"));
                $("#time").css("visibility", "visible");
                aftersound.style.width = c + 'px';
                $("#aftersound span").text(recordTime + "′′");
                //localId = b.localId;
                shareFunc()
            } else {
                holdSound.style.display = "none"
            }
        },
        fail: function(a) {
            isRecord = false;
            shortTimeFunc()
        }
    })
}

function slide(e) {
    if (sendFlag) {
        return
    }
    e.preventDefault();
    var y = e.touches[0].pageY;
    if (touchStartY - y >= 50 && !sendFlag) {
        stopVoiceType = 1;
        et.style.display = "none";
        et.style.visibility = "hidden";
        record.style.display = "none";
        slideRecord.style.display = "block"
    } else {
        stopVoiceType = 0;
        slideRecord.style.display = "none";
        if (timeLimitFlag) {
            et.style.display = "block";
            et.style.visibility = "visible"
        } else {
            record.style.display = "block"
        }
    }
}

function startVoice() {
    var a = 1;
    if (startVoiceFlag) {
        wx.stopVoice({
            localId: a
        });
        startVoiceFlag = false;
        stopSay();
        return
    }
    say();
    startVoiceFlag = true;
    var a = localId;
    wx.playVoice({
        localId: a
    })
}

function startRecord() {
    wx.startRecord({
        cancel: function() {}
    })
}

function stopRecordCycle() {
    setInterval(function() {
        isRecord = (startTouch ? 0 : isRecord ? cycleTimes++ : 0) > 3 ? false : isRecord;
        if (!startTouch && !isRecord) {
            wx.stopRecord({
                success: function(a) {},
                fail: function(a) {}
            })
        }
    }, 1000)
}

function shortTimeFunc() {
    if (shortClickId != null) {
        return
    }
    shortRecord.style.display = "block";
    shortClickId = setTimeout(function() {
        shortRecord.style.display = "none";
        holdSound.style.display = "none";
        shortClickId = null
    }, 1000)
}
