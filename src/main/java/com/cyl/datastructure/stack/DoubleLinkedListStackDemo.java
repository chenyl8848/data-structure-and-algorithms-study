package com.cyl.datastructure.stack;

import java.util.Scanner;

/**
 * @author：Java陈序员
 * @date：2023/10/29 20:20
 * @description：双向链表模拟栈
 */
public class DoubleLinkedListStackDemo {

    public static void main(String[] args) {
        DoubleLinkedListStack doubleLinkedListStack = new DoubleLinkedListStack(4);

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
                    doubleLinkedListStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = doubleLinkedListStack.pop();
                        System.out.printf("出栈数据：%d\n", pop);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case "list":
                    doubleLinkedListStack.list();
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序成功~");
    }
}

class DoubleNode {

    public int data;

    public DoubleNode next;

    public DoubleNode pre;

    public DoubleNode(int data) {
        this.data = data;
    }
}

class DoubleLinkedList {

    public DoubleNode head = new DoubleNode(-1);

}

class DoubleLinkedListStack {

    private int maxSize;

    private int top = -1;

    public DoubleLinkedList stack = new DoubleLinkedList();

    public DoubleNode head = stack.head;

    /**
     * 链表的最后一个节点
     */
    public DoubleNode currentNode = head;

    public DoubleLinkedListStack(int maxSize) {
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
        // 判断栈是否已满
        if (isFull()) {
            System.out.println("栈已满~");
            return;
        }

        DoubleNode doubleNode = new DoubleNode(value);

        currentNode.next = doubleNode;
        doubleNode.pre = currentNode;

        currentNode = currentNode.next;
        this.top++;
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

        int data = currentNode.data;

        // 将最后一个节点往前移动
        currentNode = currentNode.pre;

        currentNode.next = null;
        this.top--;

        return data;
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空~");
            return;
        }

        DoubleNode tempNode = currentNode;
        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, tempNode.data);
            tempNode = tempNode.pre;
        }
    }
}
