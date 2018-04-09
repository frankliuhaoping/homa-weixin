package cn.cnyirui.homaweixin.dao.weixin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.ChatGroup;

/**
 * 聊天通讯录
 * 
 * @author zhoujuhui
 *
 */
public interface ChatGroupDao extends BaseDao<ChatGroup> {

	/**
	 * 获取用户的群组信息
	 * @param parentId
	 * @return
	 */
	@Query("select p.chatGroup from ChatSessionMember p where p.employee.id = ?1 and p.chatGroup.isDel = 0 order by p.chatGroup.createdTime desc ")
	List<ChatGroup> getByEmployeeId(String employeeId);
}
