package com.jchen.javaStu.concurrent.artConccurrent.poolConnect;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by jchen on 17-7-22.
 * 实现一个简单的额数据库连接池
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initalSize) {
        if (initalSize > 0) {
            for (int i = 0; i < initalSize; i++) {
                pool.add(CoonectionDriver.createConnection());
            }
        }
    }
    public void releaseConnection(Connection connection){
        if(connection!=null){
            synchronized (pool){
//                释放连接后需要进行通知，这样其他的消费者就能感知到连接池中已经归还一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
//    超时获取连接池
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
//            完全超时
            if(mills<0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future=System.currentTimeMillis()+mills;

                long remaining=mills;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining=future-System.currentTimeMillis();
                }
                Connection result=null;

                if(!pool.isEmpty()){
                    result=pool.removeFirst();
                }
                return result;
            }
        }
    }
}
