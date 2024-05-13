package com.codechen.datastructure.queue;

import java.util.Scanner;

/**
 * @author：Java陈序员
 * @date：2023/10/21 16:02
 * @description：使用数组模拟队列
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        // 创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);

        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：推出程序");
            System.out.println("a(add)：数据入队");
            System.out.println("g(get)：数据出队");
            System.out.println("h(head)：查看队头");

            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入一个值");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int headValue = arrayQueue.getQueue();
                        System.out.printf("取出的数据:%d\n", headValue);
                    } catch (Exception e) {
//                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = arrayQueue.peekQueue();
                        System.out.printf("队头:%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    break;
            }
        }

        System.out.println("程序退出~");

    }
}

class ArrayQueue {

    /**
     * 表示数组的最大容量
     */
    private int maxSize;

    /**
     * 队头
     */
    private int front;

    /**
     * 队尾
     */
    private int rear;

    /**
     * 存放数据，模拟队列
     */
    private int[] array;

    /**
     * 构造器
     *
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = -1;
        rear = -1;
    }

    /**
     * 判断队列是否为满
     *
     * @return
     */
    public boolean isFull() {
        return this.rear == this.maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    /**
     * 数据入队
     *
     * @param data
     */
    public void addQueue(int data) {
        // 判断队列是否满
        if (this.isFull()) {
            System.out.println("队列已满");
            return;
        }

        // rear 后移
        this.rear++;
        this.array[rear] = data;
    }

    /**
     * 数据出队
     *
     * @return
     */
    public int getQueue() {
        // 判断队列是否为空
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }

        this.front++;
        return this.array[this.front];
    }

    /**
     * 显示所有的队列数据
     */
    public void showQueue() {
        if (this.isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        for (int i = 0; i < this.array.length; i++) {
            System.out.printf("array[%d]=%d\n", i, this.array[i]);
        }
    }

    /**
     * 获取队列的头数据，但是不出队
     *
     * @return
     */
    public int peekQueue() {
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }

        return this.array[front + 1];
    }
}
