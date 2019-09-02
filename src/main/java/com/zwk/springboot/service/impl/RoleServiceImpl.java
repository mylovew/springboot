package com.zwk.springboot.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import com.zwk.springboot.dao.RoleDao;
import com.zwk.springboot.dao.RolePermissionDao;
import com.zwk.springboot.dao.UserRoleDao;
import com.zwk.springboot.entity.Role;
import com.zwk.springboot.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-07 09:28
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public int deleteByRoleId(Integer roleId) {
        int i = roleDao.deleteByRoleId(roleId);
        userRoleDao.deleteByRoleId(roleId);
        rolePermissionDao.deleteByRoleId(roleId);
        return i;
    }

    @Override
    public int updByRoleId(Role role) {
        return roleDao.updByRoleId(role);
    }

    @Override
    public Long getCount() {
        return roleDao.getCount();
    }

    @Override
    public List<Map<String, Object>> findByPage(Integer start, Integer limit) {
        return roleDao.findByPage(start,limit);
    }
}
