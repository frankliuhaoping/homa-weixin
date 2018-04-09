package cn.cnyirui.framework.dao.search;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.google.common.collect.Lists;

import cn.cnyirui.framework.dao.search.exception.SearchException;
import cn.cnyirui.framework.dao.search.filter.Condition;
import cn.cnyirui.framework.dao.search.filter.SearchFilter;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.dao.search.utils.SearchableUtils;

/**
 * 查询条件（包括分页和排序）
 */

public final class SearchRequest extends Searchable {
	/**
	 * 使用这个的目的是保证拼sql的顺序是按照添加时的顺序
	 */
	private List<SearchFilter> searchFilters = Lists.newArrayList();

	private Pageable page;

	private Sort sort;

	private boolean converted;

	@Override
	public Searchable addSearchParam(final String key, final Object value) throws SearchException {
		addSearchFilter(SearchFilterHelper.newCondition(key, value));
		return this;
	}

	@Override
	public Searchable addSearchFilter(final String searchProperty, final SearchOperator operator, final Object value) {
		SearchFilter searchFilter = SearchFilterHelper.newCondition(searchProperty, operator, value);
		return addSearchFilter(searchFilter);
	}

	@Override
	public Searchable addSearchFilter(SearchFilter searchFilter) {
		if (searchFilter == null) {
			return this;
		}
		int index = searchFilters.indexOf(searchFilter);
		if (index != -1) {
			searchFilters.set(index, searchFilter);
		} else {
			searchFilters.add(searchFilter);
		}
		return this;

	}

	@Override
	public Searchable addSearchFilters(Collection<? extends SearchFilter> searchFilters) {
		if (CollectionUtils.isEmpty(searchFilters)) {
			return this;
		}
		for (SearchFilter searchFilter : searchFilters) {
			addSearchFilter(searchFilter);
		}
		return this;
	}

	@Override
	public Searchable or(final SearchFilter first, final SearchFilter... others) {
		addSearchFilter(SearchFilterHelper.or(first, others));
		return this;
	}

	@Override
	public Searchable and(final SearchFilter first, final SearchFilter... others) {

		addSearchFilter(SearchFilterHelper.and(first, others));
		return this;
	}

	@Override
	public List<SearchFilter> findSearchFilter(final String searchProperty, final SearchOperator operator) {
		return SearchFilterHelper.findSearchFilter(searchFilters, searchProperty, operator);
	}

	@Override
	public SearchFilter findOneSearchFilter(String searchProperty, SearchOperator operator) {
		List<SearchFilter> list = findSearchFilter(searchProperty, operator);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public Searchable removeSearchFilter(final String searchProperty, final SearchOperator operator) {
		SearchFilterHelper.removeSearchFilter(searchFilters, searchProperty, operator);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(final String searchProperty, final SearchOperator operator) {
		SearchFilter searchFilter = findOneSearchFilter(searchProperty, operator);
		if (searchFilter != null && searchFilter instanceof Condition) {
			return (T) ((Condition) searchFilter).getValue();
		}
		return null;
	}

	@Override
	public void setValue(String searchProperty, SearchOperator operator, Object value) {
		List<SearchFilter> searchFilterList = findSearchFilter(searchProperty, operator);
		for (SearchFilter searchFilter : searchFilterList) {
			if (searchFilter instanceof Condition) {
				((Condition) searchFilter).setValue(value);
			}
		}

	}

	@Override
	public Searchable setPageable(final Pageable page) {
		merge(sort, page);
		return this;
	}

	@Override
	public Searchable setPageable(int pageNumber, int pageSize) {
		merge(sort, new PageRequest(pageNumber, pageSize));
		return this;
	}

	@Override
	public Searchable addSort(final Sort sort) {
		merge(sort, page);
		return this;
	}

	@Override
	public Searchable addSort(final Sort.Direction direction, final String property) {
		merge(new Sort(direction, property), page);
		return this;
	}

	@Override
	public <T> Searchable convert(final Class<T> entityClass) {
		SearchableUtils.convertSearchValueToEntityValue(this, entityClass);
		markConverted();
		return this;
	}

	@Override
	public Searchable markConverted() {
		this.converted = true;
		return this;
	}

	@Override
	public Collection<SearchFilter> getSearchFilters() {
		return Collections.unmodifiableCollection(searchFilters);
	}

	@Override
	public boolean isConverted() {
		return converted;
	}

	@Override
	public boolean hasSearchFilter() {
		return searchFilters.size() > 0;
	}

	@Override
	public boolean hashSort() {
		return this.sort != null && this.sort.iterator().hasNext();
	}

	@Override
	public boolean hasPageable() {
		return this.page != null && this.page.getPageSize() > 0;
	}

	@Override
	public void removeSort() {
		this.sort = null;
		if (this.page != null) {
			this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), null);
		}
	}

	@Override
	public void removePageable() {
		this.page = null;
	}

	public Pageable getPageable() {
		return page;
	}

	public Sort getSort() {
		return sort;
	}

	private void merge(Sort sort, Pageable page) {
		if (sort == null) {
			sort = this.sort;
		}
		if (page == null) {
			page = this.page;
		}

		// 合并排序
		if (sort == null) {
			this.sort = page != null ? page.getSort() : null;
		} else {
			this.sort = (page != null ? sort.and(page.getSort()) : sort);
		}
		// 把排序合并到page中
		if (page != null) {
			this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), this.sort);
		} else {
			this.page = null;
		}
	}

	@Override
	public String toString() {
		return "SearchRequest{" +
		        "searchFilters=" + searchFilters +
		        ", page=" + page +
		        ", sort=" + sort +
		        '}';
	}

}
