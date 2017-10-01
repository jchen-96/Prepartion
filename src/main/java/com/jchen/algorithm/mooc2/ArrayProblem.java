package com.jchen.algorithm.mooc2;


import java.util.List;

/**
 * Created by jchen on 17-5-5.
 */
public class ArrayProblem {
        /**
         * 二分查找
         */
        public static int BinargSearch(int[] arr, int n, int target) {
            int l = 0;
            int r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return -1;
        }

        /**
         * leetcode283:将一个数组中所有的零元素移动到数组的末尾,其他元素的相对位置不变
         */
        public static void moveZeros1(int[] arr) {
            /**
             * 采用两个指针,其中一个指向非零元素,一个指向零元素
             */
            int len = arr.length;
            int j = 0;
            int i = 0;
            while (j < len) {
                while (arr[j] == 0 && j < len - 1) {
                    j++;
                }
                arr[i] = arr[j];
                i++;
                j++;
            }
            while (i < len) {
                arr[i++] = 0;
            }

        }

        public static void moveZeros2(int[] arr) {
            /**
             * 交换零元素和非零元素,省去了后来的置零操作
             */
            int len = arr.length;
            int j = 0;
            int i = 0;
            while (j < len) {
                while (arr[j] == 0 && j < len - 1) {
                    j++;
                }
                if (i != j) {
                    int tem = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tem;
                }
                i++;
                j++;
            }
        }

        /**
         * leetcode:27:给定一个数组和一个数值,讲将数组中所有值等于该数值的元素删除,并返回剩余元素的个数给定一个数组和一个数值,讲将数组中所有值等于该数值的元素删除,并返回剩余元素的个数
         */
        public int removeElement(int[] nums, int val) {
            /**
             * 采用指针对撞
             */
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            int l = 0;
            int r = len - 1;
            while (l <= r) {
                if (l == r) {
                    if (nums[r] == val) {
                        return l;
                    } else {
                        return l + 1;
                    }
                }
                while (nums[r] == val && r > 0) {
                    r--;
                }
                while (nums[l] != val && l < len - 1) {
                    l++;
                }
                if (l < r) {
                    int temp = nums[l];
                    nums[l] = nums[r];
                    nums[r] = temp;
                    l++;
                    r--;
                }
            }
            return l;
        }

        /**
         * leetcode:26:给定一已经排序了的数组,对数组中的元素进行去重,使得原数组中的每个元素只有一个.返回去重后的数组长度n,并保证原数组的前n个数就是去重后的数组，其余的任意
         */
        public int removeDuplicates1(int[] nums) {
            int len = nums.length;
            if (len <= 1) {
                return len;
            }
            int j = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] != nums[j]) {
                    nums[++j] = nums[i];
                }
            }
            return ++j;
        }

        /**
         * leetcode:80:给定一个数组,对数组中的元素进行去重,使得原数组中的每个元素最多只用两个.返回去重后的属数组长度
         */
        public int removeDuplicates(int[] nums) {
            int i = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i < 2 || nums[j] > nums[i - 1]) {
                    nums[i++] = nums[j];
                }
            }
            return i;
        }

        /**
         * leetcode:75:给定一个数组，只用三种数0,1,2进行排序
         */
        public void sortColors1(int[] nums) {
            /**
             *采用计数排序
             */
            int[] arr = {0, 0, 0};
            for (int i = 0; i < nums.length; i++) {
                arr[nums[i]]++;
            }
            int index = 0;
            for (int j = 0; j < arr[0]; j++) {
                nums[index++] = 0;
            }
            for (int j = 0; j < arr[1]; j++) {
                nums[index++] = 0;
            }
            for (int j = 0; j < arr[2]; j++) {
                nums[index++] = 0;
            }
        }

        public void sortColors(int[] nums) {
            /**
             * 采用三路快排思想
             */
            int len = nums.length;
            int l = -1;
            int r = len;
            for (int i = 0; i < r; i++) {
                if (nums[i] == 0) {
                    int temp = nums[l + 1];
                    nums[l + 1] = nums[i];
                    nums[i] = temp;
                    l++;
                } else if (nums[i] == 2) {
                    int temp = nums[r - 1];
                    nums[r - 1] = nums[i];
                    nums[i] = temp;
                    r--;
                    i--;
                }
            }
        }

        /**
         * leetcode:88:给定两个整形数组，将nums2的元素归并到nums1中
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (nums1 == null) {
                return;
            }
            if (n == 0) {
                return;
            }
            __merge(nums1, m, nums2, n);
        }

        private void __merge(int[] num1, int m, int[] num2, int n) {
            int[] arr = new int[m + n];
            int i = 0;
            for (int j = 0; j < m; j++) {
                arr[i] = num1[j];
                i++;
            }
            for (int j = 0; j < n; j++) {
                arr[i] = num2[j];
                i++;
            }
            int left = 0;
            int right = m;
            for (int k = 0; k < m + n; k++) {
                if (left >= m) {
                    num1[k] = arr[right];
                    right++;
                } else if (right >= n + m) {
                    num1[k] = arr[left];
                    left++;
                } else if (arr[left] <= arr[right]) {
                    num1[k] = arr[left];
                    left++;
                } else {
                    num1[k] = arr[right];
                    right++;
                }
            }
        }

        /**
         * leetcode:215 给一个整数找到第K大的元素
         */
        public int findKthLargest(int[] nums, int k) {
            int length = nums.length;
            int temp = length - k;
            int l = 0;
            int r = length - 1;
            int p = partition(nums, l, r);
            while (p != temp) {
                if (p < temp) {
                    l = p + 1;
                    p = partition(nums, l, r);
                } else {
                    r = p - 1;
                    p = partition(nums, l, r);
                }
            }
            return nums[p];

        }

        private int partition(int[] arr, int l, int r) {
            int rand = (int) (Math.random() * (r - l + 1) + l);
            int base = arr[rand];
            arr[rand] = arr[l];
            arr[l] = base;
            int k = l;
            int i = l + 1;
            while (i <= r) {
                if (arr[i] <= base) {
                    swap(arr, k + 1, i);
                    k++;
                    i++;
                } else {
                    i++;
                }
            }
            swap(arr, l, k);

            return k;
        }

        private void swap(int[] a, int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        /**
         * leetcode:167 给定一个整形数组和一个target,寻找两个元素使得元素和为target,返回索引
         */
        public int[] twoSum(int[] numbers, int target) {
            int len = numbers.length;
            int[] result = {0, 0};
            int i = 0;
            int j = len - 1;
            while (i < j) {
                if (numbers[i] + numbers[j] > target) {
                    j--;
                } else if (numbers[i] + numbers[j] < target) {
                    i++;
                } else {
                    break;
                }
            }
            result[0] = i + 1;
            result[1] = j + 1;
            return result;
        }

        /**
         * leetcode:125:给定一个字符串，只看其中的数字和字母判断是否为为回文串,忽略大小写
         */
        public boolean isPalindrome(String s) {
            if (s.isEmpty()) {
                return true;
            }
            int head = 0;
            int tail = s.length() - 1;
            while (head <= tail) {
                char chead = s.charAt(head);
                char ctail = s.charAt(tail);
                if (!Character.isLetterOrDigit(chead)) {
                    head++;
                } else if (!Character.isLetterOrDigit(ctail)) {
                    tail--;
                } else {
                    if (Character.toLowerCase(chead) != Character.toLowerCase(ctail)) {
                        return false;
                    } else {
                        head++;
                        tail--;
                    }
                }
            }
            return true;

        }

        /**
         * leetcode:344给定一个字符串，返回这个字符串的倒序字符串
         */
        public String reverseString(String s) {
            char[] arr = s.toCharArray();
            int len = arr.length;
            int head = 0;
            int tail = len - 1;
            while (head < tail) {
                char temp = arr[head];
                arr[head] = arr[tail];
                arr[tail] = temp;
                head++;
                tail--;
            }
            return new String(arr);
        }

        /**
         * leetcode:345给定一个字符串,将该字符串中的元音字母进行翻转
         */
        public String reverseVowels(String s) {
            char[] arr = s.toCharArray();
            int len = arr.length;
            int i = 0;
            int j = len - 1;
            String vo = "aeiouAEIOU";
            while (i < j) {
                while (i < j && !vo.contains(arr[i] + "")) {
                    i++;
                }
                while (i < j && !vo.contains(arr[j] + "")) {
                    j--;
                }
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
            return new String(arr);
        }

        /**
         * leetcode:11给定一个非负数组,每一个整数表示一个树立在x轴的墙，求最多容纳的水
         */
        public int maxArea(int[] height) {
            int result = 0;
            int i = 0;
            int j = height.length - 1;
            while (i < j) {
                result = Math.max(result, Math.min(height[i], height[j]) * (j - i));
                if (height[i] < height[j]) {
                    i++;
                } else {
                    j--;
                }
            }
            return result;

        }


        /**
         * leetcode209 给定一个数组和一个整数，找到数组中的最短的一个连续子数组,使得其和>=s,最短长度
         */
        public int minSubArrayLen(int s, int[] nums) {
            int l = 0;
            int r = -1;
            int sum = 0;
            int len = nums.length;
            int result = len + 1;
            while (l < len) {
                if (r + 1 < len && sum < s) {
                    r++;
                    sum += nums[r];
                } else {
                    sum -= nums[l];
                    l++;
                }
                if(sum>=s){
                    result=result>r-l+1?r-l+1:result;
                }
            }
            if(result==len+1){
                return 0;
            }else{
                return result;
            }
        }

        /**
         * leetcode:3 给定一个字符串，找到没有重复字母的最长子串
         */
        public int lengthOfLongestSubstring(String s) {
            return 0;
        }

        /**
         * leetcode:438 fina all anagrams in a stirng
         */
        public List<Integer> findAnagrams(String s, String p) {
            return null;
        }

        /**
         * leetcode:76给定一个字符串s和t，在s中寻找最短的子串，包含t中所有的字符
         */
        public String minWindow(String s, String t) {
            return null;
        }

}
