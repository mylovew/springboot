package com.zwk.springboot.controller;

import com.zwk.springboot.entity.LoginResult;
import com.zwk.springboot.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-02 15:47
 */
@Controller
@Slf4j
public class HomeController {
    @Resource
    private LoginService loginService;
    @RequestMapping("/login")
    public String login(Map<String, Object> map){
        return "/admin/login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request,Map<String, Object> map) throws Exception{
        System.out.println("login()");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+userName+"-----password:"+password);
        LoginResult loginResult = loginService.login(userName,password);
        map.put("state",loginResult.isLogin());
        map.put("msg",loginResult.getResult());
        if (loginResult.isLogin()){
            return "redirect:index/toIndex";
        }
        return "/admin/login";
    }
//    @RequestMapping("/logout")
//    public String logOut(HttpSession session) {
////        loginService.logout();
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return "/user/login";
//    }
}
