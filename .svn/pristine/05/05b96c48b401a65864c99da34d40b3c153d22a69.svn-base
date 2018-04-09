package cn.cnyirui.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerMapping;

public class ServletUtils {

	// -- 常用数值定义 --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	/**
	 * 是否是微信浏览器
	 * 
	 * @param request
	 * @return true 是微信浏览器
	 */
	public static boolean isWeiXinBrowser(HttpServletRequest request) {
		String ua = request.getHeader("user-agent").toLowerCase();
		return ua != null && ua.indexOf("micromessenger") > 0;
	}

	/**
	 * 是否ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
		        && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With").toString()));
	}

	/**
	 * 设置客户端缓存过期时间 的Header.
	 * 
	 * @param response
	 * @param expiresSeconds
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + (expiresSeconds * 1000));
		// Http 1.1 header, set a time after now.
		response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 * 
	 * @param response
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader(HttpHeaders.EXPIRES, 1L);
		response.addHeader(HttpHeaders.PRAGMA, "no-cache");
		// Http 1.1 header
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
	}

	/**
	 * 设置LastModified Header.
	 * 
	 * @param response
	 * @param lastModifiedDate
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 * 
	 * @param response
	 * @param etag
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader(HttpHeaders.ETAG, etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param request
	 * @param response
	 * @param lastModified 内容的最后修改时间.
	 * @return
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
	        long lastModified) {
		long ifModifiedSince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
		if ((ifModifiedSince != -1) && (lastModified < (ifModifiedSince + 1000))) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param etag 内容的ETag.
	 * @return
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader(HttpHeaders.IF_NONE_MATCH);
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader(HttpHeaders.ETAG, etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param request
	 * @param response
	 * @param fileName 下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response,
	        String fileName) {
		// 中文文件名支持
		String encodedfileName = null;
		// 替换空格，否则firefox下有空格文件名会被截断,其他浏览器会将空格替换成+号
		encodedfileName = fileName.trim().replaceAll(" ", "_");
		String agent = request.getHeader("User-Agent");
		boolean isMSIE = (agent != null
		        && (agent.toUpperCase().indexOf("MSIE") != -1 || agent.toUpperCase().indexOf("EDGE") != -1
		                || agent.toUpperCase().indexOf("TRIDENT") != -1));
		if (isMSIE) {
			try {
				encodedfileName = URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				encodedfileName = "";
				e.printStackTrace();
			}
		} else {
			encodedfileName = new String(fileName.getBytes(), Charsets.ISO_8859_1);
		}
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
	}

	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 替换URL中变量
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String replaceUriTemplateVariables(HttpServletRequest request, String url) {
		Map<String, String> map = (Map<String, String>) request
		        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if (map != null) {
			for (String key : map.keySet()) {
				url = StringUtils.replace(url, "{" + key + "}", map.get(key));
			}
		}
		return url;
	}

}
