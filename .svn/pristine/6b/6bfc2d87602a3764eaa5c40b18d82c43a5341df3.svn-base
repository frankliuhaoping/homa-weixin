package cn.cnyirui.homaweixin.controller.backend;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.controller.bind.annotation.PageableDefaults;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.ChatContent;
import cn.cnyirui.homaweixin.service.backend.ChatContentService;

@Controller
@RequestMapping("/backend/ChatContent")
public class ChatContentController extends BaseCRUDController<ChatContent> {

	@Resource
	private ChatContentService chatContentService;

	@Override
	protected BaseService<ChatContent> getBaseService() {
		// TODO Auto-generated method stub
		return chatContentService;
	}
	
	/**
	 * 导出聊天记录
	 * 
	 * @return jiny 2015-11-27
	 */
	@RequestMapping("/export")
	@ResponseBody
	public ObjectNode exportExcel(HttpServletRequest request, HttpServletResponse response,String hhid) {
		ObjectNode objectNode = chatContentService.exportExcel(request, response,hhid);
		return objectNode;
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		super.handleSearchFilter(searchable);
		String sendTime = searchable.getValue("sendTime", SearchOperator.gt);
		if (sendTime != null) {
			if (sendTime.equals("3")) {
				//最近三个月
				Date dNow = new Date(); // 当前时间
				Date dBefore3 = new Date();
				Calendar calendar = Calendar.getInstance(); // 得到日历
				calendar.setTime(dNow);// 把当前时间赋给日历
				calendar.add(calendar.MONTH, -3); // 设置为前3月
				dBefore3 = calendar.getTime(); // 得到前3月的时间
				long date = dBefore3.getTime();
				searchable.addSearchFilter("sendTime", SearchOperator.gt, date);
			} else if (sendTime.equals("6")) {
				//最近六个月
				Date dNow = new Date(); // 当前时间
				Date dBefore3 = new Date();
				Calendar calendar = Calendar.getInstance(); // 得到日历
				calendar.setTime(dNow);// 把当前时间赋给日历
				calendar.add(calendar.MONTH, -6); // 设置为前3月
				dBefore3 = calendar.getTime(); // 得到前3月的时间
				long date = dBefore3.getTime();
				searchable.addSearchFilter("sendTime", SearchOperator.gt, date);
			} else if (sendTime.equals("1")) {
				//最近一年
				Date dNow = new Date(); // 当前时间
				Date dBefore3 = new Date();
				Calendar calendar = Calendar.getInstance(); // 得到日历
				calendar.setTime(dNow);// 把当前时间赋给日历
				calendar.add(calendar.YEAR, -1); // 设置为前3月
				dBefore3 = calendar.getTime(); // 得到前3月的时间
				long date = dBefore3.getTime();
				searchable.addSearchFilter("sendTime", SearchOperator.gt, date);
			}
		}
		
	}

	/**
	 * 聊天内容
	 * 
	 * @return jiny 2015-11-27
	 */
	@RequestMapping("/Content")
	@ResponseBody
	@PageableDefaults(sort = { "sendTime=desc" })
	public ObjectNode getChatContentList(Searchable searchable, HttpServletRequest request) {
		handleSearchFilter(searchable);
		String hhid = searchable.getValue("hhid", SearchOperator.eq);
		ObjectNode objectNode = null;
		if (hhid != null && !hhid.equals("")) {
			objectNode = chatContentService.getChatContent(hhid);
		} else {
			String searContent = searchable.getValue("content", SearchOperator.like);
			objectNode = super.doJsonList(searchable, request);
			objectNode.put("searContent", searContent);
		}
		
		return objectNode;
	}

}
