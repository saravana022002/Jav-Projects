package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 387. First Unique Character in a String   [Easy]
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * Topics: Hash Table, String, Queue, Counting
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #4
 *
 * PROBLEM
 *   Given a string s, find the first character that occurs exactly once in the whole string
 *   and return its index (counting from 0). If every character repeats, return -1.
 *
 * EXAMPLES
 *   s = "leetcode"      ->  0    'l' is the first character that appears nowhere else
 *   s = "loveleetcode"  ->  2    'l' and 'o' come back later; 'v' at index 2 never does
 *   s = "aabb"          ->  -1   every character repeats
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s consists of only lowercase English letters
 *
 * INTUITION
 *   Whether a character is unique cannot be seen from the character alone - it depends on
 *   its total over the WHOLE string. So count first and decide second: a letter is unique
 *   exactly when its total is 1, and the answer is the unique letter that sits earliest in
 *   the string. Both methods below follow this order; they differ in what they remember.
 *
 * APPROACHES IN THIS FILE
 *   A. firstUniqChar()      - HashMap letter -> index of its first occurrence, overwritten with
 *                             -1 once the letter shows up again; the answer is the smallest
 *                             index still >= 0 - O(n) time, O(1) space     <- main() runs this one
 *   B. firstUniqueCharOpt() - int[26] letter counts, then a second pass over the STRING in
 *                             order: the first position whose letter has count 1
 *                             - O(n) time, O(1) space
 *
 * APPROACH (as implemented below, method A)
 *   1. map: letter -> index of its first occurrence.
 *   2. Pass over chars: a letter not in the map yet is stored with map.put(letter, i); a
 *      letter that is already there gets map.put(letter, -1), meaning "repeated". A third or
 *      fourth occurrence just writes -1 again.
 *   3. min starts at Integer.MAX_VALUE; for every map entry whose value is > -1,
 *      min = Math.min(min, value).
 *   4. If min is still Integer.MAX_VALUE no letter is unique -> return -1, else return min.
 *
 * DRY RUN   s = "leetcode"   (what main() runs)
 *   i=0 'l' new        l:0                 i=4 'c' new        c:4
 *   i=1 'e' new        e:1                 i=5 'o' new        o:5
 *   i=2 'e' again      e:-1                i=6 'd' new        d:6
 *   i=3 't' new        t:3                 i=7 'e' again      e:-1 (stays -1)
 *   map = {l:0, e:-1, t:3, c:4, o:5, d:6}; the entries > -1 hold 0, 3, 4, 5, 6 -> min = 0
 *   return 0
 *
 * COMPLEXITY
 *   A. Time : O(n) - one pass over the string, then one pass over at most 26 map entries
 *      Space: O(1) - at most 26 letters in the map (O(k) for k distinct characters in
 *             general); toCharArray() copies the string, O(n)
 *   B. Time : O(n) - one counting pass and one scan.   Space: O(1) - 26 counters
 *
 * GOTCHAS
 *   - -1 does double duty: inside the map it means "repeated", as a return value it means
 *     "no unique character".
 *   - In A the smallest index is taken over the whole map, so the HashMap's iteration order
 *     is irrelevant.
 *   - In B the second scan must walk the STRING (positions), not the 26 counters: scanning
 *     the counters would find the alphabetically first unique letter, not the leftmost one.
 *   - B indexes with letter - 'a', so it only accepts 'a'..'z' (A accepts any characters).
 *
 * RELATED
 *   - LC 451 Sort Characters By Frequency - also counts every character first; solved in
 *     LC451SortCharactersByFrequency.java in this folder.
 *   - LC 2351 First Letter to Appear Twice - another "first position that satisfies a
 *     counting rule" question on a string, here the first letter to repeat.
 * </pre>
 */
public class LC387FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        // Example 1 - prints 0: 'l' is the first letter that never repeats
        String s = "leetcode";
        System.out.println(firstUniqChar(s));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: HashMap letter -> first index, or -1 once the letter has repeated. O(n)
    public static int firstUniqChar(String s) {
        // letter -> index of its first occurrence, or -1 after it has appeared again
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        // pass 1: remember first positions and mark every repeat
        for (int i = 0; i < chars.length; i++){
            // first sighting: store the index; any later sighting: -1 ("not unique")
            if(!map.containsKey(chars[i])) {
                map.put(chars[i], i);
            }else {
                map.put(chars[i], -1);
            }
        }

        // pass 2: among the letters that never repeated (index > -1) take the smallest index
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Character, Integer> ans : map.entrySet()){
            if(ans.getValue() > -1) {
                min = Math.min(min, ans.getValue());
            }
        }
        // min never moved -> every letter repeats
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // Approach B: count the letters, then walk the string and stop at the first letter whose
    // total is 1. O(n) time, O(1) space. Not called by main().
    public static int firstUniqueCharOpt(String s) {
        // ans[c - 'a'] = how many times letter c occurs in s
        int[] ans = new int[26];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            ans[aChar - 'a']++;
        }
        // scan in string order, so the first hit is the leftmost unique letter
        for(int i = 0; i < chars.length; i++){
            if(ans[chars[i] - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 387. First Unique Character in a String)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (one character, a letter repeated three times,
    // the unique letter first or last, leftmost unique vs alphabetically first, n = 10^5). Every
    // input is run through BOTH methods of the solution, firstUniqChar and firstUniqueCharOpt.
    // Expected values come from the statement or from a count-every-letter brute force.
    //   Expected result: 18/18 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: leetcode", 0, "leetcode");
        both("example 2: loveleetcode", 2, "loveleetcode");
        both("example 3: aabb", -1, "aabb");
        // edge: a single character is unique
        both("single character", 0, "z");
        // edge: one letter, odd number of repeats (a third sighting must stay "repeated")
        both("same letter three times", -1, "aaa");
        // edge: a long run of one letter, then the only unique letter at the very end
        both("unique letter last", 3, "aaab");
        // edge: the unique letter is the first character
        both("unique letter first", 0, "abb");
        // edge: two unique letters; the answer is the leftmost (c), not the alphabetical first (a)
        both("leftmost, not alphabetically first", 2, "ddcab");
        // edge: the length limit 10^5
        both("10^5 letters, unique one last", 99999, "a".repeat(99999) + "b");
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // run the same input through both methods of the solution
    private static void both(String name, int expected, String s) {
        check(name + "  [firstUniqChar]", expected,
                () -> LC387FirstUniqueCharacterInAString.firstUniqChar(s));
        check(name + "  [firstUniqueCharOpt]", expected,
                () -> LC387FirstUniqueCharacterInAString.firstUniqueCharOpt(s));
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
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
