package Leetcode.HashMapAsLookup3;

import java.util.HashSet;

public class LC217ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        containsDuplicate(nums);
    }
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> s = new HashSet<>();
        for (int num : nums) {
            if (!s.add(num)) {
                return true;
            }
        }
        return false;
    }

}
