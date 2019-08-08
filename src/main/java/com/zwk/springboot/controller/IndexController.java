package com.zwk.springboot.controller;

import com.zwk.springboot.entity.Permission;
import com.zwk.springboot.entity.User;
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
        List<Map<String,Object>> menuList = new ArrayList<>();

        //权限菜单
        List<Permission> permissionList = user.getRoleList().get(0).getPermissionList();
        for (int i = 0; i < permissionList.size(); i++){
            Permission permission = permissionList.get(i);
            //顶级菜单
            if (null == permission.getParentId() || permission.getParentId().equals("")){
                Map<String,Object> map = new HashMap<>();
                map.put("oneMenu",permission);
                //二级菜单
                List<Permission> list = new ArrayList<>();
                for (int j = 0; j < permissionList.size(); j++){
                    Permission p = permissionList.get(j);
                    if (p.getParentId() != null && p.getParentId().equals(permission.getPermissionId())){
                        list.add(p);
                    }
                }
                if (list.size() > 0){
                    map.put("twoMenu",list);
                }else {
                    map.put("twoMenu",null);
                }
                menuList.add(map);
            }
        }
        System.out.println(menuList);
        model.addAttribute("menuList",menuList);
        model.addAttribute("username",user.getName());
        return "/admin/index";
    }
}
