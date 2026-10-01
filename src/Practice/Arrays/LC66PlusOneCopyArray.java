package Practice.Arrays;

import java.util.Arrays;

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
//   stops there; a 9 becomes 0 and passes the carry on to the left. So at most two kinds of
//   cells change: the LAST digit that is not 9 (+1) and the run of 9s after it (they turn
//   into 0). If every digit is 9 (like 999) nothing can absorb the carry: the answer is a 1
//   followed by zeros, one digit longer. With up to 100 digits the number cannot be kept in
//   an int or long, which is why the digits are handled directly.
//
// APPROACH (as implemented below)
//   1. k = last index; move k left while digits[k] == 9. Now k is the index of the last
//      non-9 digit, or -1 if every digit is 9.
//   2. extra = (k == -1): the result needs one more slot. Then k++ : k is now the first
//      index of the trailing 9s (0 if all are 9, digits.length if there are none).
//   3. ans = Arrays.copyOf(digits, length or length + 1) - a copy, so digits stays untouched.
//   4. If extra: ans[k++] = 1 (the new leading 1). Otherwise ans[k - 1] + 1 (bump the last
//      non-9 digit).
//   5. Set every cell from k to the end of ans to 0 (the former trailing 9s, or the whole
//      tail behind the leading 1). Return ans.
//
// DRY RUN   digits = [8,9,9]
//   step 1  k=2: 9 -> k=1: 9 -> k=0: 8 stops          k = 0, extra = false
//   step 2  k++ -> 1
//   step 3  ans = [8,9,9]
//   step 4  ans[0] = 8 + 1                            [9,9,9]
//   step 5  ans[1] = 0, ans[2] = 0                    [9,0,0]   -> returns [9,0,0]
//   digits = [9,9]: k ends at -1, extra = true, ans = [9,9,0]; ans[0] = 1 and k = 1, then
//   ans[1] = 0 and ans[2] = 0 -> [1,0,0].
//   main()'s [1,9,0]: k stops at 2, ans[2] = 0 + 1 -> [1,9,1] (no trailing 9s to zero).
//
// COMPLEXITY
//   Time : O(n) - one scan from the right, one fill (each digit is touched a constant number
//          of times)
//   Space: O(n) - the returned copy; digits itself is not changed
//
// GOTCHAS
//   - All nines: the result is one slot longer ([9,9] -> [1,0,0]). copyOf pads the new slot
//     with 0, and the final loop zeroes it again anyway.
//   - The scan stops at the LAST non-9 digit; a 9 earlier in the array ([9,1,9]) is not
//     touched.
//   - k >= 0 in the last while is always true here; it is only a safety check.
//
// RELATED
//   - LC 67 Add Binary - the same right-to-left carry, on two binary strings.
//   - LC 989 Add to Array-Form of Integer - adds a whole number instead of 1 to a digit array.
//
// SEE ALSO
//   LC66PlusOneInPlace.java - turns the trailing 9s into 0 directly in the given array and
//   only allocates a new array when every digit was 9 (it changes its input)
//
// STATUS
//   Correct for every input tried. main() prints [1, 9, 1].
// ============================================================================
public class LC66PlusOneCopyArray {
    public static void main(String[] args) {
        // 190 + 1 = 191. Prints [1, 9, 1]
        int[] digits = {1,9,0};

        System.out.println(Arrays.toString(plusOne(digits)));
    }
    // Returns a new array holding the digits of (number + 1); digits is not modified.
    public static int[] plusOne(int[] digits) {
         // find the last digit that is not 9 (k == -1 if there is none)
         int k = digits.length - 1;
         while (k >= 0 && digits[k] == 9){
             k--;
         }
         // all nines -> the answer needs one extra digit
         boolean extra = k == -1;
         // k = start of the trailing 9s
         k++;
         int[] ans = Arrays.copyOf(digits, extra ? digits.length + 1 : digits.length);
         if(extra){
             // 99..9 + 1 = 100..0
             ans[k++] = 1;
         }else {
             // the carry stops at the last non-9 digit: add 1 to it
             ans[k - 1] = ans[k - 1] + 1;
         }
         // everything after it becomes 0 (9 + carry = 10 -> digit 0)
         while (k >= 0 && k < ans.length){
             ans[k++] = 0;
         }
         return ans;
    }
}
