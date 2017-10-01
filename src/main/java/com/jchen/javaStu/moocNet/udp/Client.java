package com.jchen.javaStu.moocNet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jchen on 17-9-1.
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException,IOException{

        /**
         * 发送数据
         */
//        指定服务器的相关信息
        InetAddress address=InetAddress.getByName("localhost");
        int port=8800;

        byte[] data="用户 admin,密码123".getBytes();

//        创建数据报，包含要发送的信息
        DatagramPacket packet=new DatagramPacket(data,data.length,address,port);

//        创建socket对象
        DatagramSocket socket=new DatagramSocket();
        socket.send(packet);

        /**
         * 接受数据
         */

//        床架数据包，用于接受服务器端响应的数据
        byte[] data2=new byte[1024];

        DatagramPacket packet1=new DatagramPacket(data2,data2.length);

//        接受服务器响应的数据
        socket.receive(packet1);

//        读取响应信息
        String reponse=new String(data2);
        System.out.println("response"+reponse);
        socket.close();


    }
}
