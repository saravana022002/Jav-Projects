package Practice.Arrays;

// ============================================================================
// LC 66. Plus One   [Easy]
// https://leetcode.com/problems/plus-one/
// Topics: Array, Math
// ============================================================================
//
// PROBLEM
//   digits holds a non-negative integer, one decimal digit per cell, most significant digit
//   first, with no leading zeros. Add 1 to that number and return its digits as an array.
//   The result can be one digit longer (999 + 1 = 1000).
//
// EXAMPLES
//   digits = [1,2,3]    ->  [1,2,4]
//   digits = [4,3,2,1]  ->  [4,3,2,2]
//   digits = [9]        ->  [1,0]      9 + 1 = 10, so the answer gets a new leading digit
//
// CONSTRAINTS
//   1 <= digits.length <= 100,   0 <= digits[i] <= 9,   no leading zeros
//
// INTUITION
//   Add 1 by hand from the right. A digit below 9 just becomes one larger and the carry
//   stops there; a 9 becomes 0 and passes the carry on to the left. So walk left while the
//   digit is 9, turning each 9 into 0 on the way, and add 1 to the first digit that is not
//   9. If the walk falls off the left end, every digit was 9 (like 999): the answer is a 1
//   followed by zeros, one digit longer, and that needs a new, longer array. With up to 100
//   digits the number cannot be kept in an int or long, so the digits are handled directly.
//
// APPROACH (as implemented below)
//   1. i = last index, lastNumber = digits[i], allNine = false.
//   2. While lastNumber == 9 (and i >= 0): write digits[i] = 0 (9 + carry = 10, keep the 0,
//      carry on), then i--. If i fell to -1 there is nothing left to carry into: set
//      lastNumber = 0 to end the loop and allNine = true. Otherwise lastNumber = digits[i].
//   3. If allNine: digits = new int[length + 1] (all zeros) and digits[0] = 1.
//   4. Otherwise digits[i] + 1: the first non-9 digit from the right absorbs the carry.
//   5. Return digits.
//
// DRY RUN   digits = [8,9,9]
//   start   i=2, lastNumber=9
//   loop    digits[2] = 0, i=1, lastNumber=9      [8,9,0]
//   loop    digits[1] = 0, i=0, lastNumber=8      [8,0,0]   8 is not 9, the loop ends
//   step 4  digits[0] = 8 + 1                     [9,0,0]   -> returns [9,0,0]
//   digits = [9,9]: both cells become 0 and i reaches -1 -> allNine, so a new array
//   [1,0,0] is returned (the caller's array is left as [0,0]).
//   main()'s [4,3,2,1]: the last digit is 1, so the loop does not run and it becomes 2.
//
// COMPLEXITY
//   Time : O(n) - each digit is visited at most once
//   Space: O(1) extra in the normal case (the same array is returned); O(n) when every digit
//          is 9 (the new array of n + 1 cells, which is the output itself)
//
// GOTCHAS
//   - This version CHANGES the array it is given: the trailing 9s are zeroed in place, and
//     when every digit is 9 the caller's array is left all zeros while a new, longer array
//     is returned ([9,9] stays [0,0]). Use the return value, not the argument.
//   - digits = new int[length + 1] only re-points the local variable; it cannot grow the
//     caller's array. A fresh int[] is all zeros, so only the leading 1 has to be written.
//   - The loop test is lastNumber == 9 && i >= 0. When i drops to -1 the code sets
//     lastNumber = 0 so the loop stops instead of reading digits[-1].
//
// RELATED
//   - LC 67 Add Binary - the same right-to-left carry, on two binary strings.
//   - LC 989 Add to Array-Form of Integer - adds a whole number instead of 1 to a digit array.
//
// SEE ALSO
//   LC66PlusOneCopyArray.java - the copy-based version: finds the last non-9 digit first and
//   builds the answer in a copy, so the input array is not changed
//
// STATUS
//   Correct for every input tried. main() calls plusOne but does not print the result
//   (it returns [4, 3, 2, 2] for the example in main()).
// ============================================================================
public class LC66PlusOneInPlace {
    public static void main(String[] args) {
        // Example 2 - the result is not printed; it is [4, 3, 2, 2]
        int[] nums = {4,3,2,1};
        plusOne(nums);
    }
    // Returns the digits of (number + 1). Modifies its argument, see GOTCHAS.
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        int i = length - 1;
        int lastNumber = digits[length - 1];
        boolean allNine = false;
        // walk left over the trailing 9s, zeroing them (each one passes the carry on)
        while(lastNumber == 9 && i >= 0){
           digits[i] = 0;
           --i;
           if(i == -1) {
               // ran off the left end: every digit was 9
               lastNumber = 0;
               allNine = true;
           }else {
               lastNumber = digits[i];
           }
        }
        if(allNine) {
            // 99..9 + 1 = 100..0: one digit longer, the new cells are already 0
            digits = new int[length + 1];
            digits[0] = 1;
        }else {
            // the carry is absorbed by the first digit below 9
            digits[i] = digits[i] + 1;
        }
        return digits;
    }
}
