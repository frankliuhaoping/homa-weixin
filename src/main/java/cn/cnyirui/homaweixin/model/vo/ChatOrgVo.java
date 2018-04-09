package cn.cnyirui.homaweixin.model.vo;


public class ChatOrgVo {

	/**
	 * 部门id
	 */
	private String id;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 是否上级部门主管所在的部门
	 */
	private boolean directorOrg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDirectorOrg() {
		return directorOrg;
	}
	public void setDirectorOrg(boolean directorOrg) {
		this.directorOrg = directorOrg;
	}
	
}
