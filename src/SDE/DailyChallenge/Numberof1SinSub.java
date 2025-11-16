package SDE.DailyChallenge;

public class Numberof1SinSub {
    public static void main(String[] args) {
        String s = "10011";
        System.out.println(numSub(s));
    }
    public static int numSub(String s) {
        char[] chars = s.toCharArray();
        int start = -1;
        int end = -1;
        int ans = 0;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if(chars[i] == '1') {
                start = i;
                while ( i < length && chars[i] != '0') {
                    i++;
                }
                end = i;
                ans += ((end - start) * (end - start + 1)) / 2;
                ans %= (int) (Math.pow(10, 9) + 7);
            }
        }
        return ans;
    }

}
