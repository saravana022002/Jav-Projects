package Practice.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// ============================================================================
// LC 151. Reverse Words in a String   [Medium]
// https://leetcode.com/problems/reverse-words-in-a-string/
// Topics: Two Pointers, String
// ============================================================================
//
// PROBLEM
//   s is a sentence whose words (runs of non-space characters) are separated by one or more
//   spaces, and it may also start or end with spaces. Return the words in reverse order,
//   joined by exactly one space, with no space at the front or the back.
//
// EXAMPLES
//   s = "the sky is blue"    ->  "blue is sky the"
//   s = "  hello world  "    ->  "world hello"     the edge spaces are dropped
//   s = "a good   example"   ->  "example good a"   the three spaces become one
//
// CONSTRAINTS
//   1 <= s.length <= 10^4,   s has English letters (both cases), digits and spaces ' ',
//   and at least one word
//
// INTUITION
//   Once the words are lifted out of the sentence, the spacing no longer matters: reverse
//   the list of words and glue it back together with single spaces. Finding the words is one
//   left-to-right scan that only has to notice where a word starts (a non-space after a
//   space or at the front) and where it ends (a space after a word, or the end of the
//   string).
//
// APPROACH (as implemented below)
//   1. words = empty list; starting = start index of the word being read; isWord = "the scan
//      is currently inside a word".
//   2. For each index i of s:
//      - non-space and not isWord : a word begins -> starting = i, isWord = true
//      - space and isWord         : the word just ended -> words.add(s.substring(starting, i)),
//                                   isWord = false
//      - anything else (more letters of a word, or extra spaces) : nothing to do
//   3. After the loop, if isWord is still true the string ended inside a word:
//      words.add(s.substring(starting)).
//   4. Collections.reverse(words), then String.join(" ", words).
//
// DRY RUN   s = "  hello world  "   (15 characters: 2 spaces, hello, space, world, 2 spaces)
//   i=0,1   spaces while isWord is false            -> nothing
//   i=2     'h' starts a word                       -> starting = 2, isWord = true
//   i=7     space ends it                           -> words = [hello]  (substring(2, 7))
//   i=8     'w' starts a word                       -> starting = 8, isWord = true
//   i=13    space ends it                           -> words = [hello, world]  (substring(8, 13))
//   i=14    space while isWord is false             -> nothing
//   after the loop isWord is false, so nothing more to add
//   reverse -> [world, hello]   join -> "world hello"
//   main() input "the sky is blue" ends inside "blue", so that last word is added by step 3.
//
// COMPLEXITY
//   Time : O(n) - one scan, then the reverse and the join are linear in the number of
//          characters
//   Space: O(n) - the list of word substrings plus the result; every character is copied
//          at most twice
//
// GOTCHAS
//   - Step 3 matters: a sentence that ends with a letter never meets a closing space, so its
//     last word would be lost without the check after the loop.
//   - Not in place: the follow-up (mutable string, O(1) extra space) is not what this code
//     does, since Java strings are immutable and the words are stored in a list.
//   - A string of only spaces would return "" here; the constraints guarantee a word.
//
// STATUS
//   The method is correct; main() prints "blue is sky the".
//
// RELATED
//   - LC 186 Reverse Words in a String II - the in-place version on a character array.
//   - LC 557 Reverse Words in a String III - reverses the letters inside each word and keeps
//     the word order.
// ============================================================================
public class LC151ReverseWordsInAString {
    public static void main(String[] args) {
        // Expected output: blue is sky the
        String s = "the sky is blue";
        System.out.println(reverseWords(s));
    }
    // Collects the words left to right, then returns them reversed and joined by one space
    public static String reverseWords(String s) {
        List<String> words = new ArrayList<>();

        // starting = index where the current word began; isWord = are we inside a word
        int starting = 0;
        boolean isWord = false;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != ' '){
                // first letter of a new word
                if(!isWord){
                    starting = i;
                    isWord = true;
                }
            }else {
                // a space right after a word closes that word
                if(isWord){
                    words.add(s.substring(starting, i));
                    isWord = false;
                }
            }
        }
        // the string ended inside a word, so no space closed it: add it now
        if (isWord) {
            words.add(s.substring(starting));
        }
        Collections.reverse(words);
        return String.join(" ", words);

    }
}
