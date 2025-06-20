package SDE.DailyChallenge;

import java.util.Arrays;

public class DivideArrayIntoArrays {
    public static void main(String[] args) {
        int[] nums = {2,4,2,2,5,2};
        int k = 2;
        System.out.println(Arrays.deepToString(divideArray(nums, k)));
    }
    public static int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int eachSize = nums.length / 3;
        int[][] ans = new int[eachSize][3];
        for (int i = 0; i < eachSize; i++) {
            int start = i * 3;
            if (nums[start + 2] - nums[start] > k) {
                return new int[0][];
            }
            for (int j = 0; j < 3; j++) {
                ans[i][j] = nums[start + j];
            }
        }
        return ans;
    }
}
