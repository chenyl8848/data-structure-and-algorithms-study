package com.cyl.datastructure.recursion;

/**
 * @author：Java陈序员
 * @date：2023/11/12 22:52
 * @description：八皇后问题
 */
public class EightQueueDemo {

    public static void main(String[] args) {
        EightQueue eightQueue = new EightQueue(8);
        eightQueue.check(0);

        System.out.printf("一共有 %d 种解法\n", eightQueue.getCount());
        System.out.printf("一共判断冲突的次数： %d", eightQueue.getJudgeCount());
    }
}

class EightQueue {

    /**
     * 表示有多少个皇后
     */
    private int max;

    private int[] array;

    private int count = 0;

    private int judgeCount = 0;

    public EightQueue(int max) {
        this.max = max;
        this.array = new int[max];
    }

    public void print() {
        this.count++;
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }

    public boolean judge(int n) {
        this.judgeCount++;
        for (int i = 0; i < n; i++) {
            // 1.this.array[n] == this.array[i] 表示判断第 n 个皇后是否和前面的 n - 1 个皇后在同一列
            // 2.Math.abs(n - i) == Math.abs(this.array[n] - this.array[i])
            //  表示判断第 n 个皇后是否和第 i 个皇后在同一斜线上
            // 3.因为 n 在增加，所以不用判断是否在同一行
            if (this.array[n] == this.array[i]
                    || Math.abs(n - i) == Math.abs(this.array[n] - this.array[i])) {
                return false;
            }

        }

        return true;
    }

    /**
     * n 代表第几个皇后
     *
     * @param n
     */
    public void check(int n) {
        if (this.max == n) {
            print();
            return;
        }

        // 依次放入皇后，判断是否有冲突
        for (int i = 0; i < this.max; i++) {
            // 先把当前皇后 n 放入该行的第 1 列
            array[n] = i;

            // 判断是否冲突
            if (judge(n)) {
                // 不冲突，接着放 n + 1
                check(n + 1);
            }

            // 如果冲突，就继续执行 array[n] = i ，即将第 n 个皇后放置在本行后移一位的位置
        }
    }

    public int getCount() {
        return count;
    }

    public int getJudgeCount() {
        return judgeCount;
    }
}
