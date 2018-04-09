package cn.cnyirui.homaweixin.service.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.NewsDao;
import cn.cnyirui.homaweixin.model.po.News;

@Service
public class NewsService extends BaseService<News> {

	@Resource
	private NewsDao newsDao;

	@Override
	public BaseDao<News> getBaseDao() {
		return newsDao;
	}
}
