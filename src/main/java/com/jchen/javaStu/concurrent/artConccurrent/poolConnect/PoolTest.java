package com.jchen.javaStu.concurrent.artConccurrent.poolConnect;


import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jchen on 17-7-24.
 */
public class PoolTest {

//    初始化一个数据库连接池
    static ConnectionPool connectionPool=new ConnectionPool(10);


//    保证所有ConnectionRunner同时开始
    static CountDownLatch start=new CountDownLatch(1);

//    main线程会等待所有的ConnectionRunner 结束后才开始执行
    static CountDownLatch end;


    public static void main(String[] args) throws Exception {
        int threadCount=20;
        end=new CountDownLatch(threadCount);

        int count=20;

        AtomicInteger got=new AtomicInteger();

        AtomicInteger notGot=new AtomicInteger();

        for(int i=0;i<threadCount;i++){
            Thread thread=new Thread(new ConnectionRunner(count,got,notGot),"connectionThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke:"+(threadCount*count));
        System.out.println("gotConnection:"+got);
        System.out.println("notGot:"+notGot);
    }
    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run(){
            try{
                start.await();
            }catch (Exception ex){

            }
            while (count>0){
                try{
                    //从连接池获取连接，如果没有获取到，返回空
                    //分别统计获取和未获取到的情况
                    Connection connection=connectionPool.fetchConnection(1000);
                    if(connection!=null){
                        try{
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            connectionPool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                    }
                }catch (Exception e){
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}















