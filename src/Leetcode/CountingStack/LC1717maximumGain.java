package Leetcode.CountingStack;

import java.util.Stack;

public class LC1717maximumGain {
    public static void main(String[] args) {
       String s = "aabbaaxybbaabb";
       int x = 5;
       int y = 4;
        System.out.println(maximumGain(s, x, y));
    }

    static int ans = 0;
    public static int maximumGain(String s, int x, int y) {
       if(x >= y){
           Stack<Character> c = abHalfSum(s, x);
           StringBuilder str = new StringBuilder();
           while(!c.isEmpty()){
               str.append(c.pop());
           }
           abHalfSum(str.toString(), y);
       }else {
           Stack<Character> c = baHalfSum(s, y);
           StringBuilder str = new StringBuilder();
           while(!c.isEmpty()){
               str.append(c.pop());
           }
           baHalfSum(str.toString(), x);
       }
       return ans;
    }

    public static Stack<Character> abHalfSum(String s, int score){
        Stack<Character> str = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(!str.isEmpty() && str.peek().equals('a') && ch == 'b'){
                str.pop();
                ans += score;
                continue;
            }
            str.push(s.charAt(i));
        }
        return str;
    }

    public static Stack<Character> baHalfSum(String s, int score){
        Stack<Character> str = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(!str.isEmpty() && str.peek().equals('b') && ch == 'a'){
                str.pop();
                ans += score;
                continue;
            }
            str.push(s.charAt(i));
        }
        return str;
    }
}
