package cn.cnyirui.framework.model.po.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.utils.Constants;

/**
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法
 */
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public abstract class BaseEntity implements Persistable<String> {
	private static final long serialVersionUID = -1798419462881536426L;

	@Id
	@GeneratedValue(generator = "uuid")
	// @GenericGenerator(name = "uuid", strategy =
	// "org.hibernate.id.UUIDGenerator")
	@GenericGenerator(name = "uuid", strategy = "cn.cnyirui.framework.extension.hibernate.uuid.TimeBasedUUIDGenerator")
	@Column(length = 36, updatable = false, nullable = false)
	private String id;

	/**
	 * 版本号
	 */
	@Version
	private Integer version;

	/**
	 * 由谁创建
	 */
	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdBy")
	private SysUser createdBy;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
	@CreatedDate
	private Date createdTime;

	/**
	 * 最后由谁修改
	 */
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastModifiedBy")
	private SysUser lastModifiedBy;
	/**
	 * 最后修改时间
	 */
	@DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
	@LastModifiedDate
	private Date lastModifiedTime;

	/**
	 * 主鍵
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 主鍵
	 */
	public String getId() {
		return id;
	}

	/**
	 * 版本号
	 * 
	 * @return version 版本号
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * 版本号
	 * 
	 * @param version 版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * 由谁创建
	 * 
	 * @return createdBy 由谁创建
	 */
	public SysUser getCreatedBy() {
		return createdBy;
	}

	/**
	 * 由谁创建
	 * 
	 * @param createdBy 由谁创建
	 */
	public void setCreatedBy(SysUser createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 创建时间
	 * 
	 * @return createdTime 创建时间
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createdTime 创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 最后由谁修改
	 * 
	 * @return lastModifiedBy 最后由谁修改
	 */
	public SysUser getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * 最后由谁修改
	 * 
	 * @param lastModifiedBy 最后由谁修改
	 */
	public void setLastModifiedBy(SysUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * 最后修改时间
	 * 
	 * @return lastModifiedTime 最后修改时间
	 */
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	/**
	 * 最后修改时间
	 * 
	 * @param lastModifiedTime 最后修改时间
	 */
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * 如果lastModifiedTime为空则返回createTime
	 * 
	 * @return
	 */
	@Transient
	public Date getLastModifiedTimeOrCreateTime() {
		if (getLastModifiedTime() == null) {
			return getCreatedTime();
		}
		return getLastModifiedTime();
	}

	public boolean isNew() {
		return null == getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity that = (BaseEntity) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
}
