package com.codechen.datastructure.queue;

import java.util.Scanner;

/**
 * @author：Java陈序员
 * @date：2023/10/21 16:53
 * @description：
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // 创建一个队列
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(3);

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
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入一个值");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int headValue = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据:%d\n", headValue);
                    } catch (Exception e) {
//                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = circleArrayQueue.peekQueue();
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

class CircleArrayQueue {

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
    private int array[];

    /**
     * 构造器
     *
     * @param maxSize
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[this.maxSize];
    }

    /**
     * 判断队列是否为满
     *
     * @return
     */
    public boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    /**
     * 数据入队
     *
     * @param data
     */
    public void addQueue(int data) {
        if (this.isFull()) {
            System.out.println("队列已满");
            return;
        }

        this.array[this.rear] = data;
        this.rear = (this.rear + 1) % this.maxSize;
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

        int value = this.array[this.front];
        this.front = (this.front + 1) % this.maxSize;
        return value;
    }

    /**
     * 显示所有的队列数据
     */
    public void showQueue() {
        if (this.isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        // 从 front 开始遍历，遍历多少个元素
        for (int i = this.front; i < this.front + this.size(); i++) {
            System.out.printf("array[%d]=%d\n", i % this.maxSize, array[i % this.maxSize]);
        }
    }

    /**
     * 获取当前队列有效数字的个数
     */
    public int size() {
        return (this.rear + this.maxSize - this.front) % this.maxSize;
    }

    public int peekQueue() {
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }

        return this.array[front];
    }
}
