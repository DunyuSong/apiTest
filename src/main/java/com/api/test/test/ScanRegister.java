package com.api.test.test;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ScanRegister {

    /**
     * 参数说明
     * PACKAGEPATH:需要扫描的包PATH
     * extendClass:被扫描的接口
     */
    private static final String PACKAGEPATH = "com.api.demo1";
    private static final String METAINFPATH="META-INF/services/so.dian.qa.dataFactory.dataBase.Register";
    private Class extendClass;
    /**
     * 参数说明：
     * path:jar包所在磁盘路径
     * extendClassName:被扫描的接口名称（如："Register" || "Handle"）
     */
//    public <T> List<String> scanInterfaceExtends(String path, String extendClassName) throws MalformedURLException {
//        //判断指定所需扫描的接口
//        if(extendClassName.equals("Register")){        //定义所有指定接口实现类的全限定名集合
//
//            logger.info(extendClassName);
//            extendClass = Register.class;
//        }else if(extendClassName.equals("Handle")){
//            logger.info(extendClassName);
//            extendClass = Handler.class;
//        }
//        ClassLoader cl = Thread.currentThread().getContextClassLoader();
//        List<String> classNames = new ArrayList<>();
//        URL[] urls = new URL[]{new URL(path)};
//        //采用自定义类加载器用于jar包运行的容器隔离
//        BundleClassLoader classLoader   = new BundleClassLoader(urls,cl);
//        Reflections reflections = new Reflections(PACKAGEPATH,classLoader);
//        //反射获取所有继承或实现某接口的实现类集合
//        Set<Class<? extends T>> classes = reflections.getSubTypesOf(extendClass);
//        for(Class clazz : classes){
//            classNames.add(clazz.getName());
//        }
//        return classNames;
//    }

    public List<String> scanRegisters(String filePath) throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        List<String> classNames = new ArrayList<>();
        URL[] urls = new URL[]{new URL(filePath)};
        BundleClassLoader classLoader   = new BundleClassLoader(urls,cl);


        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackages(PACKAGEPATH)
                        .addClassLoader(classLoader)
                        .addUrls(urls)
                        .addScanners(new FieldAnnotationsScanner())
                        .addScanners(new MethodAnnotationsScanner())
                        .addScanners(new MethodParameterScanner())
                        .addScanners(new SubTypesScanner())
        );
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(FeignClient.class);
        for (Class clazz : classes) {
            System.out.println(clazz.getName() + "对应类加载器" + clazz.getClassLoader());
            classNames.add(clazz.getName());
        }
        return classNames;
    }

//    public List<String> scanJar(String filePath) throws IOException {
//        List<String> classNames = new ArrayList<>();
//        String line;
//        URL url = new URL("jar:file:" + filePath + "!/" + METAINFPATH);
//        InputStream is = url.openStream(); //使用url的方式获取输入流
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        while ((line = reader.readLine()) != null){
//            System.out.println("扫描Register类:"+line);
//            classNames.add(line);
//        }
//        return classNames;
//    }

    public static void main(String[] args) throws IOException {
        System.out.println(1);
        ScanRegister scanRegister = new ScanRegister();
        scanRegister.scanRegisters("file:/Users/warmwater/Desktop/testjar/ofc-client-1.0.9.jar");
    }

    public void ff() throws IOException {
        System.out.println(1);
        ScanRegister scanRegister = new ScanRegister();
        scanRegister.scanRegisters("file:\\D:\\myjar\\ofc-client-1.0.9.jar");
    }

}
