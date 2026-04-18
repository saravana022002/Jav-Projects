package Posh;

public class CheckInclusion {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidboaoo";
        checkInclusion(s1, s2);
    }
    public static boolean checkInclusion(String s1, String s2) {
        int lengthS1 = s1.length();
        int lengthS2 = s2.length();
        if(lengthS1 > lengthS2){
            return false;
        }
        int[] countS1 = new int[26];
        int[] countS2 = new int[26];
        char[] charStr1 = s1.toCharArray();
        char[] charStr2 = s2.toCharArray();
        for (int i = 0; i < lengthS1; i++) {
            countS1[charStr1[i] - 'a']++;
            countS2[charStr2[i] - 'a']++;
        }

        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if(countS1[i] == countS2[i]){
                matches++;
            }
        }

        int l = 0;
        int r = lengthS1;
        while (r < lengthS2){
            if(matches == 26){
                return true;
            }

            int leftChar = charStr2[l] - 'a';
            int rightChar = charStr2[r] - 'a';
            countS2[leftChar]--;
            if(countS1[leftChar] == countS2[leftChar]){
                matches++;
            }else if(countS1[leftChar] == countS2[leftChar] + 1){
                matches--;
            }
            countS2[rightChar]++;
            if(countS1[rightChar] == countS2[rightChar]){
                matches++;
            }else if(countS1[rightChar] == countS2[rightChar] - 1){
                matches--;
            }
            l++;
            r++;
        }
        return matches == 26;
    }



}
