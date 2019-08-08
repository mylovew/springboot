package com.zwk.springboot.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-02 16:20
 */
@Getter
@Setter
public class LoginResult {
    private boolean isLogin = false;
    private String result;
}
