package com.zwk.springboot.service.impl;

import com.zwk.springboot.dao.UserRoleDao;
import com.zwk.springboot.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-07 13:49
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    @Override
    public int updRoleIdByUserId(Integer roleId, Integer userId) {
        return userRoleDao.updRoleIdByUserId(roleId,userId);
    }
}
