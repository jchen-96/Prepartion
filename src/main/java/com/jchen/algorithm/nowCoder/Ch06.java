package com.jchen.algorithm.nowCoder;

import java.util.ArrayList;

/**
 * Created by jchen on 17-8-15.
 * 二分搜索相关
 */
public class Ch06 {
    /**
     * 局部最小值
     */
    public int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr[0] < arr[1] || arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr[0] < arr[1] ? arr[0] : arr[arr.length - 1];
        }

        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;

            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return arr[mid];
    }

    /**
     * 给定一个有序数组arr和一个整数arr,找到arr中num这个数字出现的最左边的位置
     */
    public int findLeft(int[] arr, int num) {
        int res = -1;

        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == num) {
                res = mid;
                r = mid;
            } else if (arr[mid] > num) {
                r = mid - 1;
            } else if (arr[mid] < num) {
                l = mid + 1;
            }
        }
        return res;
    }

    /**
     * 给定一个有序循环数组，找到数组的最小值
     */
    public int finMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[l] > arr[r]) {
                r = mid;
            } else if (arr[mid] > arr[r]) {
                l = mid + 1;
            } else {
                break;
            }
        }
        if (l == r) {
            return arr[l];
        }
        int min = arr[l];
        while (l <= r) {
            if (arr[l] < min) {
                l++;
                min = arr[l];
            }
        }
        return min;
    }

    /**
     * 给定一个有序没有重复元素的数组，找到满足arr[i]=i的最左元素
     */
    public int find(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[0] > arr.length - 1 || arr[arr.length - 1] < 0) {
            return -1;
        }

        int res = -1;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > mid) {
                r = mid - 1;
            } else if (arr[mid] < mid) {
                l = mid + 1;
            } else {
                res = mid;
                r = mid - 1;
            }
        }
        return res;
    }

    /**
     * 给定一个完全二叉树，返回这个二叉树的节点个数
     */
    public int findCount(TreeNode head){
        if(head==null){
            return 0;
        }
        int leftLen=depth(head.left);
        int rightLen=depth(head.right);
        if(rightLen==leftLen){
            return (int)Math.pow(2.0,leftLen)+findCount(head.right);
        }else{
            return (int)Math.pow(2.0,rightLen)+findCount(head.left);
        }
    }
    int depth(TreeNode node){
        if(node==null){
            return 0;
        }
        int len=0;
        TreeNode temp=node;
        while (temp!=null){
            len++;
            temp=temp.left;
        }
        return len;
    }

    /**
     *更快的求解一个数的N次方
     * */
    public int getPower(int k,int N){
        if(k==0){
            return 0;
        }
        if(N==0){
            return 1;
        }
        if(k>1000000007){
            k=k%1000000007;
        }
        ArrayList<Long> arr=new ArrayList<>();
        ArrayList<Integer> bit=new ArrayList<>();
        long m=N,temp=k,res=1;
        while (m!=0){
            arr.add(temp);
            temp*=temp;

            if(temp>1000000007){
                temp=temp%1000000007;
            }
            if(m%2==0){
                bit.add(0);
            }else {
                bit.add(1);
            }
            m/=2;
        }
        for(int i=0;i<bit.size();i++){
            if(bit.get(i)!=0){
                res*=arr.get(i);
                if(res>1000000007){
                    res%=1000000007;
                }
            }
        }
        return (int)res%1000000007;
    }



    public static void main(String[] args) {
        System.out.println(new Ch06().findLeft(new int[]{1, 2, 3, 3, 3, 3, 4}, 3));
    }
}
