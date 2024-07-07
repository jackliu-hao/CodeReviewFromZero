package com.llu.util;

import com.llu.bean.AdminUser;
import com.llu.bean.CommonBean;
import com.llu.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jackliu  Email:
 * @description: 初始化用户
 * @Version
 * @create 2024-07-07 19:50
 */
public class InitUser {


    // 初始化普通用户
    public static List<CommonBean> initCommonUser(){
        ArrayList<CommonBean> users = new ArrayList<CommonBean>();
        User user = new User();
        user.setName("jack");
        user.setPhone("123456789");
        user.setLoginName("jack");
        user.setPassword("123456");
        user.setRole(0);
        User user1 = new User();
        user1.setName("tom");
        user1.setPhone("9654780");
        user1.setLoginName("tom");
        user1.setPassword("123456789");
        user1.setRole(0);
        users.add(user);
        users.add(user1);
        return  users;
    }

    // 初始化管理员用户
    public static List<CommonBean> initAdminUser(){

        ArrayList<CommonBean> objects = new ArrayList<CommonBean>();
        AdminUser adminUser = new AdminUser();
        adminUser.setName("admin");
        adminUser.setPhone("123456000");
        adminUser.setEmail("123456@qq.com");
        adminUser.setLoginName("admin");
        adminUser.setPassword("123456");
        adminUser.setRole(1);
        objects.add(adminUser);
        return objects;
    }

}
