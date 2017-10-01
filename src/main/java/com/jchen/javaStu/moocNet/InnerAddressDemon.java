package com.jchen.javaStu.moocNet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by jchen on 17-8-30
 * inetAddress类用于表示网络的硬件资源.
 */
public class InnerAddressDemon {
    /**
     * 没有构造方法
     *通过提供的静态方法，获取相关信息
     */
    public static void main(String[] args) throws UnknownHostException{
//        获取本机额实例
        InetAddress address=InetAddress.getLocalHost();
        System.out.println("计算接名称"+address.getHostName());
        System.out.println("ip地址:"+address.getHostAddress());
        byte[] bytes=address.getAddress();//获取字节数组形式的ip地址
        System.out.println("字节数组形式的ip地址:"+ Arrays.toString(bytes));
        System.out.println(address);//直接输出对象


//        获取其他主机的实例
        InetAddress address1=InetAddress.getByName("Jchen");
        System.out.println(address1);


//        根据ip地址获取实例
        InetAddress address2=InetAddress.getByName("127.0.1.1");
        System.out.println(address2);
        System.out.println(address2.getHostName());
    }
}
