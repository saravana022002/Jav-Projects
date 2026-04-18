package NeetCode250.ArraysAndHashing;

import java.util.Arrays;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"ab", "a"};
        System.out.println(longestCommonPrefixSorting(strs));
    }
    public static String longestCommonPrefix(String[] strs) {


        String matches = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < Math.min(strs[i].length(), matches.length())){
                if (strs[i].charAt(j) != matches.charAt(j)) {
                    break;
                }
                j++;
            }
            matches = matches.substring(0, j);
        }
        return matches;
    }


    public static String longestCommonPrefixVertical(String[] strs) {
        String first = strs[0];
        for (int i = 0; i < strs[0].length(); i++) {
            for (String str : strs){
                if(i >= str.length() || first.charAt(i) != str.charAt(i)){
                    return str.substring(0, i);
                }
            }
        }
        return first;
    }

    public static String longestCommonPrefixSorting(String[] strs) {
        Arrays.sort(strs);
        String firstStr = strs[0];
        String lastStr = strs[strs.length - 1];

        int length = Math.min(firstStr.length(), lastStr.length());
        int l = 0;
        while (l < length){
            if(firstStr.charAt(l) != lastStr.charAt(l)){
                break;
            }
            l++;
        }
        return firstStr.substring(0, l);
    }
}
