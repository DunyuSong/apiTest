package com.api.test.factory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonModuleImpl implements JsonModule {
    @Override
    public String MyJson(JSONObject jsonObject, String jsonPath, int count) {
        //获取指定路径下的jsonArray数据
        JSONArray jsonArray = (JSONArray) JSONPath.eval(jsonObject,jsonPath);
        //获取jsonArray里面的第一个元素作为copy的对象
        JSONObject jsonObject_copy = (JSONObject) jsonArray.get(0);
        //清楚jsonArray下面的数据
        JSONPath.remove(jsonObject,jsonPath);
        //插入构造好后的JsonArry
        JSONArray jsonArrayNew = new JSONArray();
        this.insert(jsonArrayNew,count,jsonObject_copy);
        //将够造好后的JsonArray插入到原始的数据中
        JSONPath.set(jsonObject,jsonPath,jsonArrayNew);
        return jsonObject.toString();
    }

    public JSONArray insert(JSONArray jsonArray,int count,JSONObject jsonObjectCopy){
        for(int i=0;i<count;i++){
            JSONObject jsonObjectNew = new JSONObject();
            for(Map.Entry<String,Object> entry : jsonObjectCopy.entrySet()){
                if(entry.getKey().equals("month")){
                    int a = (int)entry.getValue();
                    jsonObjectNew.put(entry.getKey(),a+i);
                }else{
                    jsonObjectNew.put(entry.getKey(),entry.getValue());
                }
            }
            jsonArray.add(jsonObjectNew);
        }
        return jsonArray;
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
//        for(int i = 0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
        for(String a : list){
            System.out.println(a);
        }


    }

}
