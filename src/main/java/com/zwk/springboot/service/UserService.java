package com.zwk.springboot.service;

import com.zwk.springboot.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
