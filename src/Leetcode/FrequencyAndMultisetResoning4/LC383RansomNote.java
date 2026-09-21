package Leetcode.FrequencyAndMultisetResoning4;

public class LC383RansomNote {
    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b";
        canConstruct(ransomNote, magazine);
    }
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        char[] charm = ransomNote.toCharArray();
        for (char c : charm) {
            count[c - 'a']++;
        }
        char[] chars = ransomNote.toCharArray();
        for(int i = 0; i < ransomNote.length(); i++){
            if(count[chars[i] - 'a'] == 0){
                return false;
            }
            count[chars[i] - 'a']--;
        }
        return true;
    }
}
