package cn.cnyirui.ims.entity;

public enum IMSOrganizationType {

	/**
	 * 业务组织
	 */
	BUSIORG(0, "业务组织"), //

	/**
	 * 办事处
	 */
	OFFICE(1, "办事处"), //

	/**
	 * 门店
	 */
	STORE(2, "门店");

	private IMSOrganizationType(int value, String text) {
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

}
