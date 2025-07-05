package SDE.DailyChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FindLuckyIntegerInAnArray {
    public static void main(String[] args) {
        int[] arr = {2,2,3,4};
        findLucky(arr);
    }
    public static int findLucky(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i] , 0) + 1);
        }

        int max = -1;
        for (Map.Entry<Integer, Integer> map : freqMap.entrySet()){
            if(Objects.equals(map.getValue(), map.getKey()) && map.getValue() > max){
                max = map.getValue();
            }
        }
        return max;
    }
}
