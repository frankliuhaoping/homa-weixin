package cn.cnyirui.framework.model.eo;

public enum SysMenuUseOf {

	PC(0, "PC端"), WX(1, "微信端");

	private SysMenuUseOf(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	private Integer value;
	private String text;

	/**
	 * value
	 * 
	 * @return value value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * value
	 * 
	 * @param value value
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * text
	 * 
	 * @return text text
	 */
	public String getText() {
		return text;
	}

	/**
	 * text
	 * 
	 * @param text text
	 */
	public void setText(String text) {
		this.text = text;
	}

	public static SysMenuUseOf valueOf(Integer value) {
		for (SysMenuUseOf e : SysMenuUseOf.values()) {
			if (e.getValue() == value) {
				return e;
			}
		}
		return null;
	}

}
