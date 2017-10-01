package com.jchen.algorithm.LintCode;

/**
 * Created by jchen on 17-9-9.
 * lintCode的字符串问题
 */
public class StringProblem {


    //交叉字符串
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) return s3.equals(s1 + s2);

        if (s1.length() + s2.length() != s3.length()) return false;

        int row = s1.length() + 1, col = s2.length() + 1;
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
//        len[i][j]表示s1的前i个元素和s2的前j个元素，是否可以表示s3的前i+j个元素
        int[][] len = new int[row][col];

        for (int i = 1; i < row; i++) if (c1[i - 1] == c3[i - 1]) len[i][0] = len[i - 1][0] + 1;

        for (int i = 1; i < col; i++) if (c2[i - 1] == c3[i - 1]) len[0][i] = len[0][i - 1] + 1;

        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++) {
                int i3 = i + j - 1, i1 = i - 1, i2 = j - 1;

                if (c3[i3] == c1[i1] && c3[i3] == c2[i2]) len[i][j] = Math.max(len[i1][j], len[i][i2]) + 1;

                else if (c3[i3] == c1[i1]) len[i][j] = len[i1][j] + 1;

                else if (c3[i3] == c2[i2]) len[i][j] = len[i][i2] + 1;
            }

        return len[row - 1][col - 1] == c3.length;
    }

    //    LCS问题
//    public int longestCommonSubsequence(String A, String B) {
//        // write your code here
//        int i=A.length();
//        int j=B.length();
//        int res=0;
//        if(A.charAt(i-1)==B.charAt(j-1)){
//            res++;
//            return res+longestCommonSubsequence(A.substring(0,A.length()-1),B.substring(0,B.length()-1));
//        }
//
//        return Math.max(longestCommonSubsequence(A.substring(0,A.length()),B.substring(0,B.length()-1))),longestCommonSubsequence(A.substring(0,A.length()),B.substring(0,B.length()-1));
//
//    }


    public static void main(String[] args) {

    }
}
