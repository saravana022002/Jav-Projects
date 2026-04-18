package NeetCode250.ArraysAndHashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class IsAnagram {
    public static void main(String[] args) {
        String str1 = "dgqztusjuu";
        String str2 = "dqugjzutsu";
        isAnagram(str1, str2);
        Set<String> set = new HashSet<>();
        set.add("add");
    }

    public static boolean isAnagram(String s, String t) {
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        if(char1.length != char2.length){
            return false;
        }

        int[] char1int = new int[26];

        int length = char1.length;

        int i = 0;
        while(i < length){
            char1int[char1[i] - 'a' ]++;
            char1int[char2[i] - 'a' ]--;
            i++;
        }

        for (int k = 0; k < 26; k++) {
            if(char1int[k] != 0){
                return false;
            }
        }

        return true;
    }
}
