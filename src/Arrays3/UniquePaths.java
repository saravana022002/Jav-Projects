package Arrays3;

public class UniquePaths {

    public static void main(String[] args) {

        int m = 17;
        int n = 18;

        System.out.println(System.currentTimeMillis());
        System.out.println(uniquePaths(0,0, m, n));
        System.out.println(System.currentTimeMillis());
    }

    public static int uniquePaths(int i, int j, int m, int n, ) {
        if(i == (m - 1) && j == (n - 1)){
            return 1;
        }
        if(j >= n || i  >= m){
            return 0;
        }else {
            return uniquePaths(i, j + 1, m, n) + uniquePaths(i + 1, j, m, n);
        }
    }

}
