package Quests.Heap;

import java.util.*;
import java.util.stream.Collectors;

public class KSmallestPairs {
    public static void main(String[] args) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        System.out.println(kSmallestPairs1(nums1, nums2, k));
    }
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0] + a[1])
        );

        for (int value : nums1) {
            for (int i : nums2) {
                minHeap.offer(new int[]{value, i});
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(Arrays.stream(minHeap.poll())
                    .boxed()
                    .collect(Collectors.toList()));
        }
        return ans;
    }

    public static List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0 || k == 0) {
            return result;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])
        );
        for (int i = 0; i < nums1.length; i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        while(!minHeap.isEmpty() && k > 0){
            int[] curr = minHeap.poll();
            int i = curr[1];
            int j = curr[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
            if(j + 1 < nums2.length) {
                minHeap.offer(new int[]{
                        nums1[i] + nums2[j + 1],
                        i,
                        j + 1
                });
            }
            k--;
        }
        return result;
    }

}
