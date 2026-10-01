package Practice.Math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// ============================================================================
// LC 3536. Maximum Product of Two Digits   [Easy]
// https://leetcode.com/problems/maximum-product-of-two-digits/
// Topics: Math, Sorting
// ============================================================================
//
// PROBLEM
//   Take the decimal digits of a positive integer n and pick two of them (two different
//   positions). Return the largest product you can make. A digit value can be used twice
//   only if it really appears twice in n.
//
// EXAMPLES
//   n = 31   ->  3     digits {3,1}: 3 * 1
//   n = 22   ->  4     digits {2,2}: the 2 appears twice, so 2 * 2 is allowed
//   n = 124  ->  8     digits {1,2,4}: best pair is 4 * 2
//
// CONSTRAINTS
//   10 <= n <= 10^9      (so n always has at least two digits)
//
// INTUITION
//   Digits are never negative, so the product is largest when both factors are as big as
//   possible: the answer is (largest digit) * (second largest digit), where "second
//   largest" may equal the largest if that digit occurs twice.
//
// APPROACHES IN THIS FILE
//   A. maxProduct()          - put every digit in a list, take the max, remove ONE copy
//                              of it, take the max again - O(d) time, O(d) space
//   B. optimizedMaxProduct() - one pass that keeps the two largest digits seen so far
//                              - O(d) time, O(1) space
//   (d = number of digits, at most 10.) main() calls both.
//
// APPROACH (as implemented below, method B)
//   1. max1 = max2 = 0.
//   2. Peel digits off the right end with n % 10 and n / 10.
//   3. digit > max1        -> the old best becomes second best: max2 = max1, max1 = digit.
//      else digit > max2   -> it only beats the second best: max2 = digit.
//   4. Return max1 * max2.
//
// DRY RUN   n = 124 (digits come out right to left: 4, 2, 1)
//   digit 4: 4 > max1(0)            -> max2 = 0, max1 = 4
//   digit 2: not > 4, but > max2(0) -> max2 = 2
//   digit 1: beats neither          -> no change
//   return 4 * 2 = 8
//
// COMPLEXITY
//   Time : O(d) for both - each digit is handled once (A also scans its list twice)
//   Space: A: O(d) for the list of digits;  B: O(1)
//
// GOTCHAS
//   - integers.remove(max) removes by VALUE only because max is an Integer object;
//     with a plain int it would call remove(int index) instead.
//   - A repeated digit counts twice (22 -> 4), but a single one does not (21 -> 2).
//   - Zeros are fine: n = 90 -> 9 * 0 = 0.
//
// STATUS
//   Both methods are correct. main() calls them but does not print the results
//   (both return 8 for n = 124).
// ============================================================================
public class LC3536MaximumProductOfTwoDigits {

    public static void main(String[] args) {
        // Example 3: both calls return 8 (the results are not printed)
        int n = 124;
        maxProduct(n);
        optimizedMaxProduct(n);
    }
    // Approach A: collect the digits, then largest * (largest of what is left)
    public static int maxProduct(int n) {
        List<Integer> integers = new ArrayList<>();
        int remainder = 0;
        // split n into its digits (right to left)
        while (n > 0){
            remainder = n % 10;
            integers.add(remainder);
            n = n / 10;
        }

        Integer max = Collections.max(integers);
        // Integer argument -> remove(Object): drops ONE copy of the largest digit
        integers.remove(max);
        // largest remaining digit (equals max when that digit appeared twice)
        Integer secondMax = Collections.max(integers);
        int ans = max * secondMax;
        return ans;
    }

    // Approach B: single pass keeping the two largest digits
    public static int optimizedMaxProduct(int n) {
        int max1 = 0;
        int max2 = 0;
        int digit;
        while (n > 0){
            digit = n % 10;
            if(digit > max1){
                // new largest: the previous largest slides down to second place
                max2 = max1;
                max1 = digit;
            }else if (digit > max2){
                // not the largest, but better than the current second place
                max2 = digit;
            }
            n = n / 10;
        }

        return max1 * max2;
    }



}
