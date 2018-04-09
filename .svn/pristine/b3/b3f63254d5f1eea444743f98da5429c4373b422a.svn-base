package cn.cnyirui.framework.dao.rbac;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.po.rbac.SysMenu;

/**
 * 菜单
 * 
 * @author pengzhihua
 *
 */
public interface SysMenuDao extends BaseDao<SysMenu> {
	/**
	 * 最顶层菜单
	 * 
	 * @return
	 */
	@Query("select m from SysMenu as m where m.parent.id is null order by m.seq, m.id")
	public List<SysMenu> findRootSysMenu();

	/**
	 * 子菜单
	 * 
	 * @return
	 */
	@Query("select m from SysMenu as m where m.parent.id = ?1 order by m.seq, m.id")
	public List<SysMenu> findSubSysMenu(String parentId);

	/**
	 * 按url查询sysMenu
	 * 
	 * @param url
	 * @return
	 */
	@Query("select m from SysMenu as m where m.url = ?1")
	public List<SysMenu> findByUrl(String url);

}
