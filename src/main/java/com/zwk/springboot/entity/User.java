package com.zwk.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program: springboot
 * @description: 用户实体类
 * @author: wkzhang
 * @create: 2019-07-22 10:11
 */
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String password;
    private String phone;
}
