package SDE.Strings;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }
    public static String longestCommonPrefix(String[] strs) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            for (String str : strs) {
                if (i == str.length() || strs[0].charAt(i) != str.charAt(i)) {
                    return builder.toString();
                }
            }
            builder.append(strs[0].charAt(i));
        }
        return builder.toString();
    }
}
