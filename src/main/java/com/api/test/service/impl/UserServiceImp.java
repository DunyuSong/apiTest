package com.api.test.service.impl;

import com.api.test.dao.UserDao;
import com.api.test.entity.UserEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserServiceImp implements UserDao {
    @Resource
    UserDao userDao;

    @Override
    public boolean addUser(UserEntity entity) {
        return userDao.addUser(entity);
    }

    @Override
    public List<UserEntity> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public boolean updateUser(UserEntity entity) {
        return userDao.updateUser(entity);
    }
}
