package cn.cnyirui.homaweixin.controller.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.controller.bind.annotation.PageableDefaults;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.model.po.TypicalCase;
import cn.cnyirui.homaweixin.model.po.TypicalCaseComment;
import cn.cnyirui.homaweixin.service.backend.TypicalCaseCommentService;
import cn.cnyirui.homaweixin.service.backend.TypicalCaseService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
@RequestMapping("/backend/typicalCase")
public class TypicalCaseController extends BaseCRUDController<TypicalCase> {

	@Resource
	private TypicalCaseService typicalCaseService;
	@Resource
	private TypicalCaseCommentService  typicalCaseCommentService;

	@Override
	protected BaseService<TypicalCase> getBaseService() {
		return typicalCaseService;
	}

	@RequestMapping("/stick/{id}")
	@ResponseBody
	public JsonResult stick(@PathVariable String id) {
		if (id != null) {
			TypicalCase typicalCase = typicalCaseService.findOne(id);
			typicalCase.setIsTop(true);
			typicalCaseService.save(typicalCase);
			return JsonResult.success("yes");
		}
		return JsonResult.error("no");
	}

	@RequestMapping("/comment/{id}")
	public ModelAndView comment(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("/backend/typicalCase/comment");
		if (id != null) {
			TypicalCase typicalCase = typicalCaseService.findOne(id);
			mav.addObject("commentList", typicalCase.getCommentList());
		}
		return mav;
	}

	@PageableDefaults(sort = { "isTop=desc", "createdTime=desc" })
	@ResponseBody
	@Override
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		return super.jsonList(searchable, request);
	}

	@RequestMapping("/findAllTypicalCases")
	@ResponseBody
	public JsonResponse findAllTypicalCases(Searchable searchable) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			List<Order> orders = new ArrayList<Sort.Order>();
			orders.add(new Order(Direction.DESC, "isTop"));
			orders.add(new Order(Direction.DESC, "createdTime"));
			searchable.addSort(new Sort(orders));
			searchable.addSearchFilter("isRelease", SearchOperator.eq, false);
			searchable.addSearchFilter("releaseTime", SearchOperator.lte, new Date());
			Page<TypicalCase> page = typicalCaseService.findAll(searchable);
			List<TypicalCase> typicalCases = page.getContent();
			List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
			for (TypicalCase typicalCase : typicalCases) {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodes.add(objectNode);
				objectNode.put("id", typicalCase.getId());
				objectNode.put("title", typicalCase.getTitle());
			//	objectNode.put("content", typicalCase.getContent().substring(0, typicalCase.getContent().length()>200?200:typicalCase.getContent().length()));
				objectNode.put("more", page.hasNext());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = typicalCase.getReleaseTime();
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
	
	
	@RequestMapping("/typicalCasesDetail")
	public String noticeDetail(Model model, String id) {
		TypicalCase typicalCase = typicalCaseService.findOne(id);
		typicalCase.setClickCount((typicalCase.getClickCount()==null?0:typicalCase.getClickCount())+1L);
		typicalCaseService.save(typicalCase);
		model.addAttribute("typicalCase", typicalCase);
		return "/weixin/workbench/notice/typicalCasesDetail";
	}
	
	
	@RequestMapping("/saveComment")
	@ResponseBody
	public JsonResponse saveComment(String id,String content) {
		
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			TypicalCase typicalCase = typicalCaseService.findOne(id);
			TypicalCaseComment typicalCaseComment = new TypicalCaseComment();
			typicalCaseComment.setCommentTime(new Date());
			typicalCaseComment.setContent(content);
			typicalCaseComment.setTypicalCase(typicalCase);
			typicalCaseComment.setCommentBy(CurrentUserUtils.getSysUser());
			typicalCaseCommentService.save(typicalCaseComment);
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			return jsonResponse;
			// TODO: handle exception
		}
		return jsonResponse;
	}
	
	
	
	@RequestMapping("/getMoreComment")
	@ResponseBody
	public JsonResponse getMoreComment(Searchable searchable) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			searchable.addSort(Direction.DESC, "commentTime");
			Page<TypicalCaseComment> page = typicalCaseCommentService.findAll(searchable);
			List<TypicalCaseComment> typicalCaseComments = page.getContent();
			List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
			for (TypicalCaseComment typicalCase : typicalCaseComments) {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodes.add(objectNode);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				objectNode.put("time", sdf.format(typicalCase.getCommentTime()));
				objectNode.put("content", typicalCase.getContent());
				String name = "";
				if(typicalCase.getCommentBy().getEmployee()!=null){
					name = typicalCase.getCommentBy().getEmployee().getName();
				}
				objectNode.put("name", name);
				objectNode.put("more", page.hasNext());
			}
			jsonResponse.setResult(objectNodes);
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			return jsonResponse;
			// TODO: handle exception
		}
		return jsonResponse;
	}
	
	
	
	
}
