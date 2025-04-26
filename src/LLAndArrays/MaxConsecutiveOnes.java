package LLAndArrays;

public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        findMaxConsecutiveOnes(nums);
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int currentMax = 0;
        for (int num : nums) {
            if (num != 1) {
                currentMax = 0;
            } else {
                currentMax++;
                max = Math.max(max, currentMax);
            }
        }
        return max;
    }
}
