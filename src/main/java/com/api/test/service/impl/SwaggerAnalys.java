package com.api.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.api.test.common.util.HttpClient;
import com.api.test.entity.TestCaseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SwaggerAnalys {
    List<TestCaseEntity> listEntity = new ArrayList<>();

    public List<Map<String,TestCaseEntity>> check(){
        String url;
        List<Map<String,TestCaseEntity>> reusltList = new ArrayList<>();
        //拿到已落库的接口用例信息
        List<TestCaseEntity> list  = this.init();
        //拿到最新的swagger接口信息
        String swaggerInfo = HttpClient.doGet("http://localhost:8080/v2/api-docs");
        JSONObject swaggerJson = JSONObject.parseObject(swaggerInfo);
        String result = JSONPath.eval(swaggerJson,"$.paths").toString();
        JSONObject resultJson = JSONObject.parseObject(result);
        //拿到每个已落库接口信息的api，判断是否存在于最新的swagger文档中
        for(TestCaseEntity entity : list ){
            url = entity.getApi();
            boolean flag = false;
            for(Map.Entry<String,Object> entry:resultJson.entrySet()){
                if(url.equals(entry.getKey())){
                    flag = true;
                    System.out.println("该接口信息已存在，不变");
                }
            }
            if(flag == false){
                TestCaseEntity testCaseEntityDelete = new TestCaseEntity();
                testCaseEntityDelete.setApi(url);
                Map<String,TestCaseEntity> mapDelete = new HashMap<>();
                mapDelete.put("删除",testCaseEntityDelete);
                reusltList.add(mapDelete);
                System.out.println("该接口已被删除："+url);
            }
        }
        for(Map.Entry<String,Object> entry : resultJson.entrySet()){
            boolean flag2 = false;
            for(TestCaseEntity entity : list){
                if(entry.getKey().equals(entity.getApi())){
                    flag2 =true;
                }
            }
            if(flag2 == false){
                TestCaseEntity testCaseEntityAdd = new TestCaseEntity();
                testCaseEntityAdd.setApi(entry.getKey());

                Map<String,TestCaseEntity> mapAdd = new HashMap<>();
                mapAdd.put("新增",testCaseEntityAdd);
                reusltList.add(mapAdd);
                System.out.println("新增接口："+entry.getKey());
            }
        }
        return reusltList;
    }

    public List<TestCaseEntity> init(){
        TestCaseEntity testCaseEntity1 = new TestCaseEntity();
        testCaseEntity1.setApi("/test/queryaa");
        TestCaseEntity testCaseEntity2 = new TestCaseEntity();
        testCaseEntity2.setApi("/test/querybb");

        this.listEntity.add(testCaseEntity1);
        this.listEntity.add(testCaseEntity2);
        return listEntity;
    }


    public static void main(String[] args) {
        SwaggerAnalys swaggerAnalys = new SwaggerAnalys();
        System.out.println(swaggerAnalys.check());
//        SwaggerAnalys swaggerAnalys = new SwaggerAnalys();
//        List<TestCaseEntity> list  = swaggerAnalys.init();
//        String swaggerInfo = HttpClient.doGet("http://localhost:8080/v2/api-docs");
//        JSONObject swaggerJson = JSONObject.parseObject(swaggerInfo);
//        String result = JSONPath.eval(swaggerJson,"$.paths").toString();
//        JSONObject resultJson = JSONObject.parseObject(result);
//        String api = "/test/queryaa";
//        boolean flag = false;
//        for(Map.Entry<String,Object> entry:resultJson.entrySet()){
//            if(api.equals(entry.getKey())){
//                flag = true;
//                System.out.println("该接口信息已存在，不变");
//                break;
//            }
//        }
//        if(flag == false){
//            System.out.println("该接口已被删除："+api);
//        }
//        for(Map.Entry<String,Object> entry : resultJson.entrySet()){
//            boolean flag2 = false;
//            for(TestCaseEntity entity : list){
//                if(entry.getKey().equals(entity.getApi())){
//                    flag2 =true;
//                }
//            }
//            if(flag2 == false){
//                System.out.println("新增接口："+entry.getKey());
//            }
//        }

    }
}
