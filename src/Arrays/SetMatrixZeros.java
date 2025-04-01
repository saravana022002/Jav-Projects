package Arrays;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
    Examples 1:
    Input:
    matrix=[[1,1,1],[1,0,1],[1,1,1]]

    Output:
            [[1,0,1],[0,0,0],[1,0,1]]

    Explanation:
    Since matrix[2][2]=0.Therfore the 2nd column and 2nd row wil be set to 0.

    Input:
    matrix=[[0,1,2,0],[3,4,5,2],[1,3,1,5]]

    Output:
            [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

    Explanation:
    Since matrix[0][0]=0 and matrix[0][3]=0. Therefore 1st row, 1st column and 4th column will be set to 0
  */
public class SetMatrixZeros {
    public static void main(String[] args) {

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

        approach1(matrix, n, m);
        approach2(matrix, n, m);
        approach3(matrix, n, m);
    }

    private static void approach3(ArrayList<ArrayList<Integer>> matrix, int n, int m) {

        int col0 = -1;

        matrix.forEach(ma -> System.out.println(ma+"\n"));
        System.out.println(col0);
        for(int i = 0; i < n; i++){

            for(int j = 0; j < m; j++){


                if(j == 0 && i == 0 && matrix.get(i).get(j) == 0){
                    col0 = 0;
                    matrix.get(0).set(0, 0);
                    continue;
                }

                if(i == 0 && matrix.get(i).get(j) == 0){
                    matrix.get(0).set(0, 0);
                    continue;
                }

                if(j == 0 && matrix.get(i).get(j) == 0){
                    col0 = 0;
                    continue;
                }

                if(matrix.get(i).get(j) == 0){
                    matrix.get(i).set(0, 0);
                    matrix.get(0).set(j, 0);
                }
            }
        }

        matrix.forEach(ma -> System.out.println(ma+"\n"));
        System.out.println(col0);
        for(int i = 0; i < n; i++){
            if(matrix.get(i).get(0) == 0){
                makeRowZero(m, i, matrix);
            }
        }

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


    private static void makeRowZero(int arrSize, int index, ArrayList<ArrayList<Integer>> arr) {
        for(int k = 0; k < arrSize; k++){
            arr.get(index).set(k, 0);
        }
    }

    private static void makeColumnZero(int arrSize, int index, ArrayList<ArrayList<Integer>> arr) {
        for(int k = 0; k < arrSize; k++){
            arr.get(k).set(index, 0);
        }
    }
    private static void approach2(ArrayList<ArrayList<Integer>> matrix,int n, int m) {
        ArrayList<Integer> rowArr = new ArrayList<>(Collections.nCopies(m, 0));
        ArrayList<Integer> columnArr = new ArrayList<>(Collections.nCopies(m, 0));

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix.get(i).get(j) == 0){
                    rowArr.set(i, 1);
                    columnArr.set(j, 1);
                }
            }
        }


        for(int i = 0; i < n; i++){
            if(rowArr.get(i) == 1){
                makeRowZero(m, i, matrix);
            }
        }

        for(int j = 0; j < m; j++){
            if(columnArr.get(j) == 1){
                makeColumnZero(n, j, matrix);
            }
        }

        System.out.println(matrix);
    }



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

    private static void approach1(ArrayList<ArrayList<Integer>> matrix,int n, int m) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (ArrayList<Integer> row : matrix) {
            arr.add(new ArrayList<>(row));
        }
        for(int i = 0; i < n; i++){
          for(int j = 0; j < m; j++){
             if(matrix.get(i).get(j) == 0){
                 returnMakeZeros(n, m, i, j, arr);
             }
          }
       }
       System.out.println(arr);
    }

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


    public static void returnMakeZerosPrimitive(int n, int m, int i, int j, int[][] matrix){
        makeRowZeroPrimitive(m, i, matrix);
        makeColumnZeroPrimitive(n, j, matrix);
    }


    private static void makeRowZeroPrimitive(int arrSize, int index, int[][] matrix) {
        for(int k = 0; k < arrSize; k++){
            matrix[index][k] = 0;
        }
    }

    private static void makeColumnZeroPrimitive(int arrSize, int index, int[][] matrix) {
        for(int k = 0; k < arrSize; k++){
            matrix[k][index] = 0;
        }
    }
    public static void returnMakeZeros(int n, int m, int i, int j, ArrayList<ArrayList<Integer>> arr){
        makeRowZero(m, i, arr);
        makeColumnZero(n, j, arr);
    }
}
