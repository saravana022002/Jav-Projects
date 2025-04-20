package Contest.Apr20;

public class MakeArrayNonDecreasing {

    public static void main(String[] args) {
        int[] nums = {2,27,15};
        System.out.println(maximumPossibleSize(nums));
    }

    public static int maximumPossibleSize(int[] nums) {
        int i = 0;
        int count = 0;
        while(i < nums.length){
           int maxInGroup = nums[i];
           int j = i + 1;
           while(j < nums.length && nums[j] < maxInGroup){
               maxInGroup = Math.max(nums[j], maxInGroup);
               j++;
           }
           count++;
           i = j;
        }
        return count;
    }
}
