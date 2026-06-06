package Quests.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        lastStoneWeight(stones);
    }
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        int left = 0;
        int right = 0;
        int diff = 0;
        while (!maxHeap.isEmpty() && maxHeap.size() > 1){
            left = maxHeap.poll();
            right = maxHeap.poll();
            diff = left - right;
            if(diff > 0){
                maxHeap.offer(diff);
            }
        }
        return !maxHeap.isEmpty() ? maxHeap.peek() : 0;
    }
}
