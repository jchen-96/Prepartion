package com.jchen.javaStu.moocIO.Serial;

import java.io.*;

/**
 * Created by jchen on 17-8-28.
 */
public class ObjectSerialDemon {
    public static void main(String[] args)  throws IOException,ClassNotFoundException{
        String file="student.dat";

        //对象的序列化
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
        Student student=new Student("1","jchen",19);
        oos.writeObject(student);
        oos.flush();
        oos.close();

        //对象的反序列化
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
        Student s=(Student)ois.readObject();
        System.out.println(s);
    }
}
