package SDE.Contest169;

public class MinMoves {
    public static void main(String[] args) {
        int[] nums = {4,4,5};
        System.out.println(minMoves(nums));
    }

    public static int minMoves(int[] nums) {
        int ans = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = max - nums[i];
            ans = ans + temp;
        }
        return ans;
    }
}
