package com.mybatis.springSources.dao;

import com.mybatis.springSources.pojo.User;

/**
 * Created by Administrator on 2019/3/18.
 */
public interface UserDao {
    User getuserById(String id);
}
