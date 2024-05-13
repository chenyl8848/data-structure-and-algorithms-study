package com.codechen.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author：Java陈序员
 * @date：2023/10/22 22:57
 * @description：
 */
public class TestStackDemo {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
