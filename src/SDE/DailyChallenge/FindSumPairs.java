package SDE.DailyChallenge;

import java.util.*;

public class FindSumPairs {
    public static void main(String[] args) {
        String[] operations = {
                "FindSumPairs", "count", "add", "count", "count", "add", "add", "count"
        };

        int[][] values = {
                {1, 1, 2, 2, 2, 3},
                {1, 4, 5, 2, 5, 4},
                {7},
                {3, 2},
                {8},
                {4},
                {0, 1},
                {1, 1},
                {7}
        };

        FindSumPairs obj = null;
        int valueIndex = 0;

        for (String op : operations) {
            switch (op) {
                case "FindSumPairs":
                    int[] nums1 = values[valueIndex++];
                    int[] nums2 = values[valueIndex++];
                    obj = new FindSumPairs(nums1, nums2);
                    break;
                case "add":
                    int index = values[valueIndex][0];
                    int val = values[valueIndex][1];
                    valueIndex++;
                    obj.add(index, val);
                    break;
                case "count":
                    int tot = values[valueIndex++][0];
                    obj.count(tot);
                    break;
            }
        }
    }


    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> freqMap;
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        freqMap = new HashMap<>();
        for (int num : nums2) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int oldValue = nums2[index];
        freqMap.put(oldValue, freqMap.get(oldValue) - 1);
        if (freqMap.get(oldValue) == 0) freqMap.remove(oldValue);
        nums2[index] += val;
        freqMap.put(nums2[index], freqMap.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int count = 0;
        for (int num : nums1){
            count += freqMap.getOrDefault(tot - num, 0);
        }
        return count;
    }

}
