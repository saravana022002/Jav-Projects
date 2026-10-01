package Practice.Contests.Contest158;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// ============================================================================
// LC 3572. Maximize Y-Sum by Picking a Triplet of Distinct X-Values   [Medium]
// https://leetcode.com/problems/maximize-ysum-by-picking-a-triplet-of-distinct-xvalues/
// Topics: Array, Hash Table, Greedy, Sorting, Heap (Priority Queue)
// ============================================================================
//
// PROBLEM
//   x and y are integer arrays of the same length n. Choose three different indices i, j, k
//   whose x values are all different from each other (x[i], x[j], x[k] pairwise distinct)
//   so that y[i] + y[j] + y[k] is as large as possible, and return that sum. If no such
//   three indices exist, return -1.
//
// EXAMPLES
//   x = [1,2,1,3,2], y = [5,3,4,6,2]  ->  14    indices 0, 1, 3: x = 1, 2, 3 and y = 5 + 3 + 6
//   x = [1,2,1,2],   y = [4,5,6,7]    ->  -1    only two different x values exist
//
// CONSTRAINTS
//   n == x.length == y.length,   3 <= n <= 10^5,   1 <= x[i], y[i] <= 10^6
//
// INTUITION
//   Two indices with the same x can never be picked together, so for each x value only its
//   LARGEST y could ever matter. The question becomes: among the distinct x values, take
//   the three whose best y is biggest. Sorting all (x, y) pairs by y from high to low and
//   taking the first pair of every new x until three different x values are collected does
//   exactly that (the first pair seen for an x is its best). If the list ends before three
//   different x values turned up, there is no valid triplet: -1.
//
// APPROACH (as implemented below)
//   1. Wrap every index as NumStore(left = x[i], right = y[i], index = i).
//   2. Sort the list by right (the y value), highest first.
//   3. Walk the list and pick greedily (0 means "not picked yet"):
//        x1 / x2 = the x value / y value of the very first pair;
//        y1 / y2 = the first later pair whose x differs from x1;
//        z1 / z2 = the first later pair whose x differs from both x1 and y1.
//      Stop as soon as all three are filled.
//   4. If any of the three y values is still 0, fewer than 3 distinct x exist: return -1.
//   5. Otherwise return x2 + y2 + z2, the three picked y values.
//
// DRY RUN   main(): x = [1,2,1,2], y = [4,5,6,7]
//   sorted by y descending: (x=2,y=7) (x=1,y=6) (x=2,y=5) (x=1,y=4)
//   (2,7): first pair      -> x1 = 2, x2 = 7
//   (1,6): 1 != x1         -> y1 = 1, y2 = 6
//   (2,5): 2 equals x1     -> cannot be the third
//   (1,4): 1 equals y1     -> cannot be the third
//   z2 is still 0 -> return -1
//   (Example 1 would pick (3,6), (1,5), skip (1,4), then (2,3): 6 + 5 + 3 = 14.)
//
// COMPLEXITY
//   Time : O(n log n) - dominated by the sort; the scan is O(n)
//   Space: O(n) - the list of NumStore objects
//
// GOTCHAS
//   - The names are misleading: x1, y1, z1 hold the chosen X values and x2, y2, z2 hold the
//     Y values that belong to them.
//   - 0 is the "not picked yet" marker. That is safe because every x and y is at least 1.
//   - The third pick must differ from BOTH earlier x values, the second only from the first.
//   - NumStore.index is stored but never used.
//
// RELATED
//   - LC 1090 Largest Values From Labels - the same "sort by value descending, then take
//     greedily while a per-label rule allows it" pattern.
// ============================================================================
public class LC3572MaximizeYSumByPickingATripletOfDistinctXValues {
    public static void main(String[] args) {
        // Example 2 - only two distinct x values, so this prints -1
        int[] x = {1,2,1,2};
        int[] y = {4,5,6,7};
        System.out.println(maxSumDistinctTriplet(x, y));
    }
    // Returns the largest y-sum over triplets with pairwise different x values, or -1.
    public static int maxSumDistinctTriplet(int[] x, int[] y) {
        // pair up x[i] (left) and y[i] (right)
        List<NumStore> stores = new ArrayList<>();
        for (int i = 0; i < y.length; i++) {
            stores.add(new NumStore(x[i], y[i], i));
        }

        // largest y first (the explicit NumStore type lets .reversed() be chained)
        stores.sort(Comparator.comparingInt((NumStore a) -> a.right).reversed());
        // x1/y1/z1 = x values picked so far, x2/y2/z2 = their y values (0 = not picked yet)
        int x1 = 0, y1 = 0, z1 = 0;
        int x2 = 0, y2 = 0, z2 = 0;
        for(NumStore store : stores){
            // pick 1: the pair with the largest y
            if(x1 == 0){
                x1 = store.left;
                x2 = store.right;
                continue;
            }
            // pick 2: the next pair with a different x
            if(y1 == 0 && store.left != x1){
                y1 = store.left;
                y2 = store.right;
                continue;
            }
            // pick 3: the next pair whose x differs from both earlier picks
            if(z1 == 0 && store.left != y1 && store.left != x1){
                z1 = store.left;
                z2 = store.right;
                continue;
            }

            // all three are picked: no need to look further
            if(z2 != 0 && y2 != 0 && x2 != 0){
                break;
            }
        }

        // fewer than three distinct x values
        if(z2 == 0 || y2 == 0 || x2 == 0){
            return -1;
        }

        return x2 + y2 + z2;

    }

    // an (x, y) pair plus its index (the index is never used)
    static class NumStore{
        int left;
        int right;
        int index;
        NumStore(int left, int right, int index){
            this.left = left;
            this.right = right;
            this.index = index;
        }
    }
}
