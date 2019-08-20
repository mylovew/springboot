package com.zwk.springboot.service.impl;

import com.zwk.springboot.dao.RolePermissionDao;
import com.zwk.springboot.entity.RolePermission;
import com.zwk.springboot.service.RolePermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-15 11:38
 */
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionDao rolePermissionDao;
    @Override
    public List<RolePermission> findByRoleId(Integer roleId) {
        return rolePermissionDao.findByRoleId(roleId);
    }
}
