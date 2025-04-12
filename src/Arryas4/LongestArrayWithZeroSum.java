package Arryas4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LongestArrayWithZeroSum {
    public static void main(String[] args) {
        int[] nums = {16 ,1 ,13 ,-30 ,18 ,-18 ,14 ,11 ,6 ,3 ,-34 ,0};
        System.out.println(optimalSolution(nums));
    }

    public static int optimalSolution(int[] nums){
        int sizeArr = nums.length;
        Map<Integer, Integer> sumArr = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < sizeArr; i++) {
            sum = sum + nums[i];
            if(sum == 0){
                maxLength = i + 1;
            }else {
                if (sumArr.get(sum) != null) {
                    maxLength = Math.max(maxLength, i - sumArr.get(sum));
                } else {
                    sumArr.put(sum, i);
                }
            }
        }
        return maxLength;
    }

    public static int optimalSolution(ArrayList<Integer> arr){
        int sizeArr = arr.size();
        Map<Integer, Integer> sumArr = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < sizeArr; i++) {
            sum = sum + arr.get(i);
            if(sum == 0){
                maxLength = i + 1;
            }else {
                if (sumArr.get(sum) != null) {
                    maxLength = Math.max(maxLength, i - sumArr.get(sum));
                } else {
                    sumArr.put(sum, i);
                }
            }
        }
        return maxLength;
    }
}
