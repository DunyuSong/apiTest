package com.api.test.factory;

import com.alibaba.fastjson.JSONObject;

public interface JsonModule {
    public String MyJson(JSONObject jsonObject,String jsonPath,int count);
//    public String MyJson2(JSONObject jsonObject, Map<String,Integer> mesage);
}
