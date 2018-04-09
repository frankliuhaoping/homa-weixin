package cn.cnyirui.framework.model.po.base;

/**
 * 允许用户自定排序的接口(对应页面上移下移行)
 * 
 * @author pengzhihua
 *
 */
public interface Sequenceable {
	/**
	 * @return 排序值
	 */
	public Long getSeq();

	/**
	 * @param seq 排序值
	 */
	public void setSeq(Long seq);

	/**
	 * 默认排序值
	 */
	public void setDefaultSeq();

	/**
	 * 交换排序值
	 * 
	 * @param other
	 */
	public void swapSeq(Sequenceable other);
}
