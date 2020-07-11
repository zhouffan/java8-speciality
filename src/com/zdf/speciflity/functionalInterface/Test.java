package com.zdf.speciflity.functionalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author: zhoudafu
 * @Date: 2020/7/11 15:25
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        List<Person> persionList = new ArrayList<>();
        persionList.add(new Person(1,"赵六","男",38));
        persionList.add(new Person(2,"赵六","男",12));
        persionList.add(new Person(3,"孙八","女",65));
        testInterface();
//        testConsumer(persionList);
//        testSupplier();
//        testFunction(persionList);
//        testPredicate(persionList);
    }

    public static void testInterface() {
        PersonInterface personInterface = ()-> System.out.println("xxxx");
        personInterface.test();//输出 'xxxx'
        //函数式接口 + Lambda表达式
        //()->{} 就是代表对say()方法的重写
    }

    /**
     * 消费型： 传入一个指定泛型的参数，无返回值
     */
    public static void testConsumer(List<Person> persionList) {
        Consumer<Integer> squareConsumer = x-> System.out.println(""+x+x);
        Consumer<Integer> consumer2 = x-> System.out.println(""+x*x);
//        squareConsumer.accept(2);
        squareConsumer.andThen(consumer2).accept(2);

        //public interface Iterable<T> {
        //    //forEach方法传入的就是Consumer
        //    default void forEach(Consumer<? super T> action) {
        //        Objects.requireNonNull(action);
        //        for (T t : this) {
        //            action.accept(t);
        //        }
        //    }
        //}

        //传统遍历
//        for (Person person : persionList) {
//            person.setName("xxx");
//        }
        //通过forEach的Consumer添加
        persionList.forEach((x)-> x.setName("xxx2"));

        System.out.println(persionList);
    }

    /**
     * 供给型： 无参数，返回泛型对象
     */
    public static void testSupplier() {
        Person son = null;
//        public T orElseGet(Supplier<? extends T> supplier) {
//            return value != null ? value : supplier.get();
//        }
        //先判断son是否为null,如果为不为null则返回当前对象,如果为null则返回新创建的对象
//        Person person = Optional.ofNullable(son).orElseGet(Person::new);
        Person person = Optional.ofNullable(son).orElseGet(() -> new Person(10));
        System.out.println(person);
    }

    /**
     * 方法型： 输入一个参数，返回一个结果
     */
    public static void testFunction(List<Person> persionList) {
        //compose 组合函数，调用当前function之前调用
        //示例：使用使用compose()、andThen()实现一个函数 y=(10+x)*2+10;
        //执行第二步
        Function<Integer, Integer> function4 = x -> x * 2;
        //执行第一步
        function4 = function4.compose(x -> x + 10);
        //执行第三步
        function4 = function4.andThen(x -> x + 10);
        System.out.println(function4.apply(3));
        //结果：36

        List<String> collect = persionList.stream()
                //取出该集合中所有姓名组成一个新集合（将Person对象转为String对象）
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    /**
     * 断言型： 判断函数， 返回 true/false
     */
    public static void testPredicate(List<Person> persionList) {
        Predicate<Integer> predicate = x -> x > 7;
        System.out.println(predicate.test(2));
        System.out.println(predicate.test(12));

        List<Person> collect = persionList.stream()
                .distinct() //去重   重新equal 和 hashcode
                .collect(Collectors.toList());
        System.out.println(collect);

        Person person = new Person(62,"孙八","男v",27);
        List<Person> collect1 = persionList.stream()
                .filter(Predicate.isEqual(person)) //根据重写的equal 和 hashcode判断
                .collect(Collectors.toList());
        System.out.println(collect1);
    }
}
