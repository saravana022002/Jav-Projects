package Practice.TwoPointers;

import java.util.*;

// ============================================================================
// LC 5. Longest Palindromic Substring   [Medium]
// https://leetcode.com/problems/longest-palindromic-substring/
// Topics: Two Pointers, String, Dynamic Programming, Manacher
// ============================================================================
//
// PROBLEM
//   Given a string s, return its longest palindromic substring: a contiguous piece of s that
//   reads the same forwards and backwards. If several palindromes share the maximum length,
//   any one of them is accepted.
//
// EXAMPLES
//   s = "babad"  ->  "bab"   "aba" is an equally valid answer (this code returns "aba")
//   s = "cbbd"   ->  "bb"
//
// CONSTRAINTS
//   1 <= s.length <= 1000,   s consists of digits and English letters only
//
// INTUITION
//   Every palindrome mirrors around its centre, and the centre is either one character (odd
//   length, like "aba") or the gap between two characters (even length, like "bb"). So try all
//   2n - 1 centres and grow outwards with two pointers while the two ends are equal; the
//   longest span seen is the answer. Each palindrome is reached from its own centre, so none
//   is missed, and a growth step costs O(1).
//
// APPROACH (as implemented in longestPalindrome)
//   1. chars = s.toCharArray(); longest = ""; maxLength = 0 (this holds r - l of the best
//      palindrome so far, i.e. its length minus one).
//   2. For every index i:
//      a. Odd centre: l = r = i. While l >= 0, r < n and chars[l] == chars[r]: if
//         maxLength <= r - l, then longest = s.substring(l, r + 1) and maxLength = r - l;
//         then l--, r++.
//      b. Even centre: l = i, r = i + 1, the same loop.
//   3. Return longest.
//   The test is <=, so a palindrome that ties with the best so far replaces it: the LAST one of
//   maximum length wins.
//
// DRY RUN   s = "babad"
//   i=0  odd "b": longest = "b", maxLength = 0           even: 'b' != 'a'
//   i=1  odd "a": tie, longest = "a"; widen: 'b' == 'b' -> "bab" (r-l = 2), maxLength = 2
//        even: 'a' != 'b'
//   i=2  odd "b": r-l = 0, too short; widen: 'a' == 'a' -> "aba" (r-l = 2), ties -> replaces
//        "bab"; widen: 'b' != 'd'.   even: 'b' != 'a'
//   i=3  odd "a": too short; widen: 'b' != 'd'.   even: 'a' != 'd'
//   i=4  odd "d": too short.   even: r runs past the end
//   return "aba"
//
// COMPLEXITY
//   Time : O(n^2) - 2n - 1 centres, each widening at most n/2 times; the substring copies
//          happen only when the best grows or ties, O(n) times in total
//   Space: O(n) - the char[] copy and the result substring
//
// GOTCHAS
//   - Even-length palindromes have no middle character. Without the second loop "cbbd" would
//     come out as "b" instead of "bb".
//   - maxLength stores r - l, not the length, and the comparison uses the same quantity, so it
//     is consistent; longest is always set together with it.
//   - Because of <=, the later of two equally long palindromes is returned (V2 uses < and
//     returns the earlier one).
//   - The index checks l >= 0 and r < chars.length come before chars[l] == chars[r].
//
// ALSO IN THIS FILE
//   LC 1411. Number of Ways to Paint N * 3 Grid   [Hard]
//   https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
//   Colour every cell of an n x 3 grid red, yellow or green so that cells sharing a side never
//   have the same colour, and count the ways (LeetCode wants the count modulo 10^9 + 7).
//   colour(array, row, column) is a brute-force attempt: it fills the cells in row order, tries
//   R, G and Y wherever the left and the upper neighbour differ from that colour, recurses on
//   a deep copy of the grid (deepCopy), and adds one to the static counter total for each
//   finished grid (allList keeps every finished grid). It gives the right counts for small
//   grids (n = 1..6 give 12, 54, 246, 1122, 5118, 23346) but is exponential and has no
//   modulo.
//   Official examples: n = 1 -> 12, n = 5000 -> 30228214;   constraint: 1 <= n <= 5000.
//
// STATUS
//   Known issues. The code is left unchanged on purpose.
//   1. main() does not run longestPalindrome: its two lines are commented out. It runs colour()
//      on a 5000 x 3 grid (the LC 1411 brute force), which can never finish: the number of
//      colourings grows exponentially with the rows, and the recursion, up to 15000 levels
//      deep, keeps a full copy of the grid at every level. With a 256 MB heap it died with
//      java.lang.OutOfMemoryError within seconds. To try LC 5, enable the commented lines and
//      drop the colour() call.
//   2. colour() counts into the static field total, which is never reset, so a second call in
//      the same run returns the old total plus the new count.
//   longestPalindrome itself is correct (checked against a brute force on random strings).
//
// SEE ALSO
//   LC5LongestPalindromicSubstringV2.java - the same expand-around-centre idea with a strict <
//     comparison (the earlier of equal palindromes wins) and no char[] copy
//
// RELATED
//   - LC 647 Palindromic Substrings - the same centre expansion, counting every palindrome
//     instead of keeping the longest.
//   - LC 516 Longest Palindromic Subsequence - palindromes that need not be contiguous (DP).
// ============================================================================
public class LC5LongestPalindromicSubstringV1 {
    public static void main(String[] args) {
        // KNOWN ISSUE (see STATUS): the LC 5 call below is commented out. main() runs the LC 1411
        // brute force on a 5000 x 3 grid instead, and that run never finishes (OutOfMemoryError).
//        String str = "cbbd";
//        longestPalindrome(str);
//
        Character [][] a = new Character[5000][3];
        int c=  colour(a, 0, 0);
        System.out.println(c);
//        for (Character[][] array : allList) {
//            System.out.println(Arrays.deepToString(array));
//        }
    }

    // LC 1411 attempt: total = number of finished colourings, allList = every finished grid
    private static Integer total = 0;
    private static final List<Character[][]> allList = new ArrayList<>();
    // Brute force: colours cell (row, column), then recurses on the next cell in row order.
    // A cell may get R, G or Y if its left and its upper neighbour do not already have that
    // colour. Returns the running total. Exponential - see STATUS.
    public static int colour(Character[][] array, int row, int column) {
        // safety guard; the recursion below never steps past the last cell
        if(array.length < row || array[0].length < column) {
            return total;
        }


        // colour 'R': allowed when the cell to the left and the cell above are not 'R'
        if((column == 0 || (column > 0 && array[row][column - 1] != 'R')) && (row == 0 || (row > 0 && array[row-1][column] != 'R'))) {

            int locRow = row, locCol = column;
            // paint a copy, so this grid stays unchanged for the other two colours
            Character[][] test = deepCopy(array);
            test[locRow][locCol] = 'R';

            // that was the last cell: one more complete colouring
            if(array.length == row+1 && array[0].length == column+1) {
                total++;
                allList.add(test);
            } else {
                // move to the next cell: along the row, or to the start of the next row
                if(array[0].length == column+1) {
                    locCol = 0;
                    locRow++;
                } else {
                    locCol++;
                }

                colour(deepCopy(test), locRow, locCol);
            }
        }

        // colour 'G': the same steps as for 'R'
        if((column == 0 || (column > 0 && array[row][column - 1] != 'G')) && (row == 0 || (row > 0 && array[row-1][column] != 'G'))) {
            int locRow = row, locCol = column;
            Character[][] test = deepCopy(array);
            test[locRow][locCol] = 'G';

            if(array.length == row+1 && array[0].length == column+1) {
                total++;
                allList.add(test);
            } else {
                if(array[0].length == column+1) {
                    locCol = 0;
                    locRow++;
                } else {
                    locCol++;
                }
                colour(deepCopy(test), locRow, locCol);
            }
        }
        // colour 'Y': the same steps as for 'R'
        if((column == 0 || (column > 0 && array[row][column - 1] != 'Y')) && (row == 0 || (row > 0 && array[row-1][column] != 'Y'))) {
            int locRow = row, locCol = column;
            Character[][] test = deepCopy(array);
            test[locRow][locCol] = 'Y';

            if(array.length == row+1 && array[0].length == column+1) {
                total++;
                allList.add(test);
            }  else {
                if(array[0].length == column+1) {
                    locCol = 0;
                    locRow++;
                } else {
                    locCol++;
                }
                colour(deepCopy(test), locRow, locCol);
            }
        }

        return total;
    }

    // Copies the grid row by row (copying only the outer array would share the rows); the
    // width is fixed at 3 columns
    static Character[][] deepCopy(Character[][] src) {
        Character[][] copy = new Character[src.length][3];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone(); // clone each row
        }
        return copy;
    }

    // LC 5: expand around each of the 2n - 1 centres; on equal length the later palindrome
    // wins (<=). O(n^2)
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        String longest = "";
        int l;
        int r;
        // r - l of the best palindrome so far (its length minus one)
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            // odd-length palindromes: the centre is the character i
            l = i;
            r = i;
            while(l >= 0 && r < chars.length && chars[l] == chars[r]){
                // at least as long as the best so far: remember it
                if(maxLength <= r - l){
                    longest = s.substring(l, r + 1);
                    maxLength = r - l;
                }
                l--;
                r++;
            }
            // even-length palindromes: the centre is the gap between i and i + 1
            l = i;
            r = i+1;
            while(l >= 0 && r < chars.length && chars[l] == chars[r]){
                if(maxLength <= r - l){
                    longest = s.substring(l, r + 1);
                    maxLength = r - l;
                }
                l--;
                r++;
            }
        }
        return longest;
    }
}
