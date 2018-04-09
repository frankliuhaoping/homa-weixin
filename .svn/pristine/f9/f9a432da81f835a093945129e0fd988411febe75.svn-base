package cn.cnyirui.homaweixin.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang3.StringEscapeUtils;

import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.ims.entity.IMSTaskType;

/**
 * 内销系统对接记录
 * 
 */
@Entity
@Table(name = "ims_log")
public class IMSLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务类型
	 * 
	 * @see IMSTaskType
	 */
	@Column
	private Integer taskType;

	/**
	 * 是否成功 true=成功
	 */
	@Column
	private Boolean successed;

	/**
	 * 日志内容
	 */
	@Lob
	@Column(columnDefinition = "CLOB")
	private String content;

	/**
	 * 日志内容
	 * 
	 * @return content 日志内容
	 */
	public String getContent() {
		return StringEscapeUtils.escapeHtml4(content);
	}

	/**
	 * 日志内容
	 * 
	 * @param content 日志内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 是否成功true=成功
	 * 
	 * @return successed 是否成功true=成功
	 */
	public Boolean getSuccessed() {
		return successed;
	}

	/**
	 * 是否成功true=成功
	 * 
	 * @param successed 是否成功true=成功
	 */
	public void setSuccessed(Boolean successed) {
		this.successed = successed;
	}

	/**
	 * 任务类型
	 * 
	 * @return taskType 任务类型
	 */
	public Integer getTaskType() {
		return taskType;
	}

	/**
	 * 任务类型
	 * 
	 * @param taskType 任务类型
	 */
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

}
