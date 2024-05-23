package spring.code.jake.myleetcode;

import java.util.Stack;

public class MyStacks {

    public boolean isValidClosedBrackets(String str) {
        // Assume input String only contains parentheses
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : str.toCharArray()) {
            
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false; 
                }
                
                char top = stack.pop();
                
                if (c == ')' && top != '(') {
                    return false;
                } else if (c == ']' && top != '[') {
                    return false;
                } else if (c == '}' && top != '{') {
                    return false;
                }
            }
            /* 遇到左括号就入栈，遇到右括号就立即弹出栈顶的左括号并进行匹配
            最后空栈说明每一个右括号必然有一个位于栈顶的左括号与之匹配
            核心方法是Stack的push()和pop() */
        }
        
        // 如果栈不为空，说明还有未闭合的左括号
        return stack.isEmpty();
    }
}
