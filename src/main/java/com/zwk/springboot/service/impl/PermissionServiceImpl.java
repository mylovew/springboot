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
        return permissionDao.deleteByPermissionId(permissionId);
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
        int i = permissionDao.updByPermissionId(permission,oldPermissionId);
        if (!permission.getPermissionId().equals(oldPermissionId)){
            permissionDao.updParentIdByParentId(permission.getPermissionId(),oldPermissionId);
            rolePermissionDao.updPermissionIdByPermissionId(permission.getPermissionId(),oldPermissionId);
        }

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
}
