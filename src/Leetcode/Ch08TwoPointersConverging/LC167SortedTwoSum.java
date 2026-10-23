package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;

public class LC167SortedTwoSum {
    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        int sum;
        while (low < high){
            sum =  numbers[low] + numbers[high];
            if(sum > target){
                high--;
            }else if (sum < target){
                low++;
            }else {
                return new int[]{low + 1, high + 1};
            }
        }
        return new int[]{low + 1, high + 1};
    }
}

