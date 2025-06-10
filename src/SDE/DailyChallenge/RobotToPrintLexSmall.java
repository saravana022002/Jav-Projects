package SDE.DailyChallenge;

import java.util.Stack;

public class RobotToPrintLexSmall {
    public static void main(String[] args) {
        String s = "bdda";
        System.out.println(robotWithString(s));
    }
    public static String robotWithString(String s) {
        Stack<Character> sStk = new Stack<>();
        Stack<Character> tStk = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        char[] minSuffix = new char[s.length()];
        minSuffix[s.length() - 1] = s.charAt(s.length() - 1);
        for (int i = s.length() - 2; i >= 0; i--) {
            minSuffix[i] = (char) Math.min(s.charAt(i), minSuffix[i + 1]);
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            sStk.push(s.charAt(i));
        }

        int i = 0;
        while (!sStk.isEmpty()){
            Character ch = sStk.pop();
            tStk.push(ch);
            while (!tStk.isEmpty() && (i == s.length() - 1 || tStk.peek() <= minSuffix[i+1])){
                stringBuilder.append(tStk.pop());
            }
            i++;
        }
        while (!tStk.isEmpty()){
            stringBuilder.append(tStk.pop());
        }
        return stringBuilder.toString();
    }



    public static String robotWithString1(String s) {
        Stack<Character> t = new Stack<>();
        StringBuilder result = new StringBuilder();
        int n = s.length();
        char[] minSuffix = new char[n];
        minSuffix[n - 1] = s.charAt(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = (char) Math.min(s.charAt(i), minSuffix[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            t.push(s.charAt(i));
            while (!t.isEmpty() && (i == n - 1 || t.peek() <= minSuffix[i + 1])) {
                result.append(t.pop());
            }
        }
        while (!t.isEmpty()) {
            result.append(t.pop());
        }
        return result.toString();
    }

}
