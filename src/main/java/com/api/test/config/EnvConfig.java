package com.api.test.config;

public  class EnvConfig {
    public final static String ODOCKER3 = "http://odocker3.dian.so";
    public final static String ODOCKER4 = "http://odocker4.dian.so";
    public final static String ZDOCKER3 = "http://zdocker3.dian.so";
    public final static String WDOCKER3 = "http://wdocker3.dian.so";
    public final static String ODEV = "http://odev.dian.so";
    public final static String BDEV = "http://bdev.dian.so";
    public final static String FOXDEV = "http://foxdev.dian.so";
    public final static String ZDEV = "http://zdev.dian.so";
    public final static String WDEV = "http://wdev.dian.so";
    public final static String VEN = "http://venicedocker1.dian.so";
    public static String getEnv(String env){
        String url_pre = "";
        switch (env){
            case "odocker3":
                url_pre = ODOCKER3;
                break;
            case "odocker4":
                url_pre = ODOCKER4;
                break;
            case "zdocker3":
                url_pre = ZDOCKER3;
                break;
            case "wdocker3":
                url_pre = WDOCKER3;
                break;
            case "odev":
                url_pre = ODEV;
                break;
            case "bdev":
                url_pre = BDEV;
                break;
            case "foxdev":
                url_pre = FOXDEV;
                break;
            case "zdev":
                url_pre = ZDEV;
                break;
            case "wdev":
                url_pre = WDEV;
                break;
            case "venicedocker1":
                url_pre = VEN;
        }
        return url_pre;
    }


}
