package com.codechen.datastructure.linkedlist;

/**
 * @author：Java陈序员
 * @date：2023/10/28 15:15
 * @description：双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(new Node(1, "宋江", "及时雨"));
        doubleLinkedList.add(new Node(2, "卢俊义", "玉麒麟"));
        doubleLinkedList.add(new Node(3, "吴用", "智多星"));
        doubleLinkedList.add(new Node(4, "公孙胜", "入云龙"));
        doubleLinkedList.add(new Node(10, "花荣", "小李广"));

        // 遍历双向链表
        System.out.println("遍历双向链表~");
        doubleLinkedList.list();

        // 按顺序添加节点
        System.out.println("按顺序添加节点~");
        doubleLinkedList.addByOrder(new Node(2, "卢俊义", "玉麒麟"));
        doubleLinkedList.addByOrder(new Node(6, "林冲", "豹子头"));
        doubleLinkedList.list();

        // 修改双向链表
        System.out.println("修改双向链表~");
        doubleLinkedList.update(new Node(5, "关胜", "大刀"));
        doubleLinkedList.update(new Node(4, "关胜", "大刀"));
        doubleLinkedList.list();

        // 删除双向链表
        System.out.println("删除双向链表1~");
        doubleLinkedList.delete(5);
        doubleLinkedList.list();
        System.out.println("删除双向链表2~");
        doubleLinkedList.delete(4);
        doubleLinkedList.list();
        System.out.println("删除双向链表3~");
        doubleLinkedList.delete(2);
        doubleLinkedList.list();
    }
}

class Node {
    public int no;

    public String name;

    public String nickName;

    /**
     * 指向前一个节点
     */
    public Node pre;

    /**
     * 指向下一个节点
     */
    public Node next;

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

class DoubleLinkedList {

    private Node head = new Node(0, "", "");

    /**
     * 遍历双向链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("双线链表为空");
            return;
        }

        Node node = head.next;
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    /**
     * 在链表末尾添加节点
     *
     * @param newNode
     */
    public void add(Node newNode) {
        Node node = head;

        // 遍历找到最后一个节点
        while (true) {
            if (node.next == null) {
                break;
            }
            node = node.next;
        }

        node.next = newNode;
        newNode.pre = node;
    }

    /**
     * 按照顺序添加节点
     *
     * @param node
     */
    public void addByOrder(Node node) {

        Node tempNode = head;
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }
            if (tempNode.no > node.no) {
                break;
            } else if (tempNode.no == node.no) {
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (flag) {
            System.out.printf("要插入的英雄编码 %d 已存在，不能加入了\n", node.no);
        } else {
            tempNode.pre.next = node;
            tempNode.pre = node;
            node.next = tempNode;
        }
    }

    /**
     * 修改节点信息
     *
     * @param node
     */
    public void update(Node node) {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        Node tempNode = head.next;
        boolean flag = false;
        while (tempNode != null) {
            if (tempNode.no == node.no) {
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }

        if (flag) {
            tempNode.name = node.name;
            tempNode.nickName = node.nickName;
        } else {
            System.out.println("没有找到要修改的节点");
        }
    }

    /**
     * 删除节点信息
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        Node node = head.next;
        boolean flag = false;

        while (node != null) {
            if (node.no == no) {
                flag = true;
                break;
            }

            node = node.next;
        }

        if (flag) {
            // 要删除节点的前一个节点指向要删除节点的下一个节点
            node.pre.next = node.next;
            // 要删除节点的后一个节点指向要删除节点的前一个节点
            // 如果要删除的是最后一个节点呢？
            if (node.next != null) {
                node.next.pre = node.pre;
            }
        } else {
            System.out.println("没有找到要删除的节点信息");
        }
    }
}
