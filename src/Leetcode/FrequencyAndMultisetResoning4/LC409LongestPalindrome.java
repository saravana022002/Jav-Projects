package Leetcode.FrequencyAndMultisetResoning4;

public class LC409LongestPalindrome {
    public static void main(String[] args) {
        String s = "abccccdd";
        longestPalindrome(s);
    }

    public static int longestPalindrome(String s) {
        int[] max = new int[256];
        boolean hasOdd = false;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            max[s.charAt(i) - 'A']++;
        }

        for (int j : max) {
            ans += (j / 2) * 2;
            if(j%2 == 1) {
                hasOdd = true;
            }
        }

        if(hasOdd) {
            ans += 1;
        }
        return ans;
    }
}
