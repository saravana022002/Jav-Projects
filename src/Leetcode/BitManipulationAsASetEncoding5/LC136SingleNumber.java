package Leetcode.BitManipulationAsASetEncoding5;

public class LC136SingleNumber {
    public static void main(String[] args) {

    }

    public int singleNumber(int[] nums) {
        int num = 0;
        for (int j : nums) {
            num ^= j;
        }
        return num;
    }
}
