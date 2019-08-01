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
public class Permission {
    @Id
    private String permissionId;
    private String permissionName;
    private String resourceType;
    private String url;
    private String permission;
    private String parentId;
}
