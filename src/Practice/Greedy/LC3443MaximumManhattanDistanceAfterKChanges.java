package Practice.Greedy;

// ============================================================================
// LC 3443. Maximum Manhattan Distance After K Changes   [Medium]
// https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/
// Topics: Hash Table, Math, String, Counting
// ============================================================================
//
// PROBLEM
//   s is a walk on an infinite grid starting at (0, 0): 'N' = one step north, 'S' = south,
//   'E' = east, 'W' = west, performed in order. Before walking you may change at most k of
//   the characters into any of the four letters. Return the largest Manhattan distance
//   |x| + |y| from the origin that you can be at at ANY moment of the walk.
//
// EXAMPLES
//   s = "NWSE", k = 1    ->  3     change the 'S' to 'N': "NWNE" reaches (-1, 2) after three steps
//   s = "NSWWEW", k = 3  ->  6     "NNWWWW" (S -> N, E -> W) walks 6 steps away (main() runs this)
//
// CONSTRAINTS
//   1 <= s.length <= 10^5,   0 <= k <= s.length,   s has only 'N', 'S', 'E', 'W'
//
// INTUITION
//   After some prefix the distance is |N - S| + |E - W| (counts of each letter so far). On an
//   axis the minority steps (the S's when N's are ahead, and so on) cancel part of the
//   majority; turning one of them into the majority direction makes that axis difference grow
//   by 2. There are min(N,S) such steps on the vertical axis and min(E,W) on the horizontal
//   one, and a single change can never gain more than 2 (it swaps one unit step for another,
//   which moves the end point by at most 2). So for a prefix the best distance is
//       |N - S| + |E - W| + 2 * min(k, min(N,S) + min(E,W)).
//   The walk is followed in order but only the best moment counts, and the changes for that
//   moment can be chosen freely inside its own prefix - so evaluate every prefix and keep the
//   maximum.
//
// APPROACH (as implemented below)
//   1. Four counters north, south, east, west for the prefix read so far; ans = 0.
//   2. For each character, increase its counter.
//   3. times1 = flips spent on the N/S axis = min(min(north, south), k);
//      times2 = flips left for the E/W axis = min(min(west, east), k - times1).
//   4. The best distance for this prefix is count(north, south, times1) +
//      count(east, west, times2), where count(a, b, t) = |a - b| + 2 * t.
//   5. ans = max(ans, that value). Return ans after the last character.
//
// DRY RUN   s = "NSWWEW", k = 3   (main() prints 6)
//   times1 = min(min(N,S), 3);   times2 = min(min(W,E), 3 - times1)
//   N/S part = |N - S| + 2 * times1;   E/W part = |E - W| + 2 * times2
//   prefix   counts           times1 times2   N/S part + E/W part   ans
//   N        N1 S0 E0 W0        0      0       1 + 0 = 1             1
//   NS       N1 S1 E0 W0        1      0       2 + 0 = 2             2
//   NSW      N1 S1 E0 W1        1      0       2 + 1 = 3             3
//   NSWW     N1 S1 E0 W2        1      0       2 + 2 = 4             4
//   NSWWE    N1 S1 E1 W2        1      1       2 + 3 = 5             5
//   NSWWEW   N1 S1 E1 W3        1      1       2 + 4 = 6             6
//
// COMPLEXITY
//   Time : O(n) - one pass, constant work per character
//   Space: O(1) - five integers
//
// GOTCHAS
//   - The changes are re-chosen for every prefix; they do not have to be one consistent set
//     across the whole walk, because only the single best moment is reported.
//   - The k flips are shared by the two axes: the vertical axis takes what it can use first,
//     the horizontal axis gets the rest (k - times1). Every flip is worth +2 on either axis,
//     so the order does not matter.
//   - Once every minority step is flipped, extra flips are useless, hence the min() caps.
//   - ans starts at 0, the distance at the origin.
//
// RELATED
//   - LC 3968 Maximum Manhattan Distance After All Moves - listed by LeetCode as similar.
// ============================================================================
public class LC3443MaximumManhattanDistanceAfterKChanges {
    public static void main(String[] args) {
        // Example 2 - expected output: 6
        System.out.println(maxDistance("NSWWEW", 3));
    }
    // Returns the largest Manhattan distance reachable at any moment with up to k changes.
    public static int maxDistance(String s, int k) {
        int north = 0;
        int south = 0;
        int east = 0;
        int west = 0;
        int ans = 0;
        int times1;
        int times2;
        for (char it : s.toCharArray()){
            // count the letters of the prefix read so far
            switch (it){
                case 'N':
                    north ++;
                    break;
                case 'S':
                    south ++;
                    break;
                case 'W':
                    west ++;
                    break;
                case 'E':
                    east ++;
                    break;
            }
            // flips for the N/S axis, then what is left of k for the E/W axis
            times1 = Math.min(Math.min(north, south), k);
            times2 = Math.min(Math.min(west, east), k - times1);
            // best distance after this prefix; keep the maximum over all prefixes
            ans = Math.max(ans, count(north, south, times1) + count(east, west, times2));
        }
        return ans;
    }
    // Distance along one axis: the gap between the two directions, plus 2 for each flip
    // (a flip turns a minority step into a majority step).
    public static int count(int dir1, int dir2, int times){
        return Math.abs(dir1 - dir2) + times * 2;
    }
}
