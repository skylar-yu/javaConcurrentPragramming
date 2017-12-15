package com.op.concurrent.mytest.proxy;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class ElectricCar$Proxy extends Proxy implements Rechargable, Vehicle {
    private static Method m1;
    private static Method m3;
    private static Method m4;
    private static Method m0;
    private static Method m2;

    public ElectricCar$Proxy(InvocationHandler var1) throws UndeclaredThrowableException {
        super(var1);
    }

    public final boolean equals(Object var1) throws UndeclaredThrowableException {
        try {
            return ((Boolean) super.h.invoke(this, m1, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void recharge() throws UndeclaredThrowableException {
        try {
            super.h.invoke(this, m3, (Object[]) null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void drive() throws UndeclaredThrowableException {
        try {
            super.h.invoke(this, m4, (Object[]) null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws UndeclaredThrowableException {
        try {
            return ((Integer) super.h.invoke(this, m0, (Object[]) null)).intValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() throws UndeclaredThrowableException {
        try {
            return (String) super.h.invoke(this, m2, (Object[]) null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m3 = Class.forName("com.op.concurrent.mytest.proxy.Rechargable").getMethod("recharge", new Class[0]);
            m4 = Class.forName("com.op.concurrent.mytest.proxy.Vehicle").getMethod("drive", new Class[0]);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
