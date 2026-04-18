package Quests.Stack;

import java.util.Arrays;
import java.util.Stack;

public class EvalRPN {
    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> ans = new Stack<>();
        int a;
        int b;
        for (String token : tokens) {
            if (token.length() == 1 && "+*-/".contains(token)) {
                b = ans.pop();
                a = ans.pop();
                switch (token) {
                    case "+" -> ans.push(a + b);
                    case "-" -> ans.push(a - b);
                    case "*" -> ans.push(a * b);
                    case "/" -> ans.push(a / b);
                }
            } else {
                ans.push(Integer.valueOf(token));
            }
        }
        return ans.peek();
    }
}
