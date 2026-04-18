package NeetCode250.ArraysAndHashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        containsDuplicateBrute(nums);
    }

    public static boolean containsDuplicateBrute(int[] nums) {
        Map<Integer, Integer> ints = new HashMap<>();
        for (int num : nums) {
            if (ints.containsKey(num)) {
                return true;
            } else {
                ints.put(num, 1);
            }
        }
        return false;
    }
//    public static boolean containsDuplicateBetter(int[] nums) {
//
//    }

//    public static boolean containsDuplicateOptimal(int[] nums) {
//
//    }
}
