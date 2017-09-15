package com.op.concurrent.test;

public class FinalReferenceEscapeExample {
    final int i;
    final int[] arr;
    FinalReferenceEscapeExample finalReferenceEscapeExample = null;
    static FinalReferenceEscapeExample obj;

    {
        arr = new int[2];
        finalReferenceEscapeExample = this;
    }

    public FinalReferenceEscapeExample () {
//        arr = new int[2];
//        arr[0]=1;
        i = 1;                              //1写final域
        obj = this;                          //2 this引用在此“逸出”
    }

    public static void writer() {
        new FinalReferenceEscapeExample ();
    }

    public static void reader() {
        if (obj != null) {                     //3
            int temp = obj.i;                 //4
        }
    }
}