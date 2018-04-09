package cn.cnyirui.framework.model.po.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 允许用户自定排序的实现类(对应页面上移下移行)
 * 
 * @author pengzhihua
 *
 */
@MappedSuperclass
public class SequenceableEntity extends BaseEntity implements Sequenceable {
	private static final long serialVersionUID = -1209818291821193037L;

	/**
	 * 排序字段
	 */
	@Column
	protected Long seq = System.nanoTime();

	@Override
	public Long getSeq() {
		return seq;
	}

	@Override
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Override
	@Transient
	public void swapSeq(Sequenceable other) {
		if (other == null) {
			return;
		}
		Long seq = this.seq;
		this.seq = other.getSeq();
		other.setSeq(seq);
	}

	@Override
	@Transient
	public void setDefaultSeq() {
		setSeq(System.nanoTime());
	}
}
