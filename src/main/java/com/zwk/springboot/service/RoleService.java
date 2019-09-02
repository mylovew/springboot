package com.zwk.springboot.service;

import com.zwk.springboot.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-07 09:28
 */
public interface RoleService {
    List<Role> findAll();
    Role save(Role role);
    @Transactional
    int deleteByRoleId(Integer roleId);
    int updByRoleId(Role role);
    Long getCount();
    List<Map<String,Object>> findByPage(Integer start, Integer limit);
}
