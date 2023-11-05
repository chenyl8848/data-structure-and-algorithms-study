package com.cyl.datastructure.stack;

import java.util.ArrayList;
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

        System.out.printf("%s = %d\n", expression, compute);

//        String infixExpression = "(3+4)*5-6";
        String infixExpression = "1-10+(2+3)*4-15";
        List<String> list = calculator.infixExpressionToList(infixExpression);
        System.out.println(list);

        String suffixExpression = calculator.infixExpressionToSuffixExpression(infixExpression);
        System.out.println(suffixExpression);

        int result = calculator.compute(suffixExpression);
        System.out.printf("%s = %d\n", infixExpression, result);
    }
}

class ReversePolishCalculator {

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

    /**
     * 计算后缀表达式的值
     *
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

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getPriority(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }

        return result;
    }
}
