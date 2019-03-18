package com.mybatis.springSources.service.impl;

import com.mybatis.springSources.dao.UserDao;
import com.mybatis.springSources.myInterface.MyAutowired;
import com.mybatis.springSources.myInterface.MyService;
import com.mybatis.springSources.pojo.User;
import com.mybatis.springSources.service.UserService;

/**
 * Created by Administrator on 2019/3/18.
 */
@MyService("userServiceImpl")
public class UserServiceImpl implements UserService{
    @MyAutowired
    private UserDao userDao;

    @Override
    public User getuserById(String id) {
        return userDao.getuserById(id);
    }
}
