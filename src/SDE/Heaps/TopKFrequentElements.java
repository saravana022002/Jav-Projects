package SDE.Heaps;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = {-1,-1};
        int k = 1;
        topKFrequent(nums, k);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 0){
            return nums;
        }
        if (k <= 0) return new int[0];

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }


        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        int[] ans1 = new int[result.size()];
        int index = 0;
        for(int a : result){
            ans1[index] = a;
            index ++;
        }
        return ans1;
    }


    public static int[] topKFrequentOptimal(int[] nums, int k) {
        if(nums.length == 0){
            return nums;
        }
        if (k <= 0) return new int[0];

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> freqList = new ArrayList<>(freqMap.entrySet());

        // Step 3: Bubble sort by frequency (descending)
        int n = freqList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if(freqList.get(j).getValue() < freqList.get(j + 1).getValue() ){
                    Collections.swap(freqList, j, j + 1);
                }
            }
        }

        int[] ans = new int[k];
        int index = 0;
        for (int i = 0; i < k; i++) {
            ans[index] = freqList.get(i).getKey();
            index ++;
        }
        return ans;
    }
}
