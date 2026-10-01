package Practice.Arrays;

// ============================================================================
// LC 3423. Maximum Difference Between Adjacent Elements in a Circular Array   [Easy]
// https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/
// Topics: Array
// ============================================================================
//
// PROBLEM
//   nums is a circular array: besides the usual neighbours, the last element is also next
//   to the first one. Return the largest absolute difference |x - y| over all pairs of
//   neighbouring elements.
//
// EXAMPLES
//   nums = [1,2,4]      ->  3    the wrap-around pair (4, 1) gives |4 - 1| = 3; the usual
//                                pairs give only 1 and 2
//   nums = [-5,-10,-5]  ->  5    |-5 - (-10)| = 5
//
// CONSTRAINTS
//   2 <= nums.length <= 100,   -100 <= nums[i] <= 100
//
// INTUITION
//   The neighbouring pairs are (0,1), (1,2), ..., (n-2,n-1) plus the wrap-around pair
//   (n-1,0). There are only n of them, so look at each one and keep the biggest absolute
//   difference.
//
// APPROACH (as implemented below)
//   1. max = Integer.MIN_VALUE (nothing seen yet).
//   2. For i = 0 .. n-2: comp = |nums[i] - nums[i+1]|; if comp > max, max = comp.
//   3. The wrap-around pair: edge = |nums[n-1] - nums[0]|; if edge > max, max = edge.
//   4. Return max.
//
// DRY RUN   nums = [1,2,4]
//   i=0: |1 - 2| = 1  -> max = 1
//   i=1: |2 - 4| = 2  -> max = 2
//   edge: |4 - 1| = 3 -> max = 3          return 3
//
// COMPLEXITY
//   Time : O(n) - one pass over the n neighbouring pairs
//   Space: O(1) - three integers
//
// GOTCHAS
//   - The wrap-around pair is the easy one to forget: in [1,2,4] the answer comes only from
//     it.
//   - Absolute values are needed because a difference can be negative (example 2).
//   - Integer.MIN_VALUE as the start is safe: n >= 2 guarantees at least one pair, and every
//     difference is >= 0, so the first comparison replaces it. For n = 2 the last-first pair
//     is the same pair again, which does no harm.
//
// STATUS
//   main() is empty, so running the class prints nothing. maxAdjacentDistance is an
//   instance (non-static) method, in LeetCode's Solution style: call it on an object.
//   The code is correct (it returns 3 and 5 for the two examples above).
// ============================================================================
public class LC3423MaximumDifferenceBetweenAdjacentElementsInACircularArray {
    public static void main(String[] args) {
        // nothing is run here - create an object of this class and call maxAdjacentDistance
        // on an array (for [1,2,4] it returns 3)
    }
    // Returns the largest |difference| over all neighbouring pairs, wrap-around pair included.
    public int maxAdjacentDistance(int[] nums) {
        int max = Integer.MIN_VALUE;
        int comp;
        // the ordinary neighbours (i, i+1)
        for (int i = 0; i < nums.length - 1; i++) {
            comp = Math.abs(nums[i] - nums[i + 1]);
            if(comp > max){
                max = comp;
            }
        }

        // the circular pair: last element next to the first
        int edge = Math.abs(nums[nums.length - 1] - nums[0]);
        if(edge > max){
            max = edge;
        }
        return max;
    }

}
