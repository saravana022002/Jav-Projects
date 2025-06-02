package SDE.Strings;

public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "cbba";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        int maxLen = 0;
        String maxStr = "";
        for (int i = 0; i < s.length(); i++) {
            int r = i;
            int l = i;
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                if(maxLen < r - l + 1){
                    maxLen = r - l + 1;
                    maxStr = s.substring(l, r + 1);
                }
                r++;
                l--;
            }

            int lE = i;
            int rE = i + 1;
            while(lE >= 0 && rE < s.length() && s.charAt(lE) == s.charAt(rE)){
                if(maxLen < rE - lE + 1){
                    maxLen = rE - lE + 1;
                    maxStr = s.substring(lE, rE + 1);
                }
                rE++;
                lE--;
            }
        }
        return maxStr;
    }
}
