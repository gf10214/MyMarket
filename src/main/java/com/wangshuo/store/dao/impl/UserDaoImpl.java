package com.wangshuo.store.dao.impl;

import com.wangshuo.store.dao.UserDao;
import com.wangshuo.store.domain.User;
import com.wangshuo.store.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Administrator on 2017/5/1 0001.
 */
public class UserDaoImpl implements UserDao{
    @Override
    public void add(User user) throws Exception {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
        qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),
                user.getName(),user.getEmail(),user.getTelephone(),
                user.getBirthday(),user.getSex(),user.getState(),user.getCode());
    }

    @Override
    public User getByCode(String code) throws Exception {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where code=? limit 1";

        return  qr.query(sql,new BeanHandler<>(User.class),code);
    }

    @Override
    public void update(User user) throws Exception {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="update user set username=?,password=?,name=?,email=?,birthday=?,state=?,code=? where uid=?";
        qr.update(sql,user.getUsername(),user.getPassword(),
                user.getName(),user.getEmail(),
                user.getBirthday(),user.getState(),null,user.getUid());
    }

    @Override
    public User getByUserNameAndPwd(String username,String password) throws Exception {
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where username=? and password=? limit 1";

        return  qr.query(sql,new BeanHandler<>(User.class),username,password);
    }


}
