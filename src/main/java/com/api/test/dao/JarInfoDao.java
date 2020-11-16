package com.api.test.dao;

import com.api.test.entity.JarInfoEntity;

import java.net.MalformedURLException;


/**
 * @author xiaoyu
 * @create 2020-11-06
 * @description
 */
public interface JarInfoDao {

    boolean addInterface(JarInfoEntity jarInfoEntity) throws MalformedURLException, ClassNotFoundException;//不需要返回值List<JarInfoEntity>会报错

}