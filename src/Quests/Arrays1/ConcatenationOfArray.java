package Quests.Arrays1;

import java.util.Arrays;

public class ConcatenationOfArray {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        System.out.println(Arrays.toString(getConcatenation(nums)));
    }


    public static int[] getConcatenation(int[] nums) {
        int length = nums.length;
        int[] ans = new int[2 * length];
        for (int i = 0; i < length; i++) {
            ans[i] = nums[i];
            ans[length + i] = nums[i];
        }
        return ans;
    }
}
