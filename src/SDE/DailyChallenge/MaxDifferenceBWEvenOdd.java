package SDE.DailyChallenge;

import java.util.HashMap;
import java.util.Map;

public class MaxDifferenceBWEvenOdd {
    public static void main(String[] args) {
        String s = "abcabcab";
        System.out.println(maxDifference(s));
    }
    public static int maxDifference(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i) , 0) + 1);
        }
        int oddMax = Integer.MIN_VALUE;
        int evenMin = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> freq : freqMap.entrySet()){
            if(freq.getValue() % 2 == 1) {
                oddMax = Math.max(oddMax, freq.getValue());
            }else {
                evenMin = Math.min(evenMin, freq.getValue());
            }
        }
        return oddMax - evenMin;
    }
}
