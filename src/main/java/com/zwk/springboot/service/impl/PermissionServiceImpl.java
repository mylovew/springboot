package com.zwk.springboot.service.impl;

import com.zwk.springboot.dao.PermissionDao;
import com.zwk.springboot.dao.RolePermissionDao;
import com.zwk.springboot.entity.Permission;
import com.zwk.springboot.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-09 15:06
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public Long getCount() {
        return permissionDao.getCount();
    }

    @Override
    public int deleteByPermissionId(String permissionId) {
        //删除绑定
        rolePermissionDao.deleteByPermissionId(permissionId);
        //删除子菜单绑定
        rolePermissionDao.deleteBySelectPermissionIdByParentId(permissionId);
        //删除子菜单
        permissionDao.deleteByParentId(permissionId);
        //删除当前菜单
        int i = permissionDao.deleteByPermissionId(permissionId);
        return i;
    }

    @Override
    public int deleteByParentId(String parentId) {
        return permissionDao.deleteByParentId(parentId);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public int updByPermissionId(Permission permission,String oldPermissionId) {
        //判断ID是否改变
        if (!permission.getPermissionId().equals(oldPermissionId)){
            //修改parentId 绑定字段
            permissionDao.updParentIdByParentId(permission.getPermissionId(),oldPermissionId);
            //修改子菜单绑定
            rolePermissionDao.updPermissionIdByPermissionId(permission.getPermissionId(),oldPermissionId);
        }

        int i = permissionDao.updByPermissionId(permission,oldPermissionId);

        return i;
    }

    @Override
    public List<Permission> findByResourceType(String resourceType) {
        return permissionDao.findByResourceType(resourceType);
    }

    @Override
    public Permission findByPermissionId(String permissionId) {
        return permissionDao.findByPermissionId(permissionId);
    }

    @Override
    public List<Permission> findByRoleId(Integer roleId) {
        return permissionDao.findByRoleId(roleId);
    }
}
