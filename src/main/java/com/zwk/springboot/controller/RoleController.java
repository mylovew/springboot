package com.zwk.springboot.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.zwk.springboot.entity.Permission;
import com.zwk.springboot.entity.Role;
import com.zwk.springboot.service.PermissionService;
import com.zwk.springboot.service.RolePermissionService;
import com.zwk.springboot.service.RoleService;
import com.zwk.springboot.util.Menu;
import com.zwk.springboot.util.ResultAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@Slf4j
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionService permissionService;

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
    @RequiresPermissions(value = "user:upd")
    @RequestMapping("findAll")
    @ResponseBody
    public List<Role> findAll(){
        log.info("查询所有角色");
        return roleService.findAll();
    }
    @RequiresPermissions(value = "role:save")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public ResultAPI save(Role role){
        log.info("添加角色");
        try {
            roleService.save(role);
            return new ResultAPI(1,"添加成功");
        }catch (Exception e){
            log.error("角色添加失败");
            e.printStackTrace();
            return new ResultAPI(0,"添加失败");
        }
    }
    @RequiresPermissions(value = "role:upd")
    @RequestMapping(value = "upd",method = RequestMethod.POST)
    @ResponseBody
    public ResultAPI upd(Role role){
        log.info("修改角色");
        try {
            roleService.updByRoleId(role);
            return new ResultAPI(1,"修改成功");
        }catch (Exception e){
            log.error("角色修改失败");
            e.printStackTrace();
            return new ResultAPI(0,"修改失败");
        }
    }
    @RequiresPermissions(value = "role:del")
    @RequestMapping(value = "del",method = RequestMethod.POST)
    @ResponseBody
    public ResultAPI del(Integer roleId){
        log.info("删除角色");
        try {
            roleService.deleteByRoleId(roleId);
            return new ResultAPI(1,"删除成功");
        }catch (Exception e){
            log.error("角色删除失败");
            e.printStackTrace();
            return new ResultAPI(0,"删除失败");
        }
    }

//    @RequiresPermissions(value = "role:updPermission")
    @RequestMapping("findAllPermission")
    @ResponseBody
    public ResultAPI findAllPermission(Integer roleId){
        try {
            List<Permission> permissionList = permissionService.findAll();
            List<Permission> list = Menu.menuList(permissionList);
            List<Map<String, Object>> mapList = Menu.tree_mapList(list);
            return new ResultAPI(1,"获取所有权限数据成功",mapList);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultAPI(0,"获取所有权限数据失败");
        }
    }
    @RequestMapping("findPermissionByRoleId")
    @ResponseBody
    public ResultAPI findPermissionByRoleId(Integer roleId){
        try {
            List<Permission> permissionList = permissionService.findByRoleId(roleId);
            //Id格式化成数组返回
            List<String> data = new ArrayList<>();
            for(Permission permission : permissionList){
                data.add(permission.getPermissionId());
            }
            return new ResultAPI(1,"获取角色权限数据成功",data);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultAPI(0,"获取角色权限数据失败");
        }
    }

}
