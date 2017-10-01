package com.jchen.javaStu.moocIO.Serial;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jchen on 17-8-28.
 */
public class Student implements Serializable {
    private String number;
    private transient String name;//用了transient就不会被序列化
    private int age;


    public Student(String number, String name, int age) {
        this.number = number;
        this.name = name;
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        s.defaultWriteObject();//将jvm能默认序列化的元素进行序列化
        s.writeObject(name);
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {

        s.defaultReadObject();//默认的反序列化
        this.name=(String) s.readObject();//额外的序列化
    }
}
