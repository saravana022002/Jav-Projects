package Leetcode.FrequencyAndMultisetResoning4;

public class LC242ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        isAnagram(s, t);
    }

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] anagram = new int[26];
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            anagram[sChars[i] - 'a']++;
            anagram[tChars[i] - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(anagram[i] != 0){
                return false;
            }
        }
        return true;
    }
}
