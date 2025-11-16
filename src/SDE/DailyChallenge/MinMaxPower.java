package SDE.DailyChallenge;

public class MinMaxPower {

    public static void main(String[] args) {
        int[] stations = {1,2,4,5,0};
        int r = 1;
        int k = 2;
        maxPower(stations, r, k);
    }
    public static long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] cnt = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);
            cnt[left] += stations[i];
            cnt[right] -= stations[i];
        }
        return 0;
    }
}
