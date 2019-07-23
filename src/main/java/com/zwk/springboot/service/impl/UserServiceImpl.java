package com.zwk.springboot.service.impl;

import com.zwk.springboot.dao.UserDao;
import com.zwk.springboot.entity.User;
import com.zwk.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-07-22 11:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
