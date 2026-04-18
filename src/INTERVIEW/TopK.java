package INTERVIEW;

import java.util.*;

public class TopK {


//    Print the k'th most frequent number from the array A.
//    input: A = [1, 7, 7, 7, 4, 4, 4, 9, 9]
//    k = 1, ouptut: 7 (or) 4
//    k = 2, ouptut: 9
//    k = 3, ouptut: 1
    public static void main(String[] args) {
        int[] A = {1, 7, 7, 4, 4, 9, 9};
        int k =  3;
        System.out.println(giveTopKElements(A, k));
    }

    private static int giveTopKElements(int[] A, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        List<List<Integer>> freqencyMapBasedOnCount = new ArrayList<>();

        for (int i = 0; i < A.length + 2; i++) {
            freqencyMapBasedOnCount.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            freqencyMapBasedOnCount.get(value).add(key);
        }

        int topKth = 0;

        int count = 0;

        for (int i = A.length + 1; i >= 0; i--) {
            if (!freqencyMapBasedOnCount.get(i).isEmpty()) {
                count++;
            }
            if(count == k){
                topKth = freqencyMapBasedOnCount.get(i).get(0);
                break;
            }
        }

        return topKth;
    }


}
