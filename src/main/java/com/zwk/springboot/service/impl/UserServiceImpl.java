package com.zwk.springboot.service.impl;

import com.zwk.springboot.dao.UserDao;
import com.zwk.springboot.entity.User;
import com.zwk.springboot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<Map<String,Object>> findByPage(Integer start, Integer limit) {
        return userDao.findByPage(start,limit);
    }

    @Override
    public Long getCount() {
        return userDao.getCount();
    }


    @Override
    public void deleteByUserId(Integer userId) {
        System.out.println("userid: "+ userId);
        userDao.deleteByUserId(userId);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public int updByUserId(User user) {
        return userDao.updByUserId(user);
    }
}
