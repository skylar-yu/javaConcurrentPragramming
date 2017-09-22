package com.op.concurrent.test.cache;

/**
 * Created by Administrator on 2017/9/22.
 */
public interface Computable <K,V> {
    V compute(K k);
}
