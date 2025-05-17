package AToZ.LearnTheBasics.KnowBasicmaths;

public class ReverseInteger {
    public static void main(String[] args) {
        int x = -123;
        System.out.println(reverse(x));
    }
    public static int reverse(int x) {
        int remainder;
        int res = 0;
        while(x != 0){
            remainder = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && remainder > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && remainder < -8)) {
                return 0;
            }
            res = res * 10 + remainder;
        }
        return res;
    }
}
