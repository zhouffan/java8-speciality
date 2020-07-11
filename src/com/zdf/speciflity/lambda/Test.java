package com.zdf.speciflity.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: zhoudafu
 * @Date: 2020/7/11 10:13
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        // 原始类型
        MathOperation operation1 = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a+b;
            }
        };
        // 类型声明
        MathOperation operation2 = (a, b)-> a+b;
        // 不申明类型声明
        MathOperation operation3 = (int a, int b)-> a+b;
        //括号/ 返回
        MathOperation operation4 = (int a, int b)-> {return a+b;};

        System.out.println(operation1.operation(25, 10));
        System.out.println(operation2.operation(25, 10));
        System.out.println(operation3.operation(25, 10));
        System.out.println(operation4.operation(25, 10));

        //=========================================================
        //============jdk封装的类===================================
        //=========================================================
        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxxx");
            }
        }).start();
        //lambda
        new Thread(()->System.out.println("xxxx2")).start();

        String[] data = {"ats1", "zts12", "bts132", "gts1",  "es1"};
        Arrays.sort(data, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        //lambda
        Arrays.sort(data, (o1, o2) -> o1.compareTo(o2));
        //类引用
        Arrays.sort(data, String::compareTo);
        System.out.println(data);
        //=========================================================
        //=======================方法引用===========================
        //=========================================================
        List<String> list = Arrays.asList("ats1", "zts12", "bts132", "gts1", "es1");
        list.forEach(System.out::println); //instance::method
        list.forEach((str)-> System.out.println(str));
        //构造器引用：Class::new
//        Car car = Car.create(Car::new);
//        Car car1 = Car.create(Car::new);
        Car car = new Car("name1");
        Car car1 = new Car("name2");
        List<Car> cars = Arrays.asList(car, car1);
        //静态方法引用：Class::static_method  注：遍历cars返回的car，调用car的对应方法， 有方法参数
        cars.forEach(Car::collide);
        //特定类的任意对象的方法引用：Class::method   注：非静态  无方法参数
        cars.forEach(Car::repair);
        //特定对象的方法引用：instance::method   注：使用car 或者car1都是一样的. 有方法参数
        cars.forEach( car1::follow );
    }



    interface MathOperation {
        int operation(int a, int b);
    }


    static class Car {
        private String name;
        //Supplier是jdk1.8的接口，这里和lamda一起使用了
//        public static Car create(final Supplier<Car> supplier) {
//            return supplier.get();
//        }

        public Car(){}
        public Car(String name) {
            this.name = name;
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }


        public void repair() {
            System.out.println("Repaired " + this.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
