package Practice.Sorting;

import java.util.Arrays;
import java.util.Comparator;

// ============================================================================
// LC 2099. Find Subsequence of Length K With the Largest Sum   [Easy]
// https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
// Topics: Array, Hash Table, Sorting, Heap (Priority Queue)
// ============================================================================
//
// PROBLEM
//   Choose exactly k elements of nums, keeping them in their original order (a subsequence),
//   so that their sum is as large as possible, and return that subsequence. When several
//   choices tie, any one is accepted.
//
// EXAMPLES
//   nums = [2,1,3,3],   k = 2  ->  [3,3]      sum 6
//   nums = [-1,-2,3,4], k = 3  ->  [-1,3,4]   sum 6 - k forces three picks, so even the -1
//                                             is taken (the best three are 4, 3 and -1)
//   nums = [3,4,3,3],   k = 2  ->  [3,4]      sum 7 ([4,3] is also accepted)
//
// CONSTRAINTS
//   1 <= nums.length <= 1000,   -10^5 <= nums[i] <= 10^5,   1 <= k <= nums.length
//
// INTUITION
//   The k largest values always make the largest possible sum of k elements (swapping a
//   chosen value for a bigger unchosen one can only raise the sum). The catch is the order:
//   the answer must be a subsequence, so after picking the k biggest we must put them back in
//   their original positions. Hence every value travels with its index: sort (value, index)
//   pairs by value to find the top k, then sort those k pairs by index.
//
// APPROACH (as implemented below)
//   1. pair[i] = {nums[i], i}: a value together with its original position.
//   2. Arrays.sort(pair, (a, b) -> b[0] - a[0]): largest value first.
//   3. topK = the first k pairs (Arrays.copyOfRange(pair, 0, k)).
//   4. Arrays.sort(topK, by a[1]): back into original index order.
//   5. result[i] = topK[i][0] for i in 0 .. k-1; return result.
//
// DRY RUN   nums = [2,1,3,3], k = 2   (what main() runs)
//   pair            : {2,0} {1,1} {3,2} {3,3}
//   sorted by value : {3,2} {3,3} {2,0} {1,1}     (the sort is stable, equal values keep order)
//   topK (k = 2)    : {3,2} {3,3}
//   sorted by index : {3,2} {3,3}                 -> result = [3,3]
//
// COMPLEXITY
//   Time : O(n log n) - the sort of n pairs dominates; the second sort is only k log k
//   Space: O(n) - the pair array (plus the k-sized topK and result)
//
// GOTCHAS
//   - Without the index you cannot restore the order: sorting plain values would produce the
//     right multiset but not a subsequence of nums.
//   - Ties between equal values do not change the sum; this code happens to keep the earlier
//     ones (stable sort, descending by value).
//   - The comparator b[0] - a[0] is safe only because |nums[i]| <= 10^5; with arbitrary ints
//     the subtraction could overflow (Integer.compare(b[0], a[0]) never does).
//   - Negative numbers are still picked when k is larger than the count of positive ones.
//
// STATUS
//   Correct (checked on random inputs: length k, maximum sum, and a real subsequence).
//
// RELATED
//   - LC 215 Kth Largest Element in an Array - the "top k by value" step done with a heap
//     (solved in src/Practice/Heap/LC215KthLargestElementInAnArrayPriorityQueue.java).
// ============================================================================
public class LC2099FindSubsequenceOfLengthKWithTheLargestSum {
    public static void main(String[] args) {
        // Example 1. Expected output: [3, 3]
        int[] nums = {2,1,3,3};
        int k = 2;
        System.out.println(Arrays.toString(maxSubsequence(nums, k)));
    }
    // The k largest values, listed in their original order (a subsequence of nums).
    public static int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        // pair[i] = {value, original index}
        int[][] pair = new int[n][2];
        for (int i = 0; i < n; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }

        // largest value first
        Arrays.sort(pair, (a, b) -> b[0] - a[0]);

        // the k biggest values (with their indices)
        int[][] topK = Arrays.copyOfRange(pair, 0, k);

        // back to the original order, so the result is a subsequence
        Arrays.sort(topK, Comparator.comparingInt(a -> a[1]));

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topK[i][0];
        }
        return result;
    }
}
