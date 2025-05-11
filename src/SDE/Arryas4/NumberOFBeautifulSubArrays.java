package SDE.Arryas4;

import java.util.HashMap;
import java.util.Map;

public class NumberOFBeautifulSubArrays {
    public static void main(String[] args) {
        int[] nums = {4,3,1,2,4};
        System.out.println(optimalSolution(nums));
    }
    public static long optimalSolution(int[] nums){
        long k = 0;
        Map<Long, Long> prefixXORFreq = new HashMap<>();
        prefixXORFreq.put(0L, 1L);
        long sum = 0;
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum ^ nums[i];
            count += prefixXORFreq.getOrDefault(sum ^ k, 0L);
            prefixXORFreq.put(sum, prefixXORFreq.getOrDefault(sum, 0L) + 1L);
        }
        return count;
    }
}
