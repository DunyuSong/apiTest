package com.api.test.controller;

import com.api.test.dao.JarInfoDao;
import com.api.test.entity.JarInfoEntity;
import com.api.test.model.params.JarInfoParam;
import com.api.test.service.impl.JarInfoServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.MalformedURLException;

/**
 * @author xiaoyu
 * @create 2020-11-06
 * @description
 */


@RestController
@RequestMapping("/jar")
public class JarInfoController {
    @Resource
    JarInfoDao jarInfoDao;
    @Resource
    JarInfoServiceImp jarInfoServiceImp;

    @PostMapping(value = "/add")
    public void add(@RequestBody JarInfoParam jarInfoParam) throws MalformedURLException, ClassNotFoundException {
        JarInfoEntity jarInfoEntity = new JarInfoEntity();
        jarInfoEntity.setUrl(jarInfoParam.getUrl());
        jarInfoServiceImp.addInterface(jarInfoEntity);
    }
}