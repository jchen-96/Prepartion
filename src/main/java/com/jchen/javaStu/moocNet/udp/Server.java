package com.jchen.javaStu.moocNet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by jchen on 17-9-1.
 */
public class Server {
    public static void main(String[] args) throws SocketException,IOException {


        /**
         * 接受数据
         */

//    1　创建datagramSocket指定端口号
        DatagramSocket socket = new DatagramSocket(8800);

//    ２　创建数据包，用于接受客户端发送的数据
        byte[] data = new byte[1024];//创建字节数组，接受制定的数据包大小
        DatagramPacket packet = new DatagramPacket(data, data.length);
//    ３　接受客户端发送的数据
        System.out.println("服务器端已经启动");
        socket.receive(packet);//此方法在接收到数据包之前会一直阻塞

//      ４  读取数据
        String info=new String(data,0 ,packet.getLength());

        System.out.println("我是服务器，客户端说"+info);

        /**
         * 进行相应
         */
//        定义客户端的地址，端口
        InetAddress address=packet.getAddress();
        int port=packet.getPort();

        byte[] data2="欢迎你".getBytes();

        DatagramPacket packet1=new DatagramPacket(data2,data2.length,address,port);

        socket.send(packet1);

        socket.close();



    }

}
