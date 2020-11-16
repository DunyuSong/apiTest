package com.api.test.service.impl;

import com.api.test.common.util.*;
import com.api.test.dao.TestCaseDao;
import com.api.test.entity.TestCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.List;

@Component
public  class TestCaseServiceImp implements TestCaseDao {

    @Resource
    TestCaseDao testCaseDao;
    @Resource
    CreateCaseId createCaseId;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    //添加用例的实现逻辑
    @Override
    public boolean addCase(TestCaseEntity testCaseEntity){
        testCaseDao.addCase(testCaseEntity);
        int id = testCaseDao.queryIdByCaseName(testCaseEntity.getCaseName());
        //自动生成caseid
        String caseId = createCaseId.getCreateCaseId(testCaseEntity.getApplication(),id);
        return  testCaseDao.updateCaseIdById(caseId,id);
    }

    public boolean updateByCaseId(TestCaseEntity testCaseEntity) {
        return testCaseDao.updateById(testCaseEntity);
    }

    //查询所有用例
    @Override
    public List<TestCaseEntity> queryAll(){
        return testCaseDao.queryAll();
    }

    @Override
    public List<String> queryCaseName() {
        return testCaseDao.queryCaseName();
    }

    //更新用例
    @Override
    public boolean updateById(TestCaseEntity testCaseEntity){
        LOGGER.info("更新API："+testCaseEntity.getApi());
        String caseId = createCaseId.getCreateCaseId(testCaseEntity.getApplication(),testCaseEntity.getId());
        testCaseDao.updateCaseIdById(caseId,testCaseEntity.getId());
        return testCaseDao.updateById(testCaseEntity);
    }
    //按照作者查询
    @Override
    public List<TestCaseEntity> queryByDetail(TestCaseEntity testCaseEntity){
        return testCaseDao.queryByDetail(testCaseEntity);
    }

    //删除用例
    @Override
    public boolean deleteByCaseId(TestCaseEntity testCaseEntity){
        return  testCaseDao.deleteByCaseId(testCaseEntity);
    }

    //按照测试用例id查询
    @Override
    public List<TestCaseEntity> queryByCaseId(TestCaseEntity testCaseEntity){
        return testCaseDao.queryByCaseId(testCaseEntity);
    }

    //调试用例
//    public CaseActionEntity action(TestCaseEntity testCaseEntity) {
//        GetRoleCookies getRoleCookies = new GetRoleCookies();
//        CaseActionEntity caseActionEntity = new CaseActionEntity();
//        //获取当前测试用例
//        List<TestCaseEntity> testCases = this.queryByCaseId(testCaseEntity);
//        //拿到第一条用例数据
//        TestCaseEntity testCase = testCases.get(0);
//        if(testCases.size() == 0)
//        {
//            System.out.println(testCaseEntity.getCaseId());
//            return caseActionEntity;
//        }
//        LOGGER.info(testCase.getCaseId());
//        //环境选择
//        String Env = EnvConfig.getEnv(testCase.getEnv());
//        //获取api
//        String url=Env+testCase.getApi();
//        if(url.contains("$")){
//            //进行URL转码
//            url = UrlEncodeing.GetRealUrl(url.replace("$",String.valueOf(System.currentTimeMillis())));
//            LOGGER.info("URL包含随机参数："+url);
//        }else{
//            url = UrlEncodeing.GetRealUrl(url);
//        }
//
//        LOGGER.info("运行环境为:"+url);
//        //获取预期结果
//        String expectResult = testCase.getExpectResult();
//        Map<String,Object> body = new HashMap<>();
//        Map<String,Object> header = new HashMap<>();
//        //获取入参转换成json格式
//        //做json解析，支持随机参数
//        if( testCase.getParam()!=null && !testCase.getParam().trim().isEmpty())
//        {
//            JSONObject jsonParam = JSONObject.parseObject(testCase.getParam());
//        for(Map.Entry<String,Object> entry : jsonParam.entrySet()){
//            if(entry.getValue() == null)
//            {
//                body.put(entry.getKey(),entry.getValue());
//                LOGGER.info("入参键值："+entry.getKey()+"===="+entry.getValue());
//            }else{
//                if(entry.getValue().toString().startsWith("$")){
//                    String newValue = entry.getValue().toString().replace("$","")+System.currentTimeMillis();
//                    body.put(entry.getKey(),newValue);
//                    LOGGER.info("入参键值(随机)："+entry.getKey()+"===="+newValue);
//                }else{
//                    body.put(entry.getKey(),entry.getValue());
//                    LOGGER.info("入参键值："+entry.getKey()+"===="+entry.getValue());
//                }
//            }
//
//        }
//        }
//
//        //发送请求，传递参数
//        if(testCase.getRole()!=null && !testCase.getRole().isEmpty()){
//            if(testCase.getRole().equals("LHC角色")){
//                String token = getRoleCookies.loginAndGetCookie(testCase.getRole(),testCase.getRoleAccount(),testCase.getEnv());
//                header.put("x-lhc-token",token);
//                LOGGER.info("LHC角色token："+token);
//            }else if(testCase.getRole().equals("广告后台")){
//                String token = getRoleCookies.loginAndGetCookie(testCase.getRole(),testCase.getRoleAccount(),testCase.getEnv());
//                header.put("x-plat-token",token);
//                LOGGER.info("广告后台角色token："+token);
//            }
//            else{
//                String cookie=getRoleCookies.loginAndGetCookie(testCase.getRole(),testCase.getRoleAccount(),testCase.getEnv());
//                header.put("Cookie",cookie);
//                LOGGER.info("Cookie信息："+cookie);
//            }
//
//        }
//        header.put("Content-Type",testCase.getHeader());
//        String type = testCase.getMethod();
//        String result = "";
//        switch (type){
//            case "POST":
//                LOGGER.info("开始执行POST方式：");
//                if(testCase.getRole().equals("广告后台")){
//                    result = HttpClient.doPost2(testCase.getParam(),url,header);
//                }else if(testCase.getHeader().equals("multipart/form-data")){
//                    result = HttpClient.doPost_form(testCase.getParam(),url,header);
//                }
//                else{
//                    result = HttpClient.doPost(testCase.getParam(),url,header);
//                }
//
//                break;
//            case "GET":
//                LOGGER.info("开始执行GET方式：");
//                result = HttpClient.doGetWithHeaders(
//                        url,header);
//                break;
//        }
//        LOGGER.info("实际结果："+result);
//        caseActionEntity.setCaseId(testCase.getCaseId());
//        caseActionEntity.setActualResult(result);
//        caseActionEntity.setExpectResult(expectResult);
//        if(testCase.getSqlJson()!= null && testCase.getSqlJson().trim()!=""){
//             MediaType HJSON = MediaType.parse("application/json; charset=utf-8");
//             RequestBody sqlbody = RequestBody.create(HJSON,testCase.getSqlJson());
//             Response sqlResult = HttpClient.doPost(sqlbody, DataFactoryApi.DOSQLLIST,"application/json");
//           try{
//               LOGGER.info("sqlResult="+sqlResult.body().string());
//           }catch (IOException e){
//               e.printStackTrace();
//           }
//        }
//        return caseActionEntity;
//    }

    //批量调试用例
//    public List<CaseActionEntity> actionAll (TestCaseEntity testCaseEntity){
//        List<CaseActionEntity> caseActionEntityList = new ArrayList<>();
//        System.out.println(testCaseEntity.getEnv());
//        String Env = EnvConfig.getEnv(testCaseEntity.getEnv());
//        System.out.println("===========111111:"+Env);
//        //获取当前测试用例
//        List<TestCaseEntity> testCases =  testCaseDao.queryByDetail(testCaseEntity);
//        for(int i=0;i<testCases.size();i++){
//            //拿到用例数据
//            TestCaseEntity testCase = testCases.get(i);
//            LOGGER.info(testCase.getCaseId());
//            try{
//                CaseActionEntity caseActionEntity = this.action(testCase);
//                //测试用例执行结果实体中set相关信息
//                if(caseActionEntity.getExpectResult().equals(caseActionEntity.getActualResult())){
//                    caseActionEntity.setIsPass("Pass");
//                }else{
//                    caseActionEntity.setIsPass("Fail");
//                }
//                caseActionEntityList.add(caseActionEntity);
//                LOGGER.info(caseActionEntityList.get(i).getCaseId());
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//        for(int a=0;a<caseActionEntityList.size();a++){
//            LOGGER.info("第"+(a+1)+"条数据为："+caseActionEntityList.get(a).getCaseId());
//        }
//        return caseActionEntityList;
//    }

    //统计用例数量
    @Override
    public int count(TestCaseEntity testCaseEntity){
        return  testCaseDao.count(testCaseEntity);
    }

    @Override
    public int queryIdByCaseName(String caseName){
        return testCaseDao.queryIdByCaseName(caseName);
    }

    @Override
    public boolean updateCaseIdById(String caseId,int id){
        return testCaseDao.updateCaseIdById(caseId,id);
    }

    @Override
    public boolean updateIsDeleteById(TestCaseEntity testCaseEntity) {
        return testCaseDao.updateIsDeleteById(testCaseEntity);
    }

}
