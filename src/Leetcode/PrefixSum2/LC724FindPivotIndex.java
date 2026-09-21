package Leetcode.PrefixSum2;

public class LC724FindPivotIndex {
    public static void main(String[] args) {
        int[] nums = {0,0};
        System.out.println(pivotIndexWithoutSpace(nums));
    }
    public static int pivotIndex(int[] nums) {
        int[] ans = new int[nums.length];
        int count = 0;
        for(int i = nums.length - 1; i >= 0; i--){
            count += nums[i];
            ans[i] = count;
        }

        int count1 = 0;
        for(int i = 0; i < nums.length; i++){
            count1 += nums[i];
            if(count1 == ans[i]){
                return i;
            }
        }
        return -1;
    }

    // without extra space
    // Sum = Leftsum + rightSum + num[i]
    // Sum - Leftsum - num[i] = rightSum

    public static int pivotIndexWithoutSpace(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        int rightSum = 0;
        for(int i = 0; i < nums.length; i++){
            rightSum = totalSum - leftSum - nums[i];
            if(rightSum == leftSum){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }


}
