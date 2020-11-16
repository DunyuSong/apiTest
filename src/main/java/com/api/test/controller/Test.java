package com.api.test.controller;

import com.api.test.dao.TestCaseDao;
import com.api.test.entity.TestCaseEntity;
import com.api.test.entity.UserEntity;
import com.api.test.service.impl.TestCaseServiceImp;
import com.api.test.service.impl.UserServiceImp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {
    @Resource
    TestCaseServiceImp testCaseServiceImp;
    @Resource
    UserServiceImp userServiceImp;
    @Resource
    TestCaseDao testCaseDao;

    @GetMapping(value = "/queryaa")
    public String aa(){
        return "aa";
    }

//    @GetMapping(value = "/querybb")
//    public String bb(){
//        return "bb";
//    }
    @PostMapping(value = "/querycc")
    public String cc(){
        return "cc";
    }

    @GetMapping(value = "/queryCase")
    public List<TestCaseEntity> casequery(){
        return testCaseServiceImp.queryAll();
    }

    @GetMapping(value = "/update")
    public boolean update(){
        TestCaseEntity testCaseEntity = new TestCaseEntity();
        testCaseEntity.setId(2);
        testCaseDao.updateById(testCaseEntity);
        return true;
    }

    @PostMapping(value = "/add")
    public boolean add(@RequestBody TestCaseEntity entity){
        return testCaseDao.addCase(entity);
    }

    public static void main(String[] args) {
        System.out.println(1);
    }

    @PostMapping(value = "/addUser")
    public boolean addUser(@RequestBody UserEntity entity){
        List list = new ArrayList();
        list.size();
       return  userServiceImp.addUser(entity);
    }

}
