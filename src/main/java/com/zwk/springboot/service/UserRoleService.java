package com.zwk.springboot.service;

import org.springframework.transaction.annotation.Transactional;

public interface UserRoleService {
    int updRoleIdByUserId(Integer roleId,Integer userId);
    int deleteByRoleId(Integer roleId);

    @Transactional
    int deleteByUserId(Integer userId);
}
