package com.cycloneboy.shiromybatis.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-10-28 11:19
 **/
@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "/index";
    }

    @RequestMapping("/home")
    public String home(){
        return "home/test";
    }

    @RequestMapping("/user")
    public String authUser(){
        return "auth/user";
    }

    @RequestMapping("/role")
    public String authRole(){
        return "auth/role";
    }

    @RequestMapping("/permission")
    public String authPermission(){
        return "auth/permission";
    }

    @RequestMapping("/user/role")
    public String authUserRole(){
        return "auth/userRole";
    }

    @RequestMapping("/role/permission")
    public String authRolePermission(){
        return "auth/rolePermission";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("exception:" + exception.toString());
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("----------------没有权限------------------");
        return "403";
    }

    @GetMapping("/users")
    public String userlist(){
        return "user";
    }


}
