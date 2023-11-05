package com.cyl.datastructure.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author：Java陈序员
 * @date：2023/11/5 18:04
 * @description：逆波兰计算器
 */
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
