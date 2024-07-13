package com.llu.controller;

import com.llu.entity.User;
import com.llu.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author 11u
 * @since 2024-07-12 22:42:53
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    // 注册
    @PostMapping("register")
    public ResponseEntity<Map> register(@RequestBody User user) {
        Boolean result = userService.register(user);
        HashMap hashMap = new HashMap();
        hashMap.put("result", result );
        return ResponseEntity.ok(hashMap);
    }



    // 登录
    @PostMapping("login")
    public ResponseEntity<Map> login(@RequestBody User user , HttpServletRequest request) {
        User user1 = userService.login(user);


        HashMap hashMap = new HashMap();
        if (user1 == null) {
            hashMap.put("result", false );
        }else {
            request.getSession().setAttribute("loginName", user1.getLoginname());
            hashMap.put("result", true );
        }
        hashMap.put("data", user1  );

        return ResponseEntity.ok(hashMap);
    }

    // 查询个人信息
    @GetMapping("info")
    public ResponseEntity<Map> queryByLoginName(HttpServletRequest request) {
        String loginName = (String) request.getSession().getAttribute("loginName");
        HashMap hashMap =  new HashMap<>();
        User user = userService.queryByLoginName(loginName);
        if (user == null) {
            hashMap.put("result", false );
        }else {
            hashMap.put("result", true );
        }
        hashMap.put("data",user );

        return ResponseEntity.ok(hashMap);
    }





}

