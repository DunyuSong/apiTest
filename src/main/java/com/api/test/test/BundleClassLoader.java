package com.api.test.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Classname BoundleClassLoader * @Description TODO * @Date 2020-04-22 18:11 * @Created by fengqian
 */
public class  BundleClassLoader extends URLClassLoader {
    private static Logger logger = LoggerFactory.getLogger(BundleClassLoader.class);

    public BundleClassLoader(URL[] urls,ClassLoader parent) {
        super(urls,parent);
    }
    public BundleClassLoader(URL[] urls){
        super(urls);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        logger.debug("try find class {}", name);
        Class<?> claz = null;
        try {
            claz = super.findClass(name);
        } catch (ClassNotFoundException e) {
            claz = null;
        }
        if (claz != null) {
            logger.debug("load from class path for {}", name);
            return claz;
        }
        logger.warn("not found class {}", name);
        throw new ClassNotFoundException(name);
    }

}
