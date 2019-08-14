package com.zwk.springboot.controller;

import com.zwk.springboot.entity.Permission;
import com.zwk.springboot.entity.User;
import com.zwk.springboot.util.Menu;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-02 15:01
 */
@Controller
@RequestMapping("index")
public class IndexController {
    private Logger log = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping("toIndex")
    public String toIndex(Model model){
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        log.info(user.toString());
        List<Map<String,Object>> menuList = new ArrayList<>();
        //权限菜单
        List<Permission> permissionList = user.getRoleList().get(0).getPermissionList();

        model.addAttribute("menuList",Menu.menuList(permissionList));
        log.info(""+Menu.menuList(permissionList));
        model.addAttribute("username",user.getName());
        return "/admin/index";
    }
}
