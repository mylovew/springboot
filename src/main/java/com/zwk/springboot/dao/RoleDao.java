package com.zwk.springboot.dao;

import com.zwk.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: 角色表
 * @author: wkzhang
 * @create: 2019-08-07 09:26
 */
public interface RoleDao extends JpaRepository<Role,Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE role r set " +
            "r.role = CASE WHEN :#{#role.role} IS NULL THEN r.role ELSE :#{#role.role} END," +
            "r.description = CASE WHEN :#{#role.description} IS NULL THEN r.description ELSE :#{#role.description} END" +
            "where r.role_id = :#{#role.roleId} " , nativeQuery = true)
    int updByRoleId(Role role);

    @Transactional
    int deleteByRoleId(Integer roleId);

    Role save(Role role);

    @Query(value = "select count(*) from role",nativeQuery = true)
    Long getCount();

    @Query(value = "select * from role ORDER BY role.role_id ASC  limit ?1,?2",nativeQuery = true)
    List<Map<String,Object>> findByPage(Integer start, Integer limit);
}
