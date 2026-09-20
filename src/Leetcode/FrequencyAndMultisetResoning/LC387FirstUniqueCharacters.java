package Leetcode.FrequencyAndMultisetResoning;

import java.util.HashMap;
import java.util.Map;

public class LC387FirstUniqueCharacters {
    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqChar(s));
    }
    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if(!map.containsKey(chars[i])) {
                map.put(chars[i], i);
            }else {
                map.put(chars[i], -1);
            }
        }

        int min = Integer.MAX_VALUE;
        for(Map.Entry<Character, Integer> ans : map.entrySet()){
            if(ans.getValue() > -1) {
                min = Math.min(min, ans.getValue());
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int firstUniqueCharOpt(String s) {
        int[] ans = new int[26];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            ans[aChar - 'a']++;
        }
        for(int i = 0; i < chars.length; i++){
            if(ans[chars[i] - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }
}
