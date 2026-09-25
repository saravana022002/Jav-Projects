package Leetcode.BitManipulationAsASetEncoding5;

public class LC191NumberFo1Bits {
    public static void main(String[] args) {
        int n = 11;
        System.out.println(hammingWeight(n));
    }
    public static int hammingWeight(int n) {
        int temp;
        int count = 0;
        while(n > 0){
            temp = n - 1;
            n = n & temp;
            count++;
        }
        return count;
    }
}
