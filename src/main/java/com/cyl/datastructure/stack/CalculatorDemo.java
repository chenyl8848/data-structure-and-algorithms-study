package com.cyl.datastructure.stack;

/**
 * @author：Java陈序员
 * @date：2023/11/4 22:32
 * @description：计算器 demo
 */
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
