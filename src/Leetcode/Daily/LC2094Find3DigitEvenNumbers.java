package Leetcode.Daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LC2094Find3DigitEvenNumbers {
    public static void main(String[] args) {
        int[] digits = {6, 6, 6};
        System.out.println(Arrays.toString(findEvenNumbers(digits)));
    }
    public static int[] findEvenNumbers(int[] digits) {
        int[] map = new int[10];
        List<Integer> resultList = new ArrayList<>();
        for (int digit : digits) {
            map[digit]++;
        }
        for (int i = 100; i < 1000; i = i + 2) {
            if(isDigitFormable(i , map)){
                resultList.add(i);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }


    private static boolean isDigitFormable(int k, int[] map) {
        int third = k%10;
        int second = (k/10) % 10;
        int first = ((k/10) / 10) % 10;
        int[] required = new int[10];
        required[first]++;
        required[second]++;
        required[third]++;
        return required[first] <= map[first] && required[second] <= map[second] && required[third] <= map[third];
    }
}
