package cn.cnyirui.homaweixin.service.backend;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.utils.QueryUtils;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.SalesOrderDao;
import cn.cnyirui.homaweixin.dao.backend.SalesOrderDetailDao;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.po.SalesOrder;
import cn.cnyirui.homaweixin.model.po.SalesOrderDetail;

@Service
public class SalesOrderDetailService extends BaseService<SalesOrderDetail> {
	@Resource
	private SalesOrderDetailDao salesOrderDetailDao;
	@Resource
	private OrganizationService organizationService;

	@Override
	public BaseDao<SalesOrderDetail> getBaseDao() {
		return salesOrderDetailDao;
	}
}
