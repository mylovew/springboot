package com.zwk.springboot.controller;

import com.zwk.springboot.entity.Role;
import com.zwk.springboot.entity.User;
import com.zwk.springboot.service.UserRoleService;
import com.zwk.springboot.service.UserService;
import com.zwk.springboot.util.MD5;
import com.zwk.springboot.util.ResultAPI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: 用户接口
 * @author: wkzhang
 * @create: 2019-07-22 11:23
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @RequiresPermissions(value = "user:userList")
    @RequestMapping("userList")
    public String userList(Map<String,Object> map){
        log.info("去userList页面");

        return "/admin/userList";
    }
    @RequiresPermissions(value = "user:userList")
    @RequestMapping("userListPage")
    @ResponseBody
    public Map<String,Object> userListPage(Integer page,Integer limit){
        log.info("加载用户列表");
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",userService.getCount());
        map.put("data",userService.findByPage((page-1)*limit,limit));
        return map;
    }

    @RequiresPermissions(value = "user:del")
    @RequestMapping("del")
    @ResponseBody
    public ResultAPI del(Integer user_id){
        log.info("删除用户");
        try {
            userService.deleteByUserId(user_id);
            return new ResultAPI(1,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultAPI(0,"删除失败");
    }
    @RequiresPermissions(value = "user:save")
    @RequestMapping("save")
    @ResponseBody
    public ResultAPI save(User user, Integer roleId){
        log.info("新增用户:"+user.toString());
        log.info("roleId:"+roleId);
        try {
            //密码加密
            user.setPassword(MD5.MD5(user.getPassword()));
            List<Role> roleList = new ArrayList<>();
            Role role = new Role();
            role.setRoleId(roleId);
            roleList.add(role);
            user.setRoleList(roleList);
            User save = userService.save(user);
            return new ResultAPI(1,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultAPI(0,"添加失败");
    }
    @RequiresPermissions(value = "user:upd")
    @RequestMapping("upd")
    @Transactional//事务
    @ResponseBody
    public ResultAPI upd(User user, Integer roleId) throws SQLException{
        log.info("修改用户:"+user.toString());
        log.info("roleId:"+roleId);
        user.setPassword(null);//密码不允许修改
        int upd = userService.updByUserId(user);
        int roleUpd = userRoleService.updRoleIdByUserId(roleId,user.getUserId());
        return new ResultAPI(1,"修改成功");
    }


}
