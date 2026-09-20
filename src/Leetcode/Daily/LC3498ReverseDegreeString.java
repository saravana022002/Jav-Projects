package Leetcode.Daily;

public class LC3498ReverseDegreeString {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(reverseDegree(s));
    }
    public static int reverseDegree(String s) {
        char[] chars = s.toCharArray();
        int score = 0;
        for (int i = 0; i < chars.length; i++) {
            score += ((i + 1) * ('z' - chars[i] + 1));
        }
        return score;
    }
}
