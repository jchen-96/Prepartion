package com.jchen.javaStu.moocNet;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jchen on 17-8-30.
 */
public class Server {
    /**
     * 实现基于tcp的socket通信，实现用户登录
     */
    public static void main(String[] args) {

        try {
            int count=0;
            //创建一个服务器端的socket制定绑定的端口，并监听端口
            ServerSocket serverSocket=new ServerSocket(8888);
            //调用accept 方法进行监听，等待客户端的连接
            System.out.println("服务器即将启动，等待客户单的连接");

            while (true){
                //循环监听
                count++;
                Socket socket=serverSocket.accept();

                ServerThread serverThread=new ServerThread(socket);
                serverThread.start();
                System.out.println(count);
                InetAddress inetAddress=socket.getInetAddress();
                System.out.println("当前客户端的ip地址:"+inetAddress.getHostAddress());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
