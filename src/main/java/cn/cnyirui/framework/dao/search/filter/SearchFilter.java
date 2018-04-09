package cn.cnyirui.framework.dao.search.filter;

public interface SearchFilter {
	Condition asCondition();

	OrCondition asOrCondition();

	AndCondition asAndCondition();
}
