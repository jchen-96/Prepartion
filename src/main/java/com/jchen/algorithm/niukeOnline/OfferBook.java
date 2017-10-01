package com.jchen.algorithm.niukeOnline;

import org.apache.tomcat.util.digester.ArrayStack;

import java.util.*;

/**
 * Created by jchen on 17-8-18.
 * 牛客网在线编程《剑指offer》
 */
public class OfferBook {
    /**
     * question1:二维数组中的查找
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 1) {
            return false;
        }
        boolean found = false;

        int m = array.length;
        int n = array[0].length;

        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (array[i][j] == target) {
                found = true;
                break;
            } else if (target > array[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return found;
    }

    /**
     * question2:替换空格
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            } else {
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }

    /**
     * question3:从后到前打印链表
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>();
        }
        Stack<Integer> stack = new Stack<>();
        ListNode head = listNode;
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    /**
     * question4:根据前序遍历和中序遍历结果，重构二叉树
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        TreeNode root = built(pre, in, 0, pre.length - 1, 0, in.length - 1);
        return root;
    }

    //对pre[l1,r1]和in[l2,r2]范围内的数组进行重建
    private TreeNode built(int[] pre, int[] in, int l1, int r1, int l2, int r2) {
        int rootvalue = pre[l1];

        TreeNode root = new TreeNode(rootvalue);

        if (l1 == r1) {
            if (l2 == r2 && pre[l1] == in[r2]) {
                return root;
            } else {
                System.out.println("输入不合法");
            }
        }

        int temp = l2;
        while (temp <= r2 && rootvalue != in[temp]) {
            temp++;
        }
        if (in[temp] != rootvalue) {
            System.out.println("输入不合法");
        }

        int leftlen = temp - l2;
        int rightlen = r2 - temp;

        if (leftlen > 0) {
            root.left = built(pre, in, l1 + 1, l1 + leftlen, l2, temp - 1);
        }
        if (rightlen > 0) {
            root.right = built(pre, in, l1 + leftlen + 1, r1, temp + 1, r2);
        }
        return root;
    }

    /**
     * question5:用两个栈实现一个队列
     */
//    Stack<Integer> stack1 = new Stack<Integer>();
//    Stack<Integer> stack2 = new Stack<Integer>();
    public void push１(int node) {
        stack1.push(node);
    }

    public int pop1() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

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
     * question7:斐波拉切数列
     */
    public int Fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] arr = new int[]{1, 1};

        if (n <= 2) {
            return arr[n - 1];
        }
        int k = 2;
        while (k < n) {
            arr[k % 2] = arr[0] + arr[1];
            k++;
        }

        return arr[(n - 1) % 2];

    }

    /**
     * 　question8:跳台阶
     */
    public int JumpFloor(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] arr = new int[]{1, 2};

        if (n <= 2) {
            return arr[n - 1];
        }
        int k = 2;
        while (k < n) {
            arr[k % 2] = arr[0] + arr[1];
            k++;
        }

        return arr[(n - 1) % 2];
    }

    /**
     * question9:变态跳台阶
     */
    public int JumpFloorII(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target <= 2) {
            return target;
        }
        int[] dp = new int[target];

        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < target; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] += dp[j];
            }
            dp[i] += 1;
        }
        return dp[target - 1];
    }

    /**
     * question10:用2X1的小矩形，覆盖2*n的大矩形
     */
    public int RectCover(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] arr = new int[]{1, 2};

        if (n <= 2) {
            return arr[n - 1];
        }
        int k = 2;
        while (k < n) {
            arr[k % 2] = arr[0] + arr[1];
            k++;
        }

        return arr[(n - 1) % 2];
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
     * question12:浮点数的整数次方
     */
    public double Power(double base, int exponent) {
        //由于exponent是int类型的整数，则可能包含正整数、0以及负整数三种情况。
        double temp = 1;
        if (exponent > 0) {
            return base(base, exponent);

        }
        if (exponent < 0) {
            exponent = -exponent;
            temp = base(base, exponent);
            temp = 1.0 / temp;
            return temp;
        } else {
            return 1;
        }
    }

    private double base(double base, int exponent) {
        double temp = 1;
        for (int i = 1; i <= exponent; i++) {
            temp = temp * base;
            if (temp > 1.7976931348623157E308) {
                System.out.println("已经超出double类型的取值范围。");
                return -1;
            }
        }
        return temp;
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
     * 14:删除链表倒数第k个节点
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int temp = k - 1;
        ListNode phead = head;
        while (phead != null && temp >= 0) {
            phead = phead.next;
            temp--;
        }
        if (temp == -1) {
            ListNode pre = phead;
            ListNode behind = head;
            while (pre != null) {
                pre = pre.next;
                behind = behind.next;
            }
            return behind;
        } else {
            return null;
        }

    }

    /**
     * 15:反转单链表
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;

    }

    /**
     * 16:合并两个单链表
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode newhead;

        if (list1.val < list2.val) {
            newhead = list1;
            list1 = list1.next;
        } else {
            newhead = list2;
            list2 = list2.next;
        }

        ListNode phead = newhead;

        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null && list1.val < list2.val) {
                phead.next = list1;
                list1 = list1.next;
            } else if (list1 != null && list2 != null && list1.val >= list2.val) {
                phead.next = list2;
                list2 = list2.next;
            } else if (list1 == null) {
                phead.next = list2;
                break;
            } else {
                phead.next = list1;
                break;
            }
            phead = phead.next;
        }
        return newhead;

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
     * 19:转圈圈打印二维矩阵
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }

        res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i * 2 < m && i * 2 < n; i++) {
            outCircle(matrix, i, m, n);
        }
        return res;

    }

    private void outCircle(int[][] arr, int start, int m, int n) {
        int end1 = m - start - 1;
        int end2 = n - start - 1;

        //打印上面一行
        for (int i = start; i <= end2; i++) {
            res.add(arr[start][i]);
        }
        //打印右边
        if (start < end1) {
            for (int i = start + 1; i <= end1; i++) {
                res.add(arr[i][end2]);
            }
        }
        //打印下边
        if (start < end1) {
            for (int i = end2 - 1; i >= start; i--) {
                res.add(arr[end1][i]);
            }
        }
        //打印左边
        if (start < end2) {
            for (int i = end1 - 1; i > start; i--) {
                res.add(arr[i][start]);
            }
        }
    }

    private ArrayList<Integer> res;

    /**
     * 20:包含min函数的栈
     */


    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();


    public void push(int node) {
        if (stack1.isEmpty()) {
            stack1.push(node);
            stack2.push(node);
        } else {
            stack1.push(node);
            if (stack2.peek() > node) {
                stack2.push(node);
            } else {
                stack2.push(stack2.peek());
            }
        }
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
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
     * 25:复杂链表的复制
     */
    public RandomListNode Clone(RandomListNode pHead) {
        RandomListNode p = pHead;
        RandomListNode t = pHead;
        while (p != null) {
            RandomListNode q = new RandomListNode(p.label);
            q.next = p.next;
            p.next = q;
            p = q.next;
        }
        while (t != null) {
            RandomListNode q = t.next;
            if (t.random != null)
                q.random = t.random.next;
            t = q.next;

        }
        RandomListNode s = new RandomListNode(0);
        RandomListNode s1 = s;
        while (pHead != null) {
            RandomListNode q = pHead.next;
            pHead.next = q.next;
            q.next = s.next;
            s.next = q;
            s = s.next;
            pHead = pHead.next;


        }
        return s1.next;

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
     * 28:找出数组中出现次数超过一半的数字
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        int res = 0;
        int count = 0;
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                temp = array[i];
                count++;
            } else {
                if (temp == array[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        int count2 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == temp) {
                count2++;
            }
        }
        return count2 > (array.length / 2) ? temp : 0;
    }


    /**
     * 29: 找到数组中最小的k个数
     */
    //index为k-1;
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || k <= 0 || k > input.length) {
            return new ArrayList<>();
        }
        int index = k - 1;
        int l = 0, r = input.length - 1;
        int res = partition(input, l, r);
        while (res != index) {
            if (res > index) {
                r = res - 1;
                res = partition(input, l, r);
            } else {
                l = res + 1;
                res = partition(input, l, r);
            }
        }
        ArrayList<Integer> resutl = new ArrayList<>();
        for (int i = 0; i <= index; i++) {
            resutl.add(input[i]);
        }
        return resutl;
    }

    //partition解法,对arr[l....r]进行partition操作
    private int partition(int[] arr, int l, int r) {
        int random = (int) (Math.random() * (r - l)) + 1;
        int temp = arr[random];
        swap(arr, l, random);

        int i = l + 1;
        int j = l + 1;
        int h = r;
        //[l+1,i)<,[i,j)==,(h,r]>
        while (j <= h) {
            if (arr[j] == temp) {
                j++;
            } else if (arr[j] < temp) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                swap(arr, j, h);
                h--;
            }
        }
        swap(arr, l, i - 1);

        return i - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * TODO堆排序
     */
    /**
     * 30:连续子数组的最大和
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        //dp[i]表示包含arr[i]的最大连续子数组
        int[] dp = new int[array.length];
        dp[0] = array[0];

        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = array[i];
            } else {
                dp[i] = dp[i - 1] + array[i];
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

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
     * 32:把数组排成最小的数
     */
    public String PrintMinNumber(int[] numbers) {


        String[] strs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }
        StringBuilder res = new StringBuilder();
        Arrays.sort(strs, new myComparator());
        for (int i = 0; i < numbers.length; i++) {
            res.append(strs[i]);
        }
        return new String(res);

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
     * 34:在一个字符串中找到第一只出现一次的字符并返回它的位置
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        char[] temp = str.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (map.containsKey(temp[i])) {
                map.put(temp[i], map.get(temp[i]) + 1);
            } else {
                map.put(temp[i], 1);
            }

        }
        for (int i = 0; i < temp.length; i++) {
            if (map.get(temp[i]) == 1) {
                return i;
            }
        }
        return -1;
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
     * 36:输入两个链表，找到第一个公共节点
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        int count1 = 0;
        int count2 = 0;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != null) {
            count1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            count2++;
            p2 = p2.next;
        }
        p1 = pHead1;
        p2 = pHead2;
        int diff;
        if (count1 > count2) {
            diff = count1 - count2;
            while (diff > 0) {
                p1 = p1.next;
                diff--;
            }
        } else {
            diff = count2 - count1;
            while (diff > 0) {
                p2 = p2.next;
                diff--;
            }
        }
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return null;
    }

    /**
     * 37:统计一个数在排序数组中出现的次数
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
     * 40:数组中只出现一次的数字
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (num1 == null) {
            return;
        }
        int res1 = 0;
        int flag = 1;
        for (int i = 0; i < array.length; i++) {
            res1 ^= array[i];
        }
        while ((res1 & flag) == 0) {
            flag <<= 1;
        }
        for (int i = 0; i < array.length; i++) {
            if ((flag & array[i]) == 0) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
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
     * 42:输入一个递增序列，找到两个数合为s
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array == null || array.length <= 1) {
            return new ArrayList<>();
        }
        int begin = 0;
        int end = array.length - 1;
        int n1 = 0;
        int n2 = 0;
        int p = Integer.MAX_VALUE;
        while (begin < end) {
            if (array[begin] + array[end] == sum) {
                if (n1 * n2 < p) {
                    n1 = array[begin];
                    n2 = array[end];
                    p = n1 * n2;
                }
                begin++;
                end--;
            } else if (array[begin] + array[end] > sum) {
                end--;
            } else {
                begin++;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        if (p != Integer.MAX_VALUE) {
            res.add(n1);
            res.add(n2);
        }

        return res;
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
     * 旋转单词
     */
    public String ReverseSentence(String str) {
        if (str == null) {
            return null;
        }
        if (str.trim().equals("")) {
            return str;
        }
        String string = str;
        String[] strings = string.split(" ");
        StringBuilder sBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            if (i == 0) {
                sBuilder.append(strings[i]);
            } else {
                sBuilder.append(strings[i]);
                sBuilder.append(" ");
            }
        }

        String string2 = sBuilder.toString();
        return string2;
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
     * 47:将一个字符串转换成整数
     */
    public int StrToInt(String str) {
        if (str.equals("") || str.length() == 0) {
            return 0;
        }
        int fuhao = 0;
        char[] a = str.toCharArray();
        if (a[0] == '-') {
            fuhao = 1;
        }
        int sum = 0;
        for (int i = fuhao; i < a.length; i++) {
            if (a[i] == '+') {
                continue;
            }
            if (a[i] < '0' || a[i] > '9') {
                return 0;
            }
            sum = sum * 10 + a[i] - '0';
        }

        return fuhao == 0 ? sum : sum * (-1);
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
     * 50:判断一个字符串是否表示数值
     */
    public boolean isNumeric(char[] str) {
        String s = String.valueOf(str);
        //return s.matches("[+-]?[0-9]*(.[0-9]*)?([eE][+-]?[0-9]+)?");
        return s.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
    }

    /**
     * 51:字符流中第一个不重复的字符
     */
    int count[] = new int[256];
    //Insert one char from stringstream
    int index = 1;

    public void Insert(char ch) {
        if (count[ch] == 0) {
            count[ch] = index++;
        } else {
            count[ch] = -1;
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int temp = Integer.MAX_VALUE;
        char ch = '#';
        for (int i = 0; i < 256; i++) {
            if (count[i] != 0 && count[i] != -1 && count[i] < temp) {
                temp = count[i];
                ch = (char) i;
            }
        }
        return ch;
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
     * 53:删除链表中重复的节点
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode pre = new ListNode(0);
        pre.next = pHead;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            if (temp.next.val == temp.next.next.val) {
                while (temp.next.next != null && temp.next.val == temp.next.next.val) {
                    temp.next.next = temp.next.next.next;
                }
                temp.next = temp.next.next;

            } else {
                temp = temp.next;
            }

        }
        return pre.next;

    }

    /**
     * 54:二叉树中序遍历的后一个节点
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        TreeLinkNode temp = pNode;
        if (temp.right != null) {
            temp = temp.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;

        }
        if (temp.next != null) {
            if (temp.next.left == temp) {
                return temp.next;
            }
        }
        temp = temp.next;
        while (temp != null) {
            if (temp.next != null) {
                TreeLinkNode pre = temp.next;
                if (pre.left == temp) {
                    return pre;
                } else {
                    temp = temp.next;
                }
            }

            return null;
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
     * 实现二叉树的序列化和反序列化
     */
    public String Serialize(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        StringBuilder s = new StringBuilder();

        s.append(root.val + "!");

        perOrder(root.left, s);
        perOrder(root.right, s);

        return new String(s);
    }

    private void perOrder(TreeNode node, StringBuilder stringBuilder) {
        if (node == null) {
            stringBuilder.append("#!");
            return;
        } else {
            stringBuilder.append(node.val + "!");
        }
        perOrder(node.left, stringBuilder);
        perOrder(node.right, stringBuilder);
    }


    public TreeNode Deserialize(String str) {
        if (str.length() == 0)
            return null;
        String[] strs = str.split("!");
        return Deserialize2(strs);
    }

    public TreeNode Deserialize2(String[] strs) {
        index++;
        if (!strs[index].equals("#")) {
            TreeNode root = new TreeNode(0);
            root.val = Integer.parseInt(strs[index]);
            root.left = Deserialize2(strs);
            root.right = Deserialize2(strs);
            return root;
        }

        return null;
    }
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


class myComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return (o1 + o2).compareTo(o2 + o1);
    }
}















































