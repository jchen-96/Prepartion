package com.jchen.algorithm.chapter8;

/**
 * Created by jchen on 17-8-4.
 */
public class BiteQuestion {
//    如何判断一个数是否为2的n次方
    public boolean isPower(int n){
        if(n<1){
            return false;
        }
        int k=1;
        while (k<=n){
            if(k==n){
                return true;
            }else{
                k=k<<1;
            }
        }
        return false;
    }
//    将n和n-1进行位运算，判断是否是2的整数次幂
    public boolean isPower2(int n){
        if(n<1){
            return false;
        }
        int k=n&(n-1);
        return k==0;

    }
//   求一个数的二进制表示中1的个数
    public int count(int k){
        int count=0;
        while(k>0){
            k=k&(k-1);
            count++;
        }
        return count;
    }
}
