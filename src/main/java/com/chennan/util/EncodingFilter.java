package com.chennan.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * demo class
 * 解决中文乱码问题的过滤器
 * @author 陈楠
 * @date 2019/12/2 11:55
 * To change this template use File | Settings | File Templates.
 */
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}