package com.api.test.service;

import com.api.test.common.util.MD5Utils;
import com.api.test.entity.JarInfoPluginsEntity;
import com.api.test.result.Result;
import com.api.test.service.impl.JarInfoPluginsServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class UpLoadFileService {
    @Resource
    JarInfoPluginsServiceImp jarInfoPluginsServiceImp;

    private final String BASE_PATH = "/Users/warmwater/Desktop/testjar/uploadtest/";


    public Result uploadJar(MultipartFile jarFile) throws IOException {

        Result result = new Result();

        String targetFilePath = BASE_PATH;
        //判断是否是jar文件
        if (jarFile.getOriginalFilename().endsWith(".jar")) {
            File targetFile = new File(targetFilePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            File sourceFile = new File(targetFilePath + File.separator + jarFile.getOriginalFilename());
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(sourceFile);
                //文件IO流的传输
                IOUtils.copy(jarFile.getInputStream(), fileOutputStream);
                String MD5 = MD5Utils.getFileMD5String(sourceFile);
                log.info("MD5值" + MD5);
                {
                    if (!MD5.equals("5cbf1883bb59520d96ab647b3fc59298")) {
                        //扫描jar包,解析jar包
                        JarInfoPluginsEntity jarInfoPluginsEntity = new JarInfoPluginsEntity();
                        String url = BASE_PATH+jarFile.getOriginalFilename();
                        jarInfoPluginsEntity.setUrl(url);
                        log.info("解析路径："+url);
                        log.info("文件名："+jarFile.getOriginalFilename());
                        boolean jarInfoResult = jarInfoPluginsServiceImp.addInterface(jarInfoPluginsEntity);
                        result.setCode(0);
                        result.setData(jarInfoResult);
                        result.setMsg("上传解析成功");
                        return result;
                    } else {
                        result.setCode(1);
                        result.setData(MD5);
                        result.setMsg("上传失败，MD5重复");
                        return result;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode(0);
                result.setData(e.getMessage());
                result.setMsg("异常");
                return result;
            } finally {
                fileOutputStream.close();
            }
        }
        return result;
    }
}
