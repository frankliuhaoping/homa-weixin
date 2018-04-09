package cn.cnyirui.framework.dao.search.exception;

import cn.cnyirui.framework.dao.search.SearchOperator;

public final class InvlidSearchOperatorException extends SearchException {
	private static final long serialVersionUID = -3777969535562395248L;

	public InvlidSearchOperatorException(String searchProperty, String operatorStr) {
		this(searchProperty, operatorStr, null);
	}

	public InvlidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
		super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
				"operator [" + operatorStr + "], must be one of " + SearchOperator.toStringAllOperator(), cause);
	}
}
