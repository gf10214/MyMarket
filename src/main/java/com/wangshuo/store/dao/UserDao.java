package com.wangshuo.store.dao;

import com.wangshuo.store.domain.User;


/**
 * Created by Administrator on 2017/5/1 0001.
 */
public interface UserDao {
    void add(User user) throws Exception;

    User getByCode(String code) throws Exception;

    void update(User user) throws Exception;


    User getByUserNameAndPwd(String username,String password) throws Exception;
}
