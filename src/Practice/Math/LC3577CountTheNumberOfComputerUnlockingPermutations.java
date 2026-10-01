package Practice.Math;

// ============================================================================
// LC 3577. Count the Number of Computer Unlocking Permutations   [Medium]
// https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations/
// Topics: Array, Math, Brainteaser, Combinatorics
// ============================================================================
//
// PROBLEM
//   Computers 0..n-1 each have a password complexity complexity[i]. Computer 0 starts
//   unlocked. Computer i can be unlocked only through an ALREADY unlocked computer j with
//   a smaller label and a strictly smaller complexity (j < i and complexity[j] <
//   complexity[i]). Count the orders (permutations of 0..n-1, starting from computer 0)
//   in which every computer can be unlocked one after another. Answer modulo 10^9 + 7.
//
// EXAMPLES
//   complexity = [1,2,3]          ->  2     orders [0,1,2] and [0,2,1]
//   complexity = [3,3,3,4,4,4]    ->  0     computer 1 ties with computer 0, and nobody
//                                           has a smaller complexity, so it stays locked
//
// CONSTRAINTS
//   2 <= n <= 10^5,   1 <= complexity[i] <= 10^9
//
// INTUITION
//   Look at the cheapest computer other than 0. If its complexity is <= complexity[0],
//   nothing in the room is strictly cheaper than it, so it can never be unlocked -> 0.
//   Otherwise computer 0 is strictly the cheapest of all, so computer 0 alone can unlock
//   ANY other computer at ANY time. Then every ordering of computers 1..n-1 after 0 is
//   valid: (n-1)! orders.
//
// APPROACH (as implemented below)
//   1. initial = complexity[0]. If any complexity[i] (i >= 1) is <= initial, return 0.
//   2. Otherwise return factorial(n - 1), computed recursively modulo 10^9 + 7.
//
// DRY RUN   complexity = [3,5,7,9]   (the input main() uses)
//   5, 7 and 9 are all > 3  -> computer 0 can open every other computer
//   factorial(3) = 3 * 2 * 1 = 6   -> prints 6
//
// COMPLEXITY
//   Time : O(n) - one scan plus n-1 multiplications
//   Space: O(n) - factorial() recurses n-1 levels deep
//
// GOTCHAS
//   - "Strictly smaller" matters: a tie with computer 0 also blocks everything (ex. 2).
//   - (n-1)! is huge, so every multiplication must be reduced mod 10^9 + 7 - in a type
//     wide enough (long) that the product itself cannot overflow first.
//
// STATUS
//   Known issue 1: factorial() multiplies two ints before taking %, so the product
//   overflows once it passes 12!: 14 computers with increasing complexity return
//   932053497 instead of 227020758 (= 13! mod 10^9 + 7).
//   Known issue 2: the recursion is n-1 levels deep; with n = 10^5 it throws
//   StackOverflowError on Java's default stack.
//   Both come from factorial(); an iterative loop with long arithmetic avoids them.
//   The code is left unchanged on purpose.
// ============================================================================
public class LC3577CountTheNumberOfComputerUnlockingPermutations {
    public static void main(String[] args) {
        // computer 0 is strictly the cheapest -> 3! = 6 orders. Expected output: 6
        int[] complexity = {3, 5, 7, 9};
        System.out.println(countPermutations(complexity));
    }


    public static int countPermutations(int[] complexity) {
        int initial = complexity[0];
        int count = 0;
        int length = complexity.length;
        // any computer not strictly harder than computer 0 can never be unlocked
        for (int i = 1; i < length; i++) {
            if(initial >= complexity[i]){
                return 0;
            }
        }
        // computer 0 opens everything, so computers 1..n-1 may come in any order
        count = factorial(length - 1);
        return count;
    }

    // n! mod 10^9 + 7 by recursion (int product overflows past 12! - see STATUS)
    public static int factorial(int n){
        if (n == 0 || n == 1) return 1;
        return (n * factorial(n - 1)) % 1_000_000_007;
    }

}
