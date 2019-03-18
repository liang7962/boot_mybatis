package com.mybatis.springSources.dao;

import com.mybatis.springSources.pojo.User;

/**
 * Created by Administrator on 2019/3/18.
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User getuserById(String id) {
        User user=new User();
        user.setId(id);
        user.setName("胡亚军");
        user.setAge("18");
        user.setSex("男");
        return user;
    }
}
