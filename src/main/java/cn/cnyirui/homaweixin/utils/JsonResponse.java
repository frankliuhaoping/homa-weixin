package cn.cnyirui.homaweixin.utils;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

public class JsonResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	
	private String msg;

	private Object result;
	
	public JsonResponse(ResponseCode responseCode){
		this(responseCode, null);
	}

	public JsonResponse(ResponseCode responseCode, String msg, Object result){
	    this.code = responseCode.getCode();
	    this.msg = generateMsg(responseCode, msg);
	    this.result = result;
	}
	
	public JsonResponse(ResponseCode responseCode, String msg){
	    this.code = responseCode.getCode();
	    this.msg = generateMsg(responseCode, msg);
	}

	public JsonResponse(int responseCode, String msg){
	    this.code = responseCode;
	    this.msg = msg;
	}
	
	/**
	 * code
	 * @return code code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * code
	 * @param code code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * msg
	 * @return msg msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * msg
	 * @param msg msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * result
	 * @return result result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * result
	 * @param result result
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	public String toString(){
	    return new StringBuilder().append("RestResponse [getResult()=").append(getResult()).append(", getCode()=").append(getCode()).append(", getMsg()=").append(getMsg()).append("]").toString();
	}

	public static String generateMsg(ResponseCode responseCode, String msg){
	    return new StringBuilder().append(responseCode.getMsg()).append(StringUtils.isBlank(msg) ? "" : new StringBuilder().append("<").append(msg).append(">").toString()).toString();
	}
}
