package com.zwk.springboot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
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
public class Permission implements Serializable {
    private static final long serialVersionUID = -6224365265495825638L;
    @Id
    private String permissionId;
    private String permissionName;
    private String resourceType;
    private String url;
    private String permission;
    private String parentId;
    private String icon;
    @Transient
    private List<Permission> childList = null;
    @Transient
//    @JsonProperty(value = "lay_is_open")
//    private boolean lay_is_open = false;


    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId='" + parentId + '\'' +
                ", icon='" + icon + '\'' +
                ", childList=" + childList +
//                ", lay_is_open=" + lay_is_open +
                '}';
    }
}
