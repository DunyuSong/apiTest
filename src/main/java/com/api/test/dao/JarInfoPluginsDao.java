package com.api.test.dao;

import com.api.test.entity.JarInfoPluginsEntity;

import java.net.MalformedURLException;


/**
 * @author xiaoyu
 * @create 2020-11-06
 * @description
 */
public interface JarInfoPluginsDao {

    boolean addInterface(JarInfoPluginsEntity jarInfoPluginsEntity) throws MalformedURLException, ClassNotFoundException;//不需要返回值List<JarInfoPluginsEntity>会报错

}