package SDE.DailyChallenge;

public class MaximumManhattanDistAfterKChanges {
    public static void main(String[] args) {
        System.out.println(maxDistance("NSWWEW", 3));
    }
    public static int maxDistance(String s, int k) {
        int north = 0;
        int south = 0;
        int east = 0;
        int west = 0;
        int ans = 0;
        int times1;
        int times2;
        for (char it : s.toCharArray()){
            switch (it){
                case 'N':
                    north ++;
                    break;
                case 'S':
                    south ++;
                    break;
                case 'W':
                    west ++;
                    break;
                case 'E':
                    east ++;
                    break;
            }
            times1 = Math.min(Math.min(north, south), k);
            times2 = Math.min(Math.min(west, east), k - times1);
            ans = Math.max(ans, count(north, south, times1) + count(east, west, times2));
        }
        return ans;
    }
    public static int count(int dir1, int dir2, int times){
        return Math.abs(dir1 - dir2) + times * 2;
    }
}
