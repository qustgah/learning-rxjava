package com.jni;

/**
 * Created by Administrator on 2017/5/6.
 */
public class HelloJNI {
    private int number = 8;
    private String message = "from java";

    private String name;

    private HelloJNI(String name){
        this.name = name;
    }

    static {
        System.loadLibrary("lib/HelloJNI");
    }

    public static native HelloJNI getInstance();

    private native void modifyField();

    public static void main(String[] args){
        HelloJNI obj = HelloJNI.getInstance();
        System.out.println(obj.name);
    }
}
