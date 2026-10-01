package Practice.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// ============================================================================
// LC 1298. Maximum Candies You Can Get from Boxes   [Hard]
// https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/
// Topics: Array, Breadth-First Search, Graph Theory
// ============================================================================
//
// PROBLEM
//   There are n boxes, 0..n-1. For box i:
//     status[i]         1 = already open, 0 = locked
//     candies[i]        candies inside it
//     keys[i]           boxes whose keys are inside it
//     containedBoxes[i] boxes found inside it
//   You start holding the boxes in initialBoxes. You can open a box you hold if it is
//   open or you have its key; opening it gives you its candies, its keys and its inner
//   boxes. Return the most candies you can collect.
//
// EXAMPLES
//   status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]],
//   containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]  ->  16
//     box 0 (7) reveals boxes 1 and 2; box 2 is open (4) and holds the key to box 1 (5);
//     box 1 reveals box 3, but no key for box 3 ever turns up. 7 + 4 + 5 = 16
//   status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]],
//   containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]  ->  6
//     box 0 hands over every other box together with its key
//
// CONSTRAINTS
//   n == status.length == candies.length == keys.length == containedBoxes.length
//   1 <= n <= 1000,   status[i] is 0 or 1,   1 <= candies[i] <= 1000
//   0 <= keys[i].length <= n,   0 <= keys[i][j] < n,   values of keys[i] are unique
//   0 <= containedBoxes[i].length <= n,   0 <= containedBoxes[i][j] < n,   values unique
//   Each box is contained in one box at most.
//   0 <= initialBoxes.length <= n,   0 <= initialBoxes[i] < n
//
// INTUITION
//   Opening a box never costs anything - it only adds keys and boxes - and every box has
//   at least 1 candy. So the answer is the candies of EVERY box that ever becomes
//   openable, and the order of opening does not matter. BFS: keep a queue of boxes that
//   are ready to open, and park the boxes that are held but still locked until their key
//   shows up.
//
// APPROACH (as implemented below)
//   1. Initial boxes: open ones go into queue (boxInQueue marks them), locked ones go
//      into waitingBoxes.
//   2. Poll a box; skip it if seen (already emptied), else mark seen and add its candies.
//   3. For each key in it: set hasKey; if that box is in waitingBoxes, move it to queue.
//   4. For each nextBox inside it (not seen / not queued): queue it if it is open or
//      hasKey says we already own its key, otherwise put it in waitingBoxes.
//   5. When the queue is empty, nothing else can be opened: return totalCandies.
//
// DRY RUN   example 1 (the one main() uses)
//   start            queue [0]   waiting {}
//   poll 0 : +7  = 7   inner 1 locked, no key -> waiting {1};  inner 2 open -> queue [2]
//   poll 2 : +4  = 11  key 1 -> hasKey[1], box 1 was waiting -> queue [1], waiting {}
//   poll 1 : +5  = 16  inner 3 locked, no key -> waiting {3}
//   queue empty -> return 16 (box 3 is never opened)
//
// STATUS
//   main() calls maxCandies() but does not print the result (it returns 16).
//
// COMPLEXITY
//   Time : O(n + total keys + total inner boxes) - each box is emptied once (seen[]) and
//          each key / inner-box entry is looked at once; HashSet operations are O(1).
//   Space: O(n) - the three boolean arrays, the queue and waitingBoxes.
//
// GOTCHAS
//   - A key can arrive BEFORE its box (remembered in hasKey, used in step 4) or AFTER it
//     (the box is parked in waitingBoxes, released in step 3). Both orders must work.
//   - initialBoxes can be empty -> 0 candies.
//   - boxInQueue is not set in step 4; that is harmless because a box sits inside at most
//     one box, and seen[] would absorb a duplicate queue entry anyway.
// ============================================================================
public class LC1298MaximumCandiesYouCanGetFromBoxes {

    public static void main(String[] args) {
        // Example 1 - expected answer 16 (box 3 is found, but its key never shows up).
        // The return value is not printed, so the program prints nothing (see STATUS).
        int[] status = {1,0,1,0};
        int[] candies = {7,5,4,100};
        int[][] keys = {{},{},{1},{}};
        int[][]containedBoxes = {{1,2},{3},{},{}};
        int[] initialBoxes = {0};
        maxCandies(status, candies, keys, containedBoxes, initialBoxes);
    }
    // BFS over every box that can ever be opened; returns the candies taken from them.
    public static int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] seen = new boolean[n];       // box already opened and emptied
        boolean[] hasKey = new boolean[n];     // we own a key for this box
        boolean[] boxInQueue = new boolean[n]; // box already waiting in the queue


        Queue<Integer> queue = new LinkedList<>();     // boxes we hold AND can open
        Set<Integer> waitingBoxes = new HashSet<>();   // boxes we hold but cannot open yet

        // step 1: sort the starting boxes into "open now" and "wait for a key"
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
            // step 2: open the next ready box (a duplicate entry is skipped)
            int box = queue.poll();
            if(seen[box])
                continue;
            seen[box] = true;
            totalCandies += candies[box];
            // step 3: a new key may unlock a box we are already holding
            for (int key : keys[box]) {
                hasKey[key] = true;
                if (waitingBoxes.contains(key)){
                    queue.offer(key);
                    boxInQueue[key] = true;
                    waitingBoxes.remove(key);
                }
            }
            // step 4: a newly found box opens now if it is open or its key came earlier
            for (int nextBox : containedBoxes[box]) {
                if (seen[nextBox] || boxInQueue[nextBox])
                    continue;
                if(status[nextBox] == 1 || hasKey[nextBox]){
                    queue.add(nextBox);
                    hasKey[nextBox] = true; // not needed for the logic, harmless
                }else {
                    waitingBoxes.add(nextBox);
                }
            }
        }
        // step 5: nothing else can be opened
        return totalCandies;
    }

}
