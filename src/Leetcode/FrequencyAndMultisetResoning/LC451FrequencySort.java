package Leetcode.FrequencyAndMultisetResoning;

import java.util.*;

public class LC451FrequencySort {
    public static void main(String[] args) {
        String s = "cccaaa";
        System.out.println(frequencySort(s));
    }
    public static String frequencySort(String s) {
        int[] freq = new int[128];
        for (char ch : s.toCharArray()) {
            freq[ch]++;
        }
        TreeMap<Integer, List<Character>> arr = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < freq.length; i++) {
            if(freq[i] > 0){
                arr.computeIfAbsent(freq[i], k -> new ArrayList<>()).add((char) i);
            }
        }
        StringBuilder out = new StringBuilder();
        for(Map.Entry<Integer, List<Character>> entry : arr.entrySet()){
            for (Character c : entry.getValue()) {
                out.append(String.valueOf(c).repeat(Math.max(0, entry.getKey())));
            }
        }
        return out.toString();
    }


    public static String frequencySortOpt(String s) {
        int[] freq = new int[128];
        int maxFreq = Integer.MIN_VALUE;
        for (char ch : s.toCharArray()) {
            freq[ch]++;
            maxFreq = Math.max(maxFreq, freq[ch]);
        }

        List<Character>[] buckets = new List[maxFreq + 1];
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                if (buckets[freq[i]] == null) {
                    buckets[freq[i]] = new ArrayList<>();
                }
                buckets[freq[i]].add((char) i);
            }
        }
        StringBuilder out = new StringBuilder();
        for (int i = maxFreq - 1; i > 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        out.append(c);
                    }
                }
            }
        }

        return out.toString();
    }
}
