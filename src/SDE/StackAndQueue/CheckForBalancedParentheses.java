package SDE.StackAndQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CheckForBalancedParentheses {
    public static void main(String[] args) {
        String s = "]";
        System.out.println(isValid(s));
    }
    public static boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == '{' || ch == '(' || ch == '['){
                stk.push(ch);
            }else {
                if(stk.isEmpty()){
                    return false;
                }

                char top = stk.peek();
                if(top == '{' && ch == '}'
                        || top == '(' && ch == ')'
                        || top == '[' && ch == ']'){
                    stk.pop();
                }else {
                    return false;
                }
            }
        }
        return stk.empty();
    }
}
