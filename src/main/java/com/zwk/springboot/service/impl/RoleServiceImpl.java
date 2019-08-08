package com.zwk.springboot.service.impl;

import com.zwk.springboot.dao.RoleDao;
import com.zwk.springboot.entity.Role;
import com.zwk.springboot.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
