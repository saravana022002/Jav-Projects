package Practice.Contests.Contest169;

// ============================================================================
// LC 3738. Longest Non-Decreasing Subarray After Replacing at Most One Element   [Medium]
// https://leetcode.com/problems/longest-non-decreasing-subarray-after-replacing-at-most-one-element/
// Topics: Array, Dynamic Programming
// ============================================================================
//
// PROBLEM
//   You may replace at most one element of nums with ANY integer you like. Afterwards,
//   find the longest contiguous subarray whose values never decrease (each element >= the
//   one before it) and return its length.
//
// EXAMPLES
//   nums = [1,2,3,1,2]  ->  4     replace the 1 at index 3 by 3: [1,2,3,3,2] has [1,2,3,3]
//   nums = [2,2,2,2,2]  ->  5     already non-decreasing
//   nums = [3,-4,-2]    ->  3     (main's input) replace 3 by -4: [-4,-4,-2]
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9
//
// INTUITION   (the idea of a correct solution - the code below does NOT implement it)
//   Suppose the replaced element sits at index i and is part of the final subarray. The
//   subarray is then [a non-decreasing run ending at i-1] + [the new value] + [a
//   non-decreasing run starting at i+1]. The new value can be anything, so the two runs
//   can be glued together exactly when nums[i-1] <= nums[i+1]. With
//     pref[i] = length of the longest non-decreasing run ENDING at i
//     suff[i] = length of the longest non-decreasing run STARTING at i
//   the best subarray through i has length pref[i-1] + 1 + suff[i+1] when the runs can be
//   glued, and max(pref[i-1], suff[i+1]) + 1 when they cannot (attach the new value to
//   the longer side). Also take the longest run of the untouched array. The maximum over
//   all i is the answer, in O(n).
//
// APPROACHES IN THIS FILE   (both are wrong on some inputs - see STATUS)
//   A. longestSubarray()  - from every start i scan to the right while the values do not
//                           decrease. The FIRST drop is "repaired": the length still grows
//                           and prev is unchanged, as if that element had been replaced
//                           by prev. The second drop ends the scan. O(n^2)
//                           <- main() runs this one
//   B. longestSubarray1() - the same scan plus a guess at replacing nums[j-1] instead:
//                           prev = nums[j] when j >= 2 and nums[j] >= nums[j-2]. O(n^2)
//
// APPROACH (as implemented below, method A)
//   1. max = 1. For every start i: prev = nums[i], len = 1, isAltered = false.
//   2. For j = i+1 ...: if prev <= nums[j] the run continues (prev = nums[j], len++).
//      Otherwise, if no repair was used yet, use it: isAltered = true and len++ (prev stays).
//      Otherwise break.
//   3. max = the larger of max and len; return max.
//
// DRY RUN   main(): nums = [3,-4,-2]   (method A)
//   i=0: prev=3, len=1.  j=1: 3 <= -4? no, repair -> len=2 (prev stays 3).
//        j=2: 3 <= -2? no, repair already used -> break.          len = 2
//   i=1: prev=-4.        j=2: -4 <= -2 yes -> len = 2.
//   i=2: len = 1.
//   max = 2, but the correct answer is 3: replace the 3 (not the -4) by -4 or less.
//
// STATUS
//   Known issue: both methods give wrong answers, so main() prints 2 for {3,-4,-2} where the
//   correct answer is 3. The scan can only "repair" the element that breaks the order (by
//   pretending it equals prev); it never replaces an EARLIER, too-big element by a smaller
//   value, which is the right move here. Checked against an exhaustive brute force on
//   30000 random arrays (up to 7 elements, values -4..4): longestSubarray is wrong on 4210
//   of them and longestSubarray1 on 2498. Other misses: [4,-1,2] gives 2 from both (correct
//   3); [1,5,2,3] gives 3 from longestSubarray (correct 4; longestSubarray1 gets this one).
//   Both do match the two LeetCode examples (4 and 5). The code is left unchanged on purpose.
//
// COMPLEXITY
//   As written: Time O(n^2) - two nested loops, although the inner one stops at the second
//               drop; Space O(1). (The n <= 10^5 limit needs the O(n) pref / suff idea.)
//
// GOTCHAS
//   - The replacement may be ANY integer, not just a value already in the array, so a gap
//     between two runs closes whenever nums[i-1] <= nums[i+1].
//   - A replaced element can also simply extend one run by 1 (when the runs cannot be glued).
//   - The replacement might be needed at the START of the window (as in main's input) or
//     at the end of it; a left-to-right scan that only fixes "the current element" misses
//     the first kind.
//
// RELATED
//   - LC 674 Longest Continuous Increasing Subsequence - the longest run with no
//     replacement (strictly increasing there).
//   - LC 1493 Longest Subarray of 1's After Deleting One Element - another "longest
//     subarray with one allowed change" problem.
// ============================================================================
public class LC3738LongestNonDecreasingSubarrayAfterReplacingAtMostOneElement {
    public static void main(String[] args) {
        // prints 2 - WRONG, the correct answer is 3 (replace the 3 by -4); see STATUS
        int[] nums = {3,-4,-2};
        System.out.println(longestSubarray(nums));
    }
    // Approach A (wrong on some inputs, see STATUS): scan from every start, allow one repair.
    public static int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 1;
        int len;
        boolean isAltered;
        int prev;
        // i = start of the window
        for (int i = 0; i < n; i++) {
            prev = nums[i];
            isAltered = false;
            len = 1;
            for (int j = i + 1; j < n; j++) {
                if(prev <=  nums[j]){
                    // order kept: the run continues
                    prev = nums[j];
                    len++;
                } else if (!isAltered){
                    // first drop: pretend nums[j] was replaced by prev (length grows, prev stays)
                    isAltered = true;
                    len++;


                } else {
                    // second drop: the window cannot be extended any further
                    break;
                }
            }
            max = Math.max(max, len);
        }
        return max;
    }

    // Approach B: method A plus a guess at replacing nums[j-1] instead (also wrong on some
    // inputs, see STATUS). Not called by main().
    public static int longestSubarray1(int[] nums) {
        int n = nums.length;
        int max = 1;
        int len;
        boolean isAltered;
        int prev;

        for (int i = 0; i < n; i++) {
            prev = nums[i];
            isAltered = false;
            len = 1;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] >= prev) {
                    prev = nums[j];
                    len++;
                } else if (!isAltered) {
                    // Use our one replacement
                    isAltered = true;
                    len++;

                    // simulate replacement:
                    // replace nums[j] with prev (so next comparisons still valid)
                    // OR if j >= 2 and nums[j] >= nums[j-2], simulate replacing nums[j-1]
                    if (j >= 2 && nums[j] >= nums[j - 2]) {
                        prev = nums[j]; // replacing nums[j-1]
                    } // else keep prev same (replacing nums[j])
                } else {
                    break;
                }
            }
            max = Math.max(max, len);
        }
        return max;
    }

}
