package com.zwk.springboot.dao;

import com.zwk.springboot.entity.Permission;
import com.zwk.springboot.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Transactional
    int deleteByPermissionId(String permissionId);

    @Transactional
    int deleteByRoleId(Integer roleId);

    @Transactional
    @Modifying
    @Query(value = "DELETE rp from role_permission rp where rp.permission_id in (select p.permission_id from permission p where p.parent_id = :#{#parentId})",nativeQuery = true)
    int deleteBySelectPermissionIdByParentId(String parentId);

    List<RolePermission> findByRoleId(Integer roleId);


}
