package com.api.test.service.impl;

import com.api.test.dao.JarInfoDao;
import com.api.test.entity.JarInfoEntity;
import com.api.test.entity.TestCaseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaoyu
 * @create 2020-11-25
 * @description
 */
@Service
public class JarInfoServiceImp implements JarInfoDao {
    @Resource
    JarInfoDao jarInfoDao;

    @Override
    public boolean addJar(JarInfoEntity jarInfoEntity) {
        return jarInfoDao.addJar(jarInfoEntity);
    }

    @Override
    public boolean updateById(int id) {
        return false;
    }

    @Override
    public boolean updateJarInfo(JarInfoEntity jarInfoEntity) {
        return false;
    }

    @Override
    public List<JarInfoEntity> queryById(int id) {
        return jarInfoDao.queryById(id);
    }


}