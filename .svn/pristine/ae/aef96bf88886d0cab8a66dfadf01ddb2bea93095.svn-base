package cn.cnyirui.homaweixin.controller.backend;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.backend.OrganizationService;

@RestController
@RequestMapping("/backend/common")
public class CommonController {

	@Resource
	private OrganizationService organizationService;
	@Resource
	private EmployeeService employeeService;

	/**
	 * 
	 * @param parentId
	 *            如果=ALL，一次性返回所有的，如果parentId=null，且当前是登录状态，
	 *            则返回登录用户所在的组织架构及下面的组织架构
	 * @param forTreeGrid
	 *            true为treeGrid的数据
	 * @return
	 */
	private List<ObjectNode> organizationTree(String parentId, boolean forTreeGrid, HttpServletRequest request) {
		boolean getAll = "ALL".equalsIgnoreCase(parentId);
		if (getAll) {
			parentId = null;
		}
		return organizationService.getOrganizationTreeJson(parentId, getAll, forTreeGrid, request);
	}

	/**
	 * 组织架构的Tree Json数据
	 * 
	 * @param parentId
	 *            如果=ALL，返回所有的，如果parentId=null，且当前是登录状态，则返回登录用户所在的组织架构及下面的组织架构
	 * @return
	 */
	@RequestMapping("/organizationTree")
	public List<ObjectNode> organizationTree(@RequestParam(name = "id", required = false) String parentId,
	        HttpServletRequest request) {
		return organizationTree(parentId, false, request);
	}

	/**
	 * 组织架构的TreeGrid Json数据
	 * 
	 * @param parentId
	 *            如果=ALL，返回所有的，如果parentId=null，且当前是登录状态，则返回登录用户所在的组织架构及下面的组织架构
	 * @return
	 */
	@RequestMapping("/organizationTreeGrid")
	public List<ObjectNode> organizationTreeGrid(@RequestParam(name = "id", required = false) String parentId,
	        HttpServletRequest request) {
		return organizationTree(parentId, true, request);
	}

	@RequestMapping("/selectYearMonth")
	public ModelAndView selectYearMonth() {
		return new ModelAndView("/common/select_year_month_dialog");
	}

}
