package cn.cnyirui.framework.controller.standardsetup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.base.StandardSetupEntity;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.standardsetup.StandardSetupService;
import cn.cnyirui.framework.utils.EntityUtils;
import cn.cnyirui.framework.utils.CurrentUserUtils;

/**
 * 执行标准
 * 
 * @author pengzhihua
 *
 */
@Controller
@RequestMapping("/standardSetup/{entityClassName}")
public class StandardSetupController extends BaseCRUDController<StandardSetupEntity> {

	@Resource
	private StandardSetupService standardSetupService;
	private Map<String, Class<?>> clazzMap = new ConcurrentHashMap<String, Class<?>>();

	@Override
	public String getViewPrefix() {
		return "/standardSetup/standardSetup_";
	}

	@Override
	protected void setCommonModel(ModelAndView m, String viewName) {
		super.setCommonModel(m, viewName);
		m.addObject("sysUser", CurrentUserUtils.getSysUser());
		m.addObject("standardSetupConfig",
		        standardSetupService.findByEntityClassName(standardSetupService.getEntityClass().getSimpleName()));

		if ("list".equalsIgnoreCase(viewName)) {
			m.addObject("entityClassName", standardSetupService.getEntityClass().getSimpleName());
			m.addObject("controllerConfig", getControllerConfig(getRequest()));
		}
	}

	@SuppressWarnings("unchecked")
	private void setEntityClassName(HttpServletRequest request) {
		Map<String, String> map = (Map<String, String>) request
		        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if (map != null) {
			String className = map.get("entityClassName");
			Class<?> clazz = clazzMap.get(className);
			if (clazz == null) {
				String fullClassName = EntityUtils.getJpaManagedEntityFullClassName(className);
				try {
					clazz = Class.forName(fullClassName);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			standardSetupService.setEntityClass((Class<StandardSetupEntity>) clazz);

		}
	}

	@Override
	public ModelAndView list(HttpServletRequest request) {
		setEntityClassName(request);
		if (standardSetupService.findByEntityClassName(standardSetupService.getEntityClass().getSimpleName()) == null) {
			return getMessageView(true, "找不到相关配置，请检查执行标准管理中是否有配置！");
		}
		return super.list(request);
	}

	@Override
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		setEntityClassName(request);
		return super.jsonList(searchable, request);
	}

	@Override
	public void exportToExcel(Searchable searchable, HttpServletRequest request, HttpServletResponse response) {
		setEntityClassName(request);
		super.exportToExcel(searchable, request, response);
	}

	@Override
	public ModelAndView view(@PathVariable("id") String id) {
		setEntityClassName(getRequest());
		return super.view(id);
	}

	@Override
	public ModelAndView edit(@PathVariable("id") String id) throws Exception {
		setEntityClassName(getRequest());
		return super.edit(id);
	}

	@Override
	public JsonResult delete(@PathVariable("ids") String[] ids) {
		setEntityClassName(getRequest());
		return super.delete(ids);
	}

	@Override
	public JsonResult save(String action, StandardSetupEntity pageModel, HttpServletRequest request) {
		setEntityClassName(request);
		return super.save(action, pageModel, request);
	}

	@Override
	public JsonResult doSave(String action, StandardSetupEntity pageModel, HttpServletRequest request) {
		StandardSetupEntity standardSetupEntity = standardSetupService.findOneByName(pageModel.getName());
		if (assertExists(action, pageModel, standardSetupEntity)) {
			return JsonResult.error("名称：" + pageModel.getName() + " 已经被占用，请更换一个！");
		}
		return super.doSave(action, pageModel, request);
	}

	@Override
	protected BaseService<StandardSetupEntity> getBaseService() {
		return standardSetupService;
	}
}
