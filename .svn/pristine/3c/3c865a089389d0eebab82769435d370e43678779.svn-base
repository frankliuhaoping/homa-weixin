package cn.cnyirui.ims.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.ims.entity.IMSBusinessOrganization;
import cn.cnyirui.ims.entity.IMSEmployee;
import cn.cnyirui.ims.entity.IMSOffice;
import cn.cnyirui.ims.entity.IMSProduct;
import cn.cnyirui.ims.entity.IMSProductCategory;
import cn.cnyirui.ims.entity.IMSSaler;
import cn.cnyirui.ims.entity.IMSSalerWage;
import cn.cnyirui.ims.entity.IMSStore;

@Repository
@Lazy(true)
public class IMSCommonDao {

	@PersistenceContext(unitName = "jpa.ims")
	private EntityManager entityManager;

	/**
	 * 保存数据
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional(transactionManager = "imsTransactionManager")
	public <T extends Persistable<Long>> T save(T entity) {
		if (entity.isNew()) {
			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param ql
	 * @param parameters
	 */
	@Transactional(transactionManager = "imsTransactionManager")
	public void deleteAll(String ql, Map<String, Object> parameters) {
		Query query = entityManager.createNativeQuery(ql);
		if (parameters != null) {
			for (String parameter : parameters.keySet()) {
				query.setParameter(parameter, parameters.get(parameter));
			}
		}
		query.executeUpdate();
	}

	/**
	 * 查询数据
	 * 
	 * @param ql
	 * @param nativeQl
	 * @param parameters
	 * @param resultClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(String ql, boolean nativeQl, Map<String, Object> parameters, Class<T> resultClass) {
		if (ql == null) {
			return Collections.emptyList();
		}

		Query query = null;
		if (nativeQl) {
			query = entityManager.createNativeQuery(ql, resultClass);
		} else {
			query = entityManager.createQuery(ql, resultClass);
		}
		if (parameters != null) {
			for (String parameter : parameters.keySet()) {
				query.setParameter(parameter, parameters.get(parameter));
			}
		}
		return query.getResultList();
	}

	/**
	 * 所有产品分类
	 * 
	 * @return
	 */
	public List<IMSProductCategory> findAllIMSProductCategory(Long parentId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String ql = null;
		if (parentId != null) {
			ql = " select e from IMSProductCategory as e where e.parentId = :parentId order by e.name";
			parameters.put("parentId", parentId);
		} else {
			ql = " select e from IMSProductCategory as e where e.parentId is null order by e.name";
		}

		return findAll(ql, false, parameters, IMSProductCategory.class);
	}

	/**
	 * 所有产品分类(箱条分类)
	 * 
	 * @return
	 */
	public List<IMSProductCategory> findAllIMSProductCategory() {
		String ql = "select distinct new cn.cnyirui.ims.entity.IMSProductCategory(p.boxType)"
		        + " from IMSProduct as p where p.boxType is not null order by p.boxType";
		return findAll(ql, false, null, IMSProductCategory.class);

	}

	/**
	 * 所有产品
	 * 
	 * @return
	 */
	// public List<IMSProduct> findAllIMSProduct() {
	// String ql = "select e from IMSProduct as e order by e.name";
	// return findAll(ql, false, null, IMSProduct.class);
	// }

	/**
	 * 所有产品
	 * 
	 * @return
	 */
	public List<IMSProduct> findAllIMSProduct() {
//		String ql = "select distinct new cn.cnyirui.ims.entity.IMSProduct(e.name, e.boxType)"
//		        + " from IMSProduct as e where e.name is not null and e.boxType is not null order by e.name";
		String sql = "from  IMSProduct a  where not exists (select 1 from IMSProduct b "
				+ "where b.name = a.name and b.boxType = a.boxType and b.id < a.id ) "
				+ "and a.name is not null and a.boxType is not null order by a.name";
		return findAll(sql, false, null, IMSProduct.class);
	}

	/**
	 * 所有业务组织
	 * 
	 * @param parentId
	 * @return
	 */
	public List<IMSBusinessOrganization> findAllIMSBusinessOrganization(Long parentId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String ql = null;
		if (parentId != null) {
			
			ql = " select e from IMSBusinessOrganization as e where e.parentId = :parentId order by e.name";
			//ql = " select e from IMSBusinessOrganization as e order by e.name";
			//parameters.put("parentId", parentId);
		} else {
			ql = " select e from IMSBusinessOrganization as e where e.parentId is null order by e.name";
		}

		return findAll(ql, false, parameters, IMSBusinessOrganization.class);
	}

	/**
	 * 所有办事处
	 * 
	 * @param parentId
	 * @return
	 */
	public List<IMSOffice> findAllIMSOffice() {
		String ql = " select e from IMSOffice as e order by e.name";
		return findAll(ql, false, null, IMSOffice.class);
	}

	/**
	 * 所有门店
	 * 
	 * @param parentId
	 * @return
	 */
	public List<IMSStore> findAllIMSStore() {
		String ql = " select e from IMSStore as e order by e.name";
		return findAll(ql, false, null, IMSStore.class);
	}

	/**
	 * 所有导购员
	 * 
	 * @return
	 */
	public List<IMSSaler> findAllIMSSaler() {
		String ql = " select e from IMSSaler as e order by e.id";
		return findAll(ql, false, null, IMSSaler.class);
	}

	/**
	 * 所有员工
	 * 
	 * @return
	 */
	public List<IMSEmployee> findAllIMSEmployee() {
		String ql = " select e from IMSEmployee as e order by e.id";
		return findAll(ql, false, null, IMSEmployee.class);
	}

	/**
	 * 所有导购员工资数据
	 * 
	 * @param wageYearMonth
	 * @param wageVersion
	 * @return
	 */
	public List<IMSSalerWage> findAllIMSSalerWage(String wageYearMonth) {
		//String ql = " select e from IMSSalerWage as e where wageYearMonth = :wageYearMonth and wageVersion = :wageVersion";
		String ql = " select e from IMSSalerWage as e where wageYearMonth = :wageYearMonth ";
		//String ql = " select e from IMSSalerWage as e";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("wageYearMonth", wageYearMonth);
		//parameters.put("wageVersion", String.valueOf(wageVersion));
		return findAll(ql, false, parameters, IMSSalerWage.class);
	}

	/**
	 * 删除指定年月的订单数据
	 * 
	 * @param year
	 * @param month
	 */
	@Transactional(transactionManager = "imsTransactionManager")
	public void deleteAllSalesOrder(Integer year, Integer month) {
		String ql = "delete from  CRM_MKT_TERMINAL_SALES_I where extract(year from SALE_DATE) = :year"
		        + " and extract(month from SALE_DATE) = :month";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("year", year);
		parameters.put("month", month);
		deleteAll(ql, parameters);
	}
}
