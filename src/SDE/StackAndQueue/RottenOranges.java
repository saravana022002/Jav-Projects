package SDE.StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        int [][] grid = {{2,1,1},{1,0,0},{0,0,1}};
        orangesRotting(grid);
    }

    static class Pair{
        int row;
        int col;
        int sec;
        public Pair(int row, int col, int sec){
            this.row = row;
            this.col = col;
            this.sec = sec;
        }
    }
    public static int orangesRotting(int[][] grid) {
        Queue<Pair> queue = new LinkedList<>();

        int n = grid.length;
        int m = grid[0].length;


        int[][] vis = new int[n][m];
        int cntFresh = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 2){
                    queue.add(new Pair(i, j, 0));
                    vis[i][j] = 2;
                }else {
                    vis[i][j] = 0;
                }
                if(grid[i][j] == 1){
                    cntFresh ++;
                }
            }
        }

        int tm = 0;
        int cnt = 0;
        while (!queue.isEmpty()){
            int r = queue.peek().row;
            int c = queue.peek().col;
            int sec = queue.peek().sec;
            tm = Math.max(tm, sec);
            queue.poll();
            // top
            if (r - 1 >= 0 && vis[r - 1][c] != 2 && grid[r - 1][c] == 1){
                queue.add(new Pair(r - 1, c, sec + 1));
                vis[r - 1][c] = 2;
                cnt++;
            }
            // left
            if (c - 1 >= 0 && vis[r][c - 1] != 2 && grid[r][c - 1] == 1){
                queue.add(new Pair(r, c - 1, sec + 1));
                vis[r][c - 1] = 2;
                cnt++;
            }
            // bottom
            if (r + 1 <= n - 1 && vis[r + 1][c] != 2 && grid[r + 1][c] == 1){
                queue.add(new Pair(r + 1, c, sec + 1));
                vis[r + 1][c] = 2;
                cnt++;
            }
            // right
            if (c + 1 <= m - 1 && vis[r][c + 1] != 2 && grid[r][c + 1] == 1){
                queue.add(new Pair(r, c + 1, sec + 1));
                vis[r][c + 1] = 2;
                cnt++;
            }
        }

        if(cnt != cntFresh){
            return -1;
        }
        return tm;
    }
}
