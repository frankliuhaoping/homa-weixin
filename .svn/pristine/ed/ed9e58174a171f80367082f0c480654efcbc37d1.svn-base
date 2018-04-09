package cn.cnyirui.framework.dao.search.filter;

import java.util.List;

import com.google.common.collect.Lists;

public class AndCondition implements SearchFilter {

	private List<SearchFilter> andFilters = Lists.newArrayList();

	AndCondition() {
	}

	public AndCondition add(SearchFilter filter) {
		this.andFilters.add(filter);
		return this;
	}

	public List<SearchFilter> getAndFilters() {
		return andFilters;
	}

	@Override
	public String toString() {
		return "AndCondition{" + andFilters + '}';
	}

	@Override
	public Condition asCondition() {
		return null;
	}

	@Override
	public OrCondition asOrCondition() {
		return null;
	}

	@Override
	public AndCondition asAndCondition() {
		return this;
	}
}
