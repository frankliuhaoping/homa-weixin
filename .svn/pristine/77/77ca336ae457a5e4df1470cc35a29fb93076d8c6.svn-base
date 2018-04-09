// function wxInit(getInitInfoPath, jsApilist) {
//     var rqurl = "http://schoolmedia.xtuers.com/wx/JSConfigServlet" + "?pageurl=" + replaceAll(window.location.href, "&", "|") + "&data=" + new Date().getMilliseconds();
//     rqurl = encodeURI(encodeURI(rqurl));
//     $.get(rqurl, function(res) {
//         var json = JSON.parse(res);
//         alert(res);
//         wx.config({
//             debug: true,
//             appId: json.appId,
//             timestamp: json.timestamp,
//             nonceStr: json.noncestr,
//             signature: json.signature,
//             jsApiList: jsApilist
//         })
//     })
// };

function replaceAll(str, oldChar, newChar) {
    var oldStr = str;
    var newStr = oldStr;
    do {
        oldStr = newStr;
        newStr = newStr.replace(oldChar, newChar)
    } while (oldStr != newStr);
    return newStr
};

function getQueryString(url, name) {
    var reg = new RegExp(name + "=(:*[\\-_0-9A-Za-z]*)*(:/)*(/[0-9A-Za-z]*)*", "gi");
    var res = url.substring(1).match(reg) + "";
    res = res.substring(res.indexOf("=") + 1, res.length);
    return res
};

function controlSize(str) {
    str = str + "";
    var rge = new RegExp("[0-9]{2}", "gi");
    if (str.match(rge) == null) {
        return "0" + str
    };
    return str
};

function getDate(type) {
    var dateObj = new Date();
    var date = null;
    if (type == "time") {
        var hour = controlSize(dateObj.getHours());
        var min = controlSize(dateObj.getMinutes());
        date = hour + ":" + min
    };
    if (type == "url") {
        var month = dateObj.getMonth();
        var day = dateObj.getDate();
        var week = dateObj.getDay();
        var mill = dateObj.getTime();
        var hour = dateObj.getHours();
        var min = dateObj.getMinutes();
        date = month + ":" + day + ":" + hour + ":" + min + ":" + week + ":" + mill
    };
    return date
}