package com.zdf.speciflity.functionalInterface;
public class Person {
    
    private Integer  id;
    
    private String name;
    
    private String sex;
    
    private Integer age;
    
    //提供get，set，和满参构造函数

    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Person(Integer id, String name, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * public native int hashCode();
     * public boolean equals(Object obj) {
     *         return (this == obj);
     * }
     * 从代码中我们可以知道，hashCode()方法是一个本地native方法，
     * 返回的是对象引用中存储的对象的内存地址，
     * 而equals方法是利用==来比较的也是对象的内存地址。
     * 从上边我们可以看出，hashCode方法和equals方法是一致的。
     * 还有最关键的一点，我们来看Object类中关于hashCode()方法的注释：
     *
     * hashCode()和equals()保持一致，如果equals方法返回true，那么两个对象的hasCode()返回值必须一样。如果equals方法返回false，hashcode可以不一样
     *
     * 重写equals()方法就尽量重写hashCode()方法
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person){
            Person person = (Person) obj;
            //这里使用了equqls判断，则需要重写hashcode
            return person.name.equals(this.getName());
//            return person.name==this.getName(); //其他判断规则
        }
        return false;
    }
}