package Practice.Matrix;

import java.util.Arrays;

// ============================================================================
// LC 36. Valid Sudoku   [Medium]
// https://leetcode.com/problems/valid-sudoku/
// Topics: Array, Hash Table, Matrix
// ============================================================================
//
// PROBLEM
//   Decide whether a 9 x 9 Sudoku board, possibly only partly filled, is valid so far: no
//   digit 1-9 may appear twice in any row, in any column, or in any of the nine 3 x 3
//   boxes. Empty cells ('.') are ignored. The puzzle does not have to be solvable, and
//   solving it is not asked for.
//
// EXAMPLES
//   the board in main() (LeetCode example 1)             ->  true
//   the same board with the top-left 5 changed to 8      ->  false   the top-left box then
//                                                        holds two 8s (column 0 has two too)
//
// CONSTRAINTS
//   board.length == 9,   board[i].length == 9,   board[i][j] is a digit '1'-'9' or '.'
//
// INTUITION
//   Only repeats matter, so check every filled cell against the other cells of its three
//   groups: its row, its column and its 3 x 3 box. The first conflict ends the check. One
//   loop counter i = 0..8 is enough to walk all three groups at once: board[row][i] runs
//   along the row, board[i][col] down the column, and
//   (3 * (row / 3) + i / 3, 3 * (col / 3) + i % 3) over the nine cells of the box.
//
// APPROACH (as implemented below)
//   1. solveSudoku (the name is misleading - it only validates): for every cell (i, j) that
//      is not '.', call isValid(board, i, j, board[i][j]); if one call fails, return false.
//      After all cells: return true.
//   2. isValid(board, row, col, k) loops i = 0..8 and looks for another k in
//        row     : board[row][i] == k  && i != col
//        column  : board[i][col] == k  && i != row
//        box     : r = 3 * (row / 3) + i / 3, c = 3 * (col / 3) + i % 3;
//                  board[r][c] == k && r != row && c != col
//      (a box cell sharing the row or the column is already covered by the first two tests,
//      and this also skips the cell itself).
//   3. isValidSudoku is the method LeetCode calls (an instance method); it just calls
//      solveSudoku.
//
// DRY RUN
//   Box walk for cell (row=4, col=7): r = 3 + i / 3, c = 6 + i % 3 gives
//   (3,6) (3,7) (3,8) (4,6) (4,7) (4,8) (5,6) (5,7) (5,8) - the middle-right box.
//   Example 2: the first filled cell (0,0) is '8'. Its row and column scans skip the cell
//   itself (i == col, i == row), and at i = 3 the column test finds board[3][0] == '8' with
//   3 != 0 -> isValid is false -> solveSudoku returns false.
//   Example 1 (main): all 30 filled cells pass every test, so it prints true.
//
// COMPLEXITY
//   Time : O(1) - the board is always 9 x 9: at most 81 cells x 9 steps x 3 tests
//          (about 2,000 comparisons)
//   Space: O(1) - no extra structures
//
// GOTCHAS
//   - solveSudoku does not solve anything (that is LC 37 Sudoku Solver); it is only a
//     validator with an odd name.
//   - The box test has r != row && c != col on purpose; do not "simplify" it to just
//     board[r][c] == k, because that would compare the cell with itself and fail.
//   - Only filled cells are checked, and a valid board need not be solvable.
//   - The other common way is one pass with a seen-set (or bitmask) per row, per column and
//     per box; this file instead re-scans the three groups for every filled cell.
//
// RELATED
//   - LC 37 Sudoku Solver - actually fills the board; the usual backtracking solution tests
//     every placement with a check like isValid here.
//   - LC 2133 Check if Every Row and Column Contains All Numbers - a similar row and column
//     check on an n x n grid.
// ============================================================================
public class LC36ValidSudoku {
    public static void main(String[] args) {
        // LeetCode example 1 - a valid board. Prints true
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(solveSudoku(board));
    }

    // The method LeetCode calls; it delegates to solveSudoku.
    public boolean isValidSudoku(char[][] board) {
        return solveSudoku(board);
    }

    // Despite the name this only VALIDATES the board: false as soon as one filled cell
    // repeats in its row, column or box.
    public static boolean solveSudoku(char[][] board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // empty cells need no check
                if (board[i][j] != '.') {
                    if(!isValid(board, i, j, board[i][j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Is the digit k at (row, col) free of repeats in its row, column and 3 x 3 box?
    private static boolean isValid(char[][] board, int row, int col, char k) {
        for (int i = 0; i < 9; i++) {

            // another k in the same row
            if(board[row][i] == k && i != col){
                return false;
            }
            // another k in the same column
            if(board[i][col] == k && i != row){
                return false;
            }

            // the i-th cell of the box that contains (row, col): top-left corner + offset
            int r = 3 * (row / 3) + i / 3;
            int c = 3 * (col / 3) + i % 3;
            // another k in the box (same row / same column cases were caught above)
            if(board[r][c] == k && r != row && c != col){
                return false;
            }
        }
        return true;
    }
}
