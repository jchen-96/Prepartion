package com.jchen.algorithm.hard;

import com.jchen.algorithm.niukeOnline.ListNode;
import com.jchen.algorithm.niukeOnline.RandomListNode;
import com.jchen.algorithm.niukeOnline.TreeLinkNode;
import com.jchen.algorithm.niukeOnline.TreeNode;

import java.util.*;

/**
 * Created by jchen on 17-9-5.
 */
public class Collect {






        /**
         * question:6找到旋转数组的最小值
         */
        public int minNumberInRotateArray(int[] array) {
            if (array == null || array.length == 0) {
                return 0;
            }
            if (array.length == 1) {
                return array[0];
            }
            int l = 0;
            int r = array.length - 1;
            int mid;
            while (l <= r) {
                mid = l + (r - l) / 2;

                if (array[r] < array[mid]) {
                    l = mid + 1;
                } else if (array[l] > array[mid]) {
                    r = mid;
                } else {
                    break;
                }
            }
            if (l == r) {
                return array[l];
            }
            int min = array[l];

            for (int i = l; i <= r; i++) {
                min = Math.min(min, array[i]);
            }
            return min;
        }





        /**
         * question11:二进制中１的个数
         */
        public int NumberOf1(int n) {
            int count = 0;
            while (n != 0) {
                count++;
                n &= (n - 1);
            }
            return count;
        }



        /**
         * 13: 调整一个数组，实现奇数位于前半部分，偶数位于后半部分
         */
        //[0,i)放奇数，[i,j)存放偶数
        public void reOrderArray(int[] array) {
            int i = 0;
            int j = 0;
            for (; j < array.length; j++) {
                if (array[j] % 2 == 0) {
                    continue;
                }
                if (array[j] % 2 == 1) {
                    int tem = array[j];
                    for (int k = j; k > i; k--) {
                        array[k] = array[k - 1];
                    }
                    array[i] = tem;
                    i++;
                }
            }
        }








        /**
         * 17:判断root2是不是root1的子结构
         */
        public boolean HasSubtree(TreeNode root1, TreeNode root2) {
            boolean res = false;
            if (root1 != null && root2 != null) {
                if (root1.val == root2.val) {
                    res = isSame(root1, root2);
                }
                if (!res) {
                    res = HasSubtree(root1.left, root2);
                }
                if (!res) {
                    res = HasSubtree(root1.right, root2);
                }
            }

            return res;
        }

        //在根节点相同的情况下，判断root1是否包含root2
        private boolean isSame(TreeNode root1, TreeNode root2) {
            if (root2 == null) {
                return true;
            }
            if (root1 == null) {
                return false;
            }
            if (root1.val != root2.val) {
                return false;
            }
            return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
        }

        /**
         * 18:将一个二叉树镜像
         */
        public void Mirror(TreeNode root) {
            if (root == null) {
                return;
            }
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;


            if (root.left != null) {
                Mirror(root.left);
            }
            if (root.right != null) {
                Mirror(root.right);
            }

        }





        /**
         * 21:栈的压入，弹出序列
         */
        public boolean IsPopOrder(int[] pushA, int[] popA) {
            if (pushA == null || popA == null || pushA.length != popA.length) {
                return false;
            }
            Stack<Integer> help = new Stack<>();
            int i = 0;
            int j = 0;
            while (i < popA.length) {
                if (help.isEmpty() || help.peek() != popA[j]) {
                    help.push(pushA[i]);
                    i++;
                } else {
                    j++;
                    help.pop();
                    help.push(pushA[i]);
                    i++;
                }
            }
            while (j < popA.length && popA[j] == help.peek()) {
                help.pop();
                j++;
            }
            return help.isEmpty();
        }

        /**
         * 22:层序遍历二叉树
         */
        public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            ArrayList<Integer> res = new ArrayList<>();

            Queue<TreeNode> queue = new LinkedList<>();

            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode temp = queue.remove();

                res.add(temp.val);

                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }

            return res;

        }
        /**
         * 层序遍历，按层打印
         */
        /**
         * TODO
         */
        public ArrayList<ArrayList<Integer>> printbyLevel() {
            return null;
        }

        /**
         * 23:给定一个数组，判断是不是搜索二叉树的后序遍历
         */

        public boolean VerifySquenceOfBST(int[] sequence) {
            if (sequence == null || sequence.length <= 0) {
                return false;
            }
            return judge(sequence, 0, sequence.length - 1);
        }

        //判断[start...end]是不是二叉搜索树
        private boolean judge(int[] arr, int start, int end) {
            if (start == end) {
                return true;
            }
            int temp = arr[end];

            int i;
            for (i = start; i < end; i++) {
                if (arr[i] > temp) {
                    break;
                }
            }
            int j = i;
            for (; j < end; j++) {
                if (arr[j] < temp) {
                    return false;
                }
            }

            int leftlen = i - start;
            int rightlen = end - i;

            boolean left = true;
            if (leftlen > 0) {
                left = judge(arr, start, i - 1);
            }
            boolean right = true;
            if (rightlen > 0) {
                right = judge(arr, i, end - 1);
            }

            return left && right;

        }


        /**
         * 24:给定一个二叉树和一个整数，打印二叉树中节点的值的和为输入整数的所有路径
         */
        public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
            if (root == null) {
                return new ArrayList<>();
            }
            result = new ArrayList<>();
            findAPath(root, new ArrayList<Integer>(), 0, target);
            return result;
        }

        private void findAPath(TreeNode node, ArrayList<Integer> path, int cur, int target) {
            path.add(node.val);
            cur += node.val;

            if (node.left == null && node.right == null && cur == target) {
                result.add(new ArrayList<>(path));
            }
            if (node.left != null) {
                findAPath(node.left, path, cur, target);
            }
            if (node.right != null) {
                findAPath(node.right, path, cur, target);
            }
            path.remove(path.size() - 1);
        }

        private ArrayList<ArrayList<Integer>> result;

        /**
         * 打印一个二叉树从根节点到叶子节点的所有路径
         */
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            if (root.left == null && root.right == null) {
                res.add(root.val + "");
                return res;
            }

            List<String> left = binaryTreePaths(root.left);
            for (int i = 0; i < left.size(); i++) {
                res.add(root.val + "->" + left.get(i));
            }

            List<String> right = binaryTreePaths(root.right);
            for (int i = 0; i < right.size(); i++) {
                res.add(root.val + "->" + right.get(i));
            }

            return res;
        }




        /**
         * 26:将搜索二叉树转换成双向链表
         * TODO
         */
        protected TreeNode leftLast = null;

        public TreeNode Convert(TreeNode root) {
            if (root == null)
                return null;
            if (root.left == null && root.right == null) {
                leftLast = root;// 最后的一个节点可能为最右侧的叶节点
                return root;
            }
            // 1.将左子树构造成双链表，并返回链表头节点
            TreeNode left = Convert(root.left);
            // 3.如果左子树链表不为空的话，将当前root追加到左子树链表
            if (left != null) {
                leftLast.right = root;
                root.left = leftLast;
            }
            leftLast = root;// 当根节点只含左子树时，则该根节点为最后一个节点
            // 4.将右子树构造成双链表，并返回链表头节点
            TreeNode right = Convert(root.right);
            // 5.如果右子树链表不为空的话，将该链表追加到root节点之后
            if (right != null) {
                right.left = root;
                root.right = right;
            }
            return left != null ? left : root;
        }

        /**
         * 27: 打印一个字符串的全排列
         */
//    private void swap(char[] arr, int i, int j) {
//        char temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }
        public ArrayList<String> Permutation(String str) {
            res2 = new ArrayList<>();

            if (str == null || str.length() == 0) {
                return new ArrayList<>();
            }

            print(str.toCharArray(), 0);


            return res2;

        }

        private void print(char[] s, int begin) {
            if (begin == s.length) {
                res2.add(new String(s));
            }
            for (int i = begin; i < s.length; i++) {
                if (begin != i && s[i] == s[begin]) {
                    continue;
                }

                swap(s, begin, i);

                print(s, begin + 1);

                swap(s, begin, i);
            }
        }

        private ArrayList res2;






        /**
         * TODO堆排序
         */

        /**
         * 31:[1,n]区间所有数中包含1的个数
         */
        public int NumberOf1Between1AndN_Solution(int n) {
            int res = 0;

            return res;
        }

        //找规律

        //TODO


        //求解每一个数字1的个数，累加
        public int number1(int n) {
            int number = 0;
            while (n != 0) {
                if (n % 10 == 1)
                    number++;
                n /= 10;
            }
            return number;
        }

        /**
         * 33：求从小到大的第N个丑数
         */
        public int GetUglyNumber_Solution(int index) {
            if (index <= 0) {
                return 0;
            }
            int[] dp = new int[index];
            dp[0] = 1;
            int t2 = 0;
            int t3 = 0;
            int t5 = 0;

            for (int i = 1; i < index; i++) {
                int m2 = dp[i - 1];
                for (int k = t2; k < i; k++) {
                    if (dp[k] * 2 > m2) {
                        m2 = dp[k] * 2;
                        t2 = k;
                        break;
                    }
                }
                int m3 = dp[i - 1];
                for (int k = t3; k < i; k++) {
                    if (dp[k] * 3 > m3) {
                        m3 = dp[k] * 3;
                        t3 = k;
                        break;
                    }
                }
                int m5 = dp[i - 1];
                for (int k = t5; k < i; k++) {
                    if (dp[k] * 5 > m5) {
                        m5 = dp[k] * 5;
                        t5 = k;
                        break;
                    }
                }

                dp[i] = min(m2, m5, m3);
            }

            return dp[index - 1];
        }

        private int min(int a, int b, int c) {
            int min = a < b ? a : b;
            return min < c ? min : c;
        }



        /**
         * 35:计算数组中逆序对
         */
//    public int InversePairs(int[] array) {
//        mergeSort(array, 0, array.length - 1);
//
//        return count;
//    }
//
//    private void mergeSort(int[] arr, int l, int r) {
//        if (l < r) {
//            int mid = l + (r - l) / 2;
//            mergeSort(arr, l, mid);
//            mergeSort(arr, mid + 1, r);
//            merge(arr, l, mid, r);
//        }
//    }
//
//    //对[l...mid],[mid+1,r]中的数字进行合并
//    public void merge(int[] arr, int l, int mid, int r) {
//        int len = r - l + 1;
//        int[] aux = new int[len];
//        for (int i = l; i <= r; i++) {
//            aux[i - l] = arr[i];
//        }
//        int i = l;
//        int j = mid + 1;
//        for (int k = l; k <= r; k++) {
//            if (i > mid) {
//                arr[k] = aux[j - l];
//                j++;
//            } else if (j > r) {
//                arr[k] = aux[i - l];
//                i++;
//            } else if (aux[i - l] > aux[j - l]) {
//                arr[k] = aux[j - l];
//                count += (mid + 1 - i);
//                count %= 1000000007;
//                j++;
//            } else {
//                arr[k] = aux[i - l];
//                i++;
//            }
//        }
//    }
//
//    private int count;



        /**
         * 37:统计一个数组在排序数组中出现的次数
         */
        public int GetNumberOfK(int[] array, int k) {

            int left = left(array, k);
            int right = right(array, k);

            if (left == right && left == -1) {
                return 0;
            }

            return right - left + 1;

        }

        private int left(int[] arr, int num) {
            int res = -1;

            int l = 0;
            int r = arr.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (arr[mid] == num) {
                    res = mid;
                    r = mid - 1;
                } else if (arr[mid] > num) {
                    r = mid - 1;
                } else if (arr[mid] < num) {
                    l = mid + 1;
                }
            }
            return res;
        }

        public int right(int[] arr, int num) {
            int res = -1;

            int l = 0;
            int r = arr.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (arr[mid] == num) {
                    res = mid;
                    l = mid + 1;
                } else if (arr[mid] > num) {
                    r = mid - 1;
                } else if (arr[mid] < num) {
                    l = mid + 1;
                }
            }
            return res;
        }

        /**
         * 38:计算二叉树的深度
         */
        public int TreeDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = 0;
            if (root.left != null) {
                left = TreeDepth(root.left);
            }
            int right = 0;
            if (root.right != null) {
                right = TreeDepth(root.right);
            }

            return 1 + Math.max(left, right);
        }

        /**
         * 　39:给定一棵树，判断是不是平衡二叉树
         * TODO
         */
        public boolean IsBalanced_Solution(TreeNode root) {
            Integer depth = 0;
            return isBalance(root, depth);
        }

        private boolean isBalance(TreeNode node, Integer depth) {
            if (node == null) {
                depth = 0;
                return true;
            }
            Integer left = 0;
            Integer right = 0;
            if (isBalance(node.left, left) && isBalance(node.right, right)) {
                int diff = left - right;
                if (diff <= 1 && diff >= -1) {
                    depth = 1 + Math.max(left, right);
                    return true;
                }
            }
            return false;
        }



        /**
         * 41:和为s的所有连续序列
         */
        public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
            if (sum <= 0) {
                return new ArrayList<>();
            }
            int small = 1;
            int big = 2;
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            //[small,big]之间的序列
            int cursum = 0;
            while (small <= (sum + 1) / 2) {
                cursum = (small + big) * (big - small + 1) / 2;

                if (cursum == sum) {
                    ArrayList<Integer> res = new ArrayList<>();

                    for (int k = small; k <= big; k++) {
                        res.add(k);
                    }
                    result.add(res);
                    small++;
                    big++;
                } else if (cursum > sum) {
                    small++;
                } else {
                    big++;
                }
            }

            return result;
        }



        /**
         * 43:循环左移动位置
         */
        public String LeftRotateString(String str, int n) {
            if (str == null || n > str.length()) {
                return new String();
            }
            char[] strs = str.toCharArray();
            reverse(strs, 0, n - 1);
            reverse(strs, n, strs.length - 1);
            reverse(strs, 0, strs.length - 1);

            return new String(strs);
        }

        private void reverse(char[] s, int start, int end) {
            while (start < end) {
                swap(s, start, end);
                start++;
                end--;
            }
        }

        private void swap(char[] s, int i, int j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        /**
         * 44:扑克牌
         */
        public boolean isContinuous(int[] numbers) {
            if (numbers == null || numbers.length < 5) {
                return false;
            }
            int max = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] == 0) {
                    continue;
                }
                if (numbers[i] < min) {
                    min = numbers[i];
                }
                if (numbers[i] > max) {
                    max = numbers[i];
                }
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] == 0) {
                    continue;
                }
                if (map.containsKey(numbers[i])) {
                    return false;
                }
                map.put(numbers[i], 1);
            }

            if (max - min <= 4) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 45:约瑟夫环
         */
        public int LastRemaining_Solution(int n, int m) {
            if (n < 1 || m < 1) {
                return -1;
            }
            int last = 0;
            for (int i = 2; i <= n; i++) {
                last = (last + m) % i;
            }
            return last;
        }

        /**
         * 46:求解1加到n
         */
        public int Sum_Solution(int n) {
            int sum = n;
            boolean ans = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
            return sum;
        }

        /**
         * 46:不用运算符做加法
         */
        public int Add(int num1, int num2) {
            int sum;
            int carry;
            do {
                sum = num1 ^ num2;
                carry = (num1 & num2) << 1;

                num1 = sum;
                num2 = carry;
            } while (num2 != 0);

            return sum;
        }


        /**
         * 48:在一个长度为n的数组，数值范围为n-1中找到重复的数字
         */
        public boolean duplicate(int numbers[], int length, int[] duplication) {
            for (int i = 0; i < length; i++) {
                int index = numbers[i];
                if (index >= length) {
                    index -= length;
                }
                if (numbers[index] >= length) {
                    duplication[0] = numbers[index] - length;
                    return true;
                }
                numbers[index] = numbers[index] + length;
            }
            return false;
        }

        /**
         * 49:正则匹配
         */
        public boolean match(char[] str, char[] pattern) {
            return matchTwo(str, 0, str.length, pattern, 0, pattern.length);

        }

        private boolean matchTwo(char[] str, int i, int length1, char[] pattern, int j, int length2) {
            if (i == length1 && j == length2) {
                return true;
            }
            if (i == length1 && j != length2) {
                while (j != length2) {
                    if (pattern[j] != '*' && (j + 1 >= length2 || pattern[j + 1] != '*')) {
                        return false;
                    }
                    j++;
                }
                return true;
            }
            if (i != length1 && j == length2) {
                return false;
            }
            if (j + 1 == length2) {
                if (str[i] == pattern[j] || pattern[j] == '.')
                    return matchTwo(str, i + 1, length1, pattern, j + 1, length2);
                else {
                    return false;
                }
            }
            if ((str[i] == pattern[j] || pattern[j] == '.') && pattern[j + 1] != '*')
                return matchTwo(str, i + 1, length1, pattern, j + 1, length2);
            if ((str[i] == pattern[j] || pattern[j] == '.') && pattern[j + 1] == '*')
                return matchTwo(str, i, length1, pattern, j + 2, length2) || matchTwo(str, i + 1, length1, pattern, j, length2);
            if (pattern[j + 1] == '*')
                return matchTwo(str, i, length1, pattern, j + 2, length2);
            return false;
        }



        /**
         * 52:找到链表中换的额入口节点
         */
        public ListNode EntryNodeOfLoop(ListNode pHead) {
            if (pHead == null || pHead.next == null) return null;
            ListNode p1 = pHead;
            ListNode p2 = pHead;
            while (p2 != null && p2.next != null) {
                p1 = p1.next;
                p2 = p2.next.next;
                if (p1 == p2) {
                    p1 = pHead;
                    while (p1 != p2) {
                        p1 = p1.next;
                        p2 = p2.next;
                    }
                    if (p1 == p2) return p1;
                }
            }
            return null;
        }





        /**
         * 判断一个二叉树是否是对称的
         */
        //重新定义一种便利方式，先右边，在中间，再左边，将其和一般的中序遍历进行比较，一样则是对称的
        boolean isSymmetrical(TreeNode pRoot) {
            return isSameMIrror(pRoot, pRoot);
        }

        boolean isSameMIrror(TreeNode p1, TreeNode p2) {
            if (p1 == null && p2 == null) {
                return true;
            }
            if (p1 == null || p2 == null) {
                return false;
            }
            if (p1.val != p2.val) {
                return false;
            }


            return isSameMIrror(p1.left, p2.right) && isSameMIrror(p1.right, p2.left);
        }

        /**
         * 按照之字形状打印二叉树
         */
//    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
//        if(pRoot==null){
//            return new ArrayList<>();
//        }
//        Stack[] stacks=new Stack[2];
//        stacks[0]=new Stack<TreeNode>();
//
//        stacks[1]=new Stack<TreeNode>();
//        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
//        int cur=0;
//        int next=1;
//        ArrayList<Integer> res=new ArrayList<>();
//        stacks[0].push(pRoot);
//        while (!stacks[0].isEmpty()||!stacks[1].isEmpty()){
//            TreeNode temp=(TreeNode)stacks[cur].pop();
//            res.add(temp.val);
//
//            if(cur==0){
//                if(temp.left!=null){
//                    stacks[next].push(temp.left);
//                }
//                if(temp.right!=null){
//                    stacks[next].push(temp.right);
//                }
//            }else{
//                if(temp.right!=null){
//                    stacks[next].push(temp.right);
//                }
//                if(temp.left!=null){
//                    stacks[next].push(temp.left);
//                }
//            }
//            if(stacks[cur].isEmpty()){
//                result.add(new ArrayList<>(res));
//                res.clear();
//                cur=1-cur;
//                next=1-next;
//            }
//        }
//
//        return result;
//
//    }

        /**
         * 按照层打印二叉树
         */
        public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
            if (pRoot == null) {
                return new ArrayList<>();
            }
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            ArrayList<Integer> res = new ArrayList<>();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(pRoot);
            TreeNode last = pRoot;
            TreeNode nlast = null;
            while (!queue.isEmpty()) {
                TreeNode temp = queue.remove();
                res.add(temp.val);

                if (temp.left != null) {
                    queue.add(temp.left);
                    nlast = temp.left;
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    nlast = temp.right;
                }
                if (temp == last) {
                    result.add(new ArrayList<>(res));
                    res.clear();
                    last = nlast;
                }
            }
            return result;


        }

        /**
//         * 实现二叉树的序列化和反序列化
//         */
//        public String Serialize(TreeNode root) {
//            if (root == null) {
//                return "#!";
//            }
//            StringBuilder s = new StringBuilder();
//
//            s.append(root.val + "!");
//
//            perOrder(root.left, s);
//            perOrder(root.right, s);
//
//            return new String(s);
//        }
//
//        private void perOrder(TreeNode node, StringBuilder stringBuilder) {
//            if (node == null) {
//                stringBuilder.append("#!");
//                return;
//            } else {
//                stringBuilder.append(node.val + "!");
//            }
//            perOrder(node.left, stringBuilder);
//            perOrder(node.right, stringBuilder);
//        }
//
//
//        public TreeNode Deserialize(String str) {
//            if (str.length() == 0)
//                return null;
//            String[] strs = str.split("!");
//            return Deserialize2(strs);
//        }
//
//        public TreeNode Deserialize2(String[] strs) {
//            index++;
//            if (!strs[index].equals("#")) {
//                TreeNode root = new TreeNode(0);
//                root.val = Integer.parseInt(strs[index]);
//                root.left = Deserialize2(strs);
//                root.right = Deserialize2(strs);
//                return root;
//            }
//
//            return null;
//        }
//    private index=-1;

        /**
         * 找到二叉搜索树中第k大的数
         * TODO
         */
        public TreeNode KthNode(TreeNode pRoot, int k) {
            if(k<=0||pRoot==null){
                return null;
            }
            ArrayList<TreeNode> result=new ArrayList<>();

            inOrder(pRoot,result);
            if(result.size()<k){
                return null;
            }
            return result.get(k-1);
        }
        private void inOrder(TreeNode node,ArrayList<TreeNode> res){
            if(node==null){
                return;
            }
            inOrder(node.left,res);
            res.add(node);
            inOrder(node.right,res);
        }

        /**
         *
         */


    }

