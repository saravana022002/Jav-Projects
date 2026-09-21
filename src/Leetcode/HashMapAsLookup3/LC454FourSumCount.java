package Leetcode.HashMapAsLookup3;

import java.util.HashMap;
import java.util.Map;

public class LC454FourSumCount {
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>();
        int count = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                map1.put(i + j, map1.getOrDefault(i + j, 0) + 1);
            }
        }
        for (int k : nums3) {
            for (int l : nums4) {
                int target = -(k + l);
                count += map1.getOrDefault(target, 0);
            }
        }

        return count;
    }
}
