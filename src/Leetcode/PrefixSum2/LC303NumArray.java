package Leetcode.PrefixSum2;

public class LC303NumArray {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        LC303NumArray numArray = new LC303NumArray(nums);
        System.out.println(numArray.sumRange(0, 2)); // return (-2) + 0 + 3 = 1
        System.out.println(numArray.sumRange(2, 5)); // return 3 + (-5) + 2 + (-1) = -1
        System.out.println(numArray.sumRange(0, 5)); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3

    }

    long[] prefixSum;
    public LC303NumArray(int[] nums) {
        prefixSum = new long[nums.length + 1];
        prefixSum[0] = 0L;
        long count = 0L;
        for(int i = 0; i < nums.length; i++){
            count += nums[i];
            prefixSum[i + 1] = count;
        }
    }

    public int sumRange(int left, int right) {
        return (int) (prefixSum[right + 1] - prefixSum[left]);
    }

}
