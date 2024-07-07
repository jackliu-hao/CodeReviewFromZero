package com.llu.controller;



import com.llu.bean.AdminUser;
import com.llu.bean.CommonBean;
import com.llu.bean.User;
import com.llu.util.InitUser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author jackliu  Email:
 * @description:
 * @Version
 * @create 2024-07-07 19:57
 */

@WebServlet(name = "UserController", urlPatterns = "/user/*" )
public class UserController extends BaseServlet {

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");//设置字体防止乱码
//        // 获取uri
//        String uri = request.getRequestURI();
//
//        if (StringUtils.isNotEmpty(uri)){
//            switch (uri){
//                case "/day2/user/index":
//                    index(request, resp);
//                    break;
//                default:
//                    resp.sendRedirect("/error/404.html");
//            }
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");//设置字体防止乱码
//        // 获取uri
//        String uri = request.getRequestURI();
//        // 判断uri不为空
//        if (StringUtils.isNotEmpty(uri)){
//            switch (uri){
//                case "/day2/user/login":
//                    login(request, resp);
//                    break;
//                case "/day2/user/index":
//                    index(request, resp);
//                    break;
//                default:
//                    resp.sendRedirect("/error/404.html");
//
//            }
//        }
//
//
//    }

    private void index(HttpServletRequest request, HttpServletResponse resp) {

        // 需要做鉴权 ,  放到filter中

    }


    // 登录
    public void login(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        // 必须是POST请求
        if (!"POST".equals(request.getMethod())){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(405);
            resp.getWriter().write("方法不允许");
            return;
        }

        User user = new User();
        user.setLoginName(request.getParameter("loginName"));
        user.setPassword(request.getParameter("password"));
        String role = request.getParameter("role");

        CommonBean commonBean = judgeLogin(user, role,request);

        if (commonBean != null){

            // 设置session
            request.getSession().setAttribute("user", commonBean);

            // 跳转页面
            try {
//                request.getRequestDispatcher("/index.jsp").forward(request, resp);
                resp.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            // 跳转页面 ,登录失败
            try {
                request.setAttribute("loginName", request.getParameter("loginName"));
                request.getRequestDispatcher("/error/loginfailed.jsp").forward(request, resp);

            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 处理登录逻辑
    public CommonBean judgeLogin(CommonBean user,String role,HttpServletRequest req){

        // 获取参数
        String loginName = user.getLoginName();
        String password = user.getPassword();

        // 初始化
        // 登录 逻辑
        List<CommonBean> users = null;
        if ("0".equals(role)){
            // 初始化普通用户 , 相当于查表
           users = InitUser.initCommonUser();
        }else if ("1".equals(role)){
            // 初始化管理员,相当于查表
            users = InitUser.initAdminUser();
        }

        for (CommonBean user1 : users) {
            if (loginName.equals(user1.getLoginName()) && password.equals(user1.getPassword())){
                req.getSession().setAttribute("users",users );
                return user1;
            }
        }
        return null;
    }

     // 退出登录
    public void logout(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        // 获取session
        request.getSession().invalidate();
        // 跳转页面
        resp.sendRedirect(request.getContextPath() + "/index.jsp");
    }



}
