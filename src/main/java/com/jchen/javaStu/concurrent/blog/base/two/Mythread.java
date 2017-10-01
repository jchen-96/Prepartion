package com.jchen.javaStu.concurrent.blog.base.two;

/**
 * Created by jchen on 17-5-13.
 * 线程的两种实现方式
 * extend thread
 * implements runable
 * 不能使用overidde注解
 */
public class Mythread{
    public static void main(String[] args){
//        Thread t1=new MythreadDe();
//        Thread t2=new MythreadDe();
//        Thread t3=new MythreadDe();
//        t1.start();
//        t2.start();
//        t3.start();

        Runnable mt=new MyThreadRu();
        Thread t4=new Thread(mt);
        Thread t5=new Thread(mt);
        Thread t6=new Thread(mt);
        t4.start();
        t5.start();;
        t6.start();
    }
}
class MythreadDe extends Thread {
    private int ticket = 10;

    public void run() {
        for (int i = 0; i < 20; i++) {
            if (this.ticket > 0) {
                System.out.println(this.getName() + "卖票:ticket" + this.ticket--);
            }
        }
    }
}
class MyThreadRu implements Runnable{
    private int tickest=10;

    @Override
    public void run(){
        for(int i=0;i<20;i++){
            if(this.tickest>0){
                System.out.println(Thread.currentThread().getName()+"卖票：tickets"+this.tickest--);
            }
        }

    }
}

