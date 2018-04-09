package cn.cnyirui.homaweixin.service.weixin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.weixin.ChatContactDao;
import cn.cnyirui.homaweixin.model.po.ChatGroup;
import cn.cnyirui.homaweixin.model.po.ChatSession;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.po.OrganizationTreeTemp;
import cn.cnyirui.homaweixin.model.vo.ChatEmployeeVo;
import cn.cnyirui.homaweixin.model.vo.ChatOrgPageVo;
import cn.cnyirui.homaweixin.model.vo.ChatOrgVo;
import cn.cnyirui.homaweixin.model.vo.RecentContactPageVo;
import cn.cnyirui.homaweixin.model.vo.RecentContactVo;
import cn.cnyirui.homaweixin.service.backend.OrganizationService;

/**
 * 联系人接口
 * 
 * @author zhoujuhui
 *
 */
@Service
public class ChatContactService extends BaseService<Organization> {

	@Resource
	private ChatContactDao chatContactDao;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private OrganizationTreeTempService organizationTreeTempService;

	@Override
	public BaseDao<Organization> getBaseDao() {
		// TODO Auto-generated method stub
		return chatContactDao;
	}

	/**
	 * 根据组织id查询通讯录信息（包括上一级部门、当前部门、有权限查看的部门）
	 * 
	 * @param orgId
	 * @param currentEmployeeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ChatOrgPageVo getChatOrg(String orgId, String currentEmployeeId, String callId, Searchable searchable) {
		ChatOrgPageVo chatOrgPageVo = new ChatOrgPageVo();
		List<ChatOrgVo> orgVoList = new ArrayList<ChatOrgVo>();
		if (StringUtils.isNotBlank(orgId)) {
			Organization organization = organizationService.findOne(orgId);
			/**
			 * 查询主管所在的部门
			 */
			String directorOrgId = "";
			Employee directorEmployee = organization.getDirector();
			if (directorEmployee != null) {
				Organization directorOrganization = directorEmployee.getOrganization();
				
				directorOrgId = directorOrganization.getId();
				/**
				 * 第一页加上负责人所在部门
				 */
				if(searchable.getPageable().getPageNumber() == 0 
						&& !orgId.equals(directorOrgId)){
					ChatOrgVo chatOrgVo = new ChatOrgVo();
					chatOrgVo.setId(directorOrganization.getId());
					chatOrgVo.setName(directorOrganization.getName());
					chatOrgVo.setDirectorOrg(true);
					orgVoList.add(chatOrgVo);
				}
			}
			
			/**
			 * 第一页加上当前所在部门
			 */
			if(searchable.getPageable().getPageNumber() == 0){
				ChatOrgVo chatOrgVo = new ChatOrgVo();
				chatOrgVo.setId(organization.getId());
				chatOrgVo.setName(organization.getName());
				chatOrgVo.setDirectorOrg(false);
				orgVoList.add(chatOrgVo);
			}
			/**
			 * 查询有权限查看的部门
			 */
			String sql= "select * from organization_tree_temp o where o.callId='" + callId+ "' "
					+ "and o.organizationId !='" + orgId+ "'";
			if(StringUtils.isNotBlank(directorOrgId)){
				sql += " and o.organizationId !='" + directorOrgId+ "'";
			}
			sql += "  order by o.depth,o.organizationName ";
			Page<OrganizationTreeTemp> organizationTreeTempPage = (Page<OrganizationTreeTemp>) chatContactDao.findAll(sql, searchable, true, OrganizationTreeTemp.class);
			List<OrganizationTreeTemp> organizationTreeTempList = organizationTreeTempPage.getContent();
			for(OrganizationTreeTemp organizationTreeTemp : organizationTreeTempList){
				
				ChatOrgVo chatOrgVo = new ChatOrgVo();
				chatOrgVo.setId(organizationTreeTemp.getOrganizationId());
				chatOrgVo.setName(organizationTreeTemp.getOrganizationName());
				chatOrgVo.setDirectorOrg(false);
				orgVoList.add(chatOrgVo);
			}
			int page = searchable.getPageable().getPageNumber() + 1;
			
			int totalPages = organizationTreeTempPage.getTotalPages();
			if(totalPages == 0){
				totalPages = 1;
			}
			chatOrgPageVo.setPage(page);
			chatOrgPageVo.setTotalPages(totalPages);
			chatOrgPageVo.setChatOrgVoList(orgVoList);
		}
		return chatOrgPageVo;
	}

	/**
	 * 获取部门下可以联系的员工数据
	 * @param myOrgId
	 * @param pageOrgId
	 * @param directorOrg
	 * @return
	 */
	public List<ChatEmployeeVo> getOrgEmployee(String myOrgId, String pageOrgId, boolean directorOrg){
		List<ChatEmployeeVo> employeeVoList = new ArrayList<ChatEmployeeVo>();
		if(directorOrg){
			Employee employee = organizationService.findOne(myOrgId).getDirector();
			ChatEmployeeVo employeeVo = new ChatEmployeeVo();
			employeeVo.setId(employee.getId());
			employeeVo.setName(employee.getName());
			
			String remark = "";
			if(StringUtils.isNotBlank(employee.getSysRoleType())){
				remark = SysRoleType.valueOf(employee.getSysRoleType()).getText();
			}
			employeeVo.setRemark(remark);
			employeeVo.setHeadImgUrl(employee.getHeadImgUrl());
			employeeVoList.add(employeeVo);
		}else{
			List<Employee> employeeList = organizationTreeTempService.findEmployeeByOId(pageOrgId);
			for(Employee employee : employeeList){
				ChatEmployeeVo employeeVo = new ChatEmployeeVo();
				employeeVo.setId(employee.getId());
				employeeVo.setName(employee.getName());
				
				String remark = "";
				if(StringUtils.isNotBlank(employee.getSysRoleType())){
					remark = SysRoleType.valueOf(employee.getSysRoleType()).getText();
				}
				employeeVo.setRemark(remark);
				employeeVo.setHeadImgUrl(employee.getHeadImgUrl());
				employeeVoList.add(employeeVo);
			}
		}
		return employeeVoList;
	}
	
	
	/**
	 * 获取我可以联系的员工
	 * 
	 * @param orgId
	 * @param currentEmployeeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ChatEmployeeVo> getMyContact(String orgId, String currentEmployeeId, String keyword, String callId) {
		boolean isSearch = false;
		if(StringUtils.isNotBlank(keyword)){
			isSearch = true;
		}
		List<ChatEmployeeVo> chatEmployeeVoList = new ArrayList<ChatEmployeeVo>();
		/**
		 * 查询主管所在的部门
		 */
		Organization organization = organizationService.findOne(orgId);
		Employee directorEmployee = organization.getDirector();
		String directorId = "";
		if(directorEmployee != null){
			directorId = directorEmployee.getId();
			String name = directorEmployee.getName();
			if(!directorEmployee.getId().equals(currentEmployeeId)){
				if(!isSearch || (name != null && name.contains(keyword))){
					
					ChatEmployeeVo employeeVo = new ChatEmployeeVo();
					employeeVo.setId(directorEmployee.getId());
					employeeVo.setName(name);
					
					String remark = "";
					if(StringUtils.isNotBlank(directorEmployee.getSysRoleType())){
						remark = SysRoleType.valueOf(directorEmployee.getSysRoleType()).getText();
					}
					employeeVo.setRemark(remark);
					employeeVo.setHeadImgUrl(directorEmployee.getHeadImgUrl());
					chatEmployeeVoList.add(employeeVo);
				}
			}
		}
		
		/**
		 * 当前自己所在部门的所有员工
		 */
		List<Employee> employeeList = organization.getEmployeeList();
		for(Employee employee : employeeList){
			if(employee.getId().equals(directorId))continue;
			if(employee.getId().equals(currentEmployeeId))continue;
			
			String name = employee.getName();
			if(!isSearch || (name != null && name.contains(keyword))){
				ChatEmployeeVo employeeVo = new ChatEmployeeVo();
				employeeVo.setId(employee.getId());
				employeeVo.setName(employee.getName());
				
				String remark = "";
				if(StringUtils.isNotBlank(employee.getSysRoleType())){
					remark = SysRoleType.valueOf(employee.getSysRoleType()).getText();
				}
				employeeVo.setRemark(remark);
				employeeVo.setHeadImgUrl(employee.getHeadImgUrl());
				chatEmployeeVoList.add(employeeVo);
			}
		}

		/**
		 * 可以联系的员工列表
		 */
		EntityManager em = chatContactDao.getEntityManager();
		
		String sql = "select e.* from employee e,organization_tree_temp t "
		        + " where e.organizationId = t.organizationId and t.organizationId != ? and t.callId = ? ";
		if(isSearch){
			sql += " and e.name like ? ";
		}
		Query query = em.createNativeQuery(sql, Employee.class);
		query.setParameter(1, orgId);
		query.setParameter(2, callId);
		if(isSearch){
			query.setParameter(3, "%"+keyword+"%");
		}
		
		List<Employee> canContactEmployeeList = query.getResultList();

		for (Employee employee: canContactEmployeeList) {
			
			ChatEmployeeVo employeeVo = new ChatEmployeeVo();
			employeeVo.setId(employee.getId());
			employeeVo.setName(employee.getName());
			
			String remark = "";
			if(StringUtils.isNotBlank(employee.getSysRoleType())){
				remark = SysRoleType.valueOf(employee.getSysRoleType()).getText();
			}
			employeeVo.setRemark(remark);
			employeeVo.setHeadImgUrl(employee.getHeadImgUrl());
			chatEmployeeVoList.add(employeeVo);
		}
		return chatEmployeeVoList;
	}
	
	/**
	 *  获取最近联系人列表
	 * 
	 * @param searchable
	 * @param employeeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public RecentContactPageVo getRecentContact(Searchable searchable, String employeeId) {
		searchable.addSearchFilter("employeeId", SearchOperator.eq,
		employeeId);
		Sort sort = new Sort(Direction.DESC, "lastSendTime");
		searchable.addSort(sort);

		String sql = "select a.* from chat_session_member a,chat_session b where a.sessionId = b.id ";
		Page<ChatSessionMember> chatSessionMemberPage = (Page<ChatSessionMember>) chatContactDao.findAll(sql,
		        searchable, true, ChatSessionMember.class);
		List<ChatSessionMember> chatSessionMemberList = chatSessionMemberPage.getContent();

		List<RecentContactVo> recentContactVoList = new ArrayList<RecentContactVo>();

		for (ChatSessionMember chatSessionMember : chatSessionMemberList) {
			RecentContactVo recentContactVo = new RecentContactVo();
			ChatSession chatSession = chatSessionMember.getChatSession();
			recentContactVo.setId(chatSession.getId());
			recentContactVo.setUnReadNum(chatSessionMember.getUnReadNum());
			recentContactVo.setType(chatSession.getType());
			if (chatSession.getType() == 1) {
				/**
				 * 单聊
				 */
				List<ChatSessionMember> chatSessionMembers = chatSession.getChatSessionMembers();
				Employee other = null;
				for (ChatSessionMember oneChatSessionMember : chatSessionMembers) {
					if (!employeeId.equals(oneChatSessionMember.getEmployee().getId())) {
						other = oneChatSessionMember.getEmployee();
						break;
					}
				}
				
				if (other != null) {
					recentContactVo.setName(other.getName());
					recentContactVo.setEmployeeId(other.getId());
					recentContactVo.setHeadImgUrl(other.getHeadImgUrl());
				}
			} else if (chatSession.getType() == 2) {
				/**
				 * 判断群组是否已被删除
				 */
				ChatGroup chatGroup = chatSessionMember.getChatGroup();
				if(chatGroup.getIsDel() == 1){
					continue;
				}
				/**
				 * 群聊
				 */
				recentContactVo.setName(chatSessionMember.getChatGroup().getName());
				recentContactVo.setHeadImgUrl(chatSessionMember.getChatGroup().getHeadImg());
			}

			recentContactVo.setLastContent(chatSession.getLastContent());
			recentContactVo.setLastSendTime(chatSession.getLastSendTime());
			recentContactVoList.add(recentContactVo);
		}

		int page = searchable.getPageable().getPageNumber() + 1;
		
		int totalPages = chatSessionMemberPage.getTotalPages();
		if(totalPages == 0){
			totalPages = 1;
		}
		
		RecentContactPageVo pageVo = new RecentContactPageVo();
		pageVo.setPage(page);
		pageVo.setTotalPages(chatSessionMemberPage.getTotalPages());
		pageVo.setRecentContactVoList(recentContactVoList);
		return pageVo;
	}
}
