package cn.cnyirui.framework.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.utils.QueryUtils;
import cn.cnyirui.framework.dao.search.utils.SearchableUtils;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.base.LogicDeleteable;

public class BaseDaoImpl<T extends BaseEntity> extends
        SimpleJpaRepository<T, String> implements BaseDao<T> {

	protected final EntityManager entityManager;
	protected final Class<T> entityClass;
	protected final String entityName;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 构造函数
	 * 
	 * @param entityInformation
	 * @param entityManager
	 */
	public BaseDaoImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityClass = entityInformation.getJavaType();
		this.entityName = entityInformation.getEntityName();
	}

	/**
	 * 扩展delete，实现逻辑删除
	 *
	 * @param t 实体
	 */
	@Override
	public void delete(T t) {
		if (t == null) {
			return;
		}
		if (LogicDeleteable.class.isAssignableFrom(t.getClass())) {
			((LogicDeleteable) t).markDeleted();
			save(t);
		} else {
			super.delete(t);
		}
	}

	@Transactional
	public void delete(String[] ids) {
		if (ArrayUtils.isNotEmpty(ids)) {
			List<T> entities = findAll(Arrays.asList(ids));
			delete(entities);
		}
	}

	public T findOne(Searchable searchable) {
		Specification<T> specification = SearchableUtils.toSpecification(searchable);
		return findOne(specification);
	}

	public Page<T> findAll(Searchable searchable) {
		QueryUtils.assertConverted(searchable, entityClass);
		Specification<T> specification = SearchableUtils.toSpecification(searchable);
		if (searchable.hasPageable()) {
			return findAll(specification, searchable.getPageable());
		} else if (searchable.hashSort()) {
			return new PageImpl<T>(findAll(specification, searchable.getSort()));
		} else {
			return new PageImpl<T>(findAll(specification));
		}

	}

	@SuppressWarnings("unchecked")
	public Page<T> findAll(String ql, Searchable searchable) {
		return (Page<T>) findAll(ql, searchable, false, null);
	}

	public long count(Searchable searchable) {
		Specification<T> specification = SearchableUtils.toSpecification(searchable);
		return count(specification);
	}

	public Page<?> findAll(String ql, Searchable searchable, boolean nativeSql, Class<?> resultClass) {
		return findAll(ql, null, searchable, nativeSql, resultClass);
	}

	@Override
	public Page<?> findAll(String ql, String countQl, Searchable searchable, boolean nativeSql, Class<?> resultClass) {
		if (StringUtils.isEmpty(ql)) {
			return findAll(searchable);
		} else {
			return QueryUtils.findAll(getEntityManager(), ql, countQl, searchable, entityClass, nativeSql, resultClass);
		}
	}

	public boolean exists(Searchable searchable) {
		T t = findOne(searchable);
		return t != null;
	}

	private Query getQuery(String ql, boolean nativeSql) {
		Query query = null;
		if (nativeSql) {
			query = entityManager.createNativeQuery(ql);
		} else {
			query = entityManager.createQuery(ql);
		}
		return query;
	}

	@Override
	@Transactional
	public int executeUpdate(String ql, Searchable searchable, boolean nativeSql) {
		StringBuilder sb = new StringBuilder(ql);
		SearchableUtils.toWhereQL(sb, searchable);
		Query query = getQuery(sb.toString(), nativeSql);
		SearchableUtils.setQueryParameter(query, searchable);
		return query.executeUpdate();
	}

	public Object getScalarValue(String ql, Searchable searchable, boolean nativeSql) {
		StringBuilder sb = new StringBuilder(ql);
		SearchableUtils.toWhereQL(sb, searchable);
		Query query = getQuery(sb.toString(), nativeSql);
		SearchableUtils.setQueryParameter(query, searchable);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
