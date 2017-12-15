package com.op.concurrent.mytest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * http://blog.csdn.net/luanlouis/article/details/24589193
 */
public class TestJdkProxyMain {
    public static void main(String[] args) {
        final ElectricCar ec = new ElectricCar();
        Object o = Proxy.newProxyInstance(ec.getClass().getClassLoader(), ec.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getDeclaringClass()==Object.class);
                System.out.println(method.getDeclaringClass());
                if("recharge".equals(method.getName())) {
                    System.out.println("pre Handler...recharge");
                    Object result = method.invoke(ec, args);
                    System.out.println("after Handler>>>recharge");
                    return result;
                } else {
                    return method.invoke(ec,args);
                }
//                method.invoke(ec,args);
//                return null;
            }
        });
//        System.out.println(o);
//        ElectricCar proxy  = (ElectricCar) o;
        Rechargable proxy = (Rechargable) o;
        proxy.recharge();
        Vehicle proxy2 = (Vehicle) o;
        proxy2.drive();

        ProxyUtils.generateClassFile(ec.getClass(), "ElectricCar$Proxy");
    }
}
