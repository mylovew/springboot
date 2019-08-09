package com.zwk.springboot.service.impl;

import com.zwk.springboot.dao.PermissionDao;
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

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
