package com.jchen.algorithm.leetcode;

import java.util.*;

/**
 * Created by jchen on 17-5-9.
 * 简单一
 */
public class leetCode1 {

    //    leetcode:561
    public int arrayPairSum(int[] nums) {
        int sums = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            sums += nums[i];
        }
        return sums;
    }

    //    leetcode:566　将一个二维数组变换成另外一个r*c的二维数组
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int hang = nums.length;
        int lie = nums[0].length;
        if (hang * lie != r * c) {
            return nums;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            result[i / c][i % c] = nums[i / lie][i % lie];
        }
        return result;
    }

    //leetcode:476 将一个数写成二进制(不算前面的零位)，将各位翻转
    //左移( << )、右移( >> ) 、无符号右移( >>> ) 、位与( & ) 、位或( | )、位非( ~ )、位异或( ^ )，除了位非( ~ )是一元操作符外，其它的都是二元操作符
    public int findComplement(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        num = ~num;
        return num & mask;
    }

    //leetcode:577 将字符串中的每个单词进行反转
    public String reverseWords(String s) {
        String[] string = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length; i++) {
            char[] temp = string[i].toCharArray();
            int l = 0;
            int r = temp.length - 1;
            while (l < r) {
                char t = temp[l];
                temp[l] = temp[r];
                temp[r] = t;
                r--;
                l++;
            }
            if (i != string.length - 1)
                result.append(new String(temp) + " ");
            else
                result.append(new String(temp));
        }
        return new String(result);

    }

    //leetcode:575
    public int distributeCandies(int[] candies) {
        int len = candies.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            set.add(candies[i]);
        }
        int size = set.size();
        if (size > len / 2) {
            return len / 2;
        } else {
            return size;
        }
    }

    //leetcode:500
    public String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            for (char c : strs[i].toCharArray()) {
                map.put(c, i);//put <char, rowIndex> pair into the map
            }
        }
        List<String> res = new LinkedList<>();
        for (String w : words) {
            if (w.equals("")) continue;
            int index = map.get(w.toUpperCase().charAt(0));
            for (char c : w.toUpperCase().toCharArray()) {
                if (map.get(c) != index) {
                    index = -1; //don't need a boolean flag.
                    break;
                }
            }
            if (index != -1) res.add(w);//if index != -1, this is a valid string
        }
        return res.toArray(new String[0]);
    }

    //leetcode:412
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(new Integer(i).toString());
            }
        }
        return res;
    }

    //leetcode:496
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] res=new int[findNums.length];
        for(int i=0;i<findNums.length;i++){
            int index=0;
            for(int j=0;j<nums.length;j++){
                if(nums[j]==findNums[i]){
                    index=j;
                    break;
                }
            }
            int k=index;
            for(;k<nums.length;k++){
                if(nums[k]>findNums[index]){
                    res[i]=nums[k];
                }
            }
            if(k==nums.length){
                res[i]=-1;
            }
        }
        return res;
    }

    //leetcode:463 不重不漏
    public int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }

    //leetcode:485 一个零和一的数组，找到连续1的最大值
    public int findMaxConsecutiveOnes(int[] nums) {
        int result=0;
        int l=0;
        int r=0;
        while (true){
            while(r<nums.length&&nums[r]==0){
                r++;
            }
            l=r;
            while (r<nums.length&&nums[r]==1){
                r++;
            }
            result=result>r-l?result:r-l;
            if(r>=nums.length){
                break;
            }
        }
        return result;
    }


}

