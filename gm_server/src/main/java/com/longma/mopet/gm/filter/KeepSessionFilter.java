package com.longma.mopet.gm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:Lvxingqing
 * @Description: 跨域sessionId保持
 * @Date:Create in 14:04 2018/4/23
 * @Modified By:
 *
 * 使用CrossDomainConfig配置跨域
 */
@WebFilter(filterName = "sessionKeepFilter", urlPatterns = { "/*" })
//@Deprecated
public class KeepSessionFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		res.setContentType("textml;charset=UTF-8");
		res.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "0");
		res.setHeader("Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("XDomainRequestAllowed", "1");
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
