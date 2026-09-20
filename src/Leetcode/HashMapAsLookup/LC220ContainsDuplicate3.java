package Leetcode.HashMapAsLookup;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LC220ContainsDuplicate3 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int indexDiff = 3, valueDiff = 0;
        System.out.println(containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff));
    }

    ////

//    i != j

//    abs(i - j) <= indexDiff

//    abs(nums[i] - nums[j]) <= valueDiff
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(i != j && Math.abs(i - j) <= indexDiff && Math.abs(nums[i] - nums[j]) <= valueDiff){
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean containsNearbyAlmostDuplicateBetter(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> arr = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long current = nums[i];
            Long floorOrCeiling = arr.ceiling(current - valueDiff);
            if(floorOrCeiling != null && floorOrCeiling <= current + valueDiff){
                return true;
            }
            arr.add(current);
            if(i >= indexDiff){
                arr.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }
}
