package Casual;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumequalsK {
    public static void main(String[] args) {
        int[] nums = {1,-1,0};
        int k = 0;
        System.out.println(subarraySum(nums, k));
    }
    public static int subarraySum(int[] nums, int k) {
        Map<Long, Integer> freqMap = new HashMap<>();
        freqMap.put(0L, 1);
        long count = 0;
        int res = 0;
        for (int num : nums) {
            count += num;
            res += freqMap.getOrDefault(count - k, 0);
            freqMap.put(count, freqMap.getOrDefault(count, 0) + 1);
        }
        return res;
    }


}
