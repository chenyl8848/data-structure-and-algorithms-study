package com.cyl.datastructure.stack;

import java.util.Scanner;

/**
 * @author：Java陈序员
 * @date：2023/10/28 21:37
 * @description：用数组模拟栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);

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
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        System.out.printf("出栈数据：%d\n", pop);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case "list":
                    arrayStack.list();
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序成功~");
    }
}

class ArrayStack {
    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 将数据放在数组中
     */
    private int[] stack;

    /**
     * 栈顶
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
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
        top++;
        this.stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        // 先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空~");
        }

        int value = this.stack[top];
        top--;

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
            System.out.printf("stack[%d]=%d\n", i, this.stack[i]);
        }
    }
}
