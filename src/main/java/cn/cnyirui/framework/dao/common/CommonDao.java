package cn.cnyirui.framework.dao.common;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.utils.QueryUtils;
import cn.cnyirui.framework.dao.search.utils.SearchableUtils;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.utils.SpringUtils;

@Repository
public class CommonDao {

	@PersistenceContext(unitName = "jpa.yirui.homaweixin")
	private EntityManager entityManager;

	public <T extends BaseEntity> T findOne(String entityClassName, String id) {
		BaseDao<T> baseDao = SpringUtils.getDaoBean(entityClassName);
		if (baseDao != null) {
			return baseDao.findOne(id);
		} else {
			Searchable searchable = Searchable.newSearchable();
			searchable.addSearchFilter("id", SearchOperator.eq, id);
			Page<T> page = QueryUtils.findAll(entityManager, QueryUtils.getQueryString(entityClassName),
			        searchable, null, false, null);
			if (page.hasContent()) {
				return page.getContent().get(0);
			}
		}
		return null;
	}

	public <T extends BaseEntity> T findOne(String entityClassName, Searchable searchable) {
		BaseDao<T> baseDao = SpringUtils.getDaoBean(entityClassName);
		if (baseDao != null) {
			return baseDao.findOne(searchable);
		} else {
			Page<T> page = findAll(entityClassName, searchable);
			if (page.hasContent()) {
				return page.getContent().get(0);
			}
			return null;
		}
	}

	public <T extends BaseEntity> Page<T> findAll(String entityClassName, Searchable searchable) {
		BaseDao<T> baseDao = SpringUtils.getDaoBean(entityClassName);
		if (baseDao != null) {
			return baseDao.findAll(searchable);
		} else {
			return QueryUtils.findAll(entityManager, QueryUtils.getQueryString(entityClassName),
			        searchable, null, false, null);
		}
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional
	public <T extends BaseEntity> T save(T entity) {
		BaseDao<T> baseDao = SpringUtils.getDaoBean(entity.getClass().getSimpleName());
		if (baseDao != null) {
			return baseDao.save(entity);
		} else {
			if (entity.isNew()) {
				entityManager.persist(entity);
				return entity;
			} else {
				return entityManager.merge(entity);
			}
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param entityClassName
	 * @param ids
	 */
	@Transactional
	public void delete(String entityClassName, String[] ids) {
		if (ArrayUtils.isEmpty(ids)) {
			return;
		}
		Searchable searchable = Searchable.newSearchable();
		searchable.addSearchFilter("id", SearchOperator.in, Arrays.asList(ids));

		List<?> list = findAll(entityClassName, searchable).getContent();
		for (int i = 0; i < list.size(); i++) {
			entityManager.remove(list.get(i));
		}
	}

	/**
	 * 逻辑删除所有数据
	 * 
	 * @param entityClassName
	 */
	@Transactional
	public void logicDeleteAll(String entityClassName, Searchable searchable) {
		StringBuilder s = new StringBuilder(String.format("update %s set deleted = 1", entityClassName));
		SearchableUtils.toWhereQL(s, searchable);
		Query query = entityManager.createQuery(s.toString());
		SearchableUtils.setQueryParameter(query, searchable);
		query.executeUpdate();
	}

	/**
	 * 数据是否存在
	 * 
	 * @param entityClassName
	 * @param searchable
	 * @return
	 */
	public <T extends BaseEntity> boolean exists(String entityClassName, Searchable searchable) {
		T t = findOne(entityClassName, searchable);
		return t != null;
	}

}
