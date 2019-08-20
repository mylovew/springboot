package com.zwk.springboot.controller;

import com.zwk.springboot.entity.Role;
import com.zwk.springboot.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: 角色
 * @author: wkzhang
 * @create: 2019-08-07 09:30
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private Logger log = LoggerFactory.getLogger(RoleController.class);
    @Resource
    private RoleService roleService;
    @RequiresPermissions(value = "role:roleList")
    @RequestMapping("roleList")
    public String roleList(){
        return "/admin/roleList";
    }

    @RequiresPermissions(value = "role:roleList")
    @RequestMapping("roleListPage")
    @ResponseBody
    public Map<String,Object> roleListPage(Integer page,Integer limit){
        log.info("查询所有角色");
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",roleService.getCount());
        map.put("data",roleService.findByPage((page-1)*limit,limit));
        return map;
    }
}
