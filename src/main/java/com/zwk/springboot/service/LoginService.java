package com.zwk.springboot.service;

import com.zwk.springboot.entity.LoginResult;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-02 16:41
 */
public interface LoginService {
    LoginResult login(String userName, String password);
    void logout();
}
