package cn.cnyirui.framework.service.rbac;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.rbac.SysMenuDao;
import cn.cnyirui.framework.dao.rbac.SysPermissionDao;
import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.po.rbac.SysPermission;
import cn.cnyirui.framework.service.BaseService;

@Service
public class SysPermissionService extends BaseService<SysPermission> {

    @Resource
    private SysPermissionDao sysPermissionDao;
    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public BaseDao<SysPermission> getBaseDao() {
        return sysPermissionDao;
    }

    @Override
    public SysPermission doSavePageModel(String action, SysPermission pageModel, SysPermission toSavePageModel,
            HttpServletRequest request) {
        SysPermission sysPermission = super.doSavePageModel(action, pageModel, toSavePageModel, request);
        SysMenu sysMenu = sysPermission.getSysMenu();
        sysMenu.setPermissionCode(StringUtils.substringBefore(sysPermission.getPermissionValue(), ":"));
        sysMenuDao.save(sysMenu);
        return sysPermission;
    }
}
