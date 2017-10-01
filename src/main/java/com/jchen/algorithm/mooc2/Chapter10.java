package com.jchen.algorithm.mooc2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jchen on 17-8-11.
 */
public class Chapter10 {
    /**
     * 贪心分饼干(４５５)
     * leetCode:392,
     */
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

    /**
     * 删除区间使得重叠最小(435)
     */


    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, new myComparator());
        int[] dp=new int[intervals.length];

        dp[0]=1;

        for (int i=1;i<dp.length;i++){
            int res=1;
            for (int j=0;j<i;j++){
                if(intervals[j].end<=intervals[i].start){
                    res=Math.max(res,dp[j]+1);
                }
            }
            dp[i]=res;
        }

        return intervals.length-dp[intervals.length-1];

    }

}

class myComparator implements Comparator<Interval> {
    public int compare(Interval a, Interval b){
        if(a.start!=b.start){
            return a.start-b.start;
        }else{
            return a.end-b.end;
        }
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
