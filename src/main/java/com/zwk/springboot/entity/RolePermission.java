package com.zwk.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-01 16:23
 */
@Entity
@Getter
@Setter
@Table(name = "role_permission", schema = "springboot", catalog = "")
public class RolePermission {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int rolePermissionId;
    private Integer roleId;
    private Integer permissionId;

}
