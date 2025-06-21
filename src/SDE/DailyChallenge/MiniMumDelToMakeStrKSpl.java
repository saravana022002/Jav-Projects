package SDE.DailyChallenge;

import java.util.*;

public class MiniMumDelToMakeStrKSpl {
    public static void main(String[] args) {
        String word = "aabcaba";
        int k = 0;
        System.out.println(minimumDeletions(word, k));
    }
    public static int minimumDeletions(String word, int k) {
        int deletions;
        int ans = Integer.MAX_VALUE;
        Map<Character, Integer> freq = new HashMap<>();
        for(Character c : word.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        List<Integer> frequency = new ArrayList<>(freq.values());

        Collections.sort(frequency);

        for (int i = 0; i < frequency.size(); i++) {
            int target = frequency.get(i);
            deletions = 0;
            for (int comp : frequency) {
                if (target > comp) {
                    deletions += comp;
                } else if (target + k < comp) {
                    deletions += comp - (target + k);
                }
            }
            ans = Math.min(ans, deletions);
        }
        return ans;
    }
}
