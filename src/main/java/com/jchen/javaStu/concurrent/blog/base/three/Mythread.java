package com.jchen.javaStu.concurrent.blog.base.three;

/**
 * Created by jchen on 17-5-13.
 * run和start方法的区别
 *   start() : 它的作用是启动一个新线程，新线程会执行相应的run()方法。start()不能被重复调用。
 *   run()   : run()就和普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！
 *
 */
public class Mythread {
    public static void main(String[] args){
        Thread mythread=new MythreadDe("mythread");
        System.out.println(Thread.currentThread().getName()+"call mythread.run");
        mythread.run();
        System.out.println(Thread.currentThread().getName()+" call mythread.start");
        mythread.start();
        Thread.currentThread().start();
    }
}
class MythreadDe extends Thread{
    public MythreadDe(String name){
        super(name);
    }
    public void run(){
        System.out.println(this.getName()+"is Running");
    }

}
