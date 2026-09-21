package Leetcode.HashMapAsLookup3;

import java.util.HashSet;
import java.util.Set;

public class LC128LongestConsecutive {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> integers = new HashSet<>();
        for (int num : nums) {
            integers.add(num);
        }
        int max = 0;
        for (int num : integers) {
            if (integers.contains(num - 1)) {
                continue;
            }
            int counter = 1;
            while (integers.contains(num + counter)) {
                counter++;
            }
            max = Math.max(max, counter);
        }
        return max;
    }
}