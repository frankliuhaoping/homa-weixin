package cn.cnyirui.framework.dao.search;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import cn.cnyirui.framework.dao.search.exception.InvalidSearchPropertyException;
import cn.cnyirui.framework.dao.search.exception.InvalidSearchValueException;
import cn.cnyirui.framework.dao.search.exception.SearchException;
import cn.cnyirui.framework.dao.search.filter.SearchFilter;

/**
 * 查询条件接口
 * 
 */
public abstract class Searchable {

	/**
	 * 创建一个新的查询
	 *
	 * @return
	 */
	public static Searchable newSearchable() {
		return new SearchRequest();
	}

	/**
	 * 添加过滤条件 如key="parent.id_eq" value = 1
	 * 如果添加时不加操作符 默认是custom 即如key=parent 实际key是parent_custom
	 *
	 * @param key 如 name_like
	 * @param value 如果是in查询 多个值之间","分隔
	 * @return
	 */
	public abstract Searchable addSearchParam(final String key, final Object value) throws SearchException;

	/**
	 * 添加过滤条件
	 *
	 * @param searchProperty 查询的属性名
	 * @param operator 操作运算符
	 * @param value 值
	 */
	public abstract Searchable addSearchFilter(
	        final String searchProperty, final SearchOperator operator, final Object value) throws SearchException;

	/**
	 * 添加过滤条件
	 * 
	 * @param searchFilter
	 * @return
	 */
	public abstract Searchable addSearchFilter(final SearchFilter searchFilter);

	/**
	 * 添加多个and连接的过滤条件
	 *
	 * @param searchFilters
	 * @return
	 */
	public abstract Searchable addSearchFilters(final Collection<? extends SearchFilter> searchFilters);

	/**
	 * 添加多个or连接的过滤条件
	 *
	 * @param first 第一个
	 * @param others 其他
	 * @return
	 */
	public abstract Searchable or(final SearchFilter first, final SearchFilter... others);

	/**
	 * 添加多个and连接的过滤条件
	 *
	 * @param first
	 * @param others
	 * @return
	 */
	public abstract Searchable and(final SearchFilter first, final SearchFilter... others);

	/**
	 * 查找指定属性和操作符的过滤条件
	 * 
	 * @param searchProperty
	 * @param operator
	 * @return
	 */
	public abstract List<SearchFilter> findSearchFilter(final String searchProperty, final SearchOperator operator);

	/**
	 * 查找指定属性和操作符的过滤条件，返回符合条件的第一个
	 * 
	 * @param searchProperty
	 * @param operator
	 * @return
	 */
	public abstract SearchFilter findOneSearchFilter(final String searchProperty, final SearchOperator operator);

	/**
	 * 移除指定属性 和 操作符的过滤条件
	 * 
	 * @param searchProperty
	 * @param operator
	 * @return
	 */
	public abstract Searchable removeSearchFilter(final String searchProperty, final SearchOperator operator);

	/**
	 * 获取查询属性对应的值
	 * 
	 * @param searchProperty
	 * @param operator
	 * @return
	 */
	public abstract <T> T getValue(final String searchProperty, final SearchOperator operator);

	/**
	 * 设置查询属性对应的值
	 * 
	 * @param searchProperty
	 * @param operator
	 * @return
	 */
	public abstract void setValue(final String searchProperty, final SearchOperator operator, Object value);

	/**
	 * 把字符串类型的值转化为entity属性值
	 *
	 * @param entityClass
	 * @param <T>
	 */
	public abstract <T> Searchable convert(final Class<T> entityClass)
	        throws InvalidSearchValueException, InvalidSearchPropertyException;

	/**
	 * 标识为已经转换过了 避免多次转换
	 */
	public abstract Searchable markConverted();

	/**
	 * 是否已经转换过了 避免多次转换
	 *
	 * @return
	 */
	public abstract boolean isConverted();

	/**
	 * 是否有查询参数
	 *
	 * @return
	 */
	public abstract boolean hasSearchFilter();

	/**
	 * 获取查询过滤条件
	 *
	 * @return
	 */
	public abstract Collection<SearchFilter> getSearchFilters();

	/**
	 * 添加排序
	 * 
	 * @param sort
	 * @return
	 */
	public abstract Searchable addSort(final Sort sort);

	/**
	 * 添加排序
	 * 
	 * @param direction
	 * @param property
	 * @return
	 */
	public abstract Searchable addSort(final Sort.Direction direction, String property);

	/**
	 * 获取排序信息
	 *
	 * @return
	 */
	public abstract Sort getSort();

	/**
	 * 是否有排序
	 *
	 * @return
	 */
	public abstract boolean hashSort();

	/**
	 * 删除排序
	 */
	public abstract void removeSort();

	/**
	 * 设置分页
	 * 
	 * @param pageable
	 * @return
	 */

	public abstract Searchable setPageable(final Pageable pageable);

	/**
	 * @param pageNumber 分页页码 索引从 0 开始
	 * @param pageSize 每页大小
	 * @return
	 */
	public abstract Searchable setPageable(final int pageNumber, final int pageSize);

	/**
	 * 获取分页和排序信息
	 *
	 * @return
	 */
	public abstract Pageable getPageable();

	/**
	 * 是否有分页
	 *
	 * @return
	 */
	public abstract boolean hasPageable();

	/**
	 * 删除分页
	 */
	public abstract void removePageable();

}
