package Leetcode.PrefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1442CountTriplets {
    public static void main(String[] args) {
        int[] arr = {2,3,1,6,7};
        System.out.println(countTriplets(arr));
    }
    public static int countTriplets(int[] arr) {
        Map<Integer, List<Integer>> index = new HashMap<>();
        List<Integer> arrList = new ArrayList<>();
        arrList.add(-1);
        index.put(0, arrList);
        int count = 0;
        int prefixXor = 0;
        for (int j = 0; j < arr.length; j++) {
            prefixXor ^= arr[j];
            if(index.containsKey(prefixXor)){
                for (Integer i : index.get(prefixXor)) {
                    count += j - i - 1;
                }
            }
            index.computeIfAbsent(prefixXor, k -> new ArrayList<>()).add(j);
        }
        return count;
    }


    public static int countTripletsOptimal(int[] arr) {
        Map<Integer, Integer> totalIndexMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        totalIndexMap.put(0, -1);
        countMap.put(0, 1);
        int count = 0;
        int prefixXor = 0;
        for (int j = 0; j < arr.length; j++) {
            prefixXor ^= arr[j];
            if(countMap.containsKey(prefixXor)){
                int minusTerm = totalIndexMap.get(prefixXor);
                int countTerm = totalIndexMap.get(prefixXor);
                count += countTerm * (j - 1) - minusTerm;
            }
            totalIndexMap.put(prefixXor, totalIndexMap.getOrDefault(prefixXor, 0) + j);
            countMap.put(prefixXor, countMap.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }






}
