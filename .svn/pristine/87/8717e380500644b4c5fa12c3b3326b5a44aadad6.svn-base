package cn.cnyirui.framework.model.po.base;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * 执行标准的基类
 * 
 * @author pengzhihua
 *
 */
@MappedSuperclass
public class StandardSetupEntity extends BaseEntity {
	private static final long serialVersionUID = -7073211725598706774L;

	/**
	 * 编号
	 */
	@Column(length = 100)
	private String code;
	/**
	 * 名称
	 */
	@Column(name = "`name`", length = 100)
	private String name;
	/**
	 * 备注
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "CLOB")
	private String remark;
	/**
	 * 元数据
	 */
	@Column
	private Boolean isMetaData = Boolean.FALSE;;

	/**
	 * 编号
	 * 
	 * @return code 编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编号
	 * 
	 * @param code 编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 名称
	 * 
	 * @return name 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 * 
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 备注
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 元数据
	 * 
	 * @return metaData 元数据
	 */
	public Boolean getIsMetaData() {
		return isMetaData;
	}

	/**
	 * 元数据
	 * 
	 * @param metaData 元数据
	 */
	public void setIsMetaData(Boolean isMetaData) {
		this.isMetaData = isMetaData;
	}

}
