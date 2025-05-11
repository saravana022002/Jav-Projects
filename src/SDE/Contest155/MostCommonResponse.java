package SDE.Contest155;

import java.util.*;

public class MostCommonResponse {

    public static void main(String[] args) {
        List<List<String>> responses = Arrays.asList(
                Arrays.asList("good", "ok"),
                Arrays.asList("ok", "bad"),
                Arrays.asList("bad", "notsure"),
                Arrays.asList("great", "good")
        );

        System.out.println(findCommonResponse(responses));
    }

    public static String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> stringVsCount = new HashMap<>();
        for (List<String> strings : responses){
            Set<String> strs = new HashSet<>(strings);
            for(String s : strs){
                if(stringVsCount.containsKey(s)){
                    stringVsCount.put(s, stringVsCount.get(s) + 1);
                }else {
                    stringVsCount.put(s, 1);
                }
            }
        }
        int max = 0;
        Set<String> maxStrs = new HashSet<>();
        for(Map.Entry<String,Integer> entry : stringVsCount.entrySet()){
            Integer value = entry.getValue();
            if(max < value){
                max = value;
            }
        }
        for(Map.Entry<String,Integer> entry : stringVsCount.entrySet()){
            if(max == entry.getValue()){
                maxStrs.add(entry.getKey());
            }
        }
        return Collections.min(maxStrs);
    }
}
