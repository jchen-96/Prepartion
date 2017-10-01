package com.jchen.javaStu.concurrent.artConccurrent.ThreadPool;

/**
 * Created by jchen on 17-7-24.
 * 实现一个简单的线程池
 */
public interface MyThreadPool<Job extends Runnable> {
//    执行一个job这个job需要实现Runable接口
    void execute(Job job);

//    关闭线程池
    void shutdown();

//    增加工作者线程
    void addWorkers(int num);
//    减少工作者进程

    void removeWorkers(int num);

    int getJobSize();
}
