package com.api.test.factory;

public class StringModuleImpl implements StringModule {
    @Override
    public String tenLengthString() {
        return "abcdefgiii";
    }

    @Override
    public String fiveLengthString() {
        return "abcde";
    }
}
