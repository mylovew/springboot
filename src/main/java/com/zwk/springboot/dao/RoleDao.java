package com.zwk.springboot.dao;

import com.zwk.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: springboot
 * @description: 角色表
 * @author: wkzhang
 * @create: 2019-08-07 09:26
 */
public interface RoleDao extends JpaRepository<Role,Integer> {

}
