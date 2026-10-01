package Practice.Matrix;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ============================================================================
// LC 2257. Count Unguarded Cells in the Grid   [Medium]
// https://leetcode.com/problems/count-unguarded-cells-in-the-grid/
// Topics: Array, Matrix, Simulation
// ============================================================================
//
// PROBLEM
//   An m x n grid holds some guards and some walls (their cells are given). A guard sees
//   along its row and its column, in all four directions, until something is in the way: a
//   wall or another guard. A cell is guarded when at least one guard sees it. Count the free
//   cells - those holding neither a guard nor a wall - that no guard sees.
//
// EXAMPLES
//   m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]  ->  7
//   m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]        ->  4
//       (the single guard is boxed in by four walls and sees nothing, so the four corner
//       cells stay unguarded)
//
// CONSTRAINTS
//   1 <= m, n <= 10^5,   2 <= m * n <= 10^5,
//   1 <= guards.length, walls.length <= 5 * 10^4,   2 <= guards.length + walls.length <= m * n,
//   all positions in guards and walls are distinct
//
// INTUITION
//   Paint the grid, then let every guard shoot four rays. A ray paints each free cell it
//   passes as "guarded" and stops at the first wall, guard or grid edge. A ray also stops at
//   another guard, so in one direction a cell can be reached by at most one ray (the nearest
//   guard before it with nothing in between): all rays together cost about 4 * m * n steps.
//   What is left unpainted is the answer.
//
// APPROACH (as implemented below)
//   1. prison = new int[m][n] with codes: 0 free, 1 wall, 2 guard, 3 free but guarded.
//   2. Put every guard (2) and every wall (1) on the grid BEFORE any ray is shot.
//   3. dirs = {down, right, up, left}. For each guard g and each d in dirs, start at the
//      neighbour (x, y) = g + d. While (x, y) is inside the grid and is neither a wall nor a
//      guard: if the cell is 0 make it 3, then step on (x += d[0], y += d[1]).
//   4. Count the cells that are still 0 and return that count.
//
// DRY RUN   example 1 (m = 4, n = 6) after all rays   (G guard, # wall, + guarded, . free)
//   row 0:  G # . + . .
//   row 1:  + G + + # .
//   row 2:  + + # G + +
//   row 3:  + + . + . .
//   guard (0,0): sees only downwards, (0,1) is a wall.  guard (1,1): sees left, down, and
//   right as far as the wall (1,4); up is the wall (0,1).  guard (2,3): sees down, right and
//   up; left is the wall (2,2).
//   Cells still 0 (the '.'): (0,2) (0,4) (0,5) (1,5) (3,2) (3,4) (3,5)  -> 7
//
// COMPLEXITY
//   Time : O(m * n) - building and counting the grid are O(m * n); rays cost at most one
//          visit per cell per direction, because every ray stops at walls and other guards
//   Space: O(m * n) - the prison grid
//
// GOTCHAS
//   - Guards and walls must all be on the grid before the first ray is shot, otherwise a ray
//     would run through an obstacle that has not been placed yet.
//   - Rays stop at other guards too, not only at walls. They do NOT stop at cells already
//     painted 3: two guards may see the same cell.
//   - Guards and walls are occupied cells, so they are never counted, guarded or not.
//   - The imports HashSet, Map and Set are not used.
//
// RELATED
//   - LC 999 Available Captures for Rook - also walks in the four directions until something
//     blocks the way.
//   - LC 361 Bomb Enemy - also counts what a blast along a row and a column reaches before
//     a wall.
// ============================================================================
public class LC2257CountUnguardedCellsInTheGrid {
    public static void main(String[] args) {
        // Example 1 - prints 7
        int m = 4;
        int n = 6;
        int[][] guards = {{0,0},{1,1},{2,3}};
        int[][] walls = {{0,1},{2,2},{1,4}};
        System.out.println(countUnguarded(m, n, guards, walls));
    }

    // Returns the number of free cells that no guard can see.
    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // cell codes: 0 free, 1 wall, 2 guard, 3 free but guarded
        int[][] prison = new int[m][n];
        for (int[] guard : guards){
            prison[guard[0]][guard[1]] = 2;
        }

        for (int[] wall : walls){
            prison[wall[0]][wall[1]] = 1;
        }


        // the four directions a guard can look: down, right, up, left
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        // shoot a ray from every guard in every direction
        for (int[] g : guards) {
            for (int[] d : dirs) {
                int x = g[0] + d[0];
                int y = g[1] + d[1];
                // keep going until the edge, a wall or another guard
                while (x >= 0 && x < m && y >= 0 && y < n && prison[x][y] != 1 && prison[x][y] != 2){
                    // mark free cells as guarded (cells already marked 3 are simply passed)
                    if(prison[x][y] == 0)
                        prison[x][y] = 3;
                    x += d[0];
                    y += d[1];
                }
            }
        }

        // whatever is still 0 was never seen
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(prison[i][j] == 0){
                    ans++;
                }
            }
        }

        return ans;
    }








}
