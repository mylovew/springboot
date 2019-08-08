package com.zwk.springboot.controller;

import com.zwk.springboot.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-02 15:01
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @RequestMapping("toIndex")
    public String toIndex(Model model){
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username",user.getName());
        return "/admin/index";
    }
}
