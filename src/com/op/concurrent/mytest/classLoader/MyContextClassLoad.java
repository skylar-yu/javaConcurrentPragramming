
package com.op.concurrent.mytest.classLoader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author yutianci
 * @version V1.0
 * @Title: MyContextClassLoad.java
 * @Package com.op.concurrent.mytest.classLoader
 * @Description
 * @date 2018 01-02 17:57.
 */
public class MyContextClassLoad {

    public static void testContextClassLoader(){
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:>>>" + driver.getClass() + ",loader:>>>" + driver.getClass().getClassLoader());
        }
        System.out.println("current thread contextloader:>>>"+Thread.currentThread().getContextClassLoader());

        System.out.println("current loader:>>>" + MyContextClassLoad.class.getClassLoader());
        System.out.println("ServiceLoader loader:>>>" + ServiceLoader.class.getClassLoader());
    }


    public static void main(String []arg){

//        testContextClassLoader();
        System.out.println("--------------------------------------------");
        testContextClassLoader2();
    }

    public static void testContextClassLoader2() {
        //获取extclassloader
        ClassLoader extClassloader = MyContextClassLoad.class.getClassLoader().getParent();
        System.out.println("extloader:"  +extClassloader);
        //设置当前线程上下文加载器为ext
        Thread.currentThread().setContextClassLoader(extClassloader);
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("current thread context loader:"  +Thread.currentThread().getContextClassLoader());
        System.out.println("current loader:" + MyContextClassLoad.class.getClassLoader());
        System.out.println("ServiceLoader loader:" + ServiceLoader.class.getClassLoader());
    }
}
