package com.zwk.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
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
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int roleId;
    private String role;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissionList;

}
