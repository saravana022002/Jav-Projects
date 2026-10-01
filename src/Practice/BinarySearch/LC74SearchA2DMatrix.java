package Practice.BinarySearch;

// ============================================================================
// LC 74. Search a 2D Matrix   [Medium]
// https://leetcode.com/problems/search-a-2d-matrix/
// Topics: Array, Binary Search, Matrix
// ============================================================================
//
// PROBLEM
//   matrix is an m x n grid of integers with two properties: every row is sorted in
//   non-decreasing order, and the first number of each row is bigger than the last number
//   of the row above. Return true if target occurs in the matrix, false otherwise, in
//   O(log(m * n)) time.
//
// EXAMPLES
//   matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3   ->  true
//   matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13  ->  false
//       13 would have to sit in the row starting with 10, and that row has no 13
//
// CONSTRAINTS
//   m == matrix.length,   n == matrix[i].length,   1 <= m, n <= 100,
//   -10^4 <= matrix[i][j], target <= 10^4
//
// INTUITION
//   The second property means that reading the matrix row after row gives ONE long sorted
//   list, so binary search applies. The code does it in two easy steps instead of index
//   arithmetic: only one row can possibly contain the target - the last row whose first
//   element is not bigger than it - so first binary search the FIRST COLUMN to find that
//   row, then binary search inside the row. O(log m + log n) = O(log(m * n)).
//
// APPROACH (as implemented below)
//   1. Row search on the first column: rowStart = 0, rowEnd = M - 1. array[rowMid][0] ==
//      target -> return true; smaller than target -> rowStart = rowMid + 1; bigger ->
//      rowEnd = rowMid - 1.
//   2. When that loop ends, rowEnd is the last row whose first element is smaller than
//      target (an equal one would have returned): the only row that can hold target.
//   3. Column search inside row rowEnd: a plain binary search with columnStart /
//      columnEnd / columnMid over its N entries; equal -> return true.
//   4. Not found -> return false.
//
// DRY RUN   main(): rows [1,3,5,7] / [10,11,16,20] / [23,30,34,50], target = 11
//   row search:    rowStart=0 rowEnd=2 rowMid=1: array[1][0] = 10 < 11 -> rowStart = 2
//                  rowStart=2 rowEnd=2 rowMid=2: array[2][0] = 23 > 11 -> rowEnd = 1
//                  rowStart 2 > rowEnd 1: the candidate row is 1
//   column search: columnStart=0 columnEnd=3 columnMid=1: array[1][1] = 11 == target
//                  -> return true
//
// STATUS
//   Known issue: a target SMALLER than matrix[0][0] makes the row search end with
//   rowEnd = -1, and the column search then reads array[-1][...] and throws an
//   ArrayIndexOutOfBoundsException instead of returning false. Such targets are valid
//   input: for the first example matrix, target = 0 crashes. Every other case is
//   answered correctly. The code is left unchanged on purpose.
//   Signature differs from LeetCode: the method is isTargetFound(array, M, N, target),
//   so the caller passes the row and column counts M and N.
//
// COMPLEXITY
//   Time : O(log M + log N) = O(log(M * N)) - one binary search over the rows, one over
//          the columns of a single row
//   Space: O(1)
//
// GOTCHAS
//   - The column search uses rowEnd, not rowStart: after the row loop rowStart has moved
//     one past the candidate row.
//   - The first property (each row starts above the previous row's end) is what makes one
//     row enough. Without it, a target could hide in several rows.
//   - A target below matrix[0][0] leaves rowEnd = -1 - see STATUS.
//
// RELATED
//   - LC 240 Search a 2D Matrix II - rows and columns are sorted, but a row does NOT start
//     above the previous row's end, so this two-step search does not apply there.
//   - LC 704 Binary Search (in this repo) - the plain one-dimensional version.
// ============================================================================
public class LC74SearchA2DMatrix {
    public static void main(String[] args) {
        // prints true (11 is in the second row)
        int[][] array = {{1,3,5,7},
                {10,11,16,20},
                {23,30,34,50}};

        int M = array.length;
        int N = array[0].length;
        int target = 11;

        System.out.println(isTargetFound(array, M, N, target));
    }

    // Returns true if target occurs in the matrix (M rows, N columns).
    public static boolean isTargetFound(int[][] array, int M, int N, int target){
        // step 1: binary search the first column for the row that could hold target
        int rowStart = 0;
        int rowEnd = M - 1;
        int rowMid = 0;
        while (rowStart <= rowEnd){
            rowMid = ((rowStart + rowEnd ) / 2);
            if(array[rowMid][0] == target){
                return true;
            } else if (array[rowMid][0] < target) {
                // row starts below target: this row or a later one
                rowStart = rowMid + 1;
            } else {
                // row starts above target: an earlier row
                rowEnd = rowMid - 1;
            }
        }

        // step 2: binary search inside row rowEnd (BUG: rowEnd is -1 when target < array[0][0])
        int columnStart = 0;
        int columnEnd = N - 1;
        int columnMid = 0;
        while (columnStart <= columnEnd){
            columnMid =(columnStart + columnEnd) / 2;
            if(array[rowEnd][columnMid] == target){
                return true;
            } else if (array[rowEnd][columnMid] < target) {
                columnStart = columnMid + 1;
            } else {
                columnEnd = columnMid - 1;
            }
        }
        // the candidate row does not contain target
        return false;
    }
}
