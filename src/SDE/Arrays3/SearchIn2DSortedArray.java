package SDE.Arrays3;

public class SearchIn2DSortedArray {
    public static void main(String[] args) {
        int[][] array = {{1,3,5,7},
                {10,11,16,20},
                {23,30,34,50}};

        int M = array.length;
        int N = array[0].length;
        int target = 11;

        System.out.println(isTargetFound(array, M, N, target));
    }

    public static boolean isTargetFound(int[][] array, int M, int N, int target){
        int rowStart = 0;
        int rowEnd = M - 1;
        int rowMid = 0;
        while (rowStart <= rowEnd){
            rowMid = ((rowStart + rowEnd ) / 2);
            if(array[rowMid][0] == target){
                return true;
            } else if (array[rowMid][0] < target) {
                rowStart = rowMid + 1;
            } else {
                rowEnd = rowMid - 1;
            }
        }

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
        return false;
    }
}
