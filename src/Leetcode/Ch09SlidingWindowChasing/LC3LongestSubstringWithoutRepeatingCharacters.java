package Leetcode.Ch09SlidingWindowChasing;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 3. Longest Substring Without Repeating Characters   [Medium]
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #4
 *       also: ch. 1 Complexity, amortisation, and counting (#5),
 *             ch. 4 Frequency and multiset reasoning (#10)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Given a string s, return the length of the longest substring (a contiguous piece, not a
 *   subsequence) in which no character occurs twice.
 *
 * EXAMPLES
 *   s = "abcabcbb"  ->  3    "abc" (also "bca" and "cab")
 *   s = "bbbbb"     ->  1    "b"
 *   s = "pwwkew"    ->  3    "wke"; "pwke" does not count, it is a subsequence
 *
 * CONSTRAINTS
 *   0 <= s.length <= 10^5,   s consists of English letters, digits, symbols and spaces
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersQueueWindow.java
 *   src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches.java
 *   src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC3LongestSubstringWithoutRepeatingCharacters.java
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC3LongestSubstringWithoutRepeatingCharacters.java
 * </pre>
 */
public class LC3LongestSubstringWithoutRepeatingCharacters {

    /**
     * Variant A - brute force: from every start, extend until a character repeats
     * <br>Target: time O(n^2), space O(min(n, a))    (a = alphabet size)
     */
    public static int lengthOfLongestSubstringBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: grow the window, shrink it from the left on a repeat
     * <br>Target: time O(n), space O(min(n, a))
     */
    public static int lengthOfLongestSubstring(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - same window, but jump the left edge using each character's last position
     * <br>Target: time O(n), space O(min(n, a))
     */
    public static int lengthOfLongestSubstringLastSeen(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC3LongestSubstringWithoutRepeatingCharacters::lengthOfLongestSubstringBruteForce);
        runAll("B", LC3LongestSubstringWithoutRepeatingCharacters::lengthOfLongestSubstring);
        runAll("C", LC3LongestSubstringWithoutRepeatingCharacters::lengthOfLongestSubstringLastSeen);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    //   (LC 3. Longest Substring Without Repeating Characters)
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve("abcabcbb"));
        check(v + " example 2", 1, () -> s.solve("bbbbb"));
        check(v + " example 3", 3, () -> s.solve("pwwkew"));
        // edge: the empty string is allowed
        check(v + " empty string", 0, () -> s.solve(""));
        // edge: the repeated letter lies before the current window start
        check(v + " abba", 2, () -> s.solve("abba"));
        // edge: after a repeat only the part up to the old copy is dropped
        check(v + " dvdf", 3, () -> s.solve("dvdf"));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}
