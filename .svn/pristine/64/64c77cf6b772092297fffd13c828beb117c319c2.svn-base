package cn.cnyirui.framework.controller.rbac;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.controller.bind.annotation.PageableDefaults;
import cn.cnyirui.framework.controller.bind.annotation.SearchableDefaults;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.vo.EasyUITreeNode;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.rbac.SysMenuService;
import cn.cnyirui.framework.utils.CurrentUserUtils;

@Controller
@RequestMapping("/rbac/sysMenu")
public class SysMenuController extends BaseCRUDController<SysMenu> {

	@Resource
	private SysMenuService sysMenuService;

	@Override
	protected BaseService<SysMenu> getBaseService() {
		return sysMenuService;
	}

	@Override
	@PageableDefaults(sort = { "seq=asc", "parent.id=asc", "name=asc", "id=asc" })
	@SearchableDefaults(needPage = false)
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		return super.jsonList(searchable, request);
	}

	@RequestMapping("/treeJson")
	@ResponseBody
	public List<ObjectNode> treeJson() {
		return sysMenuService.getSysMenuTreeJson(null);
	}

	/**
	 * 权限树json
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/permisssionTreeJson/{id}")
	@ResponseBody
	public List<EasyUITreeNode> permisssionTreeJson(@PathVariable("id") String id) {
		List<EasyUITreeNode> easyUITreeNodes = CurrentUserUtils.getSysMenuTreeList();
		for (EasyUITreeNode easyUITreeNode : easyUITreeNodes) {
			if (easyUITreeNode.getId().equals(id)) {
				return easyUITreeNode.getChildren();
			}
		}
		return null;
	}

}
