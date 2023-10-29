package com.cyl.datastructure.stack;

import java.util.Scanner;

/**
 * @author：Java陈序员
 * @date：2023/10/29 19:57
 * @description：使用链表模拟栈升级版
 */
public class LinkedListStackPlusDemo {

    public static void main(String[] args) {
        LinkedListStackPlus linkedListStackPlus = new LinkedListStackPlus(4);

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";

        while (flag) {
            System.out.println("exit:退出程序");
            System.out.println("push:数据入栈");
            System.out.println("pop:数据出栈");
            System.out.println("list:遍历栈");

            key = scanner.next();

            switch (key) {
                case "exit":
                    scanner.close();
                    flag = false;
                    break;
                case "push":
                    int value = scanner.nextInt();
                    linkedListStackPlus.push(value);
                    break;
                case "pop":
                    try {
                        int pop = linkedListStackPlus.pop();
                        System.out.printf("出栈数据：%d\n", pop);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case "list":
                    linkedListStackPlus.list();
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序成功~");
    }
}

class NodePlus {
    public int data;

    public NodePlus next;

    public NodePlus(int data) {
        this.data = data;
    }
}

class SingleLinkedListPlus {

    public NodePlus head = new NodePlus(-1);

    /**
     * 获取链表有效节点的个数
     *
     * @return
     */
    public int getLength() {
        if (head.next == null) {
            return 0;
        }

        int sum = 0;

        NodePlus tempNode = head.next;
        while (tempNode != null) {
            sum++;
            tempNode = tempNode.next;
        }

        return sum;
    }

    /**
     * 获取第 K 个节点
     *
     * @return
     */
    public NodePlus getKNode(int k) {

        if (head.next == null) {
            throw new RuntimeException("链表为空~");
        }

        int length = getLength();
        if (k < 1 || k > length) {
            throw new IllegalArgumentException("参数k至少要大于等于1且要小于等于链表的有效节点个数~");
        }

        NodePlus tempNode = head;

        for (int i = 0; i < k; i++) {
            tempNode = tempNode.next;
        }

        return tempNode;
    }
}

class LinkedListStackPlus {

    private int maxSize;

    private int top = -1;

    private SingleLinkedListPlus stack = new SingleLinkedListPlus();

    private NodePlus head = stack.head;

    private NodePlus currentNode = head;

    public LinkedListStackPlus(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 判断栈否已满
     * @return
     */
    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        // 判断栈是否已满
        if (isFull()) {
            System.out.println("栈已满~");
            return;
        }

        NodePlus nodePlus = new NodePlus(value);

        this.top++;
        currentNode.next = nodePlus;

        currentNode = currentNode.next;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        // 判栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空~");
        }

        int value = currentNode.data;

        // 需要删除最后一个节点
        currentNode = head;
        for (int i = 0; i < this.top; i++) {
            currentNode = currentNode.next;
        }
        currentNode.next = null;

        this.top--;
        return value;
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空~");
            return;
        }

        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, this.stack.getKNode(i + 1).data);
        }
    }
}

