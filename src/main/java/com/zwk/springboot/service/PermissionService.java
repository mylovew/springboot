package com.zwk.springboot.service;

import com.zwk.springboot.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
}
