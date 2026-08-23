package Leetcode.CountingStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

public class LC1047RemoveDuplicates {
    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicatesStr(s));
    }

    public static String removeDuplicates(String s) {
        Stack<Character> ae = new Stack<Character>();
        for (Character c : s.toCharArray()){
            if(!ae.isEmpty() && ae.peek().equals(c)){
                ae.pop();
            }else {
                ae.push(c);
            }
        }
        StringBuilder reversed = new StringBuilder();
        while (!ae.isEmpty()){
            reversed.append(ae.pop());
        }
        return reversed.reverse().toString();
    }

    public static String removeDuplicatesStr(String s) {
        StringBuilder reversed = new StringBuilder();
        for (Character c : s.toCharArray()){
            int len = reversed.length();
            if (len > 0 && reversed.charAt(reversed.length() - 1) == c){
                reversed.deleteCharAt(reversed.length() - 1);
            }else {
                reversed.append(c);
            }
        }
        return reversed.toString();
    }

}
