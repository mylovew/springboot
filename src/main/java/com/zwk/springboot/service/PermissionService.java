package com.zwk.springboot.service;

import com.zwk.springboot.entity.Permission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
    Long getCount();
    int deleteByPermissionId(String permissionId);
    int deleteByParentId(String parentId);
    Permission save(Permission permission);
    @Transactional
    int updByPermissionId(Permission permission,String oldPermissionId);
    List<Permission> findByResourceType(String resourceType);
    Permission findByPermissionId(String permissionId);
}
