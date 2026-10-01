package Practice.Arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// ============================================================================
// LC 448. Find All Numbers Disappeared in an Array   [Easy]
// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// Topics: Array, Hash Table
// ============================================================================
//
// PROBLEM
//   nums has n numbers and every one of them lies between 1 and n (repeats are allowed, so
//   some numbers of 1..n may never show up). Return all the numbers from 1 to n that do not
//   appear in nums. Follow-up: O(n) time and no extra space (the returned list is free).
//
// EXAMPLES
//   nums = [4,3,2,7,8,2,3,1]  ->  [5,6]   n = 8, and 5 and 6 are the only values of 1..8
//                                         that never occur
//   nums = [1,1]              ->  [2]
//
// CONSTRAINTS
//   n == nums.length,   1 <= n <= 10^5,   1 <= nums[i] <= n
//
// INTUITION
//   The answer can only contain numbers from 1 to n, so all we need is a "was it seen?"
//   flag for each of them. A HashSet holds those flags (A). The follow-up hides them inside
//   nums itself (B): every value v is also a valid index v - 1 of the same array, so seeing
//   v can be recorded by turning nums[v - 1] negative. At the end, a cell that is still
//   positive was never flagged, so the value index + 1 never appeared.
//
// APPROACHES IN THIS FILE
//   A. findDisappearedNumbers(nums)       - HashSet of the values, then every i in 1..n that
//                                           is not in it        - O(n) time, O(n) space
//                                           <- main() runs this one
//   B. findDisappearedNumbersBetter(nums) - sign marking inside nums - O(n) time, O(1) extra
//                                           space. Has a bug, see STATUS.
//   (APPROACH and DRY RUN describe A; B follows them.)
//
// APPROACH (as implemented below, findDisappearedNumbers)
//   1. sets = empty HashSet, ans = empty list.
//   2. Add every num of nums to sets.
//   3. For i = 1 .. nums.length: if sets does not contain i, add i to ans.
//   4. Return ans.
//
// APPROACH B (findDisappearedNumbersBetter - the intended idea)
//   1. For every element: idx = |nums[i]| - 1 (abs, because the cell may already have been
//      made negative); if nums[idx] > 0, negate it. The "> 0" test stops a second flag from
//      turning the sign back to positive.
//   2. Every index i whose cell is still positive was never flagged: add i + 1 to ans.
//   As written, the first loop starts at i = 1 and skips nums[0] (see STATUS).
//
// DRY RUN A   nums = [4,3,2,7,8,2,3,1]
//   sets = {1,2,3,4,7,8}
//   i=1..4: all in sets;  i=5: missing -> add;  i=6: missing -> add;  i=7, i=8: in sets
//   ans = [5,6]
//
// DRY RUN B   the code as written, same input (marking loop runs for i = 1..7)
//   i=1: value 3 -> flag index 2       [4,3,-2,7,8,2,3,1]
//   i=2: value 2 -> flag index 1       [4,-3,-2,7,8,2,3,1]
//   i=3: value 7 -> flag index 6       [4,-3,-2,7,8,2,-3,1]
//   i=4: value 8 -> flag index 7       [4,-3,-2,7,8,2,-3,-1]
//   i=5: value 2 -> index 1 already negative, nothing to do
//   i=6: value 3 -> index 2 already negative, nothing to do
//   i=7: value 1 -> flag index 0       [-4,-3,-2,7,8,2,-3,-1]
//   positive cells: indexes 3, 4, 5 -> ans = [4,5,6]. The 4 is wrong: nums[0] = 4 was never
//   used to flag index 3. The correct answer is [5,6].
//
// COMPLEXITY
//   A: Time O(n) - one pass to fill the set, one scan of 1..n;  Space O(n) - the set
//   B: Time O(n) - two passes;  Space O(1) extra - the marks live in nums itself
//
// GOTCHAS
//   - The numbers to report are 1..n (n = nums.length), not 1..max(nums).
//   - B: value v flags index v - 1 (off by one); B also rewrites nums (negatives are left in).
//
// RELATED
//   - LC 442 Find All Duplicates in an Array - the same sign-flip marking, used to find the
//     values that occur twice.
//   - LC 41 First Missing Positive - also uses the array itself as a table of "seen" flags.
//
// STATUS
//   Known issue: findDisappearedNumbersBetter (B) starts its marking loop at i = 1, so
//   nums[0] is never processed. Whenever the value of nums[0] occurs nowhere else in nums,
//   that value is wrongly reported as missing: [4,3,2,7,8,2,3,1] -> [4,5,6] (correct
//   [5,6]); [1] -> [1] (correct []); [1,2,3] -> [1] (correct []). Starting the loop at
//   i = 0 would fix it. The code is left unchanged on purpose.
//   Also: main() calls A but does not print the result (it returns [5,6]); B is not called.
// ============================================================================
public class LC448FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        // Example 1 - the result is not printed; approach A returns [5, 6]
        int[] nums = {4,3,2,7,8,2,3,1};
        findDisappearedNumbers(nums);
    }

    // Approach A: remember every value in a HashSet, then list the numbers 1..n that are
    // not in it. O(n) time, O(n) space.
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> sets = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        // pass 1: which values occur?
        for (int num : nums){
            sets.add(num);
        }
        // pass 2: every number of 1..n that never occurred is an answer
        for (int i = 1; i <= nums.length; i++) {
            if(!sets.contains(i)){
                ans.add(i);
            }
        }
        return ans;
    }

    // Approach B: mark "value v was seen" by negating nums[v - 1], so no set is needed.
    // O(n) time, O(1) extra space. KNOWN ISSUE: the first loop starts at i = 1 and never
    // looks at nums[0] - see STATUS.
    public static List<Integer> findDisappearedNumbersBetter(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        // pass 1: for each value, flag its home cell (abs: the cell may be flagged already)
        for (int i = 1; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            // negate only once - flagging twice would restore the positive sign
            if(nums[idx] > 0){
                nums[idx] = -nums[idx];
            }
        }
        // pass 2: a cell that is still positive was never flagged -> value i + 1 is missing
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
