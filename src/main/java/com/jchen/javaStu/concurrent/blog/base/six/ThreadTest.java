package com.jchen.javaStu.concurrent.blog.base.six;

/**
 * 线程基础之线程让步
 * １，yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；
 * ，２， 但是，并不能保证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
 */
public class ThreadTest {
    private static Object object = new Object();

    public static void main(String[] args) {
        /**
         * 第二条测试
         */
//        Thread t1=new ThreadA("t1");
//        Thread t2=new ThreadA("t2");
//        t1.start();
//        t2.start();
        /**
         * wait和yield的区别
         */
//        wait() 的作用是让当前线程由“运行状态”进入“等待(阻塞) 状态”的同时，也会释放同步锁。而yield() 的作用是让步，它也会让当前线程离开“运行状态”。它们的区别是：
//        (01) wait() 是让线程由“运行状态”进入到“等待(阻塞) 状态”，而不yield() 是让线程由“运行状态”进入到“就绪状态”。
//        (02) wait() 是会线程释放它所持有对象的同步锁，而yield() 方法不会释放锁。
        Thread t1 = new ThreadB("t1");
        Thread t2 = new ThreadB("t2");
        t1.start();
        t2.start();
    }

    static class ThreadB extends Thread {
        public ThreadB(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                for (int i = 0; i < 10; i++) {
                    System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
                    // i整除4时，调用yield
                    if (i % 4 == 0)
                        Thread.yield();
                }
            }
        }
    }

}

class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
            if (i % 4 == 0) {
                Thread.yield();
            }
        }
    }
}
