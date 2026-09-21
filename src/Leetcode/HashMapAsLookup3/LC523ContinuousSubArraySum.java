package Leetcode.HashMapAsLookup3;

import java.util.HashMap;
import java.util.Map;

public class LC523ContinuousSubArraySum {

    public static void main(String[] args) {
        int[] nums = {23,2,4,6,7};
        int k = 6;
        checkSubarraySum(nums, k);
    }
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            int remainder = count % k;
            if(map.containsKey(remainder)){
                int l = map.get(remainder);
                if(i - l >= 2){
                    return true;
                }
            }else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}
