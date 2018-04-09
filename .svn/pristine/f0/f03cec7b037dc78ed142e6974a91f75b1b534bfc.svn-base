package cn.cnyirui.homaweixin.dao.backend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.Organization;

/**
 * 组织架构
 * 
 * @author liumuya
 *
 */
public interface OrganizationDao extends BaseDao<Organization> {
	/**
	 * 最顶层
	 * 
	 * @return
	 */
	@Query("select m from Organization as m where nvl(m.deleted, 0) = 0 and m.parent.id is null order by m.name")
	public List<Organization> findRootOrganization();

	/**
	 * 下级组织架构
	 * 
	 * @return
	 */
	@Query("select m from Organization as m where nvl(m.deleted, 0) = 0 and m.parent.id = ?1 order by m.name")
	public List<Organization> findSubOrganization(String parentId);

	/**
	 * 按名称查找
	 * 
	 * @param name
	 * @return
	 */
	@Query("select e from Organization as e where e.name = ?1")
	public Organization findByName(String name);

	/**
	 * 按内销系统的ID查找
	 * 
	 * @param imsId
	 * @param imsType
	 *            (0=业务组织，1=办事处，2=门店)
	 * @return
	 */
	@Query("select e from Organization as e where e.IMSId = ?1 and e.IMSType = ?2")
	public Organization findByIMSIdAndType(Long imsId, Integer imsType);

	/**
	 * 按内销系统的ID查找
	 * 
	 * @param imsId
	 * @return
	 */
	@Query("select e from Organization as e where e.IMSId = ?1")
	public Organization findByIMSId(Long imsId);

	/**
	 * 按负责人查找
	 * 
	 * @param employeeId
	 * @return
	 */
	@Query("select e from Organization as e where e.director.id = ?1")
	public List<Organization> findByDirector(String employeeId);

	/**
	 * 业务员下的门店
	 * 
	 * @return
	 */
	@Query("select m from Organization as m where m.director.id = ?1")
	public List<Organization> findSubOrganization2(String userid);

}
