package com.cyl.datastructure;

/**
 * @author：Java陈序员
 * @date：2023/10/10 23:04
 * @description：稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        // 二维数组 => 稀疏数组

        // 1.先创建一个二维数组
        // 11*11 的棋盘 0-没有 1-白子 2-黑子
        int chessboard1[][] = new int[11][11];

        // 赋值
        chessboard1[1][2] = 1;
        chessboard1[2][3] = 2;
        chessboard1[3][4] = 1;

        // 打印棋盘信息
        for (int chess[] : chessboard1) {
            for (int data : chess) {
                System.out.printf("%d\t", data);
            }

            System.out.println();
        }

        // 2.统计二维数组中非 0 元素的个数
        int sum = 0;
        for (int i = 0; i < chessboard1.length; i++) {
            for (int j = 0; j < chessboard1[i].length; j++) {
                if (chessboard1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("二维数组中非0元素个数：" + sum);

        // 3.创建一个稀疏数组
        int sparseArray[][] = new int[sum + 1][3];

        // 4.给二维数组的第一行赋值
        sparseArray[0][0] = chessboard1.length;
        sparseArray[0][1] = chessboard1[0].length;
        sparseArray[0][2] = sum;

        // 5.遍历二维数组转成稀疏数组
        int count = 0;
        for (int i = 0; i < chessboard1.length; i++) {
            for (int j = 0; j < chessboard1[i].length; j++) {
                if (chessboard1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessboard1[i][j];
                }
            }
        }

        // 遍历输出稀疏数组
        System.out.println("二维数组转化为稀疏数组为：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        // 稀疏数组 => 二维数组
        // 1.创建一个二维数组
        int chessboard2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        // 2.遍历稀疏数组转为二维数组
        for (int i = 1; i < sparseArray.length; i++) {
            chessboard2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        // 遍历输出二维数组
        System.out.println("稀疏数组转二维数组为：");
        for (int chess[] : chessboard2) {
            for (int data : chess) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
