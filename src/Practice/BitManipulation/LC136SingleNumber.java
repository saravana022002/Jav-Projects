package Practice.BitManipulation;

// ============================================================================
// LC 136. Single Number   [Easy]
// https://leetcode.com/problems/single-number/
// Topics: Array, Bit Manipulation
// ============================================================================
//
// PROBLEM
//   You get a non-empty int array where every value shows up exactly twice, except for
//   one value that shows up only once. Return that lonely value.
//   The problem asks for O(n) time and only O(1) extra space.
//
// EXAMPLES
//   nums = [2,2,1]      ->  1     the two 2s pair up, 1 is left over
//   nums = [4,1,2,1,2]  ->  4     1 and 2 each come twice, 4 only once
//   nums = [1]          ->  1     a one-element array is its own answer
//
// CONSTRAINTS
//   1 <= nums.length <= 3 * 10^4,   -3 * 10^4 <= nums[i] <= 3 * 10^4
//   every element appears twice except exactly one element, which appears once
//
// INTUITION
//   XOR has three handy properties:
//     x ^ x = 0            a value cancels itself
//     x ^ 0 = x            0 changes nothing
//     order is irrelevant  XOR is commutative and associative
//   So XOR-ing the whole array cancels every pair to 0, no matter where the two copies
//   sit, and only the value without a partner survives. No map, no sorting.
//
// APPROACH (as implemented below)
//   1. Start with num = 0.
//   2. For every element j: num = num ^ j.
//   3. Return num - all pairs have cancelled, only the single value is left.
//
// DRY RUN   nums = [2,2,1]   (binary in brackets)
//   start      num = 0            (00)
//   j = 2      num = 0 ^ 2 = 2    (10)
//   j = 2      num = 2 ^ 2 = 0    (00)   <- the pair cancels
//   j = 1      num = 0 ^ 1 = 1    (01)
//   return 1
//
// COMPLEXITY
//   Time : O(n) - one pass over the array
//   Space: O(1) - a single int accumulator
//
// GOTCHAS
//   - Negative values are fine: XOR works on the two's-complement bits, so -5 ^ -5 = 0 too.
//   - The trick needs every other value to appear an EVEN number of times. When the others
//     appear three times (LC 137 Single Number II) plain XOR no longer works.
//
// RELATED
//   - LC 137 Single Number II - others appear three times; count each bit mod 3 instead.
//   - LC 260 Single Number III - two lonely values; XOR all, then split by a set bit.
//   - LC 268 Missing Number - XOR the indices with the values; the missing one is left.
//
// SEE ALSO
//   src/Leetcode/BitManipulationAsASetEncoding5/LC136SingleNumber.java - your curated
//   version (same XOR loop, written as an instance method)
// ============================================================================
public class LC136SingleNumber {

    public static void main(String[] args) {
        // Example 1 - the two 2s cancel each other. Expected output: 1
        int[] nums = {2,2,1};
        System.out.println(singleNumber(nums));
    }
    // Returns the value that appears once: XOR of all elements, pairs cancel to 0.
    public static int singleNumber(int[] nums) {
        // 0 is the XOR identity, so it is a neutral starting value
        int num = 0;
        for (int j : nums) {
            // x ^ x = 0, so each pair wipes itself out; the single value survives
            num = num ^ j;
        }
        return num;
    }

}
