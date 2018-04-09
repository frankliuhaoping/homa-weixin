package cn.cnyirui.homaweixin.service.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.dao.search.utils.QueryUtils;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.dao.backend.SalesOrderDao;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.po.SalesOrder;

@Service
public class SalesOrderService extends BaseService<SalesOrder> {
	@Resource
	private SalesOrderDao salesOrderDao;
	@Resource
	private OrganizationService organizationService;

	@Override
	public BaseDao<SalesOrder> getBaseDao() {
		return salesOrderDao;
	}

	// public List<Map<String, Object>> findOrders(Searchable searchable) {
	// String ql = null;
	// String parentId = searchable.getValue("e.organizationParentId",
	// SearchOperator.eq);
	// if (parentId != null) {
	// searchable.removeSearchFilter("e.organizationParentId",
	// SearchOperator.eq);
	// String callId = organizationService.createSubOrganizationList(parentId);
	//
	// ql = "SELECT e.name as dgname,SUM(h.price*h.qty) as je,COUNT(h.id) as sl
	// from sales_order s LEFT JOIN sales_order_detail h ON s.id =
	// h.salesOrderId "
	// + " LEFT JOIN employee e on s.salerId = e.id"
	// + " INNER JOIN organization_tree_temp t ON e.organizationId =
	// t.organizationId AND t.callId = '"
	// + callId + "'" + " GROUP BY e.`name`";
	// } else {
	// ql = "SELECT e.name as dgname,SUM(h.price*h.qty) as je,COUNT(h.id) as sl
	// from sales_order s LEFT JOIN sales_order_detail h ON s.id =
	// h.salesOrderId "
	// + " LEFT JOIN employee e on s.salerId = e.id " + " GROUP BY e.`name`";
	// }
	// Page<?> page = QueryUtils.findAll(salesOrderDao.getEntityManager(), ql,
	// searchable, null, true, HashMap.class);
	// @SuppressWarnings("unchecked")
	// List<Map<String, Object>> list = (List<Map<String, Object>>)
	// page.getContent();
	// return list;
	//
	// }

	public ObjectNode findOrdersDb(Searchable searchable, HttpServletRequest request) {
		searchable.removeSort();
		String nodeType = ServletRequestUtils.getStringParameter(request, "nodeType", "root");

		String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
		if (organizationId != null) {
			searchable.removeSearchFilter("organization.id", SearchOperator.eq);
		}

		String sql = null;
		List<Organization> organizations = null;
		if ("leaf".equalsIgnoreCase(nodeType)) {
			sql = "select * from v_sales_order_h v ";
			searchable.addSearchFilter("v.organizationId", SearchOperator.eq, organizationId);
		} else if ("root".equalsIgnoreCase(nodeType) || StringUtils.isBlank(nodeType)) {
			sql = "select v.* from v_sales_order_h v, organization_tree_temp tt where v.organizationId = tt.organizationId";
			searchable.addSearchFilter("tt.callId", SearchOperator.eq, SessionUtils.getSession().getId().toString());
		} else {
			sql = "select * from v_sales_o v ";
			searchable.addSearchFilter("tt.callId", SearchOperator.eq, SessionUtils.getSession().getId().toString());
			Organization o = organizationService.findOne(organizationId);
			organizations = o.getSubOrganizationList();
		}
		if (organizations != null) {
			List<String> idList = new ArrayList<String>();
			for (Organization organization : organizations) {
				idList.add(organization.getId());
			}
			searchable.addSearchFilter("v.organizationId", SearchOperator.in, idList);

		}

		Page<?> page = QueryUtils.findAll(salesOrderDao.getEntityManager(), sql, searchable, null, true, HashMap.class);
		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		objectNode.put("total", page.getTotalElements());
		objectNode.putPOJO("rows", page.getContent());
		return objectNode;
	}

	public ObjectNode countSalesOrderByProduct(Searchable searchable, HttpServletRequest request) {
		String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
		searchable.removeSearchFilter("organization.id", SearchOperator.eq);

		StringBuilder ql = new StringBuilder(
		        "SELECT s.PRODUCTCATEGORYNAME, s.PRODUCTNAME, SUM(s.QTY) QTY, SUM(s.AMOUNT) AMOUNT FROM V_SALES_ORDER_PRODUCT s");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, ql, "s");
		ql.append(" GROUP BY s.PRODUCTCATEGORYNAME, s.PRODUCTNAME");

		StringBuilder sumQl = new StringBuilder(
		        "SELECT '总计' PRODUCTNAME, SUM(s.QTY) QTY, SUM(s.AMOUNT) AMOUNT FROM V_SALES_ORDER_PRODUCT s");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, sumQl, "s");

		SearchFilterHelper.wrapperDateTimeSearchFilter(searchable.getSearchFilters(), "s.salesTime");
		if (!searchable.hashSort()) {
			searchable.addSort(Direction.DESC, "QTY");
		}

		Page<?> page = QueryUtils.findAll(salesOrderDao.getEntityManager(), ql.toString(), searchable, null, true,
		        HashMap.class);

		searchable.removePageable();
		searchable.removeSort();
		Page<?> sumPage = QueryUtils.findAll(salesOrderDao.getEntityManager(), sumQl.toString(), searchable, null, true,
		        HashMap.class);

		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		objectNode.put("total", page.getTotalElements());
		objectNode.putPOJO("rows", page.getContent());
		objectNode.putPOJO("footer", sumPage.getContent());
		return objectNode;
	}

	public ObjectNode countSalesOrderByProductCategory(Searchable searchable, HttpServletRequest request) {
		String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
		searchable.removeSearchFilter("organization.id", SearchOperator.eq);

		StringBuilder ql = new StringBuilder(
		        "SELECT s.PRODUCTCATEGORYNAME, SUM(s.QTY) QTY, SUM(s.AMOUNT) AMOUNT FROM V_SALES_ORDER_PRODUCT s");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, ql, "s");
		ql.append(" GROUP BY s.PRODUCTCATEGORYNAME");

		StringBuilder sumQl = new StringBuilder(
		        "SELECT '总计' PRODUCTCATEGORYNAME, SUM(s.QTY) QTY, SUM(s.AMOUNT) AMOUNT FROM V_SALES_ORDER_PRODUCT s");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, sumQl, "s");

		SearchFilterHelper.wrapperDateTimeSearchFilter(searchable.getSearchFilters(), "s.salesTime");
		if (!searchable.hashSort()) {
			searchable.addSort(Direction.DESC, "QTY");
		}

		Page<?> page = QueryUtils.findAll(salesOrderDao.getEntityManager(), ql.toString(), searchable, null, true,
		        HashMap.class);

		searchable.removePageable();
		searchable.removeSort();
		Page<?> sumPage = QueryUtils.findAll(salesOrderDao.getEntityManager(), sumQl.toString(), searchable, null, true,
		        HashMap.class);

		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		objectNode.put("total", page.getTotalElements());
		objectNode.putPOJO("rows", page.getContent());
		objectNode.putPOJO("footer", sumPage.getContent());
		return objectNode;
	}

	public ObjectNode countSalesOrderByEmployee(Searchable searchable, HttpServletRequest request) {
		String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
		searchable.removeSearchFilter("organization.id", SearchOperator.eq);

		StringBuilder ql = new StringBuilder();
		ql.append("SELECT s.SALERNAME, s.ORGANIZATIONNAME, s.SALERTYPETEXT, s.IDNUMBER, SUM(s.QTY) QTY,")
		        .append(" SUM(s.AMOUNT) AMOUNT FROM V_SALES_ORDER_EMPLOYEE s");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, ql, "s");
		ql.append(" GROUP BY s.SALERNAME, s.ORGANIZATIONNAME, s.SALERTYPETEXT, s.IDNUMBER");

		StringBuilder sumQl = new StringBuilder();
		sumQl.append("SELECT '总计' IDNUMBER, SUM(s.QTY) QTY, SUM(s.AMOUNT) AMOUNT FROM V_SALES_ORDER_EMPLOYEE s");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, sumQl, "s");

		SearchFilterHelper.wrapperDateTimeSearchFilter(searchable.getSearchFilters(), "s.salesTime");
		if (!searchable.hashSort()) {
			searchable.addSort(Direction.DESC, "QTY");
		}
		Page<?> page = QueryUtils.findAll(salesOrderDao.getEntityManager(), ql.toString(), searchable, null, true,
		        HashMap.class);

		searchable.removePageable();
		searchable.removeSort();
		Page<?> sumPage = QueryUtils.findAll(salesOrderDao.getEntityManager(), sumQl.toString(), searchable, null, true,
		        HashMap.class);
		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		objectNode.put("total", page.getTotalElements());
		objectNode.putPOJO("rows", page.getContent());
		objectNode.putPOJO("footer", sumPage.getContent());
		return objectNode;
	}

	@SuppressWarnings("rawtypes")
	public ObjectNode findSalesOrders(Searchable searchable) {
		String organizationId = searchable.getValue("organization.id", SearchOperator.eq);
		searchable.removeSearchFilter("organization.id", SearchOperator.eq);

		StringBuilder ql = new StringBuilder();
		ql.append(
		        "select d.id, s.customerName,e.name salename,s.createdTime createdTime,s.customerTel,s.customerAddress,s.invoiceNo,invoiceImageUrl,"
		                + " to_char(s.salesTime, 'yyyy-MM-dd HH24:mi:ss') salesTime, p.name, t.parentNames,"
		                + " d.price,d.qty,d.price*d.qty as zje,d.cashBack from sales_order s, sales_order_detail d, product p, employee e");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, ql, "s");
		ql.append(" and s.id = d.salesOrderId");
		ql.append(" and d.productId = p.id");
		ql.append(" and e.id = s.salerId");

		StringBuilder sumSql = new StringBuilder();
		sumSql.append("select '总计' name, sum(d.qty) qty, sum(d.price*d.qty) zje, sum(d.cashBack) cashBack"
		        + " from sales_order s, sales_order_detail d, product p, employee e");
		SearchFilterHelper.addOrganizationLimitSearchFilter(organizationId, sumSql, "s");
		sumSql.append(" and s.id = d.salesOrderId");
		sumSql.append(" and d.productId = p.id");
		sumSql.append(" and e.id = s.salerId");

		SearchFilterHelper.wrapperDateTimeSearchFilter(searchable.getSearchFilters(), "salesTime");

		Page<HashMap> page = QueryUtils.findAll(salesOrderDao.getEntityManager(), ql.toString(), searchable, null, true,
		        HashMap.class);
		searchable.removePageable();
		searchable.removeSort();
		Page<HashMap> sumPage = QueryUtils.findAll(salesOrderDao.getEntityManager(), sumSql.toString(), searchable,
		        null, true, HashMap.class);

		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		objectNode.put("total", page.getTotalElements());
		objectNode.putPOJO("rows", page.getContent());
		objectNode.putPOJO("footer", sumPage.getContent());

		return objectNode;

	}

	public SalesOrder findOneBySalerIdAndOrderId(String salerId, String orderId) {
		return salesOrderDao.findOneBySalerIdAndOrderId(salerId, orderId);
	}

	public List<ObjectNode> findOrdersByType(String salerId, String keyWord, Integer type, Long currentPage, Date fdate,
	        Date ldate) {
		Long cp = currentPage == null ? 0 : currentPage;
		keyWord = keyWord == null ? "" : keyWord;
		keyWord = "%" + keyWord + "%";
		System.err.println(keyWord);
		Pageable pageable = new PageRequest(cp.intValue(), 10, Direction.DESC, "salesTime");
		Page<SalesOrder> page = null;
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		if (fdate == null || ldate == null) {
			page = salesOrderDao.findOrdersByTypeAll(salerId, keyWord, pageable);
		} else {
			page = salesOrderDao.findOrdersByTime(salerId, keyWord, fdate, ldate, pageable);

		}

		for (SalesOrder s : page.getContent()) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("phone", s.getCustomerTel() == null ? "暂无" : s.getCustomerTel());
			objectNode.put("customerName", s.getCustomerName() == null ? "" : s.getCustomerName());
			SimpleDateFormat ss = new SimpleDateFormat("MM-dd");
			objectNode.put("salesTime", ss.format(s.getSalesTime()));
			ss = new SimpleDateFormat("HH:mm");
			objectNode.put("time", ss.format(s.getSalesTime()));
			objectNode.put("salesMoney", s.getSalesMoney() == null ? 0 : s.getSalesMoney());
			objectNode.put("invoiceNo", s.getOrderNoN() == null ? "" : s.getOrderNoN());
			objectNode.put("id", s.getId());
			objectNode.put("more", page.hasNext() ? 1 : 0);
			objectNode.put("flag", (s.getInvoiceNo() != null) && (s.getInvoiceImageUrl() != null));
			objectNodeList.add(objectNode);
		}

		return objectNodeList;
	}

	public List<SalesOrder> findOrderByMonth(String employeeId, Date fdate, Date ldate) {
		// TODO Auto-generated method stub
		return salesOrderDao.findOrderByMonth(employeeId, fdate, ldate);
	}

	/**
	 * 部门统计
	 * 
	 * @param callId
	 * @return
	 */
	/*
	 * public Page<SalesOrder> getCountOfDepartment(String callId,String eid) {
	 * return salesOrderDao.getCountOfDepartment( callId, eid);
	 * 
	 * }
	 */

	/**
	 * 按微信二维码的场景ID查找
	 * 
	 * @param sceneId
	 * @return
	 */
	public SalesOrder findByWeiXinQrCodeSceneId(String sceneId) {
		List<SalesOrder> salesOrderList = salesOrderDao.findByWeiXinQrCodeSceneId(Long.valueOf(sceneId),
		        new PageRequest(0, 1));
		if (!salesOrderList.isEmpty()) {
			return salesOrderList.get(0);
		}
		return null;
	}

	/**
	 * 统计个人 销售额
	 */
	public Double countByEmployee(String employeeId, Integer year, Integer month) {
		String ql = "SELECT sum(salesMoney) as salesMoney from SalesOrder s";
		Searchable searchable = Searchable.newSearchable()
		        .addSearchFilter("salerId", SearchOperator.eq, employeeId);
		if (year != null) {
			searchable.addSearchFilter("YEAR(salesTime)", SearchOperator.eq, year);
		}
		if (month != null) {
			searchable.addSearchFilter("MONTH(salesTime)", SearchOperator.eq, month);
		}
		return NumberUtils.toDouble(String.valueOf(salesOrderDao.getScalarValue(ql, searchable, false)));
	}

}
