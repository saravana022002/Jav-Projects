package SDE.Arrays2;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {

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
    public static int[][] rotate(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int count = matrix.length-1;
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[count][i];
                count --;
            }
        }
        return result;
    }

    public static int[][] rotateArrayOptimistic(int[][] matrix) {
        int temp;
        int matSize = matrix.length;
        for (int i = 0; i < matSize; i++) {
            for (int j = i; j < matSize; j++) {
                temp =  matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        int half = matSize%2 == 1? (matSize/2)+1 : (matSize/2);

        for (int i = 0; i < matSize; i++) {
            for (int j = 0; j < half; j++) {
                temp =  matrix[i][matSize - j - 1];
                matrix[i][matSize - j - 1] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        return matrix;
    }


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
