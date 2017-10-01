package com.jchen.algorithm.mooc2;

import java.util.List;

/**
 * Created by jchen on 17-8-11.
 */
public class Chapter9 {
    /**
     * 9.1斐波拉切数列
     */
    public int fib(int n) {
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = -1;
        }
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * 9.2跳台阶
     * leetCode:
     */
    public int climbStair(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    /**
     * 9.3 整数分解获得最大值
     * leetCode:279,91,62,63,
     */
    public int integerBreak(int n) {
        memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return breakInteger(n);
    }

    public int integerBreak2(int n) {
        memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                memo[i] = max(memo[i], j * (i - j), memo[i - j] * j);
            }
        }
        return memo[n];

    }

    private int[] memo;

    // 将n进行分割(至少分割两部分), 可以获得的最大乘积
    private int breakInteger(int n) {
        if (n == 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            res = max(res, i * (n - i), i * breakInteger(n - 1));
        }
        memo[n] = res;
        return res;
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * 9.4 leetCode:198
     * leetCode:213,337,309,
     */

    int[] memo4;

    public int rob(int[] nums) {
        memo4 = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            memo4[i] = -1;
        }

        return tryRob(nums, 0);
    }

    private int tryRob(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        if (memo4[start] != -1) {
            return memo4[start];
        }
        int res = 0;
        for (int i = start; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        memo4[start] = res;
        return res;
    }

    public int rob2(int[] nums) {
        memo4 = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            memo4[i] = -1;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                memo4[i] = Math.max(memo4[i], nums[j] + (j + 2 < nums.length ? nums[j + 2] : 0));
            }
        }
        return memo4[0];
    }

    /**
     * 9.5
     * 0,1背包问题()
     * leetCode:
     */
    public int maxBag(int capacity, int[] weight, int[] value) {
        assert (weight.length == value.length && capacity >= 0);
        int n = weight.length;
        if (n == 0 || capacity == 0) {
            return 0;
        }
        memo5 = new int[n][capacity + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                memo5[i][j] = -1;
            }
        }
        return findMore(weight, value, n - 1, capacity);
    }

    public int maxBag2(int C, int[] w, int[] v) {
        assert (w.length == v.length && C >= 0);
        int n = w.length;
        if (n == 0 || C == 0)
            return 0;
        memo5 = new int[n][C + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                memo5[i][j] = -1;
            }
        }

        for (int j = 0; j <= C; j++)
            memo5[0][j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i < n; i++)
            for (int j = 0; j <= C; j++) {
                memo5[i][j] = memo5[i - 1][j];
                if (j >= w[i])
                    memo5[i][j] = Math.max(memo5[i][j], v[i] + memo5[i - 1][j - w[i]]);
            }
        return memo5[n - 1][C];

    }

    private int[][] memo5;

    // 用 [0...index]的物品,填充容积为c的背包的最大价值
    private int findMore(int[] weight, int[] value, int index, int c) {
        if (c <= 0 || index < 0) {
            return 0;
        }
        if (memo5[index][c] != -1) {
            return memo5[index][c];
        }
        int res = findMore(weight, value, index - 1, c);
        if (c >= weight[index]) {
            res = Math.max(findMore(weight, value, index - 1, c - weight[index] + value[index]), res);
        }
        memo5[index][c] = res;
        return res;
    }

    /**
     * 9.7 (416)
     * leetCode:322,377,474,139,494
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        memo6 = new int[nums.length][sum / 2 + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                memo6[i][j] = -1;
            }
        }

        return fill(nums.length - 1, sum / 2, nums);

    }

    private int[][] memo6;

    //    用nums[0,...,index]填满容量c
    private boolean fill(int index, int c, int[] nums) {
        if (c == 0) {
            return true;
        }
        if (c < 0 || index < 0) {
            return false;
        }
        if (memo6[index][c] != -1) {
            return memo6[index][c] == 1;
        }

        boolean res = fill(index - 1, c, nums) || fill(index - 1, c - nums[index], nums);

        memo6[index][c] = res ? 1 : 0;
        return memo6[index][c] == 1;

    }

    /**
     * 最长上升子序列问题(300)
     * leetCode:376
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        memo7 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo7[i] = -1;
        }
        int res = 1;
        for (int i = 0; i < memo7.length; i++) {
            res = Math.max(res, getMaxLength(nums, i));
        }
        return res;

    }

    private int[] memo7;

    //以 nums[index] 为结尾的最长上升子序列的长度
    private int getMaxLength(int[] nums, int index) {
        if (memo7[index] != -1) {
            return memo7[index];
        }
        int res = 1;
        for (int i = 0; i < index; i++) {
            if (nums[index] > nums[i]) {
                res = Math.max(res, 1 + getMaxLength(nums, i));
            }
        }
        memo7[index] = res;
        return res;
    }

    /**
     * 最长公共子序列
     */
    // 求s1[0...m]和s2[0...n]的最长公共子序列的长度值
    private int __LCS(char[] s1, char[] s2, int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }
        if (memo8[m][n] != -1) {
            return memo8[m][n];
        }
        int res = 0;
        if (s1[m] == s2[n]) {
            res = 1 + __LCS(s1, s2, m - 1, n - 1);
        } else {
            res = Math.max(__LCS(s1, s2, m - 1, n), __LCS(s1, s2, m, n - 1));
        }
        memo8[m][n] = res;
        return res;
    }

    //    通过memo反向求解s1和s2的最长公共子序列
    private char[] __getLCS(char[] s1, char[] s2) {
        int m = s1.length - 1;
        int n = s2.length - 1;

        StringBuilder s = new StringBuilder();

        while (m >= 0 && n >= 0) {
            if (s1[m] == s2[n]) {
                s.append(s1[m]);
                m--;
                n--;
            } else if (m == 0) {
                n--;
            } else if (n == 0) {
                m--;
            } else {
                if(memo8[m-1][n]>memo8[m][n-1]){
                    m--;
                }else{
                    n--;
                }
            }
        }

        return s.reverse().toString().toCharArray();
    }

    private int[][] memo8;

    public String getLCS(String s1, String s2) {
        memo8 = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo8[i][j] = -1;
            }
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        __LCS(str1, str2, str1.length - 1, str2.length - 1);

        return new String(__getLCS(str1, str2));


    }


}
