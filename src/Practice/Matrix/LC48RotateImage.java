package Practice.Matrix;

import java.util.Arrays;

// ============================================================================
// LC 48. Rotate Image   [Medium]
// https://leetcode.com/problems/rotate-image/
// Topics: Array, Math, Matrix
// ============================================================================
//
// PROBLEM
//   Rotate an n x n matrix (an image) by 90 degrees clockwise, in place: the given matrix
//   itself must be changed, and a second 2D matrix must not be allocated.
//
// EXAMPLES
//   [[1,2,3],[4,5,6],[7,8,9]]  ->  [[7,4,1],[8,5,2],[9,6,3]]
//   [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//                              ->  [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
//   (the second one is what main() rotates)
//
// CONSTRAINTS
//   n == matrix.length == matrix[i].length,   1 <= n <= 20,   -1000 <= matrix[i][j] <= 1000
//
// INTUITION
//   After a clockwise quarter turn the NEW top row is the OLD left column read from bottom
//   to top. Two easy in-place moves produce that:
//     1. transpose (mirror over the main diagonal): the old left column becomes the top row,
//        but it reads top to bottom;
//     2. reverse every row: now it reads bottom to top.
//   Both steps only swap elements inside the matrix, so no extra matrix is needed.
//
// APPROACHES IN THIS FILE
//   A. rotate(matrix)                - builds a NEW matrix with result[i][j] = matrix[n-1-j][i]
//                                      - O(n^2) time, O(n^2) space; not in place, and main()
//                                        only has its call commented out
//   B. rotateArrayOptimistic(matrix) - transpose, then reverse each row, all in place
//                                      - O(n^2) time, O(1) space   <- main() runs this one
//   (APPROACH and DRY RUN below describe B.)
//
// APPROACH (as implemented below, rotateArrayOptimistic)
//   1. matSize = n. Transpose: for every i and every j >= i, swap matrix[i][j] with
//      matrix[j][i]. Starting j at i swaps each off-diagonal pair exactly once (a diagonal
//      cell just swaps with itself).
//   2. half = number of swaps per row: matSize / 2 for even n, matSize / 2 + 1 for odd n.
//   3. Reverse every row: for every i and every j < half, swap matrix[i][matSize - j - 1]
//      with matrix[i][j]. (For odd n the middle cell is swapped with itself - harmless.)
//   4. Return the same matrix.
//
// DRY RUN   main()'s matrix (4 x 4)
//   start        [5,1,9,11]   [2,4,8,10]   [13,3,6,7]   [15,14,12,16]
//   transpose    [5,2,13,15]  [1,4,3,14]   [9,8,6,12]   [11,10,7,16]
//   reverse rows [15,13,2,5]  [14,3,4,1]   [12,6,8,9]   [16,7,10,11]     <- the expected output
//   (3 x 3 check: [[1,2,3],[4,5,6],[7,8,9]] -> transpose [[1,4,7],[2,5,8],[3,6,9]] ->
//   reverse rows [[7,4,1],[8,5,2],[9,6,3]].)
//
// COMPLEXITY
//   B: Time O(n^2) - about n^2 / 2 swaps for the transpose and n^2 / 2 for the reversal;
//      Space O(1) - one temp variable
//   A: Time O(n^2);  Space O(n^2) for the new matrix
//
// GOTCHAS
//   - The order matters. Transpose THEN reverse each row is clockwise; reversing the rows
//     first and transposing afterwards would turn the matrix counter-clockwise.
//   - The transpose loop must start at j = i. Starting at j = 0 swaps every pair twice and
//     undoes the transpose.
//   - half is ceil(n / 2), one swap more than a reversal needs when n is odd; it only swaps
//     the middle cell with itself.
//   - Both methods return a matrix (LeetCode's signature is void). B returns the same array
//     it changed; A returns a new one and leaves its input as it was.
//   - main() ignores the returned matrix and prints nothing, but arr has been rotated.
//
// RELATED
//   - LC 1886 Determine Whether Matrix Can Be Obtained By Rotation - asks whether rotating by
//     multiples of 90 degrees turns one matrix into another.
//   - LC 73 Set Matrix Zeroes - another in-place matrix transformation
//     (LC73SetMatrixZeroes.java in this folder).
// ============================================================================
public class LC48RotateImage {
    public static void main(String[] args) {

        // LeetCode example 2. The result is not printed; arr ends up as
        // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]] after the call below
        int[][] arr = { {5,1,9,11},
                        {2,4,8,10},
                        {13,3,6,7},
                        {15,14,12,16}};
//        System.out.println(SDE.Arrays.deepToString(rotate(arr)));
        rotateArrayOptimistic(arr);

//        for (int[] arrsub : arr) {
//            System.out.println(SDE.Arrays.toString(arrsub));
//        }

    }
    // Approach A: copy into a NEW matrix, result[i][j] = matrix[n-1-j][i]. O(n^2) time and
    // space; not in place, so it does not meet the problem's rule. The input stays unchanged.
    public static int[][] rotate(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            // count walks up the source column i, from the bottom row to the top row
            int count = matrix.length-1;
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[count][i];
                count --;
            }
        }
        return result;
    }

    // Approach B: rotate clockwise in place = transpose, then reverse every row.
    // O(n^2) time, O(1) space.
    public static int[][] rotateArrayOptimistic(int[][] matrix) {
        int temp;
        int matSize = matrix.length;
        // step 1: transpose - swap matrix[i][j] and matrix[j][i] for j >= i
        for (int i = 0; i < matSize; i++) {
            for (int j = i; j < matSize; j++) {
                temp =  matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // number of swaps per row (ceil(n / 2))
        int half = matSize%2 == 1? (matSize/2)+1 : (matSize/2);

        // step 2: reverse every row by swapping mirrored cells
        for (int i = 0; i < matSize; i++) {
            for (int j = 0; j < half; j++) {
                temp =  matrix[i][matSize - j - 1];
                matrix[i][matSize - j - 1] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        return matrix;
    }


// LeetCode example 2 (the owner's copy), input and expected output:
//    Input: matrix = [[5,1,9,11],
//                     [2,4,8,10],
//                     [13,3,6,7],
//                     [15,14,12,16]]
//
//    Output:         [[15,13,2,5],
//                     [14,3,4,1],
//                     [12,6,8,9],
//                     [16,7,10,11]]
}
