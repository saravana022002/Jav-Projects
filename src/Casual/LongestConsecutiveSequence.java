package Casual;

import java.util.*;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        longestConsecutiveAmr(nums);
    }
    public static int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int n : nums){
            map.put(n, map.getOrDefault(n - 1, 0) + 1);
            max = Math.max(max, map.getOrDefault(n, 0));
        }
        return max;
    }

    public static int longestConsecutiveAmr(int[] nums) {
        Set<Integer> list = new HashSet<>();
        for (int i : nums) list.add(i);
        int max = Integer.MIN_VALUE;
        for (int k : list){
            if(list.contains(k - 1)){
                continue;
            }
            int len = 1;
            int hello = k;
            while (list.contains(hello + 1)){
                len++;
                hello++;
            }
            max = Math.max(len, max);
        }
        return max;
    }
}
