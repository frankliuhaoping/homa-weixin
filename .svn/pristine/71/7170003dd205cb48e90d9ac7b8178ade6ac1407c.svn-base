package cn.cnyirui.framework.model.po.rbac;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * 操作记录
 * 
 * @author pengzhihua
 *
 */

@Entity
@Table(name = "SYS_LOG")
@DynamicInsert
@DynamicUpdate
public class SysLogEntity extends BaseEntity {
	private static final long serialVersionUID = -2523202953273158161L;
	@Column(length = 100)
	private String ip;

	@Column(length = 200)
	private String method;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "CLOB")
	private String params;

	@Column(length = 200)
	private String description;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return StringEscapeUtils.escapeHtml4(params);
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
