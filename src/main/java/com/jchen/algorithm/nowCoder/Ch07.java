package com.jchen.algorithm.nowCoder;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jchen on 17-8-16.
 * 二叉树相关
 */
public class Ch07 {
    /**
     * 非递归的方式实现二叉树的遍历
     */
    //非递归的方式实现先序遍历
    public void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.println(cur.val + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

    }

    //非递归的凡是实现中序遍历
    public void midOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            System.out.println(p.val);
            p = p.right;
        }
    }

    //非递归的方式实现后序遍历
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(node);
        while (!s1.isEmpty()) {
            TreeNode p = s1.pop();

            s2.push(p);
            if (p.left != null) {
                s1.push(p.left);
            }
            if (p.right != null) {
                s1.push(p.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.println(s2.pop().val);
        }
    }

    /**
     * 平衡二叉树的判断
     */
    public boolean isBalance(TreeNode root) {
        Boolean res = true;
        getHigh(root, 1, res);
        return res;
    }

    private int getHigh(TreeNode head, int level, Boolean res) {
        if (head == null) {
            return level;
        }
        int left = getHigh(head.left, level + 1, res);
        if (!res) {
            return level;
        }
        int right = getHigh(head.right, level + 1, res);
        if (!res) {
            return level;
        }
        if (Math.abs(left - right) > 1) {
            res = false;
        }

        return Math.max(left, right);
    }

    /**
     * 判断一个二叉树是不是搜索二叉树
     */
    public boolean isSerarchTree(TreeNode root) {
        return false;
    }

    private boolean isTree(TreeNode node, TreeNode pre) {
        return false;
    }
    /**
     * 找到一个二叉树后继节点
     */
    public ParentTree findNext(ParentTree node){
        ParentTree temp=node;
        if(temp.right!=null){
            while (temp!=null){
                temp=temp.left;
            }
            return temp;
        }
        temp=node.parent;
        if(temp.left==node){
            return temp;
        }

        ParentTree up=temp.parent;

        while (up!=null){
            if(up.left==temp){
                return up;
            }
            else {
                temp=up;
                up=up.parent;
            }
        }
        return null;
    }
    /**
     * 打印折痕
     */
    public List<String> print(int n){
        List<String> res=new ArrayList<>();

        if(n==0){
            return res;
        }
        ReInOrder(n-1,1,res);
        res.add("down");
        ReInOrder(n-1,0,res);
        return res;

    }

    private void ReInOrder(int n,int flag,List<String> res){
        if(n==0){
            return;
        }
        ReInOrder(n-1,1,res);

        if(flag==1){
            res.add("down");
        }else{
            res.add("up");
        }
        ReInOrder(n-1,0,res);
    }

    /**
     * 找到二叉树中错误节点的位置
     */
    public void getError(TreeNode root){

    }
    /**
     *求二叉树的节点距离最大值
     */
    public void getMax(TreeNode root,TreeNode node1,TreeNode node2){
        return;
    }
    /**
     * 给定一个二叉树的头结点，找到含节点数最多搜索二叉树的子树
     */
    public TreeNode findMaxChildren(TreeNode root){
        return null;
    }

    /**
     *
     */


}
