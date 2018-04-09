package cn.cnyirui.ims.entity;

public enum IMSEmployeeTye {
	/**
	 * 员工
	 */
	EMPLOYEE(0, "员工"), //

	/**
	 * 导购员
	 */
	SALER(1, "导购员");

	private IMSEmployeeTye(int value, String text) {
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
