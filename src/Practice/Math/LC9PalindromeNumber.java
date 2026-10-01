package Practice.Math;

// ============================================================================
// LC 9. Palindrome Number   [Easy]
// https://leetcode.com/problems/palindrome-number/
// Topics: Math
// ============================================================================
//
// PROBLEM
//   Return true if the integer x reads the same forwards and backwards, else false.
//   The minus sign counts as a character, so a negative number is never a palindrome.
//
// EXAMPLES
//   x = 121   ->  true
//   x = -121  ->  false     backwards it would read "121-"
//   x = 10    ->  false     backwards it reads "01"
//
// CONSTRAINTS
//   -2^31 <= x <= 2^31 - 1
//   Follow-up: solve it without converting x to a string.
//
// INTUITION
//   A number is a palindrome exactly when it equals its own digit-reversal. So build the
//   reversed number with arithmetic only (no strings) and compare it with x. Negative
//   numbers can be rejected right away.
//
// APPROACH (as implemented below)
//   1. If x is negative (Math.signum gives -1), return false.
//   2. Count the digits: divide a copy by 10 until it reaches 0.
//   3. For i = 0 .. digits - 1, take the i-th digit from the LEFT,
//        digit = (int) (x / 10^(digits - i - 1)) % 10,
//      and place it at position i from the RIGHT: sum += digit * 10^i.
//      After the loop, sum is x with its digits reversed.
//   4. Return sum == x.
//
// DRY RUN   x = 121 (3 digits)
//   i = 0: 121 / 100 -> 1,   digit 1   -> sum = 1
//   i = 1: 121 / 10  -> 12,  digit 2   -> sum = 1 + 20   = 21
//   i = 2: 121 / 1   -> 121, digit 1   -> sum = 21 + 100 = 121  == x -> true
//   main()'s x = 13425 reverses to 52431 -> false
//
// COMPLEXITY
//   Time : O(d) - d = number of digits (at most 10), two passes over them
//   Space: O(1)
//
// GOTCHAS
//   - (int) value % 10 casts first and then takes % 10 - that is what isolates one digit.
//   - Reversing a 10-digit int can overflow sum (1000000003 -> 3000000001). Here it does no
//     harm: an exhaustive check of every 10-digit int found no case where the wrapped sum
//     equals x. A variant that never overflows reverses only the second half of the digits.
//   - x = 0 is a palindrome: there are no digits, so sum stays 0 == x.
//   - Math.pow works on doubles, but 10^0 .. 10^9 are exact, so no rounding creeps in.
//
// RELATED
//   - LC 7 Reverse Integer - the same digit-reversal, with explicit overflow checks.
//   - LC 234 Palindrome Linked List - the same question for a linked list.
// ============================================================================
public class LC9PalindromeNumber {
    public static void main(String[] args) {
        // 13425 reads 52431 backwards -> expected output: false
        int x = 13425;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x) {
        int nums = x;
        int noOfDigits = 0;
        // a negative number is never a palindrome (its '-' would end up at the back)
        float sign = Math.signum(nums);

        if(sign == -1L){
            return false;
        }

        // count the digits of x
        while (nums > 0){
            nums /= 10;
            noOfDigits++;
        }

        double divedInt;
        double value;
        int digit;
        int sum = 0;
        // read digits from the LEFT, write them from the RIGHT -> sum becomes reversed x
        for (int i = 0; i < noOfDigits; i++) {
            divedInt = Math.pow(10, noOfDigits - i - 1);
            value = x / divedInt;
            // cast first, then % 10: the i-th digit from the left
            digit = (int) value % 10;

            // put it at position i from the right
            sum += digit * (int) Math.pow(10, i);
        }
        // palindrome <=> x equals its own reversal
        return sum == x;
    }
}
