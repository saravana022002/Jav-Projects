package Practice.Math;

// ============================================================================
// LC 231. Power of Two   [Easy]
// https://leetcode.com/problems/power-of-two/
// Topics: Math, Bit Manipulation, Recursion
// ============================================================================
//
// PROBLEM
//   Given any 32-bit integer n (it may be 0 or negative), return true when n equals
//   2^x for some integer x >= 0 (1, 2, 4, 8, ...), otherwise false.
//
// EXAMPLES
//   n = 1   ->  true      2^0
//   n = 16  ->  true      2^4
//   n = 3   ->  false
//
// CONSTRAINTS
//   -2^31 <= n <= 2^31 - 1
//   Follow up: can you do it without loops or recursion?
//
// INTUITION
//   A power of two is 1 multiplied by 2 some number of times. So undo that: keep
//   halving n while it is even. A power of two ends at exactly 1; anything else ends
//   at an odd number bigger than 1 (it had an odd factor). Zero and negatives are never
//   powers of two.
//
// APPROACH (as implemented below)
//   1. n == 0 -> false.
//   2. While n is positive and even, n = n / 2.
//   3. Return n == 1 (a negative n skips the loop and fails this test).
//
// DRY RUN   n = 8   (the input main() uses)
//   8 even -> 4 even -> 2 even -> 1 odd, stop      1 == 1  -> true
//   counter-example n = 12: 12 -> 6 -> 3 odd, stop  3 != 1 -> false
//
// COMPLEXITY
//   Time : O(log n) - n halves every step, at most 30 steps for an int
//   Space: O(1)
//
// GOTCHAS
//   - The n > 0 test in the loop matters: 0 % 2 == 0 and 0 / 2 == 0, so 0 would loop
//     forever without it. (With it, the n == 0 check is just an early exit.)
//   - Negatives: -16 is even, but the n > 0 test stops the loop and -16 != 1 -> false.
//   - Follow-up without a loop: n > 0 && (n & (n - 1)) == 0, because a power of two has
//     exactly one set bit and n - 1 flips that bit and every bit below it.
//
// SEE ALSO
//   src/Leetcode/BitManipulationAsASetEncoding5/LC231IsPowerOfTwo.java - your curated
//   version: a bit-check loop on n - 1 plus the one-line n & (n - 1) trick.
//
// RELATED
//   - LC 326 Power of Three / LC 342 Power of Four - the same question for other bases.
//   - LC 191 Number of 1 Bits - a power of two is exactly the case "one set bit".
// ============================================================================
public class LC231PowerOfTwo {
    public static void main(String[] args) {
        // 8 = 2^3 -> expected output: true
        int n = 8;
        System.out.println(isPowerOfTwo(n));
    }
    // true when n == 2^x for some x >= 0
    public static boolean isPowerOfTwo(int n) {
        if(n == 0){
            return false;
        }
        // strip factors of 2; n > 0 also rules out negatives (and stops 0 looping forever)
        while(n > 0 && n % 2 == 0){
            n = n / 2;
        }
        return n == 1;
    }
}
