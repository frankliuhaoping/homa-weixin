package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.MarketingExperienceDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.MarketingExperience;
import cn.cnyirui.homaweixin.service.backend.MarketingExperienceService;

@Controller
@RequestMapping("/backend/marketingExperience")
public class MarketingExperienceController extends BaseCRUDController<MarketingExperience> {
	
	@Resource
	MarketingExperienceService marketingExperienceService;
	
	@Resource
	private MarketingExperienceDao marketingExperienceDao;
	
	@Override
	protected BaseService<MarketingExperience> getBaseService() {
		// TODO Auto-generated method stub
		return marketingExperienceService;
	}
	
	
	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	public ModelAndView doView(String id) {
		ModelAndView m = new ModelAndView(getControllerConfig(getRequest()).getEditViewName());
		if (id != null) {
			MarketingExperience market =marketingExperienceService.getBaseDao().findOne(id);
			m.addObject("market", market);
			//图片信息
			m.addObject("imageList",market.getImageList());
			//评论
			m.addObject("comments",market.getCommentList());
			//评论人
			
			SysUser sysUser = market.getCreatedBy();
			Employee employee = sysUser.getEmployee();
			
			m.addObject("employee",employee);

	/*			//评论
				List<ObjectNode> comments = new ArrayList<ObjectNode>();
				for(MarketingExperienceComment comment : s.getCommentList()){
					ObjectNode on = JsonUtil.getObjectMapper().createObjectNode();
					on.put("content", comment.getContent());
					Employee employee2 = comment.getCommentBy().getEmployee(); 
					on.put("nickName", employee2.getNickName());
					on.put("headImgUrl", employee2.getHeadImgUrl());
					on.put("createTime", sdf.format(comment.getCreatedTime()));
					comments.add(on);
				}
				objectNode.putPOJO("comments", comments);*/
			
		
		setCommonModel(m, "edit");
		
		
	}
	
		return m;

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
			MarketingExperience market =marketingExperienceService.getBaseDao().findOne(id);
			m.addObject("market", market);
			//图片信息
			m.addObject("imageList",market.getImageList());
			//评论
			m.addObject("comments",market.getCommentList());
			//评论人
			
			SysUser sysUser = market.getCreatedBy();
			Employee employee = sysUser.getEmployee();
			
			m.addObject("employee",employee);

	/*			//评论
				List<ObjectNode> comments = new ArrayList<ObjectNode>();
				for(MarketingExperienceComment comment : s.getCommentList()){
					ObjectNode on = JsonUtil.getObjectMapper().createObjectNode();
					on.put("content", comment.getContent());
					Employee employee2 = comment.getCommentBy().getEmployee(); 
					on.put("nickName", employee2.getNickName());
					on.put("headImgUrl", employee2.getHeadImgUrl());
					on.put("createTime", sdf.format(comment.getCreatedTime()));
					comments.add(on);
				}
				objectNode.putPOJO("comments", comments);*/
			
		
			setCommonModel(m, "edit");
		
		
		}
		return m;
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 * @return 返回null 或者 JsonResult("yes", null);，表示保存成功
	 */
	@Override
	public JsonResult doDelete(String[] ids) {
		marketingExperienceService.deleteExperience(ids);
		return JsonResult.success();
	}
	
}
