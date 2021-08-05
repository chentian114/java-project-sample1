//package com.chen.sample1.web;
//
//import cn.hutool.json.JSONUtil;
//import io.vavr.collection.List;
//import io.vavr.control.Try;
//import org.junit.jupiter.api.Test;
//
//import static io.vavr.API.*;
//import static io.vavr.Predicates.instanceOf;
//
///**
// * @desc vavr 实践
// * @Author Chentian
// * @date 2021/7/20
// * Vavr(以前称为Javaslang)是一个Java 8+的函数库，它提供了持久的数据类型和函数控制结构。
// */
//public class VavrTest {
//
//    /**
//     * 处理 Java 应用程序 副作用
//     */
//    @Test
//    public void testTry(){
//        System.out.println("hello world!");
//        Try<Integer> divide = divide(4, 2);
//        System.out.println(divide);
//        System.out.println(JSONUtil.toJsonStr(divide));
//        divide.onSuccess(System.out::println);
//
//        divide = divide(4, 0);
//        System.out.println(divide);
////        divide.getCause().printStackTrace();
//        divide.onSuccess(System.out::println);
//
//
//    }
//
//    private Try<Integer> divide(Integer a, Integer b){
//        return Try.of(() -> a/b);
//    }
//
//    /**
//     * 引用透明性
//     *  如果一个调用可以被它的值替换而不影响程序的行为，那么一个函数，或者更一般的表达式，就被称为引用透明的。
//     *
//     * 更好的Java的关键是使用不可变值与引用透明函数相匹配。
//     * Vavr提供了必要的控件和集合来实现日常Java编程中的这一目标。
//     *
//     * Vavr的集合库由构建在lambdas之上的一组功能丰富的数据结构组成。它们与Java原始集合共享的唯一接口是Iterable。
//     */
//
//    @Test
//    public void testList(){
//        List<Integer> list = List.of(1,2,3);
//        for (Integer obj : list){
//            System.out.print(obj);
//        }
//        System.out.println();
//
//        List<Integer> list2 = list.tail().prepend(0);
//        list2.forEach(System.out::print);
//        System.out.println();
//        java.util.List<Integer> integers = list.asJava();
//
//    }
//
//
//    @Test
//    public void testMatch(){
//        int val = 1;
//        Integer of = Match(val).of(
//                Case($(instanceOf(Integer.class)), r -> {
//                    System.out.println(r);
//                    return r+1;
//                })
//
//        );
//        System.out.println(of);
//
//
//    }
//
//
//
//
//}
