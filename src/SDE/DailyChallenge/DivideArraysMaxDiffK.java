package SDE.DailyChallenge;

import java.util.Arrays;

public class DivideArraysMaxDiffK {
    public static void main(String[] args) {
        int[] nums = {1,2,3,0};
        int k = 2;
        partitionArray(nums, k);
    }
    public static int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int cnt = 0;
        int min = nums[0];
        for(int i = 1 ; i < nums.length; i++){
            if(nums[i] - min  > k){
                cnt++;
                min = nums[i];
            }
        }
        return cnt + 1;
    }
}
