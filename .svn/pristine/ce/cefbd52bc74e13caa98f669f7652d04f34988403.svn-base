/**
 * 
 */

var ryUILib = ryUILib || {}; // 命名空间
(function(window, undefined) {
	/**
	 * 外部可调用的对象
	 * 
	 * <pre>
	 * options:{ 
	 * 		treeId, 页面中tree的id，默认'organizationTree'
	 *      onClick, 与tree的onClick事件不同的是，相同的节点重复点，只执行一次
	 * }
	 * </pre>
	 * 
	 */
	ryUILib.OrganizationTree = function(options, treeOptions) {
		return new OrganizationTree(options, treeOptions);
	}

	var OrganizationTree = function(options, treeOptions) {
		var defaultOptions = {
			treeId : '#organizationTree',
		};

		// tree默认选项
		var defaultTreeOptions = {
			url : contextPath + '/backend/common/organizationTree',
		};
		this.options = $.extend(true, defaultOptions, options);
		this.treeOptions = $.extend(true, defaultTreeOptions, treeOptions);
		this.treeObject = $(this.options.treeId);

		var oldOnClick = this.treeOptions.onClick;
		var onClick = this.options.onClick;
		var oldClickNodeId = null;
		// onClick事件
		this.treeOptions.onClick = function(node) {
			var nodeType = '';
			if ($(this).tree('isLeaf', node.target)) {
				nodeType = 'leaf'; // 叶子节点
			} else {
				var rootNode = $(this).tree('getRoot'); // 根节点
				if (rootNode.id == node.id) {
					nodeType = 'root';
				} else {
					nodeType = 'middle'; // 中间的节点
				}
			}

			if (nodeType != 'leaf') {
				$(this).tree('toggle', node.target);
			}
			if ((node.id != oldClickNodeId) && onClick) {
				onClick.call(this, node, nodeType);
				oldClickNodeId = node.id;
			}
			if (oldOnClick) {
				oldOnClick.call(this, node);
			}
		};
		// 默认展开第一层
		var oldOnLoadSuccess = this.treeOptions.onLoadSuccess;
		this.treeOptions.onLoadSuccess = function(node, data) {
			if (node == null) {
				var rootNode = $(this).tree('getRoot');
				if (rootNode && rootNode.state == 'closed') {
					$(this).tree('toggle', rootNode.target);
				}
			}

			if (oldOnLoadSuccess) {
				oldOnLoadSuccess.call(this, node, data);
			}
		};
		this.treeObject.tree(this.treeOptions);
	}

	OrganizationTree.prototype = {};

	// /////////////////////////////// private

})(window);
