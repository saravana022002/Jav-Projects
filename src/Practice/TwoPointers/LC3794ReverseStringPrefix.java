package Practice.TwoPointers;

// ============================================================================
// LC 3794. Reverse String Prefix   [Easy]
// https://leetcode.com/problems/reverse-string-prefix/
// Topics: Two Pointers, String
// ============================================================================
//
// PROBLEM
//   Given a string s and an integer k, reverse the first k characters of s and leave the rest
//   as it is. Return the resulting string.
//
// EXAMPLES
//   s = "abcd", k = 2  ->  "bacd"   "ab" becomes "ba", "cd" stays
//   s = "xyz",  k = 3  ->  "zyx"    the whole string is the prefix
//   s = "hey",  k = 1  ->  "hey"    a one-letter prefix reads the same reversed
//
// CONSTRAINTS
//   1 <= s.length <= 100,   s has lowercase English letters only,   1 <= k <= s.length
//
// INTUITION
//   Reversing a stretch of an array is the classic two-pointer swap: one pointer at the first
//   position of the stretch, one at the last, swap the two characters and walk both inwards
//   until they meet. Here the stretch is positions 0 .. k-1. Java strings cannot be edited, so
//   the work is done on a char array and the result is turned back into a String.
//
// APPROACH (as implemented below)
//   1. chars = s.toCharArray(); l = 0, r = k - 1.
//   2. While l <= r: swap chars[l] and chars[r] through the variable ch, then l++ and r--.
//   3. Append all of chars to a StringBuilder and return its String.
//
// DRY RUN   s = "xyz", k = 3   (the input main() uses)
//   chars = [x,y,z]   l=0 r=2
//   l=0 r=2  swap x and z -> [z,y,x]
//   l=1 r=1  swap y with itself -> [z,y,x]
//   l=2 r=0  l > r -> stop
//   result "zyx".     For s = "abcd", k = 2: l=0 r=1 swap -> [b,a,c,d]; l=1 r=0 stop -> "bacd".
//
// COMPLEXITY
//   Time : O(n) - copy the string, at most k/2 swaps, rebuild the string
//   Space: O(n) - the char array and the StringBuilder
//
// GOTCHAS
//   - The loop condition l <= r also runs when the pointers meet on the middle character of an
//     odd-length prefix; that swap is with itself and changes nothing (l < r would do as well).
//   - k = 1 gives l = r = 0, a self-swap, so the string is returned unchanged.
//   - Characters after position k - 1 are never touched.
//
// STATUS
//   The method is correct (checked against a StringBuilder.reverse() reference on random
//   inputs). main() prints zyx.
//
// RELATED
//   - LC 344 Reverse String - the same swap loop over the whole array.
//   - LC 2000 Reverse Prefix of Word - reverses the prefix that ends at the first occurrence
//     of a given character, instead of a given length k.
//   - LC 541 Reverse String II - reverses the first k characters of every 2k block.
// ============================================================================
public class LC3794ReverseStringPrefix {
    public static void main(String[] args) {
        // Example 2: the whole string is reversed. Expected output: zyx
        String s = "xyz";
        int k = 3;
        System.out.println(reversePrefix(s, k));
    }
    // Reverses the first k characters of s and returns the new string
    public static String reversePrefix(String s, int k) {
        char[] chars = s.toCharArray();
        // l and r walk towards each other over positions 0 .. k-1
        int l = 0;
        int r = k - 1;
        char ch;
        while (l <= r){
            // swap the two ends of the prefix
            ch = chars[l];
            chars[l] = chars[r];
            chars[r] = ch;
            l++;
            r--;
        }
        // turn the char array back into a String
        StringBuilder string = new StringBuilder();
        for (char aChar : chars) {
            string.append(aChar);
        }
        return string.toString();
    }
}
