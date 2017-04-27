package com.java8.newfeature;

import java.util.function.Function;

/**
 * Function<T, R>：将 T 作为输入，返回 R 作为输出，他还包含了和其他函数组合的默认方法。
 * Supplier<T>
 * Predicate<T> ：将 T 作为输入，返回一个布尔值作为输出，该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（与、或、非）。
 * Consumer<T> ：将 T 作为输入，不返回任何内容，表示在单个参数上的操作。
 *
 * Created by Administrator on 2016/12/8.
 */
public class Lambda {


    void testFunctionInterface(FunctionInterface functionalInterface){
        functionalInterface.test();
    }

    void run(){
        testFunctionInterface(()->System.out.println("test"));
        testFunctionInterface(() -> System.out.println("dddd"));

        Function<Integer,String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return null;
            }
        };

        Function<Integer,String> function1 = (integer)-> String.valueOf(integer);
        Function<Integer,String> function2 = String::valueOf;
        System.out.println("1"+function1.apply(1));
        System.out.println("2"+function2.apply(2));

    }










    public static void main(String[] args) {
        new Lambda().run();
    }









}
