package com.jchen.javaStu.moocNet;

import java.io.*;
import java.net.Socket;

/**
 * Created by jchen on 17-8-30.
 */
public class Client {
    /**
     * 客户端的实现
     */
    public static void main(String[] args) {
        try {
            //创建一个socket，指定服务器地址和端口
            Socket socket=new Socket("localhost",8888);

            //客户端可以向服务器端发送信息，获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流

            pw.write("用户名：admin,密码：123");
            pw.flush();//刷新，将流输出
            socket.shutdownOutput();

//            接受服务器响应信息
            InputStream in=socket.getInputStream();
            InputStreamReader reader=new InputStreamReader(in);
            BufferedReader reader1=new BufferedReader(reader);

            String data;
            while ((data=reader1.readLine())!=null){
                System.out.println("我是客户端,服务器说"+data);
            }

            reader1.close();
            reader.close();

//            关闭资源
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
