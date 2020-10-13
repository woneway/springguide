package com.woneway.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 4:17 下午
 * @description
 */
public class Person {
    /**
     * 使用@Value赋值
     * 1.基本数值
     * 2.可以写SpEL：#{}
     * 3.可以写${}:取配置文件中的值
     */
    @Value("张三")
    private String name;

    @Value("#{40-2}")
    private int age;

    @Value("${person.nickname}")
    private String nickName;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
