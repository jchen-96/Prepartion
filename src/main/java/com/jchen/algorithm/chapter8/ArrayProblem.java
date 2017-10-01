package com.jchen.algorithm.chapter8;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jchen on 17-8-4.
 */
public class ArrayProblem {
    //    找到数组中的第二大元素
    public int findSecond(int[] arr) {
        if (arr.length < 2) {
            System.out.println("ilegal arguement");
            return -1;
        }
        int larger = arr[0] > arr[1] ? arr[1] : arr[0];
        int largest = arr[0] > arr[1] ? arr[0] : arr[1];

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] <= larger) {
                continue;
            } else if (arr[i] > largest) {
                larger = largest;
                largest = arr[i];
            } else if (arr[i] > larger && arr[i] < largest) {
                larger = arr[i];
            } else {
                continue;
            }

        }
        return larger;
    }

    //    求最大连续子数组之和
    public int maxSubArray(int[] arr) {
        int begin = 0;
        int end = 0;
        int nSum = 0;
        int nStart = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (nSum < 0) {
                nSum = arr[i];
                nStart = i;
            } else {
                nSum += arr[i];
            }
            if (nSum > maxValue) {
                maxValue = nSum;
                begin = nStart;
                end = i;
            }
        }
        return maxValue;
    }

    //    求解最短连续子数组，满足大于指定给的数k
    public int minSubArrayLen(int[] arr, int s) {
        int l = 0;
        int r = -1;
        int sum = 0;
        int len = arr.length + 1;
        while (l < arr.length) {
            if (sum < s && r + 1 < arr.length) {
                r++;
                sum += arr[r];
            } else {
                sum -= arr[l];
                l++;
            }
            if (sum >= s) {
                len = len > (r - l + 1) ? (r - l + 1) : len;
            }
        }
        if (len == arr.length + 1) {
            return 0;
        }
        return len;
    }

    //    将一个数组循环右移动k位
    public void shift_k(int[] arr, int k) {
        int len = arr.length;
        k = k % len;
        reverse(arr, 0, len - k - 1);
        reverse(arr, len - k, len - 1);
        reverse(arr, 0, len - 1);
    }

    public static void reverse(int[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            while (l < r) {
                swap(arr, l, r);
                l++;
                r--;
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //    找到数组中k小的数
    public int findKth(int[] arr, int k) {
        int p = partion(arr, 0, arr.length - 1);
        int l = 0;
        int r = arr.length - 1;
        while (p + 1 != k) {
            if (p + 1 < k) {
                l = p + 1;
                p = partion(arr, l, r);
            } else {
                r = p + 1;
                p = partion(arr, l, r);
            }
        }
        return arr[p];
    }

    //    找到数组中只出现过一次的数字,其余的数组只出现两次
    public int findOnly(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int j = 0; j < arr.length; j++) {
            sum = sum ^ arr[j];
        }
        return sum;
    }

    //    找出数组中唯一重复的元素　1-n-1 中只有一个元素重复
    public int findOne(int[] arr) {
        int len = arr.length;
        int sum = (len - 2) * (len - 1) / 2;
        int sum2 = 0;
        for (int j = 0; j < len; j++) {
            sum2 += arr[j];
        }
        return sum2 - sum;
    }

    //    求数对之差的最大值
//    求解一个包含重复元素的数组中给定的两个元素的最小距离
    public int minDistance(int[] arr, int a, int b) {
        if (arr == null) {
            return Integer.MIN_VALUE;
        }
        int len = arr.length;
        int n1_index = -1;
        int n2_index = -1;
        int min_dis = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (arr[i] == a) {
                n1_index = i;
                if (n2_index >= 0) {
                    min_dis = min(Math.abs(min_dis), Math.abs(n1_index - n2_index));
                }
            }
            if (arr[i] == b) {
                n2_index = i;
                if (n1_index >= 0) {
                    min_dis = min(Math.abs(min_dis), Math.abs(n1_index - n2_index));
                }

            }
        }
        return min_dis;
    }
//   找到数组中第一个出现的给定的数，这个数组相邻两个数的差值为1
    public int finIndex(int[] arr,int t){
        if(arr==null){
            return -1;
        }
        int len= arr.length;
        int i=0;
        while (i<len){
            if(arr[i]==t){
                return i;
            }else{
                i=i+Math.abs(t-arr[i]);
            }
        }
        return -1;
    }
//    计算两个有序整形数组的交集
    public ArrayList<Integer> findCommon(int[] a,int[] b){
        ArrayList<Integer> list=new ArrayList<>();
        int i=0;
        int j=0;
        int len1=a.length;
        int len2=b.length;

        while (i<len1&&j<len2){
            if(a[i]==b[j]){
                list.add(a[i]);
            }else if(a[i]<b[j]){
                i++;
            }else{
                j++;
            }
        }
        return list;
    }
//    求解数组中反序对的个数
    private static int reverseCount=0;
    public static int reverseCount(int[] arr){
        mergeSort(arr,0,arr.length-1);
        return reverseCount;
    }

//    寻找最小三元组的距离
    public int minDistance(int[] a,int[] b,int[] c){
        int alen=a.length;
        int blen=b.length;
        int clen=c.length;

        return 0;
    }

//    三元素的最小值
    public static int min(int a,int b,int c){
        int min1=a>b?b:a;
        return min1<c?min1:c;
    }
//    三元素的最大值
    public static int max(int a,int b,int c){
        int max1=a>b?a:c;
        return max1>c?max1:c;
    }




    public static void mergeSort(int[] arr,int l,int r){
        if(l<r){
            int mid=(r+l)/2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid+1,r);
            merge(arr,l,mid,r);
        }
    }


    //   归并排序的一部分，将一个数组的两个有序部分[l,mid],[mid+1,l]
    public static void merge(int[] arr, int l, int mid, int r) {
        int len = r - l + 1;
        int[] aux = new int[len];
        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] > aux[j - l]) {
                arr[k] = aux[j - l];
                reverseCount+=(mid-k+1);
                j++;
            } else {
                arr[k] = aux[i - l];
                i++;
            }
        }
    }

    //    快速排序partition部分
    public int partion(int[] arr, int l, int r) {
        int random = (int) (Math.random()) * (r - l + 1) + l;
        swap(arr, random, r);
        int j = l;
        //        arr[l+1...j]<temp,arr[j+1..i)>temp
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] > arr[l]) {
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, j, l);
        return j;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static int min(int a, int b) {
        return a > b ? b : a;
    }

    public static void main(String[] args) {
        int arr[]={1,5,3,2,6};
        int result=reverseCount(arr);
        for (int a:arr){
            System.out.print(a+" ");
        }
        System.out.println();
        System.out.println(result);
    }
}
