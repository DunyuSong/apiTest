package com.api.test.factory;

public interface AbstractFactory {
    //声明该工厂能够造哪些东西
    public StringModule stringModuleBuilder();
    public int intModuleBuilder();
    public JsonModule jsonModuleBuilder();
}
