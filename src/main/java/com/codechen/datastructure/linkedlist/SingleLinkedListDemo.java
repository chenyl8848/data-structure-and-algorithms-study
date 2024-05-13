package com.codechen.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author：Java陈序员
 * @date：2023/10/21 23:43
 * @description：单链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"));
        singleLinkedList.add(new HeroNode(7, "秦明", "霹雳火"));

        System.out.println("按顺序添加节点");
        singleLinkedList.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.addByOrder(new HeroNode(6, "林冲", "豹子头"));
        singleLinkedList.list();

        System.out.println("修改节点");
        // 修改节点
        singleLinkedList.update(new HeroNode(6, "公孙胜", "入云龙"));
        singleLinkedList.list();

        // 删除节点
        System.out.println("删除节点");
        singleLinkedList.delete(2);
        singleLinkedList.list();

        System.out.println("删除节点");
        singleLinkedList.delete(6);
        singleLinkedList.list();

        singleLinkedList.delete(60);

        int length = singleLinkedList.getLength(singleLinkedList.getHead());
        System.out.printf("length=%d\n", length);

        System.out.println("获取单链表的倒数第k个节点");
        int k = -3;
        HeroNode kNode = singleLinkedList.getKNode(singleLinkedList.getHead(), k);
        System.out.printf("倒数第%d个节点数据:", k);
        System.out.println(kNode);

        System.out.println("反转链表");
        singleLinkedList.reverse(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("从尾到头逆序打印单链表");
        singleLinkedList.reverseShow(singleLinkedList.getHead());

        System.out.println("合并单链表并保持有序");
        SingleLinkedList linkedList1 = new SingleLinkedList();
        linkedList1.add(new HeroNode(1, "1", "1"));
        linkedList1.add(new HeroNode(2, "2", "2"));
        linkedList1.add(new HeroNode(3, "3", "3"));
        linkedList1.add(new HeroNode(5, "5", "5"));
        linkedList1.add(new HeroNode(9, "9", "9"));
        System.out.println("单链表1:");
        linkedList1.list();

        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.add(new HeroNode(2, "2", "2"));
        linkedList2.add(new HeroNode(4, "4", "4"));
        linkedList2.add(new HeroNode(6, "6", "6"));
        linkedList2.add(new HeroNode(8, "8", "8"));
        linkedList2.add(new HeroNode(10, "10", "10"));
        linkedList2.add(new HeroNode(12, "12", "12"));
        linkedList2.add(new HeroNode(14, "14", "14"));
        System.out.println("单链表2:");
        linkedList2.list();

        HeroNode heroNode = singleLinkedList.merge(linkedList1.getHead(), linkedList2.getHead());
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.setHead(heroNode);
        System.out.println("合并之后的新链表:");
        linkedList.list();
    }
}

class HeroNode {
    public int no;

    public String name;

    public String nickName;

    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
//                ", next=" + next +
                '}';
    }
}

class SingleLinkedList {

    /**
     * 定义一个头节点
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    /**
     * 在链表的末尾添加节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 找到链表的最后一个节点
        HeroNode tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                break;
            }

            // 如果没有找到最后一个节点，将 tempNode 后移
            tempNode = tempNode.next;
        }

        // 将最后一个节点的 next 指向新节点
        tempNode.next = heroNode;
    }

    /**
     * 将节点添加到指定节点
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {

        HeroNode tempNode = head;
        // 标识编号是否存在
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.next.no > heroNode.no) {
                // 位置找到了，就在 tempNode 的后面插入
                break;
            } else if (tempNode.next.no == heroNode.no) {
                // 编号已存在
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (!flag) {
            heroNode.next = tempNode.next;
            tempNode.next = heroNode;
        } else {
            System.out.printf("要插入的英雄编码 %d 已存在，不能加入了\n", heroNode.no);
        }
    }

    /**
     * 根据节点编号来修改
     *
     * @param heroNode
     */
    public void update(HeroNode heroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }

        // 找到要修改的节点
        HeroNode tempNode = head.next;
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.no == heroNode.no) {
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (flag) {
            tempNode.name = heroNode.name;
            tempNode.nickName = heroNode.nickName;
        } else {
            System.out.println("没有找到要修改的节点");
        }
    }

    /**
     * 删除节点
     *
     * @param no
     */
    public void delete(int no) {
        // 0.判断链表是否为空
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }

        // 1.找到要删除节点的前一个节点
        HeroNode tempNode = head.next;
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.next.no == no) {
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (flag) {
            tempNode.next = tempNode.next.next;
        } else {
            System.out.println("没有找到要删除的节点");
        }
    }

    /**
     * 打印链表
     */
    public void list() {
        if (head.next == null) {
            return;
        }

        HeroNode tempNode = head.next;

        while (true) {
            if (tempNode == null) {
                break;
            }

            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }

    /**
     * 获取单链表的节点个数，如果是带头节点的单链表，不统计头节点
     *
     * @param head
     * @return
     */
    public int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }

        HeroNode currentNode = head.next;
        int length = 0;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }

        return length;
    }

    /**
     * 获取单链表倒数的第 k 个节点
     *
     * @param k
     * @return
     */
    public HeroNode getKNode(HeroNode head, int k) {
        // 0.判断链表是否为空
        if (head.next == null) {
            return null;
        }

        // 1.先遍历获取单链表的有效个数 length
        int length = getLength(head);

        // 2.校验 k 是否正确
        if (k <= 0 || k > length) {
            return null;
        }

        // 3.从链表的第一个节点开始遍历 length - k 个即可得到
        HeroNode currentNode = head.next;
        for (int i = 0; i < length - k; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public void reverse(HeroNode head) {
        // 0.判断链表是否为空
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 1.定义一个新的头节点
        HeroNode newHead = new HeroNode(-1, null, null);

        // 2.遍历原链表
        HeroNode currentNode = head.next;
        HeroNode nextNode = null;

        while (currentNode != null) {
            // 1.3使用一个临时变量保存当前节点的下一个节点
            nextNode = currentNode.next;
            // 1.2当前节点的下一个节点执行头节点的下一个节点
            currentNode.next = newHead.next;
            // 1.1头节点的下一个节点指向当前节点
            newHead.next = currentNode;
            // 1.4当前节点等于下一个节点
            currentNode = nextNode;
        }

        head.next = newHead.next;
    }

    /**
     * 从尾到头逆序打印单链表
     */
    public void reverseShow(HeroNode head) {
        if (head.next == null) {
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode currentNode = head.next;
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并连个有序的单链表，并且保持有序
     *
     * @param head1
     * @param head2
     * @return
     */
    public HeroNode merge(HeroNode head1, HeroNode head2) {
        HeroNode newHead = new HeroNode(0, "", "");

        HeroNode currentNode1 = head1.next;
        HeroNode currentNode2 = head2.next;
        HeroNode tempNode = newHead;

        while (currentNode1 != null && currentNode1 != null) {
            if (currentNode1.no < currentNode2.no) {
                tempNode.next = currentNode1;
                // 如果 currentNode1 < currentNode2, currentNode1 就往后移
                currentNode1 = currentNode1.next;
                tempNode = tempNode.next;
            } else if (currentNode1.no == currentNode2.no) {
                tempNode.next = currentNode1;
                // 如果 currentNode1 == currentNode2, currentNode1、currentNode2 就往后移
                currentNode1 = currentNode1.next;
                currentNode2 = currentNode2.next;
                tempNode = tempNode.next;
            } else {
                // 如果 currentNode1 > currentNode2, currentNode2 就往后移
                tempNode.next = currentNode2;
                currentNode2 = currentNode2.next;
                tempNode = tempNode.next;
            }
        }

        if (currentNode1 == null) {
            tempNode.next = currentNode2;
        }

        if (currentNode2 == null) {
            tempNode.next = currentNode1;
        }

        return newHead;
    }
}
