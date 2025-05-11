package SDE.RecursionAndBackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        System.out.println(wordBreak(s, wordDict, 0, new Boolean[s.length()]));
    }
    public static boolean wordBreak(String s, List<String> wordDict, int index, Boolean[] memo) {
        if(index == s.length()){
            return true;
        }
        if(memo[index] != null){
            return memo[index];
        }
        for (int i = 0; i < wordDict.size(); i++){
            String compare = wordDict.get(i);
            if (index + compare.length() <= s.length()) {
                String subString = s.substring(index, index + compare.length());
                if (subString.equals(compare)) {
                    if (wordBreak(s, wordDict, index + compare.length(), memo)) {
                        return memo[index] = true;
                    }
                }
            }
        }
        return memo[index] = false;
    }
}
