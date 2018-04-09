package cn.cnyirui.framework.controller.bind;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerMapping;

public abstract class BaseMethodArgumentResolver implements HandlerMethodArgumentResolver {

	/**
	 * 获取指定前缀的参数：包括uri varaibles 和 parameters
	 *
	 * @param namePrefix
	 * @param request
	 * @return
	 * @subPrefix 是否截取掉namePrefix的前缀
	 */
	protected Map<String, String[]> getPrefixParameterMap(String namePrefix, NativeWebRequest request,
			boolean subPrefix) {
		Map<String, String[]> result = new HashMap<String, String[]>();
		Map<String, String> variables = getUriTemplateVariables(request);

		int namePrefixLength = namePrefix.length();
		for (String name : variables.keySet()) {
			if (name.startsWith(namePrefix)) {
				String[] values = decodeParameterValues(variables.get(name));
				// page.pn 则截取 pn
				if (subPrefix) {
					char ch = name.charAt(namePrefix.length());
					// 如果下一个字符不是 数字 . _ 则不可能是查询 只是前缀类似
					if (illegalChar(ch)) {
						continue;
					}
					result.put(name.substring(namePrefixLength + 1), values);
				} else {
					result.put(name, values);
				}
			}
		}

		Iterator<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasNext()) {
			String name = parameterNames.next();
			if (name.startsWith(namePrefix)) {
				String[] values = decodeParameterValues(request.getParameterValues(name));
				// page.pn 则截取 pn
				if (subPrefix) {
					char ch = name.charAt(namePrefix.length());
					// 如果下一个字符不是 数字 . _ 则不可能是查询 只是前缀类似
					if (illegalChar(ch)) {
						continue;
					}
					result.put(name.substring(namePrefixLength + 1), values);
				} else {
					result.put(name, values);
				}
			}
		}

		return result;
	}

	private boolean illegalChar(char ch) {
		return ch != '.' && ch != '_' && !(ch >= '0' && ch <= '9');
	}

	@SuppressWarnings("unchecked")
	protected final Map<String, String> getUriTemplateVariables(NativeWebRequest request) {
		Map<String, String> variables = (Map<String, String>) request.getAttribute(
				HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		return (variables != null) ? variables : Collections.<String, String> emptyMap();
	}

	private String[] decodeParameterValues(String... parameterValues) {
		if (ArrayUtils.isEmpty(parameterValues)) {
			return null;
		}
		String[] result = new String[parameterValues.length];
		for (int i = 0; i < parameterValues.length; i++) {
			try {
				result[i] = URLDecoder.decode(parameterValues[i], "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
