package cn.cnyirui.framework.dao.search.filter;

import java.util.List;

import com.google.common.collect.Lists;

public class OrCondition implements SearchFilter {

	private List<SearchFilter> orFilters = Lists.newArrayList();

	OrCondition() {
	}

	public OrCondition add(SearchFilter filter) {
		this.orFilters.add(filter);
		return this;
	}

	public List<SearchFilter> getOrFilters() {
		return orFilters;
	}

	@Override
	public String toString() {
		return "OrCondition{" + orFilters + '}';
	}

	@Override
	public Condition asCondition() {
		return null;
	}

	@Override
	public OrCondition asOrCondition() {
		return this;
	}

	@Override
	public AndCondition asAndCondition() {
		return null;
	}
}
