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

### 2.3 队列

#### 2.3.1 基本介绍

- 队列是一个有序列表，可以用**数组**或**链表**来实现
- 遵循先入先出的原则，即：先存入队列的数据，先取出，后存入的数据后取出

#### 2.3.2 数组模拟队列

思路分析：

1. 队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图, 其中 `maxSize` 是该队列的最大容量。
2. 因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 `front` 及 `rear` 分别记录队列前后端的下标。`front` 会随着数据输出而改变，而 `rear` 则是随着数据输入而改变，如图所示:

![./doc/images/queue.png](img.png)

将数据存入队列时称为**入队**，**入队**的处理需要有两个步骤：
1. 判断队列是否为满：当 `rear = maxSize - 1` 时队列已满
2. 若尾指针 `rear` 小于队列的最大下标 `maxSize - 1`，则将数据存入 `rear` 所指的数组元素中，并将尾指针往后移：`rear + 1`

将数据从队列取出时称为**出队**，**出队**的处理需要有两个步骤：
1. 判断队列是否为空：当 `front = rear` 时队列为空
2. 若 `front != rear`，则将数组元素中下标位置为 `front + 1` 的数据取出，并将头指针往后移：`front + 1`

代码实现：

```java
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
        return this.array[front];
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
            System.out.printf("array[%d]=%d\n", i, array[i]);
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
```

存在的问题和优化：
1. 目前数组只能使用一次就不能使用，没有达到复用的效果
2. 可以使用 % 运算改造成一个**环形队列**

#### 2.3.3 数组模拟环形队列

对前面的数组模拟队列的优化，可以通过取模的方式将数组看做是一个环形的。

分析说明：
1. `front`: 指向队列的第一个元素，初始值为 0
2. `rear`: 指向队列的最后一个元素的后一个位置，初始值为 0
3. 队满：`(rear + 1) % maxSize == front`
4. 队空：`front == rear`
5. 队列中有效数据的个数：`(rear + maxSize - front) % maxSize`

代码实现：
```java
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
```


