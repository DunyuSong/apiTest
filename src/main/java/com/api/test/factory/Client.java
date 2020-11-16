package com.api.test.factory;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new AbstractFactoryImpl();
//        String a = factory.stringModuleBuilder().tenLengthString();
//        String b = factory.stringModuleBuilder().fiveLengthString();
        String jsonString="{\n" +
                "  \"code\": 1,\n" +
                "  \"msg\": \"success\",\n" +
                "  \"data\": {\n" +
                "    \"billRecord\": [\n" +
                "      {\n" +
                "        \"month\": 1,\n" +
                "        \"bill\": 500\n" +
                "      },\n" +
                "      {\n" +
                "        \"month\": 2,\n" +
                "        \"bill\": 200\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        String newJson = factory.jsonModuleBuilder().MyJson(jsonObject,"$.data.billRecord",12);
        System.out.println(newJson);

        Map<String,Integer> message = new HashMap<>();
        message.put("$.data.billRecord",5);
        message.put("$.data.billHistory",5);

        for(Map.Entry<String,Integer> entry : message.entrySet() ){
            String jsonPath = entry.getKey();
            Integer count = entry.getValue();
        }
    }
}
