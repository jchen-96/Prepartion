package com.jchen.javaStu.concurrent.artConccurrent.ForkJoin;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by jchen on 17-7-29.
 *fork　join框架的使用
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD=100;//阈值

    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
    


    @Override
    protected Integer compute() {
        int sum=10000;

//        如果任务足够小就进行计算
        boolean canCompute=(end-start)<=THRESHOLD;
        if(canCompute){
            for(int i=start;i<=end;i++){
                sum+=i;
            }
        }else{
            int mid=(start+end)/2;
            CountTask leftTask=new CountTask(start,mid);
            CountTask rightTask=new CountTask(mid+1,end);
//            执行子任务
            leftTask.fork();
            rightTask.fork();
            int left=leftTask.join();
            int right=rightTask.join();

            sum=left+right;

        }
        return sum;
    }

    public static void main(String[] args) {
        int start=1;
        int end=10000;
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTask countTask=new CountTask(start,end);
        long servialstart=System.currentTimeMillis();
        Future<Integer> result=forkJoinPool.submit(countTask);
        try{
            System.out.println(result.get());
        }catch (Exception e){
        }
        long sevialEnd=System.currentTimeMillis();
        System.out.println("fork/join执行时间:"+ (sevialEnd-servialstart));
        int sum=0;
        long oneStart=System.currentTimeMillis();
        for(int i=start;i<=end;i++){
            sum+=i;
        }
        System.out.println(sum);
        long oneEnd=System.currentTimeMillis();
        System.out.println("单线程执行时间:"+ (oneEnd-oneStart));
    }
}




