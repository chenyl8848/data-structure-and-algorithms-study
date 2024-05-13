package com.codechen.datastructure.linkedlist;

/**
 * @author：Java陈序员
 * @date：2023/10/28 16:33
 * @description：约瑟夫环问题
 */
public class JosephDemo {

    public static void main(String[] args) {
        SingleCircleLinkedList singleCircleLinkedList = new SingleCircleLinkedList();

        System.out.println("单向循环链表初始化~");
        singleCircleLinkedList.init(5);
        singleCircleLinkedList.list();

        System.out.println("测试初圈");
        singleCircleLinkedList.countBoy(3, 3, 5);
    }
}

class Boy {

    public int no;

    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

}

class SingleCircleLinkedList {

    // 先创建一个 first 节点
    private Boy first;

    /**
     * 初始化环形链表
     *
     * @param numbers 要添加节点的个数
     */
    public void init(int numbers) {
        // 对 numbers 进行数据校验
        if (numbers < 1) {
            System.out.println("numbers 的至少大于1");
            return;
        }

        // 辅助指针
        Boy currentBoy = null;

        for (int i = 1; i <= numbers; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
                currentBoy = first;
            } else {
                currentBoy.next = boy;
                boy.next = first;
                currentBoy = boy;

            }
        }
    }

    /**
     * 遍历单向环形链表
     */
    public void list() {
        if (first == null) {
            System.out.println("单向环形链表为空~");
            return;
        }

        Boy currentBoy = first;
        while (true) {
            System.out.printf("当前节点编号：%d\n", currentBoy.no);
            if (currentBoy.next == first) {
                break;
            }
            currentBoy = currentBoy.next;
        }
    }

    /**
     * 统计输出小孩出圈
     *
     * @param startNo  从第几个小孩开始数数
     * @param countNum 表示每次数几个
     * @param nums     表示最初圈里有多少个孩子
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 对数据进行校验
        if (first == null || startNo < 1 || startNo > nums || countNum < 1) {
            System.out.println("参数输入有误");
            return;
        }

        // 使用遍历，找到单向循环链表的最后一个节点
        Boy helperBoy = first;
        while (true) {
            if (helperBoy.next == first) {
                break;
            }
            helperBoy = helperBoy.next;
        }

        // 移动 startNo - 1 次，找到开始数数的那个节点编号
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helperBoy = helperBoy.next;
        }

        while (true) {
            if (helperBoy == first) {
                break;
            }

            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                helperBoy = helperBoy.next;
            }

            // 此时 first 的节点要初圈
            System.out.printf("编号为：%d初圈\n", first.no);

            first = first.next;
            helperBoy.next = first;
        }
        System.out.printf("最后留圈的编号：%d", first.no);
    }
}
