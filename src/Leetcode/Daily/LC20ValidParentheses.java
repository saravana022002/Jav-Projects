package Leetcode.Daily;

import java.util.Stack;

public class LC20ValidParentheses {
    public static void main(String[] args) {
        String s = "))";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        char[] characters = s.toCharArray();
        if(characters.length % 2 == 1){
            return false;
        }
        for (char cur : characters) {
            if(!stk.empty()) {
                switch (cur) {
                    case '}':
                        if (stk.peek().equals('(') || stk.peek().equals('[')) {
                            return false;
                        }
                        stk.pop();
                        break;
                    case ')':
                        if (stk.peek().equals('[') || stk.peek().equals('{')) {
                            return false;
                        }
                        stk.pop();
                        break;
                    case ']':
                        if (stk.peek().equals('{') || stk.peek().equals('(')) {
                            return false;
                        }
                        stk.pop();
                        break;
                    default:
                        stk.push(cur);
                }
            }
            else {
                if("{([".indexOf(cur) > -1){
                    stk.push(cur);
                }else {
                    return false;
                }
            }
        }
        return stk.empty();
    }
}
