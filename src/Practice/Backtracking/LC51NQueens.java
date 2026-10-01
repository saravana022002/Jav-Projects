package Practice.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ============================================================================
// LC 51. N-Queens   [Hard]
// https://leetcode.com/problems/n-queens/
// Topics: Array, Backtracking, Algorithm X
// ============================================================================
//
// PROBLEM
//   Place n chess queens on an n x n board so that no two attack each other: no two may
//   share a row, a column or a diagonal. Return EVERY such arrangement, in any order.
//   Each arrangement is a list of n strings (one per row) where 'Q' is a queen and '.'
//   an empty square.
//
// EXAMPLES
//   n = 4  ->  [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//              the only two ways for a 4x4 board (mirror images of each other)
//   n = 1  ->  [["Q"]]
//
// CONSTRAINTS
//   1 <= n <= 9
//
// INTUITION
//   Every column must hold exactly one queen, so place them column by column and only
//   decide the row. A new queen clashes with an earlier one only through its row or one
//   of its two diagonals, and each of those can be remembered in a boolean array:
//     - leftRow[row]                  : this row already has a queen
//     - lowerDiagonal[row + col]      : "/" diagonals - all their cells share row + col
//     - upperDiagonal[n - 1 + row - col]: "\" diagonals - all their cells share row - col,
//                                         and + (n - 1) shifts it into 0 .. 2n-2
//   So "is this square safe?" is three O(1) lookups. Try each safe row, recurse into the
//   next column, and undo the placement afterwards (backtracking).
//
// APPROACH (as implemented below)
//   1. solveNQueens builds the empty board sum (n rows of '.') and the three flag arrays,
//      then calls solveQ for col = 0.
//   2. solveQ: if col == n, all queens are placed - add a copy of sum to res.
//   3. Otherwise for every row whose three flags are all false: write 'Q' into that row's
//      String, set the three flags, recurse with col + 1.
//   4. Undo: put '.' back and clear the three flags, then try the next row.
//
// DRY RUN   n = 4   (queens placed column by column, rows tried 0..3)
//   col0 row0 -> col1: rows 0,1 blocked, row 2 ok -> col2: all 4 rows blocked, undo
//                col1: row 3 ok -> col2: row 1 ok -> col3: all rows blocked, undo, undo
//   col0 row1 -> col1 row3 -> col2 row0 -> col3 row2 -> col == 4: save
//                [..Q., Q..., ...Q, .Q..]
//   col0 row2 -> the mirror image: save [.Q.., ...Q, Q..., ..Q.]
//   col0 row3 -> only dead ends
//   res = [[..Q., Q..., ...Q, .Q..], [.Q.., ...Q, Q..., ..Q.]]  (LeetCode's two, swapped)
//
// STATUS
//   main() calls solveNQueens(4) but never prints the result, so running the file shows
//   nothing (the method itself returns the correct two boards). isSafe() is unused.
//
// COMPLEXITY
//   Time : O(n! * n) - the rows chosen for the n columns are all different, so at most
//          n! full placements are explored; each placement / undo rebuilds a row String
//          and each saved board is copied, both O(n).
//   Space: O(n^2) - the board strings; the flag arrays and the recursion depth are O(n)
//          (output extra).
//
// GOTCHAS
//   - There are 2n-1 diagonals of each kind, hence the array size 2*n - 1.
//   - row - col can be negative; the n - 1 offset is what keeps the index >= 0.
//   - res.add(new ArrayList<>(sum)) copies the row list; the Strings inside can be shared
//     safely because they are immutable (every change builds a new String).
//
// RELATED
//   - LC 52 N-Queens II (in this repo) - the same search, but only the count is returned.
//   - LC 37 Sudoku Solver (in this repo) - place / check / undo backtracking on a grid.
// ============================================================================
public class LC51NQueens {
    public static void main(String[] args) {
        // Example 1 - the result is computed but not printed, so there is no output
        // (it would be [[..Q., Q..., ...Q, .Q..], [.Q.., ...Q, Q..., ..Q.]])
        int n = 4;
        solveNQueens(n);
    }
    // Returns every valid board for n queens.
    public static List<List<String>> solveNQueens(int n) {
        // empty board: n rows of n dots
        List<List<String>> res = new ArrayList<>();
        List<String> sum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            sum.add(new String(row));
        }

        // taken-markers: "\" diagonals (row - col), "/" diagonals (row + col), rows
        boolean[] upperDiagonal = new boolean[2*n - 1];
        boolean[] lowerDiagonal = new boolean[2*n - 1];
        boolean[] leftRow = new boolean[n];
        solveQ(res, n, 0, sum, upperDiagonal, lowerDiagonal, leftRow);
        return res;
    }


    // Places queens in columns col..n-1 (columns 0..col-1 already hold one each).
    public static void solveQ(List<List<String>> res, int n, int col, List<String> sum, boolean[] upperDiagonal, boolean[] lowerDiagonal, boolean[] leftRow){
        // every column has its queen: record a copy of the board
        if(col == n){
            res.add(new ArrayList<>(sum));
            return;
        }

        for (int row = 0; row < n; row++) {
            // safe = row and both diagonals through (row, col) are still free
            if(!upperDiagonal[n - 1 + row - col] && !lowerDiagonal[row + col] && !leftRow[row]){
                // place the queen (Strings are immutable, so rebuild the row)
                char[] rowArray = sum.get(row).toCharArray();
                rowArray[col] = 'Q';
                sum.set(row, new String(rowArray));
                upperDiagonal[n - 1 + row - col] = true;
                lowerDiagonal[row + col]  = true;
                leftRow[row] = true;
                solveQ(res, n, col + 1, sum, upperDiagonal, lowerDiagonal, leftRow);
                // undo the placement before trying the next row
                rowArray[col] = '.';
                sum.set(row, new String(rowArray));
                upperDiagonal[n - 1 + row - col] = false;
                lowerDiagonal[row + col]  = false;
                leftRow[row] = false;
            }
        }
    }

    // Unused - the older O(n) safety test that scans the board to the LEFT of (row, col):
    // the upper-left diagonal, the same row, then the lower-left diagonal. The three
    // boolean arrays above replace it with O(1) lookups.
    private static boolean isSafe(int row, int col, List<String> sum, int size) {
        int dupRow = row;
        int dupCol = col;

        while(dupCol >= 0 && dupRow >= 0){
            char[] rowArray = sum.get(dupRow).toCharArray();
            if(rowArray[dupCol] == 'Q')
                return false;
            dupCol --;
            dupRow --;
        }

        dupCol = col - 1;
        dupRow = row;
        while(dupCol >= 0){
            char[] rowArray = sum.get(dupRow).toCharArray();
            if(rowArray[dupCol] == 'Q')
                return false;
            dupCol --;
        }


        dupCol = col;
        while(dupCol >= 0 && dupRow < size){
            char[] rowArray = sum.get(dupRow).toCharArray();
            if(rowArray[dupCol] == 'Q')
                return false;
            dupCol --;
            dupRow ++;
        }
        return true;
    }

}
