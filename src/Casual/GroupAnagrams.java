package Casual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Long, List<String>> nums = new HashMap<>();
        Long val;
        for (String str : strs) {
            val = strToNums(str);
            nums.computeIfAbsent(val, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(nums.values());
    }
    public static Long strToNums(String str){
        int[] prime = {
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97, 101
        };
        char[] character = str.toCharArray();
        long value = 1L;
        for (int c : character) {
            value *= prime[c - 'a'];
        }
        return value;
    }



}
