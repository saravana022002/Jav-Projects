package Casual;

public class IsAnagram {
    public static void main(String[] args) {
        String s1 = "rat";
        String s2 = "car";
        isAnagram(s1, s2);
    }
    public static boolean isAnagram(String s, String t) {
        int[] freq = new int[26];
        char[] charS = s.toCharArray();
        for (char aChar : charS) {
            freq[aChar - 'a']++;
        }

        char[] charT = t.toCharArray();
        for (char c : charT) {
            freq[c - 'a']--;
        }
        for (int j : freq) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }
}
