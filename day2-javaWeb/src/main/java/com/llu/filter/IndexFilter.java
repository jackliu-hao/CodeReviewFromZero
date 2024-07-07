package com.llu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jackliu  Email:
 * @description:
 * @Version
 * @create 2024-07-07 22:34
 */
@WebFilter(urlPatterns = "/*")
public class IndexFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();

        // 检查请求的URL是否为"/index"，如果是，判断是否登录
        if (requestURI.endsWith("index.jsp")) {
            if (req.getSession().getAttribute("user") == null) {
                // 未登录，重定向到登录页面
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
