package com.llu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

//@WebServlet(name = "LoginServlet", urlPatterns = "/test" )
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
//        super.init();
        System.out.println("初始化");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取id
        System.out.println("id -> " + req.getParameter("id"));
        //获取请求资源路径
        System.out.println("URI -> " + req.getRequestURI()  );
        //获取绝对路径
        System.out.println("URL -> " + req.getRequestURL()  );
        //获取客户端IP
        System.out.println("IP -> " + req.getRemoteHost()  );
        //获取请求头
        System.out.println( "User-Agent -> " + req.getHeader("User-Agent"));
        //获取请求方式
        System.out.println("请求方式 ->" + req.getMethod());

        resp.getWriter().write("id is : " + req.getParameter("id"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置字体防止乱码
        // 获取请求URI
        request.getRequestURI();
        System.out.println("URI -> " + request.getRequestURI());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
