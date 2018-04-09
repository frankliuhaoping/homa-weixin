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

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.model.po.News;
import cn.cnyirui.homaweixin.model.po.OtherInfo;
import cn.cnyirui.homaweixin.service.backend.OtherInfoService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@RequestMapping("/weixin/workbench/otherInfo")
public class WxOtherInfoController extends BaseCRUDController<OtherInfo> {

	@Resource
	private OtherInfoService otherInfoService;

	@Override
	protected BaseService<OtherInfo> getBaseService() {
		return otherInfoService;
	}

	@RequestMapping("/findAllOtherInfo")
	@ResponseBody
	public JsonResponse findAllNotice(Searchable searchable) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			List<Order> orders = new ArrayList<Sort.Order>();
			orders.add(new Order(Direction.DESC, "isTop"));
			orders.add(new Order(Direction.DESC, "createdTime"));
			searchable.addSort(new Sort(orders));
			searchable.addSearchFilter("isRelease", SearchOperator.eq, false);
			searchable.addSearchFilter("releaseTime", SearchOperator.lte, new Date());
			Page<OtherInfo> page = otherInfoService.findAll(searchable);
			List<OtherInfo> otherInfos = page.getContent();
			List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
			for (OtherInfo otherInfo : otherInfos) {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodes.add(objectNode);
				objectNode.put("picture", otherInfo.getCoverPictureUrl());
				objectNode.put("id", otherInfo.getId());
				objectNode.put("title", otherInfo.getTitle());
				objectNode.put("content", otherInfo.getContent().substring(0, otherInfo.getContent().length()>200?200:otherInfo.getContent().length()));
				objectNode.put("more", page.hasNext());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = otherInfo.getReleaseTime();
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
	
	@RequestMapping("/otherInfoDetail")
	public String otherInfoDetail(Model model, String id) {
		OtherInfo otherInfo = otherInfoService.findOne(id);
		otherInfo.setClickCount((otherInfo.getClickCount()==null?0:otherInfo.getClickCount())+1L);
		otherInfoService.save(otherInfo);
		model.addAttribute("otherInfo", otherInfo);
		return "/weixin/workbench/notice/otherInfoDetail";
	}

}
