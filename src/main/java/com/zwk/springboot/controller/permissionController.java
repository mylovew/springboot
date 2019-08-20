package com.zwk.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.zwk.springboot.entity.Permission;
import com.zwk.springboot.service.PermissionService;
import com.zwk.springboot.util.Menu;
import com.zwk.springboot.util.ResultAPI;
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
 * @description: 菜单管理
 * @author: wkzhang
 * @create: 2019-08-09 15:02
 */
@Controller
@RequestMapping("permission")
public class permissionController {
    private Logger log = LoggerFactory.getLogger(permissionController.class);
    @Resource
    private PermissionService permissionService;
    @RequiresPermissions(value = "permission:permissionList")
    @RequestMapping("permissionList")
    public String toPermissionList(Map<String,Object> map){
//        //加载所有权限
//        List<Permission> permissionList = permissionService.findAll();
//        //格式化权限上下级
//        List<Permission> list = Menu.menuList(permissionList);
//
//        //转化为前端tree可用json
//        List<Map<String,Object>> treeData = Menu.tree_mapList(list);
//        log.info("treeDate : " + treeData);
//        map.put("treeData",treeData);
        return "/admin/permissionList";
    }
    @RequiresPermissions(value = "permission:permissionList")
    @RequestMapping(value = "permissionListPage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> permissionListPage(){
        Map<String,Object> map = new HashMap<>();
        List<Permission> permissionList = permissionService.findAll();
        Long count = permissionService.getCount();
        map.put("code",0);
        map.put("msg","");
        map.put("data",permissionList);
        map.put("count",count);
        map.put("is",false);
        map.put("tip","操作成功！");
        log.info(JSONObject.toJSONString(permissionList));
        return map;
    }
    @RequiresPermissions(value = "permission:save")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public ResultAPI save(Permission permission){
        try {
            log.info(permission.toString());
            permissionService.save(permission);
            return new ResultAPI(1,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResultAPI(0,"添加失败");
    }
    @RequiresPermissions(value = "permission:del")
    @RequestMapping(value = "del",method = RequestMethod.POST)
    @ResponseBody
    public ResultAPI del(String permissionId){
        try {
            //删除菜单
            permissionService.deleteByPermissionId(permissionId);
            return new ResultAPI(1,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultAPI(0,"删除失败");
    }
    @RequiresPermissions(value = "permission:upd")
    @RequestMapping(value = "upd",method = RequestMethod.POST)
    @ResponseBody
    public ResultAPI upd(Permission permissionId,String oldPermissionId){
        try {
            permissionService.updByPermissionId(permissionId,oldPermissionId);
            return new ResultAPI(1,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultAPI(0,"修改失败");
    }
    @RequiresPermissions(value = "permission:permissionList")
    @RequestMapping(value = "parentIdSelect",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> parentIdSelect(String resourceType){
        List<Permission> permissionList = permissionService.findByResourceType(resourceType);
        //格式化权限上下级
        List<Permission> list = Menu.menuList(permissionList);
        log.info(Menu.treeSelect_mapList(list)+"");
        return Menu.treeSelect_mapList(list);
    }
}
