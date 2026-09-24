package Leetcode.FrequencyAndMultisetResoning4;

public class LC567PermutationinString {
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()){
            return false;
        }
        int[] need = new int[26];
        int[] have = new int[26];
        int matching = 0;
        for (int i = 0; i < s1.length(); i++) {
            need[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if(need[i] == 0){
                matching++;
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            int in = s2.charAt(i) - 'a';
            if(have[in] == need[in]){
                matching--;
            }
            have[in]++;
            if(have[in] == need[in]){
                matching++;
            }

            if(i >= s1.length()){
                int out = s2.charAt(i - s1.length()) - 'a';
                if(have[out] == need[out]){
                    matching--;
                }
                have[out]--;
                if(have[out] == need[out]){
                    matching++;
                }
            }
            if (matching == 26){
                return true;
            }
        }
        return false;
    }
}
