package com.jchen.javaStu.moocIO.summary;

import java.io.*;
import java.util.Arrays;

/**
 * Created by jchen on 17-8-30.
 */
public class Summary {
    private final static String curDir = "/home/jchen/project/jproject/prepartion/Prepartion/src/main/java/com/jchen/javaStu/moocIO/summary/";

    public static void main(String[] args) throws IOException {
//        encodeDemon();
//        filebase();
//        listAll(new File(curDir));
//        rafDemon();
//        printHex(curDir+"日记.txt");
//        printHexByArray(curDir+"日记.txt");
//        copyFileByArray(new File(curDir + "日记.txt"), new File(curDir + "copy日记.txt"));
        /**
         * 三种复制方式的性能测试
         */
//        long start=System.currentTimeMillis();
//        copyFileWithBufferArray(new File(curDir + "we.mp3"), new File(curDir + "we1.mp3"));
//        System.out.println("带缓冲区的字节数组的复制方式:"+(System.currentTimeMillis()-start)/1000.0);
//
//        start=System.currentTimeMillis();
//        copyFileByArray(new File(curDir + "we.mp3"), new File(curDir + "we2.mp3"));
//        System.out.println("通过字节数组复制:"+(System.currentTimeMillis()-start)/1000.0);
//
//        start=System.currentTimeMillis();
//        copyFileWithBuffer(new File(curDir + "we.mp3"), new File(curDir + "we3.mp3"));
//        System.out.println("只借助缓冲区进行复制:"+(System.currentTimeMillis()-start)/1000.0);

        charDemon();










    }


    //编码相关
    public static void encodeDemon() throws IOException {
        String s = "慕课ABC";
        byte[] bytes = s.getBytes("utf8");//获取指定编码的字节数组，不加utf-8会采用项目默认编码,Idea采用utf-8,
        //一个字节怎样编码转换成字节数组，将字节数组转换成字符串时候也要采用同样的编码方式
        String s1 = new String(bytes, "utf8");
        System.out.println(s1);
    }

    //File类的基本使用
    public static void filebase() throws IOException {
        File file = new File(curDir);

        if (!file.exists()) {
            file.mkdir();//创建文件夹
        } else {
//            file.delete();//删除文件夹操作
        }
        System.out.println(file.isFile());//判断是不是文件
        System.out.println(file.isDirectory());//判断是不是文件夹


        File file2 = new File(curDir, "日记.txt");
        if (!file2.exists()) {
            file2.createNewFile();//创建文件
        }

        System.out.println(file2);//打印文件的路径信息
        ///home/jchen/project/jproject/prepartion/Prepartion/src/main/java/com/jchen/javaStu/moocIO/summary/日记.txt

        System.out.println(file2.getAbsolutePath());//答应文件的绝对路径
        ///home/jchen/project/jproject/prepartion/Prepartion/src/main/java/com/jchen/javaStu/moocIO/summary/日记.txt


        System.out.println(file2.getName());//打印文件名称
        //日记.txt

        System.out.println(file2.getParent());//打印上层目录名称
        ///home/jchen/project/jproject/prepartion/Prepartion/src/main/java/com/jchen/javaStu/moocIO/summary


        System.out.println(file2.getParentFile().getName());//上层文件夹名字
        //summary

        System.out.println(file2.exists());


    }

    //File类的高级技巧：便利一个文件夹下的所有文件夹
    public static void listAll(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException("目录" + file + "不存在");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + "不是一个文件夹子");
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    listAll(f);
                } else if (f.isFile()) {
                    System.out.println(f);
                    ;
                }
            }
        }
    }

    //RandomAccessFile基本使用:用于文件的随机读写
    public static void rafDemon() throws IOException {
        File file = new File(curDir, "raf.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");//可以设置为读写或者只读

        System.out.println(raf.getFilePointer());//随机读写的指针

        raf.write('A');//写入A的后八位,只写入一个字节
        System.out.println(raf.getFilePointer());
        raf.write('B');
        System.out.println(raf.getFilePointer());

        int i = 0x7fffffff;//将这个整数写进去

//        用write 方法每次只写入一个字节

        raf.write(i >> 24);
        raf.write(i >> 16);
        raf.write(i >> 8);
        raf.write(i);

        System.out.println(raf.getFilePointer());

        //可以直接写一个int:本质是对于上一个　过程的简单封装

        raf.writeInt(i);

        //写一个中文字符
        String s = "中";

        byte[] bytes = s.getBytes("utf8");
        raf.write(bytes);


        System.out.println(raf.length());

        //读文件要将指针移到到文件开始位置
        raf.seek(0);
        byte[] buff = new byte[(int) raf.length()];
        raf.read(buff);

        System.out.println(Arrays.toString(buff));

        byte[] ch = new byte[3];

        //单独将中文打印出来
        raf.seek(10);
        raf.read(ch, 0, ch.length);
        String s2 = new String(ch, "utf8");
        System.out.println(s2);


        String s1 = new String(buff);
        for (byte b : buff) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        raf.close();


    }


    /**
     * 字节流相关
     */
    //读取一个文件将其按照十六进制的方式打印到控制台
    public static void printHex(String fileName) throws IOException {
        FileInputStream fin = new FileInputStream(fileName);
        int b;
        int i = 1;
        while ((b = fin.read()) != -1) {
            System.out.print(Integer.toHexString(b) + " ");
            i++;
            if (i % 10 == 0) {
                System.out.println();
            }
        }
    }

    //采用数组的方式读取文件，按照十六进制的方式打印
    public static void printHexByArray(String fileName) throws IOException {
        FileInputStream fin = new FileInputStream(fileName);

        byte[] bytes = new byte[8 * 1024];//批量读取，一次读取8KB

        int j = 0;
        int len;
        while ((len = fin.read(bytes, 0, bytes.length)) != -1) {//这样read一次最多可以读取一个数组，返回值i为读进去的字节数
            for (int i = 0; i < len; i++) {
                System.out.print(Integer.toHexString(bytes[i] & 0xff) + " ");
                if (++j % 10 == 0) {
                    System.out.println();
                }
            }
        }

    }

    //通过字节数组复制文件
    public static void copyFileByArray(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException(srcFile.getName() + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException("源文件" + srcFile.getName() + "不是一个文件");
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileInputStream fin = new FileInputStream(srcFile);
        FileOutputStream fout = new FileOutputStream(destFile);

        byte[] buffer = new byte[5 * 1024];
        int b;
        while ((b = fin.read(buffer, 0, buffer.length)) != -1) {
            fout.write(buffer, 0, b);
        }

        fin.close();
        fout.close();
    }


    //带缓冲区的文件拷贝
    public static void copyFileWithBuffer(File src, File dest) throws IOException {
        if (!src.exists()) {
            throw new IllegalArgumentException(src.getName() + "不存在");
        }
        if (!src.isFile()) {
            throw new IllegalArgumentException("源文件" + src.getName() + "不是一个文件");
        }
        if (!dest.exists()) {
            dest.createNewFile();
        }
        BufferedInputStream bfin = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bfout = new BufferedOutputStream(new FileOutputStream(dest));

        int i;
        while ((i = bfin.read()) != -1) {
            bfout.write(i);
            bfout.flush();
        }
        bfin.close();
        bfout.close();
    }

    //带缓冲区的字节数组的问价拷贝
    public static void copyFileWithBufferArray(File src,File dest) throws IOException{
        if (!src.exists()) {
            throw new IllegalArgumentException(src.getName() + "不存在");
        }
        if (!src.isFile()) {
            throw new IllegalArgumentException("源文件" + src.getName() + "不是一个文件");
        }
        if (!dest.exists()) {
            dest.createNewFile();
        }

        BufferedInputStream bfin=new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bfout=new BufferedOutputStream(new FileOutputStream(dest));

        byte[] buffer=new byte[5*1024];

        int i;
        while ((i=bfin.read(buffer,0,buffer.length))!=-1){
            bfout.write(buffer,0,i);
            bfout.flush();
        }
        bfin.close();
        bfout.close();
    }


    //装饰器模式的体现:对基本的文件流进行封装，实现更高阶的操作
    public static void dataStream() throws IOException{
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("file"));
        dos.writeInt(10);
        dos.writeDouble(10.5);
        dos.writeUTF("中国");
        dos.writeChars("中国");//utf-16be

        DataInputStream dis=new DataInputStream(new FileInputStream("file"));

        int i=dis.readInt();
        System.out.println(i);
        double j=dis.readDouble();
        System.out.println(j);

        String s=dis.readUTF();
        System.out.println(s);

        System.out.println(dis.readChar());


        dos.close();
    }

    /**
     * 字符流
     */

    //字符流的基本操作
    public static void charDemon()throws IOException{
        FileInputStream in=new FileInputStream(curDir+"日记.txt");
        InputStreamReader reader = new InputStreamReader(in,"utf8");//默认项目编码,要注意文件本身的格式编码
        OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(curDir+"日记2.txt"),"utf8");


        int c;
//        while ((c=reader.read())!=-1){
//            System.out.print((char)c);
//        }
        System.out.println();
        //这个两个同同时打印的话，由于是同一个流，所以只会打印一次，因为上面已经将reader读完了
        char[] buffer=new char[8*1024];
        while ((c=reader.read(buffer,0,buffer.length))!=-1){
            String s=new String(buffer,0,c);
            System.out.print(s);
            writer.write(buffer,0,c);
        }
        writer.flush();
        writer.close();
        reader.close();
    }
    //带缓冲区的字符流
    public static void charBuffer()throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("file")));

        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("file4")));

        String line;
        while ((line=reader.readLine())!=null){
            System.out.print(line);//不能识别换行符
            writer.write(line);
            //单独写出换行操作
            writer.newLine();
            writer.flush();
        }
    }

    //fileReader,FileWrite
    public static void readerWriter()throws IOException{
        FileReader reader=new FileReader("file");
        FileWriter writer=new FileWriter("file3",true);//设置为true追加内容
        char[] buffer=new char[2056];
        int c;
        while ((c=reader.read(buffer,0,buffer.length))!=-1){
            writer.write(buffer,0,c);
            writer.flush();
        }
        reader.close();
        writer.close();
    }



}
