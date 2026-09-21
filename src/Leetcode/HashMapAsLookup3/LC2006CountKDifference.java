package Leetcode.HashMapAsLookup3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC2006CountKDifference {
    public static void main(String[] args) {
        int[] nums = {1,2,2,1};
        int k = 1;
        System.out.println(countKDifference(nums, k));
    }

    // num[i] - num[j] = k ==>
    // num[i] - num[j] = -k ==>
    public static int countKDifference(int[] nums, int k) {
        int[] arr = new int[101];
        int count = 0;
        for (int num : nums) {
            count += arr[num + k];
            count += arr[num - k];
            arr[num]++;
        }
        return count;
    }
}
