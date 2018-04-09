package cn.cnyirui.framework.model.eo;

/**
 * 微信二维码场景类型
 * 
 * @author pengzhihua
 *
 */
public enum WeiXinQrCodeType {

	TMEP(1, "临时二维码"), PERMANENT(2, "永久二维码");

	private WeiXinQrCodeType(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	private String text;
	private Integer value;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
