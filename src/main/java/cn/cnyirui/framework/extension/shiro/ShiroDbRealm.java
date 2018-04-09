package cn.cnyirui.framework.extension.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import cn.cnyirui.framework.extension.captcha.CaptchaService;
import cn.cnyirui.framework.model.po.rbac.SysRole;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.service.rbac.SysUserService;
import cn.cnyirui.framework.utils.Constants;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.model.po.Employee;

public class ShiroDbRealm extends AuthorizingRealm {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private CaptchaService captchaService;

	/**
	 * 帐号权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 帐号
		String loginName = (String) principals.getPrimaryPrincipal();
		SysUser sysUser = sysUserService.findByLoginName(loginName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<SysRole> sysRoles = sysUser.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			// 基于Role的权限信息
			info.addRole(sysRole.getName());
		}
		info.addStringPermissions(CurrentUserUtils.getPermissionList());
		return info;
	}

	/**
	 * 帐号认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
	        throws AuthenticationException {
		if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
			throw new AccountException("Null username are not allowed by this realm.");
		}
		String username = authenticationToken.getPrincipal().toString();
		clearAuthorizationInfo(username);
		if (CaptchaUsernamePasswordToken.class.isAssignableFrom(authenticationToken.getClass())) {
			CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authenticationToken;
			// 输入的验证码
			String inputedCaptcha = token.getCaptcha();
			if (!captchaService.validateCaptcha(SecurityUtils.getSubject().getSession(), inputedCaptcha)) {
				throw new IncorrectCaptchaException("验证码错误");
			}
		}
		SysUser sysUser = sysUserService.findByLoginNameOrEmployeeCodeOrEmployeeMobile(username);
		if (sysUser == null) {
			throw new UnknownAccountException("用户不存在！");
		}
		if (sysUser.getIsDisabled() == true) {
			throw new LockedAccountException("用户已经被屏蔽！");
		}
		if (!sysUser.getIsAdmin()) {
			Employee employee = sysUser.getEmployee();
			if (employee == null) {
				throw new AccountException("当前登录帐号未与关联到任何员工，请设置好后再重新登录！");
			}
			if (employee.getOrganization() == null) {
				throw new AccountException("当前登录帐号未设置组织架构，请设置好后再重新登录！");
			}
		}
		return new SimpleAuthenticationInfo(sysUser.getLoginName(), sysUser.getPassword(), getName());
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAuthorizationInfo(String loginName) {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			cache.remove(loginName);
		}
	}

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		if (SessionUtils.getAttribute(Constants.WEIXIN_OPENID_SESSION_KEY) != null) {
			return CurrentUserUtils.getCurrentLoginName() + "_WX";
		} else {
			return CurrentUserUtils.getCurrentLoginName() + "PC";
		}

	}

}
