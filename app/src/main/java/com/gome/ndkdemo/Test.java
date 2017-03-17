package com.gome.ndkdemo;

/**
 * Created by chenhang01 on 2017/3/15.
 */

public class Test {
    static {
        System.loadLibrary("test");
    }
    public native void test(String oldApk,String newApk,String patch);
}
