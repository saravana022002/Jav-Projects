package Casual;

import java.util.HashMap;
import java.util.Map;

public class MinLength {
    public static void main(String[] args) {
        int[] nums = {3,1,7};
        int k = 8;
        minLength(nums, k);
    }
    public static int minLength(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < nums.length){
            if(nums[r] >= k){
                minLength = Math.min(minLength, 1);
            }
            if(map.containsKey(nums[r])){
                l = r;
                sum = 0;
            }
            sum = sum + nums[r];
            if(sum >= k){
                minLength = Math.min(minLength, r - l + 1);
            }
            map.put(nums[r], r);
            r++;
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static int minLength1(int[] nums, int k) {
        int l = 0;
        int distinctSum = 0;
        int minLen = Integer.MAX_VALUE;

        Map<Integer, Integer> freq = new HashMap<>();

        int[] drelanvixo = nums;

        for (int r = 0; r < nums.length; r++) {
            int val = nums[r];
            freq.put(val, freq.getOrDefault(val, 0) + 1);

            if (freq.get(val) == 1) {
                distinctSum += val;
            }

            while (distinctSum >= k) {
                minLen = Math.min(minLen, r - l + 1);

                int leftVal = nums[l];
                freq.put(leftVal, freq.get(leftVal) - 1);
                if (freq.get(leftVal) == 0) {
                    distinctSum -= leftVal;
                }
                l++;
            }
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }


}
