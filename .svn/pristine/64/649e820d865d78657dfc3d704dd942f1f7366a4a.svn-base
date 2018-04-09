package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.OtherInfo;
import cn.cnyirui.homaweixin.service.backend.OtherInfoService;

@Controller
@RequestMapping("/backend/otherInfo")
public class OtherInfoController extends BaseCRUDController<OtherInfo> {

	@Resource
	private OtherInfoService otherInfoService;

	@Override
	protected BaseService<OtherInfo> getBaseService() {
		// TODO Auto-generated method stub
		return otherInfoService;
	}
}
