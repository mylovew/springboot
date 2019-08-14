package com.zwk.springboot.dao;

import com.zwk.springboot.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-14 16:24
 */
public interface RolePermissionDao extends JpaRepository<RolePermission,Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE role_permission rp set " +
            "rp.permission_id = :#{#permissionId} where rp.permission_id = :#{#oldPermissionId} " , nativeQuery = true)
    int updPermissionIdByPermissionId(String permissionId,String oldPermissionId);
}
