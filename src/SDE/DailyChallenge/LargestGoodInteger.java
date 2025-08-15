package SDE.DailyChallenge;

import java.util.Arrays;

public class LargestGoodInteger {
    public static void main(String[] args) {
        String num = "42352338";
        System.out.println(largestGoodIntegerOp(num));
    }
    public static String largestGoodInteger(String num) {
        int count = 1;
        char prev = num.charAt(0);
        Character max = ' ';
        for (int i = 1; i < num.length(); i++) {
            if(num.charAt(i) == prev){
                prev = num.charAt(i);
                count ++;
                if(count == 3 && prev > max){
                    max = prev;
                }
            }else {
                prev = num.charAt(i);
                count = 1;
            }
        }
        if(max != ' ') {
            return String.valueOf(max) + max + max;
        }else {
            return "";
        }
    }

    public static String largestGoodIntegerOp(String num) {
        char maxChar = ' ';
        char tuco;
        for (int i = 0; i < num.length() - 2; i++) {
            tuco = num.charAt(i);
            if(tuco == num.charAt(i + 1) && tuco == num.charAt(i + 2)){
                if(maxChar <  tuco){
                    maxChar = tuco;
                }
            }
        }
        return maxChar == ' ' ?  "" : String.format("%c%c%c", maxChar, maxChar, maxChar);
    }
}
