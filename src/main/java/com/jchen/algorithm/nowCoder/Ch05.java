package com.jchen.algorithm.nowCoder;

import org.omg.CORBA.NO_IMPLEMENT;

/**
 * Created by jchen on 17-8-14.
 * 链表相关问题
 */
public class Ch05 {
    /**
     * 反转单链表
     */
    public Node reverse(Node root) {
        if (root == null || root.next == null) {
            return root;
        }

        Node pre = null;
        Node cur = root;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;

        }
        return pre;
    }

    /**
     * 环形有序链表中插入节点
     */
    public CicleNode insert(CicleNode head, int data) {
        CicleNode node = new CicleNode(data);

        if (head == null) {
            node.pre = node;
            node.next = node;
        }

        CicleNode pre = head;
        CicleNode next = head.next;
        int flag = 0;

        while (pre != head || flag != 1) {
            if (pre.val <= data && next.val >= data) {
                pre.next = node;
                node.next = next;
                next.pre = node;
                node.pre = pre;
                flag = 1;
                return head;
            } else {
                pre = pre.next;
                next = next.next;
                flag = 1;
            }
        }
        if (node.next == null) {
            head.next = node;
            head.pre = node;
            node.pre = head;
            node.next = head;
        }
        return node.val > head.val ? head : node;
    }

    /**
     * 对链表进行partiton操作
     * 将链表划分为三个子链表
     */
//    public Node partition(Node head, int num) {
//        Node smallh = null;
//        Node smallt=null;
//        Node bigh=null;
//        Node bigt=null;
//
//        Node p=head;
//        Node q=head;
//
//        while (p!=null){
//            q=p;
//            if(p.val<=)
//        }
//
//
//    }

    /**
     * 打印两个有序链表的公共部分
     */
    public void printCommon(Node head1, Node head2) {
        Node p = head1;
        Node q = head2;

        if (head1 == null || head2 == null) {
            return;
        }
        while (p != null && q != null) {
            if (p.val == q.val) {
                System.out.print(p.val + " ");
                p = p.next;
                q = q.next;
            }
            if (p.val > q.val) {
                q = q.next;
            } else {
                p = p.next;
            }
        }
    }

    /**
     * 将单链表按k划分，每一组逆序
     */
    public void reeverse(Node head, int k) {
        if (head == null || head.next == null || k < 2) {
            return;
        }
    }

    /**
     * 删除链表中值为给定值的节点
     */
    public void delVal(Node head, int val) {

    }

    /**
     * 判断一个链表是否是回文结构
     */
    public boolean isMiorr(Node head, int val) {

        return false;
    }

    /**
     * 复杂链表的复制问题
     */
    public Node complexCopy(Node head) {
        return head;
    }

    /**
     * 如何判断一个单链表是否有环，有环的话，返回进入环的第一个节点
     */
    public Node hasCircle(Node head) {
        return null;
    }

    /**
     * 如何判断无环单链表是否相交
     */
    public boolean isCross(Node head1, Node head2) {
        return false;
    }

    /**
     * 判断两个有环单链表是否相交
     */
    public boolean isCross2(Node head1, Node head2) {
        return false;
    }

    /**
     * 给定两个单链表，判断是否相交
     */
    public Node isCross3(Node head1, Node head2) {
        return null;
    }


}


class Node {
    int val;
    Node next;

    public Node(int data) {
        this.val = data;
    }

}

class CicleNode {
    int val;
    CicleNode pre;
    CicleNode next;

    public CicleNode(int data) {
        this.val = data;
        this.pre = null;
        this.next = null;
    }
}
