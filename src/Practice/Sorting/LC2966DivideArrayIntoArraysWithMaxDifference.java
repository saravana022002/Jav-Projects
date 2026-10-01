package Practice.Sorting;

import java.util.Arrays;

// ============================================================================
// LC 2966. Divide Array Into Arrays With Max Difference   [Medium]
// https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/
// Topics: Array, Greedy, Sorting
// ============================================================================
//
// PROBLEM
//   nums has n elements, n a multiple of 3, and k is a positive integer. Split nums into n / 3
//   groups of exactly 3 elements so that inside every group any two elements differ by at
//   most k. Return the groups as a 2D array, or an empty array if no such split exists.
//   If several splits work, any one is accepted.
//
// EXAMPLES
//   nums = [1,3,4,8,7,9,3,5,1], k = 2  ->  [[1,1,3],[3,4,5],[7,8,9]]   each group spans <= 2
//   nums = [2,4,2,2,5,2],       k = 2  ->  []    the 5 always ends up with at least one of
//                                                the four 2s, and 5 - 2 = 3 > 2
//
// CONSTRAINTS
//   n == nums.length, 1 <= n <= 10^5, n is a multiple of 3,
//   1 <= nums[i] <= 10^5,   1 <= k <= 10^5
//
// INTUITION
//   Sort first. The smallest unused element must sit in some group, and the two elements
//   closest to it are its sorted neighbours; any other partners are farther away (a wider
//   group) and would leave worse leftovers for the rest. So grouping consecutive triples of
//   the sorted array is the best possible: if one of those triples is too wide (largest -
//   smallest > k), no other split can succeed. In a sorted triple only the two ends matter,
//   because the largest difference is last - first.
//
// APPROACH (as implemented below)
//   1. Arrays.sort(nums). eachSize = nums.length / 3 is the number of groups.
//   2. ans = new int[eachSize][3]. For each group i, start = i * 3 is where its triple begins.
//   3. If nums[start + 2] - nums[start] > k, the triple is too wide -> return an empty
//      array (new int[0][]).
//   4. Otherwise copy nums[start .. start + 2] into ans[i].
//   5. Return ans.
//
// DRY RUN   nums = [2,4,2,2,5,2], k = 2   (what main() runs)
//   sorted: [2,2,2,2,4,5]   eachSize = 2
//   i=0: start=0: nums[2] - nums[0] = 0 <= 2  -> ans[0] = [2,2,2]
//   i=1: start=3: nums[5] - nums[3] = 5 - 2 = 3 > 2 -> return [] (prints [])
//   (Example 1 sorts to [1,1,3,3,4,5,7,8,9]: spreads 2, 2, 2 -> [[1,1,3],[3,4,5],[7,8,9]].)
//
// COMPLEXITY
//   Time : O(n log n) - the sort; the triple check is one O(n) pass
//   Space: O(n) for the output groups (the sort itself happens in place)
//
// GOTCHAS
//   - Arrays.sort(nums) reorders the caller's array.
//   - "Impossible" is an EMPTY 2D array, not null and not a partial result: new int[0][].
//   - Checking last - first of each sorted triple is enough; the middle element lies between.
//   - n being a multiple of 3 is guaranteed, so there are no leftover elements to handle.
//
// STATUS
//   Correct (checked against an exhaustive search on small random inputs).
//
// RELATED
//   - LC 2294 Partition Array Such That Maximum Difference Is K - also sort, then group
//     greedily, but the group sizes are free and you minimise their number
//     (solved in src/Practice/Greedy/LC2294PartitionArraySuchThatMaximumDifferenceIsK.java).
// ============================================================================
public class LC2966DivideArrayIntoArraysWithMaxDifference {
    public static void main(String[] args) {
        // Example 2 - no valid split exists. Expected output: []
        int[] nums = {2,4,2,2,5,2};
        int k = 2;
        System.out.println(Arrays.deepToString(divideArray(nums, k)));
    }
    // Sort, cut into consecutive triples, reject the whole input if one triple is too wide.
    public static int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        // number of groups (n / 3)
        int eachSize = nums.length / 3;
        int[][] ans = new int[eachSize][3];
        for (int i = 0; i < eachSize; i++) {
            // the triple is nums[start], nums[start + 1], nums[start + 2]
            int start = i * 3;
            // sorted, so last - first is the largest difference inside the triple
            if (nums[start + 2] - nums[start] > k) {
                return new int[0][];
            }
            for (int j = 0; j < 3; j++) {
                ans[i][j] = nums[start + j];
            }
        }
        return ans;
    }
}
