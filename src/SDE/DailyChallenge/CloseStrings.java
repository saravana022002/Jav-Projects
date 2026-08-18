package SDE.DailyChallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloseStrings {
    public static void main(String[] args) {
        String word1 = "uau";
        String word2 = "ssx";
        System.out.println(closeStrings(word1, word2));
    }

    public static boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()){
            return false;
        }
        int[] charCount1 = new int[26];
        int[] charCount2 = new int[26];
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (int i = 0; i < word1.length(); i++){
            charCount1[word1.charAt(i) - 'a']++;
            charCount2[word2.charAt(i) - 'a']++;
            set1.add(word1.charAt(i));
            set2.add(word2.charAt(i));
        }

        for(Character c : set1){
            if(!set2.contains(c)){
                return false;
            }
        }


        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            if(charCount1[i] != 0){
                map.put(charCount1[i], map.getOrDefault(charCount1[i], 0) + 1);
            }
            if(charCount2[i] != 0){
                map.put(charCount2[i], map.getOrDefault(charCount2[i], 0) - 1);
            }
        }

        for (Integer value : map.values()){
            if(value != 0){
                return false;
            }
        }
        return true;
    }
}
