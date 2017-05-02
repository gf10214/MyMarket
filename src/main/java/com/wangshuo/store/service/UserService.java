package com.wangshuo.store.service;

import com.wangshuo.store.domain.User;


/**
 * Created by Administrator on 2017/5/1 0001.
 */
public interface UserService {
    void regist(User user) throws Exception;

    User active(String code) throws Exception;

    User login(String username, String password) throws Exception;
}
