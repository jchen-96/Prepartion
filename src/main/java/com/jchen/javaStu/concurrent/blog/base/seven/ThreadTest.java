package com.jchen.javaStu.concurrent.blog.base.seven;

/**
 * sleep方法介绍
 * sleep() 定义在Thread.java中。
 * sleep() 的作用是让当前线程休眠，即当前线程会从“运行状态”进入到“休眠(阻塞)状态”。
 * sleep()会指定休眠时间，线程休眠的时间会大于/等于该休眠时间；在线程重新被唤醒时，它会由“阻塞状态”变成“就绪状态”，从而等待cpu的调度执行。
 */
public class ThreadTest {
    private static Object object=new Object();
    public static void main(String[] args){
//        简单睡眠测试
//        Thread t1=new ThreadA("t1");
//        t1.start();

//        sleep方法不会释放同步锁
        Thread t1=new ThreadB("t1");
        Thread t2=new ThreadB("t2");
        t1.start();
        t2.start();
    }
    static class ThreadB extends Thread{
        public ThreadB(String name){
            super(name);
        }
        @Override
        public void run(){
            synchronized (object){
                try{
                    for (int i=0;i<10;i++){
                        System.out.printf("%s,%d\n",this.getName(),i);
                        if(i%4==0){
                            Thread.sleep(1000);
                        }
                    }
                }catch (InterruptedException e){
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

    public synchronized void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s:%d\n", this.getName(), i);
                if (i % 4 == 0) {
                    Thread.sleep(3000);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
