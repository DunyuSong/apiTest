package com.api.test.controller;

import com.api.test.entity.JarInfoPluginsEntity;
import com.api.test.model.params.JarInfoParam;
import com.api.test.result.Result;
import com.api.test.service.UpLoadFileService;
import com.api.test.service.impl.JarInfoPluginsServiceImp;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author xiaoyu
 * @create 2020-11-06
 * @description
 */


@RestController
@RequestMapping("/jar")
public class JarInfoPluginsController {
    @Resource
    JarInfoPluginsServiceImp jarInfoPluginsServiceImp;

    @Resource
    UpLoadFileService upLoadFileService;

    @PostMapping(value = "/add")
    public void add(@RequestBody JarInfoParam jarInfoParam) throws MalformedURLException, ClassNotFoundException {
        JarInfoPluginsEntity jarInfoPluginsEntity = new JarInfoPluginsEntity();
        jarInfoPluginsEntity.setUrl(jarInfoParam.getUrl());
        jarInfoPluginsServiceImp.addInterface(jarInfoPluginsEntity);
    }
    @PostMapping(value = "/upload")
    public Result uploadJar(@RequestParam("uploadJar") MultipartFile jarFile) throws IOException {
        return upLoadFileService.uploadJar(jarFile);
    }
}