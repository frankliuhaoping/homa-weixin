/**
 * 
 */

$(function() {
		 var year = $("#year").text();
	  $.post(contextPath + "/weixin/workbench/saleTask/jsonList?year="+year,
  	 function(data){
	    console.log(data)
	    
	   // alert(data.taskmonth1)
    var obj = {"taskmonth1":data.taskmonth1,"taskmonth2":data.taskmonth2,"taskmonth3":data.taskmonth3,"taskmonth4":data.taskmonth4,"taskmonth5":data.taskmonth5,"taskmonth6":data.taskmonth6,"taskmonth7":data.taskmonth7,"taskmonth8":data.taskmonth8,"taskmonth9":data.taskmonth9,"taskmonth10":data.taskmonth10,"taskmonth11":data.taskmonth11,"taskmonth12":data.taskmonth12,"totalmonth1":data.totalmonth1,"totalmonth2":data.totalmonth2,"totalmonth3":data.totalmonth3,"totalmonth4":data.totalmonth4,"totalmonth5":data.totalmonth5,"totalmonth6":data.totalmonth6,"totalmonth7":data.totalmonth7,"totalmonth8":data.totalmonth8,"totalmonth9":data.totalmonth9,"totalmonth10":data.totalmonth10,"totalmonth11":data.totalmonth11,"totalmonth12":data.totalmonth12,"finishTotal":data.finishTotal,"taskTotal":data.taskTotal,"rate":data.rate,"type":data.type,"month1":data.month1,"month2":data.month2,"month3":data.month3,"month4":data.month4,"month5":data.month5,"month6":data.month6,"month7":data.month7,"month8":data.month8,"month9":data.month9,"month10":data.month10,"month11":data.month11,"month12":data.month12};
    var obj1 = eval(obj);

    taskData = [];totalData = [];
    
    var o = 0;
/*    for(var i in obj1){
        var task = obj1['taskmonth'+o++];
        if(task!=undefined || task!=null){
            taskData.push(task);
        }
        var total = obj1['totalmonth'+o];
        if(total!=undefined || total!=null){
            totalData.push(total);
        }
    }*/
        for(var i=1;i<13; i++){
        var task = obj1['taskmonth'+i];
            taskData.push(task);
        var total = obj1['totalmonth'+i];
            totalData.push(total);
        }


    document.getElementById('main').style.height =  window.innerHeight-50+'px'; // = window.innerHeight-10+'px'
    var myChart = echarts.init(document.getElementById('main'));



    option = {
        title : {
            /*text: year+'年度任务',*/
            subtext: '奥马'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:[year+'月度任务',year+'已完成任务']
        },
/*        dataZoom: {
            show: true,
            start :0,
            end :50
        },*/
        grid:{
            x:60,
            x2:50
        },
        toolbox: {
            show : true,
            feature : {
/*                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}*/
            }
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['一月','二月','三月','四月','五月','六月','七月',"八月","九月","十月","十一月","十二月"]
            },

        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value}'
                }
            }
        ],
        series : [
            {
                name:year+'月度任务',
                type:'line',
                data:totalData,
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:year+'已完成任务',
                type:'line',
                data:taskData,
                markPoint : {
                data : [
                        {name : '周最低', value : 0, xAxis: 1, yAxis: -1.5}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);

    var evt ="orientationChange" in window ? "orientationChange" : "resize";
    window.addEventListener(evt, function () {
            //alert(window.screen.availHeight)
            if(Math.abs(window.orientation)==90){
                document.getElementById('main').style.height = window.innerHeight-10+'px';
                var myChart = echarts.init(document.getElementById('main'));
                myChart.setOption(option);
            }


    })
    
	  },"json");
})