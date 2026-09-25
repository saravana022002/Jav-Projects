package Leetcode.BitManipulationAsASetEncoding5;

public class LC231IsPowerOfTwo {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(-16));
    }
    public static boolean isPowerOfTwo(int n) {
        if(n == 0){
            return false;
        }
        n =  n - 1;
        while(n > 0){
            if((n & 1) == 0){
                return false;
            }
            n = n >> 1;
        }
        return true;
    }


    public static boolean isPowerOfTwoOptimal(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
