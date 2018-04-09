package cn.cnyirui.framework.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cnyirui.framework.dao.rbac.SysMenuDao;
import cn.cnyirui.framework.dao.rbac.SysPermissionDao;
import cn.cnyirui.framework.dao.rbac.SysUserDao;
import cn.cnyirui.framework.model.eo.SysMenuUseOf;
import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.model.vo.EasyUITreeNode;
import cn.cnyirui.framework.model.vo.SysMenuVo;
import cn.cnyirui.framework.service.weixin.WeiXinUserService;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;

/**
 * 当前用户信息工具类
 * 
 * @author pengzhihua
 *
 */
public class CurrentUserUtils {
	private static Logger logger = LoggerFactory.getLogger(CurrentUserUtils.class);
	private static String CURRENT_SYSUSER_SESSION_KEY = "CURRENT_SYSUSER_SESSION_KEY";
	private static String CURRENT_EMPLOYEE_SESSION_KEY = "CURRENT_EMPLOYEE_SESSION_KEY";
	private static String CURRENT_WEIXINUSER_SESSION_KEY = "CURRENT_WEIXINUSER_SESSION_KEY";
	private static String CURRENT_SYSUSER_PERMISSION_SESSION_KEY = "CURRENT_SYSUSER_PERMISSION_SESSION_KEY";
	private static String CURRENT_SYSUSER_SYSMENU_TREE_SESSION_KEY = "CURRENT_SYSUSER_SYSMENU_TREE_SESSION_KEY";

	private static SysUserDao sysUserDao;
	private static SysMenuDao sysMenuDao;
	private static SysPermissionDao sysPermissionDao;
	private static EmployeeDao employeeDao;
	private static WeiXinUserService weiXinUserService;

	@Resource
	public void setSysUserDao(SysUserDao sysUserDao) {
		CurrentUserUtils.sysUserDao = sysUserDao;
	}

	@Resource
	public void setSysMenuDao(SysMenuDao sysMenuDao) {
		CurrentUserUtils.sysMenuDao = sysMenuDao;
	}

	@Resource
	public void setSysPermissionDao(SysPermissionDao sysPermissionDao) {
		CurrentUserUtils.sysPermissionDao = sysPermissionDao;
	}

	@Resource
	public void setWeiXinUserService(WeiXinUserService weiXinUserService) {
		CurrentUserUtils.weiXinUserService = weiXinUserService;
	}

	@Resource
	public void setEmployeeDao(EmployeeDao employeeDao) {
		CurrentUserUtils.employeeDao = employeeDao;
	}

	/**
	 * 当前登录用户名
	 * 
	 * @return
	 */
	public static String getCurrentLoginName() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return String.valueOf(subject.getPrincipal()).toLowerCase();
		}
		return null;
	}

	/**
	 * 返回当前登录的用户，从数据库中取
	 * 
	 * @return 返回管控实体
	 */
	public static SysUser getSysUser() {
		return getSysUser(false);
	}

	/**
	 * 返回当前登录的用户
	 * 
	 * @param fromSession
	 *            true 如果session有存储，直接从session获取，否则从数据库中取，false直接从数据库中取
	 * @return
	 */
	public static SysUser getSysUser(boolean fromSession) {
		SysUser sysUser = SessionUtils.getAttribute(CURRENT_SYSUSER_SESSION_KEY);
		if (sysUser == null || !fromSession) {
			if (sysUser != null) {
				sysUser = sysUserDao.findOne(sysUser.getId());
			} else {
				sysUser = sysUserDao.findByLoginName(getCurrentLoginName());
			}
			if (fromSession) {
				sysUserDao.getEntityManager().detach(sysUser);
				setSysUser(sysUser);
			}
		}
		if (sysUser == null) {
			logger.error("获取sysUser失败！！");
		}
		return sysUser;
	}

	/**
	 * 返回当前登录的用户员工信息
	 * 
	 * @param fromSession
	 *            true 如果session有存储，直接从session获取，否则从数据库中取，false直接从数据库中取
	 * @return
	 */
	public static Employee getEmployee(boolean fromSession) {
		Employee employee = SessionUtils.getAttribute(CURRENT_EMPLOYEE_SESSION_KEY);
		if (employee == null || !fromSession) {
			if (employee != null) {
				employee = employeeDao.findOne(employee.getId());
			} else {
				SysUser sysUser = getSysUser(true);
				employee = employeeDao.findBySysUserId(sysUser.getId());
			}
			if (fromSession) {
				// 组织架构信息
				if (employee != null) {
					Organization organization = employee.getOrganization();
					organization.getId();
					employeeDao.getEntityManager().detach(employee);
					employeeDao.getEntityManager().detach(organization);
					employee.setOrganization(organization);
				}
				SessionUtils.setAttribute(CURRENT_EMPLOYEE_SESSION_KEY, employee);
			}
		}
		if (employee == null) {
			logger.error("获取employee失败！！");
		}
		return employee;
	}

	/**
	 * 返回当前登录的用户员工ID
	 * 
	 * @return
	 */
	public static String getEmployeeId() {
		Employee employee = getEmployee(true);
		if (employee != null) {
			return employee.getId();
		}
		logger.error("获取employeeId失败！！");
		return null;
	}

	/**
	 * 返回当前登录的用户组织架构
	 * 
	 * @return
	 */
	public static Organization getOrganization(boolean fromSession) {
		Employee employee = getEmployee(fromSession);
		if (employee != null) {
			return employee.getOrganization();
		}
		logger.error("获取organization失败！！");
		return null;
	}

	/**
	 * 返回当前登录的用户组织架构ID
	 * 
	 * @param fromSession
	 * @return
	 */
	public static String getOrganizationId() {
		Organization organization = getOrganization(true);
		if (organization != null) {
			return organization.getId();
		}
		logger.error("获取organizationId失败！！");
		return null;
	}

	/**
	 * 返回当前登录的用户微信用户信息
	 * 
	 * @param fromSession
	 *            true 如果session有存储，直接从session获取，否则从数据库中取，false直接从数据库中取
	 * @return
	 */
	public static WeiXinUser getWeiXinUser(boolean fromSession) {
		WeiXinUser weiXinUser = SessionUtils.getAttribute(CURRENT_WEIXINUSER_SESSION_KEY);
		if (weiXinUser == null || !fromSession) {
			if (weiXinUser != null) {
				weiXinUser = weiXinUserService.findOne(weiXinUser.getId());
			} else {
				String wxOpenId = SessionUtils.getAttribute(Constants.WEIXIN_OPENID_SESSION_KEY);
				weiXinUser = weiXinUserService.findOrCreateByWxOpenId(wxOpenId, true);
			}
			if (fromSession) {
				weiXinUserService.getBaseDao().getEntityManager().detach(weiXinUser);
				SessionUtils.setAttribute(CURRENT_WEIXINUSER_SESSION_KEY, weiXinUser);
			}
		}
		if (weiXinUser == null) {
			logger.error("获取weiXinUser失败！！");
		}
		return weiXinUser;
	}

	/**
	 * 将当前用户信息保存到session中
	 * 
	 * @param sysUser
	 *            如果为null，将从session中删除用户信息
	 */
	public static void setSysUser(SysUser sysUser) {
		SessionUtils.setAttribute(CURRENT_SYSUSER_SESSION_KEY, sysUser);
		if (sysUser == null) {
			setPermissionList(null);
			setSysMenuTreeList(null);
		}
	}

	/**
	 * 当前登录用户权限值
	 * 
	 * @return
	 */
	public static List<String> getPermissionList() {
		List<String> permissionList = SessionUtils.getAttribute(CURRENT_SYSUSER_PERMISSION_SESSION_KEY);
		if (permissionList != null) {
			return permissionList;
		} else {
			initPermission();
			return SessionUtils.getAttribute(CURRENT_SYSUSER_PERMISSION_SESSION_KEY);
		}

	}

	/**
	 * 当前登录用户权限菜单
	 * 
	 * @return
	 */
	public static List<EasyUITreeNode> getSysMenuTreeList() {
		List<EasyUITreeNode> easyUITreeNodes = SessionUtils.getAttribute(CURRENT_SYSUSER_SYSMENU_TREE_SESSION_KEY);
		if (easyUITreeNodes != null) {
			return easyUITreeNodes;
		} else {
			initPermission();
			return SessionUtils.getAttribute(CURRENT_SYSUSER_SYSMENU_TREE_SESSION_KEY);
		}

	}

	/**
	 * 保存权限到session中
	 * 
	 * @param permissionList
	 *            如果为null，将从session中删除权限信息
	 */
	public static void setPermissionList(List<String> permissionList) {
		SessionUtils.setAttribute(CURRENT_SYSUSER_PERMISSION_SESSION_KEY, permissionList);
	}

	/**
	 * 保存菜单到session中
	 * 
	 * @param sysMenuTreeList
	 *            如果为null，将从session中删除菜单信息
	 */
	public static void setSysMenuTreeList(List<EasyUITreeNode> sysMenuTreeList) {
		SessionUtils.setAttribute(CURRENT_SYSUSER_SYSMENU_TREE_SESSION_KEY, sysMenuTreeList);
	}

	/**
	 * 获取权限菜单
	 */
	public static void initPermission() {
		SysMenuUseOf sysMenuUseOf = SysMenuUseOf.PC;
		if (SessionUtils.getAttribute(Constants.WEIXIN_OPENID_SESSION_KEY) != null) {
			sysMenuUseOf = SysMenuUseOf.WX; // 微信端
		}

		List<String> permissionList = new ArrayList<String>();
		SysUser sysUser = CurrentUserUtils.getSysUser(true);
		// 权限，分离出菜单
		List<SysMenuVo> menus = null;
		if (sysUser.getIsAdmin()) {
			menus = sysPermissionDao.getAdminPermission(sysMenuUseOf.getValue());
		} else {
			menus = sysPermissionDao.getPermissionsByLoginName(sysUser.getLoginName(), sysMenuUseOf.getValue());
		}

		EasyUITreeNode parentNode = null;
		List<SysMenu> sysMenus = sysMenuDao.findRootSysMenu();
		List<EasyUITreeNode> easyUITreeNodes = new ArrayList<EasyUITreeNode>();
		for (SysMenu sysMenu : sysMenus) {
			parentNode = new EasyUITreeNode(sysMenu.getId(), StringUtils.defaultIfEmpty(sysMenu.getName(), ""),
			        StringUtils.defaultIfEmpty(sysMenu.getIcon(), ""),
			        StringUtils.defaultIfEmpty(sysMenu.getUrl(), ""));
			easyUITreeNodes.add(parentNode);
		}

		for (SysMenuVo m : menus) {
			if (m.getParentId() == null) {
				continue;
			}
			// 添加权限list
			if (m.getPermissionValue() != null) {
				if (permissionList.indexOf(m.getPermissionValue()) == -1) {
					permissionList.add(m.getPermissionValue()); // 权限值
				}
			}
			// 父节点
			parentNode = EasyUITreeNode.findById(easyUITreeNodes, m.getParentId());
			if (parentNode != null) {
				if (!parentNode.childExists(m.getId())) {
					EasyUITreeNode child = new EasyUITreeNode(m.getId(), StringUtils.defaultIfEmpty(m.getName(), ""),
					        StringUtils.defaultIfEmpty(m.getIcon(), ""), StringUtils.defaultIfEmpty(m.getUrl(), ""));
					parentNode.getChildren().add(child);
				}
			}
		}
		setPermissionList(permissionList);
		setSysMenuTreeList(easyUITreeNodes);

		// 创建员工能查看数据的组织架构
		if (getSysUser(true).getIsAdmin()) {
			employeeDao.createCanViewOrganizationList("",
			        SessionUtils.getSession().getId().toString());
		} else {
			employeeDao.createCanViewOrganizationList(getEmployeeId(),
			        SessionUtils.getSession().getId().toString());
		}
	}
}
