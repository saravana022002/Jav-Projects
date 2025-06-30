package SDE.DailyChallenge;

import java.util.Arrays;

public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    public static void main(String[] args) {
        int[] nums = {3,5,6,7};
        int target = 9;
        System.out.println(numSubSeq(nums, target));
    }
    public static int numSubSeq(int[] nums, int target) {
        int mod = (int) (Math.pow(10, 9) + 7);
        Arrays.sort(nums);

        int n = nums.length;
        int[] pow = new int[n];
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }

        int left = 0;
        int right = nums.length - 1;
        long ans = 0;
        while (left <= right){
            int sum = nums[left] + nums[right];
            if(sum <= target){
                ans = ans + pow[right - left];
                left ++;
            }else {
                right --;
            }
        }
        return (int) (ans % mod);
    }
}
