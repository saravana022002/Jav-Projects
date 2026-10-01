package Practice.HashMap;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 3442. Maximum Difference Between Even and Odd Frequency I   [Easy]
// https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/
// Topics: Hash Table, String, Counting
// ============================================================================
//
// PROBLEM
//   s is a string of lowercase letters. Choose one letter a1 that occurs an ODD number of
//   times in s and another letter a2 that occurs an EVEN number of times, and look at
//   freq(a1) - freq(a2). Return the largest value this difference can take.
//
// EXAMPLES
//   s = "aaaaabbc"   ->  3    a occurs 5 times (odd), b twice (even): 5 - 2
//   s = "abcabcab"   ->  1    a and b occur 3 times, c twice: best is 3 - 2
//
// CONSTRAINTS
//   3 <= s.length <= 100,   s has only lowercase letters,
//   and s has at least one letter with an odd count and one with an even count
//
// INTUITION
//   a1 and a2 are chosen independently (one from the odd letters, one from the even ones), so
//   the difference is biggest when the first term is as big as possible and the second as
//   small as possible: the LARGEST odd frequency minus the SMALLEST even frequency.
//
// APPROACH (as implemented below)
//   1. Count each letter in freqMap (getOrDefault(..., 0) + 1).
//   2. oddMax = Integer.MIN_VALUE, evenMin = Integer.MAX_VALUE (sentinels).
//   3. For every entry: an odd count (% 2 == 1) raises oddMax with Math.max, an even count
//      lowers evenMin with Math.min.
//   4. Return oddMax - evenMin.
//
// DRY RUN   s = "abcabcab"   (what main() runs)
//   freqMap = {a=3, b=3, c=2}
//   a=3 is odd  -> oddMax = 3
//   b=3 is odd  -> oddMax = 3
//   c=2 is even -> evenMin = 2
//   return 3 - 2 = 1
//
// COMPLEXITY
//   Time : O(n) - one pass to count, then a scan over at most 26 map entries
//   Space: O(1) - the map never holds more than 26 letters
//
// GOTCHAS
//   - Take the SMALLEST even frequency: it is the one being subtracted.
//   - If s had no odd (or no even) frequency, a sentinel would survive and the result would be
//     meaningless (and could overflow); the constraints promise both exist.
//   - Letters that do not occur are not in the map, so a count of 0 is never picked as the
//     "even" letter.
//
// STATUS
//   Correct (checked against brute force on random strings).
//
// RELATED
//   - LC 3445 Maximum Difference Between Even and Odd Frequency II - the Hard follow-up with
//     the same odd / even frequency idea.
//   - LC 1394 Find Lucky Integer in an Array - also counts frequencies in a map and picks
//     the best one by a rule (solved in LC1394FindLuckyIntegerInAnArray.java).
// ============================================================================
public class LC3442MaximumDifferenceBetweenEvenAndOddFrequencyI {
    public static void main(String[] args) {
        // Example 2. Expected output: 1
        String s = "abcabcab";
        System.out.println(maxDifference(s));
    }
    // Largest odd letter count minus smallest even letter count.
    public static int maxDifference(String s) {
        // letter -> how many times it occurs in s
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i) , 0) + 1);
        }
        // sentinels: any real count replaces them on the first comparison
        int oddMax = Integer.MIN_VALUE;
        int evenMin = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> freq : freqMap.entrySet()){
            if(freq.getValue() % 2 == 1) {
                // odd count: keep the largest
                oddMax = Math.max(oddMax, freq.getValue());
            }else {
                // even count: keep the smallest
                evenMin = Math.min(evenMin, freq.getValue());
            }
        }
        return oddMax - evenMin;
    }
}
