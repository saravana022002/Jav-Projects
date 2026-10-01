package Practice.Math;

// ============================================================================
// LC 2169. Count Operations to Obtain Zero   [Easy]
// https://leetcode.com/problems/count-operations-to-obtain-zero/
// Topics: Math, Simulation
// ============================================================================
//
// PROBLEM
//   Two non-negative integers num1 and num2. One operation subtracts the smaller value
//   from the larger one (on a tie, num2 is subtracted from num1). Return how many
//   operations it takes until one of the two numbers is 0 (0 if one already is).
//
// EXAMPLES
//   num1 = 2,  num2 = 3   ->  3     (2,3) -> (2,1) -> (1,1) -> (0,1)
//   num1 = 10, num2 = 10  ->  1     (10,10) -> (0,10)
//
// CONSTRAINTS
//   0 <= num1, num2 <= 10^5
//
// INTUITION
//   This is the subtraction version of Euclid's gcd algorithm. Each operation lowers
//   the larger number, so the larger value strictly shrinks and the process must end.
//   With values up to 10^5 it is cheap enough to just simulate it and count.
//
// APPROACH (as implemented below)
//   1. While both numbers are > 0:
//        num1 > num2 -> num1 = num1 - num2, otherwise num2 = num2 - num1.
//   2. Count every step and return count.
//
// DRY RUN   num1 = 2, num2 = 3   (the input main() uses)
//   (2,3): 2 > 3 no  -> num2 = 1    count 1   (2,1)
//   (2,1): 2 > 1     -> num1 = 1    count 2   (1,1)
//   (1,1): tie       -> num2 = 0    count 3   (1,0)  stop
//   return 3
//
// COMPLEXITY
//   Time : O(max(num1, num2)) - the larger value drops by at least 1 per step; the worst
//          case is (10^5, 1), which takes 10^5 steps
//   Space: O(1)
//
// GOTCHAS
//   - On a tie this code zeroes num2 (else branch) while the statement zeroes num1.
//     Either way one number becomes 0 in that step, so the count is the same.
//   - If either input is 0 the loop never runs and the answer is 0.
//   - Faster variant: count += big / small; big %= small (Euclid with %), O(log).
//
// RELATED
//   - LC 1979 Find Greatest Common Divisor of Array - Euclid with % (in this repo).
//   - LC 1342 Number of Steps to Reduce a Number to Zero - another count-the-steps
//     simulation.
// ============================================================================
public class LC2169CountOperationsToObtainZero {
    public static void main(String[] args) {
        // Example 1 - expected output: 3
        int num1 = 2;
        int num2 = 3;
        System.out.println(countOperations(num1, num2));
    }

    // returns the number of subtract-the-smaller steps until one number is 0
    public static int countOperations(int num1, int num2) {
        int count = 0;
        while(num1 > 0 && num2 > 0){
            // take the smaller from the larger (tie goes to else: num2 becomes 0)
            if(num1 > num2){
                num1 = num1 - num2;
            }else {
                num2 = num2 - num1;
            }
            count++;
        }
        return count;
    }
}
