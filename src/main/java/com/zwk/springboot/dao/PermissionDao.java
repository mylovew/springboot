package com.zwk.springboot.dao;

import com.zwk.springboot.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: springboot
 * @description: 权限
 * @author: wkzhang
 * @create: 2019-08-09 15:04
 */
public interface PermissionDao extends JpaRepository<Permission,String> {
    @Query(value = "select count(*) from permission",nativeQuery = true)
    Long getCount();
    @Transactional
    int deleteByPermissionId(String permissionId);
    @Transactional
    int deleteByParentId(String parentId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE permission p set " +
            "p.permission_id = CASE WHEN :#{#permission.permissionId} IS NULL THEN p.permission_id ELSE :#{#permission.permissionId} END," +
            "p.parent_id = CASE WHEN :#{#permission.parentId} IS NULL THEN p.parent_id ELSE :#{#permission.parentId} END," +
            "p.permission_name = CASE WHEN :#{#permission.permissionName} IS NULL THEN p.permission_name ELSE :#{#permission.permissionName} END," +
            "p.resource_type = CASE WHEN :#{#permission.resourceType} IS NULL THEN p.resource_type ELSE :#{#permission.resourceType} END," +
            "p.url = CASE WHEN :#{#permission.url} IS NULL THEN p.url ELSE :#{#permission.url} END," +
            "p.permission = CASE WHEN :#{#permission.permission} IS NULL THEN p.permission ELSE :#{#permission.permission} END, " +
            "p.icon = CASE WHEN :#{#permission.icon} IS NULL THEN p.icon ELSE :#{#permission.icon} END " +
            "where p.permission_id = :#{#oldPermissionId} " , nativeQuery = true)
    int updByPermissionId(Permission permission,String oldPermissionId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE permission p set p.parent_id = :#{#parentId} where p.parent_id = :#{#oldParentId}",nativeQuery = true)
    int updParentIdByParentId(String parentId,String oldParentId);
    List<Permission> findByResourceType(String resourceType);
    Permission findByPermissionId(String permissionId);
    @Query(value = "select * from permission p right join role_permission rp on p.permission_id = rp.permission_id where rp.role_id = :#{#roleId}",nativeQuery = true)
    List<Permission> findByRoleId(Integer roleId);

}
