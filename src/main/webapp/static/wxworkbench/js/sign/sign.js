/**
 * 
 */

$(function() {
	$.getJSON(contextPath + '/weixin/getJsapiTicket', {
		url : weiXinConfigUrl
	}, function(data) {
		wx.config({
			debug : weiXinConfigDebug,
			appId : data.appid,
			timestamp : data.timestamp,
			nonceStr : data.noncestr,
			signature : data.signature,
			jsApiList : [ 'getLocation','chooseImage','uploadImage' ]
		});
	});
	wx.ready(function() {
		$.showPreloader('正在校准地址，请稍候...');
		wx.getLocation({
			type : 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			success : function(res) {
				$.hidePreloader();
			},
			fail : function() {
				$.hidePreloader();
				$.alert('无法获取地理位置');
			}
		});

		$("#singBtn").on("click", function() {
			$("#singBtn").removeClass('buttones').attr("disabled", "disabled");
			$.showIndicator();
			wx.getLocation({
				type : 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
				success : function(res) {

					wx.getLocation({
						type : 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
						success : function(res) {
							var latitude = res.latitude.toString(); // 纬度，浮点数，范围为90
																	// ~
							// -90
							// alert(latitude);
							var longitude = res.longitude; // 经度，浮点数，范围为180 ~
															// -180。
							var speed = res.speed; // 速度，以米/每秒计
							var accuracy = res.accuracy; // 位置精度

							$.ajax({
								url : contextPath + "/baidu/api/reverseGeocode/" + longitude + "/" + latitude + "/",
								dataType : "json",
								cache : false,
								success : function(data) {

									$.confirm('您当前的地址是"' + data.result.formatted_address + '"吗？', '询问地址', function() {
										// $.alert('是的');
										var serverId=[];
										wx.chooseImage({
										    count: 1, // 默认9
										    sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
										    sourceType: ['camera'], // 可以指定来源是相册还是相机，默认二者都有
										    success: function (res) {
										        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
										        wx.uploadImage({
										            localId: ''+localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
										            isShowProgressTips: 1, // 默认为1，显示进度提示
										            success: function (res) {
										               serverId = res.serverId; // 返回图片的服务器端ID
										              // alter('serverid');
										               $.ajax({
															type : "post",
															url : contextPath + "/weixin/workbench/sign/signStartWork",
															data : {
																address : data.result.formatted_address,
																serverid: serverId
															},
															cache : false,
															dataType : "json",
															success : function(data) {
																console.log(data)
																if (data.yes == true) {
																	$.toast("上班签到成功！");
																	$("#singBtn").text("已签到");
																	$("#weiqiandao").text("");
																	$("#i1").after(data.date);
																}
															}
														});
										            }
										        });
										    }
										});										
										$.hideIndicator();
									}, function() {
										$.hideIndicator();
										// 取消
									});

									console.log(data)
								}
							});

						},
						fail : function() {
							$("#singBtn").removeClass('buttones').attr("disabled", "enabled");
							$.hideIndicator();
							$.alert('无法获取地理位置');
						}
					});

				},
				fail : function() {
					$("#singBtn").removeClass('buttones').attr("disabled", "enabled");
					$.hideIndicator();
					$.alert('无法获取地理位置');
				}
			});

		})

		$("#signOffBtn").on("click", function() {
			$("#signOffBtn").removeClass('buttones').attr("disabled", "disabled");
			wx.getLocation({
				type : 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
				success : function(res) {
					wx.getLocation({
						type : 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
						success : function(res) {
							var latitude = res.latitude; // 纬度，浮点数，范围为90 ~
															// -90
							var longitude = res.longitude; // 经度，浮点数，范围为180 ~
															// -180。
							var speed = res.speed; // 速度，以米/每秒计
							var accuracy = res.accuracy; // 位置精度

							$.ajax({
								url : contextPath + "/baidu/api/reverseGeocode/" + longitude + "/" + latitude + "/",
								dataType : "json",
								cache : false,
								success : function(data) {

									$.confirm('您当前的地址是"' + data.result.formatted_address + '"吗？', '询问地址', function() {
										// $.alert('是的');
										wx.uploadImage({
								            localId: ''+localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
								            isShowProgressTips: 1, // 默认为1，显示进度提示
								            success: function (res) {
								               serverId = res.serverId; // 返回图片的服务器端ID
								              // alter('serverid');
								               $.ajax({
													type : "post",
													url : contextPath + "/weixin/workbench/sign/signOffWork",
													data : {
														address : data.result.formatted_address,
														serverid: serverId
													},
													cache : false,
													dataType : "json",
													success : function(data) {
														// console.log(data)
														if (data.yes == true) {
															$.toast("下班签退成功！");
														
															$("#signOffBtn").text("已签到");
															$("#weiqiandao2").text("");
															$("#i2").after(data.date)
															// $("#i1").after(data.date);
														}
													}
												});
								            }
								        });
										
										$.hideIndicator();
									}, function() {
										$.hideIndicator();
										// 取消

									});

								}
							});

						},
						fail : function() {
							$("#signOffBtn").removeClass('buttones').attr("disabled", "enabled");
							$.hideIndicator();
							$.alert('无法获取地理位置');
						}

					});
				},
				fail : function() {
					$("#signOffBtn").removeClass('buttones').attr("disabled", "enabled");
					$.hidePreloader();
					$.alert('无法获取地理位置');
				}
			});

		})
	});

});
