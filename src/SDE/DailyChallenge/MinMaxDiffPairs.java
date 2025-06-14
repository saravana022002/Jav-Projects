package SDE.DailyChallenge;

import java.util.Arrays;

public class MinMaxDiffPairs {
    public static void main(String[] args) {
        int[] nums = {4,2,1,2};
        int p = 1;
        System.out.println(minimizeMax(nums, p));
    }
    public static int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        int mid;
        int answer = 0;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(canFormPairs(nums, mid, p)){
                answer = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private static boolean canFormPairs(int[] nums, int maxDiff, int p) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] - nums[i - 1] <= maxDiff){
                count ++;
                i++;
            }
        }
        return count >= p;
    }
}
