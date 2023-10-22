package com.cyl.datastructure.linkedlist;

/**
 * @author：Java陈序员
 * @date：2023/10/21 23:43
 * @description：单链表
 */
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
