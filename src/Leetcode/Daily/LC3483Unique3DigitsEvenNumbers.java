package Leetcode.Daily;

import java.util.HashMap;

public class LC3483Unique3DigitsEvenNumbers {
    public static void main(String[] args) {
        int[] digits = {6, 6, 6};
        System.out.println(totalNumbers(digits));
    }

    public static int totalNumbers(int[] digits) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int digit : digits) {
            map.put(digit, map.getOrDefault(digit, 0) + 1);
        }
        for (int i = 100; i < 1000; i++) {
            count += isDigitFormable(i , map);
        }
        return count;
    }

    private static int isDigitFormable(int k, HashMap<Integer, Integer> map) {
        int third = k%10;
        int second = (k/10) % 10;
        int first = ((k/10) / 10) % 10;
        int thirdCounter = map.getOrDefault(third, 0);
        int secondCounter = map.getOrDefault(second, 0);
        int firstCounter = map.getOrDefault(first, 0);
        if(third % 2 != 0){
            return 0;
        }

        if(thirdCounter == 0 || secondCounter == 0 || firstCounter == 0){
            return 0;
        }

        if(third == second){
            if(thirdCounter < 2){
                return  0;
            }
        }

        if((second == first)){
            if(secondCounter < 2){
                return  0;
            }
        }

        if((first == third)){
            if(firstCounter < 2){
                return  0;
            }
        }

        if(first == third && second == first){
            if (firstCounter < 3){
                return 0;
            }
        }

        return 1;
    }


}
