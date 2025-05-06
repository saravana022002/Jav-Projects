package BinarySearch;

public class Sqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
    public static int mySqrt(int x) {
        int l = 0;
        int r = x / 2;
        int mid = 0;
        long square = 0;
        long square1 = 0;
        while(l <= r){
            mid = (l + r) / 2;
            square = (long) mid * mid;
            if(square < x){
                square1 = (long) (mid + 1) * (mid + 1);
                if(square1 > x){
                    return mid;
                }else if(square1 == x){
                    return mid + 1;
                }
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return mid;
    }



}
