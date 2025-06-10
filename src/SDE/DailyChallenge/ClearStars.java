package SDE.DailyChallenge;

import java.util.*;

public class ClearStars {

    public static void main(String[] args) {
        String s = "abc*de*fgh*";
        System.out.println(clearStars1(s));
    }
    public static String clearStars(String s) {
        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '*'){
                stack.add(s.charAt(i));
            }else {
                int minIndex = -1;
                char minChar = 'z' + 1;
                for (int j = 0; j < stack.size(); j++) {
                    if(stack.get(j) <= minChar){
                        minChar = stack.get(j);
                        minIndex = j;
                    }
                }
                if(minIndex != -1){
                    stack.remove(minIndex);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < stack.size(); i++) {
            stringBuilder.append(stack.get(i));
        }
        return stringBuilder.toString();
    }


    public static String clearStars1(String s) {
        TreeMap<Character, Deque<Integer>> charMap = new TreeMap<>();
        List<Integer> positions = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int idx = 0;
        for (char c : s.toCharArray()) {
            if(c != '*'){
                result.append(c);
                positions.add(idx);
                charMap.putIfAbsent(c, new ArrayDeque<>());
                charMap.get(c).addLast(idx);
                idx++;
            }else {
                Character smallestChar = charMap.firstKey();
                Deque<Integer> deque = charMap.get(smallestChar);
                int removeIndex = deque.removeLast();
                if(deque.isEmpty()){
                    charMap.remove(smallestChar);
                }
                for (int j = 0; j < positions.size(); j++) {
                    if(positions.get(j) == removeIndex){
                        result.deleteCharAt(j);
                        positions.remove(j);
                        break;
                    }
                }

            }
        }
        return result.toString();
    }
}
