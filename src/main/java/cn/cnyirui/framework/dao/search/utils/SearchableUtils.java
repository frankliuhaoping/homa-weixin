package cn.cnyirui.framework.dao.search.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.exception.InvalidSearchPropertyException;
import cn.cnyirui.framework.dao.search.exception.InvalidSearchValueException;
import cn.cnyirui.framework.dao.search.filter.AndCondition;
import cn.cnyirui.framework.dao.search.filter.Condition;
import cn.cnyirui.framework.dao.search.filter.OrCondition;
import cn.cnyirui.framework.dao.search.filter.SearchFilter;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.utils.SpringUtils;

/**
 * Searchable工具类<br/>
 * 将searchable生成ql或者Specification或者Predicat
 * 
 * @author pengzhihua
 *
 */
public class SearchableUtils {
	private static Logger logger = LoggerFactory.getLogger(SearchableUtils.class);

	private static ConversionService conversionService;
	/**
	 * query参数名的前缀
	 */
	private static final String paramPrefix = "param_";

	/**
	 * 转换成QL的where子句
	 * 
	 * @param ql
	 * @param searchable
	 */
	public static void toWhereQL(StringBuilder ql, Searchable searchable) {
		if (searchable == null || !searchable.hasSearchFilter()) {
			return;
		}
		String andString = " and ";
		boolean hasWhere = StringUtils.indexOfIgnoreCase(ql, "where") != -1;
		if (!hasWhere) {
			andString = "";
		}

		StringBuilder whereQl = new StringBuilder(!hasWhere ? " where " : "");
		int paramIndex = 1;
		for (SearchFilter searchFilter : searchable.getSearchFilters()) {
			if (searchFilter instanceof Condition) {
				Condition condition = (Condition) searchFilter;
				if (condition.getOperator() == SearchOperator.custom) {
					continue;
				}
			}
			whereQl.append(andString);
			andString = " and ";
			paramIndex = toConditionQL(whereQl, paramIndex, searchFilter);
		}

		int groupByIndex = StringUtils.indexOfIgnoreCase(ql, "group by");
		int orderByIndex = StringUtils.indexOfIgnoreCase(ql, "order by");
		int startWithIndex = StringUtils.indexOfIgnoreCase(ql, "start with");
		int countAliasIndex = StringUtils.indexOfIgnoreCase(ql, ") countAlias");
		boolean startWith = StringUtils.startsWith(StringUtils.trim(ql.toString()), "with")
		        || StringUtils.indexOfIgnoreCase(ql, "(with") != -1;
		if (groupByIndex != -1) {
			ql.insert(groupByIndex, whereQl.toString() + " ");
		} else if (orderByIndex != -1) {
			ql.insert(orderByIndex, whereQl.toString() + " ");
		} else if (countAliasIndex != -1) {
			ql.insert(countAliasIndex, whereQl.toString() + " ");
		} else if (startWithIndex != -1 && !startWith) {
			ql.insert(startWithIndex, whereQl.toString() + " ");
		} else {
			ql.append(whereQl);
		}

	}

	/**
	 * 生成condition的ql
	 * 
	 * @param ql
	 * @param paramIndex
	 * @param searchFilter
	 * @return
	 */
	private static int toConditionQL(StringBuilder ql, int paramIndex, SearchFilter searchFilter) {
		boolean needAppendBracket = searchFilter instanceof OrCondition || searchFilter instanceof AndCondition;
		if (needAppendBracket) {
			ql.append("(");
		}
		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			// 自定义条件
			String entityProperty = condition.getEntityProperty();
			String operatorStr = condition.getOperatorStr();
			// 实体名称
			ql.append(entityProperty);
			// 操作符
			// 1、如果是自定义查询符号，则使用SearchPropertyMappings中定义的默认的操作符
			ql.append(" ");
			ql.append(operatorStr);
			if (!condition.isUnaryFilter()) {
				ql.append(" :");
				ql.append(paramPrefix);
				ql.append(paramIndex++);
				return paramIndex;
			}
		} else if (searchFilter instanceof OrCondition) {
			boolean isFirst = true;
			for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
				if (!isFirst) {
					ql.append(" or ");
				}
				paramIndex = toConditionQL(ql, paramIndex, orSearchFilter);
				isFirst = false;
			}
		} else if (searchFilter instanceof AndCondition) {
			boolean isFirst = true;
			for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
				if (!isFirst) {
					ql.append(" and ");
				}
				paramIndex = toConditionQL(ql, paramIndex, andSearchFilter);
				isFirst = false;
			}
		}

		if (needAppendBracket) {
			ql.append(")");
		}
		return paramIndex;
	}

	/**
	 * 设置query的参数值
	 * 
	 * @param query
	 * @param searchable
	 */
	public static void setQueryParameter(Query query, Searchable searchable) {
		if (searchable != null && searchable.hasSearchFilter()) {
			int paramIndex = 1;
			for (SearchFilter searchFilter : searchable.getSearchFilters()) {
				paramIndex = setQueryParameter(query, searchFilter, paramIndex);
			}
		}
	}

	/**
	 * 设置query的参数值
	 * 
	 * @param query
	 * @param searchFilter
	 * @param paramIndex
	 * @return
	 */
	private static int setQueryParameter(Query query, SearchFilter searchFilter, int paramIndex) {
		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			if (condition.getOperator() == SearchOperator.custom) {
				return paramIndex;
			}
			if (condition.isUnaryFilter()) {
				return paramIndex;
			}
			logger.debug(paramPrefix + paramIndex + ": " + formatValue(condition, condition.getValue()));
			query.setParameter(paramPrefix + paramIndex++, formatValue(condition, condition.getValue()));

		} else if (searchFilter instanceof OrCondition) {
			for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
				paramIndex = setQueryParameter(query, orSearchFilter, paramIndex);
			}
		} else if (searchFilter instanceof AndCondition) {
			for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
				paramIndex = setQueryParameter(query, andSearchFilter, paramIndex);
			}
		}
		return paramIndex;
	}

	private static Object formatValue(Condition condition, Object value) {
		SearchOperator operator = condition.getOperator();
		if (operator == SearchOperator.like || operator == SearchOperator.notLike) {
			return "%" + value + "%";
		}
		if (operator == SearchOperator.prefixLike || operator == SearchOperator.prefixNotLike) {
			return value + "%";
		}
		if (operator == SearchOperator.suffixLike || operator == SearchOperator.suffixNotLike) {
			return "%" + value;
		}
		return value;
	}

	/**
	 * 设置分页参数
	 * 
	 * @param query
	 * @param searchable
	 */
	public static void setPageable(Query query, Searchable searchable) {
		if (searchable != null && searchable.hasPageable()) {
			Pageable pageable = searchable.getPageable();
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
	}

	/**
	 * 生成排序的QL
	 * 
	 * @param ql
	 * @param searchable
	 */
	public static void toOrderQL(StringBuilder ql, Searchable searchable) {
		if (searchable != null && searchable.hashSort()) {
			ql.append(" order by ");
			for (Sort.Order order : searchable.getSort()) {
				ql.append(String.format("%s %s, ", order.getProperty(), order.getDirection().name().toLowerCase()));
			}
			ql.delete(ql.length() - 2, ql.length());
		}
	}

	/**
	 * searchFilter转换成Predicate
	 * 
	 * @param predicates
	 * @param searchFilter
	 * @param root
	 * @param builder
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> void toPredicate(List<Predicate> predicates, SearchFilter searchFilter, Root<T> root,
	        CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			// 自定义条件
			String entityProperty = condition.getEntityProperty();
			SearchOperator operator = condition.getOperator();
			Object value = formatValue(condition, condition.getValue());

			PropertyPath property = PropertyPath.from(entityProperty, root.getJavaType());
			if (QueryUtils.hasCollectionProperty(property)) {
				query.distinct(true);
			}
			Expression expression = QueryUtils.toExpressionRecursively(root, property);

			Predicate predicate = null;
			switch (operator) {
			case eq:
				predicate = builder.equal(expression, value);
				break;
			case ne:
				predicate = builder.notEqual(expression, value);
				break;
			case like:
			case prefixLike:
			case suffixLike:
				predicate = builder.like(expression, value.toString());
				break;
			case notLike:
			case prefixNotLike:
			case suffixNotLike:
				predicate = builder.notLike(expression, value.toString());
				break;
			case gt:
				predicate = builder.greaterThan(expression, (Comparable) value);
				break;
			case lt:
				predicate = builder.lessThan(expression, (Comparable) value);
				break;
			case gte:
				predicate = builder.greaterThanOrEqualTo(expression, (Comparable) value);
				break;
			case lte:
				predicate = builder.lessThanOrEqualTo(expression, (Comparable) value);
				break;
			case isNull:
				predicate = builder.isNull(expression);
				break;
			case isNotNull:
				predicate = builder.isNotNull(expression);
				break;
			case in:
			case notIn:
				if (value instanceof Collection) {
					In in = builder.in(expression);
					Collection collection = (Collection) value;
					Iterator iterator = collection.iterator();
					while (iterator.hasNext()) {
						in.value(iterator.next());
					}
					predicate = in;
				}
				if (value instanceof String) {
					In in = builder.in(expression);
					String[] values = StringUtils.split(value.toString(), ",");
					for (String string : values) {
						in.value(string);
					}
					predicate = in;
				}
				if (condition.getOperator() == SearchOperator.notIn) {
					predicate = builder.not(predicate);
				}
				break;
			case custom:
				break;
			}
			predicates.add(predicate);
		} else if (searchFilter instanceof OrCondition) {
			List<Predicate> orPredicates = new ArrayList<Predicate>();
			for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
				toPredicate(orPredicates, orSearchFilter, root, query, builder);
			}
			if (!orPredicates.isEmpty()) {
				predicates.add(builder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
			}
		} else if (searchFilter instanceof AndCondition) {
			List<Predicate> andPredicates = new ArrayList<Predicate>();
			for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
				toPredicate(andPredicates, andSearchFilter, root, query, builder);
			}
			if (!andPredicates.isEmpty()) {
				predicates.add(builder.and(andPredicates.toArray(new Predicate[andPredicates.size()])));
			}
		}
	}

	/**
	 * searchable转换成Specification
	 * 
	 * @param searchable
	 * @return
	 */
	public static <T> Specification<T> toSpecification(final Searchable searchable) {
		return new Specification<T>() {

			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Collection<SearchFilter> searchFilters = searchable.getSearchFilters();
				if (!searchFilters.isEmpty()) {
					return builder.and(SearchableUtils.toPredicates(searchable, root, query, builder));
				}
				return builder.conjunction();
			}
		};
	}

	/**
	 * searchable转换成Predicates
	 * 
	 * @param searchable
	 * @param root
	 * @param query
	 * @param builder
	 * @return
	 */
	public static <T> Predicate[] toPredicates(final Searchable searchable, Root<T> root, CriteriaQuery<?> query,
	        CriteriaBuilder builder) {
		Collection<SearchFilter> searchFilters = searchable.getSearchFilters();
		if (!searchFilters.isEmpty()) {
			List<Predicate> predicates = Lists.newArrayList();
			for (SearchFilter searchFilter : searchable.getSearchFilters()) {
				toPredicate(predicates, searchFilter, root, query, builder);
			}
			// 将所有条件用 and 联合起来
			if (!predicates.isEmpty()) {
				return predicates.toArray(new Predicate[predicates.size()]);
			}
		}
		return new Predicate[0];
	}

	private static ConversionService getConversionService() {
		if (conversionService == null) {
			conversionService = SpringUtils.getBean(ConversionService.class);
		}
		return conversionService;
	}

	/**
	 * 将searchFilter的SearchValue转换成对应的类型
	 * 
	 * @param searchable
	 * @param searchProperty
	 * @param operator
	 * @param targetType
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void convertSearchValue(Searchable searchable, String searchProperty, SearchOperator operator,
	        Class<?> targetType) {
		List<SearchFilter> searchFilters = SearchFilterHelper.findSearchFilter(searchable.getSearchFilters(),
		        searchProperty, operator);
		for (SearchFilter searchFilter : searchFilters) {
			if (searchFilter instanceof Condition) {
				Condition condition = (Condition) searchFilter;

				Object value = condition.getValue();
				Object newValue = null;
				boolean isCollection = value instanceof Collection;
				boolean isArray = value != null && value.getClass().isArray();
				if (isCollection || isArray) {
					List<Object> list = Lists.newArrayList();
					if (isCollection) {
						list.addAll((Collection) value);
					} else {
						list = Lists.newArrayList(CollectionUtils.arrayToList(value));
					}
					int length = list.size();
					for (int i = 0; i < length; i++) {
						list.set(i, getConversionService().convert(list.get(i), targetType));
					}
					newValue = list;
				} else {
					newValue = getConversionService().convert(value, targetType);
				}
				condition.setValue(newValue);
			}
		}
	}

	/**
	 * 将searchable的SearchValue转换成实体对应的类型
	 * 
	 * @param searchable
	 * @param entityClass
	 */
	public static <T> void convertSearchValueToEntityValue(Searchable searchable, final Class<T> entityClass) {
		if (entityClass == null || searchable.isConverted()) {
			return;
		}
		Collection<SearchFilter> searchFilters = searchable.getSearchFilters();
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl(entityClass);
		beanWrapper.setAutoGrowNestedPaths(true);
		beanWrapper.setConversionService(getConversionService());

		for (SearchFilter searchFilter : searchFilters) {
			convertSearchValueToEntityValue(beanWrapper, searchFilter);
		}
	}

	/**
	 * 将searchFilter的SearchValue转换成实体对应的类型
	 * 
	 * @param beanWrapper
	 * @param searchFilter
	 */
	private static void convertSearchValueToEntityValue(BeanWrapperImpl beanWrapper, SearchFilter searchFilter) {
		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			convert(beanWrapper, condition);
			return;
		}
		if (searchFilter instanceof OrCondition) {
			for (SearchFilter orFilter : ((OrCondition) searchFilter).getOrFilters()) {
				convertSearchValueToEntityValue(beanWrapper, orFilter);
			}
			return;
		}
		if (searchFilter instanceof AndCondition) {
			for (SearchFilter andFilter : ((AndCondition) searchFilter).getAndFilters()) {
				convertSearchValueToEntityValue(beanWrapper, andFilter);
			}
			return;
		}

	}

	/**
	 * 转换查询值类型
	 * 
	 * @param beanWrapper
	 * @param condition
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void convert(BeanWrapperImpl beanWrapper, Condition condition) {
		String searchProperty = condition.getSearchProperty();
		// 自定义的也不转换
		if (condition.getOperator() == SearchOperator.custom) {
			return;
		}
		// 一元运算符不需要计算
		if (condition.isUnaryFilter()) {
			return;
		}
		String entityProperty = condition.getEntityProperty();
		Object value = condition.getValue();
		Object newValue = null;
		boolean isCollection = value instanceof Collection;
		boolean isArray = value != null && value.getClass().isArray();
		if (isCollection || isArray) {
			List<Object> list = Lists.newArrayList();
			if (isCollection) {
				list.addAll((Collection) value);
			} else {
				list = Lists.newArrayList(CollectionUtils.arrayToList(value));
			}
			int length = list.size();
			for (int i = 0; i < length; i++) {
				list.set(i, getConvertedValue(beanWrapper, searchProperty, entityProperty, list.get(i)));
			}
			newValue = list;
		} else {
			newValue = getConvertedValue(beanWrapper, searchProperty, entityProperty, value);
		}
		condition.setValue(newValue);
	}

	/**
	 * 转换后的值
	 * 
	 * @param beanWrapper
	 * @param searchProperty
	 * @param entityProperty
	 * @param value
	 * @return
	 */
	private static Object getConvertedValue(final BeanWrapperImpl beanWrapper, final String searchProperty,
	        final String entityProperty, final Object value) {
		Object newValue = null;
		try {
			String property = handlerCollectionProperty(beanWrapper, entityProperty);
			Class<?> clazz = beanWrapper.getPropertyType(property);
			if (clazz != null) {
				beanWrapper.setPropertyValue(property, value);
				newValue = beanWrapper.getPropertyValue(property);
			} else {
				logger.warn("在实体类[" + beanWrapper.getWrappedClass().getSimpleName()
				        + "]上找不到属性[" + entityProperty + "]！！");
				return value;
			}
		} catch (InvalidPropertyException e) {
			throw new InvalidSearchPropertyException(searchProperty, entityProperty, e);
		} catch (Exception e) {
			throw new InvalidSearchValueException(searchProperty, entityProperty, value, e);
		}
		return newValue;
	}

	/**
	 * 集合属性需要加下标
	 * 
	 * @param beanWrapper
	 * @param entityProperty
	 * @return
	 */
	private static String handlerCollectionProperty(final BeanWrapperImpl beanWrapper, final String entityProperty) {
		String[] entityPropertys = StringUtils.split(entityProperty, ".");
		StringBuilder sb = new StringBuilder();
		for (String property : entityPropertys) {
			sb.append(property);
			Class<?> clazz = beanWrapper.getPropertyType(sb.toString());
			if (clazz == null) {
				break;
			}
			if (clazz.isArray() || Iterable.class.isAssignableFrom(clazz)) {
				sb.append("[0]");
			}
			sb.append(".");
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}

	}

}
