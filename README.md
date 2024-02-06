
# 求合法表达式的值
#### @author: Wonaren
#### @datetime: `2024-02-06 20:35:49`
#### @version: 1.1
改动:\
`改用BigDecimal数据类型，提高了计算精度;`\
`减少了代码重复，优化代码冗余;`
#### @date: 2024/2/6
#### @version: 1.0
#### @description:
该类用于计算合法的算术表达式的值
1. 该类支持\
`小数运算 ---- 1.2+2`\
`负数运算 ---- -2+3, 3*-2`\
`括号嵌套 ---- (8÷(2*1))*(3*-2)`
1. 支持自定义运算符
#### @usage:
  调用double calc(String)方法,传入合法的算术表达式, 返回计算结果
#### @notes:
  1. 该类只处理合法的算术表达式，请注意输入的表达式是否合法
  2. 不合法的表达式如下：\
    ①. 未闭合的括号或单独存在的括号\
    `(), (, ), )(`\
    ②. 单独存在的小数点(__相邻无数字__)\
    `., .+, +.`\
    ③. 运算符相邻 **(×-、÷-除外)** \
    `1+-2, 1×÷2, 1*+2`

