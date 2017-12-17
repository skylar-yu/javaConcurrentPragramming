package com.op.concurrent.mytest.serializable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/17.
 */
public class Paret  implements Serializable{
    private String aa="fasdfae";

    public Paret(String aa){
        this.aa = aa;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }
}
