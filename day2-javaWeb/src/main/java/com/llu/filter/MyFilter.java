package com.llu.filter;

import com.llu.wrapper.CustomHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

//@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
         // 对参数进行解密
        servletResponse.setContentType("text/html; charset=UTF-8");
        String id = servletRequest.getParameter("id");
         if(id !=null && !id.equals("")){
             // 使用Base64解密
             Base64.Decoder decoder = Base64.getDecoder();
             byte[] decode = decoder.decode(id);
             String decodedId = new String(decode);
             // 创建自定义的 HttpServletRequestWrapper 并替换原始的 id 参数
             CustomHttpServletRequestWrapper wrappedRequest = new CustomHttpServletRequestWrapper((HttpServletRequest) servletRequest);
             wrappedRequest.addParameter("id", decodedId);

             // 继续链，使用包装后的请求
             filterChain.doFilter(wrappedRequest, servletResponse);
         }else {
             servletResponse.getWriter().write("id参数为空");
         }

    }

    @Override
    public void destroy() {
        System.out.println("被销毁了");
    }
}
