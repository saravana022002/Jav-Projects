package SDE.DailyChallenge;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountUnguardedCells {
    public static void main(String[] args) {
        int m = 4;
        int n = 6;
        int[][] guards = {{0,0},{1,1},{2,3}};
        int[][] walls = {{0,1},{2,2},{1,4}};
        System.out.println(countUnguarded(m, n, guards, walls));
    }

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] prison = new int[m][n];
        for (int[] guard : guards){
            prison[guard[0]][guard[1]] = 2;
        }

        for (int[] wall : walls){
            prison[wall[0]][wall[1]] = 1;
        }


        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        for (int[] g : guards) {
            for (int[] d : dirs) {
                int x = g[0] + d[0];
                int y = g[1] + d[1];
                while (x >= 0 && x < m && y >= 0 && y < n && prison[x][y] != 1 && prison[x][y] != 2){
                    if(prison[x][y] == 0)
                        prison[x][y] = 3;
                    x += d[0];
                    y += d[1];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(prison[i][j] == 0){
                    ans++;
                }
            }
        }

        return ans;
    }








}
