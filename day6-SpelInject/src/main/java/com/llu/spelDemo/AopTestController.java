package com.llu.spelDemo;

import com.llu.baseSpelTest.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopTestController {
    @RequestMapping("/aoptest")
    @CkOperatedLog(module = "aoptest",operation = "test", description = "aoptest", param = "'user is '+{2+2}")
    public String aoptest(@RequestBody User user){
        return "aoptest";
    }
}
