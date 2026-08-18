package Casual;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindLargestMissingNumber {
    public static void main(String[] args) {
        int k = 4;
        int[] nums = {11,12,5,4,2,8,11,8,11,7};
        System.out.println(largestInteger(nums, k));

    }
    public static int largestInteger(int[] nums, int k) {
        Set<Integer> freq1 = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i  : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> i : map.entrySet()){
            if(i.getValue().equals(1)){
                freq1.add(i.getKey());
            }
        }
        if(k == nums.length){
            int res = Integer.MIN_VALUE;
            for (Integer freq : nums) {
                res = Math.max(res, freq);
            }
            return res;
        }else if(k == 1) {
            if(!freq1.isEmpty()) {
                int c = Integer.MIN_VALUE;
                for (Integer freq : freq1) {
                   c = Math.max(c, freq);
                }
                return c;
            }else {
               return -1;
            }
        }else {
            if(freq1.contains(nums[0]) && freq1.contains(nums[nums.length - 1])){
                if(nums[0] != nums[nums.length - 1]){
                    return Math.max(nums[0], nums[nums.length - 1]);
                }else {
                    return -1;
                }
            }else {
                if(freq1.contains(nums[0])){
                    return nums[0];
                }else if (freq1.contains(nums[nums.length - 1])){
                    return nums[nums.length - 1];
                }else {
                    return -1;
                }
            }

        }
    }
}
