/**
 * 
 */

$(function() {
			var date = new Date();
			var year = date.getFullYear()
			var month = date.getMonth() + 1;
			$(".date_year").html(year);
			$(".date_month").html(month);

			$(".date_plus").on(
					"click",
					function() {
						month++;
						if (month > 12) {
							month = 1;
							year++;
						}

						var strMonth = "";
						if (month < 10) {
							console.log("1")
							strMonth = "0" + month;
						} else {
							console.log("2")
							strMonth = "" + month
						}

						$(".date_month").html(strMonth);
						$(".date_year").html(year);

						$.ajax({
							url : contextPath + "/weixin/workbench/sign/list?month=" + year + "-" + strMonth,
							cache : false,
							dataType : "json",
							success : function(data) {
								console.log(data)
								var html = "";
								for ( var i in data.list) {
									var date = data.list[i].signTime.split(" ");
									var time = date[1];
									var datetime = date[0];
									var type = "";
									if (data.list[i].signType == 0) {
										type = "上班";
									} else if (data.list[i].signType == 1) {
										type = "下班";
									}

									html += '<article class="wrapss cc">' + '<section class="sectionOne">' + datetime + '</section>' + '<section class="sectionTwo">' + time + ' </section>'
											+ '<section class="sectionThree">' + type + ' </section></article>';
								}

								$("#contentData").html(html);

								if (data.list.length == 0) {
									$("#nullImg").html('<img src="' + contextPath + '/static/wxworkbench/img/zxyhhd-qd.jpg" width="100%">');
								}

							}
						});

					})

			$(".date_reduce").on(
					"click",
					function() {
						month--;
						if (month < 1) {
							month = 12;
							year--;
						}

						var strMonth = "";
						if (month < 10) {
							console.log("1")
							strMonth = "0" + month;
						} else {
							console.log("2")
							strMonth = "" + month
						}

						$(".date_month").html(month);
						$(".date_year").html(year);

						$.ajax({
							url : contextPath + "/weixin/workbench/sign/list?month=" + year + "-" + strMonth,
							cache : false,
							dataType : "json",
							success : function(data) {
								console.log(data)
								var html = "";
								for ( var i in data.list) {
									var date = data.list[i].signTime.split(" ");
									var time = date[1];
									var datetime = date[0];
									var type = "";
									if (data.list[i].signType == 0) {
										type = "上班";
									} else if (data.list[i].signType == 1) {
										type = "下班";
									}

									html += '<article class="wrapss cc">' + '<section class="sectionOne">' + datetime + '</section>' + '<section class="sectionTwo">' + time + ' </section>'
											+ '<section class="sectionThree">' + type + ' </section></article>';
								}

								$("#contentData").html(html);

								if (data.list.length == 0) {
									$("#nullImg").html('<img src="' + contextPath + '/static/wxworkbench/img/zxyhhd-qd.jpg" width="100%">');
								}
							}
						});

					})

		})
