package com.jchen.javaStu.concurrent.blog.base.four;

/**
 * synchronized关键字
 * Created by jchen on 17-5-13.
 * 基本原理
 * 在java中，每一个对象有且仅有一个同步锁。这也意味着，同步锁是依赖于对象而存在。
 * 当我们调用某对象的synchronized方法时，就获取了该对象的同步锁。例如，synchronized(obj)就获取了“obj这个对象”的同步锁。
 * 不同线程对同步锁的访问是互斥的。也就是说，某时间点，对象的同步锁只能被一个线程获取到！通过同步锁，我们就能在多线程中，实现对“对象/方法”的互斥访问。
 * 例如，现在有两个线程A和线程B，它们都会访问“对象obj的同步锁”。假设，在某一时刻，线程A获取到“obj的同步锁”并在执行一些操作；
 * 而此时，线程B也企图获取“obj的同步锁” —— 线程B会获取失败，它必须等待，直到线程A释放了“该对象的同步锁”之后线程B才能获取到“obj的同步锁”从而才可以运行。
 * <p>
 * 基本原则
 * 我们将synchronized的基本规则总结为下面3条，并通过实例对它们进行说明。
 * 第一条: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
 * 第二条: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块。
 * 第三条: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
 * <p>
 * <p>
 * 实例锁和全局锁
 * 实例锁 -- 锁在某一个实例对象上。如果该类是单例，那么该锁也具有全局锁的概念。
 * 实例锁对应的就是synchronized关键字。
 * 全局锁 -- 该锁针对的是类，无论实例多少个对象，那么线程都共享该锁。
 * 全局锁对应的就是static synchronized（或者是锁在该类的class或者classloader对象上）。
 */
public class ThreadTest {
    public static void main(String[] args) {
        /**
         * 第一条原则
         */
//        Runnable demon = new MyRunable();
//        Thread t1 = new Thread(demon);
//        Thread t2 = new Thread(demon);
//
//        t1.start();
//        t2.start();
//        MyThread t3=new MyThread("t3");
//        MyThread t4=new MyThread("t4");
//        t3.start();
//        t4.start();
        /**
         * 第二条原则
         */
//            final Count count = new Count();
//
//            Thread t1 = new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    count.synMethod();
//                }
//            }, "t1");
//
//            Thread t2 = new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    count.noSynMethdo();
//                }
//            }, "t2");
//            t1.start();
//            t2.start();
        /**
         * 第三条原则
         */
//        final Count2 count = new Count2();
//
//        Thread t1 = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                count.synMethod();
//            }
//        }, "t1");
//
//        Thread t2 = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                count.noSynMethdo();
//            }
//        }, "t2");
//        t1.start();
//        t2.start();

    }
}

//原则一测试
class MyRunable implements Runnable {
    /**
     * 两个线程运行同一个runable实例，由于加了同步锁，两个线程依次顺序执行
     */
    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);//休眠一秒
                    System.out.println(Thread.currentThread().getName() + "loop" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class MyThread extends Thread {
    /**
     * 两个线程
     */
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(this.getName() + "loop" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//原则二测试
class Count {
    //包含同步方法
    public void synMethod() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "synMethod loop:" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    //不包含同步方法
    public void noSynMethdo() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "nosynMethod:" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//原则三测试
class Count2 {
    //包含同步方法
    public void synMethod() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "synMethod loop:" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    //包含同步方法
    public void noSynMethdo() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "nosynMethod:" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//synchronized方法 和 synchronized代码块
//synchronized代码块可以更精确的控制冲突限制访问区域，有时候表现更高效率


//关于“实例锁”和“全局锁”有一个很形象的例子：
//class Something {
//    public synchronized void isSyncA() {
//    }
//
//    public synchronized void isSyncB() {
//    }
//
//    public static synchronized void cSyncA() {
//    }
//
//    public static synchronized void cSyncB() {
//    }
//}
//假设，Something有两个实例x和y。分析下面4组表达式获取的锁的情况。
//        (01)x.isSyncA()与x.isSyncB()
//        (02)x.isSyncA()与y.isSyncA()
//        (03)x.cSyncA()与y.cSyncB()
//        (04)x.isSyncA()与Something.cSyncA()
//(01) 不能被同时访问。因为isSyncA()和isSyncB()都是访问同一个对象(对象x)的同步锁！
//(02) 可以同时被访问。因为访问的不是同一个对象的同步锁，x.isSyncA()访问的是x的同步锁，而y.isSyncA()访问的是y的同步锁。
//(03) 不能被同时访问。因为cSyncA()和cSyncB()都是static类型，x.cSyncA()相当于Something.isSyncA()，y.cSyncB()相当于Something.isSyncB()，因此它们共用一个同步锁，不能被同时反问。
//(04) 可以被同时访问。因为isSyncA()是实例方法，x.isSyncA()使用的是对象x的锁；而cSyncA()是静态方法，Something.cSyncA()可以理解对使用的是“类的锁”。因此，它们是可以被同时访问的。