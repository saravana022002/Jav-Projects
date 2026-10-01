package Practice.BinarySearch;

import java.util.Arrays;

// ============================================================================
// LC 2616. Minimize the Maximum Difference of Pairs   [Medium]
// https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/
// Topics: Array, Binary Search, Dynamic Programming, Greedy, Sorting
// ============================================================================
//
// PROBLEM
//   From nums pick p pairs of indices so that no index is used twice. The difference of a
//   pair is |nums[i] - nums[j]|. Among all ways to choose the p pairs, find the one whose
//   LARGEST pair difference is smallest, and return that value. With p = 0 (no pairs) the
//   answer is 0.
//
// EXAMPLES
//   nums = [10,1,2,7,1,3], p = 2  ->  1     pairs (1,1) and (2,3): differences 0 and 1
//   nums = [4,2,1,2],      p = 1  ->  0     the two 2s form a pair with difference 0
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^9,   0 <= p <= nums.length / 2
//
// INTUITION
//   Two observations turn this into an easy yes / no check.
//   1. Sort first. Numbers that are close together are neighbours in sorted order, and any
//      set of pairs can be rearranged into pairs of neighbours without making the largest
//      difference bigger. So only neighbouring elements ever need to be paired.
//   2. Binary search the ANSWER d. "Can I form p pairs whose differences are all <= d?" is
//      monotone: if d works, any larger d works too. So the answers go no, no, ..., yes,
//      yes, and the smallest yes is the result. d ranges over 0 .. (max - min).
//   To answer the check for one d, scan the sorted array from the left: whenever the next
//   two free elements differ by at most d, pair them and move past both; otherwise
//   skip the left one. Grabbing the earliest possible pair never blocks more elements than
//   any other choice, so this finds the largest possible number of pairs.
//
// APPROACH (as implemented below)
//   1. Arrays.sort(nums) (in place).
//   2. left = 0, right = nums[last] - nums[0], answer = 0.
//   3. While left <= right: mid = left + (right - left) / 2.
//        canFormPairs(nums, mid, p) true  -> answer = mid, right = mid - 1 (try smaller)
//        otherwise                        -> left = mid + 1
//   4. Return answer.
//   canFormPairs: count = 0; for i = 1..: if nums[i] - nums[i - 1] <= maxDiff then
//   count++ and i++ (skip the partner); at the end return count >= p.
//
// DRY RUN   nums = [4,2,1,2], p = 1   (sorted: [1,2,2,4])
//   left = 0, right = 4 - 1 = 3, answer = 0
//   mid=1: i=1: 2-1 = 1 <= 1 -> 1 pair; then i=3: 4-2 = 2 > 1.
//          count 1 >= p -> answer = 1, right = 0
//   mid=0: i=1: 2-1 = 1 > 0;  i=2: 2-2 = 0 <= 0 -> 1 pair.
//          count 1 >= p -> answer = 0, right = -1
//   loop ends -> return 0
//
// COMPLEXITY
//   Time : O(n log n) for the sort + O(n log(max - min)) for the search: about 30 rounds,
//          each one a single O(n) scan
//   Space: O(1) extra (the sort is in place)
//
// GOTCHAS
//   - i++ inside the if plus the for loop's own i++ moves two positions ahead, so no
//     element can be used in two pairs.
//   - The check is count >= p, not == p: finding more pairs than needed is fine.
//   - p = 0 works without a special case: every d passes the check, so answer ends at 0.
//   - The caller's array ends up sorted (a side effect).
//
// RELATED
//   - LC 1200 Minimum Absolute Difference - also sorts first, because the closest pairs
//     are neighbours in sorted order.
//   - LC 2528 Maximize the Minimum Powered City (in this repo) - another "binary search
//     the answer, greedy check" problem.
// ============================================================================
public class LC2616MinimizeTheMaximumDifferenceOfPairs {
    public static void main(String[] args) {
        // LC example 2 - prints 0
        int[] nums = {4,2,1,2};
        int p = 1;
        System.out.println(minimizeMax(nums, p));
    }
    // Returns the smallest possible "largest pair difference" over all ways to form p pairs.
    public static int minimizeMax(int[] nums, int p) {
        // close numbers must be neighbours
        Arrays.sort(nums);
        // the answer lies between 0 and the overall spread
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        int mid;
        int answer = 0;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(canFormPairs(nums, mid, p)){
                // p pairs fit within mid: record it and look for a smaller limit
                answer = mid;
                right = mid - 1;
            }else {
                // not enough pairs: the limit must be larger
                left = mid + 1;
            }
        }
        return answer;
    }

    // Greedy check on the sorted array: can at least p disjoint pairs each differ by <= maxDiff?
    private static boolean canFormPairs(int[] nums, int maxDiff, int p) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            // nums[i-1] and nums[i] are close enough: pair them
            if(nums[i] - nums[i - 1] <= maxDiff){
                count ++;
                // skip the partner; with the loop's own i++ the next pair starts at i + 1
                i++;
            }
        }
        return count >= p;
    }
}
