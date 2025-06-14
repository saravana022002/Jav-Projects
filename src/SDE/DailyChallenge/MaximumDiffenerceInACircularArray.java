package SDE.DailyChallenge;

public class MaximumDiffenerceInACircularArray {
    public static void main(String[] args) {

    }
    public int maxAdjacentDistance(int[] nums) {
        int max = Integer.MIN_VALUE;
        int comp;
        for (int i = 0; i < nums.length - 1; i++) {
            comp = Math.abs(nums[i] - nums[i + 1]);
            if(comp > max){
                max = comp;
            }
        }

        int edge = Math.abs(nums[nums.length - 1] - nums[0]);
        if(edge > max){
            max = edge;
        }
        return max;
    }

}
