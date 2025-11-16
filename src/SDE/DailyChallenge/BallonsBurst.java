package SDE.DailyChallenge;

public class BallonsBurst {

    public static void main(String[] args) {
        String colors = "abaac";
        int[] neededTime = {1,2,3,4,5};
        System.out.println(minCost(colors, neededTime));
    }
    public static int minCost(String colors, int[] neededTime) {
        char[] arr = colors.toCharArray();
        int max;
        int count = 0;
        int ans = 0;
        boolean isContinuity = false;
        for (int i = 0; i < arr.length - 1; i++) {
            max = neededTime[i];
            count = neededTime[i];
            while (i + 1 < arr.length && arr[i] == arr[i + 1] ){
                max = Math.max(max, neededTime[i + 1]);
                count += neededTime[i + 1];
                isContinuity = true;
                i++;
            }
            if(isContinuity) {
                count -= max;
                ans += count;
                isContinuity = false;
            }
        }
        return ans;
    }
}
