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

如图将二维数组转成稀疏数组：

![二维数组](./doc/images/TwoArray.png)

![稀疏数组](./doc/images/SparseArray.png)

#### 2.2.2 应用实例

**思路分析**

二维数组转稀疏数组：

1. 遍历原始的二维数组，得到有效数据的个数 sum
2. 根据 sum 就可以创建稀疏数组 `int sparseArray[][] = new int[sum + 1][3]`

3. 将二维数组的有效数据数据存入到稀疏数组

稀疏数组转二维数组：

1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组 `int twoArray[][] = new int[sparseArry[0][0]][sparseArry[0][1]]` 
2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组

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

![队列入队出队示意图](./doc/images/queue.png)

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

## 3.链表

### 3.1 基本介绍

链表是有序的列表，但是它在内存中是存储如下：

![linkedlist.png](./doc/images/linkedlist.png)

- 链表是以节点的方式来存储,是链式存储
- 每个节点包含 `data` 域保存数据，`next` 域指向下一个节点
- 链表的各个节点不一定是连续存储
- 链表分带头节点的链表和没有头节点的链表

### 3.2 单链表

单链表(带头结点) **逻辑结构**示意图如下：
![linkedlist-logic.png](./doc/images/linkedlist-logic.png)

#### 3.2.1 基本操作

实现对单链表的新增、修改、删除操作。

代码实现：
```java
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"));
        singleLinkedList.add(new HeroNode(7, "秦明", "霹雳火"));

        System.out.println("按顺序添加节点");
        singleLinkedList.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.addByOrder(new HeroNode(6, "林冲", "豹子头"));
        singleLinkedList.show();

        System.out.println("修改节点");
        // 修改节点
        singleLinkedList.update(new HeroNode(6, "公孙胜", "入云龙"));
        singleLinkedList.show();

        // 删除节点
        System.out.println("删除节点");
        singleLinkedList.delete(2);
        singleLinkedList.show();

        singleLinkedList.delete(6);
        singleLinkedList.show();

        singleLinkedList.delete(60);
    }
}

class HeroNode {
    public int no;

    public String name;

    public String nickName;

    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
//                ", next=" + next +
                '}';
    }
}

class SingleLinkedList {

    /**
     * 定义一个头节点
     */
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 在链表的末尾添加节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 找到链表的最后一个节点
        HeroNode tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                break;
            }

            // 如果没有找到最后一个节点，将 tempNode 后移
            tempNode = tempNode.next;
        }

        // 将最后一个节点的 next 指向新节点
        tempNode.next = heroNode;
    }

    /**
     * 将节点添加到指定节点
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {

        HeroNode tempNode = head;
        // 标识编号是否存在
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.next.no > heroNode.no) {
                // 位置找到了，就在 tempNode 的后面插入
                break;
            } else if (tempNode.next.no == heroNode.no) {
                // 编号已存在
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (!flag) {
            heroNode.next = tempNode.next;
            tempNode.next = heroNode;
        } else {
            System.out.printf("要插入的英雄编码 %d 已存在，不能加入了\n", heroNode.no);
        }
    }

    /**
     * 根据节点编号来修改
     *
     * @param heroNode
     */
    public void update(HeroNode heroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }

        // 找到要修改的节点
        HeroNode tempNode = head.next;
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.no == heroNode.no) {
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (flag) {
            tempNode.name = heroNode.name;
            tempNode.nickName = heroNode.nickName;
        } else {
            System.out.println("没有找到要修改的节点");
        }
    }

    /**
     * 删除节点
     * @param no
     */
    public void delete(int no) {
        // 0.判断链表是否为空
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }

        // 1.找到要删除节点的前一个节点
        HeroNode tempNode = head.next;
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.next.no == no) {
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (flag) {
            tempNode.next = tempNode.next.next;
        } else {
            System.out.println("没有找到要删除的节点");
        }
    }

    /**
     * 打印链表
     */
    public void show() {
        if (head.next == null) {
            return;
        }

        HeroNode tempNode = head.next;

        while (true) {
            if (tempNode == null) {
                break;
            }

            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }
}
```

#### 3.2.2 面试题

获取单链表中的有效节点个数
```java
public int getLength(HeroNode head) {
    if (head.next == null) {
        return 0;
    }

    HeroNode currentNode = head.next;
    int length = 0;
    while (currentNode != null) {
        length++;
        currentNode = currentNode.next;
    }

    return length;
}
```

获取单链表中倒数第k个节点
```java
public HeroNode getKNode(HeroNode head, int k) {
    // 0.判断链表是否为空
    if (head.next == null) {
        return null;
    }

    // 1.先遍历获取单链表的有效个数 length
    int length = getLength(head);

    // 2.校验 k 是否正确
    if (k <= 0 || k > length) {
        return null;
    }

    // 3.从链表的第一个节点开始遍历 length - k 个即可得到
    HeroNode currentNode = head.next;
    for (int i = 0; i < length - k; i++) {
        currentNode = currentNode.next;
    }

    return currentNode;
}
```

单链表反转
```java
public void reverse(HeroNode head) {
    // 0.判断链表是否为空
    if (head.next == null || head.next.next == null) {
        return;
    }

    // 1.定义一个新的头节点
    HeroNode newHead = new HeroNode(-1, null, null);

    // 2.遍历原链表
    HeroNode currentNode = head.next;
    HeroNode nextNode = null;

    while (currentNode != null) {
        // 1.3使用一个临时变量保存当前节点的下一个节点
        nextNode = currentNode.next;
        // 1.2当前节点的下一个节点执行头节点的下一个节点
        currentNode.next = newHead.next;
        // 1.1头节点的下一个节点指向当前节点
        newHead.next = currentNode;
        // 1.4当前节点等于下一个节点
        currentNode = nextNode;
    }

    head.next = newHead.next;
}
```

从尾到头逆序打印单链表
```java
public void reverseShow(HeroNode head) {
    if (head.next == null) {
        return;
    }

    Stack<HeroNode> stack = new Stack<>();
    HeroNode currentNode = head.next;
    while (currentNode != null) {
        stack.push(currentNode);
        currentNode = currentNode.next;
    }

    while (stack.size() > 0) {
        System.out.println(stack.pop());
    }
}
```

合并连个有序的单链表，并且保持有序
```java
public HeroNode merge(HeroNode head1, HeroNode head2) {
    HeroNode newHead = new HeroNode(0, "", "");

    HeroNode currentNode1 = head1.next;
    HeroNode currentNode2 = head2.next;
    HeroNode tempNode = newHead;

    while (currentNode1 != null && currentNode1 != null) {
        if (currentNode1.no < currentNode2.no) {
            tempNode.next = currentNode1;
            // 如果 currentNode1 < currentNode2, currentNode1 就往后移
            currentNode1 = currentNode1.next;
            tempNode = tempNode.next;
        } else if (currentNode1.no == currentNode2.no) {
            tempNode.next = currentNode1;
            // 如果 currentNode1 == currentNode2, currentNode1、currentNode2 就往后移
            currentNode1 = currentNode1.next;
            currentNode2 = currentNode2.next;
            tempNode = tempNode.next;
        } else {
            // 如果 currentNode1 > currentNode2, currentNode2 就往后移
            tempNode.next = currentNode2;
            currentNode2 = currentNode2.next;
            tempNode = tempNode.next;
        }
    }

    if (currentNode1 == null) {
        tempNode.next = currentNode2;
    }

    if (currentNode2 == null) {
        tempNode.next = currentNode1;
    }

    return newHead;
}
```

### 3.3 双向链表

单向链表的缺点分析： 

- 单向链表查找的方向只能是一个方向；而双向链表可以向前或者向后查找
- 单向链表不能自我删除，需要靠辅助节点 ；而双向链表，则可以自我删除。单向链表删除节点时，总是要先找到待删除节点的前一个节点。

#### 3.2.1 基本操作

实现对双向链表的遍历、新增、修改、删除操作。

代码实现：

```java
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(new Node(1, "宋江", "及时雨"));
        doubleLinkedList.add(new Node(2, "卢俊义", "玉麒麟"));
        doubleLinkedList.add(new Node(3, "吴用", "智多星"));
        doubleLinkedList.add(new Node(4, "公孙胜", "入云龙"));
        doubleLinkedList.add(new Node(10, "花荣", "小李广"));

        // 遍历双向链表
        System.out.println("遍历双向链表~");
        doubleLinkedList.list();

        // 按顺序添加节点
        System.out.println("按顺序添加节点~");
        doubleLinkedList.addByOrder(new Node(2, "卢俊义", "玉麒麟"));
        doubleLinkedList.addByOrder(new Node(6, "林冲", "豹子头"));
        doubleLinkedList.list();

        // 修改双向链表
        System.out.println("修改双向链表~");
        doubleLinkedList.update(new Node(5, "关胜", "大刀"));
        doubleLinkedList.update(new Node(4, "关胜", "大刀"));
        doubleLinkedList.list();

        // 删除双向链表
        System.out.println("删除双向链表1~");
        doubleLinkedList.delete(5);
        doubleLinkedList.list();
        System.out.println("删除双向链表2~");
        doubleLinkedList.delete(4);
        doubleLinkedList.list();
        System.out.println("删除双向链表3~");
        doubleLinkedList.delete(2);
        doubleLinkedList.list();
    }
}

class Node {
    public int no;

    public String name;

    public String nickName;

    /**
     * 指向前一个节点
     */
    public Node pre;

    /**
     * 指向下一个节点
     */
    public Node next;

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

class DoubleLinkedList {

    private Node head = new Node(0, "", "");

    /**
     * 遍历双向链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("双线链表为空");
            return;
        }

        Node node = head.next;
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    /**
     * 在链表末尾添加节点
     *
     * @param newNode
     */
    public void add(Node newNode) {
        Node node = head;

        // 遍历找到最后一个节点
        while (true) {
            if (node.next == null) {
                break;
            }
            node = node.next;
        }

        node.next = newNode;
        newNode.pre = node;
    }

    /**
     * 按照顺序添加节点
     *
     * @param node
     */
    public void addByOrder(Node node) {

        Node tempNode = head;
        boolean flag = false;

        while (true) {
            if (tempNode.next == null) {
                break;
            }
            if (tempNode.no > node.no) {
                break;
            } else if (tempNode.no == node.no) {
                flag = true;
                break;
            }

            tempNode = tempNode.next;
        }

        if (flag) {
            System.out.printf("要插入的英雄编码 %d 已存在，不能加入了\n", node.no);
        } else {
            tempNode.pre.next = node;
            tempNode.pre = node;
            node.next = tempNode;
        }
    }

    /**
     * 修改节点信息
     *
     * @param node
     */
    public void update(Node node) {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        Node tempNode = head.next;
        boolean flag = false;
        while (tempNode != null) {
            if (tempNode.no == node.no) {
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }

        if (flag) {
            tempNode.name = node.name;
            tempNode.nickName = node.nickName;
        } else {
            System.out.println("没有找到要修改的节点");
        }
    }

    /**
     * 删除节点信息
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        Node node = head.next;
        boolean flag = false;

        while (node != null) {
            if (node.no == no) {
                flag = true;
                break;
            }

            node = node.next;
        }

        if (flag) {
            // 要删除节点的前一个节点指向要删除节点的下一个节点
            node.pre.next = node.next;
            // 要删除节点的后一个节点指向要删除节点的前一个节点
            // 如果要删除的是最后一个节点呢？
            if (node.next != null) {
                node.next.pre = node.pre;
            }
        } else {
            System.out.println("没有找到要删除的节点信息");
        }
    }
}
```

### 3.4 单向循环链表

![单向循环链表结构图](./doc/images/SingleCircleLinkedList.png)

### 3.5 约瑟夫环问题

**`Josephu`  问题：**

设编号为1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，数到 m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。

**思路分析：**

用一个不带头结点的循环链表来处理 `Josephu` 问题：先构成一个有 n 个结点的单循环链表，然后由 k 结点起从 1 开始计数，计到 m 时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从 1 开始计数，直到最后一个结点从链表中删除算法结束。

代码实现：

```java
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
```

## 4.栈

### 4.1 基本介绍

- 栈的英文为：**stack**
- 栈是一个**先入后出**( FILO - First In Last Out)的有序列表
- **栈(stack)**是限制线性表，元素的插入和删除只能在线性表的同一端进行
- 允许插入和删除的一端，为变化的一端，称为**栈顶(Top)**，另一端为固定的一端，称为**栈底(Bottom)**
- 根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元素最先删除，最先放入的元素最后删除

![入栈示意图](./doc/images/StackPush.png)

![出栈示意图](./doc/images/StackPop.png)

**栈的应用场景**

- 子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以回到原来的程序中
- 处理递归调用：和子程序的调用类似，只是除了储存下一个指令的地址外，也将参数、区域变量等数据存入堆栈中
- 表达式的转换[中缀表达式转后缀表达式]与求值
- 二叉树的遍历
- 图形的深度优先(depth一first)搜索法

### 4.2 数组模拟栈

![数组模拟栈示意图](./doc/images/ArrayStack.png)

代码实现：

```java
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
                        System.out.printf("出栈数据%d\n", pop);
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
```

### 4.3 链表模拟栈

#### 4.3.1 单向链表模拟栈

代码实现：

```jade
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
```

升级版：

```java
public class LinkedListStackPlusDemo {

    public static void main(String[] args) {
        LinkedListStackPlus linkedListStackPlus = new LinkedListStackPlus(4);

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
                    linkedListStackPlus.push(value);
                    break;
                case "pop":
                    try {
                        int pop = linkedListStackPlus.pop();
                        System.out.printf("出栈数据：%d\n", pop);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case "list":
                    linkedListStackPlus.list();
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序成功~");
    }
}

class NodePlus {
    public int data;

    public NodePlus next;

    public NodePlus(int data) {
        this.data = data;
    }
}

class SingleLinkedListPlus {

    public NodePlus head = new NodePlus(-1);

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

        NodePlus tempNode = head.next;
        while (tempNode != null) {
            sum++;
            tempNode = tempNode.next;
        }

        return sum;
    }

    /**
     * 获取第 K 个节点
     *
     * @return
     */
    public NodePlus getKNode(int k) {

        if (head.next == null) {
            throw new RuntimeException("链表为空~");
        }

        int length = getLength();
        if (k < 1 || k > length) {
            throw new IllegalArgumentException("参数k至少要大于等于1且要小于等于链表的有效节点个数~");
        }

        NodePlus tempNode = head;

        for (int i = 0; i < k; i++) {
            tempNode = tempNode.next;
        }

        return tempNode;
    }
}

class LinkedListStackPlus {

    private int maxSize;

    private int top = -1;

    private SingleLinkedListPlus stack = new SingleLinkedListPlus();

    private NodePlus head = stack.head;

    private NodePlus currentNode = head;

    public LinkedListStackPlus(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 判断栈否已满
     * @return
     */
    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        // 判断栈是否已满
        if (isFull()) {
            System.out.println("栈已满~");
            return;
        }

        NodePlus nodePlus = new NodePlus(value);

        this.top++;
        currentNode.next = nodePlus;

        currentNode = currentNode.next;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        // 判栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空~");
        }

        int value = currentNode.data;

        // 需要删除最后一个节点
        currentNode = head;
        for (int i = 0; i < this.top; i++) {
            currentNode = currentNode.next;
        }
        currentNode.next = null;

        this.top--;
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
            System.out.printf("stack[%d]=%d\n", i, this.stack.getKNode(i + 1).data);
        }
    }
}
```

#### 4.3.2 双向链表模拟栈

代码实现：

```java
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
```

### 4.4 栈实现综合计算器

**思路分析：**

1. 定义两个分别存数字、运算符的栈
2. 定义比较运算符优先级的方法
3. 遍历表达式
   1. 如果是数字则入栈
   2. 如果是运算符
      1. 运算符栈为空，则直接入栈
      2. 如果当前运算符的优先级小于等于运算符栈顶的操作符
         1. 从数字栈中 `pop` 出两个数字
         2. 从运算符栈中 `pop` 出一个运算符
         3. 根据 `pop` 出的运算符和两个数字计算出结果
         4. 将结果压入数字栈
         5. 将当前运算符压入栈
      3. 如果当前运算符的优先级大于运算符栈顶的操作符，则直接入栈
4. 遍历完表达式
   1. 遍历 `pop` 出运算符栈中的运算符
   2. 从数字栈中 `pop` 出两个数字
   3. 根据 `pop` 出的运算符和两个数字计算出结果
   4. 将结果压入数字栈
5. 最后数字栈中的值即为表达式计算的值

**代码实现：**

```java
public class CalculatorDemo {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

//        String expression = "300+2*6-20";
//        String expression = "1-4/2+3+2*4";
        String expression = "7*2*2-5+1-5+3-4";

        int result = calculator.compute(expression);

        System.out.printf("%s=%d", expression, result);

    }
}

class Calculator {

    /**
     * 数值栈
     */
    ArrayStack numberStack = new ArrayStack(10);
    /**
     * 运算符栈
     */
    ArrayStack operatorStack = new ArrayStack(10);

    /**
     * 判断是否是运算发
     *
     * @param operator
     * @return
     */
    public boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    /**
     * 比较运算符的优先级，返回的值越大说明级别越高
     *
     * @param operator 运算符
     * @return
     */
    public int getPriority(char operator) {

        if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '+' || operator == '-') {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 运算只有两个数字的表达式
     *
     * @param number1  数字1
     * @param number2  数字2
     * @param operator 运算符
     * @return
     */
    public int compute(int number1, int number2, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                // 注意是 number2 - number1
                result = number2 - number1;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                // 注意是 number2 / number1
                result = number2 / number1;
                break;
            default:
                break;
        }

        return result;
    }

    public int compute(String expression) {

        int index = 0;
        int number1 = 0;
        int number2 = 0;
        char operator;
        int result = 0;
        String keyNumber = "";

        // 循环遍历表达式
        while (true) {
            // 依次得到表达式的值
            char ch = expression.charAt(index);
            if (isOperator(ch)) {
                // 是运算符
                if (operatorStack.isEmpty()) {
                    // 运算符栈为空，直接入栈
                    operatorStack.push(ch);
                } else {
                    // 运算符栈不为空

                    if (getPriority(ch) <= getPriority((char) operatorStack.peek())) {
                        number1 = numberStack.pop();
                        number2 = numberStack.pop();
                        operator = (char) operatorStack.pop();
                        result = compute(number1, number2, operator);
                        numberStack.push(result);
                        operatorStack.push(ch);

                    } else {
                        operatorStack.push(ch);
                    }
                }
            } else {
                // 只适用于个位数
//                numberStack.push(ch - 48);
                keyNumber += ch;
                // 处理多位数
                if (index == expression.length() - 1) {
                    numberStack.push(Integer.valueOf(keyNumber));
                } else {
                    if (isOperator(expression.charAt(index + 1))) {
                        // 后一位是运算符
                        numberStack.push(Integer.valueOf(keyNumber));
                        keyNumber = "";
                    }
                }
            }

            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            if (operatorStack.isEmpty()) {
                break;
            }

            operator = (char) operatorStack.pop();
            number1 = numberStack.pop();
            number2 = numberStack.pop();

            result = compute(number1, number2, operator);

            numberStack.push(result);
        }

        result = numberStack.pop();
        return result;
    }
}
```

### 4.5 逆波兰计算器

#### 4.5.1 前缀表达式（波兰表达式）

**基本介绍：**

- 前缀表达式又称波兰式，前缀表达式的运算符位于操作数之前

- 举例说明： (3+4)×5-6 对应的前缀表达式就是 - × + 3 4 5 6

**求值实现思路：**

1. **从右至左**扫描表达式
2. 遇到数字时，将数字压入堆栈
3. 遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（栈顶元素 和 次顶元素），并将结果入栈
4. 重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果

#### 4.5.2 中缀表达式

**基本介绍：**

- 中缀表达式就是常见的运算表达式，如：(3+4)×5-6
- 中缀表达式的求值是我们人最熟悉的，但是对计算机来说却不好操作(前面我们讲的案例就能看的这个问题)。因此，在计算结果时，往往会将中缀表达式转成其它表达式来操作(一般转成后缀表达式)

#### 4.5.3 后缀表达式（逆波兰表达式）

**基本介绍：**

- 后缀表达式又称逆波兰表达式,与前缀表达式相似，只是运算符位于操作数之后
- 举例说明： (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 –

| 正常的表达式 | 逆波兰表达式  |
| ------------ | ------------- |
| a+b          | a b +         |
| a+(b-c)      | a b c - +     |
| a+(b-c)*d    | a b c – d * + |
| a+d*(b-c)    | a d b c - * + |
| a=1+3        | a 1 3 + =     |

**求值实现思路：**

1. **从左至右**扫描表达式
2. 遇到数字时，将数字压入堆栈
3. 遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 和 栈顶元素），并将结果入栈
4. 重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果

例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:

1. 从左至右扫描，将 3 和 4 压入堆栈
2. 遇到 + 运算符，因此弹出 4 和 3（4为栈顶元素，3为次顶元素），计算出 3 + 4 的值，得 7，再将 7 入栈
3. 继续向右扫描将 5 入栈
4. 接下来是 × 运算符，因此弹出 5 和 7，计算出 7 × 5 = 35，将 35 入栈
5. 继续向右扫描将 6 入栈
6. 最后是 - 运算符，因此弹出 6 和 35，计算出 35 -6 = 29，将 29 入栈
7. 最后的栈顶运算即为计算的值

**代码实现：**

```java
public class ReversePolishCalculatorDemo {

    public static void main(String[] args) {

//        String expression = "3 4 + 5 * 6 -";
        String expression = "3 4 - 5 * 6 * 5 / 2 -";

        ReversePolishCalculator calculator = new ReversePolishCalculator();
        int compute = calculator.compute(expression);

        System.out.printf("%s = %d", expression, compute);
    }
}

class ReversePolishCalculator {

    /**
     * 计算后缀表达式的值
     * @param expression 后缀表达式
     * @return
     */
    public int compute(String expression) {
        // 将表达式转成 list
        List<String> list = Arrays.asList(expression.split(" "));

        // 定义一个栈
        Stack<String> stack = new Stack<>();

        // 遍历 list
        for (String item : list) {
            if (item.matches("\\d+")) {
                // 如果是多位数，就直接入栈
                stack.push(item);
            } else {
                // 如果是运算符
                int number1 = Integer.parseInt(stack.pop());
                int number2 = Integer.parseInt(stack.pop());
                int result = compute(number1, number2, item.charAt(0));
                stack.push(String.valueOf(result));
            }
        }

        int result = Integer.valueOf(stack.pop());
        return result;
    }

    /**
     * 运算只有两个数字的表达式
     *
     * @param number1  数字1
     * @param number2  数字2
     * @param operator 运算符
     * @return
     */
    public int compute(int number1, int number2, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                // 注意是 number2 - number1
                result = number2 - number1;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                // 注意是 number2 / number1
                result = number2 / number1;
                break;
            default:
                break;
        }

        return result;
    }
}
```

### 4.6 中缀表达式转转后缀表达式

**思路分析：**

1. 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2
2. **从左至右**扫描中缀表达式
3. 遇到操作数时，将其压 s2
4. 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
   1. 如果 s1 为空，或栈顶运算符为左括号 (，则直接将此运算符入栈
   2. 否则，若优先级比栈顶运算符的高，也将运算符压入 s1
   3. 否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到 4.1 与 s1 中新的栈顶运算符相比较	
5. 遇到括号时： 
   1. 如果是左括号 (，则直接压入 s1
   2. 如果是右括号 )，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
6. 重复步骤 2 至 5，直到表达式的最右边
7. 将 s1 中剩余的运算符依次弹出并压入 s2
8. 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式

**举例说明：**

将中缀表达式“1+((2+3)×4)-5”转换为后缀表达式的过程如下：

| 扫描到的元素 | s2(栈底->栈顶)        | s1 (栈底->栈顶) | 说明                               |
| ------------ | --------------------- | --------------- | ---------------------------------- |
| 1            | 1                     | 空              | 数字，直接入栈                     |
| +            | 1                     | +               | s1为空，运算符直接入栈             |
| (            | 1                     | + (             | 左括号，直接入栈                   |
| (            | 1                     | + ( (           | 同上                               |
| 2            | 1 2                   | + ( (           | 数字                               |
| +            | 1 2                   | + ( ( +         | s1栈顶为左括号，运算符直接入栈     |
| 3            | 1 2 3                 | + ( ( +         | 数字                               |
| )            | 1 2 3 +               | + (             | 右括号，弹出运算符直至遇到左括号   |
| ×            | 1 2 3 +               | + ( ×           | s1栈顶为左括号，运算符直接入栈     |
| 4            | 1 2 3 + 4             | + ( ×           | 数字                               |
| )            | 1 2 3 + 4 ×           | +               | 右括号，弹出运算符直至遇到左括号   |
| -            | 1 2 3 + 4 × +         | -               | -与+优先级相同，因此弹出+，再压入- |
| 5            | 1 2 3 + 4 × + 5       | -               | 数字                               |
| 到达最右端   | **1 2 3 + 4 × + 5 -** | 空              | s1 中剩余的运算符                  |

**代码实现：**

```java
/**
* 将中缀表达式转为后缀表达式
*
* @param infixExpression 中缀表达式
* @return
*/
public String infixExpressionToSuffixExpression(String infixExpression) {

    // 将中缀表达式转成 list 好遍历
    List<String> list = infixExpressionToList(infixExpression);

    // 定义符号栈
    Stack<String> stack = new Stack<>();
    List<String> resultList = new ArrayList<>();

    // 结果值
    String result = "";

    //        for (String item : list) {
    for (int i = 0; i < list.size(); i++) {
        String item = list.get(i);
        if (item.matches("\\d+")) {
            // 是数字
            resultList.add(item);
        } else if (item.equals("(")) {
            stack.push(item);
        } else if (item.equals(")")) {
            // 消除括号
            while (!stack.peek().equals("(")) {
                String pop = stack.pop();
                resultList.add(pop);
            }
            // 将 ( 弹出
            stack.pop();
        } else {
            // 处理运算符
            while (stack.size() != 0
                   && Operation.getPriority(stack.peek()) >= Operation.getPriority(item)) {
                resultList.add(stack.pop());
            }
            stack.push(item);
        }
    }

    while (!stack.isEmpty()) {
        resultList.add(stack.pop());
    }

    for (int i = 0; i < resultList.size(); i++) {
        result = result + resultList.get(i) + " ";
    }

    return result;
}

/**
* 将中缀表达式转为 list
*
* @param infixExpression 中缀表达式
* @return
*/
public List<String> infixExpressionToList(String infixExpression) {
    // 将表达式转为list
    List<String> list = new ArrayList<>();
    int index = 0;
    String number = "";
    char ch;

    while (index < infixExpression.length()) {
        ch = infixExpression.charAt(index);

        // ascii 码 48 ~ 57 为数字
        if (ch < 48 || ch > 57) {
            // 非数字
            list.add(ch + "");
            index++;
        } else {
            number = "";
            while (index < infixExpression.length()
                   && (ch = infixExpression.charAt(index)) >= 48
                   && (ch = infixExpression.charAt(index)) <= 57) {
                number += ch;
                index++;
            }
            list.add(number);
        }
    }
    return list;
}
```

### 4.7 逆波兰计算器完整版

1. 完整版的逆波兰计算器，功能包括
2. 支持 + - * / ( ) 
3.  多位数，支持小数
4. 兼容处理, 过滤任何空白字符，包括空格、制表符、换页符

**代码实现：**

```java
public class ReversePolishCalculatorPlus {

    public static void main(String[] args) {
        //String math = "9+(3-1)*3+10/2";
        String math = "12.8 + (2 - 3.55)*4+10/5.0";
        try {
            doCalc(doMatch(math));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 匹配 + - * / ( ) 运算符
     */
    static final String SYMBOL = "\\+|-|\\*|/|\\(|\\)";

    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String MINUS = "-";
    static final String TIMES = "*";
    static final String DIVISION = "/";

    /**
     * 加減 + -
     */
    static final int LEVEL_01 = 1;
    /**
     * 乘除 * /
     */
    static final int LEVEL_02 = 2;

    /**
     * 括号
     */
    static final int LEVEL_HIGH = Integer.MAX_VALUE;


    static Stack<String> stack = new Stack<>();
    static List<String> data = Collections.synchronizedList(new ArrayList<String>());

    /**
     * 去除所有空白符
     *
     * @param s
     * @return
     */
    public static String replaceAllBlank(String s) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return s.replaceAll("\\s+", "");
    }

    /**
     * 判断是不是数字 int double long float
     *
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(s).matches();
    }

    /**
     * 判断是不是运算符
     *
     * @param s
     * @return
     */
    public static boolean isSymbol(String s) {
        return s.matches(SYMBOL);
    }

    /**
     * 匹配运算等级
     *
     * @param s
     * @return
     */
    public static int calcLevel(String s) {
        if ("+".equals(s) || "-".equals(s)) {
            return LEVEL_01;
        } else if ("*".equals(s) || "/".equals(s)) {
            return LEVEL_02;
        }
        return LEVEL_HIGH;
    }

    /**
     * 匹配
     *
     * @param s
     * @throws Exception
     */
    public static List<String> doMatch(String s) throws Exception {
        if (s == null || "".equals(s.trim())) throw new RuntimeException("data is empty");
        if (!isNumber(s.charAt(0) + "")) throw new RuntimeException("data illeagle,start not with a number");

        s = replaceAllBlank(s);

        String each;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (isSymbol(s.charAt(i) + "")) {
                each = s.charAt(i) + "";
                //栈为空，(操作符，或者 操作符优先级大于栈顶优先级 && 操作符优先级不是( )的优先级 及是 ) 不能直接入栈
                if (stack.isEmpty() || LEFT.equals(each)
                        || ((calcLevel(each) > calcLevel(stack.peek())) && calcLevel(each) < LEVEL_HIGH)) {
                    stack.push(each);
                } else if (!stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek())) {
                    //栈非空，操作符优先级小于等于栈顶优先级时出栈入列，直到栈为空，或者遇到了(，最后操作符入栈
                    while (!stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek())) {
                        if (calcLevel(stack.peek()) == LEVEL_HIGH) {
                            break;
                        }
                        data.add(stack.pop());
                    }
                    stack.push(each);
                } else if (RIGHT.equals(each)) {
                    // ) 操作符，依次出栈入列直到空栈或者遇到了第一个)操作符，此时)出栈
                    while (!stack.isEmpty() && LEVEL_HIGH >= calcLevel(stack.peek())) {
                        if (LEVEL_HIGH == calcLevel(stack.peek())) {
                            stack.pop();
                            break;
                        }
                        data.add(stack.pop());
                    }
                }
                start = i;    //前一个运算符的位置
            } else if (i == s.length() - 1 || isSymbol(s.charAt(i + 1) + "")) {
                each = start == 0 ? s.substring(start, i + 1) : s.substring(start + 1, i + 1);
                if (isNumber(each)) {
                    data.add(each);
                    continue;
                }
                throw new RuntimeException("data not match number");
            }
        }
        //如果栈里还有元素，此时元素需要依次出栈入列，可以想象栈里剩下栈顶为/，栈底为+，应该依次出栈入列，可以直接翻转整个stack 添加到队列
        Collections.reverse(stack);
        data.addAll(new ArrayList<>(stack));

        System.out.println(data);
        return data;
    }

    /**
     * 算出结果
     *
     * @param list
     * @return
     */
    public static Double doCalc(List<String> list) {
        Double d = 0d;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            System.out.println(list);
            d = Double.valueOf(list.get(0));
            return d;
        }
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i));
            if (isSymbol(list.get(i))) {
                Double d1 = doTheMath(list.get(i - 2), list.get(i - 1), list.get(i));
                list1.remove(i);
                list1.remove(i - 1);
                list1.set(i - 2, d1 + "");
                list1.addAll(list.subList(i + 1, list.size()));
                break;
            }
        }
        doCalc(list1);
        return d;
    }

    /**
     * 运算
     *
     * @param s1
     * @param s2
     * @param symbol
     * @return
     */
    public static Double doTheMath(String s1, String s2, String symbol) {
        Double result;
        switch (symbol) {
            case ADD:
                result = Double.valueOf(s1) + Double.valueOf(s2);
                break;
            case MINUS:
                result = Double.valueOf(s1) - Double.valueOf(s2);
                break;
            case TIMES:
                result = Double.valueOf(s1) * Double.valueOf(s2);
                break;
            case DIVISION:
                result = Double.valueOf(s1) / Double.valueOf(s2);
                break;
            default:
                result = null;
        }
        
        return result;
    }
}
```

## 5. 递归

### 5.1 基本介绍

递归就是**方法自己调用自己**，每次调用时传入不同的变量。递归有助于编程者解决复杂的问题，同时可以让代码变得简洁。

#### 5.1.1 递归的调用机制

两个问题：

1. 打印问题
2. 阶乘问题

代码实现：

```java
public class RecursionDemo {

    public static void main(String[] args) {
        test(4);
        int n = 4;
        int factorial = factorial(n);
        System.out.println(n + "! = " + factorial);
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }

        System.out.println("n = " + n);
    }

    /**
     * 求 n 的阶乘
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
```

#### 5.1.2 递归的应用场景

- 各种数学问题如：8 皇后问题、汉诺塔、阶乘问题、迷宫问题、球和篮子的问题( google 编程大赛)
- 各种算法中也会使用到递归，比如快排、归并排序、二分查找、分治算法等

#### 5.1.3 递归的重要原则

1. 执行一个方法时，就创建一个新的受保护的独立空间(栈空间)
2. 方法的局部变量是独立的，不会相互影响
3. 如果方法中使用的是**引用类型变量**（比如数组），就会**共享该引用类型的数据**
4. 递归必须**向退出递归的条件逼近**，否则就是无限递归，出现 `StackOverflowError`
5. 当一个方法执行完毕，或者遇到 `return`，就会返回。遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕

