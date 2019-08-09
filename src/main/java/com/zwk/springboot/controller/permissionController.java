package com.zwk.springboot.controller;

import com.zwk.springboot.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @program: springboot
 * @description: 菜单管理
 * @author: wkzhang
 * @create: 2019-08-09 15:02
 */
@Controller
@RequestMapping("permission")
public class permissionController {
    @Resource
    private PermissionService permissionService;
    @RequiresPermissions(value = "permission:permissionList")
    @RequestMapping("permissionList")
    public String toPermissionList(){

        return "/admin/permissionList";
    }
}
