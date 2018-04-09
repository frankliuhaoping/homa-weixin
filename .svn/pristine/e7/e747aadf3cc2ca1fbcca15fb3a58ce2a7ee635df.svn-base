package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.ChatSession;
import cn.cnyirui.homaweixin.service.backend.ChatHistroyService;

@Controller
@RequestMapping("/backend/chatHistory")
public class ChatHistoryController extends BaseCRUDController<ChatSession> {

	@Resource
	private ChatHistroyService chatHistroyService;

	@Override
	protected BaseService<ChatSession> getBaseService() {
		// TODO Auto-generated method stub
		return chatHistroyService;
	}


	/**
	 * 聊天列表
	 * 
	 * @return jiny 2015-11-27
	 */
	@ResponseBody
	public ObjectNode doJsonList(Searchable searchable, HttpServletRequest request) {
		ObjectNode objectNode = chatHistroyService.getChatHistoryList();
		return objectNode;
	}
}
