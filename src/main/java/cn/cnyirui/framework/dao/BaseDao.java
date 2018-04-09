package cn.cnyirui.framework.dao;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * DAO基类
 * 
 * @author pengzhihua
 *
 * @param <T> 实体类型
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, String> {

	/**
	 * 实体管理器
	 * 
	 * @return
	 */
	EntityManager getEntityManager();

	/**
	 * 根据主键批量删除
	 *
	 * @param ids
	 */
	void delete(String[] ids);

	/**
	 * 根据条件查询一个
	 *
	 * @param searchable 条件 + 分页 + 排序
	 * @return
	 */
	T findOne(Searchable searchable);

	/**
	 * 根据条件查询所有
	 *
	 * @param searchable 条件 + 分页 + 排序
	 * @return
	 */
	Page<T> findAll(Searchable searchable);

	/**
	 * 按查询条件统计记录数
	 * 
	 * @param searchable
	 * @return
	 */
	long count(Searchable searchable);

	/**
	 * 根据条件查询所有
	 * 
	 * @param ql 查询SQL语句(select x from t)，
	 *            如果ql为空，直接调用findAll(Searchable searchable)
	 * @param searchable 条件 + 分页 + 排序
	 * @return
	 */
	Page<T> findAll(String ql, Searchable searchable);

	/**
	 * 
	 * @param ql
	 * @param searchable 查询SQL语句(select x from t)，
	 *            如果ql为空，直接调用findAll(Searchable searchable)
	 * @param nativeSql 原生sql查询
	 * @param resultClass
	 * @return
	 */
	Page<?> findAll(String ql, Searchable searchable, boolean nativeSql, Class<?> resultClass);

	/**
	 * 
	 * @param ql
	 * @param countQl 统计记录数的QL
	 * @param searchable 查询SQL语句(select x from t)，
	 *            如果ql为空，直接调用findAll(Searchable searchable)
	 * @param nativeSql 原生sql查询
	 * @param resultClass
	 * @return
	 */
	Page<?> findAll(String ql, String countQl, Searchable searchable, boolean nativeSql, Class<?> resultClass);

	/**
	 * 记录是否存在
	 * 
	 * @param searchable
	 * @return
	 */
	boolean exists(Searchable searchable);

	/**
	 * 执行update delete语句
	 * 
	 * @param ql
	 * @param searchable
	 * @param nativeSql
	 */
	int executeUpdate(String ql, Searchable searchable, boolean nativeSql);

	/**
	 * 用于sum, avg, max, min返回单个值的sql
	 * 
	 * @param ql
	 * @param searchable
	 * @param nativeSql
	 * @return
	 */
	Object getScalarValue(String ql, Searchable searchable, boolean nativeSql);
}
