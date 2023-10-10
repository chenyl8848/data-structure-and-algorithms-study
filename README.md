# 数据结构与算法

## 1. 概述

### 1.1 基本概念

数据结构是数据的组织形式

- 数据元素间的逻辑关系，即数据的逻辑结构；
- 数据元素及其关系在计算机存储内的表示，即数据的存储表示；
- 数据的运算，即对数据元素施加的操作。

算法是指操作数据的一组方法

数据结构是为算法服务的，而算法要作用在特定的数据结构上。

程序 = 数据结构 + 算法

数据结构是算法的基础, 换言之，想要学好算法，需要把数据结构学到位。

### 1.2 线性结构与非线性结构

数据结构包括：线性结构和非线性结构。

**线性结构**

线性结构作为最常用的数据结构，其特点是数据元素之间存在一对一的线性关系。

线性结构有两种不同的存储结构，即**顺序存储结构**和**链式存储结构**。

- 顺序存储的线性表称为顺序表，顺序表中的存储元素是连续的。
- 链式存储的线性表称为链表，链表中的存储元素不一定是连续的，元素节点中存放数据元素以及相邻元素的地址信息。

线性结构常见的有：**数组**、**队列**、**链表**和**栈**。

**非线性结构**

非线性结构包括：**二维数组**、**多维数组**、**广义表**、**树结构**、**图结构**。

## 2. 稀疏数组和队列

### 2.1 稀疏数组

#### 2.1.1 基本介绍

> 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。

稀疏数组的处理方法是:
记录数组一共有几行几列，有多少个不同的值， 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模。

#### 2.2.2 应用实例

**思路分析**

二维数组转稀疏数组

稀疏数组转二维数组

#### 2.2.3 代码实现

```java
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
```

### 2.2 队列