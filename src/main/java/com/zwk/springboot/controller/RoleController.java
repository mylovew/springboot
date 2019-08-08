package com.zwk.springboot.controller;

import com.zwk.springboot.entity.Role;
import com.zwk.springboot.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @RequestMapping("findAll")
    @ResponseBody
    public List<Role> findAll(){
        log.info("查询所有角色");
        return roleService.findAll();
    }
}
