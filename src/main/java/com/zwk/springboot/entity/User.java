package com.zwk.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @program: springboot
 * @description: 用户实体类
 * @author: wkzhang
 * @create: 2019-07-22 10:11
 */
@Entity
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String password;
    private String phone;
    private Integer state;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;
}
