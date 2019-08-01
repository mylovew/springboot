package com.zwk.springboot.controller;

import com.zwk.springboot.entity.User;
import com.zwk.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springboot
 * @description: 用户接口
 * @author: wkzhang
 * @create: 2019-07-22 11:23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @RequestMapping("findAll")
    public List<User> findAll(){
        log.info("findAll");
        List<User> userList = userService.findAll();
        System.out.println(userList.get(0).getName());
        System.out.println(userList.get(0).getRoleList());
        return null;
    }

    @RequestMapping("findByName")
    public User findByName(){
        return userService.findByName("admin");
    }
}
