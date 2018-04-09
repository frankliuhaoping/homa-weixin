package cn.cnyirui.framework.model.po.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 逻辑删除实现类
 * 
 * @author pengzhihua
 *
 */
@MappedSuperclass
public abstract class LogicDeleteableEntity extends BaseEntity implements LogicDeleteable {

	private static final long serialVersionUID = -8371700223093983753L;

	/**
	 * 删除标记，0 未删除，1逻辑删除，2已删除
	 */
	@Column
	private Integer deleted = 0;

	@Override
	public Integer getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	@Transient
	public void markDeleted() {
		setDeleted(this.deleted + 1);
	}

}
