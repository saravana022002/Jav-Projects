package Practice.Backtracking;

import java.util.Arrays;

// ============================================================================
// LC 37. Sudoku Solver   [Hard]
// https://leetcode.com/problems/sudoku-solver/
// Topics: Array, Hash Table, Backtracking, Matrix, Algorithm X, Dancing Links
// ============================================================================
//
// PROBLEM
//   A 9x9 Sudoku board is given as chars: a digit '1'..'9' or '.' for an empty cell.
//   Fill every empty cell IN PLACE so that each row, each column and each of the nine
//   3x3 boxes contains every digit 1-9 exactly once. The puzzle is guaranteed to have
//   exactly one solution.
//
// EXAMPLES
//   The board in main() (LeetCode example 1) becomes
//   [5,3,4,6,7,8,9,1,2] [6,7,2,1,9,5,3,4,8] [1,9,8,3,4,2,5,6,7]
//   [8,5,9,7,6,1,4,2,3] [4,2,6,8,5,3,7,9,1] [7,1,3,9,2,4,8,5,6]
//   [9,6,1,5,3,7,2,8,4] [2,8,7,4,1,9,6,3,5] [3,4,5,2,8,6,1,7,9]   (one row each)
//
// CONSTRAINTS
//   board.length == 9,   board[i].length == 9,   board[i][j] is a digit or '.'
//   The input board has exactly one solution.
//
// INTUITION
//   Solving is a chain of guesses: take an empty cell, write a digit that does not clash
//   with its row, column or box, and move on. If some later cell has no legal digit left,
//   an earlier guess was wrong - undo the most recent one and try its next digit. Every
//   legal digit of every cell is tried in turn, so the solution cannot be missed, and
//   clashing digits are rejected at once, which keeps the search small enough for 9x9.
//
// APPROACH (as implemented below)
//   1. solveSudoku scans the board row by row for the first '.'.
//   2. For that cell try k = '1'..'9', skipping any k that isValid() rejects.
//   3. Place k and call solveSudoku on the whole board again. true -> everything after
//      it got solved: return true all the way up, leaving the board filled.
//   4. false -> reset the cell to '.' and try the next k. If no k works, return false so
//      the caller changes ITS guess.
//   5. If the scan finds no '.', the board is complete: return true.
//   6. isValid(board, row, col, k): one loop i = 0..8 checks the row cell (row, i), the
//      column cell (i, col) and the box cell (3*(row/3) + i/3, 3*(col/3) + i%3).
//
// DRY RUN   main()'s board
//   The first '.' is (0,2). Row 0 holds 5,3,7, column 2 holds 8, the top-left box holds
//   5,3,6,9,8 -> isValid lets only '1', '2' and '4' through.
//   '1' is placed and the search goes on to (0,3), (0,5), ...; since the solution is
//   unique, this branch eventually reaches a cell with no legal digit, false comes back,
//   and (0,2) is reset to '.'. '2' fails the same way. '4' leads to the full solution,
//   every call returns true and row 0 ends as [5,3,4,6,7,8,9,1,2].
//   Box math in isValid for (row, col) = (4, 7): the box starts at (3*(4/3), 3*(7/3)) =
//   (3, 6) and i = 0..8 visits (3 + i/3, 6 + i%3) = (3,6) (3,7) (3,8) (4,6) ... (5,8).
//
// STATUS
//   Signature differs from LeetCode: solveSudoku returns boolean (true = solved), while
//   LeetCode's is void. The boolean is what drives the backtracking; main() prints the
//   solved board.
//
// COMPLEXITY
//   Time : O(9^E) - E = number of empty cells (<= 81), each may try up to 9 digits; each
//          try costs an O(9) isValid and each call an O(81) scan for the next '.'.
//          The early rejection keeps real puzzles far below this bound.
//   Space: O(E) - one recursion frame per filled cell; the board is changed in place.
//
// GOTCHAS
//   - The "return false" right after the digit loop matters: if the first empty cell
//     has no legal digit, the board is unsolvable from here, so do not look further.
//   - Success must stop at once (return true). Otherwise the reset to '.' would run and
//     wipe the solution out of the in-place board.
//   - isValid needs no "skip the cell itself" check: that cell is still '.' when tested.
//   - Every call rescans from (0,0); passing the position along, or keeping row / column /
//     box "used" sets, makes each step cheaper.
//
// RELATED
//   - LC 36 Valid Sudoku (in this repo) - the same row / column / box rules, only checked.
//   - LC 51 N-Queens (in this repo) - the same place / check / undo backtracking on a board.
// ============================================================================
public class LC37SudokuSolver {
    public static void main(String[] args) {
        // LeetCode example 1. Prints the solved board (the solution in EXAMPLES):
        // [[5, 3, 4, 6, 7, 8, 9, 1, 2], [6, 7, 2, ...], ... [3, 4, 5, 2, 8, 6, 1, 7, 9]]
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                          {'6','.','.','1','9','5','.','.','.'},
                          {'.','9','8','.','.','.','.','6','.'},
                          {'8','.','.','.','6','.','.','.','3'},
                          {'4','.','.','8','.','3','.','.','1'},
                          {'7','.','.','.','2','.','.','.','6'},
                          {'.','6','.','.','.','.','2','8','.'},
                          {'.','.','.','4','1','9','.','.','5'},
                          {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }

    // Fills the board in place; returns true once it is completely and validly filled.
    public static boolean solveSudoku(char[][] board){
        // find the first empty cell in row-major order
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // try every digit that does not clash with the row, column or box
                    for(char k = '1'; k <= '9'; k++){
                        if(isValid(board, i, j, k)){
                            board[i][j] = k;
                            // the rest solved -> keep this digit and stop searching
                            if(solveSudoku(board)){
                                return true;
                            }else {
                                // dead end further on -> undo and try the next digit
                                board[i][j] = '.';
                            }
                        }
                    }
                    // no digit fits this cell: an earlier guess was wrong
                    return false;
                }
            }
        }
        // no empty cell left: the board is solved
        return true;
    }

    // true if digit k is not yet used in the row, the column or the 3x3 box of (row, col).
    private static boolean isValid(char[][] board, int row, int col, char k) {
        for (int i = 0; i < 9; i++) {
            if(board[row][i] == k){
                return false;
            }
            if(board[i][col] == k){
                return false;
            }
            // box: 3*(row/3), 3*(col/3) is its top-left cell; i/3 and i%3 walk its 3x3 cells
            if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == k){
                return false;
            }
        }
        return true;
    }
}
