package RecursionAndBackTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RatInAMaze {
    public static void main(String[] args) {
        int N = 4;
        int[][] m = {{1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}};

        List<String> ans = new ArrayList<>();
        List<Character> str = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        mazeSolver(0, 0, N, m, ans, str, visited);
        System.out.println(ans);
    }

    public static void mazeSolver(int i, int j, int n, int[][] m, List<String> ans, List<Character> str, Set<String> visited){
        if(i == (n - 1) && j == (n - 1)){
            StringBuilder sb = new StringBuilder();
            for (char ch : str) {
                sb.append(ch);
            }
            ans.add(sb.toString());
            return;
        }
        if (i >= n || j >= n || i < 0 || j < 0 || m[i][j] == 0 || visited.contains(i + "," + j)) {
            return;
        }
        visited.add(i + "," + j);

        str.add('R');
        mazeSolver(i, j + 1, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        str.add('D');
        mazeSolver(i + 1, j, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        str.add('L');
        mazeSolver(i, j - 1, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        str.add('U');
        mazeSolver(i - 1, j, n, m, ans, str, visited);
        str.remove(str.size() - 1);

        visited.remove(i + "," + j);
    }
}
