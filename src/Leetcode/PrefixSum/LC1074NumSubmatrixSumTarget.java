package Leetcode.PrefixSum;

import java.util.StringTokenizer;

public class LC1074NumSubmatrixSumTarget {
    public static void main(String[] args) {
        int[][] matrix =  {{0,1,0},{1,1,1},{0,1,0}};
        int  target = 0;
        System.out.println(numSubmatrixSumTarget(matrix , target));
    }
    
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int[][] prefixSum = new int[matrix.length][matrix[0].length];
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                prefixSum[i][j] = matrix[i][j] + (i > 0 ? prefixSum[i - 1][j] : 0) + (j > 0 ? prefixSum[i][j - 1] : 0) - ((Math.min(i, j) > 0) ? prefixSum[i - 1][j - 1] : 0);
            }
        }
        int count = 0;
        for (int r1 = 0; r1 < rowSize; r1++) {
            for (int c1 = 0; c1 < colSize; c1++) {
                for (int r2 = r1; r2 < rowSize; r2++) {
                    for (int c2 = c1; c2 < colSize; c2++) {
                        int left = c1 > 0 ? prefixSum[r2][c1 - 1] : 0;
                        int top = r1 > 0 ? prefixSum[r1 - 1][c2] : 0;
                        int topLeft = Math.min(r1, c1) > 0 ? prefixSum[r1 - 1][c1 - 1] : 0;
                        count += prefixSum[r2][c2] - left - top + topLeft == target ? 1 : 0;
                    }
                }
            }
        }
        return count;
    }
}
