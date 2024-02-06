/**
 * 该类用于计算合法的算术表达式的值
 * @author: Wonaren
 * @date: 2024/2/6
 * @version: 1.0
 * @description:
 * 1. 该类支持
 *      小数运算 ---- 1.2+2
 *      负数运算 ---- -2+3, 3*-2
 *      括号嵌套 ---- (8÷(2*1))*(3*-2)
 * 2. 支持自定义运算符
 * @usage:
 * 调用double calc(String)方法,传入合法的算术表达式, 返回计算结果
 * @notes:
 * 1. 该类只处理合法的算术表达式，请注意输入的表达式是否合法
 * 2. 不合法的表达式如下：
 *    ①. 未闭合的括号或单独存在的括号     ---- (), (, ), )(
 *    ②. 单独存在的小数点(相邻无数字)     ---- ., .+, +.
 *    ③. 运算符相邻(×-、÷-除外)         ---- 1+-2, 1×÷2, 1*+2
 */

import java.util.HashMap;
import java.util.Stack;

public class StrCalculator {
    public Stack<Double> num;
    public Stack<Character> op;
    public final char plus = '+', minus = '-', mul = '×', div = '÷', lPar = '(', rPar = ')';
    public HashMap<Character, Integer> pr = new HashMap<Character, Integer>() {{
        put(plus, 1);
        put(minus, 1);
        put(mul, 2);
        put(div, 2);
        put(lPar, 0);
        put(rPar, 0);
    }};

    public StrCalculator() {
        this.num = new Stack<>();
        this.op = new Stack<>();
    }

    public void eval() {

        double num2 = num.pop();
        double num1 = num.pop();
        char opt = op.pop();

        double res = 0.0;
        if (opt == plus) res = num1 + num2;
        if (opt == minus) res = num1 - num2;
        if (opt == mul) res = num1 * num2;
        if (opt == div) res = num1 / num2;

        num.push(res);
    }

    public double calc(String str) {

        boolean mFlag = false;
        char c;
        for (int i=0; i<str.length(); i++) {
            c = str.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                double x = 0;
                int dotIndex = -1, j = i;
                while(j < str.length() && ( Character.isDigit(str.charAt(j)) || str.charAt(j) == '.')) {
                    if (str.charAt(j) != '.') x = x * 10 + str.charAt(j) - '0';
                    else dotIndex = j;
                    j++;
                }

                i = j - 1;
                if (dotIndex != -1) x /= Math.pow(10, i-dotIndex);
                if (mFlag) x = -x;
                num.push(x);
            } else if(c == lPar) {
                op.push(c);
            } else if(c == rPar) {
                while (op.peek() != lPar) eval();
                op.pop();
            } else {
                char tc = i != 0 ? str.charAt(i-1) : 'N';
                if (c == minus && tc != rPar && !Character.isDigit(tc)) {
                    mFlag = true;
                } else {
                    while (!op.isEmpty() && pr.get(op.peek()) >= pr.get(c)) eval();
                    mFlag = false;
                    op.push(c);
                }
            }
        }
        while (!op.empty()) eval();

        return num.peek();
    }

}