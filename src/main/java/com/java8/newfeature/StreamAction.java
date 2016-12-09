package com.java8.newfeature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Stream
 * Java 8 引入了流式操作（Stream），通过该操作可以实现对集合（Collection）的并行处理和函数式操作。根据操作返回的结果不同，流式操作分为中间操作和最终操作两种。
 * 最终操作返回一特定类型的结果，而中间操作返回流本身，这样就可以将多个操作依次串联起来。根据流的并发性，流又可以分为串行和并行两种。流式操作实现了集合的过滤、排序、映射等功能。
 *
 * Stream 和 Collection 集合的区别：Collection 是一种静态的内存数据结构，而 Stream 是有关计算的。中前者是主要面向内存，存储在内存，后者主要是面向 CPU，通过 CPU 实现计算。
 *
 * Created by Administrator on 2016/12/9.
 */
public class StreamAction {
//集合之流式操作

	/*串行*/
    void sequential(ArrayList list){

        long start = System.nanoTime();//获取系统开始排序的时间点
        int count= (int) ((Stream) list.stream().sequential()).sorted().count();
        long end = System.nanoTime();//获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到串行排序所用的时间
        System.out.print(ms+"ms");
    }

    void parareel(ArrayList list){
        long start = System.nanoTime();//获取系统开始排序的时间点
        int count = (int)((Stream) list.stream().parallel()).sorted().count();
        long end = System.nanoTime();//获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到并行排序所用的时间
        System.out.println(ms+"ms");
    }

    void otherAction(List list){
        list.stream().filter((s) -> ((String)s).startsWith("100")).forEach(System.out::println);
//                .forEach((s)-> System.out.println(s));
    }

    void run(){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<1000000;i++){
            double d = Math.random()*1000;
            list.add(d+"");
        }
        sequential((ArrayList) list);
        parareel((ArrayList) list);
        otherAction(list);
    }


    public static void main(String[] args) {
        new StreamAction().run();
    }
}
