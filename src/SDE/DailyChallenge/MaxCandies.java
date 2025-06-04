package SDE.DailyChallenge;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MaxCandies {

    public static void main(String[] args) {
        int[] status = {1,0,1,0};
        int[] candies = {7,5,4,100};
        int[][] keys = {{},{},{1},{}};
        int[][]containedBoxes = {{1,2},{3},{},{}};
        int[] initialBoxes = {0};
        maxCandies(status, candies, keys, containedBoxes, initialBoxes);
    }
    public static int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] seen = new boolean[n];
        boolean[] hasKey = new boolean[n];
        boolean[] boxInQueue = new boolean[n];


        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> waitingBoxes = new HashSet<>();

        for (int box : initialBoxes) {
            if(status[box] == 1){
                queue.offer(box);
                boxInQueue[box] = true;
            }else {
                waitingBoxes.add(box);
            }
        }

        int totalCandies = 0;
        while (!queue.isEmpty()){
            int box = queue.poll();
            if(seen[box])
                continue;
            seen[box] = true;
            totalCandies += candies[box];
            for (int key : keys[box]) {
                hasKey[key] = true;
                if (waitingBoxes.contains(key)){
                    queue.offer(key);
                    boxInQueue[key] = true;
                    waitingBoxes.remove(key);
                }
            }
            for (int nextBox : containedBoxes[box]) {
                if (seen[nextBox] || boxInQueue[nextBox])
                    continue;
                if(status[nextBox] == 1 || hasKey[nextBox]){
                    queue.add(nextBox);
                    hasKey[nextBox] = true;
                }else {
                    waitingBoxes.add(nextBox);
                }
            }
        }
        return totalCandies;
    }

}
