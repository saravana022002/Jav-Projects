package SDE.Contest155;

public class CountOveralppingPaternStr {
    public static void main(String[] args) {
        char[][] grid = {
                {'a', 'a', 'c', 'c'},
                {'b', 'b', 'b', 'c'},
                {'a', 'a', 'b', 'a'},
                {'c', 'a', 'a', 'c'},
                {'a', 'a', 'c', 'c'}
        };

        String pattern = "abaca";
        countCells(grid, pattern);
    }
    public static int countCells(char[][] grid, String pattern) {
        int m = grid.length;
        int n = grid[0].length;
        int patterSize = pattern.length();

        boolean[][] horizontal = new boolean[m][n];
        boolean[][] vertical = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean match = true;
                for (int k = 0; k < patterSize; k++) {
                    int ni = (i + (j + k) / n);
                    int nj = (j + k) % n;
                    if(ni >= m || pattern.charAt(k) != grid[ni][nj]){
                        match = false;
                        break;
                    }
                }
                if(match){
                    for (int k = 0; k < patterSize; k++) {
                        int ni = (i + (j + k) / n);
                        int nj = (j + k) % n;
                        horizontal[ni][nj] = true;
                    }
                }
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean match = true;
                for (int k = 0; k < patterSize; k++) {
                    int nj = (j + (i + k) / m);
                    int ni = (i + k) % m;
                    if (nj >= n || grid[ni][nj] != pattern.charAt(k)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int k = 0; k < patterSize; k++) {
                        int nj = (j + (i + k) / m);
                        int ni = (i + k) % m;
                        vertical[ni][nj] = true;
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (horizontal[i][j] && vertical[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
