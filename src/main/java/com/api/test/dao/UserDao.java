package com.api.test.dao;

import com.api.test.entity.UserEntity;

import java.util.List;

public interface UserDao {
    public boolean addUser(UserEntity entity);
    public List<UserEntity> queryAll();
    public boolean updateUser(UserEntity entity);
}
