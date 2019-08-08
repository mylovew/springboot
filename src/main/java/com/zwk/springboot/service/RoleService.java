package com.zwk.springboot.service;

import com.zwk.springboot.entity.Role;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-07 09:28
 */
public interface RoleService {
    List<Role> findAll();
}
