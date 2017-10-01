package com.jchen.javaStu.concurrent.blog.base.five;

import java.util.Objects;

/**
 * 等待和唤醒
 * 在Object.java中，定义了wait(), notify()和notifyAll()等接口。wait()的作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁。
 * 而notify()和notifyAll()的作用，则是唤醒当前对象上的等待线程；notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。
 * <p>
 * Object类中关于等待/唤醒的API详细信息如下：
 * notify()      -- 唤醒在此对象监视器上等待的单个线程。
 * notifyAll()   -- 唤醒在此对象监视器上等待的所有线程。
 * wait()        -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，当前线程被唤醒(进入“就绪状态”)。
 * wait(long timeout)             -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
 * wait(long timeout, int nanos)  -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者其他某个线程中断当前线程，或者已超过某个实际时间量”，当前线程被唤醒(进入“就绪状态”)。
 */
public class ThreadTest {
    //wait和notifyall　test
    private static Object obj = new Object();

    public static void main(String[] args) {
        /**
         * wait和notifyall
         */
//        (02) “主线程”通过 new ThreadA("t1") 新建“线程t1”。随后通过synchronized(t1) 获取“t1对象的同步锁”。然后调用t1.start() 启动“线程t1”。
//        (03) “主线程”执行t1.wait() 释放“t1对象的锁”并且进入“等待(阻塞) 状态”。等待t1对象上的线程通过notify() 或 notifyAll () 将其唤醒。
//        (04) “线程t1”运行之后，通过synchronized(this) 获取“当前对象的锁”；接着调用notify() 唤醒“当前对象上的等待线程”，也就是唤醒“主线程”。
//        (05) “线程t1”运行完毕之后，释放“当前对象的锁”。紧接着，“主线程”获取“t1对象的锁”，然后接着运行。
        //wait方法比较奇葩
        //官方解释
//        注意：jdk的解释中，说wait() 的作用是让“当前线程”等待，而“当前线程”是指正在cpu上运行的线程！
//        这也意味着，虽然t1.wait() 是通过“线程t1”调用的wait() 方法，但是调用t1.wait() 的地方是在“主线程main”中。而主线程必须是“当前线程”，也就是运行状态，才可以执行t1.wait()。所以，
//        此时的“当前线程”是“主线程main”！因此，t1.wait() 是让“主线程”等待，而不是“线程t1”！
//        Thread t1 = new ThreadA("t1");
//
//        synchronized (t1) {
//            try {
////               启动线程
//                System.out.println(Thread.currentThread().getName() + " start t1");
//                t1.start();
////                主线程等待t1通过notify唤醒
//                System.out.println(Thread.currentThread().getName() + " wait()");
//                t1.wait();
//                System.out.println(Thread.currentThread().getName() + " continue");
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        /**
         * wait(long timeout) 和notify
         */
//        Thread t2 = new ThreadB("t2");
//
//        synchronized (t2) {
//            try {
//                // 启动“线程t1”
//                System.out.println(Thread.currentThread().getName() + " start t1");
//                t2.start();
//                // 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
//                System.out.println(Thread.currentThread().getName() + " call wait");
//                t2.wait(3000);
//                System.out.println(Thread.currentThread().getName() + " continue");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        /**
         * wait和notifyall
         */
//        Object中的wait(), notify() 等函数，和synchronized一样，会对“对象的同步锁”进行操作。
//
//        wait() 会使“当前线程”等待，因为线程进入等待状态，所以线程应该释放它锁持有的“同步锁”，否则其它线程获取不到该“同步锁”而无法运行！
//        OK，线程调用wait() 之后，会释放它锁持有的“同步锁”；而且，根据前面的介绍，我们知道：等待线程可以被notify() 或notifyAll() 唤醒。现在，请思考一个问题：notify() 是依据什么唤醒等待线程的？
//        或者说，wait() 等待线程和notify() 之间是通过什么关联起来的？答案是：依据“对象的同步锁”。
//
//        负责唤醒等待线程的那个线程(我们称为“唤醒线程”)，它只有在获取“该对象的同步锁”(这里的同步锁必须和等待线程的同步锁是同一个)，并且调用notify() 或notifyAll() 方法之后，才能唤醒等待线程。虽然，
//        等待线程被唤醒；但是，它不能立刻执行，因为唤醒线程还持有“该对象的同步锁”。必须等到唤醒线程释放了“对象的同步锁”之后，等待线程才能获取到“对象的同步锁”进而继续运行。
//
//        总之，notify(), wait() 依赖于“同步锁”，而“同步锁”是对象锁持有，并且每个对象有且仅有一个！这就是为什么notify(), wait()等函数定义在Object类，而不是Thread类中的原因。
        Thread t1 = new ThreadC("t1");
        Thread t2 = new ThreadC("t2");
        Thread t3 = new ThreadC("t3");
        t1.start();
        t2.start();
        t3.start();
        try {
            System.out.println(Thread.currentThread().getName() + " sleep(3000)");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj) {
//            主线程等待唤醒
            System.out.println(Thread.currentThread().getName() + " notifyall");
            obj.notifyAll();
        }


    }

    static class ThreadC extends Thread {
        public ThreadC(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (obj) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " call notify");
//            唤醒当前wait进程
            notify();
        }
    }
}

class ThreadB extends Thread {
    public ThreadB(String name) {
        super(name);
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " run");
        while (true) ;
    }
}
