package Practice.Recursion;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 386. Lexicographical Numbers   [Medium]
// https://leetcode.com/problems/lexicographical-numbers/
// Topics: Depth-First Search, Trie
// ============================================================================
//
// PROBLEM
//   Given n, return the numbers 1 .. n sorted in lexicographical (dictionary) order, i.e.
//   compared as strings of digits: "10" comes before "2" because '1' < '2'.
//   The statement asks for O(n) time and O(1) extra space.
//
// EXAMPLES
//   n = 13  ->  [1,10,11,12,13,2,3,4,5,6,7,8,9]
//   n = 2   ->  [1,2]
//
// CONSTRAINTS
//   1 <= n <= 5 * 10^4
//
// INTUITION
//   Picture all numbers as a tree: the roots are 1 .. 9, and a number X has the children
//   X0, X1, ..., X9 (X with one more digit appended). Dictionary order is exactly the
//   pre-order walk of this tree: X comes before every number that starts with X (its
//   descendants), and siblings are visited in increasing digit order. So no sorting is
//   needed - walk the tree depth-first, write a number down BEFORE its children, and do not
//   go into numbers larger than n. Children grow with the appended digit, so once one child
//   is too big, all the following digits are too big as well and the loop can stop.
//
// APPROACH (as implemented below)
//   1. lexicalOrder(n) creates the result list and calls dfs(result, i, n) for every first
//      digit i = 1 .. 9, in that order, then returns the list.
//   2. dfs(result, curr, num):
//        a. curr > num -> return (outside the range).
//        b. result.add(curr)                     visit the number before its children
//        c. for digit = 0 .. 9: next = curr * 10 + digit;
//           if next > num -> return (the larger digits would be too big too);
//           otherwise dfs(result, next, num).
//
// DRY RUN   n = 13
//   dfs(1)   add 1;   children: 10 -> dfs(10): add 10 (its children 100.. exceed 13)
//                               11 -> add 11;  12 -> add 12;  13 -> add 13;
//                               14 > 13 -> stop
//   dfs(2)   add 2    (20 > 13, no children)
//   dfs(3) .. dfs(9)  add 3, 4, 5, 6, 7, 8, 9
//   result = [1,10,11,12,13,2,3,4,5,6,7,8,9]
//
// STATUS
//   main() calls lexicalOrder(113) but does not print the result (it starts
//   1, 10, 100, 101, ... 109, 11, 110, ...).
//   The statement asks for O(1) extra space: the recursion here needs one frame per digit of
//   n (O(log n), at most 5 or 6 frames for n <= 5 * 10^4), so it is not literally O(1).
//
// COMPLEXITY
//   Time : O(n) - every number is added exactly once, and each call does a constant number
//          of extra checks besides its recursive calls
//   Space: O(log n) - recursion depth = number of digits of n (the result list is the output
//          and is not counted)
//
// GOTCHAS
//   - The order is string order, but the comparisons in the code are numeric (curr > num):
//     the tree shape produces the dictionary order on its own.
//   - The early return inside the digit loop is a cut-off of the loop, not of the whole
//     search: the caller simply continues with its own next digit.
//   - The outer loop starts at 1, not 0: 0 is not in the range, and no number starts with a
//     leading 0.
//   - curr * 10 stays far below the int limit here (at most 5 * 10^5).
//   - O(1)-space idea without recursion: from curr, go down to curr * 10 when that is <= n;
//     otherwise step sideways to curr + 1, first climbing up (curr / 10) while curr ends in
//     9 or curr + 1 would exceed n. Repeat n times.
//
// RELATED
//   - LC 440 K-th Smallest in Lexicographical Order - the same digit tree, but it counts the
//     size of subtrees to jump straight to the k-th number
//     (LC440KThSmallestInLexicographicalOrder.java in src/Practice/Math).
// ============================================================================
public class LC386LexicographicalNumbers {
    public static void main(String[] args) {
        // the result is not printed (see STATUS)
        lexicalOrder(113);
    }
    // Returns 1 .. n in lexicographical order.
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        // the roots of the digit tree: every number's first digit is 1 .. 9
        for (int i = 1; i <= 9; i++) {
            dfs(result, i, n);
        }
        return result;
    }

    // Pre-order walk of the digit tree below curr: adds curr, then everything that extends it.
    public static void dfs(List<Integer> result, int curr, int num){
        // beyond the range
        if(curr > num){
            return;
        }
        // a number comes before all numbers that start with it
        result.add(curr);
        // children: curr with one more digit appended, in digit order
        for (int digit = 0; digit <= 9; digit++) {
            int next = curr * 10 + digit;
            // the next digits would only make it bigger: stop this loop
            if(next > num){
                return;
            }
            dfs(result, next, num);
        }
    }
}
