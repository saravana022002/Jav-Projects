package Practice.BitManipulation;

// ============================================================================
// LC 3370. Smallest Number With All Set Bits   [Easy]
// https://leetcode.com/problems/smallest-number-with-all-set-bits/
// Topics: Math, Bit Manipulation
// ============================================================================
//
// PROBLEM
//   Given a positive integer n, return the smallest x with x >= n whose binary form
//   is made of 1s only (1, 3, 7, 15, 31, ...). n itself counts if it already qualifies.
//
// EXAMPLES
//   n = 5   ->  7      5 = 101,  the next all-ones number is 111 = 7
//   n = 10  ->  15     10 = 1010 -> 1111 = 15
//   n = 3   ->  3      3 = 11 is already all ones
//
// CONSTRAINTS
//   1 <= n <= 1000
//
// INTUITION
//   All-ones numbers are 2^k - 1. If n has k binary digits then:
//     - the k-digit all-ones number 2^k - 1 is the largest k-digit value, so it is >= n;
//     - the next smaller candidate 2^(k-1) - 1 has only k-1 digits, so it is < n.
//   So the answer is simply "k ones", where k is the bit length of n.
//
// APPROACH (as implemented below)
//   1. Bit length: halve n until it is 0; count = number of halvings = number of digits.
//   2. Build count ones: repeat ans = ans * 2 + 1 (shift left, append a 1) count times.
//   3. Return ans.
//
// DRY RUN   n = 10   (Example 2; main() runs n = 3)
//   step 1   10 -> 5 -> 2 -> 1 -> 0                  count = 4
//   step 2   ans = 1 (1), 3 (11), 7 (111), 15 (1111)
//   return 15
//
// COMPLEXITY
//   Time : O(log n) - each loop runs once per binary digit of n
//   Space: O(1)
//
// GOTCHAS
//   - The answer is >= n, not > n: 3, 7, 15 ... return themselves (Example 3).
//   - Same result in one line (the official hint): the next power of 2 above n, minus 1,
//     e.g. Integer.highestOneBit(n) * 2 - 1. Not what this code does, just a check.
//   - The method halves its own parameter n; fine, Java passes ints by value.
// ============================================================================
public class LC3370SmallestNumberWithAllSetBits {
    public static void main(String[] args) {
        // Example 3 - 3 is already binary 11. Expected output: 3
        int n = 3;
        System.out.println(smallestNumber(n));
    }
    // Returns the all-ones number with as many binary digits as n, i.e. 2^(bit length) - 1.
    public static int smallestNumber(int n) {
        // step 1: count the binary digits of n
        int count = 0;
        while (n > 0){
            count ++;
            n = n / 2;
        }
        // step 2: shift left and append a 1, once per digit -> count ones
        int ans = 0;
        while (count > 0){
            ans = ans * 2 + 1;
            count--;
        }
        return ans;
    }
}
