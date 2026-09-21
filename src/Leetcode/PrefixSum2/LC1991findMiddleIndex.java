package Leetcode.PrefixSum2;

public class LC1991findMiddleIndex {
    public static void main(String[] args) {
        int[] nums = {2,3,-1,8,4};
        findMiddleIndex(nums);
    }

    public static int findMiddleIndex(int[] nums) {
        int totalNum = 0;
        for (int num : nums) {
            totalNum += num;
        }

        //totalSum = leftSum + rightSum + num[i]

        //rightSum = totalSum - leftsum - num[i]
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            rightSum = totalNum - leftSum - nums[i];
            if(rightSum == leftSum){
                return i;
            }
            leftSum += nums[i];
        }
        return 0;
    }
}
