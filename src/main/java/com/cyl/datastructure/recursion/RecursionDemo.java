package com.cyl.datastructure.recursion;

/**
 * @author：Java陈序员
 * @date：2023/11/10 20:55
 * @description：递归demo
 */
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
