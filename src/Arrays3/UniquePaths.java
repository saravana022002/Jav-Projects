package Arrays3;

import java.util.Arrays;

public class UniquePaths {

    public static void main(String[] args) {

        int m = 7;
        int n = 3;


        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], -1);
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(uniquePaths(0,0, m, n, ans));
        System.out.println(System.currentTimeMillis());
    }

    public static int uniquePaths(int i, int j, int m, int n, int[][] ans) {
        if(i < m && j < n && ans[i][j] != -1){
            return ans[i][j];
        }
        if(i == (m - 1) && j == (n - 1)){
            ans[i][j] = 1;
            return 1;
        }
        if(j >= n || i  >= m){
            return 0;
        }else {
            ans[i][j] = uniquePaths(i, j + 1, m, n, ans) + uniquePaths(i + 1, j, m, n, ans);
            return ans[i][j];
        }
    }

}
