package Casual;

import java.util.*;

public class FindKthLargest {
    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println(findKthLargest(nums1, k1));
    }

    public static int findKthLargest(int[] nums, int k) {
        List<Integer> elements = new ArrayList<>();
        for (int num : nums) {
            elements.add(num);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(elements);
        for(int i = k; i > 1; i--){
            maxHeap.poll();
        }
        return maxHeap.peek();
    }
}
