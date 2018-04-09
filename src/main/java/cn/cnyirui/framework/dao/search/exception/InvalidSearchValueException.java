package cn.cnyirui.framework.dao.search.exception;

public final class InvalidSearchValueException extends SearchException {

	private static final long serialVersionUID = -7251949700133894408L;

	public InvalidSearchValueException(String searchProperty, String entityProperty, Object value) {
		this(searchProperty, entityProperty, value, null);
	}

	public InvalidSearchValueException(String searchProperty, String entityProperty, Object value, Throwable cause) {
		super("Invalid Search Value, searchProperty [" + searchProperty + "], " +
				"entityProperty [" + entityProperty + "], value [" + value + "]", cause);
	}

}
