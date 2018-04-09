package cn.cnyirui.homaweixin.service.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.TypicalCaseCommentDao;
import cn.cnyirui.homaweixin.dao.backend.TypicalCaseDao;
import cn.cnyirui.homaweixin.model.po.TypicalCase;
import cn.cnyirui.homaweixin.model.po.TypicalCaseComment;

@Service
public class TypicalCaseCommentService extends BaseService<TypicalCaseComment>{

	@Resource
	private TypicalCaseCommentDao typicalCaseCommentDao;
	
	@Override
	public BaseDao<TypicalCaseComment> getBaseDao() {
		return typicalCaseCommentDao;
	}

}
