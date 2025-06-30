package SDE.DailyChallenge;

import java.util.*;

public class LongestHarmoniousSub {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(findLHS(nums));
    }
    public static int findLHS(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : freqMap.keySet()){
            if(freqMap.containsKey(num + 1)){
                longest = Math.max(longest, freqMap.get(num) + freqMap.get(num + 1));
            }
        }
        return longest;

    }
}
