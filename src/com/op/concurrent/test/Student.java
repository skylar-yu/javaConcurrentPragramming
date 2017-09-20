package com.op.concurrent.test;

public class Student {
    private  int age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public Student(){
        age = 3;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
