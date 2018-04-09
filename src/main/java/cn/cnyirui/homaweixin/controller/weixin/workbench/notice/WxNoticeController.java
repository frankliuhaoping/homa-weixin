package cn.cnyirui.homaweixin.controller.weixin.workbench.notice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.model.po.Notice;
import cn.cnyirui.homaweixin.service.backend.NoticeService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
@RequestMapping("/weixin/workbench/notice")
public class WxNoticeController extends BaseCRUDController<Notice> {

	@Resource
	private NoticeService noticeService;

	@Override
	protected BaseService<Notice> getBaseService() {
		return noticeService;
	}

	@RequestMapping("/findAllNotice")
	@ResponseBody
	public JsonResponse findAllNotice(Searchable searchable) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try { 
			//searchable.addSort(Direction.DESC, "createdTime");
			List<Order> orders = new ArrayList<Sort.Order>();
			orders.add(new Order(Direction.DESC, "isTop"));
			orders.add(new Order(Direction.DESC, "createdTime"));
			searchable.addSort(new Sort(orders));
			searchable.addSearchFilter("isRelease", SearchOperator.eq, false);
			searchable.addSearchFilter("releaseTime", SearchOperator.lte, new Date());
			Page<Notice> page = noticeService.findAll(searchable);
			List<Notice> notices = page.getContent();
			System.out.println(notices.size() + "fffffffffffffffffffffff");
			List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
			for (Notice notice : notices) {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodes.add(objectNode);
				objectNode.put("id", notice.getId());
				objectNode.put("title", notice.getTitle());
			//	objectNode.put("content", notice.getContent().substring(0, notice.getContent().length()>200?200:notice.getContent().length()));
				objectNode.put("more", page.hasNext());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = notice.getReleaseTime();
				String createTime = sdf.format(date);
				objectNode.put("createTime", createTime);
			}
			jsonResponse.setResult(objectNodes);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常，请稍等");
			return jsonResponse;
		}
		return jsonResponse;
	}

	@RequestMapping("/notice")
	public String index(Model model, String orderId,String type) {
		model.addAttribute("type", type);
		return "/weixin/workbench/notice/notice";
	}
	
	
	@RequestMapping("/noticeDetail")
	public String noticeDetail(Model model, String id) {
		Notice notice =  noticeService.findOne(id);
		notice.setClickCount((notice.getClickCount()==null?0:notice.getClickCount())+1L);
		noticeService.save(notice);
		model.addAttribute("notice", notice);
		return "/weixin/workbench/notice/noticeDetail";
	}

	
	
}
