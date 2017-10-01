package com.jchen.javaStu.moocNet;

import java.io.*;
import java.net.Socket;

/**
 * Created by jchen on 17-8-30.
 * 线程处理类
 */
public class ServerThread extends Thread {
    //和本线程相关的socket

    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    //执行线程操作相应客户端的额请求


    @Override
    public void run() {
        //获取一个输入流，用来读取客户端信息
        InputStream in;
        BufferedReader reader;

        OutputStream os;
        PrintWriter pw;

        try {
            in = socket.getInputStream();//字节输入流

            reader=new BufferedReader(new InputStreamReader(in));//将其转化为字符缓冲流


            //获取客户端提交的信息
            String info;
            while ((info = reader.readLine()) != null) {
                System.out.println("我是服务器，客户端说:" + info);
            }
            socket.shutdownInput();//关闭输入流


//            获取输出流响应客户端的请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os);//包装为打印流
            pw.write("欢迎：登录成功");
            pw.flush();

            pw.close();
            os.close();

            reader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
