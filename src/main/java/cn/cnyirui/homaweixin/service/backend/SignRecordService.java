package cn.cnyirui.homaweixin.service.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.SignRecordDao;
import cn.cnyirui.homaweixin.model.po.SignRecord;

@Service
public class SignRecordService extends BaseService<SignRecord> {
	@Resource
	private SignRecordDao signRecordDao;
	@Resource
	private OrganizationService organizationService;

	@Override
	public BaseDao<SignRecord> getBaseDao() {
		return signRecordDao;
	}

	@Override
	public ObjectNode entityToObjectNode(SignRecord entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		objectNode.put("signType", entity.getSignType() == 1 ? "下班" : "上班");
		return objectNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<SignRecord> findAll(Searchable searchable) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
			searchable.removeSearchFilter("organization.id", SearchOperator.eq);

			StringBuilder ql = new StringBuilder("select s.* from sign_record s, employee e");
			SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, ql, "s");
			ql.append(" and e.id = s.employeeId and e.organizationId = t.organizationId");
			searchable.addSort(
			        new Sort(new Sort.Order(Direction.DESC, "s.signTime"),
			                new Sort.Order(Direction.ASC, "t.parentNames"),
			                new Sort.Order(Direction.ASC, "e.name")));

			Page<SignRecord> result = (Page<SignRecord>) signRecordDao.findAll(ql.toString(), searchable, true,
			        SignRecord.class);
			searchable.addSearchFilter("organization.id", SearchOperator.eq, organizationId);
			return result;
		} else {
			return super.findAll(searchable);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObjectNode> countAll(Searchable searchable) {
		List<ObjectNode> list = new ArrayList<ObjectNode>();
		if (SecurityUtils.getSubject().isAuthenticated()) {
			searchable.removePageable();
			searchable.removeSort();
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();

			String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
			searchable.removeSearchFilter("organization.id", SearchOperator.eq);

			// 人数
			StringBuilder ql = new StringBuilder("select count(distinct employeeId) from sign_record s, employee e");
			SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, ql, "s");
			ql.append(" and e.id = s.employeeId and e.organizationId = t.organizationId");
			Object employeeCount = signRecordDao.getScalarValue(ql.toString(), searchable, true);

			// 上班卡，上班卡次数
			ql = new StringBuilder("select signType, count(signType) signTypeCount from sign_record s, employee e");
			SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, ql, "s");
			ql.append(" and e.id = s.employeeId and e.organizationId = t.organizationId")
			        .append(" group by signType")
			        .append(" order by signType");

			List<Map<String, Object>> dataList = (List<Map<String, Object>>) signRecordDao
			        .findAll(ql.toString(), searchable, true, HashMap.class).getContent();
			objectNode.put("signTime",
			        "签到人数：" + NumberUtils.toInt(String.valueOf(employeeCount)));
			StringBuilder sb = new StringBuilder();
			Integer totalSignTypeCount = 0;
			for (Map<String, Object> map : dataList) {
				Integer signTypeCount = NumberUtils.toInt(String.valueOf(map.get("SIGNTYPECOUNT")));
				totalSignTypeCount += signTypeCount;
				sb.append(("0".equalsIgnoreCase(String.valueOf(map.get("SIGNTYPE"))) ? "上班签到："
				        : "下班签到：") + signTypeCount + "次");
				sb.append("，");
			}
			sb.append("总签到次数：").append(totalSignTypeCount);
			objectNode.put("address", sb.toString());
			objectNode.put("employee.name", "总计");
			list.add(objectNode);
		}
		return list;
	}

}
