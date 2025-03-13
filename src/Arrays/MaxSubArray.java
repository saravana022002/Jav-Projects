package Arrays;


import java.util.HashMap;
import java.util.Map;

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
        int[] input = {-1};
//        int max = approach(input);
        int [] sumLocal = approach1(input);
        System.out.println(sumLocal[1]);
    }

    private static int[] approach1(int[] input) {
        int[] sumLocal = new int[2];
        sumLocal[1] = Integer.MIN_VALUE;
        recursivelyFindTheMaxSumInArray(0, input, sumLocal);
        return sumLocal;
    }

    private static int[] recursivelyFindTheMaxSumInArray(int index, int[] input, int[] sumLocal) {
        if(input.length == index){
            return sumLocal;
        }
        int[] sumLocal1 = recursivelyFindTheMaxSumInArray(index+1, input, sumLocal);
        sumLocal[0] = input[index] + sumLocal1[0];
        if(sumLocal[0] > sumLocal[1]){
            sumLocal[1] = sumLocal[0];
        }
        return sumLocal;
    }

    public static int approach(int[] input){
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
