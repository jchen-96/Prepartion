package com.jchen.javaStu.concurrent.artConccurrent.ThreadPool;

/**
 * Created by jchen on 17-8-26.
 */
public class CodingTask implements Runnable {
    private final int id;

    public CodingTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("coding task:"+id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
