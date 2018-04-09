package cn.cnyirui.framework.extension.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import cn.cnyirui.framework.utils.ServletUtils;

public class ClearCacheFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	        FilterChain filterChain) throws IOException, ServletException {
		ServletUtils.setNoCacheHeader((HttpServletResponse) response);
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}