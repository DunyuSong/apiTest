package com.api.test.test;

public class MyTest {
    public static void main(String[] args) {
        MyTest myTest  =  new MyTest();
        System.out.println(myTest.getClass().getClassLoader());
        Class<?> a = null;
        System.out.println(a.getClassLoader());
    }

}
