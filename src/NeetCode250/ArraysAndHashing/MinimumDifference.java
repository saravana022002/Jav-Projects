package NeetCode250.ArraysAndHashing;

import java.util.Arrays;

public class MinimumDifference {
    public static int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[k - 1];
        int minDiff = max - min;
        int diff;
        for (int i = k; i < nums.length; i++) {
            min = nums[i - k + 1];
            max = nums[i];
            diff = max - min;
            if(minDiff > diff){
                minDiff = diff;
            }
        }
        return minDiff;
    }
    public static void main(String[] args) {
        int[] nums = {9,4,1,7};
        int k = 2;
        minimumDifference(nums, k);
    }

}
