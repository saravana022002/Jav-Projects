package SDE.DailyChallenge;

public class SmallestBinNumber {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(smallestNumber(n));
    }
    public static int smallestNumber(int n) {
        int count = 0;
        while (n > 0){
            count ++;
            n = n / 2;
        }
        int ans = 0;
        while (count > 0){
            ans = ans * 2 + 1;
            count--;
        }
        return ans;
    }
}
