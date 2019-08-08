package com.zwk.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
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
public class User implements Serializable {
    private static final long serialVersionUID = 4348153037332169017L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String password;
    private String phone;
    private Integer state;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                ", roleList=" + roleList +
                '}';
    }
}
