package com.api.test.service.impl;

import com.api.test.dao.JarInfoDao;
import com.api.test.entity.JarInfoEntity;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author xiaoyu
 * @create 2020-11-06
 * @description
 */
@Component
public class JarInfoServiceImp implements JarInfoDao {
    private static final String PACKAGEPATH = "com.api.demo1";

    @Resource
    JarInfoDao jarInfoDao;

    @Override
    public boolean addInterface(JarInfoEntity jarInfoEntity) throws MalformedURLException, ClassNotFoundException {
        String url = jarInfoEntity.getUrl();
        URL[] urls = new URL[]{new URL("file:" + url)};
        URLClassLoader urlcl = new URLClassLoader(urls);
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(PACKAGEPATH)
                .setUrls(ClasspathHelper.forClassLoader(urlcl)).addClassLoader(urlcl)
                .addScanners(new SubTypesScanner()) // 添加子类扫描工具
                .addScanners(new FieldAnnotationsScanner()) // 添加 属性注解扫描工具
                .addScanners(new MethodAnnotationsScanner()) // 添加 方法注解扫描工具
                .addScanners(new MethodParameterScanner()) // 添加方法参数扫描工具
        );
        ArrayList<JarInfoEntity> JarInfoEntityList = new ArrayList<>();

        // 反射出带有指定注解的类
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(FeignClient.class);
        //Set<Method> methods = reflections.getMethodsAnnotatedWith(PostMapping.class) ;
        for (Class clazz : classes) {
            jarInfoEntity.setPackageName(clazz.getName());
//            Class<?> myclass = urlcl.loadClass(clazz.getName());
//            Method[] methods = myclass.getMethods();
//                String name = method.getName();
//                System.out.println("getMethodsAnnotatedWith:" + name);
//                jarInfoEntity.setVoidName(methods);

                JarInfoEntityList.add(jarInfoEntity);
                jarInfoDao.addInterface(jarInfoEntity);

//            for (Method m : methods) {
//                jarInfoEntity.setVoidName(m.getName());
//                JarInfoEntityList.add(jarInfoEntity);
//                Log.info("方法："+m.getName());
//            }
            System.out.println("getTypesAnnotatedWith:" + classes);
        }
        return true;
    }
}