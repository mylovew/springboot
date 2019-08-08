package com.zwk.springboot.service;

import com.zwk.springboot.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();
    User findByName(String name);
    List<Map<String,Object>> findByPage(Integer start, Integer limit);
    Long getCount();
    void deleteByUserId(Integer userId);
    User save(User user);
    int updByUserId(User user);
}
