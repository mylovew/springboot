package com.zwk.springboot.util;

import com.alibaba.fastjson.JSONObject;
import com.zwk.springboot.entity.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: 菜单处理
 * @author: wkzhang
 * @create: 2019-08-09 10:31
 */
public class Menu {


    /**
     * 格式化菜单
     * @param permissionList
     * @return
     */
    public static List<Permission> menuList(List<Permission> permissionList){
        //顶级菜单
        List<Permission> menuList = new ArrayList<>();
        for (int i = 0; i < permissionList.size(); i++){
            Permission p = permissionList.get(i);
            if (p.getParentId().equals("0")){
                p.setChildList(childList(p,permissionList));
                menuList.add(p);
            }
        }
        return menuList;
    }

    /**
     * 子菜单
     * @param permissionList
     * @return
     */
    public static List<Permission> childList(Permission permission,List<Permission> permissionList){
        List<Permission> childList = new ArrayList<>();
        for (int i = 0; i < permissionList.size(); i++){
            Permission p = permissionList.get(i);
            if (p.getParentId().equals(permission.getPermissionId())){
                p.setChildList(childList(p,permissionList));
                childList.add(p);
            }
        }

        return childList;
    }

    /**
     * 将权限格式化为tree可用格式
     * @param list
     * @return
     */
    public static List<Map<String,Object>> tree_mapList(List<Permission> list){
        List<Map<String,Object>> treeData = new ArrayList<>();
        for (Permission p : list){
            Map<String,Object> mapPer = new HashMap<>();
            mapPer.put("id",p.getPermissionId());
            mapPer.put("title",p.getPermissionName());
            mapPer.put("url",p.getUrl());
            mapPer.put("spread",true);
            mapPer.put("children",tree_mapList(p.getChildList()));
            treeData.add(mapPer);
        }
        return treeData;
    }

    /**
     * 将权限格式化为treeSelect可用格式
     * @param list
     * @return
     */
    public static List<Map<String,Object>> treeSelect_mapList(List<Permission> list){
        List<Map<String,Object>> treeData = new ArrayList<>();
        for (Permission p : list){
            Map<String,Object> mapPer = new HashMap<>();
            mapPer.put("id",p.getPermissionId());
            mapPer.put("name",p.getPermissionName());
            mapPer.put("open",false);
            mapPer.put("checked",true);
            if (p.getChildList().size() > 0)
                mapPer.put("children",treeSelect_mapList(p.getChildList()));
            treeData.add(mapPer);
        }
        return treeData;
    }

}
