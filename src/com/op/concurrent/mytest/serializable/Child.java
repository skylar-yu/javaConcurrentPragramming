package com.op.concurrent.mytest.serializable;

import java.beans.Transient;
import java.io.*;

/**
 * Created by Administrator on 2017/12/17.
 */
public class Child extends Paret {

    private static final long serialVersionUID = 5885040935957542842L;
    private transient Integer bb;
    private String ee = "hhhaha";
    public Child(){
        super("");
    }
    public Child(String aa) {
        super(aa);
    }

    public Integer getBb() {
        return bb;
    }

    public void setBb(Integer bb) {
        this.bb = bb;
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File("/qwer")));
        Child ch =  new Child();
        ch.setBb(33);
        ch.setAa("yyyyy");
        o.writeObject(ch);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("/qwer")));
        Child o1 = (Child) objectInputStream.readObject();
        System.out.println(o1.getAa());
        System.out.println(o1.getBb());
        System.out.println(o1.getEe());
    }
}
