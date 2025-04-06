package Contest;

public class MinimumPairRemoval {
    public static void main(String[] args) {
        int[] input = {1,2,2};
        minimumPairRemoval(input);
    }

    public static int minimumPairRemoval(int[] nums) {
        int currentSum = 0;
        int counter = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            if(nums.length - 1 == i + 1) {
                currentSum = nums[i + 1];
                if(nums[i] <= currentSum){
                    counter++;
                    continue;
                }
            }else {
                currentSum = nums[i] + nums[i + 1];
            }
            if(nums[i - 1] <= currentSum){
                nums[i] = currentSum;
                nums[i + 1] = -nums[i + 1];
                counter ++;
            }
        }
        return counter;
    }
}
