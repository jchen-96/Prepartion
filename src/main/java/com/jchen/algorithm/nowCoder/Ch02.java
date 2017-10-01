package com.jchen.algorithm.nowCoder;

/**
 * Created by jchen on 17-8-13.
 * 排序专题
 */
public class Ch02 {
    /**
     * 堆排序
     */
    public void heapSort(int[] arr){
        int n = arr.length;

        // 注意，此时我们的堆是从0开始索引的
        // 从(最后一个元素的索引-1)/2开始
        // 最后一个元素的索引 = n-1
        for (int i = (n - 1 - 1) / 2; i >= 0; i--)
            shiftDown(arr, i,n);

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0,i);
            shiftDown(arr, 0,i);
        }
    }
    //    从零开始索引
    private void shiftDown(int[] arr,int i,int n){
        int temp=arr[i];
        while (i*2+1<n){
            int j=i*2+1;
            if(j+1<n&&arr[j]<arr[j+1]){
                j++;
            }
            if(arr[j]>temp){
                arr[i]=arr[j];
                i=j;
            }else {
                break;
            }
        }
        arr[i]=temp;
    }

    //swap
    public void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    /**
     * 希尔排序
     */
    public void shellSort(int[] arr) {

    }

    public static void main(String[] args) {
        int[] arr=new int[]{4,3,2,1,8,6,5,7,22};
        new Ch02().heapSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
