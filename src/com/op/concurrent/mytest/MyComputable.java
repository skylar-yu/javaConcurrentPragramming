package com.op.concurrent.mytest;

/**
 * Created by Administrator on 2017/9/27.
 */
public interface MyComputable <K,V> {
     V compute(K k);
}
