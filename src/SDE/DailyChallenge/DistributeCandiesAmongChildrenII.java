package SDE.DailyChallenge;

public class DistributeCandiesAmongChildrenII {
    public static void main(String[] args) {
        int n = 3;
        int limit = 3;
        System.out.println(distributeCandies(n, limit));
    }
    public static long distributeCandies1(int n, int limit) {
        int cnt = 0;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit; j++) {
                int k = n - i - j;
                if(k <= limit && k >= 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static long distributeCandies(int n, int limit) {
        return combination(n) - 3 * combination(n - (limit + 1)) + 3 * combination(n - 2 * (limit + 1)) - combination(n - 3 * (limit + 1));
    }

    private static long combination(int n) {
        if (n < 0) return 0;
        return (long)(n + 2) * (n + 1) / 2;
    }

}
