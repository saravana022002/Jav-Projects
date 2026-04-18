package NeetCode250.ArraysAndHashing;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> stringMap = new HashMap<>();
        for (String str : strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            stringMap.computeIfAbsent(Arrays.toString(charArray), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(stringMap.values());
    }

    public static List<List<String>> groupAnagramsChar(String[] strs) {
        Map<String, List<String>> stringMap = new HashMap<>();
        for (String str : strs){
            char[] charArray = str.toCharArray();
            char[] charArray26 = new char[26];
            for (char c : charArray) {
                charArray26[c - 'a']++;
            }

            stringMap.computeIfAbsent(Arrays.toString(charArray26), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(stringMap.values());
    }
}
