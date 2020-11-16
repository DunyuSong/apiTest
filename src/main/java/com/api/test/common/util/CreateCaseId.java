package com.api.test.common.util;


import org.springframework.stereotype.Component;

@Component
public class CreateCaseId {
    //定义前缀字符串
    private String pre ="Case_";
    //定义返回的caseid
    private String caseId="";
    //定义当前应用新增后测试用例的数量
    private String end_count="";

    //获取创建CaseId的方法，参数：应用名称和当前应用下的测试用例数量
    public String getCreateCaseId(String application,int id){
         //通过getCount方法得到用例编号数
        //end_count=this.getCount(applicationCount);
         //拼接字符串得到caseid
        caseId=pre+application+"_"+id;
        //返回caseId给调用方
        return caseId;
    }

    public String  getCount(int application){
        String countStr="";
        int b=application+1;
        if(b/10>=0 && b/10<1){
            countStr = "000"+b;
        }else if(b/10>=1 && b/10<10){
            countStr = "00"+b;
        }else if(b/10>=10 && b/10<100){
            countStr = "0"+b;
        }else{
            countStr = ""+b;
        }
        return countStr;
    }


}
