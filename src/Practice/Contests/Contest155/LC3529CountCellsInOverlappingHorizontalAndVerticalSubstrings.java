package Practice.Contests.Contest155;

// ============================================================================
// LC 3529. Count Cells in Overlapping Horizontal and Vertical Substrings   [Medium]
// https://leetcode.com/problems/count-cells-in-overlapping-horizontal-and-vertical-substrings/
// Topics: Array, String, Rolling Hash, String Matching, Matrix, Hash Function
// ============================================================================
//
// PROBLEM
//   grid is an m x n matrix of lowercase letters and pattern is a string. Read the grid as
//   text in two ways:
//     horizontal substring: left to right; at the end of a row it continues at the FIRST
//                           COLUMN OF THE NEXT ROW (no wrap from the last row to the top)
//     vertical substring  : top to bottom; at the bottom of a column it continues at the
//                           FIRST ROW OF THE NEXT COLUMN (no wrap from the last column back)
//   Count the cells that lie inside at least one horizontal occurrence of pattern AND
//   inside at least one vertical occurrence of pattern.
//
// EXAMPLES
//   grid = [[a,a,c,c],[b,b,b,c],[a,a,b,a],[c,a,a,c],[a,a,b,a]], pattern = "abaca"  ->  1
//       one horizontal and one vertical occurrence, and they share exactly one cell
//   grid = [[c,a,a,a],[a,a,b,a],[b,b,a,a],[a,a,b,a]], pattern = "aba"  ->  4
//   grid = [[a]], pattern = "a"  ->  1
//
// CONSTRAINTS
//   m == grid.length,   n == grid[i].length,   1 <= m, n <= 1000,   1 <= m * n <= 10^5,
//   1 <= pattern.length <= m * n,   lowercase English letters only
//
// INTUITION
//   Reading horizontally with row-to-row wrapping is the same as reading the grid as ONE
//   string row by row; reading vertically is the same as one string column by column. So
//   the task is plain pattern matching in two strings, then marking the cells covered by
//   any match and counting the cells covered in both. This code never builds those strings:
//   from every start cell it computes where the k-th character of the pattern lands
//     horizontal: row = i + (j + k) / n,   column = (j + k) % n
//     vertical  : column = j + (i + k) / m, row = (i + k) % m
//   and compares it with pattern.charAt(k). Two boolean grids remember the covered cells.
//
// APPROACH (as implemented below)
//   1. m, n, patterSize = pattern.length(); horizontal and vertical are m x n boolean grids.
//   2. Horizontal pass: for every start (i, j) test k = 0 .. patterSize-1 at
//      (ni, nj) = (i + (j + k) / n, (j + k) % n). Stop at the first character that differs
//      or when ni >= m (ran off the bottom). If all match, set horizontal[ni][nj] = true
//      for all patterSize cells.
//   3. Vertical pass: the same with (ni, nj) = ((i + k) % m, j + (i + k) / m), failing when
//      nj >= n (ran off the right edge); mark vertical.
//   4. count the cells where horizontal[i][j] && vertical[i][j].
//
// DRY RUN   main(): 5 x 4 grid, pattern "abaca"
//       a a c c
//       b b b c
//       a a b a
//       c a a c
//       a a c c
//   horizontal: row by row the text is aaccbbbcaabacaacaacc; "abaca" occurs once, starting at
//               (2,1): cells (2,1) (2,2) (2,3) (3,0) (3,1)
//   vertical  : column by column the text is abaca abaaa cbbac ccacc; "abaca" occurs once,
//               at the very start, (0,0): cells (0,0) (1,0) (2,0) (3,0) (4,0)
//   the two marked sets share only (3,0)  ->  count = 1
//
// STATUS
//   Known issue (speed, not correctness): this is a brute force, O(m * n * L) with
//   L = pattern.length(), so a long pattern on a large grid is slow. Measured here on an
//   all-'a' grid of 99856 cells: L = 1000 took 0.3 s, L = 2000 1.0 s, L = 4000 2.1 s, and
//   the time keeps growing with L (L may be as large as m * n), so the biggest inputs will
//   not finish in time. The usual fix is KMP or the Z-function on the two flattened strings,
//   which is linear. The answers are correct.
//   Also: main() calls countCells but does not print the result (it is 1), and its last
//   grid row is {a,a,c,c} instead of the {a,a,b,a} of example 1 (the answer is still 1).
//
// COMPLEXITY
//   Time : O(m * n * L) worst case - every start cell may compare up to L characters, and
//          every match marks L cells (both passes). Early exit on a mismatch makes typical
//          inputs much faster.
//   Space: O(m * n) - the two boolean grids
//
// GOTCHAS
//   - The two wraps differ: horizontal continues on the next ROW, vertical on the next
//     COLUMN, hence (j + k) / n with % n in one pass and (i + k) / m with % m in the other.
//   - Only one coordinate can run out of range: ni >= m (horizontal) or nj >= n
//     (vertical); the modulo keeps the other one inside the grid.
//   - Occurrences may overlap. Marks only accumulate in the boolean grids, so a cell
//     covered by several matches is still counted once.
//   - The pattern can be longer than a row or column (it wraps), up to m * n characters.
//
// RELATED
//   - LC 28 Find the Index of the First Occurrence in a String - the one-string version of
//     this search; KMP / Z-function (the linear way to speed this problem up) are
//     usually introduced there.
// ============================================================================
public class LC3529CountCellsInOverlappingHorizontalAndVerticalSubstrings {
    public static void main(String[] args) {
        // the 5 x 4 grid of example 1 (except its last row); the result is not printed,
        // countCells returns 1 here (see STATUS)
        char[][] grid = {
                {'a', 'a', 'c', 'c'},
                {'b', 'b', 'b', 'c'},
                {'a', 'a', 'b', 'a'},
                {'c', 'a', 'a', 'c'},
                {'a', 'a', 'c', 'c'}
        };

        String pattern = "abaca";
        countCells(grid, pattern);
    }
    // Returns how many cells lie in both a horizontal and a vertical occurrence of pattern.
    public static int countCells(char[][] grid, String pattern) {
        int m = grid.length;
        int n = grid[0].length;
        int patterSize = pattern.length();

        // cells covered by at least one horizontal / vertical occurrence
        boolean[][] horizontal = new boolean[m][n];
        boolean[][] vertical = new boolean[m][n];
        // horizontal pass: try to read the pattern starting at every cell (i, j)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean match = true;
                for (int k = 0; k < patterSize; k++) {
                    // k-th character: move right, wrapping to the start of the next row
                    int ni = (i + (j + k) / n);
                    int nj = (j + k) % n;
                    // ran past the last row, or the letter differs: not a match
                    if(ni >= m || pattern.charAt(k) != grid[ni][nj]){
                        match = false;
                        break;
                    }
                }
                // a full match: every cell it covers counts as horizontal
                if(match){
                    for (int k = 0; k < patterSize; k++) {
                        int ni = (i + (j + k) / n);
                        int nj = (j + k) % n;
                        horizontal[ni][nj] = true;
                    }
                }
            }
        }


        // vertical pass: the same, reading downwards and wrapping to the next column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean match = true;
                for (int k = 0; k < patterSize; k++) {
                    int nj = (j + (i + k) / m);
                    int ni = (i + k) % m;
                    // ran past the last column, or the letter differs: not a match
                    if (nj >= n || grid[ni][nj] != pattern.charAt(k)) {
                        match = false;
                        break;
                    }
                }
                // a full match: every cell it covers counts as vertical
                if (match) {
                    for (int k = 0; k < patterSize; k++) {
                        int nj = (j + (i + k) / m);
                        int ni = (i + k) % m;
                        vertical[ni][nj] = true;
                    }
                }
            }
        }
        // cells covered in both directions
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (horizontal[i][j] && vertical[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
