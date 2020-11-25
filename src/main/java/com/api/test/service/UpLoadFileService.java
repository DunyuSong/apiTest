package com.api.test.service;

import com.api.test.common.util.MD5Utils;
import com.api.test.dao.JarInfoDao;
import com.api.test.entity.JarInfoEntity;
import com.api.test.entity.JarInfoPluginsEntity;
import com.api.test.result.Result;
import com.api.test.service.impl.JarInfoPluginsServiceImp;
import com.api.test.service.impl.JarInfoServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Service
public class UpLoadFileService {
    @Resource
    JarInfoPluginsServiceImp jarInfoPluginsServiceImp;

    @Resource
    JarInfoServiceImp jarInfoServiceImp;

    @Resource
    JarInfoDao jarInfoDao;

    private final String BASE_PATH = "/Users/warmwater/Desktop/testjar/uploadtest/";

    String targetFilePath = BASE_PATH;

    public Result uploadJar(MultipartFile jarFile) throws IOException {

        Result result = new Result();
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
                        String url = BASE_PATH + jarFile.getOriginalFilename();
                        //上传jar包到jar包信息管理
                        JarInfoEntity jarInfoEntity = new JarInfoEntity();
                        jarInfoEntity.setJarVersion(0);
                        jarInfoEntity.setSalt(MD5);
                        jarInfoEntity.setUrl(url);
                        jarInfoEntity.setJarName(jarFile.getOriginalFilename());
                        this.uploadjarInfo(jarInfoEntity);
                        //扫描jar包,解析jar包
                        JarInfoPluginsEntity jarInfoPluginsEntity = new JarInfoPluginsEntity();
                        jarInfoPluginsEntity.setUrl(url);
                        log.info("解析路径：" + url);
                        log.info("文件名：" + jarFile.getOriginalFilename());
                        this.jarInfoPlugins(jarInfoPluginsEntity);
                        result.setCode(0);
                        result.setMsg("上传成功");
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


    public Result updateloadJar(MultipartFile jarFile, Integer id) {
        Result result = new Result();
        //判断是否是jar文件
        if (jarFile.getOriginalFilename().endsWith(".jar")) {

            List<JarInfoEntity> jarInfoEntityList = jarInfoDao.queryById(id);
            //根据id查询到salt
            String salt = jarInfoEntityList.get(0).getSalt();
            log.info("该id的salt是：" + salt);

            //如果salt一样，更新失败
            File sourceFile = new File(targetFilePath + File.separator + jarFile.getOriginalFilename());
            String MD5 = MD5Utils.getFileMD5String(sourceFile);
            log.info("上传的文件加密后的md5是：" + MD5);
            if (MD5.equals(salt)) {
                result.setCode(1);
                result.setMsg("该jar包已存在，上传失败");
                return result;
            } else {
                //如果salt不一样，更新成功，并添加到jar包管理，并且版本号+1
                String url = BASE_PATH + jarFile.getOriginalFilename();
                JarInfoEntity jarInfoEntity = new JarInfoEntity();
                jarInfoEntity.setJarVersion(jarInfoEntityList.get(0).getJarVersion() + 1);
                jarInfoEntity.setSalt(MD5);
                jarInfoEntity.setUrl(url);
                jarInfoEntity.setJarName(jarFile.getOriginalFilename());
                this.uploadjarInfo(jarInfoEntity);
                result.setCode(0);
                result.setMsg("新版本上传成功");
            }
        }
        return result;
    }






    public boolean uploadjarInfo(JarInfoEntity jarInfoEntity){
        Result result = new Result();
        boolean jarInfo = jarInfoServiceImp.addJar(jarInfoEntity);
        if (!jarInfo == true){
            result.setCode(1);
            result.setMsg("添加jar包到信息管理失败");
            return false;
        }else {
            return true;
        }
    }

    public boolean jarInfoPlugins(JarInfoPluginsEntity jarInfoPluginsEntity) throws MalformedURLException, ClassNotFoundException {
        Result result = new Result();
        boolean jarInfoPluginsResult = jarInfoPluginsServiceImp.addInterface(jarInfoPluginsEntity);
        if (!jarInfoPluginsResult == true){
            result.setCode(1);
            result.setMsg("解析jar包失败");
            return false;
        }else {
            return true;
        }
    }
}

