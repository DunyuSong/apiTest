package com.api.test.service.impl;

import com.api.test.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xiaoyu
 * @create 2020-11-25
 * @description
 */
public class UpdateLoadFileService {
    public Result uploadJar(MultipartFile jarFile) throws IOException {
        //根据id查询到salt

        //如果salt一样，更新失败

        //如果salt不一样，更新成功，并添加到jar包管理，并且版本号+1


        return null;
        }

    }
