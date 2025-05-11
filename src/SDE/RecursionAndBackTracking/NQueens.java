package SDE.RecursionAndBackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        solveNQueens(n);
    }
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        List<String> sum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            sum.add(new String(row));
        }

        boolean[] upperDiagonal = new boolean[2*n - 1];
        boolean[] lowerDiagonal = new boolean[2*n - 1];
        boolean[] leftRow = new boolean[n];
        solveQ(res, n, 0, sum, upperDiagonal, lowerDiagonal, leftRow);
        return res;
    }


    public static void solveQ(List<List<String>> res, int n, int col, List<String> sum, boolean[] upperDiagonal, boolean[] lowerDiagonal, boolean[] leftRow){
        if(col == n){
            res.add(new ArrayList<>(sum));
            return;
        }

        for (int row = 0; row < n; row++) {
            if(!upperDiagonal[n - 1 + row - col] && !lowerDiagonal[row + col] && !leftRow[row]){
                char[] rowArray = sum.get(row).toCharArray();
                rowArray[col] = 'Q';
                sum.set(row, new String(rowArray));
                upperDiagonal[n - 1 + row - col] = true;
                lowerDiagonal[row + col]  = true;
                leftRow[row] = true;
                solveQ(res, n, col + 1, sum, upperDiagonal, lowerDiagonal, leftRow);
                rowArray[col] = '.';
                sum.set(row, new String(rowArray));
                upperDiagonal[n - 1 + row - col] = false;
                lowerDiagonal[row + col]  = false;
                leftRow[row] = false;
            }
        }
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
