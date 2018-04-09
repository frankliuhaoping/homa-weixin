package cn.cnyirui.framework.dao.search.filter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.exception.SearchException;
import cn.cnyirui.framework.utils.SessionUtils;

public final class SearchFilterHelper {
	/**
	 * 根据查询key和值生成Condition
	 *
	 * @param key 如 name_like
	 * @param value
	 * @return
	 */
	public static SearchFilter newCondition(final String key, final Object value) throws SearchException {
		return Condition.newCondition(key, value);
	}

	/**
	 * 根据查询属性、操作符和值生成Condition
	 *
	 * @param searchProperty
	 * @param operator
	 * @param value
	 * @return
	 */
	public static SearchFilter newCondition(final String searchProperty, final SearchOperator operator,
	        final Object value) {
		return Condition.newCondition(searchProperty, operator, value);
	}

	/**
	 * 拼or条件
	 *
	 * @param first
	 * @param others
	 * @return
	 */
	public static SearchFilter or(SearchFilter first, SearchFilter... others) {
		OrCondition orCondition = new OrCondition();
		orCondition.getOrFilters().add(first);
		if (ArrayUtils.isNotEmpty(others)) {
			orCondition.getOrFilters().addAll(Arrays.asList(others));
		}
		return orCondition;
	}

	/**
	 * 拼and条件
	 *
	 * @param first
	 * @param others
	 * @return
	 */
	public static SearchFilter and(SearchFilter first, SearchFilter... others) {
		AndCondition andCondition = new AndCondition();
		andCondition.getAndFilters().add(first);
		if (ArrayUtils.isNotEmpty(others)) {
			andCondition.getAndFilters().addAll(Arrays.asList(others));
		}
		return andCondition;
	}

	/**
	 * 按属性、操作符查找指定的searchFilter
	 * 
	 * @param searchFilters
	 * @param searchProperty
	 * @param operator
	 * @return
	 */
	public static List<SearchFilter> findSearchFilter(Collection<SearchFilter> searchFilters,
	        final String searchProperty, final SearchOperator operator) {
		List<SearchFilter> result = new ArrayList<SearchFilter>();
		for (SearchFilter searchFilter : searchFilters) {
			if (searchFilter instanceof OrCondition) {
				OrCondition orCondition = (OrCondition) searchFilter;
				result.addAll(findSearchFilter(orCondition.getOrFilters(), searchProperty, operator));
			}
			if (searchFilter instanceof AndCondition) {
				AndCondition andCondition = (AndCondition) searchFilter;
				result.addAll(findSearchFilter(andCondition.getAndFilters(), searchProperty, operator));
			}
			if (searchFilter instanceof Condition) {
				Condition condition = (Condition) searchFilter;
				if (condition.getSearchProperty().equalsIgnoreCase(searchProperty)
				        && (operator == null || condition.getOperator().equals(operator))) {
					result.add(searchFilter);
				}
			}
		}
		return result;
	}

	/**
	 * 删除指定的searchFilter
	 * 
	 * @param searchFilters
	 * @param searchProperty
	 * @param operator
	 */
	public static void removeSearchFilter(Collection<SearchFilter> searchFilters, final String searchProperty,
	        final SearchOperator operator) {
		Iterator<SearchFilter> iterator = searchFilters.iterator();
		while (iterator.hasNext()) {
			SearchFilter searchFilter = iterator.next();
			if (searchFilter instanceof OrCondition) {
				OrCondition orCondition = (OrCondition) searchFilter;
				removeSearchFilter(orCondition.getOrFilters(), searchProperty, operator);
			}
			if (searchFilter instanceof AndCondition) {
				AndCondition andCondition = (AndCondition) searchFilter;
				removeSearchFilter(andCondition.getAndFilters(), searchProperty, operator);
			}
			if (searchFilter instanceof Condition) {
				Condition condition = (Condition) searchFilter;
				if (condition.getSearchProperty().equalsIgnoreCase(searchProperty)
				        && condition.getOperator().equals(operator)) {
					iterator.remove();
				}
			}
		}
	}

	/**
	 * 将日期时间查询值2015-10-26 00:00:00包装成 2015-10-26 23:59:59
	 * 
	 * @param searchFilters
	 * @param searchProperty
	 */
	public static void wrapperDateTimeSearchFilter(Collection<SearchFilter> searchFilters,
	        final String searchProperty) {
		List<SearchFilter> searchFilterList = findSearchFilter(searchFilters, searchProperty, SearchOperator.lte);
		for (SearchFilter searchFilter : searchFilterList) {
			if (searchFilter.asCondition().getValue() instanceof String) {
				String value = searchFilter.asCondition().getValue().toString() + " 23:59:59";
				try {
					searchFilter.asCondition().setValue(DateUtils.parseDate(value, "yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		searchFilterList = findSearchFilter(searchFilters, searchProperty, SearchOperator.gte);
		for (SearchFilter searchFilter : searchFilterList) {
			if (searchFilter.asCondition().getValue() instanceof String) {
				String value = searchFilter.asCondition().getValue().toString();
				try {
					searchFilter.asCondition().setValue(DateUtils.parseDate(value, "yyyy-MM-dd"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 添加可查看组织架构的数据限制条件
	 * 
	 * @param searchable
	 * @param ql
	 * @param joinTableAlias
	 */
	public static void addOrganizationLimitSearchFilter(Searchable searchable, StringBuilder ql,
	        String joinTableAlias) {
		String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
		searchable.removeSearchFilter("organization.id", SearchOperator.eq);

		addOrganizationLimitSearchFilter(organizationId, ql, joinTableAlias);
	}

	/**
	 * 添加可查看组织架构的数据限制条件
	 * 
	 * @param organizationId
	 * @param ql
	 * @param joinTableAlias
	 */
	public static void addOrganizationLimitSearchFilter(String organizationId, StringBuilder ql,
	        String joinTableAlias) {
		if (ql == null || StringUtils.isEmpty(joinTableAlias)) {
			return;
		}
		if (StringUtils.isEmpty(organizationId)) {
			ql.append(", organization_tree_temp t WHERE ")
			        .append(joinTableAlias)
			        .append(".organizationId = t.organizationId")
			        .append(" AND t.callId = '").append(SessionUtils.getSession().getId().toString()).append("'");
		} else {
			String sql = "with t as (select id organizationId, parentNames from organization start with id = '"
			        + organizationId
			        + "' CONNECT BY PRIOR id = parentId) ";
			ql.insert(0, sql);
			ql.append(", t WHERE ")
			        .append(joinTableAlias)
			        .append(".organizationId = t.organizationId");
		}
	}

}
