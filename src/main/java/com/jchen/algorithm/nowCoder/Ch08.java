package com.jchen.algorithm.nowCoder;

/**
 * Created by jchen on 17-8-17.
 * 位运算相关
 */
public class Ch08 {
    //交换两个整数的值
    public void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    //给定32位整数，返回a和b中较大的数,不能进行任何比较
    public int getMax(int a, int b) {
        int c = a - b;
        int scA = sign(c);
        int scB = flip(scA);
        return a * scA + b * scB;
    }

    private static int sign(int a) {
        return flip((a >> 31) & 1);
    }

    private static int flip(int n) {
        return n ^ 1;
    }

    //找到唯一出现一次的数组
    public int findOdd(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        return res;
    }

    //找到两个出现奇数次的数字
    public int[] findOdd2(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }

        int k = 0, temp = res;
        while ((temp & 1) == 0) {
            k++;
            temp >>= 1;
        }

        int help = (int) Math.pow(2.0, k);
        int check2 = 0;

        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & help) == 1) {
                check2 ^= arr[i];
            }
        }
        int check1 = check2 ^ res;

        int[] result = new int[]{check1, check2};
        return result;
    }

}
