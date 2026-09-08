package Leetcode.HashMapAsLookup;

import java.util.Arrays;
import java.util.HashMap;

public class LC1TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer value = map.get(complement);
            if(value != null){
                return new int[]{value, i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
}
