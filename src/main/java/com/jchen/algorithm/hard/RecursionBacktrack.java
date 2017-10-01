package com.jchen.algorithm.hard;

import com.jchen.algorithm.niukeOnline.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 递归和回溯
 */
public class RecursionBacktrack {

    /**
     * 递归部分
     * 先一般后特殊，先假设递归已经进行了一半
     */


    //创建链表
    public ListNode createLinkedList(List<Integer> origin) {

        if (origin.size() == 0) {
            return null;
        }
        ListNode head = new ListNode(origin.get(0));

        ListNode next = createLinkedList(origin.subList(1, origin.size()));

        head.next = next;

        return head;
    }

    //    递归的反转链表
    public ListNode reverse(ListNode head) {

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode next = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return next;
    }

    /**
     * 循环写法
     * 定义循环不变式，并在循环体每次结束后保持循环不变式
     * 先一般后特殊
     * 必须每次向前推进循环不变式中设计的变量值
     * 每次突击的规模必须为1
     */

//    反转链表的非递归写法
    public ListNode reverse2(ListNode head) {
        //newHead 表示已经反转的链表的头节点
//        curHead 表示即将旋转的链表的头节点
        ListNode newHead = null;
        ListNode curHead = head;
        while (curHead != null) {
            ListNode next = curHead.next;
            curHead.next = newHead;
            newHead = curHead;
            curHead = next;
        }
        return newHead;
    }

    //删除链表中指定值的节点
    public ListNode deleteIf(ListNode head, int target) {
        while (head != null && head.val == target) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }

        ListNode pre = head;

        while (pre.next != null) {
            if (pre.next.val == target) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return head;

    }

    /**
     * 回溯
     * 处理循环过程的sideeffect
     */
//    打印从n个数中选出k个数的所有组合
    public void printN(List<Integer> select, List<Integer> data, int n) {
        if (n == 0) {
//            coutput
            for (Integer i : select) {
                System.out.print(i + " ");
            }

            System.out.println();
            return;
        }
        if (data.isEmpty()) {
            return;
        }

        /**
         * 当前操作完了之后要进行影响的消除
         */

        select.add(data.get(0));
        printN(select, data.subList(1, data.size()), n - 1);


        select.remove(select.size() - 1);
        printN(select, data.subList(1, data.size()), n);
    }

    //给定一个数字字符串，返回这个数字字符串能表示的所有字母的组合
    private static final String[] letterMap = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private void findOne(String data, String res, int index) {
        if (index == data.length()) {
            System.out.println(res);
            return;
        }
        char s = data.charAt(index);
        String temp = letterMap[s - '0'];

        for (int i = 0; i < temp.length(); i++) {
            findOne(data, res + temp.charAt(i), index + 1);
        }
        System.out.println("complete**********************");
    }

    /**
     * 回溯2
     * 给定一个数字字符串，在中间插入三个点，返回所有的合法ip地址
     */
    /**
     * 回溯3
     * 给定一个字符串，拆分这个字符串，使得所有拆分的子串都是虎纹字符串，返回所有的拆分可能
     */

    /**
     * 排列问题
     * 给定一个整形数组，返回所有的排列组合可能
     */
    public void printAll(int[] arr, List<Integer> res, int n) {
        //n表示已经排好的元素个数
        if (n == arr.length) {
            for (Integer i : res) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            //判断是否在res数组中
            if (!used[i]) {
                res.add(arr[i]);
                used[i] = true;
                printAll(arr, res, n + 1);
                res.remove(res.size() - 1);
                used[i] = false;
            }
        }
        return;
    }

    public void pring(int[] arr) {
        used = new boolean[arr.length];
        printAll(arr, new ArrayList<>(), 0);
    }

    private boolean[] used;

    /**
     * 如果上述数组中包含重复元素怎么办
     */
    /**
     * 组合问题
     * 给出n个数从1...n中选出k个数子
     */
    //n表示从data中选出n个数
    public void combination(List<Integer> selected, List<Integer> data, int n) {
        if (n == 0) {
            for (Integer i : selected) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }

        if (data.isEmpty()) {
            return;
        }

        selected.add(data.get(0));
        combination(selected, data.subList(1, data.size()), n - 1);

        selected.remove(selected.size() - 1);
        combination(selected, data.subList(1, data.size()), n);

        return;
    }

    /**
     * 给定一个集合，集合中的元素各不相同，以及一个数字t,寻找多有该集合中的元素组合，使得组合中多有元的和为t
     * 集合中每一元素可以使用多次
     * [2,3,6,7] 7
     * [7],[2,2,3]
     */
    /**
     * 给定一个集合，集合中的元素可能相同，以及一个数字t,寻找多有该集合中的元素组合，使得组合中多有元的和为t
     * 集合中每一元素只能使用一次
     * [10,1,2,7,6,1,5] 8
     * [1,7],[1,2,5],[2,6],[1,1,6]
     */
    /**
     * 在1-9这9个数字中，选出k个数，每个数字只能使用一次，使得其和为n
     * n=7,k=3 [1,2,4]
     *n=9,k=3 [1,2,6],[1,3,6],[2,3,4]
     */
    /**
     * 给出一个集合，其中的元素有可能相同，求出该集合的所有子集
     */


    /**
     * 二维平面的回溯法
     * 给一个二维字符数组，找一个单词
     */
    public boolean exist(char[][] board, String word) {
        isused=new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(searchWord(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }


    private int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private boolean[][] isused;

    private boolean isvalid(int x,int y,char[][] board){
        if(x>=0&&x<board.length&&y>=0&&y<board[0].length&&isused[x][y]==false){
            return true;
        }
        return false;
    }

    //从board的[startx][starty]开始,寻找word[index.....word.length)
    private boolean searchWord(char[][] board, String word, int index, int startx, int starty) {
        if (index == word.length() - 1) {
            return board[startx][starty] == word.charAt(index);
        }
        if (board[startx][starty] == word.charAt(index)) {
            //从startx,starty开始，从四个方向寻找
            isused[startx][starty]=true;
            for (int i = 0; i < 4; i++) {
                int newx = startx + d[i][0];
                int newy = starty + d[i][1];
                if (isvalid(newx,newy,board)) {
                    if(searchWord(board,word,index+1,newx,newy)){
                        return true;
                    }
                }
            }
            isused[startx][starty]=false;

        }
        return false;

    }

    /**
     * floodFill算法，一类经典问题
     * 找岛屿
     */
    public int numIslands(char[][] grid){
        if(grid==null||grid.length==0){
            return 0;
        }

        visited=new boolean[grid.length][grid[0].length];
        int count=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'&&!visited[i][j]){
                    foolFill(i,j,grid.length,grid[0].length,grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void foolFill(int x,int y,int m,int n,char[][] grid){
        visited[x][y]=true;
        for (int i=0;i<4;i++){
            int newx=x+way[i][0];
            int newy=y+way[i][1];
            if(newx>=0&&newx<m&&newy>=0&&newy<n&&!visited[newx][newy]&&grid[newx][newy]=='1'){
                foolFill(newx,newy,m,n,grid);
            }
        }
    }

    private int[][] way= {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean visited[][];

    /**
     *给定一个二维数组，只含有x和o两种元素，找到所有被x包围的o字符组成的区域，将其中的o反转成x:130
     */

    /**
     *给定一个二位数组，其中的数字代表地形高度，假设左上角为太平洋，右下角为大西洋，睡可以向上下左右四个感想的同等高度或者更低的高度流动，求出二维数组中谁可以同事流向太平洋和大西洋的地形坐标:417
     */

    /**
     *八皇后问题
     */

    public ArrayList<ArrayList<String>> solveQueens(int n){
        lie=new boolean[n];
        dia1=new boolean[2*n-1];
        dia2=new boolean[2*n-1];

        putQueen(n,0,new ArrayList<>());

        return null;
    }

    //index表示摆放到哪一行，,res表示前index行摆放的位置
    private void putQueen(int n,int index,ArrayList<Integer> res){
        if(index==n){
            numbers.add(res);
        }
        for (int i=0;i<n;i++){
            if(!lie[i]&&!dia1[i+index]&&!dia2[index-i+n-1]){
                res.add(i);
                lie[i]=true;
                dia1[i+index]=true;
                dia2[index-i+n-1]=true;
                putQueen(n,index+1,res);
                res.remove(res.size()-1);
                lie[i]=false;
                dia1[i+index]=false;
                dia2[index-i+n-1]=false;
            }
        }
    }
    private boolean[] lie;
    private boolean[] dia1;//正向对角线
    private boolean[] dia2;//你像对角线
    private ArrayList<ArrayList<Integer>> numbers;

    /**
     * 求解数独:37
     */









    public static void main(String[] args) {
        new RecursionBacktrack().combination(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);
    }


}
