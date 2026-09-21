package Leetcode.PrefixSum2;

import java.util.HashMap;
import java.util.Map;

public class LC525ContiguousArray {
    public static void main(String[] args) {
        int[] nums = {0,1,1,1,1,1,0,0,0};
        findMaxLength(nums);
    }
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(count)) {
                int leftIndex = map.get(count);
                max = Math.max(max, i - leftIndex);
            }else {
                map.put(count, i);
            }

        }
        return max;
    }
}
