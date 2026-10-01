package Practice.Strings;

// ============================================================================
// LC 686. Repeated String Match   [Medium]
// https://leetcode.com/problems/repeated-string-match/
// Topics: String, String Matching, Z Algorithm, Knuth-Morris-Pratt Algorithm,
//         Boyer-Moore String-Search Algorithm
// ============================================================================
//
// PROBLEM
//   Write the string a one copy after another (a + a + a ...) and stop as soon as the
//   string b can be found inside the result as a contiguous substring. Return how many
//   copies that takes, as small as possible, or -1 if b never shows up however long you
//   keep repeating a.
//
// EXAMPLES
//   a = "abcd", b = "cdabcdab"  ->  3    "abcdabcdabcd" holds b starting at index 2
//   a = "a",    b = "aa"        ->  2
//   a = "abc",  b = "cabcabca"  ->  4    the input main() uses: "abcabcabcabc" holds b at
//                                        index 2, while 3 copies are too short
//
// CONSTRAINTS
//   1 <= a.length, b.length <= 10^4,   both strings use lowercase English letters only
//
// INTUITION
//   Only two candidate answers exist. Let q = ceil(|b| / |a|), the fewest copies that are at
//   least as long as b. If b occurs anywhere in a repeated text, sliding that occurrence left
//   by whole copies of a shows it also occurs starting inside the FIRST copy, at some offset
//   below |a|; it then ends before |a| - 1 + |b|, so q + 1 copies always suffice. So test the
//   text with q copies, then with q + 1 copies, and answer q, q + 1 or -1.
//   "Does text contain b?" is a plain substring search. The code answers it with a
//   Rabin-Karp rolling hash: compute a number for every window of |b| letters, updating it
//   in O(1) when the window slides by one letter, and compare it with the number for b.
//   Equal numbers are confirmed with equals(), because different strings can share a hash.
//
// APPROACH (as implemented below)
//   1. repeatTimes = (lenB + lenA - 1) / lenA = ceil(lenB / lenA); text = a repeated that
//      many times (built in stringBuilder).
//   2. hash = calculateHash(b), where calculateHash(s) = s[0] + s[1]*101 + s[2]*101^2 + ...
//      hashComp = the same hash of the first lenB letters of text (the first window).
//   3. For each window start i from 0 to text.length() - lenB:
//      - hash == hashComp and b.equals(text.substring(i, i + lenB)) -> return repeatTimes
//      - otherwise slide to the next window (skipped after the last one):
//        hashComp = updateHash(...) = (hashComp - old letter) / 101 + new letter * 101^(lenB-1)
//   4. Not found: repeatTimes++, append one more a to stringBuilder, recompute hashComp for
//      the first window and run the same loop once more (the loop is written out twice).
//   5. Still not found -> return -1.
//
// DRY RUN   a = "abc", b = "cabcabca"   (|a| = 3, |b| = 8)
//   repeatTimes = (8 + 3 - 1) / 3 = 3    text = "abcabcabc" -> windows start at i = 0, 1
//   pass 1: the windows "abcabcab" and "bcabcabc" are not b                   -> no match
//   repeatTimes = 4                      text = "abcabcabcabc" -> windows start at i = 0..4
//   pass 2: i=0 "abcabcab", i=1 "bcabcabc", i=2 "cabcabca" = b: the hashes agree and
//           equals() confirms it                                             -> return 4
//   The rolling update on a 2-letter window (hash = c0 + 101 * c1):
//     "ab" = 97 + 98*101 = 9995.  Slide to "bc": (9995 - 97) / 101 = 98, then
//     98 + 99*101 = 10097, which is exactly the hash of "bc" = 98 + 99*101.
//
// COMPLEXITY   (|a|, |b| = lengths; the text has at most |b| + 2|a| letters)
//   Time : intended O(|a| + |b|) - build the text, hash b, then one O(1) slide per window;
//          equals() costs O(|b|) but only runs when the hashes agree.
//          As written (see STATUS) the hash carries no information once |b| >= 10: every
//          window "agrees" and is checked with equals(), i.e. O((|a| + |b|) * |b|) worst case.
//   Space: O(|a| + |b|) - the repeated text, plus the window substrings created for equals()
//
// GOTCHAS
//   - Only q and q + 1 copies are ever tried. a = "abcd", b = "dab" needs 2 copies although
//     q = 1: the match wraps around the end of the first copy.
//   - A hash hit is always verified with equals(), so a false POSITIVE cannot produce a wrong
//     answer; but nothing protects against a false NEGATIVE, where a correct match gets a
//     different rolled hash and is skipped (the known issue below).
//   - Textbook Rabin-Karp keeps the hash small with a modulus. This one has none: the hash is
//     a plain sum whose top term is 101^(|b|-1), which outgrows a long very quickly.
//   - The slide after the last window is skipped, so text.charAt(i + lenB) never goes out of
//     range.
//
// STATUS
//   Known issue. The code is left unchanged on purpose.
//   The rolling hash is unreliable when |b| is 8 or 9. The hash is computed with
//   Math.pow(101, i) in double arithmetic and cast to long; beyond 2^53 a double cannot hold
//   every integer, so the rolled hash of a window can differ from the hash computed from
//   scratch for the same text, and a real match is skipped. Example: a = "accac",
//   b = "cacaccac" returns -1, correct answer 2 ("accacaccac" holds b at index 2). In random
//   tests only |b| = 8 and |b| = 9 gave wrong answers, always -1 instead of the real count.
//   For |b| <= 7 the numbers stay exact. From |b| = 10 the sums overflow and are clamped to
//   Long.MAX_VALUE by the cast, so every window looks equal to b and equals() decides -
//   correct, but the hash no longer saves any work.
//   main() happens to hash correctly for its input and prints 4, which is the right answer.
//
// RELATED
//   - LC 459 Repeated Substring Pattern - also about a string made of repeated copies of a
//     smaller piece.
//   - LC 28 Find the Index of the First Occurrence in a String - the plain substring search
//     that this problem calls twice; KMP or Rabin-Karp are classic ways to do it.
// ============================================================================
public class LC686RepeatedStringMatch {
    public static void main(String[] args) {
        // Expected output: 4 (3 copies are too short). This input happens to hash correctly;
        // other 8- or 9-letter b can fail - see STATUS.
        String a = "abc";
        String b = "cabcabca";
        System.out.println(repeatedStringMatch(a, b));
    }

    // Returns the fewest copies of a whose concatenation contains b, or -1.
    // Only ceil(lenB / lenA) and one more copy ever need to be tried.
    public static int repeatedStringMatch(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();

        // ceil(lenB / lenA): the fewest copies that are at least as long as b
        int repeatTimes = (lenB + lenA - 1) / lenA;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= repeatTimes; i++) {
            stringBuilder.append(a);
        }
        String text = stringBuilder.toString();


        // hash = hash of b; hashComp = hash of the current window of text (first window here)
        long hash = calculateHash(b);
        long hashComp = calculateHash(text.substring(0, lenB));

        // pass 1: slide a window of lenB letters over text
        for (int i = 0; i <= text.length() - lenB; i++) {
            // equal hashes -> confirm with equals() (a hash match alone could be a collision)
            if(hash == hashComp){
                if(b.equals(text.substring(i, i + lenB))){
                    return repeatTimes;
                }
            }
            // move to the next window (nothing to move to after the last one)
            if(i < text.length() - lenB) {
                hashComp = updateHash(hashComp, text.charAt(i), text.charAt(i + lenB), lenB);
            }
        }

        // no match with repeatTimes copies: add one more copy and search again (same loop)
        repeatTimes++;
        stringBuilder.append(a);
        text = stringBuilder.toString();
        hashComp = calculateHash(text.substring(0, lenB));
        for (int i = 0; i <= text.length() - lenB; i++) {
            if(hash == hashComp){
                if(b.equals(text.substring(i, i + lenB))){
                    return repeatTimes;
                }
            }
            if(i < text.length() - lenB) {
                hashComp = updateHash(hashComp, text.charAt(i), text.charAt(i + lenB), lenB);
            }
        }
        // not found even with the extra copy -> b can never appear
        return -1;
    }


    // base of the polynomial hash
    public static final int PRIME = 101;
    // hash(s) = s[0] + s[1]*101 + s[2]*101^2 + ... ; computed in double through Math.pow and
    // cast back to long, so it is exact only while the values stay below 2^53 (|s| <= 7)
    private static long calculateHash(String a){
        long hash = 0;
        for (int i = 0; i < a.length(); i++) {
            hash = (long) (hash + a.charAt(i) * Math.pow(PRIME, i));
        }
        return hash;
    }
    // Slide the window one letter: drop oldChar (it has power 0), divide by 101 so every
    // remaining letter moves one power down, then add newChar at the top power 101^(pattern-1)
    private static long updateHash(long prevHash, Character oldChar, Character newChar, int pattern){
        long newHash = (prevHash - oldChar) / PRIME;
        newHash = (long) (newHash + newChar * Math.pow(PRIME, pattern - 1));
        return newHash;
    }

}
