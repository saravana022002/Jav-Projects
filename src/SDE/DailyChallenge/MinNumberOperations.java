package SDE.DailyChallenge;

public class MinNumberOperations {
    public static void main(String[] args) {
        int[] target = {};
        System.out.println(minNumberOperations(target));
    }


    public static int minNumberOperations(int[] target) {
        if(target.length == 0){
            return 0;
        }
        int ans = target[0];
        for (int i = 1; i < target.length; i++) {
            if(target[i - 1] < target[i]){
                ans += target[i] - target[i - 1];
            }
        }
        return ans;
    }

}
