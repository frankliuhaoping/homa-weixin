package cn.cnyirui.homaweixin.controller.backend;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.extension.log.SysLog;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.EntityUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.service.backend.OrganizationService;

@Controller
@RequestMapping("/backend/organization")
public class OrganizationController extends BaseCRUDController<Organization> {

	@Resource
	private OrganizationService organizationService;

	@Override
	protected BaseService<Organization> getBaseService() {
		return organizationService;
	}

	@Override
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		if (!searchable.hashSort()) {
			searchable.addSort(Direction.ASC, "parentNames");
		}
		return super.jsonList(searchable, request);
	}

	/***
	 * 查找当前用户的下级组织架构
	 * 
	 * @return
	 */
	@RequestMapping("/getSubOrganization")
	@ResponseBody
	public List<ObjectNode> getSubOrganization() {
		return organizationService.findSubOrganization();
	}

	/***
	 * 查找当前用户的下级组织架构
	 * 
	 * @return
	 */
	@RequestMapping("/getSubOrg")
	@ResponseBody
	public List<ObjectNode> getSubOrg(@RequestParam("id") String id, Searchable searchable) {
		return organizationService.findSubOrg(id);
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		searchable.addSearchFilter("deleted", SearchOperator.eq, "0");
		super.handleSearchFilter(searchable);
	}

	@RequestMapping("/showOrganizationPermissionForm/{organizationId}")
	public ModelAndView showOrganizationPermissionForm(@PathVariable("organizationId") Organization organization) {
		ModelAndView m = new ModelAndView("/backend/organization/organization_permission_list");
		m.addObject("organization", organization);
		return m;
	}

	@RequestMapping("/getOrganizationPermissionJson/{organizationId}")
	@ResponseBody
	public ObjectNode getAuthorizationJson(@PathVariable("organizationId") Organization organization) {
		List<Employee> employeeList = organization.getAllowViewEmployeeList();
		ArrayNode arrayNode = JsonUtil.getObjectMapper().createArrayNode();
		for (Employee employee : employeeList) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", employee.getId());
			objectNode.put("name", employee.getName());
			arrayNode.add(objectNode);
		}
		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		objectNode.putArray("rows").addAll(arrayNode);
		objectNode.put("total", employeeList.size());
		return objectNode;
	}

	@SysLog(description = "查看数据人员")
	@RequestMapping("/saveOrganizationPermissionForm/{organizationId}")
	@ResponseBody
	public JsonResult saveOrganizationPermissionForm(@PathVariable("organizationId") Organization organization,
	        @RequestParam("employeeIds") List<String> employeeIdList, @RequestParam("directorId") String directorId) {
		try {
			organization = organizationService.saveOrganizationPermissionForm(organization, employeeIdList, directorId);
			return JsonResult.success(
			        EntityUtils.toObjectNode(organization, EntityUtils.defaultToObjectNodeEntityConfig(getRequest())));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.error("保存失败，请重试！");
		}

	}

	@Override
	public JsonResult doDelete(String[] ids) {
		JsonResult jsonResult = new JsonResult("yes", "");
		for (int i = 0; i < ids.length; i++) {
			Organization organization = organizationService.findOne(ids[i]);
			if (!organization.getIsIMSData()) {
				organizationService.delete(ids[i]);
			} else {
				jsonResult = new JsonResult("no", String.format("%s 为IMS数据，删除失败！", organization.getName()));
			}
		}
		return jsonResult;
	}
}
