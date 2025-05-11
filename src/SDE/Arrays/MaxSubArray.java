package SDE.Arrays;


/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */
public class MaxSubArray{
    public static void main(String[] args) {
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int max = bruteForce(input);
        System.out.println("normal approach:"+max);

//        this is not the approach
//        int [] sumLocal = approach1(input);
//        System.out.println("recursive approach:"+sumLocal[1]);
        int nsqComplexity = betterApproach(input);
        System.out.println("better approach:"+nsqComplexity);

        int nComplexity = optimalApproach(input, input.length);
        System.out.println("better approach:"+nComplexity);

    }

    private static int betterApproach(int[] input) {
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            int sum = 0;
            for(int j = i; j < input.length; j++){
                sum += input[j];
                globalMax = Math.max(globalMax, sum);
            }
        }
        return globalMax;
    }


    public static int optimalApproach(int[] input, int n) {
        int globalMax = Integer.MIN_VALUE;
        int currentMax = 0;

        for (int i = 0; i < n; i++) {
            currentMax = Math.max(input[i], currentMax + input[i]);
            globalMax = Math.max(globalMax, currentMax);
        }
        return globalMax;
    }


    private static int[] approach1(int[] input) {
        int[] ans = new int[2];
        ans[1] = Integer.MIN_VALUE;
        recursivelyFindTheMaxSumInArray(0, input.length , input, ans);
        return ans;
    }

    private static int[] recursivelyFindTheMaxSumInArray(int startingAddress, int endingAddress, int[] input, int[] ans) {
        if(endingAddress == startingAddress){
            ans[0] = 0;
            return ans;
        }
        for (int i = startingAddress; i < endingAddress; i++) {
            ans = recursivelyFindTheMaxSumInArray(startingAddress, endingAddress - 1, input, ans);
            ans[0] = ans[0] + input[endingAddress - 1];
            if (ans[0] > ans[1]) {
                ans[1] = ans[0];
            }
        }
        return ans;

    }
//{-2,1,-3,4,-1,2,1,-5,4}
    public static int bruteForce(int[] input){
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;
        int startingIndex = 0;
        for (int i = 0; i < input.length; i++) {
            localMax = getMaxFormStaringIndex(input, startingIndex);
            if(localMax > globalMax){
                 globalMax = localMax;
            }
            startingIndex++;
        }
        return globalMax;
    }

    private static int getMaxFormStaringIndex(int[] input, int startingIndex) {
        int tempMax = 0;
        int max = Integer.MIN_VALUE;
        for (int i = startingIndex; i < input.length; i++) {
            for (int j = startingIndex; j <= i; j++) {
                tempMax = tempMax + input[j];
            }
            if(tempMax > max){
                max = tempMax;
            }
            tempMax = 0;
        }
        return max;
    }
}
