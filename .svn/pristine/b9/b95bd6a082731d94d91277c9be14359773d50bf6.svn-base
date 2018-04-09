package cn.cnyirui.homaweixin.dao.backend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.Employee;

/**
 * 员工
 * 
 * @author liumuya
 *
 */
public interface EmployeeDao extends EmployeeDaoCustom, BaseDao<Employee> {
	/**
	 * 按SysUser的id查找
	 * 
	 * @param sysUserId
	 * @return
	 */
	Employee findBySysUserId(String sysUserId);

	/**
	 * 按内销系统的ID查找
	 * 
	 * @param imsId
	 * @return
	 */
	@Query("select e from Employee as e where e.IMSId = ?1 and e.IMSType = ?2")
	Employee findByIMSIdAndType(Long imsId, Integer imsType);

	/**
	 * 
	 * 根据组织架构查employee
	 */
	@Query("from Employee e where e.organization.id = ?1")
	List<Employee> findEmployeeByOId(String oid);

}
