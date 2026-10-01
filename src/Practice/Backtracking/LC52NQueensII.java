package Practice.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ============================================================================
// LC 52. N-Queens II   [Hard]
// https://leetcode.com/problems/n-queens-ii/
// Topics: Backtracking, Algorithm X
// ============================================================================
//
// PROBLEM
//   Place n chess queens on an n x n board so that no two attack each other: no two may
//   share a row, a column or a diagonal. Unlike LC 51, the boards themselves are not
//   wanted - just return HOW MANY different valid arrangements exist.
//
// EXAMPLES
//   n = 4  ->  2     the two mirror-image boards of the 4-queens puzzle
//   n = 1  ->  1     one queen on a 1x1 board
//
// CONSTRAINTS
//   1 <= n <= 9
//
// INTUITION
//   Every column must hold exactly one queen, so place them column by column and only
//   decide the row. A new queen clashes with an earlier one only through its row or one
//   of its two diagonals, and each of those is remembered in a boolean array:
//     - leftRow[row]                  : this row already has a queen
//     - lowerDiagonal[row + col]      : "/" diagonals - all their cells share row + col
//     - upperDiagonal[n - 1 + row - col]: "\" diagonals - all their cells share row - col,
//                                         and + (n - 1) shifts it into 0 .. 2n-2
//   So "is this square safe?" is three O(1) lookups. For counting, each call simply
//   returns how many complete boards exist below it, and a parent adds up its children.
//
// APPROACH (as implemented below)
//   1. solveNQueens2 builds the empty board sum (n rows of '.') and the three flag
//      arrays, then returns solveQ for col = 0.
//   2. solveQ: if col == n, every column got its queen - that is one solution, return 1.
//   3. Otherwise count = 0; for every row whose three flags are all false: write 'Q' into
//      that row's String, set the three flags, and add solveQ(col + 1) to count.
//   4. Undo: put '.' back and clear the three flags, then try the next row.
//   5. Return count - the number of solutions reachable from this partial board.
//
// DRY RUN   n = 4   (columns filled left to right, rows tried 0..3)
//   col0 row0 -> col1 row2 -> col2: all 4 rows blocked, returns 0
//                col1 row3 -> col2 row1 -> col3: all 4 rows blocked, returns 0
//   col0 row1 -> col1 row3 -> col2 row0 -> col3 row2 -> col == 4, returns 1
//   col0 row2 -> the mirror image of the line above, returns 1
//   col0 row3 -> only dead ends, returns 0
//   answer = 0 + 1 + 1 + 0 = 2
//
// STATUS
//   Correct (checked for n = 1..9: 1, 0, 0, 2, 10, 4, 40, 92, 352). The code is a copy of
//   LC 51 N-Queens that now only counts, so it still carries leftovers that do not change
//   the answer: the List<String> board sum is updated but never read, sum.set(...) is
//   called twice in a row when placing a queen, and isSafe() is unused.
//
// COMPLEXITY
//   Time : O(n! * n) - the rows chosen for the n columns are all different, so at most
//          n! full placements are explored; each placement / undo rebuilds a row String
//          of length n. (Pruning makes it far smaller in practice.)
//   Space: O(n^2) - the board strings in sum, plus one char[n] alive in each of up to n
//          recursion frames; the three flag arrays are O(n).
//
// GOTCHAS
//   - There are 2n-1 diagonals of each kind, hence the array size 2*n - 1.
//   - row - col can be negative; the n - 1 offset is what keeps the index >= 0.
//   - A mirror-image board counts as a separate solution (n = 4 gives 2, not 1).
//   - Some n have no solution at all: n = 2 and n = 3 return 0.
//
// RELATED
//   - LC 51 N-Queens (in this repo) - the same search, but it returns every board.
// ============================================================================
public class LC52NQueensII {
    public static void main(String[] args) {
        // Example 1 - prints 2
        int n = 4;
        System.out.println(solveNQueens2(n));
    }
    // Returns the number of ways to place n non-attacking queens on an n x n board.
    public static int solveNQueens2(int n) {
        // empty board: n rows of n dots (only written to, never read for the count)
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

        return solveQ( n, 0, sum, upperDiagonal, lowerDiagonal, leftRow);
    }


    // Counts the ways to place queens in columns col..n-1 when columns 0..col-1 already
    // hold one each (their rows and diagonals are marked in the three boolean arrays).
    public static int solveQ(int n, int col, List<String> sum, boolean[] upperDiagonal, boolean[] lowerDiagonal, boolean[] leftRow){
        // every column has its queen: this partial board is one complete solution
        if(col == n){
            return 1;
        }
        int count = 0;
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
                // (repeats the sum.set(...) above - harmless, the board is not used for the count)
                sum.set(row, new String(rowArray));
                // solutions found below this choice are added to the running total
                count += solveQ(n, col + 1, sum, upperDiagonal, lowerDiagonal, leftRow);
                // undo the placement before trying the next row
                rowArray[col] = '.';
                sum.set(row, new String(rowArray));
                upperDiagonal[n - 1 + row - col] = false;
                lowerDiagonal[row + col]  = false;
                leftRow[row] = false;
            }
        }
        return count;
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
