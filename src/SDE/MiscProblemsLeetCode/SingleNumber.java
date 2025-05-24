package SDE.MiscProblemsLeetCode;

public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {2,2,1};
        System.out.println(singleNumber(nums));
    }
    public static int singleNumber(int[] nums) {
        int num = 0;
        for (int j : nums) {
            num = num ^ j;
        }
        return num;
    }

}
