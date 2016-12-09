package com.java8.newfeature;

/**
 * 函数式接口 ：
 *           如果一个接口定义个唯一一个抽象方法，那么这个接口就成为函数式接口。
 *           同时，引入了一个新的注解：@FunctionalInterface。可以把他它放在一个接口前，表示这个接口是一个函数式接口
 * Created by Administrator on 2016/12/9.
 */
@FunctionalInterface
public interface FunctionInterface {
    public void test();

    static String test2(Object object){
        return null;
    }

}
