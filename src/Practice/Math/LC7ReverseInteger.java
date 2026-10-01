package Practice.Math;

// ============================================================================
// LC 7. Reverse Integer   [Medium]
// https://leetcode.com/problems/reverse-integer/
// Topics: Math
// ============================================================================
//
// PROBLEM
//   Reverse the decimal digits of a 32-bit signed integer x, keeping its sign. If the
//   reversed value does not fit in a 32-bit int, return 0. Pretend 64-bit integers do not
//   exist, so you cannot compute in long and check afterwards.
//
// EXAMPLES
//   x = 123         ->  321
//   x = -123        ->  -321
//   x = 120         ->  21     the trailing zero becomes a leading zero and disappears
//   x = 1534236469  ->  0      9646324351 does not fit in an int
//
// CONSTRAINTS
//   -2^31 <= x <= 2^31 - 1
//
// INTUITION
//   Pop the last digit (x % 10) and push it onto the result (res * 10 + digit) until x is
//   0. The only danger is that res * 10 + digit overflows, so check BEFORE doing it:
//   it passes MAX = 2147483647 exactly when res > MAX / 10, or res == MAX / 10 and the
//   digit is > 7 (MAX ends in 7). Likewise for MIN = -2147483648, which ends in -8.
//
// APPROACH (as implemented below)
//   1. res = 0.
//   2. While x != 0:
//        remainder = x % 10 (negative when x is negative), then x = x / 10
//        if appending remainder would pass Integer.MAX_VALUE or Integer.MIN_VALUE
//          -> return 0
//        res = res * 10 + remainder
//   3. Return res.
//
// DRY RUN   x = -123 (the input main() uses)
//   remainder -3, x -> -12, res = -3
//   remainder -2, x -> -1,  res = -32
//   remainder -1, x -> 0,   res = -321   -> prints -321
//
// COMPLEXITY
//   Time : O(log10 |x|) - one step per digit (at most 10)
//   Space: O(1)
//
// GOTCHAS
//   - Java's % keeps the sign of x (-123 % 10 == -3), so negatives need no special case.
//   - The overflow checks must run before res * 10 + remainder; afterwards it has wrapped.
//   - Integer.MIN_VALUE reverses to 0 (8463847412 is too big) - and the code never needs
//     Math.abs, which would overflow on it.
//
// RELATED
//   - LC 9 Palindrome Number - builds the same reversed number to compare it with x.
//   - LC 8 String to Integer (atoi) - the same "check before you multiply by 10" guard.
// ============================================================================
public class LC7ReverseInteger {
    public static void main(String[] args) {
        // Example 2 -> expected output: -321
        int x = -123;
        System.out.println(reverse(x));
    }
    public static int reverse(int x) {
        int remainder;
        int res = 0;
        while(x != 0){
            // pop the last digit (it keeps the sign of x in Java) and drop it from x
            remainder = x % 10;
            x = x / 10;
            // res * 10 + remainder would pass 2147483647
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && remainder > 7)) {
                return 0;
            }
            // res * 10 + remainder would go below -2147483648
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && remainder < -8)) {
                return 0;
            }
            // safe: append the digit
            res = res * 10 + remainder;
        }
        return res;
    }
}
