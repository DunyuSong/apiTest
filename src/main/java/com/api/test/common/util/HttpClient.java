package com.api.test.common.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class HttpClient {
    public static final MediaType HJSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");


    public static Response doPost(RequestBody body, String url, String contentType){
        Response response=null;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .header("Content-Type",contentType)
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public static String  doGet(String url){
        Response response=null;
        String result = "";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(UrlEncodeing.GetRealUrl(url))
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String  doGetWithHeaders(String url, Map<String,Object> headers){
        Response response=null;
        String result = "";
        OkHttpClient okHttpClient = new OkHttpClient();
        Headers headers1 = SetHeaders(headers);
        Request request = new Request.Builder()
                .url(UrlEncodeing.GetRealUrl(url))
                .headers(headers1)
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String  doGet(String url,Map<String,Object> params){
        StringBuffer stringBuffer = new StringBuffer();
        for(Map.Entry entry : params.entrySet()){
            stringBuffer.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        url = url+"?"+stringBuffer.substring(0,stringBuffer.length()-1);



        Response response=null;
        String result = "";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(UrlEncodeing.GetRealUrl(url))
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String doPost2(String body, String url, Map<String,Object> headers){
        RequestBody bodyparam = RequestBody.create(HJSON,body);
        Response response=null;
        String result="";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .post(bodyparam)
                .url(url)
                .addHeader("Content-Type",headers.get("Content-Type").toString())
                .addHeader("x-plat-token",headers.get("x-plat-token").toString())
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    //支持POST类型为application/json的方式
    public static String doPost(String body, String url, Map<String,Object> headers){
        RequestBody bodyparam = RequestBody.create(HJSON,body);
        Headers headers1 = SetHeaders(headers);
        Response response=null;
        String result="";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .post(bodyparam)
                .url(url)
                .headers(headers1)
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    //支持POST类型为 data-from方式
    public static String doPost_form(String body,String url,Map<String,Object> headers){
        String result = "";
        MultipartBody.Builder multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        JSONObject bodyjson = JSONObject.parseObject(body);
        if(body!=null || body.trim()!=""){
            for(Map.Entry entry : bodyjson.entrySet()){
                multipartBody.addFormDataPart(entry.getKey().toString(),entry.getValue().toString());
            }
        }
        Headers headers1 = SetHeaders(headers);
        Response response=null;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .post(multipartBody.build())
                .url(url)
                .headers(headers1)
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public static String doPost_file(){
        String result= "";

        return result;
    }


    public static String doPost_form_urlencoded(String body, String url, Map<String,Object> headers){
        RequestBody bodyparam = RequestBody.create(FORM,body);
        Headers headers1 = SetHeaders(headers);
        Response response=null;
        String result="";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .post(bodyparam)
                .url(url)
                .headers(headers1)
                .build();
        try{
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    //将Map类型的Header转换为okhttp3中的Header类型
    private static Headers SetHeaders(Map<String, Object> headersParams){
        Headers headers=null;
        okhttp3.Headers.Builder headersbuilder=new okhttp3.Headers.Builder();

        if(headersParams != null)
        {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key).toString());
                System.out.println("get http"+"get_headers==="+key+"===="+headersParams.get(key));
            }
        }
        headers=headersbuilder.build();
        return headers;
    }
}
