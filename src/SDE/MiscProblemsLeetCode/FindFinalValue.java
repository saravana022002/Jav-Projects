package SDE.MiscProblemsLeetCode;

public class FindFinalValue {

    public static void main(String[] args) {
        int[] nums = {5,3,6,1,12};
        int original = 3;
        System.out.println(findFinalValue(nums, original));
    }
    public static int findFinalValue(int[] nums, int original) {

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == original){
                original = original * 2;
                i = -1;
            }
        }
        return original;
    }
}
