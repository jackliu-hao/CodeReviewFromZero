package com.llu.basexsstest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  前后端不分离的情况下或直接把数据回显到前端的情况（这种情况一般在过滤器或拦截器中直接向前端页面写内容）
 */
@Controller
public class Xss1Controller {

    @GetMapping("/xss")
    public void xss(String userName, HttpServletResponse response) throws IOException {

//        设置编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

//        修复XSS，对输出到前端的数据做html实体编码
        // 方式1 ，这个时候可以使用 <a /> 标签中的js为协议绕过
        userName = userName.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        // 方式2  使用第三方库对html进行净化
//       userName = StringEscapeUtils.escapeHtml4(userName);

        response.getWriter().write("用户名错误 : "  + userName);

    }


}
