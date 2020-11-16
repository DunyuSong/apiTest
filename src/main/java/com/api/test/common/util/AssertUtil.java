package com.api.test.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;


/**
 * @Classname AssertUtil * @Description TODO * @Date 2019-10-23 16:12 * @Created by fengqian
 */
public class AssertUtil {

    public  String getColumn(String jsonString,String jsonpath){
        String result = "";
        try{
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            result = JSONPath.eval(jsonObject,jsonpath).toString();
        }catch (Exception e){
            e.printStackTrace();
            return "json转换失败";
        }
        return result;
    }
    
}
