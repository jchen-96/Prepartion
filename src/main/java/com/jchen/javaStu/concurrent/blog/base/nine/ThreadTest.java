package com.jchen.javaStu.concurrent.blog.base.nine;

import com.jchen.javaStu.concurrent.blog.base.three.Mythread;

/**
 * interrupt说明
 * interrupt()的作用是中断本线程。
 * 本线程中断自己是被允许的；其它线程调用本线程的interrupt()方法时，会通过checkAccess()检查权限。这有可能抛出SecurityException异常。
 * 如果本线程是处于阻塞状态：调用线程的wait(), wait(long)或wait(long, int)会让它进入等待(阻塞)状态，
 * 或者调用线程的join(), join(long), join(long, int), sleep(long), sleep(long, int)也会让它进入阻塞状态。若线程在阻塞状态时，调用了它的interrupt()方法，那么它的“中断状态”会被清除并且会收到一个InterruptedException异常。例如，线程通过wait()进入阻塞状态，此时通过interrupt()中断该线程；调用interrupt()会立即将线程的中断标记设为“true”，但是由于线程处于阻塞状态，所以该“中断标记”会立即被清除为“false”，同时，会产生一个InterruptedException的异常。
 * 如果线程被阻塞在一个Selector选择器中，那么通过interrupt()中断它时；线程的中断标记会被设置为true，并且它会立即从选择操作中返回。
 * 如果不属于前面所说的情况，那么通过interrupt()中断线程时，它的中断标记会被设置为“true”。
 * 中断一个“已终止的线程”不会产生任何操作。
 * <p>
 * 线程的终止方式
 * 分为两种状态：阻塞时终止，运行时候终止
 * <p>
 * 中断处于阻塞状态的线程
 * 通常，我们通过“中断”方式终止处于“阻塞状态”的线程。
 * 当线程由于被调用了sleep(), wait(), join()等方法而进入阻塞状态；
 * 若此时调用线程的interrupt()将线程的中断标记设为true。由于处于阻塞状态，中断标记会被清除，
 * 同时产生一个InterruptedException异常。将InterruptedException放在适当的位置就能终止线程。
 * <p>
 * 中断处于运行状态的线程
 * 通常，我们通过“标记”方式终止处于“运行状态”的线程。其中，包括“中断标记”和“额外添加标记”。
 * １、通过中断标记终止线程
 * <p>
 * public void run() {
 * while (!isInterrupted())
 * // 执行任务...
 * <p>
 * 2、通过额外标记终止线程
 * 设立flag标记
 * <p>
 * interrupted() 和 isInterrupted()。
 * interrupted() 和 isInterrupted()都能够用于检测对象的“中断标记”。
 * 区别是，interrupted()除了返回中断标记之外，它还会清除中断标记(即将中断标记设为false)；而isInterrupted()仅仅返回中断标记。
 */
public class ThreadTest {
    public static void main(String[] args) {
//    通过interrupt标记方法方法来终止处于阻塞状态的线程
//        try {
//            Thread t1 = new ThreadA("t1");  // 新建“线程t1”
//            System.out.println(t1.getName() + " (" + t1.getState() + ") is new.");
//
//            t1.start();                      // 启动“线程t1”
//            System.out.println(t1.getName() + " (" + t1.getState() + ") is started.");
//
//            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
//            Thread.sleep(300);
//            t1.interrupt();
//            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted.");
//
//            // 主线程休眠300ms，然后查看t1的状态。
//            Thread.sleep(300);
//            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted now.");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        额外设置标记终止线程
        try {
            ThreadB t1 = new ThreadB("t1");
            System.out.println(t1.getName() + " (" + t1.getState() + ") is new.");
            t1.start();
            System.out.println(t1.getName() + " (" + t1.getState() + ") is started.");
            Thread.sleep(300);
            t1.stopTask();
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted.");
            Thread.sleep(300);
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }

    @Override
    public void run() {
//        如果将isInterrupt放在try catch 外面则线程不会终止，因为循环在里面就已经被捕获，抛出异常的同时会清除中断标记isinterrupt被设置为false，所以会陷入死循环
        try {
            int i = 0;
            while (!isInterrupted()) {
                Thread.sleep(100);
                i++;
                System.out.println(this.getName() + " (" + this.getState() + ") loop" + i);

            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
        }
    }

}

class ThreadB extends Thread {
    private volatile boolean flag = true;

    public void stopTask() {
        flag = false;
    }

    public ThreadB(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                int i = 0;
                while (flag) {
                    Thread.sleep(100);
                    i++;
                    System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
            }
        }
    }
}