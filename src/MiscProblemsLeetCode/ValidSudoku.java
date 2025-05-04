package MiscProblemsLeetCode;

import java.util.Arrays;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(solveSudoku(board));
    }

    public boolean isValidSudoku(char[][] board) {
        return solveSudoku(board);
    }

    public static boolean solveSudoku(char[][] board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if(!isValid(board, i, j, board[i][j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isValid(char[][] board, int row, int col, char k) {
        for (int i = 0; i < 9; i++) {

            if(board[row][i] == k && i != col){
                return false;
            }
            if(board[i][col] == k && i != row){
                return false;
            }

            int r = 3 * (row / 3) + i / 3;
            int c = 3 * (col / 3) + i % 3;
            if(board[r][c] == k && r != row && c != col){
                return false;
            }
        }
        return true;
    }
}
