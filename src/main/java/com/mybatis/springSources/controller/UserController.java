package com.mybatis.springSources.controller;

import com.mybatis.springSources.myInterface.MyAutowired;
import com.mybatis.springSources.myInterface.MyController;
import com.mybatis.springSources.myInterface.MyRequestMapping;
import com.mybatis.springSources.myInterface.MyRequestParam;
import com.mybatis.springSources.pojo.User;
import com.mybatis.springSources.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/3/18.
 */
@MyController
@MyRequestMapping("/ty")
public class UserController {

    @MyAutowired("userServiceImpl")
    private UserService userService;

    @MyRequestMapping("/getUser")
    @ResponseBody
    public User getUser(@MyRequestParam("id") String id){
        User user = userService.getuserById(id);
        return user;
    }
}
