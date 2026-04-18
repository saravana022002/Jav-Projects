package Posh;

import java.util.*;

public class LongestPalindrome {
    public static void main(String[] args) {
//        String str = "cbbd";
//        longestPalindrome(str);
//
        Character [][] a = new Character[5000][3];
        int c=  colour(a, 0, 0);
        System.out.println(c);
//        for (Character[][] array : allList) {
//            System.out.println(Arrays.deepToString(array));
//        }
    }

    private static Integer total = 0;
    private static final List<Character[][]> allList = new ArrayList<>();
    public static int colour(Character[][] array, int row, int column) {
        if(array.length < row || array[0].length < column) {
            return total;
        }


        if((column == 0 || (column > 0 && array[row][column - 1] != 'R')) && (row == 0 || (row > 0 && array[row-1][column] != 'R'))) {

            int locRow = row, locCol = column;
            Character[][] test = deepCopy(array);
            test[locRow][locCol] = 'R';

            if(array.length == row+1 && array[0].length == column+1) {
                total++;
                allList.add(test);
            } else {
                if(array[0].length == column+1) {
                    locCol = 0;
                    locRow++;
                } else {
                    locCol++;
                }

                colour(deepCopy(test), locRow, locCol);
            }
        }

        if((column == 0 || (column > 0 && array[row][column - 1] != 'G')) && (row == 0 || (row > 0 && array[row-1][column] != 'G'))) {
            int locRow = row, locCol = column;
            Character[][] test = deepCopy(array);
            test[locRow][locCol] = 'G';

            if(array.length == row+1 && array[0].length == column+1) {
                total++;
                allList.add(test);
            } else {
                if(array[0].length == column+1) {
                    locCol = 0;
                    locRow++;
                } else {
                    locCol++;
                }
                colour(deepCopy(test), locRow, locCol);
            }
        }
        if((column == 0 || (column > 0 && array[row][column - 1] != 'Y')) && (row == 0 || (row > 0 && array[row-1][column] != 'Y'))) {
            int locRow = row, locCol = column;
            Character[][] test = deepCopy(array);
            test[locRow][locCol] = 'Y';

            if(array.length == row+1 && array[0].length == column+1) {
                total++;
                allList.add(test);
            }  else {
                if(array[0].length == column+1) {
                    locCol = 0;
                    locRow++;
                } else {
                    locCol++;
                }
                colour(deepCopy(test), locRow, locCol);
            }
        }

        return total;
    }

    static Character[][] deepCopy(Character[][] src) {
        Character[][] copy = new Character[src.length][3];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone(); // clone each row
        }
        return copy;
    }

    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        String longest = "";
        int l;
        int r;
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            l = i;
            r = i;
            while(l >= 0 && r < chars.length && chars[l] == chars[r]){
                if(maxLength <= r - l){
                    longest = s.substring(l, r + 1);
                    maxLength = r - l;
                }
                l--;
                r++;
            }
            l = i;
            r = i+1;
            while(l >= 0 && r < chars.length && chars[l] == chars[r]){
                if(maxLength <= r - l){
                    longest = s.substring(l, r + 1);
                    maxLength = r - l;
                }
                l--;
                r++;
            }
        }
        return longest;
    }
}
