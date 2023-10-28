package com.cyl.datastructure.stack;

import java.util.Scanner;

/**
 * @author：Java陈序员
 * @date：2023/10/28 22:54
 * @description：链表模拟栈
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(4);

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
                    linkedListStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = linkedListStack.pop();
                        System.out.printf("出栈数据%d\n", pop);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case "list":
                    linkedListStack.list();
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序成功~");
    }
}

class Node {

    private int data;

    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}

class SingleLinkedList {
    private Node head = new Node(-1);

    /**
     * 在末尾添加节点
     *
     * @param node
     */
    public void add(Node node) {

        Node nextNode = head;

        while (true) {
            if (nextNode.next == null) {
                break;
            }
            nextNode = nextNode.next;
        }

        nextNode.next = node;
    }

    /**
     * 获取并移除最后一个节点
     *
     * @return
     */
    public Node getAndRemoveLastNode() {
        if (head.next == null) {
            throw new RuntimeException("链表为空~");
        }

        Node nextNode = head;

        while (true) {
            if (nextNode.next.next == null) {
                break;
            }
            nextNode = nextNode.next;
        }

        Node lastNode = nextNode.next;
        nextNode.next = null;

        return lastNode;

    }

    /**
     * 获取倒数第 K 个节点
     *
     * @return
     */
    public Node getKNode(int k) {

        if (head.next == null) {
            throw new RuntimeException("链表为空~");
        }

        int length = getLength();
        if (k < 1 || k > length) {
            throw new IllegalArgumentException("参数k至少要大于等于1且要小于等于链表的有效节点个数~");
        }

        Node tempNode = head;

        for (int i = 0; i < k; i++) {
            tempNode = tempNode.next;
        }

        return tempNode;
    }

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

        Node tempNode = head.next;
        while (tempNode != null) {
            sum++;
            tempNode = tempNode.next;
        }

        return sum;
    }
}

class LinkedListStack {

    private int maxSize;

    private SingleLinkedList stack = new SingleLinkedList();

    private int top = -1;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满~");
            return;
        }

        this.top++;
        this.stack.add(new Node(value));
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空~");
        }

        this.top--;
        return this.stack.getAndRemoveLastNode().getData();
    }

    /**
     * 遍历打印栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空~");
            return;
        }

        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, this.stack.getKNode(i + 1).getData());
        }
    }
}
