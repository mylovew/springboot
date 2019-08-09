package com.zwk.springboot.dao;

import com.zwk.springboot.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: springboot
 * @description: 权限
 * @author: wkzhang
 * @create: 2019-08-09 15:04
 */
public interface PermissionDao extends JpaRepository<Permission,String> {

}
