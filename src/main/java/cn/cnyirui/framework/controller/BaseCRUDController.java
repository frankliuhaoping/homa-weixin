package cn.cnyirui.framework.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.bind.annotation.PageableDefaults;
import cn.cnyirui.framework.controller.bind.annotation.SearchableDefaults;
import cn.cnyirui.framework.controller.upload.UploadController;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.extension.excel.ExcelService;
import cn.cnyirui.framework.extension.log.SysLog;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.vo.ControllerConfig;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.model.vo.Plupload;
import cn.cnyirui.framework.service.rbac.SysMenuService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.framework.utils.POIUtils;
import cn.cnyirui.framework.utils.ServletUtils;

/**
 * CRUD controller基类
 * 
 * @author pengzhihua
 *
 * @param <T>
 */
public abstract class BaseCRUDController<T extends BaseEntity> extends BaseController<T> {
	/**
	 * 用于初始权限
	 */
	@Resource
	protected SysMenuService sysMenuService;
	@Resource
	protected ExcelService excelService;
	/**
	 * view && permission
	 */
	private Map<String, ControllerConfig> controllerConfigMap = new HashMap<String, ControllerConfig>();

	public ControllerConfig getControllerConfig(HttpServletRequest request) {
		String urlPrefix = ServletUtils.replaceUriTemplateVariables(request, defaultViewPrefix(false));
		String key = StringUtils.replace(urlPrefix, "/", "_").toLowerCase();
		ControllerConfig controllerConfig = controllerConfigMap.get(key);
		if (controllerConfig == null) {
			logger.debug("********** 初始化 viewName and sysPermission **************");
			controllerConfig = ControllerConfig.newControllerConfig(
			        ServletUtils.replaceUriTemplateVariables(request, getViewPrefix()),
			        sysMenuService.findByUrl(urlPrefix + "list"));
			controllerConfigMap.put(key, controllerConfig);
		}
		return controllerConfig;
	}

	@Override
	protected void setCommonModel(ModelAndView m, String viewName) {
		super.setCommonModel(m, viewName);
		if ("list".equalsIgnoreCase(viewName)) {
			m.addObject("controllerConfig", getControllerConfig(getRequest()));
		}
	}

	/**
	 * 查询条件处理
	 * 
	 * @param searchable
	 */
	protected void handleSearchFilter(Searchable searchable) {
	}

	/**
	 * 列表-权限检查
	 * 
	 * @param searchable
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request) {
		// 权限检查
		if (!getControllerConfig(request).hasViewPermission()) {
			return getMessageView(true, "没有权限进入，请联系管理员！");
		}
		return doList(request);
	}

	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 */
	public ModelAndView doList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView(getControllerConfig(request).getListViewName());
		setCommonModel(m, "list");
		return m;
	}

	/**
	 * JSON列表
	 * 
	 * <pre>
	 * request中需要_dataFields参数，_dataFields=生成json的key=<T>的属性
	 * </pre>
	 * 
	 * @param searchable
	 * @param request
	 * @return
	 */
	@RequestMapping("/jsonList")
	@ResponseBody
	@PageableDefaults(sort = { "createdTime=desc" })
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		handleSearchFilter(searchable);
		// 权限检查
		if (!getControllerConfig(request).hasViewPermission()) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			return objectNode.put("message", "没有权限进入，请联系管理员！");
		}

		return doJsonList(searchable, request);
	}

	public ObjectNode doJsonList(Searchable searchable, HttpServletRequest request) {
		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		EntityConfig config = getBaseService().getToObjectNodeEntityConfig(request);
		Page<T> pageData = getBaseService().findAll(searchable);
		ArrayNode arrayNode = JsonUtil.getObjectMapper().createArrayNode();
		for (T t : pageData.getContent()) {
			arrayNode.add(getBaseService().entityToObjectNode(t, config));
		}
		objectNode.putArray("rows").addAll(arrayNode);
		objectNode.put("total", pageData.getTotalElements());
		return objectNode;
	}

	/**
	 * 输出excel
	 * 
	 * <pre>
	 * request中需要_dataFields参数，_dataFields=生成json的key=<T>的属性
	 * request中需要_fieldTitles参数，_fieldTitles=excel列的标题
	 * request中需要fileName参数，fileName=输出excel的文件名
	 * </pre>
	 * 
	 * @param searchable
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportToExcel")
	@SearchableDefaults(needPage = false)
	public void exportToExcel(Searchable searchable, HttpServletRequest request, HttpServletResponse response) {
		// 权限检查
		if (!getControllerConfig(request).hasExportPermission()) {
			logger.warn("无导出权限！");
			return;
		}
		handleSearchFilter(searchable);
		String dataFields = ServletRequestUtils.getStringParameter(request, "_dataFields", "");
		String fieldTitles = ServletRequestUtils.getStringParameter(request, "_fieldTitles", "");
		String fileName = ServletRequestUtils.getStringParameter(request, "fileName", "");
		if (StringUtils.isEmpty(dataFields)) {
			return;
		}

		ObjectNode objectNode = jsonList(searchable, request);
		ServletUtils.setFileDownloadHeader(request, response, fileName + ".xls");
		try {
			POIUtils.exportToExcel(dataFields, fieldTitles, objectNode, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入excel页面
	 * 
	 * @return
	 */
	@RequestMapping("/importFromExcel")
	public ModelAndView importFromExcel() {
		if (!getControllerConfig(getRequest()).hasImportPermission()) {
			return getMessageView(true, "没有导入权限，请联系管理员！");
		}
		if (!excelService.canImportFromExcel(getBaseService().getEntityClass())) {
			return getMessageView(false, "无导入配置文件，请联系管理员！");
		}
		;
		ModelAndView m = new ModelAndView("/common/import_excel");
		return m;
	}

	/**
	 * 生成导入excel模板文件
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/importFromExcel/getTemplateFile")
	public void exportTemplateFile(HttpServletRequest request, HttpServletResponse response) {
		ControllerConfig controllerConfig = getControllerConfig(request);
		if (!controllerConfig.hasImportPermission()) {
			logger.warn("无导入权限！");
			return;
		}
		String fileName = controllerConfig.getPageTitle();
		ServletUtils.setFileDownloadHeader(request, response, fileName + ".xls");
		try {
			excelService.buildExcelTemplateFile(getBaseService().getEntityClass(), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/importFromExcel/import")
	@ResponseBody
	public JsonResult importFromExcel(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {
		plupload = UploadController.pluploadHandler(plupload, request, response);
		if (plupload != null) {
			return excelService.importFromExcel(plupload.getRealPath() + "/" + plupload.getName(),
			        getBaseService().getEntityClass());
		}
		return JsonResult.error("文件导入失败，请重试！");
	}

	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") String id) {
		// 权限检查
		if (!getControllerConfig(getRequest()).hasViewPermission()) {
			return getMessageView(true, "没有权限进入，请联系管理员！");
		}
		return doView(id);
	}

	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	public ModelAndView doView(String id) {
		ModelAndView m = new ModelAndView(getControllerConfig(getRequest()).getViewViewName());
		if (id != null) {
			T model = getBaseService().findOne(id);
			m.addObject("model", model);
			setCommonModel(m, "view");
		}
		return m;
	}

	/**
	 * 查看createdBy, createdTime, lastModifiedBy, lastModifiedTime
	 * 
	 * @return
	 */
	@RequestMapping("/viewAuditor")
	public ModelAndView viewAuditor() {
		return new ModelAndView("/common/view_auditor");
	}

	/**
	 * 选择数据
	 * 
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select() {
		// 权限检查
		if (!getControllerConfig(getRequest()).hasViewPermission()) {
			return getMessageView(true, "没有权限进入，请联系管理员！");
		}
		return new ModelAndView("/common/select_data");
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public ModelAndView add() {
		// 权限检查
		if (!getControllerConfig(getRequest()).hasAddPermission()) {
			return getMessageView(false, "noPermissionError:没有权限进入，请联系管理员！");
		}
		return doAdd();
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public ModelAndView doAdd() {
		ModelAndView m = new ModelAndView(getControllerConfig(getRequest()).getAddViewName());
		T model = getBaseService().newEntity();
		m.addObject("model", model);
		setCommonModel(m, "add");
		return m;
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") String id) throws Exception {
		// 权限检查
		if (!getControllerConfig(getRequest()).hasEditPermission()) {
			return getMessageView(false, "noPermissionError:没有权限进入，请联系管理员！");
		}
		return doEdit(id);
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	public ModelAndView doEdit(String id) {
		ModelAndView m = new ModelAndView(getControllerConfig(getRequest()).getEditViewName());
		if (id != null) {
			T model = getBaseService().findOne(id);
			m.addObject("model", model);
			setCommonModel(m, "edit");
		}
		return m;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete/{ids}")
	@ResponseBody
	@SysLog(description = "删除")
	public JsonResult delete(@PathVariable("ids") String[] ids) {
		// 权限检查
		if (!getControllerConfig(getRequest()).hasDelPermission()) {
			return JsonResult.error("无删除权限！请联系管理员！");
		}
		try {
			return doDelete(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult
			        .error("删除失败，请重试，请确认在其它地方是否有用到此数据！<br><br>" + ExceptionUtils.getRootCause(e).getMessage());
		}

	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return 返回null 或者 JsonResult("yes", null);，表示保存成功
	 */
	public JsonResult doDelete(String[] ids) {
		getBaseService().delete(ids);
		return JsonResult.success();
	}

	/**
	 * 保存
	 * 
	 * @param action
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	@SysLog(description = "添加或修改")
	public JsonResult save(String action, T pageModel, HttpServletRequest request) {
		if ("edit".equalsIgnoreCase(action)) {
			if (!getControllerConfig(getRequest()).hasEditPermission()) {
				return JsonResult.error("无修改权限！！请联系管理员！");
			}
		}
		if ("add".equalsIgnoreCase(action)) {
			if (!getControllerConfig(getRequest()).hasAddPermission()) {
				return JsonResult.error("无添加权限！！请联系管理员！");
			}
		}
		if (pageModel == null) {
			return JsonResult.error("保存失败！");
		}
		try {
			return doSave(action, pageModel, request);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.error("保存失败，请重试！！");
		}
	}

	/**
	 * 保存
	 * 
	 * @param action
	 * @param pageModel
	 * @return 返回null 或者 JsonResult("yes", null);，表示保存成功
	 * @throws Exception
	 */
	public JsonResult doSave(String action, T pageModel, HttpServletRequest request) {
		T poModel = getBaseService().savePageModel(action, pageModel, request);
		return JsonResult.success(
		        getBaseService().entityToObjectNode(poModel, getBaseService().getToObjectNodeEntityConfig(request)));
	}

	/**
	 * 
	 * 排序
	 * 
	 * @param idList
	 * @param swaqSeq
	 * @return
	 */
	@RequestMapping("/sequence")
	@ResponseBody
	public JsonResult sequence(@RequestParam("ids") List<String> idList,
	        @RequestParam(value = "swapSeq", required = false) Boolean swapSeq) {
		try {
			getBaseService().sequence(idList, swapSeq == null ? false : swapSeq.booleanValue());
			return JsonResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.error("操作失败，请重试！！");
		}
	}

	/**
	 * 判断记录是否已经存在
	 * 
	 * @param action
	 *            add or edit
	 * @param pageModel
	 *            页面Model
	 * @param findedModel
	 *            找到的Model
	 * @return
	 */
	protected boolean assertExists(String action, T pageModel, T findedModel) {
		return (findedModel != null && ("add".equalsIgnoreCase(action)
		        || "edit".equalsIgnoreCase(action) && !findedModel.getId().equalsIgnoreCase(pageModel.getId())));
	}
}
