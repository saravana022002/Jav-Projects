package Leetcode.HashMapAsLookup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Lc219ContainsDuplicate2 {
    public static void main(String[] args) {
        int[] nums = {1,0,1,1};
        int k = 1;
        System.out.println(containsNearbyDuplicate(nums , k));
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> ints = new HashMap<>();
        int i = 0;
        for (int num : nums) {
            Integer prev = ints.put(num, i);
            if(prev != null && Math.abs(i - prev) <= k){
                return true;
            }
            i++;
        }
        return false;
    }
}
