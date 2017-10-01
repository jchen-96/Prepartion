package com.jchen.javaStu.concurrent.artConccurrent.ThreadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jchen on 17-7-24.
 */
public class PoolImp<Job extends Runnable> implements MyThreadPool<Job> {


    //线程池的最大最大线程数
    private static final int MAX_NUMBER_WORKERS = 10;

    //线程池的默认线程数
    private static final int DEFAULT_SIZE=5;

    //线程池的最小线程数量
    private static final int MIN_SIZE=1;

    //用一个LinkList作为线程池
    private final LinkedList<Job> jobs=new LinkedList<>();

    //工作者列表
    private final List<Worker> workers=Collections.synchronizedList(new ArrayList<Worker>());

//    工作者线程数量
    private int workNum=DEFAULT_SIZE;

//    线程编号的生成
    private AtomicLong threadNum;

    private void initWorkers(int size){
        for(int i=0;i<size;i++){
            Worker worker=new Worker();
            workers.add(worker);
            Thread thread=new Thread(worker,"Threadplool_worker:"+threadNum.incrementAndGet());
            thread.start();
        }
    }
    public PoolImp(){
        initWorkers(DEFAULT_SIZE);
    }

    public PoolImp(int num){
        workNum=num>MAX_NUMBER_WORKERS?MAX_NUMBER_WORKERS:num<MIN_SIZE?MIN_SIZE:num;
        initWorkers(workNum);
    }


    @Override
    public void execute(Job job) {
        if(job!=null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notifyAll();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker work:workers){
            work.shutDown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            if(num+this.workNum>MAX_NUMBER_WORKERS){
                initWorkers(MAX_NUMBER_WORKERS);
            }else {
                initWorkers(num);
            }
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs){
            if(num>=this.workNum){
                throw new IllegalArgumentException("beyondNum");
            }
            int count=0;
            while (count<num){
                Worker woker=workers.get(count);
                if(workers.remove(woker)){
                    woker.shutDown();
                    count++;
                }
            }
            this.workNum-=count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

     class Worker implements Runnable {
        //    是否工作
        private volatile boolean running=true;
        @Override
        public void run() {
            while (running){
                Job job=null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try{
                            jobs.wait();
                        }catch (InterruptedException e){
//                            感知到外部对workerThread中断操作
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if(!jobs.isEmpty()){
                        try{
                            job.run();
                        }catch (Exception e){

                        }
                    }
                }
            }
        }

        public void shutDown(){
            running=false;
        }
    }
}
