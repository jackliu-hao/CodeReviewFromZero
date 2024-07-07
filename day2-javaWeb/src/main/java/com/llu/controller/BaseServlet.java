package com.llu.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jackliu  Email:
 * @description: 基础Servlet
 * @Version
 * @create 2024-07-07 22:03
 */

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get启动！");

        //获取URI路径
        String uri = request.getRequestURI();

        //把"/day2/user/login" /day2/user中 删除
        String methodName = new StringBuffer(uri.substring(uri.lastIndexOf("/") + 1)).toString();

        //根据方法名获取对应的方法对象，最后执行方法
        Method method = null;
        try {
            //哪一个对象调用doGet方法，则this代表哪个对象
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            // 重定向到404
            response.sendRedirect(request.getContextPath() + "/error/404.html");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post启动！");
        this.doGet(req, resp);
    }
}
