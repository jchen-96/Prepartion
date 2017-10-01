package com.jchen.javaStu.concurrent.blog.base.ten;

/**
 * 线程优先级和守护线程
 * 线程分为用户线程和守护线程
 * 每一个线程都有一个优先级
 * 在一些运行的主线程中创建新的子线程时，子线程的优先级被设置为等于创建它的主线程的优先级，子线程的类型和主线程的类型相同
 * 当只有守护进程运行时，jVM会自动退出
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()
                + "(" + Thread.currentThread().getPriority() + ")");
        Thread t1=new ThreadA("t1");
        Thread t2=new ThreadA("t2");
        t1.start();
        t2.start();
        t1.setPriority(1);
        t2.setPriority(10);
        t1.start();
        t2.start();

    }

}

class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "(" + Thread.currentThread().getPriority() + ")"
                    + ", loop " + i);
        }
    }
}