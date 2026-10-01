package Practice.Strings;

// ============================================================================
// LC 3403. Find the Lexicographically Largest String From the Box I   [Medium]
// https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/
// Topics: Two Pointers, String, Enumeration
// ============================================================================
//
// PROBLEM
//   A game is played in rounds. In every round the string word is cut into numFriends
//   non-empty pieces (consecutive, in order), and the cut must be one that no earlier round
//   used. All the pieces of all rounds go into a box. When every possible cut has been
//   played, return the lexicographically (dictionary order) largest string in the box.
//
// EXAMPLES
//   word = "dbca", numFriends = 2  ->  "dbc"   the cuts are d|bca, db|ca, dbc|a; "dbc" is
//                                              the largest piece among all of them
//   word = "gggg", numFriends = 4  ->  "g"     the only cut is g|g|g|g
//
// CONSTRAINTS
//   1 <= word.length <= 5 * 10^3,   word has lowercase English letters only,
//   1 <= numFriends <= word.length
//
// INTUITION
//   Because every possible cut is played, the question is simply "what is the largest piece
//   any cut can produce?". A piece can start at any index i, and it can hold at most
//   L = word.length() - numFriends + 1 letters, because each of the other numFriends - 1
//   friends needs at least one letter. For a fixed start, the longest allowed piece is the
//   best one, since a string is larger than each of its own prefixes. So the answer is the
//   largest of word.substring(i, min(i + L, n)) over all start indices i.
//   (With numFriends == 1 there is only one "cut", the whole word - see STATUS.)
//
// APPROACH (as implemented below)
//   1. strLen = word.length(); maxLength = strLen - numFriends + 1 (the L above).
//   2. max = word.substring(0, maxLength) - the piece that starts at index 0.
//   3. For every other start i from 1 to strLen - 1:
//      - last = min(i + maxLength, strLen), the end of the longest allowed piece at i
//      - substring = word.substring(i, last)
//      - if substring.compareTo(max) > 0, max = substring
//      (word.indexOf(max) on the way has no effect: its result is discarded.)
//   4. Return max.
//
// DRY RUN   word = "aann", numFriends = 2   (the input main() uses)
//   strLen = 4, maxLength = 4 - 2 + 1 = 3, max = "aan"
//   i=1  last = min(4, 4) = 4  substring = "ann"  "ann" > "aan" -> max = "ann"
//   i=2  last = min(5, 4) = 4  substring = "nn"   "nn" > "ann"  -> max = "nn"
//   i=3  last = min(6, 4) = 4  substring = "n"    "n" < "nn" (a prefix is smaller) -> keep
//   return "nn"      (the cuts a|ann, aa|nn, aan|n contain "nn" as the largest piece)
//
// COMPLEXITY   (n = word.length(), L = n - numFriends + 1)
//   Time : O(n * L) for the real work - n start positions, each building a substring and
//          comparing it with max in up to L steps. The stray word.indexOf(max) call can cost
//          up to O(n * L) per iteration on its own (see STATUS).
//   Space: O(L) - the substring and max (each at most L letters)
//
// GOTCHAS
//   - Only the longest allowed piece per start matters ("nn" beats "n"), so shorter pieces
//     never need to be tried.
//   - numFriends == word.length() gives L = 1: every piece is a single letter and the
//     answer is the largest letter (Example 2).
//   - Near the end of the word the longest allowed piece runs out of letters; the
//     `if(strLen < last)` line clips it to the end of the word.
//
// STATUS
//   Known issues. The code is left unchanged on purpose.
//   1. numFriends == 1 gives a wrong answer. With one friend the only "cut" is the whole
//      word, so the answer must be word itself, but the loop still compares the suffixes and
//      returns the largest one when it is bigger than word: word = "gh", numFriends = 1
//      returns "h", correct answer "gh". The special case numFriends == 1 is missing.
//   2. The statement `word.indexOf(max);` is a leftover: its result is thrown away, yet the
//      search runs on every iteration. For word = 'a' repeated 4999 times followed by 'b',
//      numFriends = 2, it took about 5 seconds when measured here, against a few
//      milliseconds without the call.
//   For numFriends >= 2 the answers match a brute force over all cuts (checked on small
//   random words). main() prints "nn" for ("aann", 2), which is correct.
//
// RELATED
//   - LC 1163 Last Substring in Lexicographical Order - the same "largest substring"
//     question with no limit on the piece length.
// ============================================================================
public class LC3403FindTheLexicographicallyLargestStringFromTheBoxI {


    public static void main(String[] args) {
        // the cuts a|ann, aa|nn, aan|n contain "nn" as the largest piece. Expected output: nn
        String word = "aann";
        int numFriends = 2;
        System.out.println(answerString(word, numFriends));
    }
    // Returns the largest piece any cut of word into numFriends parts can contain.
    // Wrong for numFriends == 1 and slow on some inputs - see STATUS.
    public static String answerString(String word, int numFriends) {
        int strLen = word.length();
        // longest possible piece: the other numFriends - 1 friends need one letter each
        int maxLength = strLen - numFriends + 1;
        // the piece starting at index 0
        String max = word.substring(0, maxLength);
        for (int i = 1; i < strLen; i++) {
            // end of the longest allowed piece starting at i, cut off at the end of the word
            int last = i + maxLength;
            if(strLen < last){
                last = strLen;
            }
            // leftover: the result is not used, it only costs time (see STATUS)
            word.indexOf(max);
            String substring = word.substring(i, last);
            // keep the larger piece (a proper prefix compares smaller than the longer string)
            if(substring.compareTo(max) > 0 ){
                max = substring;
            }
        }
        return max;
    }
}
