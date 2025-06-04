package SDE.Strings;

import java.util.ArrayList;
import java.util.List;

public class Anagram {

    public static void main(String[] args) {
        String s = "ab";
        String t = "a";
        isAnagram(s, t);
    }
    public static boolean isAnagram1(String s, String t) {
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            list.add(s.charAt(i));
        }

        for(int i = 0; i < t.length(); i++){
            if(!list.contains(t.charAt(i))){
                return false;
            }else{
                list.remove((Character) t.charAt(i));
            }
        }
        return list.isEmpty();
    }

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[128];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }

        for (int i = 0; i < t.length(); i++) {
            if(freq[t.charAt(i)] == 0){
                return false;
            }
            freq[t.charAt(i)]--;
        }
        return true;
    }
}
