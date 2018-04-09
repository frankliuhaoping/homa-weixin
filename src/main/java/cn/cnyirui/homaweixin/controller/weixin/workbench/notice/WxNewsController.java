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
import cn.cnyirui.homaweixin.model.po.News;
import cn.cnyirui.homaweixin.service.backend.NewsService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
@RequestMapping("/weixin/workbench/news")
public class WxNewsController extends BaseCRUDController<News> {

	@Resource
	private NewsService newsService;

	@Override
	protected BaseService<News> getBaseService() {
		return newsService;
	}

	@RequestMapping("/findAllNews")
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
			Page<News> page = newsService.findAll(searchable);
			List<News> newss = page.getContent();
			System.out.println(newss.size() + "ggggg");
			List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
			for (News news : newss) {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodes.add(objectNode);
				objectNode.put("picture", news.getCoverPictureUrl());
				objectNode.put("id", news.getId());
				objectNode.put("title", news.getTitle());
				objectNode.put("content", news.getContent().substring(0, news.getContent().length()>200?200:news.getContent().length()));
				objectNode.put("more", page.hasNext());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = news.getReleaseTime();
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
	
	@RequestMapping("/newDetail")
	public String newDetail(Model model, String id) {
		News news = newsService.findOne(id);
		news.setClickCount((news.getClickCount()==null?0:news.getClickCount())+1L);
		newsService.save(news);
		model.addAttribute("news", news);
		return "/weixin/workbench/notice/newDetail";
	}

}
