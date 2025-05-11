package SDE.Arryas4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubArraySumToK {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;
        System.out.println(optimalSolution(nums, k));
    }

    public static int optimalSolution(int[] nums, int k){
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        prefixSumFreq.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[0];
            count += prefixSumFreq.getOrDefault(sum - k, 0);
            prefixSumFreq.put(sum, prefixSumFreq.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
