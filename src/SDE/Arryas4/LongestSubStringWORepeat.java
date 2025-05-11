package SDE.Arryas4;

import java.util.*;

public class LongestSubStringWORepeat {
    public static void main(String[] args) {
        String str = "abba";
        System.out.println(lengthOfLongestSubstring(str));
        System.out.println(optimalLengthOfLongestSubstring(str));
        System.out.println(optimalSoLengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> characters = new LinkedList<>();
        int longestSize = 0;
        for (int i = 0; i < s.length(); i++){
            if(!characters.contains(s.charAt(i))) {
                characters.add(s.charAt(i));
            }else {
                while(!characters.isEmpty() && characters.poll() != s.charAt(i)){}
                characters.add(s.charAt(i));
            }
            if(characters.size() > longestSize){
                longestSize = characters.size();
            }
        }
        return longestSize;
    }

    public static int optimalLengthOfLongestSubstring(String s) {
        Set<Character> characters = new HashSet<>();
        int longestSize = 0;
        int l = 0;
        int r = 0;
        while(r < s.length()){
            if(characters.contains(s.charAt(r))) {
                while (l < r && characters.contains(s.charAt(r))) {
                    characters.remove(s.charAt(l));
                    l++;
                }
            }
            characters.add(s.charAt(r));
            longestSize = Math.max(longestSize, r - l + 1);
            r++;
        }
        return longestSize;
    }

    public static int optimalSoLengthOfLongestSubstring(String s) {
        Map<Character, Integer> characterVsCount = new HashMap<>();
        int longestSize = 0;
        int l = 0;
        int r = 0;
        while(r < s.length()){
            if(characterVsCount.containsKey(s.charAt(r))) {
                l = Math.max(l , characterVsCount.get(s.charAt(r)) + 1);
            }
            characterVsCount.put(s.charAt(r), r);
            longestSize = Math.max(longestSize, r - l + 1);
            r++;
        }
        return longestSize;
    }
}
