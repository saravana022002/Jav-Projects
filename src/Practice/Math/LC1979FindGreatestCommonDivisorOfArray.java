package Practice.Math;

// ============================================================================
// LC 1979. Find Greatest Common Divisor of Array   [Easy]
// https://leetcode.com/problems/find-greatest-common-divisor-of-array/
// Topics: Array, Math, Number Theory, Euclidean Algorithm, Greatest Common Divisor
// ============================================================================
//
// PROBLEM
//   Given an integer array, take its smallest value and its largest value and return
//   their greatest common divisor: the biggest positive integer dividing both exactly.
//   The other elements do not matter.
//
// EXAMPLES
//   nums = [2,5,6,9,10]  ->  2     min 2, max 10, gcd(2, 10) = 2
//   nums = [7,5,6,8,3]   ->  1     min 3, max 8, they share no factor
//   nums = [3,3]         ->  3     min = max = 3
//
// CONSTRAINTS
//   2 <= nums.length <= 1000,   1 <= nums[i] <= 1000
//
// INTUITION
//   Euclid's rule: gcd(a, b) = gcd(b, a % b). Any number that divides both a and b
//   also divides a % b (which is a minus a multiple of b), and the other way round, so
//   the pair (b, a % b) has exactly the same common divisors. The remainder keeps
//   shrinking; once it is 0, gcd(a, 0) = a.
//
// APPROACH (as implemented below)
//   1. findGCD: one pass over nums keeps the running max and min.
//   2. gcd(max, min): while b != 0, replace (a, b) with (b, a % b) using temp.
//   3. When b reaches 0, a is the answer.
//
// DRY RUN   nums = [2,5,6,9,10]   (the input main() uses)
//   scan          -> max = 10, min = 2
//   gcd(10, 2)    : (a, b) = (10, 2) -> (2, 10 % 2 = 0)   -> return 2
//   Example 2 for a longer run, gcd(8, 3): (8,3) -> (3,2) -> (2,1) -> (1,0) -> 1
//
// COMPLEXITY
//   Time : O(n + log(min)) - one scan, then Euclid needs O(log) steps because the
//          remainder at least halves every two steps
//   Space: O(1)
//
// GOTCHAS
//   - Argument order does not matter: gcd(2, 10) would first swap to (10, 2 % 10 = 2).
//   - b != 0 is the stop test; gcd(x, 0) = x.
//
// STATUS
//   main() calls findGCD but does not print the result (it is 2).
//
// RELATED
//   - LC 1071 Greatest Common Divisor of Strings - the same gcd, applied to lengths.
//   - LC 2169 Count Operations to Obtain Zero - the subtraction form of Euclid (in
//     this repo).
// ============================================================================
public class LC1979FindGreatestCommonDivisorOfArray {
    public static void main(String[] args) {
        // Example 1 - expected 2 (the return value is not printed)
        int[] nums = {2,5,6,9,10};
        findGCD(nums);
    }

    // returns gcd(smallest element, largest element)
    public static int findGCD(int[] nums) {
        // one pass for both extremes
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        return gcd(max, min);
    }

    // iterative Euclid: gcd(a, b) = gcd(b, a % b) until b becomes 0
    public static int gcd(int a, int b){
        int temp = 0;
        while(b != 0){
            // (a, b) -> (b, a % b)
            temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }



}
