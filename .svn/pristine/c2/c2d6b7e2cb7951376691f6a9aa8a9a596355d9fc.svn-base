
$(function() {
	$('#chatHistory').datagrid(
					{
						url : contextPath + '/backend/chatHistory/jsonList',
						// 设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
						fitColumns : true,
						// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能
						autoRowHeight : false,
						// 如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
						// nowrap : false,
						// 数据网格（datagrid）面板的头部工具栏。可能的值：
						// 1、数组，每个工具选项与链接按钮（linkbutton）一样
						// 2、选择器，只是工具栏。
						// toolbar : '#datagrid-toolbar',
						// 设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
						showHeader : false,
						striped : true,
						// 指示哪个字段是标识字段
						idField : 'id',
						// 设置为 true，则在数据网格（datagrid）底部显示分页工具栏
						pagination : true,
						// 初始化页面尺寸
						pageSize : 20,
						// 隐藏边框
						border : false,
						// 当设置为 true 时，尺寸就适应它的父容器
						fit : true,
						// 设置为 true，则只允许选中一行
						singleSelect : false,
						// ctrl选择多行
						ctrlSelect : true,
						loadMsg : '',
						//单击事件
						onClickRow : function(rowIndex, rowData) {
							$("#exportBtn").attr("href",contextPath+"/backend/ChatContent/export?hhid="+rowData.id);
							$('#chatContent').datagrid('reload', {
								'search.hhid_eq' : rowData.id
							});
						},

						columns : [ [
								{
									field : 'code',
									title : 'Code',
									width : 50,
									formatter : function(value, row, index) {
										if (row) {
											var html = '';
											html += '<table class="flexbox list " style="width:100%;"><tbody><tr style="width:100%;">';
											var img = row.image;
											if (img != "" && img != null) {
												img = img;
											} else {
												// 显示默认图片
												img = contextPath
														+ "/upload/images/head.png";

											}
											
											var img1 = row.image2;
											
											if (img1 != "" && img1 != null) {
												img1 = img1;
											} else {
												// 显示默认图片
												img1 = contextPath
														+ "/upload/images/head.png";

											}
											
											html += '<td style="border:none;width:104px;"><img src="' + img + '">';
											
											if(row.type==1){
											html += '<img src="' + img1 + '"></td>';
											}
											
											html += '<td style="border:none;"><span class="flexbox-h" onclick="">';
											html += ' <span class="t tml">';
											html += row.name;
											html += '</span><br>';
											html += '<span class="p pp content">'
													+ row.lastContent
													+ '</span></td>';
											html += '<td style="border:none;"></span><span class="flexbox-h tr">';
											html += '<p class="time timet">';
											html += row.lastTime;
											html += '</p></span></td></tr></tbody></table>';
											return html;

										} else {
											return value;
										}
									}
								},

						] ]
					});

	// 内容
	$('#chatContent').datagrid(
					{
						url : contextPath + '/backend/ChatContent/Content',
						// 设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
						fitColumns : true,
						// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能
						autoRowHeight : false,
						// 如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
						// nowrap : false,
						// 数据网格（datagrid）面板的头部工具栏。可能的值：
						// 1、数组，每个工具选项与链接按钮（linkbutton）一样
						// 2、选择器，只是工具栏。
						toolbar : '#datagrid-toolbar',
						// 设置为 true，则把行条纹化。（即奇偶行使用不同背景色
						showHeader : false,
						//滚动条宽度
						scrollbarSize:18,
						// 指示哪个字段是标识字段
						idField : 'id',
						// 设置为 true，则在数据网格（datagrid）底部显示分页工具栏
						pagination : true,
						// 初始化页面尺寸
						pageSize : 20,
						// 隐藏边框
						border : false,
						striped:false,
						// 当设置为 true 时，尺寸就适应它的父容器
						fit : true,
						// 设置为 true，则只允许选中一行
						singleSelect : false,
						// ctrl选择多行
						ctrlSelect : true,
						nowrap : false,
						loadMsg : '',
						queryParams : {
							"hhid" : '2'
						},
						onClickRow : function(rowIndex, rowData) {

						},
						columns : [ [
								{
									field : 'code',
									title : 'Code',
									width : 350,
									formatter : function(value, row, index) {
										if (row.contentType == 1) {
											//文字
											var data = $('#chatContent').datagrid('getData');  
											var sear=data.searContent;
                                            var searcontent = row.content.replace(sear,"<span style='background:yellow'>"+sear+"</span>")
                                            searcontent = emojiParse(searcontent)
											var html = ""
													+ '<article class="List" id="2ddfffbab16b4aee8dcea53bc42bd5ae">'
													+ '<p class="DataTime">'
													+ '<span>'+row.senderName+'</span>'
													+ row.sendTime
													+ '</p><section class="list_content">'
													+ '<p class="">'
													+ searcontent + '</p>'
													+ '</section>'
													+ '</article>';
											return html;
											
										}else if(row.contentType == 2){
											//图片
											var html = ""
												+ '<article class="List" id="2ddfffbab16b4aee8dcea53bc42bd5ae">'
												+ '<p class="DataTime">'
												+ '<span>'+row.senderName+'</span>'
												+ row.sendTime
												+ '</p><section class="img">'
												+'<a href="'+contextPath+row.content+'" data-lightbox="example-set"><img src="'+contextPath+row.content+'" class="" /></a>';
												+ '</section>'
												+ '</article>';
												
										return html;
											
										}else if(row.contentType == 3){
											 //语音
											
										}else if(row.contentType == 4){
											//表情
											
										}
									}
								},

						] ]
					});
	
	
	$('#searcher').searchbox({ 
		searcher:function(value,name){ 
			//重新加载页面
			
			var data=null;
			
			if (name=="all") {
				
			} else if(name=="san"){
				data="3";
			} else if(name=="liu"){
   			    data="6";
			}
			 else if(name=="nian"){
			     data="1";
		    }
			
			//consloe.log(data);
			
			$('#chatContent').datagrid('reload', {
				'search.content_like' : value,
				'search.sendTime_gt' : data
			});
		}, 
		menu:'#menu', 
		prompt:'请输入值' 
		});
	

});

function emojiParse(text){
	var emoji_list = [
      "[微笑]","[撇嘴]","[色]","[发呆]","[得意]","[流泪]","[害羞]","[闭嘴]","[睡]","[大哭]","[尴尬]","[发怒]","[调皮]","[呲牙]","[惊讶]","[难过]","[酷]","[冷汗]","[抓狂]","[吐]","[偷笑]","[愉快]","[白眼]","[傲慢]","[饥饿]","[困]","[惊恐]","[流汗]","[憨笑]","[悠闲]","[奋斗]","[咒骂]","[疑问]","[嘘]","[晕]","[疯了]","[衰]","[骷髅]","[敲打]","[再见]","[擦汗]","[抠鼻]","[鼓掌]","[糗大了]","[坏笑]","[左哼哼]","[右哼哼]","[哈欠]","[鄙视]","[委屈]","[快哭了]","[阴险]","[亲亲]","[吓]","[可怜]","[菜刀]","[西瓜]","[啤酒]","[篮球]","[乒乓]"                                                                                  
    ];
	              
	var regx = /(\[[\u4e00-\u9fa5]*\w*\]){1}/g; 
    var rs = text.match(regx);  
    if (rs) {  
        for (var i = 0; i < rs.length; i++) {
            var symbol = rs[i];  
            var imgpath = null;
            for(var j=0;j<emoji_list.length; j++){
            	if(symbol == emoji_list[j]){
            		imgpath = contextPath+"/static/wxworkbench/img/emojicons/ace_emoji_"+(j+1)+".png";
            		break;
            	}
            }
            if(imgpath){
                var t = "<img src='"+imgpath+"' style='width:30px;'/>";  
                text = text.replace(symbol, t);  
            }
        }  
    }
    return text;
}

