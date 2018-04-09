var query_curretnPage = 0;
var query_flag = 0;
var query_hasNext = 1;



var query_curretnPageEmployee = 0;
var query_flagEmployee = 0;
var query_hasNextEmployee = 1;

$(function() {
	query_curretnPage = 1;
	query_flag = 0;
	query_hasNext = 1;
	load_query_Page();
	
	
	 query_curretnPageEmployee = 0;
	 query_flagEmployee = 0;
	 query_hasNextEmployee = 1;

	
	//注册部门列表 滚动事件
	$.initInfiniteScroll($(".infinite-scroll.queryScroll"));
	$(".infinite-scroll.queryScroll").on('infinite', function() {
		if($("#querycounttable").css("display")=="block")
		load_query_Page();
	});
	
	//注册员工列表 滚动事件
	$.initInfiniteScroll($(".infinite-scroll.queryScroll1"));
	$(".infinite-scroll.queryScroll1").on('infinite', function() {
		//load_query_Page();
		if($("#querycounttable1").css("display") == "block")
		searchEmployee();
	});
});

function getEmployees(oid, obj) {
	if ($(obj).next(".li_con").css("display") == 'none') {
		$(obj).addClass("hovers")
		$(obj).next(".li_con").css("display", 'block');
	} else {
		$(obj).removeClass("hovers")
		$(obj).next(".li_con").css("display", 'none');
	}
	$(obj).addClass("hover").siblings('li').removeClass("hover");
	var node = $(obj).next();
	var len = node.children().length;
	var get = false;
	get = $(obj).next(".li_con").css("display") == 'block';
	if (!get) {
		console.log("len :" + len);
		return;
	}
	if (len != 0) {
		return;
	}

	var oid = oid;
	var submitData = {
		oid : oid
	};

	$.ajax({
		data : submitData,
		type : "POST",
		dataType : "json",
		url : contextPath + "/weixin/workbench/queryCount/employeesListJson",
		success : function(data) {
			var mdata = data.result;
			for (var i = 0; i < mdata.length; i++) {
				if (mdata[i].head == '') {
					mdata[i].head = contextPath + "/static/wxworkbench/img/head.jpg";
				}
				var html = '<div class="pading_div cc" style="width:100%;" onclick="getEmployeeById(' + "'" + mdata[i].id + "'" + ')"><img src="' + "" + mdata[i].head + '"/> <p class="p1">'
						+ mdata[i].name + '</p></div>';
				$(node).append(html);
			}
		}
	})
}

function getEmployeeById(eid) {
	var url = contextPath + "/weixin/workbench/queryCount/employeeMsg?eid=" + eid;
	window.location.href = url;
}

function getDepartMent(oid) {
	var url = contextPath + "/weixin/workbench/queryCount/departmentMsg?eid=" + oid;
	window.location.href = url;
}


//部门查询 按钮
function searchDepartBtn(){
	//清空 信息
	$('#querycounttable').html("");
	//初始化 数据
	query_curretnPage = 1;
	query_flag = 0;
	query_hasNext = 1;
	$('.infinite-scroll-preloader.queryScroll').css("display","block");
	load_query_Page();
}


function load_query_Page() {
	if (query_flag == 0) {
		query_flag = 1;
	} else {
		return;
	}
	if (query_hasNext == '0') {
		$('.infinite-scroll-preloader.queryScroll').css("display","none");
		return;
	}
	
	//按部门查询
	var searchDepart = $("#searchDepart").val();
	if(!searchDepart){
		searchDepart="";
	}

	/*
	 * $.showPreloader(); $.hideIndicator(); $.showPreloader('提交数据中....')
	 */
	var submitData = {
		page : query_curretnPage,
		rows : 9,
		"search.organizationName_like":searchDepart
	};
	// alert(query_curretnPage)
	console.log(submitData)
	$.ajax({
		data : submitData,
		type : "POST",
		dataType : "json",
		url : contextPath + "/weixin/workbench/queryCount/organizationListJson",
		success : function(data) {
			// $.hidePreloader();
			console.log(data.result)
			if (data.result.length == 0) {
				$('.infinite-scroll-preloader.queryScroll').css("display","none");
			}
			query_curretnPage++;
			query_flag = 0;
			appendquery(data.result);
			if (query_hasNext == '0') {
				$('.infinite-scroll-preloader.queryScroll').css("display","none");
				return;
			}
			/* $.refreshScroller(); */
		},
		error : function() {
			$.toast("网络异常", 500);
			/* $.hidePreloader(); */
		}
	})
}

function appendquery(data) {
	for (var i = 0; i < data.length; i++) {
		var html = '<li class="cc" onclick="getDepartMent(' + "'" + data[i].id + "'" + ')">' + data[i].name + '</li><div class="li_con cc"></div>';
		if ($("#queryCountIndex-page").length != 0) {
			html = '<li class="cc" onclick="getEmployees(' + "'" + data[i].id + "'" + ',this)"> ' + data[i].name + '</li><div class="li_con cc"></div>';
		}
		$('#querycounttable').append(html);
		query_hasNext = data[i].more;
	}
}





//员工查询 按钮
function searchEmployeeBtn(){
	var searchEmployee = $("#searchEmployee").val();
	if(!searchEmployee){
		searchEmployee="";
	}
	if(searchEmployee!=""){
		$(".querycounttable").css("display","none");
		$(".querycounttable1").css("display","block");
		
		$("#querycounttable").css("display","none");
		$("#querycounttable1").css("display","block");
		$("#querycounttable1").html("");
	}
	else{
		$(".querycounttable").css("display","block");
		$(".querycounttable1").css("display","none");
		
		
		$("#querycounttable").css("display","block");
		$("#querycounttable1").css("display","none");
		
		$("#querycounttable").html("");
		
		 query_curretnPage = 0;
		 query_flag = 0;
		 query_hasNext = 1;
		load_query_Page();
		return ;
	}
	
	//清空 信息
	$('#querycounttable').html("");
	//初始化 数据
	query_curretnPageEmployee = 1;
	query_flagEmployee = 0;
	query_hasNextEmployee = 1;
	//$('.infinite-scroll-preloader.queryScroll').css("display","block");
	searchEmployeess();
}

//按员工查询

function searchEmployeess(){
	if (query_flagEmployee == 0) {
		query_flagEmployee = 1;
	} else {
		return;
	}
	if (query_hasNextEmployee == '0') {
		$('.infinite-scroll-preloader.queryScroll').css("display","none");
		return;
	}
	
	//按员工姓名查询
	var searchEmployee = $("#searchEmployee").val();
	if(!searchEmployee){
		searchEmployee="";
	}

	/*
	 * $.showPreloader(); $.hideIndicator(); $.showPreloader('提交数据中....')
	 */
	var submitData = {
		page : query_curretnPageEmployee,
		rows : 9,
		"search.name_like": searchEmployee
	};
	// alert(query_curretnPage)
	console.log(submitData)
	$.ajax({
		data : submitData,
		type : "POST",
		dataType : "json",
		url : contextPath + "/weixin/workbench/queryCount/employeesListJson",
		success : function(data) {
			// $.hidePreloader();
			console.log(data.result)
			if (data.result.length == 0 || data.result.length<9) {
				$('.infinite-scroll-preloader.queryScroll1.querycounttable1').css("display","none");
			}
			query_curretnPage++;
			query_flagEmployee = 0;
			//appendquery(data.result);
			mdata = data.result;
			for(var i=0;i<data.result.length;i++){
				if (mdata[i].head == '') {
					mdata[i].head = contextPath + "/static/wxworkbench/img/head.jpg";
				}
				var html = '<div class="pading_div_ccc" onclick="getEmployeeById(' + "'" + mdata[i].id + "'" + ')"><img src="' + "" + mdata[i].head + '" class="pading_div_image"/> <span class="pading_div_spn">'
						+ mdata[i].name + '</span></div>';
				$("#querycounttable1").append(html);
			}
			
			
			
			if (query_hasNextEmployee == '0') {
				$('.infinite-scroll-preloader.queryScroll1.querycounttable1').css("display","none");
				return;
			}
			/* $.refreshScroller(); */
		},
		error : function() {
			$.toast("网络异常", 500);
			/* $.hidePreloader(); */
		}
	})
	
}





