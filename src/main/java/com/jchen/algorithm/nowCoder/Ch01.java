package com.jchen.algorithm.nowCoder;

import java.util.*;

/**
 * Created by jchen on 17-8-11.
 * 字符串和二叉树问题
 */
public class Ch01 {
    /**
     * 二叉树的层序便利，同时按层打印
     */

    public List<List<Integer>> printByLevel(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> res = new ArrayList<>();

        TreeNode last = null;
        TreeNode nlast = null;
        TreeNode now = null;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        last = root;
        while (!queue.isEmpty()) {
            now = queue.remove();
            res.add(now.val);
            if (now.left != null) {
                queue.add(now.left);
                nlast = now.left;
            }
            if (now.right != null) {
                queue.add(now.right);
                nlast = now.right;
            }
            if (now == last) {
                result.add(new ArrayList<>(res));
                res.clear();
                last = nlast;

            }

        }

        return result;
    }

    /**
     * 二叉树的序列化和反序列化
     * 序列化和反序列化的自定义规则
     * 节点为空 #! 节点不为空　val!
     */
    public String serialAble(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        StringBuilder res = new StringBuilder();
        TreeNode head = root;
        preOrder(head, res);
        return res.toString();
    }

//    public TreeNode Dseialable(String[] arr,int index){
//        int i,j,value=0;
//        if(index>=arr.length||arr[index]=="#"){
//            return null;
//        }
//        i=j=index;
//        while (arr[j]!="!"){
//            j++;
//        }
//        while (j>i){
//            value=(Integer.valueOf(arr[i]));
//            i++;
//        }
//        TreeNode node=new TreeNode(value);
//        index=i+1;
//        node.left=Dseialable(arr,)
//
//    }

    private void preOrder(TreeNode root, StringBuilder s) {
        if (root != null) {
            s.append(root.val + "!");
        } else {
            s.append("#!");
            return;
        }
        preOrder(root.left, s);
        preOrder(root.right, s);
    }

    /**
     * 给定str1和str2判断st1是否是str２的旋转词
     * contain算法使用了kmp算法
     */
    public boolean isRoller(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String temp = s1 + s1;
        return (temp.contains(s2));
    }

    /**
     * 给定一个字符串将单词反转，是单词不是字符
     */
    public String reverse(String s) {
        char[] temp = s.toCharArray();
        reverse(temp);
        s = new String(temp);
        String[] res = s.split(" ");
        for (int i = 0; i < res.length; i++) {
            char[] chars = reverse(res[i].toCharArray());
            res[i] = new String(chars);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            result.append(res[i] + " ");
        }
        return new String(result);
    }

    private char[] reverse(char[] s) {
        int len = s.length;
        int i = 0, j = len - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
        return s;
    }

    /**
     * 给定一个字符串类型的数组，将其拼接起来使得字典序最小
     */
    public String getMinString(String[] s) {
        Arrays.sort(s, new myComparator());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            result.append(s[i]);
        }
        return new String(result);
    }




}

/**
 * 重写比较器，传入o1 02 返回值大于零表示o1>o2 返回值小于零表示o1<o2
 */
class myComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return (o1 + o2).compareTo(o2 + o1);
    }
}
