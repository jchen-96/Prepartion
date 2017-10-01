package com.jchen.algorithm.chapter8;

/**
 * Created by jchen on 17-8-2.
 */
public class linkList {
    //    从链表中删除重复节点
    public void deleteDuplecarte(Node head) {
        Node p = head;
        while (p != null) {
            Node q = p;
            while (q.next != null) {
                if (q.data == q.next.data) {
                    q.next = q.next.next;
                } else {
                    q = q.next;
                }
            }
            p = p.next;
        }
    }

    //    找到倒数第k个节点
    public Node findElem(Node head, int k) {
        if (k < 1) {
            return null;
        }
        Node p1 = head;
        Node p2 = head;

        for (int i = 0; i < k - 1 && p1 != null; i++) {
            p1 = p1.next;
        }
        if (p1 != null) {
            System.out.println("k不合法");
            return null;
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    //   实现链表的反转
    public Node reverse(Node head) {
        Node phead = new Node(0);
        phead.next = head;
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = phead;
        Node cur = head;
        Node nex = head.next;
        while (cur.next != null) {
            cur.next = pre;
            pre = cur;
            cur = nex;
            nex = nex.next;
        }
        return cur;

    }

    //    从尾到头输出单链表
    public void print(Node head) {
        if (head == null) {
            return;
        } else {
            print(head.next);
            System.out.println(head.data);
        }
    }

    //    寻找单链表的中间节点
    public Node searchNode(Node head) {
        if (head == null) {
            return null;
        }
        Node p = head;
        Node q = head;
        while (p != null && p.next != null && p.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        return p;

    }
    // 判断一个单链表是否成环


    public boolean isCircle(Node head) {
        if (head == null) {
            return false;
        }
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
//    判断链表是否成环，并且找到环的入口
    public Node findCircle(Node head){
        Node fast=head;
        Node slow=head;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                break;
        }
        if(fast!=null&&fast.next!=null){
            slow=head;
            while (slow!=fast){
                slow=slow.next;
                fast=fast.next;
            }
            return slow;
        }else {
            return null;
        }
    }
//    删除链表的给定节点
    public void deleteNode(Node node){
        if(node!=null&&node.next!=null){
            node.data=node.next.data;
            node.next=node.next.next;
        }
    }
// 判断两个链表是否相交
    public boolean isCommit(Node head1,Node head2){
        Node p1=head1;
        Node p2=head2;
        while (p1.next!=null){
            p1=p1.next;
        }
        while (p2.next!=null){
            p2=p2.next;
        }
        return p1==p2;
    }
//   判断两个单链表是否相交，并找出交点
    public Node findNode(Node head1,Node head2){
        if(head1==null||head2==null){
            return null;
        }
        Node tail1=head1;
        Node tail2=head2;
        int len1=1;
        int len2=1;
        while (tail1.next!=null){
            tail1=tail1.next;
            len1++;
        }
        while (tail2.next!=null){
            tail2=tail2.next;
            len2++;
        }
        if(tail1!=tail2){
            return null;
        }
        Node t1=head1;
        Node t2=head2;

        if(len1<len2){
            int d=len2-len1;
            while (d!=0){
                t1=t1.next;
                d--;
            }
        }
        if(len2<len1){
            int d=len1-len2;
            while (d!=0){
                t2=t2.next;
                d--;
            }
        }
        while (t1!=t2){
            t1=t1.next;
            t2=t2.next;
        }
        return t2;
    }
}


class Node {
    int data;
    Node next = null;

    public Node(int data) {
        this.data = data;
    }
}
