package com.jchen.algorithm.nowCoder;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by jchen on 17-8-13.
 * 栈和队列专题
 */
public class Ch04 {

    /**
     * 给定一个整形数组,和一个未w的窗口，返回一个长度未n-w+1的数组，为滑动过程中的数组的最大值
     */
    public int[] getMaxArr(int[] arr,int w){
        if(w==1){
            return arr;
        }
        int[] res=new int[arr.length-w+1];
        int k=0;
        Deque<Integer> deque=new LinkedList<>();
        for (int i=0;i<arr.length;i++){
            if(deque.isEmpty()||arr[i]<arr[deque.getLast()]){
                deque.addLast(i);
            }else{
                while (!deque.isEmpty()&&arr[i]>arr[deque.getLast()]){
                    deque.removeLast();
                }
                deque.addLast(i);
            }
            if(i<w-1){
                continue;
            }
            while ((i-deque.getFirst())>=w){
                deque.removeFirst();
            }
            res[k++]=arr[deque.getFirst()];
        }
        return res;
    }
    /**
     * 给定一个没有重复元素的数组arr,写出生成这个数组的maxTree函数
     */
//    public

    /**
     * 对一个栈中的元素进行排序
     */
    public void sort(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int i = stack.pop();
            if (help.isEmpty()) {
                help.push(i);
                continue;
            }
            if (i > help.peek()) {
                while (i > help.peek()) {
                    stack.push(help.pop());
                }
                help.push(i);
            } else {
                help.push(i);
            }

        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    /**
     * 实现一个栈的逆序操作
     * 实际就是利用递归回溯的过程
     */
    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = get(stack);
        reverse(stack);
        stack.push(i);
    }

    //移除栈顶的元素并返回
    public int get(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = get(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * 测试主函数
     */
    public static void main(String[] args) {
        int[] arr=new Ch04().getMaxArr(new int[]{4,3,2,6,5,1,9,10,8},3);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();

    }

}

/**
 * 用两个队列实现一个栈
 */
class MyQueue {
    private Stack<Integer> stackpush;

    private Stack<Integer> stackpop;

    public MyQueue() {
        stackpop = new Stack<>();
        stackpush = new Stack<>();
    }

    public void push(Integer data) {
        stackpush.push(data);
    }

    public void pop() {
        if (!stackpop.isEmpty()) {
            stackpop.pop();
        } else {
            while (!stackpush.isEmpty()) {
                stackpop.push(stackpush.pop());
            }
        }
    }


}
