package cn.cnyirui.framework.controller.standardsetup;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.po.standardsetup.StandardSetupConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.standardsetup.StandardSetupConfigService;

@Controller
@RequestMapping("/standardSetup/standardSetupConfig")
public class StandardSetupConfigController extends BaseCRUDController<StandardSetupConfig> {

	@Resource
	private StandardSetupConfigService standardSetupConfigService;

	@Override
	protected BaseService<StandardSetupConfig> getBaseService() {
		return standardSetupConfigService;
	}

}
