package cn.cnyirui.framework.controller.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnyirui.framework.controller.weixin.handler.WeiXinLocationHandler;
import cn.cnyirui.framework.controller.weixin.handler.WeiXinScanHandler;
import cn.cnyirui.framework.controller.weixin.handler.WeiXinSubscribeHandler;
import cn.cnyirui.framework.controller.weixin.handler.WeiXinUnSubscribeHandler;
import cn.cnyirui.framework.model.eo.WeiXinQrCodeSceneType;
import cn.cnyirui.framework.model.po.weixin.WeiXinQrCode;
import cn.cnyirui.framework.service.weixin.WeiXinQrCodeService;
import cn.cnyirui.framework.utils.Constants;
import cn.cnyirui.framework.utils.QRCodeUtil;
import cn.cnyirui.framework.utils.ServletUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

/**
 * 微信入口controller
 * 
 * @author pengzhihua
 *
 */
@Controller
public class WeiXinController {
	private static final Logger logger = LoggerFactory.getLogger(WeiXinController.class);

	@Resource
	private WxMpConfigStorage wxMpConfigStorage;
	@Resource
	private WxMpService wxMpService;
	@Resource
	private WxMpMessageRouter wxMpMessageRouter;
	@Resource
	private WeiXinQrCodeService weiXinQrCodeService;

	@Resource(name = "weiXinSubscribeHandler")
	private WeiXinSubscribeHandler weiXinSubscribeHandler;
	@Resource(name = "weiXinUnSubscribeHandler")
	private WeiXinUnSubscribeHandler weiXinUnSubscribeHandler;
	@Resource(name = "weiXinScanHandler")
	private WeiXinScanHandler weiXinScanHandler;
	@Resource(name = "weiXinLocationHandler")
	private WeiXinLocationHandler weiXinLocationHandler;

	/**
	 * 微信消息处理规则
	 */
	@PostConstruct
	private void initMpMessageRouter() {
		// 关注
		wxMpMessageRouter.rule().event(WxConsts.EVT_SUBSCRIBE).async(false).handler(weiXinSubscribeHandler).end()
		        // 取消关注
		        .rule().event(WxConsts.EVT_UNSUBSCRIBE).handler(weiXinUnSubscribeHandler).end()
		        // 扫码
		        .rule().event(WxConsts.EVT_SCAN).async(false).handler(weiXinScanHandler).end()
		        .rule().event(WxConsts.EVT_LOCATION).handler(weiXinLocationHandler).end();
	}

	@RequestMapping("/weixin/customer/index")
	public void userIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.getWriter().println(request.getSession().getAttribute(Constants.WEIXIN_OPENID_SESSION_KEY));
		response.getWriter().println("普通用户页");
		response.getWriter().flush();
		response.getWriter().close();

	}

	@RequestMapping("/weixin/createMenu")
	public void setWxMenu() throws WxErrorException {
		WxMenu wxMenu = new WxMenu();
		List<WxMenuButton> wxMenuButtons = new ArrayList<WxMenu.WxMenuButton>();

		WxMenuButton wxMenuButton;

		wxMenuButton = new WxMenuButton();
		wxMenuButton.setType(WxConsts.BUTTON_VIEW);
		wxMenuButton.setUrl("http://148140c22n.imwork.net/homa-weixin/weixin/workbench/index");
		wxMenuButton.setName("我的工作台啊");
		wxMenuButtons.add(wxMenuButton);

		// wxMenuButton = new WxMenuButton();
		// wxMenuButton.setType(WxConsts.BUTTON_VIEW);
		// wxMenuButton.setUrl("http://test106.ityunwei.org/homa-weixin/weixin/workbench/salesOrder/index");
		// wxMenuButton.setName("我的工订单");
		// wxMenuButtons.add(wxMenuButton);

		wxMenu.setButtons(wxMenuButtons);
		wxMpService.menuCreate(wxMenu);

	}

	private void initHttpServletResponse(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

	}

	@RequestMapping("/weixin/getJsapiTicket")
	@ResponseBody
	public WxJsapiSignature getJsapiTicket(@RequestParam("url") String url) {
		try {
			WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(url);
			logger.debug("JsapiTicket:" + wxMpService.getJsapiTicket());
			logger.debug("AppId:" + wxJsapiSignature.getAppid());
			logger.debug("Noncestr:" + wxJsapiSignature.getNoncestr());
			logger.debug("Timestamp:" + wxJsapiSignature.getTimestamp());
			logger.debug("URL:" + wxJsapiSignature.getUrl());
			logger.debug("Signature:" + wxJsapiSignature.getSignature());
			return wxJsapiSignature;
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示微信带参数的二维码
	 * 
	 * @param sceneType 场景类型，对应 WeiXinQrCodeSceneType枚举类型的value值
	 * @param id 场景相关id值，如场景是订单二维码，则是订单的ID
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/qrCode/{sceneType}/{id}")
	public void displayQrCode(@PathVariable("sceneType") String sceneType, @PathVariable("id") String id,
	        HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isEmpty(sceneType) || StringUtils.isEmpty(id)) {
			return;
		}
		WeiXinQrCode weiXinQrCode = null;
		// 订单
		if (WeiXinQrCodeSceneType.SALESORDER.getValue().equalsIgnoreCase(sceneType)) {
			weiXinQrCode = weiXinQrCodeService.findBySalesOrderId(id);
		}
		if (weiXinQrCode != null) {
			// 设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/png");
			ServletUtils.setNoCacheHeader(response);
			String logoPath = request.getSession().getServletContext().getRealPath("/static/images/qrCode_logo.jpg");
			try {
				QRCodeUtil.encode(weiXinQrCode.getUrl(), logoPath, response.getOutputStream(), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * oauth2授权，获取openId
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @param redirectUrl
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping("/weixin/oauth2")
	public String oauth2(HttpServletRequest request, HttpServletResponse response, String code, String redirectUrl)
	        throws WxErrorException {
		initHttpServletResponse(response);
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		request.getSession().setAttribute(Constants.WEIXIN_OPENID_SESSION_KEY, wxMpOAuth2AccessToken.getOpenId());
		if (redirectUrl != null) {
			if (redirectUrl.indexOf(request.getContextPath()) != -1
			        && !StringUtils.startsWithIgnoreCase(redirectUrl, "http://")) {
				redirectUrl = StringUtils.remove(redirectUrl, request.getContextPath());
			}
			return "redirect:" + redirectUrl;
		} else {
			return "redirect:/weixin/workbench/index";
		}

	}

	/**
	 * 主入口
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/weixin")
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initHttpServletResponse(response);

		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");

		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			// 消息签名不正确，说明不是公众平台发过来的消息
			response.getWriter().println("非法请求");
			return;
		}

		String echostr = request.getParameter("echostr");
		if (StringUtils.isNotBlank(echostr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			response.getWriter().println(echostr);
			return;
		}

		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
		        : request.getParameter("encrypt_type");

		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			logger.debug(inMessage.toString());
			WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
			if (outMessage != null) {
				logger.debug(outMessage.toString());
				response.getWriter().write(outMessage.toXml());
			}
			return;
		}

		if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage,
			        timestamp, nonce, msgSignature);
			WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
			response.getWriter().write(outMessage.toEncryptedXml(wxMpConfigStorage));
			return;
		}

		response.getWriter().println("不可识别的加密类型");
		return;
	}

}
