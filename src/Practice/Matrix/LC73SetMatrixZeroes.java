package Practice.Matrix;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// ============================================================================
// LC 73. Set Matrix Zeroes   [Medium]
// https://leetcode.com/problems/set-matrix-zeroes/
// Topics: Array, Hash Table, Matrix
// ============================================================================
//
// PROBLEM
//   Whenever a cell of the matrix holds 0, its whole row and its whole column must become
//   0. Do it in place. Follow-up: using O(rows * columns) extra memory is a bad idea,
//   O(rows + columns) is better, and there is a way that needs only constant extra memory.
//   In this file n is the number of ROWS and m the number of COLUMNS (LeetCode's m x n
//   names them the other way round).
//
// EXAMPLES
//   [[1,1,1],[1,0,1],[1,1,1]]        ->  [[1,0,1],[0,0,0],[1,0,1]]
//   [[0,1,2,0],[3,4,5,2],[1,3,1,5]]  ->  [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
//       zeros at (0,0) and (0,3): row 0 and columns 0 and 3 are cleared
//   main() uses a 10 x 7 matrix whose zeros are at (2,3), (3,4), (7,0) and (8,2): rows 2, 3,
//   7, 8 and columns 0, 2, 3, 4 become 0.
//
// CONSTRAINTS   (LeetCode naming: m rows, n columns)
//   m == matrix.length,   n == matrix[0].length,   1 <= m, n <= 200,
//   -2^31 <= matrix[i][j] <= 2^31 - 1
//
// INTUITION
//   A zero at (i, j) wipes row i and column j. The trap: if you write zeros WHILE scanning,
//   the new zeros look like original ones and the wipe spreads over everything. So every
//   approach first RECORDS where the zeros are, and only then writes:
//     A. read the zeros from the untouched original and write into a copy;
//     B. remember, in two small lists, which rows and which columns hold a zero;
//     C. keep that memory inside the matrix itself: column 0 says "this row must be zeroed"
//        and row 0 says "this column must be zeroed", plus a flag col0 for column 0 itself.
//        No extra memory, but row 0 and column 0 now play two roles (data and marker), so
//        they must be zeroed LAST, after every marker has been read.
//
// APPROACHES IN THIS FILE
//   A. approach1(matrix, n, m) - copies the matrix, zeroes the row and column of every
//                                ORIGINAL zero in the copy, prints the copy (the input is
//                                not changed)   - O(n*m*(n+m)) time, O(n*m) space
//   B. approach2(matrix, n, m) - marker lists rowArr / columnArr, then zeroes the marked rows
//                                and columns in place and prints   - O(n*m) time, O(n+m)
//                                space. Throws when n > m, see STATUS.
//   C. approach3(matrix, n, m) - markers kept in row 0 and column 0 plus the flag col0, in
//                                place   - O(n*m) time, O(1) space. Wrong when row 0
//                                contains a zero, see STATUS.
//   main() calls A, then B, then C on a 10 x 7 matrix (n = 10 rows, m = 7 columns), but B
//   throws, so C never runs. approach2Primitive and approachPrimitive (with their helpers)
//   are unused int[][] versions of B and A.
//   (APPROACH and DRY RUN below describe C; A and B follow in brief.)
//
// APPROACH (as implemented below, approach3)
//   1. col0 = -1 means "column 0 holds no zero so far" (0 means it does).
//   2. Scan every cell (row i, column j). For a cell that is 0:
//        (0,0)         : col0 = 0 and matrix[0][0] = 0
//        i == 0        : matrix[0][0] = 0 (meant as "row 0 holds a zero"; the zero cell
//                        itself already marks its column j)
//        j == 0        : col0 = 0 (the zero cell itself already marks its row i)
//        any other cell: matrix[i][0] = 0 (row i marked) and matrix[0][j] = 0 (column j
//                        marked)
//   3. Rows: for every i with matrix[i][0] == 0, makeRowZero(m, i, matrix).
//   4. Columns: for every j, makeColumnZero(n, j, matrix) when j == 0 and col0 == 0, and
//      again when matrix[0][j] == 0.
//
// DRY RUN   approach3 on LeetCode example 1, [[1,1,1],[1,0,1],[1,1,1]]  (row 0 has no zero)
//   scan     the only zero is (1,1): matrix[1][0] = 0 and matrix[0][1] = 0
//            -> [[1,0,1],[0,0,1],[1,1,1]]
//   rows     matrix[1][0] == 0 -> row 1 is zeroed        -> [[1,0,1],[0,0,0],[1,1,1]]
//   columns  j=0: matrix[0][0] = 1 and col0 = -1, nothing
//            j=1: matrix[0][1] == 0 -> column 1 is zeroed -> [[1,0,1],[0,0,0],[1,0,1]]
//            j=2: matrix[0][2] = 1, nothing               (correct)
//   Now [[1,0,1],[1,1,1],[1,1,1]], a zero in row 0: the scan sets matrix[0][0] = 0, the row
//   pass zeroes ALL of row 0 and so erases every column marker, and the column pass then
//   finds 0 in matrix[0][j] for every j and zeroes every column. The result is all zeros
//   instead of [[0,0,0],[1,0,1],[1,0,1]].
//
// APPROACH B (approach2, in brief)
//   1. rowArr and columnArr are lists of 0/1 marks. columnArr has m entries (one per column);
//      rowArr is also created with m entries, although it needs n (one per row).
//   2. For every zero at (i, j): rowArr[i] = 1 and columnArr[j] = 1.
//   3. makeRowZero for every marked row, makeColumnZero for every marked column, print.
//
// APPROACH A (approach1, in brief)
//   1. arr = a copy of matrix.
//   2. For every zero at (i, j) of the ORIGINAL matrix, returnMakeZeros(n, m, i, j, arr)
//      zeroes row i and column j of the copy.
//   3. Print arr. The input matrix itself is left as it was.
//
// COMPLEXITY
//   C: Time O(n*m) - one scan plus one zeroing pass;  Space O(1) - col0 and the loop counters
//   B: Time O(n*m) - a scan, then each marked row / column is written once;
//      Space O(n + m) - the two marker lists
//   A: Time O(n*m*(n+m)) - every zero re-zeroes a row and a column of the copy (worst case:
//      all cells are zeros);  Space O(n*m) - the copy
//
// GOTCHAS
//   - Never zero while scanning: a zero you just wrote cannot be told from an original one.
//     Record first, zero afterwards.
//   - rowArr needs one entry per ROW (n); approach2 creates it with m entries.
//   - In C, keep the answers for row 0 and column 0 themselves apart from the markers
//     (matrix[0][0] for row 0, col0 for column 0), and zero row 0 and column 0 last.
//   - matrix.get(i).get(j) == 0 compares an Integer with an int, so it unboxes and is safe
//     (two Integer objects compared with == would not be).
//   - approach3 prints the matrix and col0 three times while it runs (leftover debugging).
//
// RELATED
//   - LC 289 Game of Life - another in-place matrix update where new values must not get
//     mixed up with old ones while scanning.
//   - LC 48 Rotate Image - another in-place matrix transformation (LC48RotateImage.java in
//     src/Practice/Matrix).
//
// STATUS
//   Known issues (the code is left unchanged on purpose):
//   1. main() throws. approach2 creates rowArr with m (7) entries but the matrix has n (10)
//      rows, so the 10 x 7 sample fails with IndexOutOfBoundsException (Index 7 out of
//      bounds for length 7). Any input with more rows than columns fails the same way, zeros
//      or not; with n <= m approach2 is correct. approach3 is never reached.
//   2. approach3 is wrong whenever row 0 contains a zero: it then zeroes the whole matrix.
//      LeetCode example 2 [[0,1,2,0],[3,4,5,2],[1,3,1,5]] gives all zeros (expected
//      [[0,0,0,0],[0,4,5,0],[0,3,1,0]]). On main()'s sample (no zero in row 0) it is correct.
//   3. approach1 does not modify its input; it only prints the zeroed copy, while the
//      problem asks for the matrix itself to be changed.
//   Also: the signature is the Coding Ninjas form (ArrayList<ArrayList<Integer>> matrix,
//   n, m; returns nothing), not LeetCode's int[][] matrix. The examples are the same as in
//   LC 73.
// ============================================================================
public class LC73SetMatrixZeroes {
    public static void main(String[] args) {

        // 10 rows x 7 columns; zeros at (2,3), (3,4), (7,0) and (8,2)
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        matrix.add(new ArrayList<>(Arrays.asList(-4, 9, 1, 3, 2, 11, 6)));
        matrix.add(new ArrayList<>(Arrays.asList(2, -8, 7, 8, 10, -5, 5)));
        matrix.add(new ArrayList<>(Arrays.asList(1, 8, -4, 0, 9, 7, -4)));
        matrix.add(new ArrayList<>(Arrays.asList(1, -7, 11, 5, 0, -4, -6)));
        matrix.add(new ArrayList<>(Arrays.asList(-7, 4, 4, 4, 2, 1, 9)));
        matrix.add(new ArrayList<>(Arrays.asList(5, 11, -1, -8, -1, 6, -4)));
        matrix.add(new ArrayList<>(Arrays.asList(11, -8, -6, 2, 3, 10, 7)));
        matrix.add(new ArrayList<>(Arrays.asList(0, 1, 6, 8, 3, 9, 6)));
        matrix.add(new ArrayList<>(Arrays.asList(1, -5, 0, 2, 5, -6, 5)));
        matrix.add(new ArrayList<>(Arrays.asList(4, 1, 3, -6, 7, 1, -4)));

        int n = 10;
        int m = 7;

        // prints a zeroed copy of the matrix (the matrix itself stays unchanged)
        approach1(matrix, n, m);
        // throws IndexOutOfBoundsException here (n > m), so the next line never runs
        approach2(matrix, n, m);
        approach3(matrix, n, m);
    }

    // Approach C: markers in row 0 / column 0, O(1) extra space. KNOWN ISSUE: wrong when row 0
    // contains a zero (see STATUS). n = number of rows, m = number of columns.
    private static void approach3(ArrayList<ArrayList<Integer>> matrix, int n, int m) {

        // col0 = 0 will mean "column 0 holds a zero"
        int col0 = -1;

        // debug output (also printed after pass 1 and at the end)
        matrix.forEach(ma -> System.out.println(ma+"\n"));
        System.out.println(col0);
        // pass 1: record the zeros. matrix[i][0] marks row i, matrix[0][j] marks column j
        for(int i = 0; i < n; i++){

            for(int j = 0; j < m; j++){


                // zero in the corner: row 0 and column 0 both hold a zero
                if(j == 0 && i == 0 && matrix.get(i).get(j) == 0){
                    col0 = 0;
                    matrix.get(0).set(0, 0);
                    continue;
                }

                // zero in row 0: it already marks its own column j; matrix[0][0] = 0 means
                // "row 0 must be zeroed"
                if(i == 0 && matrix.get(i).get(j) == 0){
                    matrix.get(0).set(0, 0);
                    continue;
                }

                // zero in column 0: it already marks its own row i; col0 remembers column 0
                if(j == 0 && matrix.get(i).get(j) == 0){
                    col0 = 0;
                    continue;
                }

                // zero anywhere else: mark its row (in column 0) and its column (in row 0)
                if(matrix.get(i).get(j) == 0){
                    matrix.get(i).set(0, 0);
                    matrix.get(0).set(j, 0);
                }
            }
        }

        matrix.forEach(ma -> System.out.println(ma+"\n"));
        System.out.println(col0);
        // pass 2: zero every marked row. BUG: when matrix[0][0] == 0 this zeroes all of row 0,
        // which erases the column markers that pass 3 is about to read
        for(int i = 0; i < n; i++){
            if(matrix.get(i).get(0) == 0){
                makeRowZero(m, i, matrix);
            }
        }

        // pass 3: zero every marked column (column 0 has its own flag col0)
        for(int j = 0; j < m; j++){
            if(j == 0 && col0 == 0 ){
                makeColumnZero(n, j, matrix);
            }
            if(matrix.get(0).get(j) == 0){
                makeColumnZero(n, j, matrix);
            }
        }

        matrix.forEach(ma -> System.out.println(ma+"\n"));
        System.out.println(col0);

    }


    // Sets the cells of row `index` to 0; arrSize = number of columns.
    private static void makeRowZero(int arrSize, int index, ArrayList<ArrayList<Integer>> arr) {
        for(int k = 0; k < arrSize; k++){
            arr.get(index).set(k, 0);
        }
    }

    // Sets the cells of column `index` to 0; arrSize = number of rows.
    private static void makeColumnZero(int arrSize, int index, ArrayList<ArrayList<Integer>> arr) {
        for(int k = 0; k < arrSize; k++){
            arr.get(k).set(index, 0);
        }
    }
    // Approach B: marker lists for rows and columns, then zero the marked ones in place.
    // O(n*m) time, O(n+m) space. KNOWN ISSUE: rowArr has m entries but needs n (see STATUS).
    private static void approach2(ArrayList<ArrayList<Integer>> matrix,int n, int m) {
        // rowArr[i] == 1 -> row i holds a zero; columnArr[j] == 1 -> column j holds a zero
        ArrayList<Integer> rowArr = new ArrayList<>(Collections.nCopies(m, 0));
        ArrayList<Integer> columnArr = new ArrayList<>(Collections.nCopies(m, 0));

        // pass 1: record where the zeros are (nothing is changed yet)
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix.get(i).get(j) == 0){
                    rowArr.set(i, 1);
                    columnArr.set(j, 1);
                }
            }
        }


        // pass 2: zero the marked rows
        for(int i = 0; i < n; i++){
            if(rowArr.get(i) == 1){
                makeRowZero(m, i, matrix);
            }
        }

        // pass 3: zero the marked columns
        for(int j = 0; j < m; j++){
            if(columnArr.get(j) == 1){
                makeColumnZero(n, j, matrix);
            }
        }

        System.out.println(matrix);
    }



    // Unused int[][] version of approach B (here rowArr is correctly sized n, columnArr m).
    private static void approach2Primitive(int[][] matrix,int n, int m) {
        int[] rowArr = new int[n];
        int[] columnArr = new int[m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 0){
                    rowArr[i] = 1;
                    columnArr[j] = 1;
                }
            }
        }


        for(int i = 0; i < n; i++){
            if(rowArr[i] == 1){
                makeRowZeroPrimitive(m, i, matrix);
            }
        }

        for(int j = 0; j < m; j++){
            if(columnArr[j] == 1){
                makeColumnZeroPrimitive(n, j, matrix);
            }
        }
    }

    // Approach A: copy the matrix, then for every zero of the ORIGINAL matrix zero its row and
    // column in the copy, and print the copy. The input stays unchanged.
    // O(n*m*(n+m)) time, O(n*m) space.
    private static void approach1(ArrayList<ArrayList<Integer>> matrix,int n, int m) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        // deep copy: every row is copied, not just the outer list
        for (ArrayList<Integer> row : matrix) {
            arr.add(new ArrayList<>(row));
        }
        for(int i = 0; i < n; i++){
          for(int j = 0; j < m; j++){
             // read the zeros from the untouched matrix, write into the copy
             if(matrix.get(i).get(j) == 0){
                 returnMakeZeros(n, m, i, j, arr);
             }
          }
       }
       System.out.println(arr);
    }

    // Unused int[][] version of approach A that writes into the input itself: the zeros are
    // read from a copy and the row / column are zeroed in `matrix`.
    private static void approachPrimitive(int[][] matrix,int n, int m) {
        int[][] copy = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(copy[i][j] == 0){
                    returnMakeZerosPrimitive(n, m, i, j, matrix);
                }
            }
        }
    }


    // int[][] helper (unused path): zero row i and column j of matrix.
    public static void returnMakeZerosPrimitive(int n, int m, int i, int j, int[][] matrix){
        makeRowZeroPrimitive(m, i, matrix);
        makeColumnZeroPrimitive(n, j, matrix);
    }


    // int[][] helper (unused path): zero one row; arrSize = number of columns.
    private static void makeRowZeroPrimitive(int arrSize, int index, int[][] matrix) {
        for(int k = 0; k < arrSize; k++){
            matrix[index][k] = 0;
        }
    }

    // int[][] helper (unused path): zero one column; arrSize = number of rows.
    private static void makeColumnZeroPrimitive(int arrSize, int index, int[][] matrix) {
        for(int k = 0; k < arrSize; k++){
            matrix[k][index] = 0;
        }
    }
    // Zero row i and column j of arr (n rows, m columns).
    public static void returnMakeZeros(int n, int m, int i, int j, ArrayList<ArrayList<Integer>> arr){
        makeRowZero(m, i, arr);
        makeColumnZero(n, j, arr);
    }
}
