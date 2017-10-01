package com.jchen.algorithm.mooc2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jchen on 17-8-6.
 */
public class Chapter8 {


    // 8.1   打印出手机键盘的字母全排列
    private ArrayList<String> res1 = new ArrayList<>();
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

    public ArrayList<String> letterCommbintion(String digits) {
        if (digits.length() == 0) {
            return null;
        }
        findOne(digits, 0, "");
        return res1;
    }

    private void findOne(String digits, int index, String s) {
        if (index == digits.length()) {
            res1.add(s);
            return;
        }
        char c = digits.charAt(index);
        assert (c >= '0' && c <= '9' && c != '1');

        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findOne(digits, index + 1, s + letters.charAt(i));
        }
    }

    /**
     * 8.2　给一个数组求其全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        for(int i=0;i<nums.length;i++){
            used.add(false);
        }
        generate(nums,0,new ArrayList<>());
        return res2;
    }
    private void generate(int[] nums,int index,ArrayList<Integer> list){
        if(index==nums.length){
            res2.add(new ArrayList<>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(!used.get(i)){
                used.set(i,true);
                list.add(nums[i]);
                generate(nums,index+1,list);
                list.remove(list.size()-1);
                used.set(i,false);
            }
        }

    }

    private List<List<Integer>> res2 = new ArrayList<>();
    private List<Boolean> used=new ArrayList<>();


    /**
     * 8.3 从n个数中选出k个数的组合
     */

    public List<List<Integer>> combine(int n, int k) {
        combine(new ArrayList<Integer>(), 1, n, k);
        return res3;
    }

    private void combine(List<Integer> comb, int start, int n, int k) {
        if (k == comb.size()) {
            res3.add(new ArrayList<Integer>(comb));
            return;
        }
        for (int i = start; i <= n; i++) {
            comb.add(i);
            combine(comb, i + 1, n, k);
            comb.remove(comb.size() - 1);
        }
        return;
    }

    private List<List<Integer>> res3 = new ArrayList<>();

    /**
     * 8.6 二维平面上的字母寻找（79）
     */
    public boolean exist(char[][] board, String word) {
        m=board.length;
        n=board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                visited[i][j]=false;
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(searchWord(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    //查找word的index位置
    private boolean searchWord(char[][] board,String word,int index,int i,int j){
        visited[i][j]=true;
        if(index==word.length()-1){
            return board[i][j]==word.indexOf(index);
        }
        if(board[i][j]==word.indexOf(index)){
            for(int k=0;i<4;k++){
                int newx=i+d[k][0];
                int newy=j+d[k][1];
                if((inArea(newx,newy))&&!visited[i][j]&&searchWord(board,word,index+1,newx,newy)){
                    return true;
                }
            }
            visited[i][j]=false;
        }
        return false;
    }

    private boolean inArea( int x , int y ){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    int m,n;
    private int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};

    private boolean[][] visited;


    /**
     * 8.7 floodFilld
     */
    public int numIslands(char[][] grid) {
        m=grid.length;
        if( m == 0 )
            return 0;
        n=grid[0].length;
        visited=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                visited[i][j]=false;
            }
        }

        int result=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'&&!visited[i][j]){
                    result++;
                    dfs(grid,i,j);
                }
            }
        }
        return result;
    }
    private void dfs(char[][] grid,int i,int j){
        visited[i][j]=true;
        for(int k=0;k<4;k++){
            int newx=i+d[k][0];
            int newy=j+d[k][1];
            if(inArea(newx,newy)&&!visited[newx][newy]&&grid[newx][newy]=='1'){
                dfs(grid,newx,newy);
            }
        }
        return;
    }

    /**
     * 8.8n皇后问题
     */
    public List<List<String>> solveNQueens(int n) {
        lie=new boolean[n];
        all(lie,n,false);
        dai1=new boolean[2*n-1];
        all(dai1,2*n-1,false);
        dail2=new boolean[2*n-1];
        all(dail2,2*n-1,false);




        res8.clear();

        List<Integer> row=new ArrayList<>();
        putQueen(n,0,row);
        return res8;
    }

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置
    private void putQueen(int n,int index,List<Integer> row){
        if(index==n){
            res8.add(generateByrow(n,row));
            return;
        }
        for(int i=0;i<n;i++){
//            尝试将index行的皇后摆放在第i列
            if(!lie[i]&&!dail2[index+i]&&!dai1[index-i+n-1]){
                row.add(i);
                lie[i]=true;
                dail2[index+i]=true;
                dai1[index-i+n-1]=true;
                putQueen(n,index+1,row);
                lie[i]=false;
                dail2[index+i]=false;
                dai1[index-i+n-1]=false;
                row.remove(row.size()-1);
            }
        }
    }
    private List<String> generateByrow(int n,List<Integer> row){
        StringBuilder s=new StringBuilder();
        for(int i=0;i<n;i++){
            s.append('.');
        }
        List<String> res=new ArrayList<>();
        String temp=s.toString();
        for(int i=0;i<n;i++){
            s.setCharAt(row.get(i),'Q');
            res.add(new String(s));
            s=new StringBuilder(temp);
        }
        return res;
    }
    //对一个bool数组进行赋值
    private void all(boolean[] arr,int n,boolean bool){
        for(int i=0;i<n;i++){
            arr[i]=bool;
        }
    }
    private List<List<String>> res8=new ArrayList<>();
    //快速的获取列冲突信息
    private boolean[] lie;
    //正向对角巷
    private boolean[] dai1;
    //反向对角线
    private boolean[] dail2;






//    主测试函数

    public static void main(String[] args) {
        Chapter8 chapter8 = new Chapter8();

        int[] arr={1,2,3};
        List<List<String>> res = chapter8.solveNQueens(4);
        for (List<String> list : res) {
            for (String i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }


}
