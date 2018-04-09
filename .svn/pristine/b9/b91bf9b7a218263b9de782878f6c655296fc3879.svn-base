package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.IMSLog;
import cn.cnyirui.homaweixin.service.backend.IMSLogService;
import cn.cnyirui.ims.task.SynchronizeIMSDataTask;

@Controller
@Lazy(true)
@RequestMapping("/backend/imsLog")
public class IMSLogController extends BaseCRUDController<IMSLog> {

	@Resource
	private IMSLogService imsLogService;

	@Resource
	private SynchronizeIMSDataTask synchronizeIMSDataTask;

	@Override
	protected BaseService<IMSLog> getBaseService() {
		return imsLogService;
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		super.handleSearchFilter(searchable);
		SearchFilterHelper.wrapperDateTimeSearchFilter(searchable.getSearchFilters(), "createdTime");
	}

	@RequestMapping("/synProduct")
	@ResponseBody
	public JsonResult synProduct() {
		new Thread(new Runnable() {
			public void run() {
				synchronizeIMSDataTask.synchronizeIMSProductCategory();
				synchronizeIMSDataTask.synchronizeIMSProduct();
			}
		}).start();
		return JsonResult.success();
	}

	@RequestMapping("/synOrganization")
	@ResponseBody
	public JsonResult synOrganization() {
		new Thread(new Runnable() {
			public void run() {
				synchronizeIMSDataTask.synchronizeIMSOrganization();
			}
		}).start();
		return JsonResult.success();
	}

	@RequestMapping("/synEmployee")
	@ResponseBody
	public JsonResult synEmployee() {
		new Thread(new Runnable() {
			public void run() {
				synchronizeIMSDataTask.synchronizeIMSEmployee();
			}
		}).start();
		return JsonResult.success();
	}

	@RequestMapping("/synSalesOrder")
	@ResponseBody
	public JsonResult synSalesOrder(final Integer year, final Integer month) {
		new Thread(new Runnable() {
			public void run() {
				synchronizeIMSDataTask.synchronizeIMSSalesOrder(year, month);
			}
		}).start();
		return JsonResult.success();
	}

	@RequestMapping("/synEmployeeWage")
	@ResponseBody
	public JsonResult synEmployeeWage(final Integer year, final  Integer month) {
		new Thread(new Runnable() {
			public void run() {
				synchronizeIMSDataTask.synchronizeIMSEmployeeWage(year, month);
			}
		}).start();
		return JsonResult.success();
	}
}
