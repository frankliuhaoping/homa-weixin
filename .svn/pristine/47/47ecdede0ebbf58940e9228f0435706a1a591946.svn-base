package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.StandardSetupEntity;

/**
 * The persistent class for the typical_case_category database table.标准设置_典型案例分类
 * 
 */
@Entity
@Table(name = "typical_case_category")
public class TypicalCaseCategory extends StandardSetupEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 典型案列
	 */
	// bi-directional many-to-one association to TypicalCase
	@OneToMany(mappedBy = "category")
	private List<TypicalCase> typicalCaseList;

	/**
	 * 典型案列
	 * 
	 * @return typicalCaseList 典型案列
	 */
	public List<TypicalCase> getTypicalCaseList() {
		return typicalCaseList;
	}

	/**
	 * 典型案列
	 * 
	 * @param typicalCaseList
	 *            典型案列
	 */
	public void setTypicalCaseList(List<TypicalCase> typicalCaseList) {
		this.typicalCaseList = typicalCaseList;
	}

	public TypicalCase addTypicalCaseList(TypicalCase typicalCaseList) {
		getTypicalCaseList().add(typicalCaseList);
		typicalCaseList.setCategory(this);

		return typicalCaseList;
	}

	public TypicalCase removeTypicalCaseList(TypicalCase typicalCaseList) {
		getTypicalCaseList().remove(typicalCaseList);
		typicalCaseList.setCategory(null);

		return typicalCaseList;
	}
}
