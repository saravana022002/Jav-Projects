package Practice.Graph;

import java.util.LinkedList;
import java.util.Queue;

// ============================================================================
// LC 994. Rotting Oranges   [Medium]
// https://leetcode.com/problems/rotting-oranges/
// Topics: Array, Breadth-First Search, Matrix
// ============================================================================
//
// PROBLEM
//   Each cell of an m x n grid is 0 (empty), 1 (fresh orange) or 2 (rotten orange).
//   Every minute, each fresh orange that touches a rotten one up, down, left or right
//   (not diagonally) turns rotten. Return how many minutes pass until no fresh orange is
//   left, or -1 if some fresh orange can never be reached.
//
// EXAMPLES
//   grid = [[2,1,1],[1,1,0],[0,1,1]]  ->  4    the rot needs 4 waves to reach (2,2)
//   grid = [[2,1,1],[0,1,1],[1,0,1]]  ->  -1   (2,0) is boxed in by empty cells; its
//                                               diagonal contact does not count
//   grid = [[0,2]]                    ->  0    nothing is fresh at minute 0
//
// CONSTRAINTS
//   m == grid.length,   n == grid[i].length,   1 <= m, n <= 10
//   grid[i][j] is 0, 1, or 2
//
// INTUITION
//   All rotten oranges spread at the same time, like ripples from several stones. Start
//   a BFS with EVERY rotten orange in the queue at minute 0 (multi-source BFS). BFS
//   reaches cells in order of their distance to the nearest rotten orange, and that
//   distance is exactly the minute the cell rots. So the answer is the largest minute
//   seen, unless some fresh orange is never reached (-1).
//
// APPROACH (as implemented below)
//   1. Scan the grid: each rotten cell goes into queue as Pair(i, j, 0) and is marked
//      vis = 2; count the fresh cells in cntFresh.
//   2. Poll a Pair and update tm = max(tm, sec).
//   3. For each neighbour (top, left, bottom, right) that is inside the grid, not yet
//      marked in vis and fresh in grid: enqueue it with sec + 1, mark vis, cnt++.
//   4. After the BFS: cnt != cntFresh means an orange was never reached -> -1, else tm.
//
// DRY RUN   grid from main()   2 1 1 / 1 0 0 / 0 0 1        cntFresh = 4
//   start            queue [(0,0,0)]
//   poll (0,0,0)  tm 0   bottom (1,0) and right (0,1) rot  -> sec 1, cnt 2
//   poll (1,0,1)  tm 1   neighbours are rotten, empty or outside
//   poll (0,1,1)  tm 1   right (0,2) rots                  -> sec 2, cnt 3
//   poll (0,2,2)  tm 2   nothing new
//   queue empty: cnt 3 != cntFresh 4 because (2,2) is cut off by empty cells -> -1
//
// STATUS
//   main() calls orangesRotting() but does not print the result (it returns -1).
//
// COMPLEXITY
//   Time : O(n * m) - every cell enters the queue at most once and checks 4 neighbours.
//   Space: O(n * m) - the vis matrix and the queue.
//
// GOTCHAS
//   - Mark a cell when it is ENQUEUED, not when it is polled; otherwise two rotten
//     neighbours could both add it and cnt would count it twice.
//   - No fresh oranges -> 0, even if there is no rotten orange either.
//   - In this code n is the number of ROWS and m the number of columns (the opposite of
//     the problem's m x n naming).
//   - The input grid is never modified; vis records what has rotted.
//
// RELATED
//   - LC 542 01 Matrix - the same multi-source BFS: distance of every cell to the
//     nearest 0.
//   - LC 286 Walls and Gates - multi-source BFS that starts from all gates at once.
// ============================================================================
public class LC994RottingOranges {
    public static void main(String[] args) {
        // (2,2) is fresh but walled off by empty cells, so the expected answer is -1.
        // The return value is not printed, so the program prints nothing (see STATUS).
        int [][] grid = {{2,1,1},{1,0,0},{0,0,1}};
        orangesRotting(grid);
    }

    // a queued cell plus the minute it turned rotten
    static class Pair{
        int row;
        int col;
        int sec;
        public Pair(int row, int col, int sec){
            this.row = row;
            this.col = col;
            this.sec = sec;
        }
    }
    // Minutes until nothing is fresh, or -1 if a fresh orange can never rot.
    public static int orangesRotting(int[][] grid) {
        Queue<Pair> queue = new LinkedList<>();

        int n = grid.length;     // rows
        int m = grid[0].length;  // columns


        // step 1: all rotten oranges start the BFS together at minute 0
        int[][] vis = new int[n][m];
        int cntFresh = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 2){
                    queue.add(new Pair(i, j, 0));
                    vis[i][j] = 2;
                }else {
                    vis[i][j] = 0;
                }
                if(grid[i][j] == 1){
                    cntFresh ++;
                }
            }
        }

        int tm = 0;   // latest minute seen so far
        int cnt = 0;  // fresh oranges that the BFS turned rotten
        while (!queue.isEmpty()){
            // step 2: take the next cell in minute order
            int r = queue.peek().row;
            int c = queue.peek().col;
            int sec = queue.peek().sec;
            tm = Math.max(tm, sec);
            queue.poll();
            // step 3: each fresh, unmarked neighbour rots one minute later
            // top
            if (r - 1 >= 0 && vis[r - 1][c] != 2 && grid[r - 1][c] == 1){
                queue.add(new Pair(r - 1, c, sec + 1));
                vis[r - 1][c] = 2;
                cnt++;
            }
            // left
            if (c - 1 >= 0 && vis[r][c - 1] != 2 && grid[r][c - 1] == 1){
                queue.add(new Pair(r, c - 1, sec + 1));
                vis[r][c - 1] = 2;
                cnt++;
            }
            // bottom
            if (r + 1 <= n - 1 && vis[r + 1][c] != 2 && grid[r + 1][c] == 1){
                queue.add(new Pair(r + 1, c, sec + 1));
                vis[r + 1][c] = 2;
                cnt++;
            }
            // right
            if (c + 1 <= m - 1 && vis[r][c + 1] != 2 && grid[r][c + 1] == 1){
                queue.add(new Pair(r, c + 1, sec + 1));
                vis[r][c + 1] = 2;
                cnt++;
            }
        }

        // step 4: some fresh orange was never reached -> impossible
        if(cnt != cntFresh){
            return -1;
        }
        return tm;
    }
}
