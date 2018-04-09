package cn.cnyirui.homaweixin.dao.backend;

public interface EmployeeDaoCustom {
	/**
	 * 创建员工能查看数据的组织架构(负责的组织架构+可查看数据的组织架构)
	 * 
	 * @param callId
	 * @param employeeId 为空返回所有组织架构
	 * @return
	 */
	String createCanViewOrganizationList(String employeeId, String callId);
}
