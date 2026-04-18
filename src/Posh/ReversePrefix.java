package Posh;

import java.util.Arrays;

public class ReversePrefix {
    public static void main(String[] args) {
        String s = "xyz";
        int k = 3;
        System.out.println(reversePrefix(s, k));
    }
    public static String reversePrefix(String s, int k) {
        char[] chars = s.toCharArray();
        int l = 0;
        int r = k - 1;
        char ch;
        while (l <= r){
            ch = chars[l];
            chars[l] = chars[r];
            chars[r] = ch;
            l++;
            r--;
        }
        StringBuilder string = new StringBuilder();
        for (char aChar : chars) {
            string.append(aChar);
        }
        return string.toString();
    }
}
