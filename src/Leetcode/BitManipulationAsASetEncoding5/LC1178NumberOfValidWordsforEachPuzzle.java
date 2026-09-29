package Leetcode.BitManipulationAsASetEncoding5;

import java.util.*;

public class LC1178NumberOfValidWordsforEachPuzzle {
    public static void main(String[] args) {
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"}, puzzles = {"absoryz","actresz"};
        System.out.println(findNumOfValidWords(words, puzzles));
    }

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Set<Character> chars;
        int[] ans = new int[puzzles.length];
        for (int i = 0; i < puzzles.length; i++) {
            chars = new HashSet<>();
            for (int j = 0; j < puzzles[i].length(); j++) {
                chars.add(puzzles[i].charAt(j));
            }
            for (int j = 0; j < words.length; j++) {
                boolean isFirstPresent = false;
                boolean outsideCharacter = false;
                for (int k = 0; k < words[j].length(); k++) {
                    if(chars.contains(words[j].charAt(k))){
                        if(puzzles[i].charAt(0) == words[j].charAt(k)){
                            isFirstPresent = true;
                        }
                    }else {
                        outsideCharacter = true;
                        break;
                    }
                }
                if(isFirstPresent && !outsideCharacter){
                    ans[i]++;
                }
            }

        }
        List<Integer> answer = new ArrayList<>();
        for (int an : ans) {
            answer.add(an);
        }
        return answer;
    }
}
