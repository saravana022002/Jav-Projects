package Practice.Greedy;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 3796. Find Maximum Value in a Constrained Sequence   [Medium]
// https://leetcode.com/problems/find-maximum-value-in-a-constrained-sequence/
// Topics: Array, Greedy
// ============================================================================
//
// PROBLEM
//   Build a sequence a[0..n-1] of non-negative integers with a[0] = 0, where neighbours differ
//   by at most diff[i]: |a[i] - a[i+1]| <= diff[i]. Each restriction [idx, maxVal] also caps
//   a[idx] <= maxVal. Among all valid sequences pick one whose LARGEST element is as big as
//   possible, and return that largest element.
//
// EXAMPLES
//   n = 10, restrictions = [[3,1],[8,1]], diff = [2,2,3,1,4,5,1,1,2]  ->  6
//        e.g. a = [0,2,4,1,2,6,2,1,1,3]   (main() runs this one)
//   n = 8, restrictions = [[3,2]], diff = [3,5,2,4,2,3,1]  ->  12
//        e.g. a = [0,3,3,2,6,8,11,12]
//
// CONSTRAINTS
//   2 <= n <= 10^5,   diff.length == n - 1,   1 <= diff[i] <= 10
//   1 <= restrictions.length <= n - 1,  restrictions[i] = [idx, maxVal],
//   1 <= idx < n (all idx values are different),   1 <= maxVal <= 10^6
//
// INTUITION   (how the problem is solved - the code below does NOT do this, see STATUS)
//   Give every position an upper bound ub[i], the largest value it can ever hold. A bound can
//   come from the left end (a[0] = 0, so ub[i] <= diff[0] + ... + diff[i-1]), from the
//   position's own restriction, or from a restriction somewhere else: a[j] <= maxVal forces
//   a[i] <= maxVal + (sum of the diffs between i and j), on either side of j. Two sweeps
//   collect all of these:
//       forward : ub[i] = min(ub[i], ub[i-1] + diff[i-1])
//       backward: ub[i] = min(ub[i], ub[i+1] + diff[i])
//   starting from ub[0] = 0, ub[idx] = maxVal and everything else "infinite". Taking a[i] = ub[i]
//   is then a legal sequence, so the answer is the largest ub[i]. For example 1:
//       after the forward sweep   ub = [0,2,4,1,2,6,11,12,1,3]
//       after the backward sweep  ub = [0,2,4,1,2,6,3,2,1,3]   -> answer 6.
//
// APPROACH (as implemented below - an attempt that does not solve the problem)
//   1. a = new int[n] with a[0] = 0, and the restrictions stored in the map `restrict`
//      (idx -> maxVal).
//   2. One forward fill for i = 1 .. n-2: if i is restricted, a[i] = its maxVal; otherwise
//      a[i] = |a[i-1] + diff[i-1]|, i.e. climb by the full diff.
//   3. Return the largest value in a.
//
// DRY RUN   example 1 (what main() runs)
//   i=1: 0+2 = 2    i=2: 2+2 = 4    i=3: restricted -> 1    i=4: 1+1 = 2    i=5: 2+4 = 6
//   i=6: 6+5 = 11   i=7: 11+1 = 12  i=8: restricted -> 1    i=9 is never visited, stays 0
//   a = [0,2,4,1,2,6,11,12,1,0]  -> returns 12, but the answer is 6: a[8] <= 1 forces
//   a[7] <= 2 and a[6] <= 3, which the forward fill never notices.
//
// COMPLEXITY   (of this code)
//   Time : O(n + r) - r = restrictions.length; one pass plus filling the map.
//   Space: O(n + r) - the array a and the map.
//   (The two-sweep solution above is O(n + r) time and O(n) space too.)
//
// GOTCHAS   (the mistakes in this attempt)
//   - A restriction is a cap ("<="), not an assignment; here a[idx] is set to maxVal even
//     when the climb from the left could not reach that high (the smaller value is the
//     legal one).
//   - A restriction must also pull down the positions to its LEFT; there is no backward sweep.
//   - The loop stops at i < n - 1, so the last element is never filled and stays 0.
//   - Math.abs() does nothing useful: all values here are non-negative.
//
// STATUS
//   Known issue: findMaxVal() returns wrong answers. Restrictions are assigned instead of
//   used as caps, they are never propagated backwards, and a[n-1] is never filled.
//     example 1 -> returns 12 (correct answer 6)
//     example 2 -> returns 11 (correct answer 12)
//     n = 2, restrictions = [[1,5]], diff = [3] -> returns 0 (correct answer 3)
//   Also, main() calls findMaxVal() but does not print the result. The code is left
//   unchanged on purpose.
//
// RELATED
//   - LC 1840 Maximum Building Height - the same idea: heights with caps and a step limit,
//     solved with one forward and one backward clamping sweep.
// ============================================================================
public class LC3796FindMaximumValueInAConstrainedSequence {
    public static void main(String[] args) {
        // Example 1: the correct answer is 6, but this code computes 12 (see STATUS);
        // the returned value is not printed.
        int n = 10;
        int[][] restrictions = {{3,1},{8,1}};
        int[] diff = {2,2,3,1,4,5,1,1,2};
        findMaxVal(n, restrictions, diff);
    }
    // Attempt: one left-to-right fill, then the maximum. Does not give correct answers.
    public static int findMaxVal(int n, int[][] restrictions, int[] diff) {
        int[] a = new int[n];
        a[0] = 0;
        int max = Integer.MIN_VALUE;
        // idx -> maxVal for every restricted position
        Map<Integer, Integer> restrict = new HashMap<>();
        for (int[] restriction : restrictions) {
            restrict.put(restriction[0], restriction[1]);
        }
        // forward fill (note: stops before the last index, and never lowers earlier values)
        for (int i = 1; i < n - 1; i++) {
            if(!restrict.containsKey(i)){
                // climb by the whole diff
                a[i] = Math.abs(a[i - 1] + diff[i - 1]);
            }else {
                // restricted: takes the restriction value as is
                a[i] = restrict.get(i);
            }
        }
        // largest value in the filled array
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }
}
