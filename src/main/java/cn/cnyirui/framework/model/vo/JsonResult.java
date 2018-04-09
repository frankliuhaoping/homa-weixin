package cn.cnyirui.framework.model.vo;

import java.io.Serializable;

/**
 * 用于前端请求，后台响应，返回josn数据
 *
 */
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -5441614835989089115L;

    /**
     * 其它实体类数据
     */
    private Object entity;

    /**
     * 其它实体类数据
     * 
     * @return entity 其它实体类数据
     */
    @SuppressWarnings("unchecked")
    public <T> T getEntity() {
        return (T) entity;
    }

    /**
     * 其它实体类数据
     * 
     * @param entity 其它实体类数据
     */
    public void setEntity(Object entity) {
        this.entity = entity;
    }

    /**
     * 是否调用成功，yes or no
     */
    private String success;
    /**
     * 提示消息
     */
    private String message;

    /**
     * 是否调用成功，yesorno
     * 
     * @return success 是否调用成功，yesorno
     */
    public String getSuccess() {
        return success;
    }

    /**
     * 是否调用成功，yesorno
     * 
     * @param success 是否调用成功，yesorno
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * 提示消息
     * 
     * @return message 提示消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 提示消息
     * 
     * @param message 提示消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIsError() {
        return "no".equalsIgnoreCase(success);
    }

    /**
     * 
     * @param success 是否调用成功，yes or no
     * @param message 提示消息
     */
    public JsonResult(String success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public static JsonResult success(String message) {
        return new JsonResult("yes", message);
    }

    public static JsonResult success() {
        return new JsonResult("yes", null);
    }

    public static JsonResult success(Object entity) {
        JsonResult jsonResult = new JsonResult("yes", null);
        jsonResult.setEntity(entity);
        return jsonResult;
    }

    public static JsonResult error(String message) {
        return new JsonResult("no", message);
    }

    public static JsonResult error() {
        return new JsonResult("no", null);
    }
}
