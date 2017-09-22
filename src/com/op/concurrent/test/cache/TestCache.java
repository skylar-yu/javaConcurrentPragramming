package com.op.concurrent.test.cache;

/**
 * Created by Administrator on 2017/9/22.
 */
public class TestCache {
    public static void main(String[] args) {

        Computable<Integer,String> computable = new ResolvePrime();
        Memoizer<Integer, String> memoizer = new Memoizer<>(computable);
        String result = memoizer.compute(12);
        System.out.println("result is:"+result);
        String result2 = memoizer.compute(13);
        System.out.println("result2 is:"+result2);
        String result3 = memoizer.compute(12);
        System.out.println("result3 is:"+result3);
    }
}
