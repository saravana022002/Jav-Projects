package SDE.DailyChallenge;

import java.util.Arrays;

public class CountValidSelections {
    public static void main(String[] args) {
        int[] nums = {1,0,2,0,3};
        System.out.println(countValidSelections(nums));
    }

    public static int countValidSelections(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                count += isValid(i, nums, true);
                count += isValid(i, nums, false);
            }
        }
        return count;
    }


    static int isValid(int curr, int[] nums, boolean isLeft){

        int[] copy = nums.clone();

        while (curr >= 0 && curr < copy.length){
            if (copy[curr] != 0) {
                copy[curr]--;
                isLeft = !isLeft;
            }
            curr += isLeft ? -1 : 1;
        }

        for (int num : copy) {
            if (num != 0) {
                return 0;
            }
        }
        return 1;
    }
}
