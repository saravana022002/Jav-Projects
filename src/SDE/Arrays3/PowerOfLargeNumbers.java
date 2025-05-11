package SDE.Arrays3;

public class PowerOfLargeNumbers {
    public static void main(String[] args) {
        double x = 2.00000;
        int n = -2147483648;
//        System.out.println(myPow(x, n));

          System.out.println(myPowApproach(x, n));


    }


    public static double myPowApproach(double x, int n) {

        long exp = n;
        double res = 1;
        if (exp > 0) {
            while (exp > 0) {
                if ((exp & 1) == 1) {
                    res = res * x;
                }
                x = x * x;
                exp >>= 1;
            }
        } else {
            exp = exp * (-1);
            while (exp > 0) {
                if ((exp & 1) == 1) {
                    res = res / x;
                }
                x = x * x;
                exp >>= 1;
            }
        }
        return res;
    }
    public static double myPow(double x, int n) {

        if (n == 0) {
            return 1;
        }
        if(n >= 0) {
            double result = myPow(x, n / 2);
            if (n % 2 == 0) {
                return result * result;
            } else {
                return result * result * x;
            }
        }else {
            n = -n;
            double result = myPow(x, -n / 2);
            if (n % 2 == 0) {
                return result * result;
            } else {
                return result * result * 1 / x;
            }
        }
    }
}
