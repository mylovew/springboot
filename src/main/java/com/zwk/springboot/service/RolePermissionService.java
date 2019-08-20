package com.zwk.springboot.service;

import com.zwk.springboot.entity.RolePermission;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-15 11:38
 */
public interface RolePermissionService {
    List<RolePermission> findByRoleId(Integer roleId);
}
