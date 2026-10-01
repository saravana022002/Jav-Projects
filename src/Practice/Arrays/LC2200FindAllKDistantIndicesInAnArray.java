package Practice.Arrays;

import java.util.*;

// ============================================================================
// LC 2200. Find All K-Distant Indices in an Array   [Easy]
// https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/
// Topics: Array, Two Pointers
// ============================================================================
//
// PROBLEM
//   You get an array nums, a value key that occurs in it, and a distance k. An index i is
//   "k-distant" when some index j with nums[j] == key lies within k steps of it, that is
//   |i - j| <= k (j may be i itself). Return every k-distant index, in increasing order.
//
// EXAMPLES
//   nums = [3,4,9,1,3,9,5], key = 9, k = 1  ->  [1,2,3,4,5,6]
//       9 sits at indices 2 and 5; everything within 1 step of them qualifies (1,2,3 and
//       4,5,6). Index 0 is 2 steps from the nearest 9, so it is left out.
//   nums = [2,2,2,2,2],     key = 2, k = 2  ->  [0,1,2,3,4]     every index is near some 2
//
// CONSTRAINTS
//   1 <= nums.length <= 1000,   1 <= nums[i] <= 1000,
//   key is an integer taken from nums,   1 <= k <= nums.length
//
// INTUITION
//   Each occurrence of key at position j lights up the window j-k .. j+k. An index is
//   k-distant exactly when it falls inside at least one window. So first find where key
//   occurs, then test every index against those positions. Testing the indices in order
//   0, 1, 2, ... hands back the answer already sorted.
//
// APPROACH (as implemented below)
//   1. indexOfKey = every position j with nums[j] == key (first loop).
//   2. For i = 0 .. nums.length-1 (second loop): look through indexOfKey; as soon as one
//      position kai satisfies |kai - i| <= k, add i to integers and break - so each index is
//      added at most once, even if several key positions are close to it.
//   3. Return integers.
//
// DRY RUN   nums = [3,4,9,1,3,9,5], key = 9, k = 1
//   indexOfKey = [2, 5]
//   i=0: |2-0| = 2, |5-0| = 5 - both > 1     -> skip
//   i=1: |2-1| = 1 <= 1                      -> add 1
//   i=2: |2-2| = 0                           -> add 2
//   i=3: |2-3| = 1                           -> add 3
//   i=4: |2-4| = 2 > 1, then |5-4| = 1       -> add 4
//   i=5: |2-5| = 3 > 1, then |5-5| = 0       -> add 5
//   i=6: |2-6| = 4 > 1, then |5-6| = 1       -> add 6
//   result = [1,2,3,4,5,6]
//
// COMPLEXITY
//   Time : O(n * m) - for each of the n indices the inner loop may walk the m positions of
//          key (m <= n), so O(n^2) in the worst case; with n <= 1000 that is about 10^6 steps
//   Space: O(m) for indexOfKey, plus the output list - O(n) in the worst case
//
// GOTCHAS
//   - The break matters: without it an index near several key positions would be added once
//     per position (in example 2, index 2 would appear five times).
//   - A key position is its own k-distant index (|i - i| = 0 <= k).
//   - Windows that stick out past either end need no special handling: Math.abs on real
//     indices never produces an out-of-range access.
//   - A linear version exists: for each key position j, add the not-yet-added indices of
//     j-k .. j+k (clipped to the array), moving forward only.
//
// RELATED
//   - LC 243 Shortest Word Distance - also measures distances between positions of a
//     target in an array.
//
// STATUS
//   Correct, but main() calls findKDistantIndices without printing the result
//   (it returns [1, 2, 3, 4, 5, 6] for the example in main()).
// ============================================================================
public class LC2200FindAllKDistantIndicesInAnArray {

    public static void main(String[] args) {
        // Example 1 - the result is not printed; it is [1, 2, 3, 4, 5, 6]
        int[] nums = {3,4,9,1,3,9,5};
        int key = 9;
        int k = 1;
        findKDistantIndices(nums, key, k);
    }
    // Returns, in increasing order, every index that is within k of an occurrence of key.
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> indexOfKey = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        // step 1: remember where key occurs
        for (int i = 0; i < nums.length; i++) {
            if(key == nums[i]){
                indexOfKey.add(i);
            }
        }

        // step 2: keep index i if any key position is within distance k of it
        // (i only moves forward, so the list is built in increasing order)
        for (int i = 0; i < nums.length; i++) {
            for (int kai : indexOfKey){
                if(Math.abs(kai - i) <= k){
                    integers.add(i);
                    // one close key position is enough - avoid adding i twice
                    break;
                }
            }
        }
        return integers;
    }

}
