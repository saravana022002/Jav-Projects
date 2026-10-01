package Practice.Math;

// ============================================================================
// LC 1513. Number of Substrings With Only 1s   [Medium]
// https://leetcode.com/problems/number-of-substrings-with-only-1s/
// Topics: Math, String
// ============================================================================
//
// PROBLEM
//   s is a string of '0's and '1's. Count the substrings (contiguous pieces) made up
//   of '1's only. The same text at different positions counts separately. The count
//   can be huge, so return it modulo 10^9 + 7.
//
// EXAMPLES
//   s = "0110111"  ->  9     runs "11" and "111": 3 + 6 substrings
//   s = "101"      ->  2     two separate "1"s
//   s = "111111"   ->  21    one run of 6: 6 + 5 + 4 + 3 + 2 + 1
//
// CONSTRAINTS
//   1 <= s.length <= 10^5,   s[i] is either '0' or '1'
//
// INTUITION
//   An all-1 substring can never cross a '0', so split s into maximal runs of 1s and
//   count each run on its own. A run of k ones holds k substrings of length 1, k - 1 of
//   length 2, ..., 1 of length k: k + (k-1) + ... + 1 = k * (k + 1) / 2.
//
// APPROACH (as implemented below)
//   1. Scan with i. When chars[i] == '1', a run starts: start = i.
//   2. Move i forward while chars[i] is not '0'; i stops on the '0' that ends the run
//      (or at length). end = i, so the run length is end - start.
//   3. ans += len * (len + 1) / 2, then ans %= 10^9 + 7.
//   4. The for loop's i++ then steps over that terminating '0'.
//
// DRY RUN   s = "10011"   (the input main() uses)
//   i=0 '1': start=0, inner loop stops at i=1 ('0'), end=1, len=1 -> +1   ans = 1
//   i=2 '0': skipped
//   i=3 '1': start=3, inner loop stops at i=5 (end of s), len=2 -> +3    ans = 4
//   return 4
//
// COMPLEXITY
//   Time : O(n) - every character is visited once, by the for loop or the inner while
//   Space: O(n) - the toCharArray copy (everything else is a few ints)
//
// STATUS
//   Known issue: (end - start) * (end - start + 1) is multiplied in int. For a run of
//   46341 or more 1s the product passes 2^31 - 1 and overflows: 100000 ones return
//   705082704, correct answer 49965 (46341 ones return -73716330 instead of 73767304).
//   Doing that multiplication in long would fix it. The code is left unchanged on purpose.
//
// GOTCHAS
//   - Take the modulo as you go; one long run alone already exceeds 10^9 + 7.
//   - The inner while moves the loop variable i, so the run is not re-scanned.
//
// RELATED
//   - LC 1759 Count Number of Homogenous Substrings - the same k * (k + 1) / 2 per run,
//     but for runs of any repeated letter.
// ============================================================================
public class LC1513NumberOfSubstringsWithOnly1s {
    public static void main(String[] args) {
        // runs "1" and "11" -> 1 + 3. Expected output: 4
        String s = "10011";
        System.out.println(numSub(s));
    }
    // returns the number of all-'1' substrings of s, modulo 10^9 + 7
    public static int numSub(String s) {
        char[] chars = s.toCharArray();
        int start = -1;
        int end = -1;
        int ans = 0;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if(chars[i] == '1') {
                // a run of 1s begins here: walk i to the first '0' after it (or the end)
                start = i;
                while ( i < length && chars[i] != '0') {
                    i++;
                }
                end = i;
                // run of k = end - start ones -> k * (k + 1) / 2 substrings
                // (int multiply: overflows once k >= 46341 - see STATUS)
                ans += ((end - start) * (end - start + 1)) / 2;
                ans %= (int) (Math.pow(10, 9) + 7);
            }
        }
        return ans;
    }

}
