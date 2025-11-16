package SDE.Contest169;

public class CountMajoritySubarrays {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;
        countMajoritySubarrays(nums, target);
    }

    public static int countMajoritySubarrays(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n; i++){
            count = 0;
            for (int j = i; j < n; j++) {
                int size = j - i + 1;
                if(nums[j] == target){
                    count++;
                }
                if (size / 2 < count){
                    ans++;
                }
            }
        }
        return ans;
    }



}
