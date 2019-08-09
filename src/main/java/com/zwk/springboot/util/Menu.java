package com.zwk.springboot.util;

import com.zwk.springboot.entity.Permission;

import java.util.ArrayList;
import java.util.List;

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
}
