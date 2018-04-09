package cn.cnyirui.homaweixin.dao.backend;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeDaoImpl implements EmployeeDaoCustom {
	@PersistenceContext(unitName = "jpa.yirui.homaweixin")
	private EntityManager entityManager;

	@Override
	@Transactional
	public String createCanViewOrganizationList(String employeeId, String callId) {
		if (StringUtils.isBlank(callId)) {
			callId = UUID.randomUUID().toString();
		}
		Query query = entityManager.createNativeQuery("CALL createCanViewOrganizationList(:employeeId, :callId)");
		query.setParameter("employeeId", employeeId);
		query.setParameter("callId", callId);
		query.executeUpdate();
		return callId;
	}

}
