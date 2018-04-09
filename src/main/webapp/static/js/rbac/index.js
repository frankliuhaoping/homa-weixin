/**
 * 
 */

var indexTabTitle = '首页';
var contentTabId = '#contentTab';

$(function() {
	$('body').addClass('easyui-layout');
	$('body').layout();
	initThemes();
	addTab(indexTabTitle, contextPath + '/backend/index', '', false);
});

/**
 * 主题颜色
 */
function initThemes() {
	var themes = [ {
		value : 'default',
		text : '天空蓝',
		group : 'Base'
	}, {
		value : 'gray',
		text : '灰霾',
		group : 'Base'
	}, {
		value : 'bootstrap',
		text : '银色',
		group : 'Base'
	}, {
		value : 'black',
		text : '金属黑',
		group : 'Base'
	}, {
		value : 'metro',
		text : '磁贴（白）',
		group : 'Metro'
	}, {
		value : 'metro-blue',
		text : '磁贴（蓝）',
		group : 'Metro'
	}, {
		value : 'metro-gray',
		text : '磁贴（灰）',
		group : 'Metro'
	}, {
		value : 'metro-green',
		text : '磁贴（绿）',
		group : 'Metro'
	}, {
		value : 'metro-orange',
		text : '磁贴（橙）',
		group : 'Metro'
	}, {
		value : 'metro-red',
		text : '磁贴（红）',
		group : 'Metro'
	}, {
		value : 'ui-cupertino',
		text : '清泉',
		group : 'UI'
	}, {
		value : 'ui-dark-hive',
		text : '黑巢',
		group : 'UI'
	}, {
		value : 'ui-pepper-grinder',
		text : '杏黄',
		group : 'UI'
	}, {
		value : 'ui-sunny',
		text : '阳光',
		group : 'UI'
	} ];

	$('#cb-theme').combobox({
		groupField : 'group',
		data : themes,
		editable : false,
		panelHeight : 'auto',
		width : 120,
		onChange : onChangeTheme,
		onLoadSuccess : function() {
			$(this).combobox('setValue', 'default');
		}
	});
}

function onChangeTheme(theme) {
	var link = $('#easyui_theme');
	link.attr('href', contextPath + '/static/easyui/themes/' + theme + '/easyui.css');
	// tab
	var tabs = $(contentTabId).tabs('tabs');
	if (tabs.length > 0) {
		$(tabs).each(function(i) {
			var iframe = getIframeInTab($(this));
			if (iframe && iframe.length > 0) {
				link = $(iframe[0].contentDocument.head).find('#easyui_theme');
				link.attr('href', contextPath + '/static/easyui/themes/' + theme + '/easyui.css');
			}
		});
	}
}

/**
 * 权限树click事件
 * 
 * @param treeNode
 */
function treeNodeClick(treeNode) {
	if ($(this).tree('isLeaf', treeNode.target)) {
		addTab(treeNode.text, contextPath + treeNode.attributes, treeNode.iconCls, true);
	} else {
		$(this).tree('toggle', treeNode.target);
	}
}

/**
 * 动态添加tab
 * 
 * @param title
 * @param url
 * @param icon
 */
function addTab(title, url, icon, canClose) {
	if ($(contentTabId).tabs('exists', title)) {
		$(contentTabId).tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
		$(contentTabId).tabs('add', {
			title : title,
			content : content,
			closable : canClose,
			iconCls : icon,
			tools : [ {
				iconCls : 'icon-mini-refresh',
				handler : function() {
					setTimeout(refreshTab, 300);
				}
			} ],
		});
		$(contentTabId).find('.icon-mini-refresh').attr('title', '刷新');
		$(contentTabId).find('.tabs-close').attr('title', '关闭');
	}
	$(document).attr('title', webAppTitle + '-' + title);
	bindTabEvent();
}

/**
 * 关闭tab
 * 
 * @param tab
 */
function closeTab(tab) {
	if (!tab) {
		tab = $(contentTabId).tabs('getSelected');
	}

	if (tab) {
		var index = $(contentTabId).tabs('getTabIndex', tab);
		$(contentTabId).tabs('close', index);
	}
}
/**
 * 刷新tab中的iframe
 * 
 * @param tab
 */
function refreshTab(tab) {
	if (!tab) {
		tab = $(contentTabId).tabs('getSelected');
	}
	if (tab) {
		var iframe = getIframeInTab(tab);
		if (iframe && iframe.length > 0) {
			iframe[0].contentWindow.location.reload(true);
		}
	}
}

/**
 * 取tab中的iframe
 * 
 * @param tab
 * @returns
 */
function getIframeInTab(tab) {
	if (tab && tab.length > 0) {
		var iframe = tab.find('iframe');
		return iframe
	}
}

/**
 * 释放iframe占用的内存
 * 
 * @param iframe
 */
function destroyIframe(iframe) {
	if (iframe && iframe.length > 0) {
		try {
			iframe[0].src = 'about:blank';
			iframe[0].contentWindow.document.write('');
			iframe[0].contentWindow.document.clear();
			iframe[0].contentWindow.document.close();
			iframe.remove();
		} catch (e) {
		}
	}
}

/**
 * tab关闭事件
 * 
 * @param title
 * @param index
 * @returns {Boolean}
 */
function beforeTabClose(title, index) {
	if (title == indexTabTitle) {
		return false;
	}
	var tab = $(this).tabs('getTab', index);
	var iframe = getIframeInTab(tab);
	destroyIframe(iframe);
	return true;
}

/**
 * tab select
 * 
 * @param title
 * @param index
 */
function onSelectTab(title, index) {
	$(document).attr('title', webAppTitle + '-' + title);
}

/**
 * 绑定tab菜单事件
 */
function bindTabEvent() {
	var tabsInner = $(contentTabId).find('.tabs-selected a.tabs-inner');
	// 双击关闭TAB选项卡
	tabsInner.on('dblclick', function() {
		var title = $(this).children(".tabs-closable").text();
		$(contentTabId).tabs('close', title);
	});
	// 右鍵菜单
	tabsInner.on('contextmenu', function(e) {
		$('#tabsMenu').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		return false;
	});
}

/**
 * tab菜单click事件
 */
function tabsMenuClick(item) {
	var allTabs = $(contentTabId).tabs('tabs');
	var allTabTitle = [];
	$.each(allTabs, function(i, n) {
		allTabTitle.push($(n).panel('options').title);
	});

	var selectedTab = $(contentTabId).tabs('getSelected');
	var selectedTabIndex = $(contentTabId).tabs('getTabIndex', selectedTab);

	switch (item.name) {
	case "refresh":
		refreshTab(selectedTab);
		break;
	case "newWindow":
		var iframe = getIframeInTab(selectedTab);
		var url = $(iframe).attr('src');
		window.open(url);
		break;
	case "close":
		closeTab(selectedTab);
		break;
	case "closeAll":
		$.each(allTabTitle, function(i, n) {
			$(contentTabId).tabs('close', n);
		});
		break;
	case "closeOther":
		$.each(allTabTitle, function(i, n) {
			if (i != selectedTabIndex) {
				$(contentTabId).tabs('close', n);
			}
		});
		break;
	case "closeRight":
		$.each(allTabTitle, function(i, n) {
			if (i > selectedTabIndex) {
				$(contentTabId).tabs('close', n);
			}
		});
		break;
	case "closeLeft":
		$.each(allTabTitle, function(i, n) {
			if (i < selectedTabIndex) {
				$(contentTabId).tabs('close', n);
			}
		});
		break;
	}
}

function modifyPassword() {
	var formOptions = {
		id : 'modifyPasswordForm',
		url : contextPath + '/rbac/sysUser/saveModifyPasswordForm',
	};

	var dialogOptions = {
		id : 'modifyPasswordDialog',
		title : '修改密码',
		width : 500,
		href : contextPath + '/rbac/sysUser/showModifyPasswordForm',
		onOpen : function() {
			$('#oldPassword').textbox('textbox').focus();
		},
	};

	base.dialog(dialogOptions, formOptions);
}