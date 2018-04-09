package cn.cnyirui.framework.dao.search.utils;

import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ELEMENT_COLLECTION;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_MANY;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_ONE;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_MANY;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_ONE;
import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.PluralAttribute;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.transform.Transformers;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.util.Assert;

import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * copy from spring data
 * 
 * @author pengzhihua
 *
 */
public abstract class QueryUtils {
	private final static String QUERY_STRING = "SELECT s FROM %s as s";
	private static final Map<PersistentAttributeType, Class<? extends Annotation>> ASSOCIATION_TYPES;

	static {
		Map<PersistentAttributeType, Class<? extends Annotation>> persistentAttributeTypes = new HashMap<PersistentAttributeType, Class<? extends Annotation>>();
		persistentAttributeTypes.put(ONE_TO_ONE, OneToOne.class);
		persistentAttributeTypes.put(ONE_TO_MANY, null);
		persistentAttributeTypes.put(MANY_TO_ONE, ManyToOne.class);
		persistentAttributeTypes.put(MANY_TO_MANY, null);
		persistentAttributeTypes.put(ELEMENT_COLLECTION, null);

		ASSOCIATION_TYPES = Collections.unmodifiableMap(persistentAttributeTypes);
	}

	@SuppressWarnings("unchecked")
	public static <T> Expression<T> toExpressionRecursively(From<?, ?> from, PropertyPath property) {

		Bindable<?> propertyPathModel = null;
		Bindable<?> model = from.getModel();
		String segment = property.getSegment();

		if (model instanceof ManagedType) {

			/*
			 * Required to keep support for EclipseLink 2.4.x. TODO: Remove once
			 * we drop that (probably Dijkstra M1) See:
			 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=413892
			 */
			propertyPathModel = (Bindable<?>) ((ManagedType<?>) model).getAttribute(segment);
		} else {
			propertyPathModel = from.get(segment).getModel();
		}

		if (requiresJoin(propertyPathModel, model instanceof PluralAttribute) && !isAlreadyFetched(from, segment)) {
			Join<?, ?> join = getOrCreateJoin(from, segment);
			return (Expression<T>) (property.hasNext() ? toExpressionRecursively(join, property.next()) : join);
		} else {
			Path<Object> path = from.get(segment);
			return (Expression<T>) (property.hasNext() ? toExpressionRecursively(path, property.next()) : path);
		}
	}

	/**
	 * Returns whether the given {@code propertyPathModel} requires the creation
	 * of a join. This is the case if we find a non-optional association.
	 * 
	 * @param propertyPathModel
	 *            must not be {@literal null}.
	 * @param for
	 * @return
	 */
	private static boolean requiresJoin(Bindable<?> propertyPathModel, boolean forPluralAttribute) {

		if (propertyPathModel == null && forPluralAttribute) {
			return true;
		}

		if (!(propertyPathModel instanceof Attribute)) {
			return false;
		}

		Attribute<?, ?> attribute = (Attribute<?, ?>) propertyPathModel;

		if (!ASSOCIATION_TYPES.containsKey(attribute.getPersistentAttributeType())) {
			return false;
		}

		Class<? extends Annotation> associationAnnotation = ASSOCIATION_TYPES
		        .get(attribute.getPersistentAttributeType());

		if (associationAnnotation == null) {
			return true;
		}

		Member member = attribute.getJavaMember();

		if (!(member instanceof AnnotatedElement)) {
			return true;
		}

		Annotation annotation = AnnotationUtils.getAnnotation((AnnotatedElement) member, associationAnnotation);
		return annotation == null ? true : (Boolean) AnnotationUtils.getValue(annotation, "optional");
	}

	static Expression<Object> toExpressionRecursively(Path<Object> path, PropertyPath property) {

		Path<Object> result = path.get(property.getSegment());
		return property.hasNext() ? toExpressionRecursively(result, property.next()) : result;
	}

	/**
	 * Returns an existing join for the given attribute if one already exists or
	 * creates a new one if not.
	 * 
	 * @param from
	 *            the {@link From} to get the current joins from.
	 * @param attribute
	 *            the {@link Attribute} to look for in the current joins.
	 * @return will never be {@literal null}.
	 */
	private static Join<?, ?> getOrCreateJoin(From<?, ?> from, String attribute) {

		for (Join<?, ?> join : from.getJoins()) {

			boolean sameName = join.getAttribute().getName().equals(attribute);

			if (sameName && join.getJoinType().equals(JoinType.LEFT)) {
				return join;
			}
		}

		return from.join(attribute, JoinType.LEFT);
	}

	/**
	 * Return whether the given {@link From} contains a fetch declaration for
	 * the attribute with the given name.
	 * 
	 * @param from
	 *            the {@link From} to check for fetches.
	 * @param attribute
	 *            the attribute name to check.
	 * @return
	 */
	private static boolean isAlreadyFetched(From<?, ?> from, String attribute) {

		for (Fetch<?, ?> f : from.getFetches()) {

			boolean sameName = f.getAttribute().getName().equals(attribute);

			if (sameName && f.getJoinType().equals(JoinType.LEFT)) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasCollectionProperty(PropertyPath property) {
		while (property.hasNext()) {
			if (property.isCollection()) {
				return true;
			}
			property = property.next();
		}
		return false;
	}

	public static String getQueryString(String entityClassName) {
		String queryString = String.format(QUERY_STRING, StringUtils.capitalize(entityClassName));
		return queryString;
	}

	/**
	 * 生成统计记录数的语句
	 * 
	 * @param ql
	 * @return
	 */
	private static String getCountQlFromQl(String ql) {
		if (ql == null) {
			return null;
		}
		boolean hasGroupBy = StringUtils.indexOfIgnoreCase(ql, "group by") != -1;
		boolean hasWith = StringUtils.indexOfIgnoreCase(ql, "with") != -1;
		if (hasGroupBy || hasWith) {
			return "select count(*) from (" + ql + ") countAlias";
		} else {
			int selectStart = StringUtils.indexOfIgnoreCase(ql, "select");
			int fromStart = StringUtils.indexOfIgnoreCase(ql, "from");

			if (selectStart != -1 && fromStart != -1) {
				String columns = StringUtils
				        .trim(StringUtils.substring(ql, selectStart + "select".length(), fromStart));
				int firstColumnEnd = StringUtils.indexOf(columns, ",");
				if (firstColumnEnd != -1) {
					columns = columns.substring(0, firstColumnEnd);
					firstColumnEnd = StringUtils.indexOf(columns, " ");
					if (firstColumnEnd != -1) {
						columns = columns.substring(0, firstColumnEnd);
					}
				}
				columns = StringUtils.replace(columns, "*", "id");
				return "select count(" + columns + ") " + ql.substring(fromStart);
			}
			return null;
		}
	}

	/**
	 * 判断 searchable的查询值是否已经按searchEntityClass对应属性类型进行转换
	 * 
	 * @param searchable
	 * @param searchEntityClass
	 */
	public static void assertConverted(Searchable searchable, Class<?> searchEntityClass) {
		if (searchable != null && !searchable.isConverted() && searchEntityClass != null) {
			searchable.convert(searchEntityClass);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends BaseEntity> Page<T> findAll(EntityManager em, Searchable searchable,
	        Class<T> searchEntityClass) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(searchEntityClass);
		Root<T> root = criteriaQuery.from(searchEntityClass);
		criteriaQuery.where(SearchableUtils.toPredicates(searchable, root, criteriaQuery, builder));
		if (searchable.hashSort()) {
			criteriaQuery.orderBy(toOrders(searchable.getSort(), root, builder));
		}
		Query query = em.createQuery(criteriaQuery);
		SearchableUtils.setPageable(query, searchable);

		List<T> content = query.getResultList();
		// 记录数
		long total = searchable != null && searchable.hasPageable() ? count(em, searchable, searchEntityClass)
		        : content.size();
		if (!searchable.hasPageable()) {
			return new PageImpl<T>(content);
		} else {
			return new PageImpl<T>(content, searchable.getPageable(), total);
		}
	}

	public static <T extends BaseEntity> long count(EntityManager em, Searchable searchable,
	        Class<T> searchEntityClass) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(searchEntityClass);
		;
		criteriaQuery.where(SearchableUtils.toPredicates(searchable, root, criteriaQuery, builder));
		if (criteriaQuery.isDistinct()) {
			criteriaQuery.select(builder.countDistinct(root));
		} else {
			criteriaQuery.select(builder.count(root));
		}
		Query query = em.createQuery(criteriaQuery);
		return (Long) query.getSingleResult();
	}

	/**
	 * ql查询
	 * 
	 * @param em
	 * @param ql
	 * @param searchCallback
	 * @param searchable
	 * @param searchEntityClass
	 * @param nativeSql
	 *            是否是原生sql
	 * @param resultClass
	 *            如果需要返回Map，使用Map.class，好像只能原生sql方式才能返回Map
	 * @return
	 */
	public static <S> Page<S> findAll(EntityManager em, String ql, Searchable searchable, Class<?> searchEntityClass,
	        boolean nativeSql, Class<S> resultClass) {
		return findAll(em, ql, null, searchable, searchEntityClass, nativeSql, resultClass);
	}

	/**
	 * ql查询
	 * 
	 * @param em
	 * @param ql
	 * @param countQl
	 * @param searchCallback
	 * @param searchable
	 * @param searchEntityClass
	 * @param nativeSql
	 *            是否是原生sql
	 * @param resultClass
	 *            如果需要返回Map，使用Map.class，好像只能原生sql方式才能返回Map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S> Page<S> findAll(EntityManager em, String ql, String countQl, Searchable searchable,
	        Class<?> searchEntityClass, boolean nativeSql, Class<S> resultClass) {
		Assert.hasText(ql, "The ql must not be null!");
		assertConverted(searchable, searchEntityClass);
		StringBuilder s = new StringBuilder(ql);
		SearchableUtils.toWhereQL(s, searchable);
		SearchableUtils.toOrderQL(s, searchable);
		Query query = null;
		if (nativeSql) {
			if (Map.class.isAssignableFrom(resultClass)) {
				query = em.createNativeQuery(s.toString());
				query.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			} else {
				query = em.createNativeQuery(s.toString(), resultClass);
			}
		} else {
			query = em.createQuery(s.toString());
		}
		SearchableUtils.setQueryParameter(query, searchable);
		SearchableUtils.setPageable(query, searchable);

		List<S> content = query.getResultList();
		if (countQl == null) {
			countQl = getCountQlFromQl(ql);
		}
		// 记录数
		long total = searchable != null && searchable.hasPageable()
		        ? count(em, countQl, searchable, searchEntityClass, nativeSql) : content.size();
		if (searchable == null) {
			return new PageImpl<S>(content);
		} else {
			return new PageImpl<S>(content, searchable.getPageable(), total);
		}
	}

	/**
	 * 统计记录数
	 * 
	 * @param em
	 * @param ql
	 * @param searchCallback
	 * @param searchable
	 * @param searchEntityClass
	 * @param nativeSql
	 * @return
	 */
	public static long count(EntityManager em, String ql, Searchable searchable, Class<?> searchEntityClass,
	        boolean nativeSql) {
		Assert.hasText(ql, "The ql must not be null!");
		assertConverted(searchable, searchEntityClass);
		StringBuilder s = new StringBuilder(ql);
		SearchableUtils.toWhereQL(s, searchable);
		Query query = null;
		if (nativeSql) {
			query = em.createNativeQuery(s.toString());
		} else {
			query = em.createQuery(s.toString());
		}
		SearchableUtils.setQueryParameter(query, searchable);
		if (nativeSql) {
			return ((BigDecimal) query.getSingleResult()).longValue();
		} else {
			return (Long) query.getSingleResult();
		}
	}
}
