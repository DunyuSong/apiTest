package com.api.test.dao;

import com.api.test.entity.JarInfoEntity;


import java.util.List;


/**
 * @author xiaoyu
 * @create 2020-11-25
 * @description
 */
public interface JarInfoDao {
    boolean addJar(JarInfoEntity jarInfoEntity);
    boolean updateById(int id);
    boolean updateJarInfo(JarInfoEntity jarInfoEntity);
    List<JarInfoEntity> queryById(int id);

}
