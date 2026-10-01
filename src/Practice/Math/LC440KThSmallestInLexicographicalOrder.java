package Practice.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ============================================================================
// LC 440. K-th Smallest in Lexicographical Order   [Hard]
// https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
// Topics: Trie
// ============================================================================
//
// PROBLEM
//   Sort the numbers 1..n as if they were STRINGS (dictionary order: 1, 10, 11, ..., 2,
//   20, ...) and return the k-th number of that order.
//
// EXAMPLES
//   n = 13, k = 2  ->  10     order: 1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9
//   n = 1,  k = 1  ->  1
//
// CONSTRAINTS
//   1 <= k <= n <= 10^9
//
// INTUITION
//   Dictionary order is a preorder walk of a 10-ary "prefix tree": 1 has children 10..19,
//   10 has children 100..109, and so on (only values <= n exist). n can be 10^9, so the
//   numbers cannot be listed - but we CAN count how many numbers start with a prefix in
//   O(log n): per level the prefix covers [prefix, prefix + 1), scaled by 10 each level
//   and clipped at n + 1. With that count, whole subtrees can be skipped:
//     - the k-th number lies after this prefix's subtree -> jump to the next sibling
//     - otherwise it lies inside                          -> step down to the first child
//
// APPROACHES IN THIS FILE
//   A. findKthNumber() + count() - prefix counting as above   <- main() runs this one
//   B. dfsTraversal()            - brute-force DFS in dictionary order that collects the
//                                  first k numbers; O(k), fine only for small k (unused)
//
// APPROACH (as implemented below, method A)
//   1. curr = 1 (current prefix) and i = 1 (curr's position in the order).
//   2. While i < k:
//        steps = count(curr, n)  -> how many numbers start with curr
//        i + steps <= k          -> skip that subtree: curr = curr + 1, i += steps
//        otherwise               -> go inside: curr = curr * 10, i++
//                                   (curr * 10 is the very next number in the order)
//   3. Return curr.
//   count(curr, n): per level add min(neg, n + 1) - curr, where neg = curr + 1 at the
//   start; then curr *= 10 and neg *= 10, while curr <= n.
//
// DRY RUN   n = 13, k = 2
//   curr = 1, i = 1: count(1) = 1 ("1") + 4 (10..13) = 5; 1 + 5 > 2 -> go inside:
//                    curr = 10, i = 2
//   i == k -> return 10
//
// COMPLEXITY
//   Time : O(log^2 n) - at most ~10 moves per level over ~10 levels, each count() O(log n)
//   Space: O(1)
//
// GOTCHAS
//   - curr * 10 can pass Integer.MAX_VALUE when n is near 10^9, so the counting variables
//     must be long (see STATUS).
//   - When stepping down, i grows by only 1: curr * 10 itself is the next number.
//
// STATUS
//   Known issue: count() keeps curr as an int, so curr *= 10 overflows once curr passes
//   214748364 (possible whenever n >= 214748365). The negative curr makes the count wrong
//   (count(3, 681692778) returns 4163638536 instead of 111111111) and the walk goes
//   astray: main()'s input (n = 681692778, k = 351251360) never finishes (stopped after
//   10 s); the correct answer is 416126219. Smaller n, such as the examples or
//   n = 2 * 10^8, work. The code is left unchanged on purpose.
// ============================================================================
public class LC440KThSmallestInLexicographicalOrder {
    public static void main(String[] args) {
        // correct answer: 416126219 - but this run never finishes (int overflow, see STATUS)
        int n = 681692778;
        int k = 351251360;
        System.out.println(findKthNumber(n, k));
    }

    // Approach A: walk the implicit prefix tree, skipping whole subtrees with count()
    public static int findKthNumber(int n, int k) {

        // the prefix "1" is the 1st number in dictionary order
        int curr = 1;
        long i = 1;
        while (i < k){
          // how many numbers in [1, n] start with curr
          long steps = count(curr, n);
          if(i + steps <= k){
              // the whole subtree comes before the k-th number -> next sibling
              curr = curr + 1;
              i += steps;
          }else {
              // the k-th number is inside -> its first child is the next number
              curr = curr * 10;
              i++;
          }
        }
        return curr;
    }
    // numbers in [1, n] whose decimal form starts with curr, counted level by level
    public static long count(int curr, int n){
        long res = 0;
        // first prefix after curr on the same level (curr + 1, scaled up each level)
        long neg = curr + 1;
        while (curr <= n){
            // this level holds curr .. neg - 1, but never more than up to n
            res += Math.min(neg, n + 1) - curr;
            // int: overflows once curr passes 214748364 (see STATUS)
            curr *= 10;
            neg *= 10;
        }
        return res;
    }
    // Approach B (unused): preorder DFS in dictionary order, collecting the first k numbers
    public static void dfsTraversal(List<Integer> result, int i, int n, int k){
        if(i > n){
            return;
        }
        if(result.size() >= k) {
            return;
        }
        result.add(i);
        for (int digit = 0; digit <= 9; digit++) {
            int curr = i * 10 + digit;
            if (curr > n) {
                break;
            }
            dfsTraversal(result, curr, n, k);
        }
    }



}
