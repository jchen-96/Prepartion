package com.jchen.javaStu.concurrent.artConccurrent.ThreadPool;

import java.util.concurrent.*;

/**
 * Created by jchen on 17-8-26.
 * java Executor framework演示
 */
public class ThreadPoolDemon {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        固定线程数量
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new CodingTask(i));
        }
        System.out.println("10 task dipatch successfully");
        executorService.shutdown();
    }
}
