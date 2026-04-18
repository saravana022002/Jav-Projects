package Quests.Arrays2;

import java.util.Arrays;

public class SetMisMatch {
    public static int[] findErrorNums(int[] nums) {
        boolean[] seen = new boolean[nums.length];
        int dup = -1;
        int miss = -1;
        for (int num : nums) {
            if(seen[num - 1]){
                dup = num;
            }
            seen[num - 1] = true;
        }
        for (int i = 0; i < seen.length; i++) {
            if(!seen[i]){
                miss = i+1;
                break;
            }
        }
        return new int[]{dup, miss};
    }

    public static int[] findErrorNumsOpt(int[] nums) {
        int dup = -1;
        int miss = -1;
        int idx;
        for (int i = 0; i < nums.length; i++) {
            idx = Math.abs(nums[i]) - 1;
            if(nums[idx] < 0){
                dup = idx + 1;
            }else {
                nums[idx] *= -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                miss = i+1;
                break;
            }
        }
        return new int[]{dup, miss};
    }

    public static void main(String[] args) {
        int[] nums = {3,2,2};
        System.out.println(Arrays.toString(findErrorNumsOpt(nums)));
    }
}
