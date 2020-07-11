package com.zdf.speciflity.functionalInterface;

/**
 * @Author: 进击的烧年
 * @Date: 2020/7/11 15:26
 * @Description:    函数式接口主要是为Lambda语法服务, 语言简洁，所以只有一个抽象方法
 */
@FunctionalInterface
public interface PersonInterface {
    //1、仅有一个抽象方法,  为Lambda语法服务
    void test();
    //4、静态方法
    @Override
    boolean equals(Object var1);
    //3、java8 接口才可以有默认的方法实现 前提是方法名称必须使用default关键字修饰
    default void defaultMethod() {
        System.out.println("haha");
    }
    //4、静态方法
    static void staticMethod() {
    }
}
