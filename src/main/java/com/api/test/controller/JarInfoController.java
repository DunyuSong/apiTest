package com.api.test.controller;
import com.api.test.dao.JarInfoDao;
import com.api.test.entity.JarInfoEntity;
import com.api.test.result.Result;
import com.api.test.service.UpLoadFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaoyu
 * @create 2020-11-25
 * @description jar包信息管理
 */

@RestController
@RequestMapping("/jarInfo")
public class JarInfoController {
    @Resource
    JarInfoDao jarInfoDao;
    @Resource
    UpLoadFileService upLoadFileService;


    @GetMapping(value = "/getJarInfo")
    public Result uploadJar(@RequestParam("id") int id) {
        Result result = new Result();
        List<JarInfoEntity> jarInfoEntity =  jarInfoDao.queryById(id);
        result.setCode(0);
        result.setData(jarInfoEntity);
        return result;
    }


    @PostMapping(value = "/updateload")
    public Result uploadJar(@RequestPart("updateFile") MultipartFile updateloadFile,
                            @RequestPart("id") String id) {
        return upLoadFileService.updateloadJar(updateloadFile,Integer.valueOf(id));
    }
}