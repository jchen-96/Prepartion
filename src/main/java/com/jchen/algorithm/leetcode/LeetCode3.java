package com.jchen.algorithm.leetcode;

import java.util.*;

/**
 * Created by jchen on 17-5-21.
 */
public class LeetCode3 {
    //leetCode:455
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for (int j = 0; i < g.length && j < s.length; j++) {
            if (g[i] <= s[j]) {
                i++;
            }
        }
        return i;
    }


    //leetcode:506
    public String[] findRelativeRanks(int[] nums) {
        if (nums == null) return null;
        int max = max(nums);
        int[] rankAll = new int[max + 1];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            rankAll[nums[i]] = i + 1;
        }

        int[] rank = new int[len];
        int r = 1;
        for (int i = rankAll.length - 1; i >= 0; i--) {
            if (rankAll[i] != 0) {
                rank[rankAll[i] - 1] = r++;
            }
        }

        String[] finalRank = new String[len];
        for (int i = 0; i < len; i++) {
            if (rank[i] == 1) finalRank[i] = "Gold Medal";
            else if (rank[i] == 2) finalRank[i] = "Silver Medal";
            else if (rank[i] == 3) finalRank[i] = "Bronze Medal";
            else finalRank[i] = String.valueOf(rank[i]);
        }
        return finalRank;
    }

    private int max(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = nums[i] > max ? nums[i] : max;
        }
        return max;
    }

    //    leetCode:617合并两个二叉树
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);

        TreeNode result = new TreeNode(val);

        result.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        result.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

        return result;
    }

    //leetCode:606将一个二叉树转化成一个带括号的字符串
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        String result = t.val + "";

        String left = tree2str(t.left);
        String right = tree2str(t.right);

        if (left == "" && right == "") return result;
        if (left == "") return result + "()" + "(" + right + ")";
        if (right == "") return result + "(" + left + ")";
        return result + "(" + left + ")" + "(" + right + ")";
    }

    //    leetCode:599
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new LinkedList<>();
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
        for (int i = 0; i < list2.length; i++) {
            Integer j = map.get(list2[i]);
            if (j != null && i + j <= minSum) {
                if (i + j < minSum) {
                    res = new LinkedList<>();
                    minSum = i + j;
                }
                res.add(list2[i]);
            }
        }
        return res.toArray(new String[res.size()]);
    }

    //leetCode:598
    public int maxCount(int m, int n, int[][] ops) {
        if (ops == null && ops.length == 0) {
            return n * m;
        }
        int hang = Integer.MAX_VALUE;
        int lie = Integer.MAX_VALUE;
        for (int[] op : ops) {
            hang = Math.min(hang, op[0]);
            lie = Math.min(lie, op[1]);
        }
        return hang * lie;
    }

    //    leetCode:530
    //    进行中序便遍历的时候，保存当前节点和前一个节点
    int min = Integer.MAX_VALUE;
    Integer pre = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }
        getMinimumDifference(root.left);
        if (pre != null) {
            min = Math.min(min, root.val - pre);
        }
        pre = root.val;
        getMinimumDifference(root.right);
        return min;
    }

    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        int result = 0;
        for (int num : nums) {
            result += (num - min);
        }
        return result;
    }

    //    leetCode:
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    private void convert(TreeNode node) {
        if (node == null) {
            return;
        }
        convert(node.right);
        node.val += sum;
        sum = node.val;
        convert(node.left);
    }

    public int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int k = s.length() - i;
            sum += ((s.charAt(i) - 'A') + 1) * (Math.pow(26, k - 1));
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode3 leetCode3 = new LeetCode3();
        System.out.print(leetCode3.titleToNumber("AB"));
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        return judge(sequence, 0, sequence.length - 1);
    }

    private boolean judge(int[] sequence, int start, int end) {
        if (sequence == null || start > end) {
            return false;
        }
        int i = start;
        int root = sequence[end];
        for (; i <end; i++) {
            if (root < sequence[i]) {
                break;
            }
        }
        int j = i;
        for (; j < end; j++) {
            if (root > sequence[j]) {
                return false;
            }
        }
        boolean left = true;
        if (start < i - 1) {
            left = judge(sequence, start, i - 1);
        }
        boolean right = true;
        if (i < end - 1)
            right = judge(sequence, i, end - 1);
        return left&&right;
    }
}

