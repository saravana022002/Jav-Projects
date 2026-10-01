package Practice.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ============================================================================
// LC 2138. Divide a String Into Groups of Size k   [Easy]
// https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/
// Topics: String, Simulation
// ============================================================================
//
// PROBLEM
//   Cut the string s, from left to right, into consecutive groups of k characters each.
//   If the final group would have fewer than k characters, complete it with the given
//   fill character. Return the groups as an array of strings, in order.
//
// EXAMPLES
//   s = "abcdefghi",  k = 3, fill = 'x'  ->  ["abc","def","ghi"]        9 letters split
//                                            exactly, nothing to pad
//   s = "abcdefghij", k = 3, fill = 'x'  ->  ["abc","def","ghi","jxx"]  only 'j' is left
//                                            for the last group, so two fills are added
//
// CONSTRAINTS
//   1 <= s.length <= 100,   s has lowercase English letters only,   1 <= k <= 100,
//   fill is a lowercase English letter
//
// INTUITION
//   Nothing clever is needed: step through s in jumps of k and take a k-letter slice each
//   time. Only the last slice can be short (every earlier one is full), so the padding is
//   applied once, at the end, to that last group.
//
// APPROACH (as implemented below)
//   1. n = s.length(); str = list of groups; curr = 0 = start of the next group.
//   2. While curr < n: end = min(curr + k, n) so the slice never runs past the string;
//      add s.substring(curr, end); curr += k.
//   3. last = the final group. If last.length() < k, append the fill character
//      (k - last.length()) times and store the padded group back with str.set(...).
//   4. Convert the list to a String[] and return it.
//
// DRY RUN   s = "abcdefghik", k = 3, fill = 'x'   (the input main() uses, n = 10)
//   curr=0  end=3            -> "abc"
//   curr=3  end=6            -> "def"
//   curr=6  end=9            -> "ghi"
//   curr=9  end=min(12,10)=10 -> "k"       curr=12 -> loop ends
//   last = "k", length 1 < 3 -> append "x" repeated 3 - 1 = 2 times -> "kxx"
//   result [abc, def, ghi, kxx]
//
// COMPLEXITY
//   Time : O(n + k) - every letter is copied once by substring, and the padding adds at
//          most k - 1 characters
//   Space: O(n + k) - the list of groups and the result array together hold ceil(n / k) * k
//          characters
//
// GOTCHAS
//   - A string whose length is a multiple of k gets no padding (Example 1), because the last
//     group already has k letters.
//   - Math.min(curr + k, n) is what keeps the final substring call inside the string.
//   - String.repeat needs Java 11 or newer; String.valueOf(fill) turns the char into a
//     String so repeat can be called.
//   - s has at least one character, so the list is never empty when str.get(...) is called.
//
// STATUS
//   The method is correct; main() prints [abc, def, ghi, kxx].
//
// RELATED
//   - LC 68 Text Justification - also cuts text into fixed-size pieces and pads them, with
//     far more rules about how the padding is spread out.
// ============================================================================
public class LC2138DivideAStringIntoGroupsOfSizeK {
    public static void main(String[] args) {
        // Three full groups and a last group "k" that is padded with 'x'.
        // Expected output: [abc, def, ghi, kxx]
        String s = "abcdefghik";
        int k = 3;
        char fill = 'x';
        System.out.println(Arrays.toString(divideString(s, k, fill)));
    }
    // Returns the groups of k letters; the last group is padded with fill if it is short
    public static String[] divideString(String s, int k, char fill) {
        int n = s.length();
        List<String> str = new ArrayList<>();
        int curr = 0;
        // cut s into consecutive slices of k letters (the last slice may be shorter)
        while (curr < n){
            // never slice past the end of the string
            int end = Math.min(curr + k, n);
            str.add(s.substring(curr, end));
            curr = curr + k;
        }
        // only the last group can be short: pad it with fill up to k letters
        String last = str.get(str.size() - 1);
        if(last.length() < k){
            last += String.valueOf(fill).repeat(k - last.length());
            str.set(str.size() - 1, last);
        }
        return str.toArray(new String[0]);
    }
}
