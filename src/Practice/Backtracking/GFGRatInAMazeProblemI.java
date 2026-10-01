package Practice.Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// ============================================================================
// GFG - Rat in a Maze   [Medium]
// https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
// Topics: Backtracking, Recursion, Matrix (grid DFS)
// ============================================================================
//
// PROBLEM
//   An n x n grid holds 1 (open cell) and 0 (wall). A rat starts on the top-left cell (0,0)
//   and must reach the bottom-right cell (n-1,n-1). Each step goes one cell Up, Down, Left
//   or Right, only onto open cells, and one path may not visit the same cell twice.
//   Return EVERY such path as a string of move letters 'U','D','L','R', listed in
//   lexicographically increasing order. No path at all (for example a walled-off
//   destination) -> return an empty list.
//
// EXAMPLES
//   maze = {{1,0,0,0},{1,1,0,1},{1,1,0,0},{0,1,1,1}}  ->  ["DDRDRR", "DRDDRR"]
//       the open cells allow exactly two routes to (3,3); "DD..." sorts before "DR..."
//   maze = [[1,0],[1,0]]  ->  []      the destination (1,1) is a wall, so no path exists
//
// CONSTRAINTS
//   2 <= n <= 5,   0 <= maze[i][j] <= 1
//
// INTUITION
//   Build the path one move at a time. From the current cell try all four directions; a
//   move that leaves the grid, hits a wall or steps on a cell already on THIS path kills
//   that branch. Standing on the destination means the moves so far are one answer.
//   When a cell has been fully explored it is taken off the path again (un-visited), so a
//   different path may still pass through it later. That "choose, explore, undo" cycle is
//   backtracking, and it lists every simple path exactly once.
//
// APPROACH (as implemented below)
//   1. mazeSolver(i, j, ...) is called for the cell the rat has just stepped onto.
//      str = the moves taken so far, visited = cells on the current path as "i,j" keys.
//   2. If (i, j) is the destination, join str into a String, add it to ans, return.
//   3. If (i, j) is outside the grid, a wall (m[i][j] == 0) or already visited, return.
//   4. Add (i, j) to visited.
//   5. For each move in the order R, D, L, U: append the letter to str, recurse into that
//      neighbour, then remove the letter again.
//   6. Remove (i, j) from visited so other paths can use it.
//
// DRY RUN   main()'s maze   (rows: 1000 / 1101 / 1100 / 0111)
//   (0,0)  R -> (0,1) wall.   D -> (1,0)
//   (1,0)  R -> (1,1) -> D (2,1) -> D (3,1) -> R (3,2) -> R (3,3)    found "DRDDRR"
//          every other branch below (1,1) meets a wall, the edge or a visited cell;
//          on the way back (1,1), (2,1), (3,1), (3,2) are un-visited again
//   (1,0)  D -> (2,0) -> R (2,1) -> D (3,1) -> R (3,2) -> R (3,3)    found "DDRDRR"
//   ans = [DRDDRR, DDRDRR]   - R is tried before D at (1,0), so the list is not sorted
//
// STATUS
//   Known issue 1: moves are tried R, D, L, U, so the paths come out in that order, not in
//   the lexicographic order the problem asks for (D < L < R < U). main() prints
//   [DRDDRR, DDRDRR]; the expected answer is [DDRDRR, DRDDRR].
//   Known issue 2: the destination test (step 2) runs before the wall test (step 3), so a
//   blocked destination still yields paths: [[1,0],[1,0]] returns [DR] instead of [].
//   The code is left unchanged on purpose.
//   Signature differs from GFG: the caller creates ans / str / visited and starts the
//   search with mazeSolver(0, 0, N, m, ...).
//
// COMPLEXITY
//   Time : O(3^(n*n)) worst case - a path is at most n*n cells long and, apart from the
//          start, each cell has at most 3 unexplored neighbours (one is where we came
//          from); each found path also costs O(n*n) to turn into a String. n <= 5 keeps
//          it small.
//   Space: O(n*n) - recursion depth, str and visited each hold at most one path
//          (the output list is extra).
//
// GOTCHAS
//   - The un-visit in step 6 is essential: without it a cell used by the first path
//     could never appear in a later path, and answers would be lost.
//   - A walled start is handled: m[0][0] == 0 returns at once with no paths.
//   - "i,j" String keys work but build a new String on every check; a boolean[n][n]
//     (or temporarily setting m[i][j] = 0) is the cheaper way to mark visited cells.
//
// RELATED
//   - LC 980 Unique Paths III - the same 4-direction grid backtracking with mark / unmark,
//     but it counts paths that cover every empty cell.
//   - LC 62 Unique Paths (in this repo) - start-to-end grid paths with only Right / Down
//     moves, which can be counted with DP instead of listed one by one.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGRatInAMazeProblemI {
    public static void main(String[] args) {
        // GFG example 1. Prints [DRDDRR, DDRDRR]; the expected (sorted) answer is
        // [DDRDRR, DRDDRR] - see STATUS
        int N = 4;
        int[][] m = {{1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}};

        // ans collects the finished paths, str is the path being built, visited marks
        // the cells on that path
        List<String> ans = new ArrayList<>();
        List<Character> str = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        mazeSolver(0, 0, N, m, ans, str, visited);
        System.out.println(ans);
    }

    // Adds to ans every path from (i, j) to (n-1, n-1), each prefixed with the moves in str.
    public static void mazeSolver(int i, int j, int n, int[][] m, List<String> ans, List<Character> str, Set<String> visited){
        // reached the destination: the moves so far form one complete path.
        // Checked BEFORE the wall test, so a walled destination still counts (STATUS #2)
        if(i == (n - 1) && j == (n - 1)){
            StringBuilder sb = new StringBuilder();
            for (char ch : str) {
                sb.append(ch);
            }
            ans.add(sb.toString());
            return;
        }
        // dead end: off the grid, a wall, or a cell already on the current path
        if (i >= n || j >= n || i < 0 || j < 0 || m[i][j] == 0 || visited.contains(i + "," + j)) {
            return;
        }
        // choose: put this cell on the path
        visited.add(i + "," + j);

        // explore the 4 moves; each one appends its letter, recurses, then undoes the letter.
        // Order R, D, L, U decides the output order (lexicographic would be D, L, R, U)
        str.add('R');
        mazeSolver(i, j + 1, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        str.add('D');
        mazeSolver(i + 1, j, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        str.add('L');
        mazeSolver(i, j - 1, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        str.add('U');
        mazeSolver(i - 1, j, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        // undo: free the cell so other paths may pass through it
        visited.remove(i + "," + j);
    }
}
