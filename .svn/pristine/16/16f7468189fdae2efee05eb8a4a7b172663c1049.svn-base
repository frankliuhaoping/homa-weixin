package cn.cnyirui.framework.dao.rbac;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.po.rbac.SysUser;

/**
 * 登录用户
 * 
 * @author pengzhihua
 *
 */
public interface SysUserDao extends BaseDao<SysUser> {

	/**
	 * 按登录名查询
	 * 
	 * @param loginName
	 * @return
	 */
	@Query("select s from SysUser s where lower(s.loginName) = ?1")
	SysUser findByLoginName(String loginName);

	/**
	 * 按旧的登录名查询
	 * 
	 * @param loginName
	 * @return
	 */
	@Query("select s from SysUser s where lower(s.oldLoginName) = ?1")
	SysUser findByOldLoginName(String loginName);

	/**
	 * 按员工工号查找
	 * 
	 * @param code
	 * @return
	 */
	@Query("select s from SysUser s join s.employeeList e where e.code = ?1")
	List<SysUser> findByEmployeeCode(String code, Pageable pageable);

	/**
	 * 按手机号码查找
	 * 
	 * @param mobileNo
	 * @return
	 */
	@Query("select s from SysUser s join s.employeeList e where e.mobileNo = ?1")
	List<SysUser> findByEmployeeMobileNo(String mobileNo, Pageable pageable);
}
