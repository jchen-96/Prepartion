package com.jchen.javaStu.moocNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by jchen on 17-8-30.
 * url:协议名称和资源名称，中间用冒号隔开
 */
public class UrlDemon {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.imooc.com");
//        ？表示参数，＃表示锚点
        URL url1=new URL(url,"/index.html?username=tom#test");
        System.out.println("协议:"+url1.getProtocol());
        System.out.println("主机:"+url1.getHost());
        System.out.println("端口:"+url1.getPort());//没有指定端口号,根据协议的不同使用默认端口，返回-1
        System.out.println("文件路径:"+url1.getPath());
        System.out.println("文件名称:"+url1.getFile());
        System.out.println("相对路径:"+url1.getRef());
        System.out.println("查询字符串"+url1.getQuery());


        //通过url对象的openStream方法可以得到制定资源的输入流
        //通过输入流可以读取访问网络上的数据
        /**
         * 使用utl读取网页内
         */
        URL url2=new URL("http://www.baidu.com");
        InputStream in=url2.openStream();

        InputStreamReader isr=new InputStreamReader(in,"utf8");

//        为字符输入流添加缓冲
        BufferedReader reader=new BufferedReader(isr);

        String data=reader.readLine();
        while (data!=null){
            System.out.println(data);
            data=reader.readLine();
        }

        in.close();
        isr.close();
        reader.close();
    }
}
