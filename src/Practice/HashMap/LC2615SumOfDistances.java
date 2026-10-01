package Practice.HashMap;

import java.util.*;

// ============================================================================
// LC 2615. Sum of Distances   [Medium]
// https://leetcode.com/problems/sum-of-distances/
// Topics: Array, Hash Table, Prefix Sum
// ============================================================================
//
// PROBLEM
//   nums is a 0-indexed int array. Build an answer array arr with one entry per position i:
//   add up |i - j| for every OTHER position j whose value equals nums[i] (the entry stays 0
//   when that value occurs only once), and return arr.
//
// EXAMPLES
//   nums = [1,3,1,1,2]  ->  [5,0,3,4,0]   the value 1 sits at 0, 2, 3:
//                                         arr[0] = 2+3, arr[2] = 2+1, arr[3] = 3+1
//   nums = [0,5,3]      ->  [0,0,0]       every value is unique
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^9
//
// INTUITION (what the code tries)
//   Only equal values interact, so work value by value. For one value the code keeps just
//   two numbers - how many times it occurs (count) and the sum of those indices - hoping to
//   get every answer in O(1): sum of (i - j) over the other occurrences
//   = i*(count-1) - (indexSum - i). The catch: (i - j) is positive for an occurrence to the
//   LEFT of i and negative for one to the RIGHT, so they cancel instead of adding up, and the
//   final Math.abs only fixes the overall sign. See STATUS.
//
// HOW A CORRECT SOLUTION WORKS (official hints: per-value index lists + prefix sums)
//   For one value, take its indices in increasing order p0 < p1 < ... < p(k-1). For the
//   t-th one, the t indices on its left add t*p_t - (their sum) and the k-1-t indices on its
//   right add (their sum) - (k-1-t)*p_t. Both sums come from running prefix sums, and the
//   left and right parts are added separately, so the total stays O(n).
//
// APPROACH (as implemented below - the closed-form guess)
//   1. sumArr: value -> a 2-element list [count, sum of indices].
//   2. Pass 1, for each i: putIfAbsent(nums[i], [0, 0]); then list[0]++ and list[1] += i.
//   3. Pass 2, for each i: take list = sumArr.get(nums[i]) and set
//      arr[i] = |i*(count-1) - (indexSum - i)|, where count = list.get(0) and
//      indexSum = list.get(1). (The null / size() < 2 guard never fires, see GOTCHAS.)
//   4. Return arr.
//
// DRY RUN   nums = [1,3,1,1,2]   (what main() runs)
//   pass 1 gives: 1 -> [3,5]   3 -> [1,1]   2 -> [1,4]        ([count, index sum])
//   pass 2:  i=0 (1): |0*2 - (5-0)| = 5     correct 5
//            i=1 (3): |1*0 - (1-1)| = 0     correct 0
//            i=2 (1): |2*2 - (5-2)| = 1     correct 3  <- occurrences at 0 (left) and 3 (right)
//                                                         cancel: (2-0) + (2-3) = 1, not 2 + 1
//            i=3 (1): |3*2 - (5-3)| = 4     correct 4
//            i=4 (2): |4*0 - (4-4)| = 0     correct 0
//   returns [5,0,1,4,0]; the correct answer is [5,0,3,4,0]
//
// COMPLEXITY
//   Time : O(n) - two passes, O(1) average per HashMap operation
//   Space: O(n) - one small list per distinct value, plus the result array
//
// GOTCHAS
//   - Math.abs of a total is not the total of the absolute values: left and right
//     occurrences cancel. The formula is right only when all other occurrences of the value lie
//     on one side of i (e.g. the first or the last occurrence, or a value that occurs twice).
//   - The arithmetic is int, although arr is long[]: i * (count-1) and the stored index sum
//     overflow for big inputs (10^5 equal values give an index sum of 4999950000).
//   - list == null || list.size() < 2 can never be true: every value was inserted in pass 1
//     and each list always holds exactly two numbers.
//
// STATUS
//   Known issue: wrong answers. The formula takes the absolute value of a SIGNED sum, so it
//   fails whenever equal values sit on both sides of i. For nums = [1,3,1,1,2] (example 1) it
//   returns [5,0,1,4,0] instead of [5,0,3,4,0]. It also overflows int: with n = 10^5 equal
//   values, arr[0] comes out as 704982704 instead of 4999950000.
//   The code is left unchanged on purpose. main() does not print the result either.
//
// RELATED
//   - LC 2121 Intervals Between Identical Elements - the same problem (the statement says so).
//   - LC 2602 Minimum Operations to Make All Array Elements Equal - also a sum of absolute
//     differences, solved with sorting + prefix sums.
// ============================================================================
public class LC2615SumOfDistances {
    public static void main(String[] args) {
        // Example 1 - nothing is printed. The code returns [5,0,1,4,0]; the correct answer
        // is [5,0,3,4,0] - see STATUS
        int[] nums = {1,3,1,1,2};
        distance(nums);
    }

    // Groups positions by value and builds each answer from (count, index sum) in O(1).
    // KNOWN ISSUE: wrong when equal values sit on both sides of i - see STATUS.
    public static long[] distance(int[] nums) {
        // value -> [how many times it occurs, sum of the indices where it occurs]
        HashMap<Integer, List<Integer>> sumArr =  new HashMap<>();


        // pass 1: fill in count and index sum for every value
        for (int i = 0; i < nums.length; i++) {
            sumArr.putIfAbsent(nums[i], new ArrayList<>(Arrays.asList(0, 0)));
            List<Integer> list = sumArr.get(nums[i]);
            list.set(0, list.get(0) + 1);
            list.set(1, list.get(1) + i);
        }

        long[] arr = new long[nums.length];
        // pass 2: one answer per position
        for (int i = 0; i < nums.length; i++){
            List<Integer> list = sumArr.get(nums[i]);

            // never true: pass 1 stored every value and each list has exactly two entries
            if(list == null || list.size() < 2){
                arr[i] = 0;
            }else {
                // = |sum of (i - j) over the other occurrences j|: a SIGNED sum, so occurrences
                // right of i cancel against those left of i. Computed in int (can overflow).
                arr[i] = Math.abs((i * (list.get(0) - 1)) - (list.get(1) - i));
            }

        }
        return arr;
    }
}
