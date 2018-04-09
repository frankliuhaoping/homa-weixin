package cn.cnyirui.homaweixin.service.weixin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.weixin.WxMarketingExperienceDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.MarketingExperience;
import cn.cnyirui.homaweixin.model.po.MarketingExperienceComment;
import cn.cnyirui.homaweixin.model.po.MarketingExperienceImages;

@Service
public class WxMarketingExperienceService extends BaseService<MarketingExperience> {
	
	@Resource
	private WxMarketingExperienceDao wxMarketingExperienceDao;

	@Override
	public BaseDao<MarketingExperience> getBaseDao() {
		return wxMarketingExperienceDao;
	}

	
	public List<ObjectNode> findMarketingExperience(Searchable searchable){
		Page<MarketingExperience> pageContent = wxMarketingExperienceDao.findAll(searchable);
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		for(MarketingExperience s : pageContent.getContent()){
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", s.getId());
			if(searchable.findSearchFilter("createdBy.employeeList.id", SearchOperator.eq).size() > 0){ //我的
				objectNode.put("createdTime", dataToUpper(s.getCreatedTime()));
			}else{ //所有人的
				objectNode.put("createdTime", sdf2.format(s.getCreatedTime()));
			}
			
			SysUser sysUser = s.getCreatedBy();
			Employee employee = sysUser.getEmployee();
			WeiXinUser weiXinUser = sysUser.getWeiXinUser();
			objectNode.put("employeeId", employee.getId());
			objectNode.put("nickName", employee.getName());
			objectNode.put("headImgUrl", weiXinUser == null ? "" : weiXinUser.getHeadImgUrl());
			//objectNode.put("categoryName", s.getCategory() == null ? "" : s.getCategory().getName());
			objectNode.put("content", s.getContent());
			
			// 图片
			List<ObjectNode> images = new ArrayList<ObjectNode>();
			for(MarketingExperienceImages image : s.getImageList()){
				ObjectNode on = JsonUtil.getObjectMapper().createObjectNode();
				on.put("imageUrl", image.getImageUrl());
				images.add(on);
			}
			
			objectNode.putPOJO("images", images);
			
			
			//评论
			List<ObjectNode> comments = new ArrayList<ObjectNode>();
			for(MarketingExperienceComment comment : s.getCommentList()){
				ObjectNode on = JsonUtil.getObjectMapper().createObjectNode();
				on.put("content", comment.getContent());
				Employee employee2 = comment.getCommentBy().getEmployee(); 
				on.put("name", employee2.getName());
				on.put("headImgUrl", employee2.getHeadImgUrl());
				on.put("createTime", sdf.format(comment.getCreatedTime()));
				comments.add(on);
			}
			objectNode.putPOJO("comments", comments);
			
			objectNodeList.add(objectNode);
		}
		return objectNodeList;
	}
	
	
	public String dataToUpper(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        String strMonth = "";
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        switch (month) {
		case 1:
			strMonth = "1";
			break;
		case 2:
			strMonth = "2";		
			break;
		case 3:
			strMonth = "3";
			break;
		case 4:
			strMonth = "4";
			break;
		case 5:
			strMonth = "5";
			break;
		case 6:
			strMonth = "6";
			break;
		case 7:
			strMonth = "7";
			break;
		case 8:
			strMonth = "8";
			break;
		case 9:
			strMonth = "9";
			break;
		case 10:
			strMonth = "10";
			break;
		case 11:
			strMonth = "11";
			break;
		case 12:
			strMonth = "12";
			break;
		
		default:
			break;
		}
        return day + "-" +  strMonth + "月";
    }
}
