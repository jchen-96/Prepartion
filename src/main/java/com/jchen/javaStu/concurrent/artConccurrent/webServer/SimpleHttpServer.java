package com.jchen.javaStu.concurrent.artConccurrent.webServer;

import com.jchen.javaStu.concurrent.artConccurrent.ThreadPool.MyThreadPool;
import com.jchen.javaStu.concurrent.artConccurrent.ThreadPool.PoolImp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jchen on 17-7-28.
 */
public class SimpleHttpServer {
    static MyThreadPool<HttpRequestHandler> threadPool = new PoolImp<HttpRequestHandler>(1);

    static String basePath;

    static ServerSocket serverSocket;

    static int port=8080;

    public static void setPort(int port){
        if(port>0){
            SimpleHttpServer.port=port;
        }
    }
    public static void setBasePath(String basePath){
        if(basePath!=null&&new File(basePath).exists()&&new File(basePath).isDirectory()){
            SimpleHttpServer.basePath=basePath;
        }
    }

//    启动httpServer
    public static void start() throws Exception{
        serverSocket=new ServerSocket(port);
        Socket socket=null;

        while ((socket=serverSocket.accept())!=null){
//            接受一个socket客户端，生成一个httpRequestHadler
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String ling = null;

            BufferedReader read1 = null;

            BufferedReader read2 = null;

            PrintWriter out = null;

            InputStream in = null;

            try {
                read1=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header=read1.readLine();
                //由相对路径计算出绝对路径
                String filePath=basePath+header.split(" ")[1];
                out=new PrintWriter(socket.getOutputStream());
//                如果请求为jpg或者ico,读取资源并输出
                if(filePath.endsWith("jpg")||filePath.endsWith("ico")){
                    in =new FileInputStream(filePath);
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    int i=0;
                    while ((i=in.read())!=-1){
                        baos.write(i);
                    }
                    byte[] arrat=baos.toByteArray();
                    out.println("HTTP/1.1 200 ok");
                    out.println("server:Molly");
                    out.println("ContentType: img/jpeg");
                    out.println("Content-Length:"+arrat.length);
                    out.println("");
                    socket.getOutputStream().write(arrat,0,arrat.length);
                }else{
                    read2=new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out=new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 ok");
                    out.println("server:Molly");
                    out.println("ContentType: img/jpeg");
                    out.println("Content-Length: text/html;charset=UTF-8");
                    out.println("");
                    while ((ling=read2.readLine())!=null){
                        out.println(ling);
                    }

                }
                out.flush();
            } catch (Exception e) {
                out.println("http/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(read1,read2,out,socket);
            }
        }
    }

//    关闭各种流
    private static void close(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable close : closeables) {
                try {
                    close.close();
                } catch (Exception e) {

                }
            }
        }
    }
}
