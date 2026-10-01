package Practice.Strings;

import java.util.Arrays;

// ============================================================================
// LC 2264. Largest 3-Same-Digit Number in String   [Easy]
// https://leetcode.com/problems/largest-3-same-digit-number-in-string/
// Topics: String
// ============================================================================
//
// PROBLEM
//   num is a string of digits. Call a substring "good" when it is exactly 3 characters long
//   and all three characters are the same digit (for example "777" or "000"; leading zeros
//   are allowed). Return the largest good substring, compared as a number, or "" when the
//   string has none.
//
// EXAMPLES
//   num = "6777133339"  ->  "777"   good substrings are "777" and "333"; 777 is larger
//   num = "2300019"     ->  "000"   "000" is the only good one, and zeros are allowed
//   num = "42352338"    ->  ""      no digit appears three times in a row
//
// CONSTRAINTS
//   3 <= num.length <= 1000,   num has digits only
//
// INTUITION
//   A good substring is fully described by its digit, and "ddd" is larger than "eee"
//   exactly when d > e. So the task is to find the largest digit that occurs three times in
//   a row. One left-to-right pass is enough; a digit that occurs four or more times in a row
//   still only counts as one candidate. (Another way, not used here: test whether num
//   contains "999", then "888", ... down to "000" and return the first hit.)
//
// APPROACHES IN THIS FILE
//   A. largestGoodInteger()   - run-length counter: count the current run of equal digits and
//                               record the digit when the count reaches 3 - O(n)
//   B. largestGoodIntegerOp() - slide a window of 3 characters and keep the largest digit of
//                               a window like "ddd" - O(n)   <- main() runs this one
//   (Both keep ' ' as "nothing found yet": every digit character is greater than a space.)
//
// APPROACH (as implemented below, method B)
//   1. maxChar = ' ' (nothing found yet).
//   2. For i from 0 to num.length() - 3, so that i + 2 is still a valid index:
//      tuco = num.charAt(i), the first character of the window.
//   3. If num.charAt(i + 1) and num.charAt(i + 2) both equal tuco, the window is a good
//      integer: if maxChar < tuco, maxChar = tuco.
//   4. If maxChar is still ' ' return "", otherwise return the digit three times
//      (String.format("%c%c%c", ...)).
//
// DRY RUN   B on num = "6777133339"   (n = 10, i runs from 0 to 7)
//   i=0  "677"  not all equal
//   i=1  "777"  all equal -> maxChar = '7'
//   i=2  "771"  no          i=3  "713"  no          i=4  "133"  no
//   i=5  "333"  all equal, but '7' < '3' is false -> maxChar stays '7'
//   i=6  "333"  the same run of four 3s seen again -> no change
//   i=7  "339"  no
//   return "777"
//   main()'s input "42352338" has no three equal neighbours, so maxChar stays ' ' and the
//   method returns "" (main prints an empty line).
//
// COMPLEXITY
//   Time : O(n) for both - A makes one pass with O(1) work per character; B looks at
//          n - 2 windows of constant size
//   Space: O(1) - a few variables and the 3-character answer
//
// GOTCHAS
//   - "000" is a legitimate good integer and it beats "": the sentinel ' ' (code 32) is below
//     every digit character (code 48 and up), so even '0' replaces it.
//   - In A, a run of 4 or more equal digits sets max when the count hits exactly 3 and is
//     ignored afterwards; in B the same run shows up in two overlapping windows.
//   - In A, max is a boxed Character; prev > max and max != ' ' unbox it, so they compare
//     plain char values.
//
// STATUS
//   Both methods are correct (checked against a brute force on random digit strings).
//   main() runs B on Example 3's input, so it prints an empty line, which is the right answer.
//   The import java.util.Arrays is not used.
//
// RELATED
//   - LC 1903 Largest Odd Number in String - also asks for the largest substring of a digit
//     string, but the rule to satisfy is "ends in an odd digit".
//   - LC 1446 Consecutive Characters - uses the same run-length counter of equal neighbours
//     as method A.
// ============================================================================
public class LC2264Largest3SameDigitNumberInString {
    public static void main(String[] args) {
        // No digit appears three times in a row, so the answer is "" and an empty line is printed
        String num = "42352338";
        System.out.println(largestGoodIntegerOp(num));
    }
    // Approach A: run-length counter - count the current run of equal digits and remember the
    // digit whenever the count reaches 3. O(n)
    public static String largestGoodInteger(String num) {
        // length of the current run of equal digits
        int count = 1;
        char prev = num.charAt(0);
        // largest digit that formed a run of 3; ' ' (below any digit) means none yet
        Character max = ' ';
        for (int i = 1; i < num.length(); i++) {
            if(num.charAt(i) == prev){
                prev = num.charAt(i);
                count ++;
                // the run just reached length 3 (longer runs leave max as it is)
                if(count == 3 && prev > max){
                    max = prev;
                }
            }else {
                // a different digit starts a new run
                prev = num.charAt(i);
                count = 1;
            }
        }
        // nothing found -> "", otherwise the digit repeated three times
        if(max != ' ') {
            return String.valueOf(max) + max + max;
        }else {
            return "";
        }
    }

    // Approach B: slide a window of 3 characters and keep the largest digit whose window is
    // "ddd". O(n)
    public static String largestGoodIntegerOp(String num) {
        // ' ' = nothing found yet (every digit character compares larger)
        char maxChar = ' ';
        char tuco;
        // i + 2 must stay inside the string
        for (int i = 0; i < num.length() - 2; i++) {
            // tuco = first character of the window num[i .. i+2]
            tuco = num.charAt(i);
            // all three characters equal -> a good integer; keep the larger digit
            if(tuco == num.charAt(i + 1) && tuco == num.charAt(i + 2)){
                if(maxChar <  tuco){
                    maxChar = tuco;
                }
            }
        }
        // "" if nothing was found, otherwise the digit three times
        return maxChar == ' ' ?  "" : String.format("%c%c%c", maxChar, maxChar, maxChar);
    }
}
