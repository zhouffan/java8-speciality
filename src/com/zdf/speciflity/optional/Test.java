package com.zdf.speciflity.optional;

import java.util.Optional;

/**
 * @Author: zhoudafu
 * @Date: 2020/7/11 13:52
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Person person = new Person();
        Person.Subject subject = new Person.Subject();
        subject.setName("xxxx");
        person.setSubject(subject);

//        getSubName(person);
//        getSubName2(person);

        //============================================
        //============================================
        //============================================
        Optional<Object> empty = Optional.empty();//一定是空的
        Optional<Object> optional1 = Optional.of(person);//不可以为null，否则NullPointerException
        Optional<Object> optional2 = Optional.ofNullable(person);//可以null

        optional1.ifPresent(System.out::println);//如果存在，则执行打印
        System.out.println(empty.orElse("如果是空容器, 返回这个...")); //没值则返回当前值
        System.out.println(empty.orElseGet(()-> "返回自定义")); //没值则返回 自定义
        // map
        Optional<String> num = Optional.of("10");
        Integer integer = num
//                .map((value) -> Integer.parseInt(value)) //转化为int
                .map(Integer::parseInt) //转化为int
                .get();
        System.out.println(integer);
        //flatMap与map（Funtion）非常相似，不同在于 map返回可以将String对象转为Integer对象
        //但flatMap转换后一定还是String对象
        String s = num.flatMap((value) -> Optional.of(value.toUpperCase()))
                .get();
        System.out.println(s);
        Optional<String> str = Optional.of("生生世世");
        // filter
        String name = str.filter((value) -> value.length() > 10)
                .orElse("bbbbb");
        System.out.println(name);

    }

    public static void getSubName(Person person){
        if(person != null){
            Person.Subject subject = person.getSubject();
            if (subject != null) {
                String name = subject.getName();
                if(name != null){
                    System.out.println(name);
                }
            }
        }
    }

    public static void getSubName1(Person person){
        if(person == null){
            return;
        }
        Person.Subject subject = person.getSubject();
        if (subject == null) {
            return;
        }
        String name = subject.getName();
        if(name != null){
            System.out.println(name);
        }
    }

    public static void getSubName2(Person person){
        Optional<String> s = Optional.ofNullable(person)
                .map(Person::getSubject)
                .map(Person.Subject::getName);
        //如果存在，则执行 打印 action
        s.ifPresent(System.out::println);
        //等价
        if(s.isPresent()){
            System.out.println(s.get());
        }
    }
}
