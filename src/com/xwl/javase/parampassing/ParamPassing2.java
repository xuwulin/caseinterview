package com.xwl.javase.parampassing;

/**
 * @author xwl
 * @date 2019-09-02 17:40
 * @description
 */
public class ParamPassing2 {
    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("xxx");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    /**
     * 栈管运行，堆管存储
     * @param args
     */
    public static void main(String[] args) {
        ParamPassing2 paramPassing2 = new ParamPassing2();
        int age = 20; // 基本类型，数据值
        paramPassing2.changeValue1(age); // age是基本类型，传递的是一个拷贝的副本
        System.out.println("age----" + age); // 这是main方法中的age, age----20

        Person person = new Person("abc");
        paramPassing2.changeValue2(person); // 指正指向同一个引用，一个改变，另一个也要改变
        System.out.println("personName----" + person.getPersonName()); // personName----xxx

        // String str = new String("abc");存在于堆
        String str = "abc"; // 存在于常量池
        paramPassing2.changeValue3(str);
        System.out.println("String----" + str); // String----abc
    }

}

class Person {
    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}