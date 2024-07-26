package com.llu.basexsstest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 前后端分离的情况，此时在后端只返回json数据，剩下的交给前端，这个时候最好还是在后端进行修复，前端修复容易绕过
 */
@RestController
@RequestMapping("/v1")
public class Xss2Controller {
    @RequestMapping("/xssTest")
    @CrossOrigin
    public Map xss(String userName , String img ,String click) {
        if (StringUtils.isEmpty(userName)){
            userName = "小明";
        }
        Map map = new HashMap();
        map.put("userName", userName);
        map.put("img", img);
        map.put("click", click);

        return map;
    }
}
