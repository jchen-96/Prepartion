package com.jchen.algorithm.leetcode;

import java.util.*;

/**
 * Created by jchen on 17-5-20.
 * 简单二
 */
public class leetCode2 {
    //leetCode:136 给定一个数组，只有一个数出现一次，其他的都出现两次，找出只出现一次的数字,进行位操作
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    //leetcode:520
    public boolean detectCapitalUse(String word) {

        int count = 0;
        for (char c : word.toCharArray()) {
            if ('Z' - c >= 0) count++;
        }

        return (count == 0 || count == word.length()) || (count == 1 && 'Z' - word.charAt(0) >= 0);
    }

    //leetcode:448
    public List<Integer> findDisappearedNumbers(int[] nums) {
        boolean[] seen = new boolean[nums.length + 1];
        List<Integer> dis = new ArrayList<Integer>();
        for (int num : nums) {
            seen[num] = true;
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (seen[i] == false)
                dis.add(i);
        }
        return dis;
    }

    //leetcode:104
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int left = depth(node.left);
            int right = depth(node.right);
            return left > right ? left + 1 : right + 1;
        }
    }

    //leetcode:389 找不同，位运算
    public char findTheDifference(String s, String t) {
        char c = 0;
        for (int i = 0; i < s.length(); ++i) {
            c ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            c ^= t.charAt(i);
        }
        return c;
    }

    public char findTheDifference２(String s, String t) {
        int sSum = 0, tSum = 0;
        for (char c : s.toCharArray())
            sSum += (int) c;
        for (char c : t.toCharArray())
            tSum += (int) c;
        return (char) (tSum - sSum);
    }

    //leetcode:371 不用加号进行加法运算
    public int getSum(int a, int b) {
        return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
    }

    //leetcode:226　将二叉树进行镜像操作
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        reverse(root);
        return root;
    }

    private void reverse(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        reverse(node.left);
        reverse(node.right);
    }

    //leetcode:258　将一个数字的各位不断迭加，直到只剩一位数
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }


    //leetcode:492
    public int[] constructRectangle(int area) {
        int temp = (int) Math.sqrt(area);
        while (area % temp != 0) {
            temp--;
        }
        return new int[]{area / temp, temp};
    }

    //leetcode:563 主要使用后序遍历

    int result = 0;

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        postOrder(root);
        return result;

    }

    private int postOrder(TreeNode node) {
        if (node == null)
            return 0;

        int left = postOrder(node.left);
        int right = postOrder(node.right);

        result += Math.abs(left - right);

        return left + right + node.val;

    }
}
