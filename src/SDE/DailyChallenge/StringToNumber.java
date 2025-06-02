package SDE.DailyChallenge;

public class StringToNumber {
    public static void main(String[] args) {
       String s = "1337c0d3";
       System.out.println(myAtoi(s));
    }
    public static int myAtoi(String s) {
        int i = 0, n = s.length();;
        while (i < n && s.charAt(i) == ' '){
            i++;
        }
        boolean isNegative = false;
        if(s.charAt(i) == '-'){
            isNegative = true;
            i++;
        }else if (s.charAt(i) == '+'){
            i++;
        }
        int sum = 0;
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0';
            if(sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && digit > 7)){
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            sum = sum * 10 + digit;
            i++;
        }
        return isNegative ? -sum : sum;
    }
}
