//package com.api.test.common.util;
//
///**
// * @Classname UrlEncodeing * @Description TODO * @Date 2019-09-20 19:58 * @Created by fengqian
// */
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.JSONPath;
//import com.api.test.config.EnvConfig;
//import com.sun.net.httpserver.Headers;
//import okhttp3.*;
//
//import javax.xml.ws.Response;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class GetRoleCookies {
//    public static final MediaType HJSON = MediaType.parse("application/json; charset=utf-8");
//    String json ="";
//    Response resp = null ;
//    Response resp1 = null;
//    String url = "";
//    String url2 = "";
//    String url3 = "";
//    String cookies1="";
//    RequestBody body = RequestBody.create(HJSON,json);
//
//    public  String loginAndGetCookie(String role,String mobilePhone,String env)  {
//        if(RoleMap.roleMap.containsKey(role)){
//            //调用Login接口
//            StringBuffer stringBuffer_kf = new StringBuffer();
//            String data_module="{\"mobile\":\"18868459601\",\"token\":\"123143\"}";
//            JSONObject new_data = JSONObject.parseObject(data_module);
//            new_data.put("mobile",mobilePhone);
//            url ="http://odocker3.dian.so/leo/1.0/h5/login?data="+new_data.toString();
//            System.out.println("手机号参数化后的url："+url);
//            String real_url = UrlEncodeing.GetRealUrl(url);
//            resp = this.doGet(real_url);
//            Headers headers1 = resp.headers();
//            List<String> lcookies = headers1.values("Set-Cookie");
//            for(String a : lcookies){
//                stringBuffer_kf.append(a.replace("Path=/","Path=/;"));
//            }
//            cookies1 = stringBuffer_kf.toString();
//            System.out.println("login======："+cookies1);
//            //调用选择角色接口
//            String data_module2="{\"role\":\"agentSellerManager\"}";
//            String roleType = RoleMap.roleMap.get(role);
//            JSONObject new_data2 = JSONObject.parseObject(data_module2);
//            new_data2.put("role",roleType);
//            url2 = "http://odocker3.dian.so/leo/1.0/h5/user/choseRole?data="+new_data2.toString();
//            System.out.println("真实url2："+url2);
//            String real_url2 = UrlEncodeing.GetRealUrl(url2);
//            Map<String,Object> h = new HashMap<String,Object>();
//            h.put("Cookie",cookies1);
//            String result = HttpClient.doGet(real_url2,h);
//            System.out.println("选择角色结果"+result);
//
//            //调用token接口
//            url3 = "http://odocker3.dian.so/leo/1.0/getToken?t=1551177922241&data=%7B%7D";
//            resp1 = this.doGet(url3,"Cookie",cookies1);
//            System.out.println("token=========:"+resp1.headers().get("Set-Cookie"));
//            cookies1 = cookies1+resp1.headers().get("Set-Cookie");
//
//        }else{
//            switch(role){
//                //获取资源服务商管理员的cookie
//                case "rpManager":
//                    String mobile = "13968052510";
//                    url ="http://odocker3.dian.so/leo/1.0/h5/login?t=1551176283703&data=%7B%22mobile%22%3A%22"+mobile+"%22%2C%22token%22%3A%221234%22%7D";
//                    url2 ="http://odocker3.dian.so/leo/1.0/h5/user/choseRole?t=1557908843197&data=%7B%22role%22%3A%22rpManager%22%7D";
//                    resp = this.doPost(body,url);
//                    Headers headers1 = resp.headers();
//                    List<String> lcookies = headers1.values("Set-Cookie");
//                    String cookies = lcookies.get(0);
//                    cookies1 = cookies.replace("Path=/,","Path=/;");
//                    Map headers = new HashMap();
//                    headers.put("Cookie",cookies1);
//                    resp1 = this.doPost(body,url2,cookies1);
//                    break;
//                case "商家":
//                    StringBuffer stringBuffer = new StringBuffer();
//                    String data="{\"companyName\":\"白舟月测试商户\",\"contactMobile\":\"18368860828\",\"contactName\":\"白舟月\",\"mobile\":\"18368860828\",\"role\":\"merchant\",\"city\":\"未知\"}";
//                    JSONObject new_data = JSONObject.parseObject(data);
//                    new_data.put("contactMobile",mobilePhone);
//                    new_data.put("mobile",mobilePhone);
//                    url =EnvConfig.getEnv(env)+"/leo/1.0/merchant/hk/signUp?sign=53d656380d01cd07229674021f09bc8c&t=1571294749764&data="+new_data.toString();
//                    System.out.println("手机号参数化后的url："+url);
//                    String real_url = UrlEncodeing.GetRealUrl(url);
//                    resp = this.doGet(real_url);
//                    Headers g_headers1 = resp.headers();
//                    List<String> g_lcookies = g_headers1.values("Set-Cookie");
//                    for(String a : g_lcookies){
//                        stringBuffer.append(a.replace("Path=/","Path=/;"));
//                    }
//                    Map g_headers = new HashMap();
//                    cookies1 = stringBuffer.toString();
//                    g_headers.put("Cookie",cookies1);
//                    break;
//                case "客服":
//                    StringBuffer stringBuffer_kf = new StringBuffer();
//                    String data_kf="{\"mobile\":\"13750881421\",\"token\":\"1\"}";
//                    JSONObject new_data_kf = JSONObject.parseObject(data_kf);
//                    new_data_kf.put("mobile",mobilePhone);
//                    url =EnvConfig.getEnv(env)+"/leo/1.0/h5/login?t=1571640297323&data="+new_data_kf.toString();
//                    System.out.println("手机号参数化后的url："+url);
//                    String real_url_kf = UrlEncodeing.GetRealUrl(url);
//                    resp = this.doGet(real_url_kf);
//                    Headers kf_headers1 = resp.headers();
//                    List<String> kf_lcookies = kf_headers1.values("Set-Cookie");
//                    for(String a : kf_lcookies){
//                        stringBuffer_kf.append(a.replace("Path=/","Path=/;"));
//                    }
//                    Map kf_headers = new HashMap();
//                    cookies1 = stringBuffer_kf.toString();
//                    kf_headers.put("Cookie",cookies1);
//                    break;
//                case "LHC角色":
//                    String data_lhc="{\"wxUnionid\":\"61575c67-4a0e-4e9e-8c76-cba162e279b0\"}";
//                    JSONObject new_data_lhc = JSONObject.parseObject(data_lhc);
//                    new_data_lhc.put("wxUnionid",mobilePhone);
//                    url = EnvConfig.getEnv(env)+"/lhc/1.0/login?data="+new_data_lhc.toString();
//                    System.out.println("账号参数化后的url："+url);
//                    String real_url_lhc = UrlEncodeing.GetRealUrl(url);
//                    resp = this.doGet(real_url_lhc);
//                    ResponseBody lhc_body = resp.body();
//                    try{
//                        JSONObject resp_body = JSONObject.parseObject(lhc_body.string());
//                        cookies1 = JSONPath.eval(resp_body,"$.data.returnToken").toString();
//                        System.out.println(cookies1);
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                    break;
//                case "广告后台":
//                    url = EnvConfig.getEnv(env)+"/foxDspPlat/login";
//                    System.out.println("账号参数化后的url："+url);
//                    String real_url_gg = UrlEncodeing.GetRealUrl(url);
//                    json = "{\"username\": \"13026202017\",\"password\": \"111111\"}";
//                    RequestBody body_gg = RequestBody.create(HJSON,json);
//                    resp = this.doPost(body_gg,real_url_gg);
//
//                    AssertUtil assertUtil = new AssertUtil();
//                    try{
//                      cookies1 = assertUtil.getColumn(resp.body().string(),"$.data.token");
//                    }catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                    break;
//
//
//            }
//        }
//
//        System.out.println("cookie信息："+cookies1);
//        return cookies1;
//    }
//
//    public Response doPost(RequestBody body,String url){
//        Response response=null;
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .header("Content-Type","application/json")
//                .post(body)
//                .url(url)
//                .build();
//        try{
//             response = okHttpClient.newCall(request).execute();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//    public Response doPost(RequestBody body,String url,String cookie){
//        Response response=null;
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .post(body)
//                .url(url)
//                .header("Cookie",cookie)
//                .build();
//        try{
//            response = okHttpClient.newCall(request).execute();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//    public Response doGet(String url){
//        Response response=null;
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        try{
//            response = okHttpClient.newCall(request).execute();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//    public Response doGet(String url,String key,String value){
//        Response response=null;
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .header(key,value)
//                .url(url)
//                .build();
//        try{
//            response = okHttpClient.newCall(request).execute();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//
//
//}
