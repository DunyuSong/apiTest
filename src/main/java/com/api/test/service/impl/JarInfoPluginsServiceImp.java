package com.api.test.service.impl;

import com.api.test.dao.JarInfoPluginsDao;
import com.api.test.entity.JarInfoPluginsEntity;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author xiaoyu
 * @create 2020-11-06
 * @description
 */
@Service
public class JarInfoPluginsServiceImp implements JarInfoPluginsDao {
    private static final String PACKAGEPATH = "com.api.demo1";

    @Resource
    JarInfoPluginsDao jarInfoPluginsDao;

    @Override
    public boolean addInterface(JarInfoPluginsEntity jarInfoPluginsEntity) throws MalformedURLException, ClassNotFoundException {
        String url = jarInfoPluginsEntity.getUrl();
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
        List<JarInfoPluginsEntity> jarInfoPluginsEntityList = new ArrayList<>();

        // 反射出带有指定注解的类
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(FeignClient.class);
        //Set<Method> methods = reflections.getMethodsAnnotatedWith(PostMapping.class) ;
        for (Class clazz : classes) {
            jarInfoPluginsEntity.setPackageName(clazz.getName());
//            Class<?> myclass = urlcl.loadClass(clazz.getName());
//            Method[] methods = myclass.getMethods();
//                String name = method.getName();
//                System.out.println("getMethodsAnnotatedWith:" + name);
//                jarInfoPluginsEntity.setVoidName(methods);

                jarInfoPluginsEntityList.add(jarInfoPluginsEntity);
                jarInfoPluginsDao.addInterface(jarInfoPluginsEntity);

//            for (Method m : methods) {
//                jarInfoPluginsEntity.setVoidName(m.getName());
//                jarInfoPluginsEntityList.add(jarInfoPluginsEntity);
//                Log.info("方法："+m.getName());
//            }
            System.out.println("getTypesAnnotatedWith:" + classes);



        }
        return true;
    }
}