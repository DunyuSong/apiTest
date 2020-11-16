package com.api.test.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractFactoryImpl implements AbstractFactory {
    @Override
    public StringModule stringModuleBuilder() {
        return new StringModuleImpl();
    }

    @Override
    public int intModuleBuilder() {
        return 0;
    }

    @Override
    public JsonModule jsonModuleBuilder() {
        return new JsonModuleImpl();
    }

    public void f1(){
        Map map = new HashMap();
        Map map1 = new ConcurrentHashMap();
        StringModule stringModule = new StringModuleImpl();
    }

}
