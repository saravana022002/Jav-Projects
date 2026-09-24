package Leetcode.FrequencyAndMultisetResoning4;

public class LC76MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }
    public static String minWindow(String s, String t) {
        int i = 0;
        int min = Integer.MAX_VALUE;
        String ans = "";

        int[] tally = new int[128];
        int required = 0; // How many distinct characters we need to satisfy
        for(int j = 0; j < s.length(); j++){
            tally[s.charAt(i)]++;
            while (isContains(s.substring(i, j + 1) , t)){
                if(min > j - i + 1){
                    min = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
                i++;
            }
        }
        return ans;
    }


    public static boolean isContains(String sub, String t) {
        if (t.length() > sub.length()) {
            return false;
        }
        int[] tally = new int[128];
        for (int i = 0; i < sub.length(); i++) {
            tally[sub.charAt(i)]++;
        }

        for (int i = 0; i < t.length(); i++) {
            int charIndex = t.charAt(i);
            tally[charIndex]--;
            if(tally[charIndex] < 0){
                return false;
            }
        }
        return true;
    }

}
