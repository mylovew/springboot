package com.zwk.springboot.dao;

import com.zwk.springboot.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-07 13:47
 */
public interface UserRoleDao extends JpaRepository<UserRole,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update user_role set role_id = ?1 where user_id = ?2 ",nativeQuery = true)
    int updRoleIdByUserId(Integer roleId,Integer userId);

    @Transactional
    int deleteByRoleId(Integer roleId);

    @Transactional
    int deleteByUserId(Integer userId);
}
