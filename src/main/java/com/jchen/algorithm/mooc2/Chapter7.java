package com.jchen.algorithm.mooc2;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;

/**
 * Created by jchen on 17-8-5.
 */
public class Chapter7 {

    //    7.1找到二叉树的最大深度
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return 1 + left > right ? left : right;

    }

    //    7.2 反转一个二叉树
    public TreeNode reverse(TreeNode node) {
        if (node == null) {
            return null;
        }
        reverse(node.left);
        reverse(node.right);
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        return node;

    }

    //  7.3  找一条从根节点到叶子节点路径，注意终止条件的判断
    public boolean hasPath(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return node.data == sum;
        }
        return hasPath(node.left, sum - node.data) || hasPath(node.right, sum - node.data);
    }
//    7.4 给一个二叉树，返回所有表示从根节点到叶子节点的路径的字符串
    public ArrayList<String> binaryTreePath(TreeNode node){
        ArrayList<String> list=new ArrayList<>();

        if(node==null){
            return null;
        }
        if(node.left==null&&node.right==null){
            list.add(""+node.data);
            return list;
        }
        ArrayList<String> left=binaryTreePath(node.left);

        for(int i=0;i<left.size();i++){
            list.add(node.data+"->"+left.get(i));
        }

        ArrayList<String> right=binaryTreePath(node.right);
        for (int i=0;i<right.size();i++){
            list.add(node.data+"->"+right.get(i));
        }

        return list;
    }
//   在二叉树中找到值未sum　的路径
    public int pathSum(TreeNode node,int sum){
        int res=0;
        if(node==null){
            return 0;
        }
        res+=findPath(node,sum);
        res+=pathSum(node.left,sum);
        res+=pathSum(node.right,sum);

        return res;
    }

    // 在以node为根节点的二叉树中,寻找包含node的路径,和为sum
    // 返回这样的路径个数
    private static int findPath(TreeNode node,int sum){
        if(node==null){
            return 0;
        }
        int res=0;
        if(node.data==sum){
            res+=1;
        }
        res+=findPath(node.left,sum-node.data);
        res+=findPath(node.right,sum-node.data);

        return res;
    }
//    二叉搜索树的最低公共祖先
    public TreeNode lowestAncestor(TreeNode root, TreeNode p1,TreeNode p2){
        assert (p1!=null&&p2!=null);

        if(root==null){
            return null;
        }
        if(p1.data<root.data&&p2.data<root.data){
            return lowestAncestor(root.left,p1,p2);
        }
        if(p1.data>root.data&&p2.data>root.data){
            return lowestAncestor(root.right,p1,p2);
        }
        assert (p1.data<root.data&&p2.data>root.data)||(p1.data>root.data&&p2.data<root.data);

        return root;
    }

}
