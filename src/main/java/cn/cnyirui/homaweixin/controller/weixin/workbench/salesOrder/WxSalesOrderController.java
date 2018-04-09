package cn.cnyirui.homaweixin.controller.weixin.workbench.salesOrder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.extension.weixin.WxMpSpringCacheConfigStorge;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.model.po.Customer;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Product;
import cn.cnyirui.homaweixin.model.po.ProductCategory;
import cn.cnyirui.homaweixin.model.po.SalesOrder;
import cn.cnyirui.homaweixin.model.po.SalesOrderDetail;
import cn.cnyirui.homaweixin.service.backend.CustomerService;
import cn.cnyirui.homaweixin.service.backend.ProductCategoryService;
import cn.cnyirui.homaweixin.service.backend.ProductService;
import cn.cnyirui.homaweixin.service.backend.SalesOrderDetailService;
import cn.cnyirui.homaweixin.service.backend.SalesOrderService;
import cn.cnyirui.homaweixin.service.webPush.IContentPushService;
import cn.cnyirui.homaweixin.utils.Base64Utils;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.PropertiesUtils;
import cn.cnyirui.homaweixin.utils.ResponseCode;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage.WxArticle;

@Controller
public class WxSalesOrderController {

	public static String webSocketPushCenterUrl = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL")
	        + "/iker_web_socket_push_center";

	@Resource
	private SalesOrderService salesOrderService;
	@Resource
	private SalesOrderDetailService salesOrderDetailService;
	@Resource
	private ProductCategoryService productCategoryService;
	@Resource
	private ProductService productService;
	@Resource
	private CustomerService customerService;
	@Resource
	private WxMpService wxMpService;
	@Resource
	private WxMpInMemoryConfigStorage wxMpConfigStorage;

	@Resource(name = "contentPushServiceImpl")
	private IContentPushService iContentPushService; // 推送给client

	/**
	 * 创建订单
	 * @param model
	 * @param orderId
	 * @param searchable
	 * @return
	 */
	@RequestMapping("/weixin/workbench/salesOrder/createOrder")
	public String createOrder(Model model, String orderId, Searchable searchable) {
		SalesOrder salesOrder = null;
		if (orderId != null && !orderId.equals("")) {
			salesOrder = salesOrderService.getBaseDao().getOne(orderId);
		}
		model.addAttribute("salesOrder", salesOrder);

		Page<ProductCategory> products = productCategoryService.findAllProductCategory(true, searchable);
		model.addAttribute("products", products.getContent());
		return "/weixin/workbench/salesOrder/createOrder";
	}

	@RequestMapping("/weixin/workbench/salesOrder/index")
	public String index(Model model) {
		Employee employee = CurrentUserUtils.getEmployee(false);

		// System.err.println("send class num : "+SendClass.num++);

		System.err.println(employee.getId());
		return "/weixin/workbench/salesOrder/index";
	}

	// @RequestMapping("/weixin/workbench/salesOrder/addProduct")
	// public String addProductIndex(Model model, Searchable searchable) {
	//
	// return "/weixin/workbench/salesOrder/addProduct";
	// }

	@RequestMapping("/weixin/workbench/salesOrder/productDetail")
	public String salesOrderDetail(Model model, String orderId) {
		Employee employee = CurrentUserUtils.getEmployee(false);
		SalesOrder salesOrder = salesOrderService.findOneBySalerIdAndOrderId(employee.getId(), orderId);
		//订单 不存在
		model.addAttribute("salesOrder", salesOrder);
		int productsSum = 0;
		for (SalesOrderDetail salesOrderDetail : salesOrder.getSalesOrderDetailList()) {
			productsSum += salesOrderDetail.getQty();
		}
		model.addAttribute("productsSum", productsSum);
		return "/weixin/workbench/salesOrder/productDetail";
	}

	@RequestMapping("/weixin/workbench/salesOrder/cardUpload")
	public String cardUpload(Model model, String orderId) {
		model.addAttribute("employee", CurrentUserUtils.getEmployee(false));
		SalesOrder salesOrder = salesOrderService.findOne(orderId);
		model.addAttribute("salesOrder", salesOrder);
		model.addAttribute("orderId", orderId);
		return "/weixin/workbench/salesOrder/cardUpload";
	}

	@RequestMapping("/weixin/workbench/salesOrder/ewm")
	public String ewm(Model model, String orderId) {
		model.addAttribute("orderId", orderId);
		model.addAttribute("webSocketPushCenterUrl", webSocketPushCenterUrl);
		return "/weixin/workbench/salesOrder/ewm";
	}

	@RequestMapping("/weixin/customer/salesOrder/customerRecv")
	public String customerRecv(Model model, String orderId) {
		SalesOrder salesOrder = salesOrderService.findOne(orderId);
		if (salesOrder == null) {
			return "/weixin/workbench/salesOrder/emptyOrder";
		}
		model.addAttribute("salesOrder", salesOrder);
		int productsSum = 0;
		for (SalesOrderDetail salesOrderDetail : salesOrder.getSalesOrderDetailList()) {
			productsSum += salesOrderDetail.getQty();
		}
		model.addAttribute("productsSum", productsSum);
		model.addAttribute("orderId", orderId);
		return "/weixin/workbench/salesOrder/customerRecv";
	}
	
	
	/**
	 * 
	 * 重复提交
	 * @param name
	 * @param invoke
	 * @param searchable
	 * @return
	 */
	@RequestMapping("/weixin/workbench/salesOrder/beforeSave")
	@ResponseBody
	public JsonResponse beforeSave(String name,String invoke,Searchable searchable) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		jsonResponse.setMsg("订单删除成功");
		try {
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date date1 = calendar.getTime();
			
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date date2 = calendar.getTime();
			
			searchable.addSearchFilter("salesTime", SearchOperator.gt, date1);
			searchable.addSearchFilter("salesTime", SearchOperator.lt, date2);
			
			if(name!=null && !name.equals("")){
				searchable.addSearchFilter("customerName", SearchOperator.eq, name);
			}else{
				searchable.addSearchFilter("invoiceNo",SearchOperator.eq,invoke);
			}
			
			
			int size = salesOrderService.findAll(searchable).getContent().size();
			if(size>0){
				jsonResponse = new JsonResponse(ResponseCode.不允许重复);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	/**
	 * 
	 * 删除订单
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/weixin/customer/salesOrder/deleteOrder")
	@ResponseBody
	public JsonResponse deleteOrder(String orderId) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		jsonResponse.setMsg("订单删除成功");
		try {
			Employee employee = CurrentUserUtils.getEmployee(false);
			SalesOrder salesOrder = salesOrderService.findOne(orderId);
			if (salesOrder == null) {
				jsonResponse.setMsg("订单不存在");
				return jsonResponse;
			}

			if (!salesOrder.getSaler().getId().equals(employee.getId())) {
				jsonResponse.setMsg("订单不存在");
				return jsonResponse;
			}

			if (salesOrder.getIsUpload() == true) {
				jsonResponse.setMsg("订单已上传到内销系统，不能删除");
				return jsonResponse;
			}
			List<SalesOrderDetail> salesOrderDetails = salesOrder.getSalesOrderDetailList();
			for (SalesOrderDetail o : salesOrderDetails) {
				salesOrderDetailService.delete(o.getId());
			}
			salesOrderService.delete(salesOrder.getId());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	@RequestMapping("/weixin/customer/salesOrder/customerSave")
	@ResponseBody
	public JsonResponse customerSave(String customerName, String customerAddr, String customerPhone, String orderId) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		jsonResponse.setMsg("保存成功");
		try {
			Customer customer = customerService.getByWinxinId(CurrentUserUtils.getWeiXinUser(false).getId());
			if (customer == null) {
				customer = new Customer();
			}
			customer.setAddress(customerAddr);
			customer.setName(customerName);
			customer.setTelephone(customerPhone);
			customerService.save(customer);
			SalesOrder salesOrder = salesOrderService.findOne(orderId);
			if (salesOrder == null) {
				jsonResponse.setMsg("订单不存在");
				return jsonResponse;
			}
			if (salesOrder.getIsUpload()) {
				jsonResponse.setMsg("订单已经上报内销系统，不能再修改个人信息");
				return jsonResponse;
			}

			String sendmsg = "";
			if (salesOrder.getCustomer() == null) {
				sendmsg = "订单号:" + salesOrder.getOrderNoN() + " \n下单成功" + "\n";

			} else {
				sendmsg = "订单号:" + salesOrder.getOrderNoN() + " \n修改成功" + "\n";
			}
			sendmsg += "客户姓名:" + customer.getName() + "\n"
			        + "电话号码:" + customer.getTelephone();

			salesOrder.setCustomerName(customerName);
			salesOrder.setCustomerAddress(customerAddr);
			salesOrder.setCustomerTel(customerPhone);
			salesOrder.setCustomer(customer);
			salesOrderService.save(salesOrder);
			// message.setToUser(toUser);

			if (salesOrder != null) {
				StringBuilder s = new StringBuilder();
				s.append("顾客姓名：" + customerName);
				s.append("\r\n");
				s.append("收货地址：" + customerAddr);
				s.append("\r\n");
				s.append("联系电话：" + customerPhone);
				s.append("\r\n");
				Double totalAmount = 0d;
				List<SalesOrderDetail> salesOrderDetailList = salesOrder.getSalesOrderDetailList();
				for (SalesOrderDetail salesOrderDetail : salesOrderDetailList) {
					Product product = salesOrderDetail.getProduct();
					s.append("产品名称：").append(product != null ? product.getName() : "").append("\r\n");
					s.append("购买数量：").append(salesOrderDetail.getQty()).append("\r\n");
					s.append("购买价格：").append(salesOrderDetail.getPrice()).append("\r\n");
					totalAmount += salesOrderDetail.getQty() * salesOrderDetail.getPrice();
					s.append("\r\n");
				}
				s.append("总计金额：").append(totalAmount);
				s.append("\r\n");
				s.append("点击查看订单详情");
				WxArticle wxArticle = new WxArticle();
				wxArticle.setTitle("客户已经完成订单");
				wxArticle.setDescription(s.toString());

				wxArticle.setUrl(((WxMpSpringCacheConfigStorge) wxMpConfigStorage).getBaseUrl() +
				        "weixin/workbench/salesOrder/productDetail?orderId=" + salesOrder.getId());
				wxMpService.customMessageSend(WxMpCustomMessage
				        .NEWS()
				        .toUser(salesOrder.getSaler().getSysUser().getWeiXinUser().getOpenId())
				        .addArticle(wxArticle)
				        .build());
			}

			/*
			 * wxMpService.customMessageSend(WxMpCustomMessage
			 * .TEXT()
			 * .toUser(salesOrder.getSaler().getSysUser().getWeiXinUser().
			 * getOpenId())
			 * .content("")
			 * .build());
			 * }
			 */

			// 推送
			String dest = "/message/receive_ewm_message/order/" + salesOrder.getId();
			iContentPushService.pushMessage(dest, "{success:success}");

		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	@RequestMapping("/weixin/workbench/salesOrder/productListJsonOnly")
	@ResponseBody
	public JsonResponse productListJsonOnly(String keyWord, Long currentPage) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			currentPage = currentPage == null ? 0 : currentPage;
			Pageable pageable = new PageRequest(currentPage.intValue(), 9);
			Page<Product> page = productService.findProductByKeyWord(keyWord, pageable);
			List<Product> products = page.getContent();
			List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
			for (Product product : products) {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodeList.add(objectNode);
				objectNode.put("id", product.getId());
				objectNode.put("text", product.getName());
				objectNode.put("more", page.hasNext() ? 1 : 0);
			}
			jsonResponse.setResult(objectNodeList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	@RequestMapping("/weixin/workbench/salesOrder/saveOrder")
	@ResponseBody
	public JsonResponse saveOrder(String salesOrderId, String[] productsId, Long[] productsNum, Double[] productsPrice,
	        Double productsMoney, Double[] retMoneys,
	        Double retMoney, String customerName, String customerAddr, String customerPhone) {
		System.err.println(productsId);
		System.err.println(productsPrice);
		System.err.println(productsNum);
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {

			List<SalesOrderDetail> salesOrderDetails = new ArrayList<SalesOrderDetail>();

			SalesOrder salesOrder = null;
			if (salesOrderId == null || salesOrderId.equals("")) {
				salesOrder = new SalesOrder();
				salesOrder.setOrderNoN(buildInvoiceNo());
			} else {
				salesOrder = salesOrderService.getBaseDao().getOne(salesOrderId);
				List<SalesOrderDetail> list = salesOrder.getSalesOrderDetailList();
				for (SalesOrderDetail o : list) {
					o.setSalesOrder(null);
				}
			}
			salesOrder.setIsApproved(true);
			salesOrder.setSaler(CurrentUserUtils.getEmployee(false));
			if (salesOrder.getId() == null || salesOrder.getId().equals(""))
				salesOrder.setSalesTime(new Date());
			salesOrder.setRetMoney(retMoney);
			salesOrder.setCustomerName(customerName);
			salesOrder.setCustomerAddress(customerAddr);
			salesOrder.setCustomerTel(customerPhone);
			salesOrder.setSalesMoney(productsMoney);
			salesOrder.setSalesOrderDetailList(salesOrderDetails);
			salesOrder.setOrganization(CurrentUserUtils.getOrganization(false));
			salesOrderService.save(salesOrder);

			for (int i = 0; i < productsId.length; i++) {
				SalesOrderDetail salesOrderDetail = new SalesOrderDetail();
				salesOrderDetail.setPrice(productsPrice[i]);
				salesOrderDetail.setProduct(productService.getBaseDao().getOne(productsId[i]));
				salesOrderDetail.setSalesOrder(salesOrder);
				salesOrderDetails.add(salesOrderDetail);
				salesOrderDetail.setQty(productsNum[i].intValue());
				salesOrderDetail.setCashBack(retMoneys[i]);
				salesOrderDetailService.save(salesOrderDetail);
			}

			jsonResponse.setResult(salesOrder.getId());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	@RequestMapping("/weixin/workbench/salesOrder/saveInvoke")
	@ResponseBody
	public JsonResponse savInvoke(
	        String picture, String invokeNo, String orderId, HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String url = null;
		// System.err.println("data :" + picture);
		// System.err.println("订单ID :" + orderId);
		try {
			if (picture != null) {
				if (picture.startsWith("data")) {// 修改或新增
					byte[] imageBytes = Base64Utils.decode(picture.split(",")[1]);
					String dir = "/upload/wxInvoke";
					String path = request.getSession().getServletContext().getRealPath("");
					File f = new File(path + "/" + dir);
					if (!f.exists()) {
						f.mkdirs();
					}
					path += dir;
					String file = Base64Utils.saveImage(imageBytes, path);
					url = dir + "/" + file;

					// System.err.println("save path :" + path);
					// System.err.println("save url :" + url);
				}
			}
			SalesOrder salesOrder = salesOrderService.findOne(orderId);
			if (salesOrder == null) {
				jsonResponse = new JsonResponse(ResponseCode.找不到路径);
				jsonResponse.setMsg("订单不存在");
				return jsonResponse;
			}
			if (url != null)
				salesOrder.setInvoiceImageUrl(url);
			salesOrder.setInvoiceNo(invokeNo);
			salesOrderService.save(salesOrder);

		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	@RequestMapping("/weixin/workbench/salesOrder/orderListJson")
	@ResponseBody
	public JsonResponse orderListJson(String keyWord, Long currentPage, String time) {
		// System.err.println(keyWord);
		// System.err.println(currentPage);
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			Employee employee = CurrentUserUtils.getEmployee(false);
			Integer type = -1;
			time = time == null ? "0" : time;
			Date fdate = null;
			Date ldate = null;

			// 全
			if (time.equals("0")) {

			} // 月
			else if (time.equals("1")) {
				fdate = DateMonth(0);
				ldate = DateMonth(1);
			} else if (time.equals("2")) {
				fdate = DateWeek(0);
				ldate = DateWeek(1);
			}
			// 周
			List<ObjectNode> products = salesOrderService.findOrdersByType(employee.getId(), keyWord, type,
			        currentPage, fdate, ldate);
			jsonResponse.setResult(products);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	@RequestMapping("/weixin/workbench/salesOrder/productListJson")
	@ResponseBody
	public JsonResponse productListJson(String parentId, Integer currentPage) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			List<ObjectNode> products = productCategoryService.getProductByParentId(parentId.toString(), currentPage);
			jsonResponse.setResult(products);
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;

	}

	private String buildInvoiceNo() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		Random random = new Random();
		int i = Math.abs(random.nextInt() % 100);
		return df.format(date) + i;

	}

	public Date DateWeek(int type) {
		Date fdate = null;
		Date ldate = null;
		Calendar c = Calendar.getInstance();
		int weekday = c.get(7) - 1;
		c.add(5, -weekday);
		fdate = (c.getTime());
		fdate.setHours(0);
		fdate.setMinutes(0);
		fdate.setSeconds(0);
		if (type == 0) {
			return fdate;
		}
		c.add(5, 6);
		ldate = c.getTime();
		ldate.setHours(23);
		ldate.setMinutes(59);
		ldate.setSeconds(59);
		return ldate;
	}

	public Date DateMonth(int type) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (type == 0) {
			return c.getTime();
		}

		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

}
