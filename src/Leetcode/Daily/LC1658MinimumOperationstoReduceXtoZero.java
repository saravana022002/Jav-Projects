package Leetcode.Daily;

import java.util.HashMap;
import java.util.Map;

public class LC1658MinimumOperationstoReduceXtoZero {
    public static void main(String[] args) {
        int[] nums = {3,2,20,1,1,3};
        int x = 10;
        System.out.println(minOperations(nums, x));
    }

    public static int minOperations(int[] nums, int x) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        int k = sum - x;

        if (k == 0) return nums.length;
        if (k < 0) return -1;

        int max = -1;
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            Integer index = map.get(runningSum - k);
            if(index != null){
                max = Math.max(max, i - index + 1);
            }
            map.put(runningSum, i + 1);
        }
        return max != -1 ? nums.length - max : -1;
    }
}
