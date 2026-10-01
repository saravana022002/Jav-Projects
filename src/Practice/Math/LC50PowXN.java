package Practice.Math;

// ============================================================================
// LC 50. Pow(x, n)   [Medium]
// https://leetcode.com/problems/powx-n/
// Topics: Math, Recursion
// ============================================================================
//
// PROBLEM
//   Compute x raised to the integer power n (n may be negative or zero) without using a
//   library power function.
//
// EXAMPLES
//   x = 2.0, n = 10   ->  1024.0
//   x = 2.1, n = 3    ->  9.261
//   x = 2.0, n = -2   ->  0.25      2^-2 = 1 / 2^2
//
// CONSTRAINTS
//   -100.0 < x < 100.0,   -2^31 <= n <= 2^31 - 1,   x != 0 or n > 0,
//   -10^4 <= x^n <= 10^4
//
// INTUITION
//   Multiplying x by itself |n| times is far too slow for n near 2^31. Squaring is the
//   shortcut: x^13 = x^8 * x^4 * x^1 because 13 is 1101 in binary. Keep squaring x
//   (x, x^2, x^4, x^8, ...) and multiply the result by the current square whenever that
//   bit of n is 1 - about 31 steps instead of 2 billion. A negative n means dividing by
//   those squares instead of multiplying.
//
// APPROACHES IN THIS FILE
//   A. myPowApproach() - iterative binary exponentiation   <- main() runs this one
//   B. myPow()         - recursive halving: x^n = (x^(n/2))^2, times x when n is odd
//                        (its call in main() is commented out)
//
// APPROACH (as implemented below, method A)
//   1. Copy n into a long exp, so that negating it cannot overflow (n = -2^31).
//   2. exp > 0: while exp > 0 - if its lowest bit is 1, res *= x; then x = x * x and
//      exp >>= 1.
//   3. Otherwise make exp positive and run the same loop with res /= x on the 1 bits.
//      (n = 0 skips the loop and returns 1.)
//
// DRY RUN   x = 2, n = 10 (binary 1010)
//   exp = 10, bit 0:  res = 1        x -> 4       exp -> 5
//   exp = 5,  bit 1:  res = 4        x -> 16      exp -> 2
//   exp = 2,  bit 0:  res = 4        x -> 256     exp -> 1
//   exp = 1,  bit 1:  res = 1024     x -> 65536   exp -> 0   => 1024.0
//
// COMPLEXITY
//   Time : O(log |n|) - one loop step (A) or one recursive call (B) per bit of n
//   Space: A: O(1);  B: O(log |n|) call stack
//
// GOTCHAS
//   - n = -2^31: -n does not fit in an int. A widens to long first; in B, n = -n stays
//     negative, but the -n / 2 recursion still halves it correctly, so both methods give
//     the right value (checked for x = 2 and x = -1).
//   - main()'s case 2^(-2^31) is far below the smallest double, so it prints 0.0.
//   - x = 0 with n <= 0 would divide by zero; the constraints rule it out.
//
// RELATED
//   - LC 372 Super Pow - the same fast exponentiation, with a huge exponent and mod 1337.
//   - LC 69 Sqrt(x) - another numeric routine where overflow at the extremes is the trap.
// ============================================================================
public class LC50PowXN {
    public static void main(String[] args) {
        // n = -2^31, the overflow edge case. 2^(-2^31) underflows -> expected output: 0.0
        double x = 2.00000;
        int n = -2147483648;
//        System.out.println(myPow(x, n));

          System.out.println(myPowApproach(x, n));


    }


    // Approach A: iterative binary exponentiation (square x, use it on the 1 bits of n)
    public static double myPowApproach(double x, int n) {

        // long, so that exp * (-1) is safe even for n = Integer.MIN_VALUE
        long exp = n;
        double res = 1;
        if (exp > 0) {
            while (exp > 0) {
                // lowest bit set -> this power of x is part of the answer
                if ((exp & 1) == 1) {
                    res = res * x;
                }
                // x, x^2, x^4, x^8, ... one square per bit
                x = x * x;
                exp >>= 1;
            }
        } else {
            // negative (or zero) power: same walk, but divide instead of multiply
            exp = exp * (-1);
            while (exp > 0) {
                if ((exp & 1) == 1) {
                    res = res / x;
                }
                x = x * x;
                exp >>= 1;
            }
        }
        return res;
    }
    // Approach B: recursive halving - x^n = (x^(n/2))^2, times x (or 1/x) when n is odd
    public static double myPow(double x, int n) {

        if (n == 0) {
            return 1;
        }
        if(n >= 0) {
            double result = myPow(x, n / 2);
            if (n % 2 == 0) {
                return result * result;
            } else {
                return result * result * x;
            }
        }else {
            n = -n;
            double result = myPow(x, -n / 2);
            if (n % 2 == 0) {
                return result * result;
            } else {
                return result * result * 1 / x;
            }
        }
    }
}
