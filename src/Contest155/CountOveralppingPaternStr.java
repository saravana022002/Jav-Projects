package Contest155;

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
        int size = grid.length;
        int jSize = grid[0].length;
        int patterSize = grid[0].length;

        boolean[][] horizontal = new boolean[size][jSize];
        boolean[][] vertical = new boolean[size][jSize];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < jSize; j++) {
                boolean match = true;
                for (int k = 0; k < patterSize; k++) {
                    int ni = (i + (j + k) / jSize);
                    int nj = (j % jSize);
                    if(ni >= size || pattern.charAt(k) != grid[ni][nj]){
                        match = false;
                        break;
                    }
                }
                if(match){
                    for (int k = 0; k < patterSize; k++) {
                        int ni = (i + (j + k) / jSize);
                        int nj = (j % jSize);
                        horizontal[ni][nj] = true;
                    }
                }
            }
        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < jSize; j++) {
                boolean match = true;
                for (int k = 0; k < patterSize; k++) {
                    int nj = (j + (i + k) / size);
                    int ni = (i + k) % size;
                    if (nj >= jSize || grid[ni][nj] != pattern.charAt(k)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int k = 0; k < patterSize; k++) {
                        int nj = (j + (i + k) / size);
                        int ni = (i + k) % size;
                        vertical[ni][nj] = true;
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < jSize; j++) {
                if (horizontal[i][j] && vertical[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
