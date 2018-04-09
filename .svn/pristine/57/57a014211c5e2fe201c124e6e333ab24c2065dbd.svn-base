package cn.cnyirui.framework.dao.rbac;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.po.rbac.SysPermission;
import cn.cnyirui.framework.model.vo.SysMenuVo;

/**
 * 菜单的权限
 * 
 * @author pengzhihua
 *
 */
public interface SysPermissionDao extends BaseDao<SysPermission> {

	final String SYSMENUVO_QL = "select new cn.cnyirui.framework.model.vo.SysMenuVo(m.id, m.name, m.url, m.icon, p.permissionValue, m.parent.id)";

	/**
	 * 按登录名获取权限，排除已经屏蔽资源的权限
	 * 
	 * @param loginName
	 * @param useOf 0=PC端，1=微信端
	 * @return
	 */
	@Query(SYSMENUVO_QL + " from SysPermission as p"
	        + " left join p.sysRoles as r"
	        + " left join r.sysUsers as s"
	        + " left join p.sysMenu as m"
	        + " where (s.loginName = ?1) and (m.isDisabled = 0) and (m.useOf = ?2)"
	        + " order by m.parent.id, m.seq, m.id")
	public List<SysMenuVo> getPermissionsByLoginName(String loginName, Integer useOf);

	/**
	 * 管理员的权限
	 * 
	 * @param useOf 0=PC端，1=微信端
	 * @return
	 */
	@Query(SYSMENUVO_QL + " from SysMenu as m"
	        + " left join m.sysPermissions as p"
	        + " where m.isDisabled = 0 and m.useOf = ?1"
	        + " order by m.parent.id, m.seq, m.id")
	public List<SysMenuVo> getAdminPermission(Integer useOf);
}
