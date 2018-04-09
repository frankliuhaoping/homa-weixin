package cn.cnyirui.framework.controller.rbac;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.po.rbac.SysPermission;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.rbac.SysMenuService;
import cn.cnyirui.framework.service.rbac.SysPermissionService;

@Controller
@RequestMapping("/rbac/sysPermission")
public class SysPermissionController extends BaseCRUDController<SysPermission> {

	@Resource
	private SysPermissionService sysPermissionService;
	@Resource
	private SysMenuService sysMenuService;

	@Override
	protected BaseService<SysPermission> getBaseService() {
		return sysPermissionService;
	}

	@Override
	public ModelAndView doList(HttpServletRequest request) {
		ModelAndView m = super.doList(request);
		String sysMenuId = ServletRequestUtils.getStringParameter(request, "sysMenuId", null);
		if (StringUtils.isNotEmpty(sysMenuId)) {
			m.addObject("sysMenu", sysMenuService.findOne(sysMenuId));
		}
		return m;
	}

}
