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
@Table(name = "user_role", schema = "springboot")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userRoleId;
    private Integer userId;
    private Integer roleId;

}
