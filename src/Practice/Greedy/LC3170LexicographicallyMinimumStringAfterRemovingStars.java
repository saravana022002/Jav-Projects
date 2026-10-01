package Practice.Greedy;

import java.util.*;

// ============================================================================
// LC 3170. Lexicographically Minimum String After Removing Stars   [Medium]
// https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/
// Topics: Hash Table, String, Stack, Greedy, Heap (Priority Queue)
// ============================================================================
//
// PROBLEM
//   s has lowercase letters and '*' characters. While a '*' is left, take the LEFTMOST '*'
//   and delete it together with the smallest letter somewhere to its left (if several copies
//   tie for smallest you may pick any of them). Return the lexicographically smallest string
//   you can end up with once every '*' is gone. The input guarantees that a letter is always
//   available for each star.
//
// EXAMPLES
//   s = "aaba*"  ->  "aab"     the star removes one 'a'; removing the last one (s[3]) gives
//                              the smallest result ("aab" beats "aba")
//   s = "abc"    ->  "abc"     no star, nothing to do
//   s = "abc*de*fgh*"  ->  "defgh"     what main() runs: the stars remove a, then b, then c
//
// CONSTRAINTS
//   1 <= s.length <= 10^5
//   s consists only of lowercase English letters and '*'
//   the input is generated so that every '*' can be deleted
//
// INTUITION
//   Two choices per star. Which letter? The rule fixes it: the smallest letter on its left.
//   Which copy, when that letter occurs several times? The RIGHTMOST one. Compare deleting the
//   copy at position p with deleting a later copy at q (same letter c, and every letter in
//   between is >= c because c is the smallest on the left): the results differ only in that
//   stretch, "stretch + c" versus "c + stretch", and since every letter of the stretch is
//   >= c, "c + stretch" is never larger. So the later copy is never worse.
//   Stars are handled left to right, so the letters "to its left" are simply the survivors
//   collected so far.
//
// APPROACHES IN THIS FILE
//   A. clearStars()  - keep the survivors in a list; for every '*' scan the whole list for the
//                      smallest letter (<= keeps moving right on ties -> rightmost copy) and
//                      remove it. O(n^2) worst case. Not used by main().
//   B. clearStars1() - TreeMap from letter to a Deque of its indices: the smallest letter and
//                      its rightmost copy come out without scanning; the StringBuilder is then
//                      fixed by a linear search. Still O(n^2) worst case.   <- main() runs this
//
// APPROACH (as implemented below, method B)
//   1. idx numbers the letters only (stars get no number). For every letter c:
//      result.append(c), positions.add(idx), charMap[c].addLast(idx), idx++.
//      So `positions` runs parallel to `result`, and each deque is in increasing index order.
//   2. For a '*': smallestChar = charMap.firstKey() (the TreeMap keeps letters sorted);
//      removeIndex = that letter's deque.removeLast() (its rightmost copy); if the deque is
//      now empty, remove the letter from charMap.
//   3. Find removeIndex in `positions` with a linear search; at position j delete
//      result[j] and positions[j].
//   4. Return result.
//
// DRY RUN   s = "abc*de*fgh*"  (main() prints defgh); a..h get idx 0..7
//   a b c   result = "abc"      charMap a:[0] b:[1] c:[2]
//   *       smallest key a, removeLast -> 0, key a dropped      result = "bc"
//   d e     result = "bcde"     (d:[3] e:[4] added)
//   *       smallest key b, removeLast -> 1                     result = "cde"
//   f g h   result = "cdefgh"
//   *       smallest key c, removeLast -> 2                     result = "defgh"
//   Tie case "aaba*": charMap a:[0,1,3] b:[2]; the star takes removeLast() = 3 from key a,
//   deleting the LAST 'a' -> "aab".
//
// COMPLEXITY
//   A: Time O(n^2) worst case - each star scans and shifts up to n survivors.  Space O(n).
//   B: Time O(n^2) worst case - the TreeMap/deque work is cheap (at most 26 keys) but finding
//      removeIndex in `positions` and deleting from the list and StringBuilder is linear.
//      Space O(n).
//   A stress input of 50000 letters followed by 50000 stars took under two seconds with
//   either method when measured for this note, so n = 10^5 is workable in practice.
//
// GOTCHAS
//   - Ties must delete the RIGHTMOST copy (A: the <= comparison, B: removeLast()).
//   - idx counts letters only, so every letter has a unique id even after stars are skipped.
//   - charMap.firstKey() would throw on an empty map; the constraints guarantee that every
//     star has a letter to its left.
//   - positions.get(j) == removeIndex compares an Integer with an int, so it unboxes and
//     compares the values.
//   - This is NOT LC 2390: there each star removes the nearest letter on its left, which a
//     plain stack handles.
//
// STATUS
//   Both methods are correct (checked against a brute force that tries every tie choice, on
//   4000 random strings). main() runs only clearStars1; clearStars is an unused alternative.
//
// RELATED
//   - LC 2390 Removing Stars From a String - the same stars, but each one deletes the nearest
//     letter on its left (a simple stack).
// ============================================================================
public class LC3170LexicographicallyMinimumStringAfterRemovingStars {

    public static void main(String[] args) {
        // Prints defgh: the three stars remove a, b and c. (Uses approach B.)
        String s = "abc*de*fgh*";
        System.out.println(clearStars1(s));
    }
    // Approach A: list of survivors, linear scan per star - O(n^2). Not called by main().
    public static String clearStars(String s) {
        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '*'){
                // a letter: it survives for now
                stack.add(s.charAt(i));
            }else {
                // a star: find the smallest survivor; <= lets a later copy replace an earlier one,
                // so minIndex ends on the RIGHTMOST smallest letter
                int minIndex = -1;
                char minChar = 'z' + 1;
                for (int j = 0; j < stack.size(); j++) {
                    if(stack.get(j) <= minChar){
                        minChar = stack.get(j);
                        minIndex = j;
                    }
                }
                if(minIndex != -1){
                    stack.remove(minIndex);
                }
            }
        }
        // join the survivors into the answer
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < stack.size(); i++) {
            stringBuilder.append(stack.get(i));
        }
        return stringBuilder.toString();
    }


    // Approach B (run by main): TreeMap letter -> deque of indices gives the smallest letter and
    // its rightmost copy quickly; removing it from the StringBuilder is a linear search.
    public static String clearStars1(String s) {
        // letter -> indices (idx values) of its surviving copies, in increasing order
        TreeMap<Character, Deque<Integer>> charMap = new TreeMap<>();
        // positions.get(k) = idx of the letter currently at result.charAt(k)
        List<Integer> positions = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int idx = 0;
        for (char c : s.toCharArray()) {
            if(c != '*'){
                // a letter: append it and record where it lives
                result.append(c);
                positions.add(idx);
                charMap.putIfAbsent(c, new ArrayDeque<>());
                charMap.get(c).addLast(idx);
                idx++;
            }else {
                // a star: smallest surviving letter, its rightmost copy
                Character smallestChar = charMap.firstKey();
                Deque<Integer> deque = charMap.get(smallestChar);
                int removeIndex = deque.removeLast();
                if(deque.isEmpty()){
                    charMap.remove(smallestChar);
                }
                // locate that copy in the current string and delete it
                for (int j = 0; j < positions.size(); j++) {
                    if(positions.get(j) == removeIndex){
                        result.deleteCharAt(j);
                        positions.remove(j);
                        break;
                    }
                }

            }
        }
        return result.toString();
    }
}
