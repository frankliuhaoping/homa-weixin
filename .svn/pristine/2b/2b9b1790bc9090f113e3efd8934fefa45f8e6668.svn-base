package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.controller.bind.annotation.PageableDefaults;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.Notice;
import cn.cnyirui.homaweixin.model.po.TypicalCase;
import cn.cnyirui.homaweixin.service.backend.NoticeService;

@Controller
@RequestMapping("/backend/notice")
public class NoticeController extends BaseCRUDController<Notice> {

	@Resource
	private NoticeService noticeService;

	@Override
	protected BaseService<Notice> getBaseService() {
		return noticeService;
	}
	
	@PageableDefaults(sort = { "isTop=desc", "createdTime=desc" })
	@ResponseBody
	@Override
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		return super.jsonList(searchable, request);
	}
	
	
	@RequestMapping("/stick/{id}")
	@ResponseBody
	public JsonResult stick(@PathVariable String id) {
		if (id != null) {
			Notice notice = noticeService.findOne(id);
			notice.setIsTop(true);
			noticeService.save(notice);
			return JsonResult.success("yes");
		}
		return JsonResult.error("no");
	}
	

}
