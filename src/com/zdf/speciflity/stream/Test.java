package com.zdf.speciflity.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: zhoudafu
 * @Date: 2020/7/11 13:05
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        List<Person> persionList = new ArrayList<Person>();
        persionList.add(new Person(1,"张三","男",38));
        persionList.add(new Person(2,"钱小","男",12));
        persionList.add(new Person(3,"李四","女",65));
        persionList.add(new Person(4,"王五","女",22));
        persionList.add(new Person(5,"赵六","男",38));
        persionList.add(new Person(6,"孙八","男",17));

        List<String> list = persionList.stream() //为集合创建串行流
                .map(Person::getName) //map 映射每个元素到对应的结果
                .collect(Collectors.toList());//聚合操作，封装目标数据。
        System.out.println(list);
        //也可用上面的方法
        List<Integer> collect = persionList.stream()
                .mapToInt(Person::getId)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(collect);
        //list 转map
        Map<Integer, String> collect1 = persionList.stream()
                .collect(Collectors.toMap(Person::getId, Person::getSex));
        System.out.println(collect1);
        //分组
        Map<Integer, List<Person>> collect2 = persionList.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(collect2);
        //获取最小
        int asInt = persionList.stream()
                .mapToInt(Person::getAge)
                .min()
                .getAsInt();
        System.out.println(asInt);
        //求和
        int sum = persionList.stream()
                .mapToInt(Person::getAge)
                .sum();
        System.out.println(sum);

        //过滤， 大于20岁 的人数
        long count = persionList.stream()
                .filter((person) -> person.getAge() > 20)
                .count();
        System.out.println(count);
        //过滤  男的    按照年龄排序
        List<Person> collect3 = persionList.stream()
                .filter(person -> "男".equals(person.getSex()))
//                .sorted(Comparator.comparingInt(Person::getAge))//默认升序
                .sorted((person1, person2)->person2.getAge() - person1.getAge())//排序
                .collect(Collectors.toList());
        System.out.println(collect3);
        //按照年龄最小年龄
//        persionList.sort(Comparator.comparing(Person::getAge));
        persionList.sort((x, y) -> x.getAge().compareTo(y.getAge()));
        System.out.println(persionList.get(0));

    }

}
