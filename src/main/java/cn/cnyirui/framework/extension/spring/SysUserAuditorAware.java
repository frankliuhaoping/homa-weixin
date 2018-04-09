package cn.cnyirui.framework.extension.spring;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.utils.CurrentUserUtils;

/**
 * 实体类自动注入createdBy createdTime lastModifiedBy lastModifiedTime
 * 
 * @author pengzhihua
 *
 */
public class SysUserAuditorAware implements AuditorAware<SysUser> {
    public SysUser getCurrentAuditor() {
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            return CurrentUserUtils.getSysUser(true);
        }
        return null;

    }

}
