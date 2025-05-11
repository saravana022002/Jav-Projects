package SDE.RecursionAndBackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens2 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(solveNQueens2(n));
    }
    public static int solveNQueens2(int n) {
        List<String> sum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            sum.add(new String(row));
        }
        boolean[] upperDiagonal = new boolean[2*n - 1];
        boolean[] lowerDiagonal = new boolean[2*n - 1];
        boolean[] leftRow = new boolean[n];

        return solveQ( n, 0, sum, upperDiagonal, lowerDiagonal, leftRow);
    }


    public static int solveQ(int n, int col, List<String> sum, boolean[] upperDiagonal, boolean[] lowerDiagonal, boolean[] leftRow){
        if(col == n){
            return 1;
        }
        int count = 0;
        for (int row = 0; row < n; row++) {
            if(!upperDiagonal[n - 1 + row - col] && !lowerDiagonal[row + col] && !leftRow[row]){
                char[] rowArray = sum.get(row).toCharArray();
                rowArray[col] = 'Q';
                sum.set(row, new String(rowArray));
                upperDiagonal[n - 1 + row - col] = true;
                lowerDiagonal[row + col]  = true;
                leftRow[row] = true;
                sum.set(row, new String(rowArray));
                count += solveQ(n, col + 1, sum, upperDiagonal, lowerDiagonal, leftRow);
                rowArray[col] = '.';
                sum.set(row, new String(rowArray));
                upperDiagonal[n - 1 + row - col] = false;
                lowerDiagonal[row + col]  = false;
                leftRow[row] = false;
            }
        }
        return count;
    }

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
