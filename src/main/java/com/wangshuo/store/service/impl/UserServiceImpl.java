package com.wangshuo.store.service.impl;

import com.wangshuo.store.dao.UserDao;
import com.wangshuo.store.dao.impl.UserDaoImpl;
import com.wangshuo.store.domain.User;
import com.wangshuo.store.service.UserService;
import com.wangshuo.store.utils.MailUtils;


/**
 * Created by Administrator on 2017/5/1 0001.
 */
public class UserServiceImpl implements UserService {

    @Override
    public void regist(User user) throws Exception {
        UserDao dao=new UserDaoImpl();
        dao.add(user);
        //发送激活邮件
        MailUtils.sendMail(user.getEmail(),"欢迎注册成为我们一员<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+"'>点此激活</a>");
    }

    @Override
    public User active(String code) throws Exception {
        UserDao dao=new UserDaoImpl();
        User user=dao.getByCode(code);
        if(user==null){
            return null;
        }
        user.setState(1);
        dao.update(user);
        return user;
    }

    @Override
    public User login(String username, String password) throws Exception {
        UserDao dao=new UserDaoImpl();
        return dao.getByUserNameAndPwd(username,password);
    }
}
