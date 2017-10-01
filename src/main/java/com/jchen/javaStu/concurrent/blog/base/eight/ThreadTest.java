package com.jchen.javaStu.concurrent.blog.base.eight;

/**
 * 多线程基础之join篇
 * join() 定义在Thread.java中。
 * join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行。
 * 这里的主线程不一定指的是main方法，也可能是包含子线程的线程
 */
public class ThreadTest {
    public static void main(String[] args) {
        try {
            ThreadA t1 = new ThreadA(("t1"));
            t1.start();
            t1.join();
            System.out.printf("%s finish\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.printf("%s start\n", this.getName());

            // 延时操作
            for (int i = 0; i < 1000000; i++)
                ;

            System.out.printf("%s finish\n", this.getName());
        }
    }

}
//join源码实现
//    public final void join() throws InterruptedException {
//        join(0);
//    }
//
//    public final synchronized void join(long millis)
//            throws InterruptedException {
//        long base = System.currentTimeMillis();
//        long now = 0;
//
//        if (millis < 0) {
//            throw new IllegalArgumentException("timeout value is negative");
//        }
//
//        if (millis == 0) {
//            while (isAlive()) {
//                wait(0);
//            }
//        } else {
//            while (isAlive()) {
//                long delay = millis - now;
//                if (delay <= 0) {
//                    break;
//                }
//                wait(delay);
//                now = System.currentTimeMillis() - base;
//            }
//        }
//    }
//        从代码中，我们可以发现。当millis==0时，会进入while(isAlive())循环；即只要子线程是活的，主线程就不停的等待。
//        我们根据上面解释join()作用时的代码来理解join()的用法！
//        问题：
//        虽然s.join()被调用的地方是发生在“Father主线程”中，但是s.join()是通过“子线程s”去调用的join()。
//        那么，join()方法中的isAlive()应该是判断“子线程s”是不是Alive状态；对应的wait(0)也   应该是“让子线程s”等待才对。
//        但如果是这样的话，s.join()的作用怎么可能是“让主线程等待，直到子线程s完成为止”呢，应该是让"子线程等待才对(因为调用子线程对象s的wait方法嘛)"？
//        答案：wait()的作用是让“当前线程”等待，而这里的“当前线程”是指当前在CPU上运行的线程。
//             所以，虽然是调用子线程的wait()方法，但是它是通过“主线程”去调用的；所以，休眠的是主线程，而不是“子线程”！