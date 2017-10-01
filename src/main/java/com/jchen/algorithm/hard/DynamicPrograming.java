package com.jchen.algorithm.hard;

import java.util.Arrays;
import java.util.List;

/**
 * 动态规划经典问题
 */
public class DynamicPrograming {
    /**
     * 动态规划:
     * 将原问题拆解成若干子问题，同时保留子问题的答案，使得每个子问题只求解一次，最终获得原问题的答案
     */
    //求最小的路径和120

    /**
     * 给定一个三角形
     * 选择一条自顶向下的路径使得沿途的所有数字之和最小:120
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return -1;
        }
        for (int j = 0; j < triangle.size(); j++) {
            if (triangle.get(j).size() != 1) {
                for (int i = 0; i < triangle.get(j).size(); i++) {
                    int left = i - 1;
                    int right = i;
                    if (left < 0) {
                        triangle.get(j).set(i, triangle.get(j - 1).get(right) + triangle.get(j).get(i));
                    } else if (right >= triangle.get(j - 1).size()) {
                        triangle.get(j).set(i, triangle.get(j - 1).get(left) + triangle.get(j).get(i));
                    } else {
                        int min = Math.min(triangle.get(j - 1).get(left), triangle.get(j - 1).get(right));
                        triangle.get(j).set(i, min + triangle.get(j).get(i));
                    }

                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int k = 0; k < triangle.get(triangle.size() - 1).size(); k++) {
            res = Math.min(res, triangle.get(triangle.size() - 1).get(k));
        }
        return res;
    }
    // 给定和一个m*n的矩阵，其中每一个格子包含一个非负整数，寻找一条从左上角到右下角的路径，使得沿途的数字和最下 64

    public int minPathSum(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];

    }

    //给定一个非负整数n可以将其分割成多个数字的和，若要让这些数字的乘积最呆，求分割方法，这个最大乘积

    public int integerBreak(int n) {
        if (n <= 1) {
            return -1;
        }
        int dp[] = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int res = 0;
            for (int j = 1; j < n; j++) {
                res = Math.max(dp[j] * (i - j), res);
                res = Math.max(res, j * (i - j));
            }
            dp[i] = res;
        }
        return dp[n];
    }

    //279:给定一个正整数，寻找最少的完全平方数，使得他们的和为n
    public int numSquares(int n) {
        if (((int) Math.sqrt(n)) * ((int) (Math.sqrt(n))) == n) {
            return 1;
        }
        int[] dp = new int[n + 1];

        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (((int) Math.sqrt(i)) * ((int) (Math.sqrt(i))) == i) {
                dp[i] = 1;
                continue;
            }
            int res = Integer.MAX_VALUE;

            for (int j = 1; j < i; j++) {
                res = Math.min(dp[j] + dp[i - j], res);
            }
            dp[i] = res;


        }
        return dp[n];

    }

    //91:给定一个字符串，包含a-z的字母，每一字母对应1-26,给定一个数字字符串，求出有多少种方法将该数字字符串，解析成字母字符串
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    //62:一个机器人，只能向下或者向右走，求一共有多少种不同的路径

    //63:62的基础上有一定的障碍物

    //偷取财宝，不能连续偷两个
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length <= 2) {
            if (nums.length == 1) {
                return nums[0];
            } else {
                return Math.max(nums[0], nums[1]);
            }
        }
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[2] + nums[0];

        for (int i = 3; i < nums.length; i++) {
            dp[i] = nums[i] + Math.max(dp[i - 2], dp[i - 3]);
        }

        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }
    //上述数组是环形的　213

    //小区是一个二叉树结构，选取不相邻的节点 337

    //买股票　309


    /**
     * 0,1背包问题
     */


    public int maxValue(int c, int[] v, int[] w) {
        if (c <= 0 || v == null || w == null || v.length != w.length) {
            return -1;
        }


        //dp[i][j] 填充前i件物品，填充空间为j的最大值
        int[][] dp = new int[v.length][c + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < dp[0].length; j++) {
            if (j >= w[0]) {
                dp[0][j] = v[0];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    //完全背包
    //多重背包


    /**
     * 给定一个非空数组，问是否可以将这个数组的元素分成两部分
     */
    public boolean canDivided(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int half = 0;
        if (sum % 2 == 0) {
            half = sum / 2;
        } else {
            return false;
        }
        //用前i个数组成和为j
        boolean[] dp=new boolean[half+1];

        for (int i=0;i<=half;i++){
            dp[i]=(arr[0]==i);
        }

        for (int i=1;i<arr.length;i++){
            for (int j=half;j>=arr[i];j--){
                dp[j]=dp[j]||dp[j-arr[i]];
            }
        }
        return dp[half];

    }

    /**
     * 最长公共子序列
     */



    public static void main(String[] args) {
        System.out.println(new DynamicPrograming().maxValue(5, new int[]{6, 10, 12}, new int[]{1, 2, 3}));

    }


}
