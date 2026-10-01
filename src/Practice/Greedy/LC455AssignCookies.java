package Practice.Greedy;

import java.util.Arrays;

// ============================================================================
// LC 455. Assign Cookies   [Easy]
// https://leetcode.com/problems/assign-cookies/
// Topics: Array, Two Pointers, Greedy, Sorting, Quicksort
// ============================================================================
//
// PROBLEM
//   Child i is content only with a cookie of size at least g[i] (their greed factor); cookie
//   j has size s[j]. Every child gets at most one cookie and every cookie goes to at most one
//   child. Return the largest number of children that can be made content.
//
// EXAMPLES
//   g = [1,2,3], s = [1,1]  ->  1     both cookies are size 1, so only the child with greed 1
//                                     can be satisfied
//   g = [1,2], s = [1,2,3]  ->  2     cookie 1 -> greed 1, cookie 2 -> greed 2 (main() runs
//                                     this one)
//
// CONSTRAINTS
//   1 <= g.length <= 3 * 10^4,   0 <= s.length <= 3 * 10^4,   1 <= g[i], s[j] <= 2^31 - 1
//
// INTUITION
//   Sort both lists and hand out cookies from the smallest one up. The smallest cookie can only
//   please the least greedy child who is still waiting. If even that child is too greedy for
//   it, every other waiting child is at least as greedy, so the cookie is useless - throw it
//   away. If it does satisfy that child, give it: a small cookie is the cheapest way to make
//   this child content, and the larger cookies stay available for the greedier children.
//
// APPROACH (as implemented below)
//   1. Sort g and s.
//   2. i = the least greedy child not yet content, j = the smallest cookie not yet looked at,
//      count = content children so far.
//   3. While both i and j are inside their arrays: if s[j] >= g[i], child i gets the cookie -
//      count++ and i++. Either way j++, because the cookie is now used up or useless.
//   4. Return count.
//
// DRY RUN   g = [1,2,3], s = [1,1]   (example 1; both already sorted)
//   i=0 j=0   s[0]=1 >= g[0]=1  -> content, count = 1, i = 1; j = 1
//   i=1 j=1   s[1]=1 >= g[1]=2? no                               j = 2
//   j == 2 = s.length, stop -> return 1
//   main()'s data g = [1,2], s = [1,2,3]: 1>=1 (count 1), 2>=2 (count 2), i reaches the end -> 2.
//
// COMPLEXITY
//   Time : O(g log g + s log s) - the two sorts; the walk is O(g + s).
//   Space: O(1) extra - both arrays are sorted in place (the sort's own working space aside).
//
// GOTCHAS
//   - j++ runs for every cookie, used or not: a cookie too small for the current child would
//     be too small for all the greedier ones as well.
//   - Arrays.sort reorders the caller's g and s.
//   - s can be empty (length 0): the loop never runs and the answer is 0.
//   - Sizes go up to 2^31 - 1, but the code only compares them, so nothing can overflow.
//
// RELATED
//   - LC 2410 Maximum Matching of Players With Trainers - the same task in different words
//     (the LeetCode statement says the two questions are the same).
// ============================================================================
public class LC455AssignCookies {
    public static void main(String[] args) {
        // Example 2 - both children get a cookie. Expected output: 2
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(findContentChildren(g, s));
    }
    // Returns how many children can be given a big enough cookie.
    public static int findContentChildren(int[] g, int[] s) {
        int count = 0;
        int gSize = g.length;
        int sSize = s.length;
        // sort both so the smallest cookie always meets the least greedy child
        Arrays.sort(g);
        Arrays.sort(s);

        // i walks the children, j walks the cookies
        int i=0;
        int j=0;
        while (i < gSize && j < sSize){
            // the cookie is big enough: this child is content, move to the next child
            if( s[j] >= g[i] ){
                count ++;
                i++;
            }
            // this cookie is used up or too small for everyone left: move to the next one
            j++;
        }
        return count;
    }
}
