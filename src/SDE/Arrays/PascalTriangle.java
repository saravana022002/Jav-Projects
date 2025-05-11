package SDE.Arrays;

import java.util.Arrays;

public class PascalTriangle {

    /**
     * You are given an integer ‘N’. You need to return the first ‘N’ rows of Pascal’s triangle.
     * <p>
     * Example:
     * <p>
     * Input:
     * N = 4
     * Output:
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * Explanation: The output matrix has the first four rows of Pascal’s Triangle.
     * Detailed explanation ( Input/output format, Notes, Images )
     * Constraints:
     * 1 <= N <= 30
     * Time Limit: 1 sec
     * Sample Input 1:
     * 5
     * Sample Output 1:
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * 1 4 6 4 1
     * Explanation Of Sample Input 1:
     * Input:
     * N = 5
     * <p>
     * Output:
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * 1 4 6 4 1
     * Explanation: The output matrix has the first five rows of Pascal’s Triangle.
     * Sample Input 2:
     * 3
     * Sample Output 2:
     * 1
     * 1 1
     * 1 2 1
     * Sample Input 3:
     * 4
     * Sample Output 3:
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     */
    public static void main(String[] args) {

        int N = 5;

        int[][] array = approachOwnCode(N);
        int[][] array2 = approachOptimal(N);
        System.out.println(Arrays.deepToString(array));
    }

    private static int[][] approachOwnCode(int N) {
        int[][] array = new int[N][];

        for (int i = 0; i <= N - 1; i++) {
            array[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j] + array[i - 1][j - 1];
                }
            }
        }
        return array;
    }


    private static int[][] approachOptimal(int N) {
        int[][] array = new int[N][];

        for (int i = 0; i < N - 1; i++) {

            array[i] = new int[i + 1];
            array[i][0] = 1;
            for (int j = 1; j < i; j++) {
                array[i][j] = array[i][j-1] * ((i+ 1- j) / j);
            }

        }

        return array;
    }
}
