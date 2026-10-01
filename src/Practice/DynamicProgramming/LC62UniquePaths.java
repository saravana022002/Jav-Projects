package Practice.DynamicProgramming;

import java.util.Arrays;

// ============================================================================
// LC 62. Unique Paths   [Medium]
// https://leetcode.com/problems/unique-paths/
// Topics: Math, Dynamic Programming, Combinatorics
// ============================================================================
//
// PROBLEM
//   A robot stands on the top-left cell of an m x n grid and wants to reach the bottom-right
//   cell. Every move is one step right or one step down - never up or left. Return how many
//   different routes it can take.
//
// EXAMPLES
//   m = 3, n = 7  ->  28
//   m = 3, n = 2  ->  3     Right-Down-Down, Down-Down-Right, Down-Right-Down
//
// CONSTRAINTS
//   1 <= m, n <= 100
//   The test cases are generated so that the answer is at most 2 * 10^9 (it fits in an int)
//
// INTUITION
//   Method A (recursion + memo): from a cell the robot can only go right or down, so
//       paths(cell) = paths(cell to the right) + paths(cell below)
//   Standing on the goal counts as 1 way, stepping off the grid counts as 0. Lots of
//   different routes pass through the same cell, so each cell's count is cached once known.
//   Method B (counting): every route makes exactly m-1 moves down and n-1 moves right,
//   m+n-2 moves in total, and a route is fixed by WHICH of those moves are the rights.
//   So the answer is "choose n-1 places out of m+n-2" = C(m+n-2, n-1).
//
// APPROACHES IN THIS FILE
//   A. uniquePaths(i, j, m, n, ans) - recursion from cell (i, j) with a memo table
//                                     - O(m*n) time, O(m*n) space
//   B. uniquePaths(m, n)            - the formula C(m+n-2, n-1) built up in a loop
//                                     - O(n) time, O(1) space
//   main() calls both, and prints System.currentTimeMillis() before and after to time them.
//
// APPROACH (as implemented below)
//   Method A - uniquePaths(i, j, m, n, ans); ans[i][j] == -1 means "not computed yet"
//   1. If (i, j) is inside the grid and ans[i][j] != -1, return the cached value.
//   2. If (i, j) is the goal (m-1, n-1): ans[i][j] = 1 and return 1.
//   3. If j >= n or i >= m the robot has left the grid: return 0.
//   4. Otherwise ans[i][j] = uniquePaths(i, j+1, ...) + uniquePaths(i+1, j, ...), i.e.
//      right + down. The first call is uniquePaths(0, 0, m, n, ans).
//   Method B - uniquePaths(m, n)
//   1. N = m + n - 2 is the number of moves, r = n - 1 of them go right.
//   2. res = 1, then for i = 1..r:  res = res * (N - r + i) / i.
//      After step i, res is C(N - r + i, i), so after step r it is C(N, r). Multiplying
//      first and dividing second keeps every step an exact integer division.
//   3. Return (int) res.
//
// DRY RUN   method A, m = 3, n = 2 (3 rows, 2 columns), ans starts all -1
//   The recursion dives (0,0) -> (0,1) -> (1,1) -> (2,1), which is the goal (1 way). Calls
//   that step off the grid - (0,2), (1,2) and (3,0) - return 0. The cells get their value
//   on the way back, in this order:
//     ans[2][1] = 1                                  the goal
//     ans[1][1] = ans[1][2] + ans[2][1] = 0 + 1 = 1
//     ans[0][1] = ans[0][2] + ans[1][1] = 0 + 1 = 1
//     ans[2][0] = ans[2][1] + ans[3][0] = 1 + 0 = 1
//     ans[1][0] = ans[1][1] + ans[2][0] = 1 + 1 = 2     (ans[1][1] comes from the cache)
//     ans[0][0] = ans[0][1] + ans[1][0] = 1 + 2 = 3
//   final table   [3, 1]
//                 [2, 1]     answer = ans[0][0] = 3, as in example 2
//                 [1, 1]
//   Method B, m = 3, n = 7: N = 8, r = 6, so each factor is (2 + i)
//     i=1: 1*3/1 = 3     i=2: 3*4/2 = 6     i=3: 6*5/3 = 10
//     i=4: 10*6/4 = 15   i=5: 15*7/5 = 21   i=6: 21*8/6 = 28      -> 28, as in example 1
//
// STATUS
//   main() uses m = 1, n = 100: a single row, so the only route is straight right and both
//   methods print 1. The two timestamp lines change on every run.
//
// COMPLEXITY
//   Method A  Time : O(m*n) - each cell is computed once, later visits hit the cache, and
//                    one computation is O(1)
//             Space: O(m*n) for ans, plus a recursion depth of about m+n
//   Method B  Time : O(n) - the loop runs n-1 times (r = min(m, n) - 1 would make it
//                    O(min(m, n)), but the code always uses r = n - 1)
//             Space: O(1)
//
// GOTCHAS
//   - Method B must multiply BEFORE it divides. res * (N - r + i) is exactly i times the
//     new value, so the division is exact; dividing first would truncate.
//   - res is a long because that product can be far above the int range even though the
//     final answer fits in an int.
//   - In method A the guard i < m && j < n in front of ans[i][j] keeps off-grid calls from
//     indexing outside the array; they just fall through to "return 0".
//   - Beyond the guarantee (for example 100 x 100, about 2.3 * 10^58 routes) both overflow.
//
// RELATED
//   - LC 63 Unique Paths II - the same walk with obstacle cells; the recursion copes by
//     returning 0 on an obstacle, but the C(m+n-2, n-1) formula no longer applies.
//   - LC 64 Minimum Path Sum - the same right/down grid, but it minimises a cost instead
//     of counting routes.
//   - Practice/Backtracking/GFGRatInAMazeProblemI.java in this repo - a walk from the
//     top-left to the bottom-right with walls, where every path is listed.
// ============================================================================
public class LC62UniquePaths {

    public static void main(String[] args) {

        // m = 1 is a single row, so there is exactly one route: both methods print 1
        int m = 1;
        int n = 100;


        // memo table for method A: -1 means "not computed yet"
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], -1);
        }
        // timestamps (ms) before and after, only to time the two calls
        System.out.println(System.currentTimeMillis());
        // method A from the start cell (0,0)
        System.out.println(uniquePaths(0,0, m, n, ans));
        // method B, the formula
        System.out.println(uniquePaths(m, n));
        System.out.println(System.currentTimeMillis());
    }




    // Approach A: routes from (i, j) to the goal = routes via the right neighbour + routes
    // via the cell below, cached in ans. O(m*n)
    public static int uniquePaths(int i, int j, int m, int n, int[][] ans) {
        // already solved (the i < m && j < n guard keeps the ans[i][j] read inside the array)
        if(i < m && j < n && ans[i][j] != -1){
            return ans[i][j];
        }
        // the goal cell: one way to be there
        if(i == (m - 1) && j == (n - 1)){
            ans[i][j] = 1;
            return 1;
        }
        // stepped off the grid (past the last column or row): no route this way
        if(j >= n || i  >= m){
            return 0;
        }else {
            // one more step right + one more step down
            ans[i][j] = uniquePaths(i, j + 1, m, n, ans) + uniquePaths(i + 1, j, m, n, ans);
            return ans[i][j];
        }
    }

    // Approach B: a route is m-1 downs and n-1 rights in some order, so the answer is
    // C(m+n-2, n-1), computed without factorials. O(n) time, O(1) space
    public static int uniquePaths(int m, int n) {
        // N = total moves, r = how many of them go right
        int N = m + n - 2;
        int r = n - 1;
        long res = 1;
        // after step i, res = C(N - r + i, i); multiply first, divide second (exact)
        for (int i = 1; i <= r; i++) {
            res = res * (N - r + i) / i;
        }
        return (int) res;
    }

}
