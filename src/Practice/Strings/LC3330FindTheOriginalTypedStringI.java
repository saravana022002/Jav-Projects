package Practice.Strings;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 3330. Find the Original Typed String I   [Easy]
// https://leetcode.com/problems/find-the-original-typed-string-i/
// Topics: String
// ============================================================================
//
// PROBLEM
//   Alice types a string, but she may hold a key down too long so that one character shows
//   up several times on the screen. She made this slip at most once. You are given the text
//   that ended up on the screen (word). Count how many different strings she could have
//   intended to type.
//
// EXAMPLES
//   word = "abbcccc"  ->  5   "abbcccc" (no slip), "abbccc", "abbcc", "abbc" (the c key was
//                            held) and "abcccc" (the b key was held)
//   word = "abcd"     ->  1   no letter is repeated, so only "abcd" itself
//   word = "aaaa"     ->  4   "aaaa", "aaa", "aa", "a"
//
// CONSTRAINTS
//   1 <= word.length <= 100,   word has lowercase English letters only
//
// INTUITION
//   A slip can only have happened inside a run of equal letters, and it squeezes that run
//   into a shorter run of the same letter. So the possible originals are: the word itself
//   (no slip), plus, for every run of length g, the g - 1 strings in which that run is cut
//   down to 1, 2, ..., g - 1 letters. All of these strings are different. A run of length g
//   has exactly g - 1 pairs of equal neighbours, so the total is
//   1 + (number of adjacent equal pairs) - the runs do not even have to be found.
//
// APPROACH (as implemented below)
//   1. prev = the first character; count = 1 (the word itself, no slip).
//   2. For each following character curr:
//      - curr equals prev : one more equal-neighbour pair -> one more possible original,
//                           count++ (prev already holds that letter)
//      - otherwise        : a new run starts -> prev = curr
//   3. Return count.
//
// DRY RUN   word = "abbcccc"
//   start             prev = 'a'   count = 1
//   i=1  'b' != 'a'   prev = 'b'
//   i=2  'b' == 'b'   count = 2
//   i=3  'c' != 'b'   prev = 'c'
//   i=4  'c' == 'c'   count = 3
//   i=5  'c' == 'c'   count = 4
//   i=6  'c' == 'c'   count = 5
//   return 5   (1 + 1 pair in "bb" + 3 pairs in "cccc")
//
// COMPLEXITY
//   Time : O(n) - one pass, constant work per character
//   Space: O(1) - prev, curr and count only; the possible strings are never built
//
// GOTCHAS
//   - Only ADJACENT equal letters count: in "ere" the two e's are separated by an r, so the
//     answer is 1 (main()'s last call).
//   - A slip shortens a run but cannot remove it, so a run of g letters adds g - 1 strings,
//     not g.
//   - prev is a boxed Character and curr a char; prev.equals(curr) boxes curr and compares
//     the letters, which is what is wanted here.
//
// STATUS
//   The method is correct. main() prints 5, 1, 4 and 1 (the three examples plus "ere").
//   The imports HashMap and Map are not used.
//
// RELATED
//   - LC 3333 Find the Original Typed String II - the harder sequel of this typing story.
//   - LC 443 String Compression - also works on runs of equal consecutive characters.
// ============================================================================
public class LC3330FindTheOriginalTypedStringI {
    public static void main(String[] args) {
        // Expected output, one per line: 5, 1, 4 (the three examples), then 1 for "ere"
        // (its two e's are not neighbours)
        System.out.println(possibleStringCount("abbcccc"));
        System.out.println(possibleStringCount("abcd"));
        System.out.println(possibleStringCount("aaaa"));
        System.out.println(possibleStringCount("ere"));
    }
    // Returns 1 + the number of adjacent equal-letter pairs
    public static int possibleStringCount(String word) {
        // prev = previous letter; count starts at 1 for "the word itself, no slip"
        Character prev = word.charAt(0);
        char curr;
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            curr = word.charAt(i);
            // equal neighbours: the slip may have been inside this run -> one more original
            if(prev.equals(curr)) {
                count++;
            }else {
                // a different letter starts a new run
                prev = curr;
            }
        }
        return count;
    }
}
