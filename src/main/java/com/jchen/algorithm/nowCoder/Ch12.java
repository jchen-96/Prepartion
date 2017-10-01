package com.jchen.algorithm.nowCoder;

/**
 * Created by jchen on 17-8-18.
 * 动态规划相关
 */
public class Ch12 {
    /**
     * 找钱
     */
    public int findResutl(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        memo1 = new int[arr.length][1 + sum];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= sum; j++) {
                memo1[i][j] = -1;
            }
        }
        return findRemain(arr, 0, sum);
    }

    //    暴力搜索
    private int process(int[] arr, int index, int aim) {
        int res;
        if ((arr.length - 1) == index) {
            res = (aim % arr[index]) == 0 ? 1 : 0;
        } else {
            res = 0;
            int k = aim / arr[index];
            for (int i = 0; i <= k; i++) {
                res += process(arr, index + 1, aim - i * arr[index]);
            }
        }
        return res;
    }

    //  记忆搜索[index ... n-1]完成aim
    private int findRemain(int[] arr, int index, int aim) {
        if (memo1[index][aim] != -1) {
            return memo1[index][aim];
        }
        int res = 0;
        if (index == arr.length) {
            res=aim==0?1:0;
        } else {
            res = 0;
            int k = aim / arr[index];
            for (int i = 0; i <= k; i++) {
                if (memo1[index + 1][aim] == -1) {
                    memo1[index + 1][aim - arr[index] * i] = findRemain(arr, index + 1, aim - arr[index] * i);
                }
                res += memo1[index + 1][aim - arr[index] * i];
            }
        }
        memo1[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    private int[][] memo1;

    //动态规划解法

    /**
     * 若arr数组的长度为N,生成行数为N,列数为aim+1的数组
     * dp[i][j]表示在使用arr[0...i]的情况下组成钱数j有多少种方法
     */
    private int count2(int[] arr, int aim) {
        if (arr == null || aim < 0) {
            return 0;
        }
        int[][] memo = new int[arr.length][aim + 1];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < aim + 1; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            memo[i][0] = 1;
        }
        for (int i = 0; i < aim + 1; i++) {
            if (i % arr[0] == 0) {
                memo[0][i] = 1;
            } else {
                memo[0][i] = 0;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < aim + 1; j++) {
                if (j - arr[i] >= 0) {
                    memo[i][j] = memo[i - 1][j] + memo[i][j - arr[i]];
                } else {
                    memo[i][j] = memo[i - 1][j];
                }
            }
        }

        return memo[arr.length - 1][aim];
    }




    /**
     * 寻找两个字符串的公共子序列
     */
    //获取[0...m] [0 ... n] 的最长公共子序列长度
    public int findLCS(char[] s1, char[] s2) {
        if (s1 == null || s2 == null || s1.length == 0 || s2.length == 0) {
            return 0;
        }
        int m = s1.length;
        int n = s2.length;

//        dp[m][n] 保存[0...m][0...n]的最长公共子序列
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            int temp=0;
            if (s2[0] == s1[i]) {
                temp = 1;
            }
            dp[i][0] = temp;
        }
        for (int i = 0; i < n; i++) {
            int temp=0;
            if (s1[0] == s2[i]) {
                temp = 1;
            }
            dp[0][i] = temp;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int temp = Math.max(dp[i - 1][j], dp[i][j-1]);
                if (s1[i] == s2[j]) {
                    temp = Math.max(dp[i - 1][j - 1] + 1,temp);
                }
                dp[i][j] = temp;
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * ０１背包问题,每件物品只能使用一次
     */

    public int maxValue(int[] weight, int[] value, int capacity) {
        if (capacity <= 0 || weight == null || value == null || weight.length == 0 || weight.length != value.length) {
            return 0;
        }

        int n = weight.length;

        //dp[i][j]表示[0...i]件物体，重量不超过j的最大价值
        int[][] dp = new int[n][capacity + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= capacity; i++) {
            if (capacity < weight[i]) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = value[i];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j - weight[i] >= 0)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n - 1][capacity];


    }

    /**
     * 编辑字符串的最低代价
     */
    public int minPrice(char[] s1, char[] s2, int insert, int delete, int replace) {
        if (s1 == null || s2 == null) {
            return 0;
        }

        int m = s1.length + 1;
        int n = s2.length + 1;

//        dp[i][j]表示str[0...i-1]变成str2[0....j-1]的最小代价
        int[][] dp = new int[m + 1][n + 1];

        dp[0][0] = 0;

        for (int i = 1; i < m; i++) {
            dp[i][0] = delete * i;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = insert * i;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int temp1 = 0, temp2 = 0, res;
                temp1 = Math.min(dp[i][j - 1] + insert, dp[i - 1][j] + delete);
                if (s1[i - 1] == s2[j - 1]) {
                    temp2 = dp[i - 1][j - 1];
                } else {
                    temp2 = dp[i - 1][j - 1] + replace;
                }
                res = Math.min(temp1, temp2);
                dp[i][j] = res;
            }
        }

        return dp[m][n];


    }
}
